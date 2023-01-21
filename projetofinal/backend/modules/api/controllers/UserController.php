<?php

namespace backend\modules\api\controllers;

use backend\modules\api\Connection;
use common\models\Restaurante;
use common\models\User;
use yii\data\ActiveDataProvider;
use yii\rest\ActiveController;
use \PhpMqtt\Client\MqttClient;
use common\models\Morada;
use Yii;

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

    public function actionValidar($idUser, $password){
        $user = User::find()->where(['id' => $idUser])->one();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "Desencriptar pass do user ".$idUser, 0);
        $mqtt->disconnect();

        $pass = $password;

        return $user->validatePassword($pass);
    }

    public function actionOne($idUser){
        $query = User::findOne($idUser);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do user ".$idUser, 0);
        $mqtt->disconnect();

        return $query;
    }

    public function actionMorada($idUser){
        $user = User::findOne($idUser);
        $morada = $user->getMorada()->one();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET da morada do user ".$idUser, 0);
        $mqtt->disconnect();

        return $morada;
    }

    public function actionPedidos($idUser){
        $User = User::findOne($idUser);
        $Pedidos = $User->getPedidos()->all();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET dos pedidos do user ".$idUser, 0);
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

    public function actionCriar($username, $nome, $password, $email, $telemovel, $nif, $pais, $cidade, $rua, $codpost){
        $morada = new Morada();
        $morada->pais = $pais;
        $morada->cidade = $cidade;
        $morada->rua = $rua;
        $morada->codpost = $codpost;
        $morada->save(false);
        $user = new User();
        $user->username = $username;
        $user->nome = $nome;
        $user->setPassword($password);
        $user->email = $email;
        $user->telemovel = $telemovel;
        $user->nif = $nif;
        $user->tipo = "Cliente";
        $user->idMorada = $morada->id;
        $user->status = 10;
        $user->generateAuthKey();
        $user->save(false);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "POST de um user", 0);
        $mqtt->disconnect();
    }

    public function actionAlterarmorada($idUser, $pais, $cidade, $rua, $codpost){
        $user = User::find()->where(['id' => $idUser])->one();
        $morada = Morada::find()->where(['id' => $user->idMorada])->one();
        $morada->pais = $pais;
        $morada->cidade = $cidade;
        $morada->rua = $rua;
        $morada->codpost = $codpost;
        $morada->save();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "PUT de uma morada", 0);
        $mqtt->disconnect();
    }

    public function actionAlterarperfil($idUser, $username, $nome, $password, $email, $telemovel, $nif){
        $user = User::find()->where(['id' => $idUser])->one();
        $user->username = $username;
        $user->nome = $nome;
        $user->setPassword($password);
        $user->email = $email;
        $user->telemovel = $telemovel;
        $user->nif = $nif;
        $user->save();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "PUT de um user", 0);
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