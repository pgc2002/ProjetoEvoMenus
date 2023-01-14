<?php

namespace backend\modules\api\controllers;

use yii\data\ActiveDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;
use common\models\User;
use common\models\Morada;

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

        return $dataProvider;
    }

    public function actionMoradauser($idUtilizador){
        $User = User::findOne($idUtilizador);
        $Morada = $User->getMorada()->all();

        return $Morada;

    }

    public function actionPedidosuser($idUtilizador){
        $User = User::findOne($idUtilizador);
        $Pedidos = $User->getPedidos()->all();

        return $Pedidos;
    }


    public function actionCount(){
        $recs = User::find()->all();
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
    }

    public function actionAlterarmorada($idUser, $pais, $cidade, $rua, $codpost){
        $user = User::find()->where(['id' => $idUser])->one();
        $morada = Morada::find()->where(['id' => $user->idMorada])->one();
        $morada->pais = $pais;
        $morada->cidade = $cidade;
        $morada->rua = $rua;
        $morada->codpost = $codpost;
        $morada->save();
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
    }
}