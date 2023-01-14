<?php

namespace backend\modules\api\controllers;

use backend\modules\api\Connection;
use common\models\Pais;
use common\models\User;
use PhpMqtt\Client\MqttClient;
use yii\data\ActiveDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;

/**
 * Default controller for the `api` module
 */
class PaisController extends ActiveController
{
    public $modelClass = 'common\models\Pais';

    public function actionAll(){
        $query = Pais::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET de todos os paises", 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionOne($id){
        $query = Pais::findOne($id);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do pais ".$id, 0);
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
