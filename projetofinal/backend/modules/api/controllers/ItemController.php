<?php

namespace backend\modules\api\controllers;

use backend\modules\api\Connection;
use common\models\Item;
use common\models\Menu;
use PhpMqtt\Client\MqttClient;
use yii\data\ActiveDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;

/**
 * Default controller for the `api` module
 */
class ItemController extends ActiveController
{
    public $modelClass = 'common\models\Item';

    public function actionAll(){
        $query = Item::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET de todos os items", 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionOne($id){
        $query = Menu::findOne($id);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do item ".$id, 0);
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
