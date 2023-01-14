<?php

namespace backend\modules\api\controllers;

use backend\modules\api\Connection;
use common\models\Mesa;
use common\models\User;
use PhpMqtt\Client\MqttClient;
use yii\data\ActiveDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;

/**
 * Default controller for the `api` module
 */
class MesaController extends ActiveController
{
    public $modelClass = 'common\models\Mesa';

    public function actionAll(){
        $query = Mesa::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET de todas as mesas", 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionOne($idMesa){
        $query = Mesa::findOne($idMesa);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET da mesa ".$idMesa, 0);
        $mqtt->disconnect();

        return $query;
    }

    public function actionCount($idRestaurante){
        $recs = Mesa::find()->where(['idRestaurante' => $idRestaurante])->all();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do número de mesas", 0);
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
