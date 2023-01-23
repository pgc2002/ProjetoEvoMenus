<?php

namespace backend\modules\api\controllers;

use backend\modules\api\Connection;
use common\models\Horariofuncionamento;
use common\models\Mesa;

use common\models\Morada;
use common\models\Restaurante;
use common\models\Categoria;
use common\models\User;
use yii\data\ActiveDataProvider;
use yii\data\ArrayDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;
use \PhpMqtt\Client\MqttClient;

/**
 * Default controller for the `api` module
 */
class RestauranteController extends ActiveController
{
    public $modelClass = 'common\models\Restaurante';

    public function actionAll()
    {
        $query = Restaurante::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET de todos os restaurantes", 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionOne($id)
    {
        $query = Restaurante::findOne($id);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do restaurante " . $id, 0);
        $mqtt->disconnect();

        return $query;
    }

    public function actionCount()
    {
        $recs = Restaurante::find()->all();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do número de restaurantes", 0);
        $mqtt->disconnect();

        return count($recs);
    }

    public function actionMorada($id)
    {
        $restaurante = Restaurante::findOne($id);
        $morada = Morada::findOne($restaurante->idMorada);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET das morada do restaurante " . $id, 0);
        $mqtt->disconnect();

        return $morada;
    }

    public function actionMoradas()
    {
        $moradas = [];
        $Restaurantes = Restaurante::find()->all();

        foreach ($Restaurantes as $restaurante) {
            $morada = Morada::findOne($restaurante->idMorada);
            array_push($moradas, $morada);
        }

        $provider = new ArrayDataProvider([
            'allModels' => $moradas,
            'pagination' => false,
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET das moradas dos restaurantes", 0);
        $mqtt->disconnect();

        return $provider;
    }

    public function actionMesas($id)
    {
        $query = Mesa::find()->where(['idRestaurante' => $id]);

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET das mesas do restaurante " . $id, 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionNum_mesas_disponiveis($id)
    {
        $mesas = Mesa::find()->where(['idRestaurante' => $id])->all();

        $count = 0;

        foreach ($mesas as $mesa) {
            if ($mesa->estado == "Livre")
                $count++;
        }

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do número de mesas disponiveis do restaurante " . $id, 0);
        $mqtt->disconnect();

        return $count;
    }

    public function actionNum_mesas($id)
    {
        $query = Mesa::find()->where(['idRestaurante' => $id])->all();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do número de mesas do restaurante " . $id, 0);
        $mqtt->disconnect();

        return count($query);
    }

    public function actionHorario($id)
    {
        $restaurante = Restaurante::findOne($id);
        $horario = $restaurante->getHorario()->one();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do horário do restaurante " . $id, 0);
        $mqtt->disconnect();

        return $horario;
    }

    public function actionCategorias($id)
    {
        $query = Categoria::find()->where(['idRestaurante' => $id]);

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET das categorias do restaurante " . $id, 0);
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
