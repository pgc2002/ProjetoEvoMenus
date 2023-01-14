<?php

namespace backend\modules\api\controllers;

use backend\modules\api\Connection;
use common\models\Categoria;
use common\models\Restaurante;
use PhpMqtt\Client\MqttClient;
use yii\data\ActiveDataProvider;
use yii\data\ArrayDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;

/**
 * Default controller for the `api` module
 */
class CategoriaController extends ActiveController
{
    public $modelClass = 'common\models\Categoria';

    public function actionAll(){
        $query = Categoria::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET de todas as categorias", 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionOne($idCategoria){
        $query = Categoria::findOne($idCategoria);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET da categoria ".$idCategoria, 0);
        $mqtt->disconnect();

        return $query;
    }
    
    public function actionMenus($idCategoria){
        $categoria = Categoria::find()->where(['id' => $idCategoria])->one();
        $menus = $categoria->getMenus()->all();

        $dataProvider = new ArrayDataProvider([
            'allModels' => $menus,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET dos menus da categoria ".$idCategoria, 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionItems($idCategoria){
        $categoria = Categoria::find()->where(['id' => $idCategoria])->one();
        $items = $categoria->getItems()->all();

        $dataProvider = new ArrayDataProvider([
            'allModels' => $items,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET dos items da categoria ".$idCategoria, 0);
        $mqtt->disconnect();

        return $dataProvider;
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
