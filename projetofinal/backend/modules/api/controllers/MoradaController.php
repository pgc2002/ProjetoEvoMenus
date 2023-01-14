<?php

namespace backend\modules\api\controllers;

use backend\modules\api\Connection;
use common\models\Morada;
use PhpMqtt\Client\MqttClient;
use yii\data\ActiveDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;

/**
 * Default controller for the `api` module
 */
class MoradaController extends ActiveController
{
    public $modelClass = 'common\models\Morada';

    public function actionAll(){
        $query = Morada::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET de todas as moradas", 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionOne($id){
        $query = Morada::findOne($id);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET da morada ".$id, 0);
        $mqtt->disconnect();

        return $query;
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
