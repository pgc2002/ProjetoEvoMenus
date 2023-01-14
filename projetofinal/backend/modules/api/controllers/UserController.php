<?php

namespace backend\modules\api\controllers;

use backend\modules\api\Connection;
use common\models\Restaurante;
use common\models\User;
use yii\data\ActiveDataProvider;
use yii\rest\ActiveController;
use \PhpMqtt\Client\MqttClient;

/**
 * Default controller for the `api` module
 */
class UserController extends ActiveController
{
    public $modelClass = 'common\models\User';

    public function actionAll(){
        $query = User::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET de todos os users", 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionOne($id){
        $query = User::findOne($id);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do user ".$id, 0);
        $mqtt->disconnect();

        return $query;
    }

    public function actionMorada($id){
        $user = User::findOne($id);
        $morada = $user->getMorada()->one();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET da morada do user ".$id, 0);
        $mqtt->disconnect();

        return $morada;
    }

    public function actionPedidos($id){
        $User = User::findOne($id);
        $Pedidos = $User->getPedidos()->all();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET dos pedidos do user ".$id, 0);
        $mqtt->disconnect();

        return $Pedidos;
    }

    public function actionCount(){
        $recs = User::find()->where(['tipo' => 'Cliente'])->all();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do número de clientes", 0);
        $mqtt->disconnect();

        return count($recs);
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
