<?php

namespace backend\modules\api\controllers;

use backend\modules\api\Connection;
use common\models\Pagamento;
use common\models\User;
use PhpMqtt\Client\MqttClient;
use yii\data\ActiveDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;

/**
 * Default controller for the `api` module
 */
class PagamentoController extends ActiveController
{
    public $modelClass = 'common\models\Pagamento';

    public function actionAll(){
        $query = Pagamento::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET de todos os pagamentos", 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionOne($id){
        $query = Pagamento::findOne($id);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do pagamento ".$id, 0);
        $mqtt->disconnect();

        return $query;
    }

    public function actionCriar($idPedido, $valor, $metodo){
        $pagamento = new Pagamento();
        $pagamento->idPedido = $idPedido;
        $pagamento->valor = $valor;
        $pagamento->metodo = $metodo;
        $pagamento->save();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "POST de um pagamento", 0);
        $mqtt->disconnect();
    }

    /*public function behaviors() {
        return [
            [
                'class' => ContentNegotiator::className(),
                'only' => ['index', 'view'],
                'formats' => [
                    'application/json' => Response::FORMAT_JSON,
                ],
            ],
        ];
    }*/
}
