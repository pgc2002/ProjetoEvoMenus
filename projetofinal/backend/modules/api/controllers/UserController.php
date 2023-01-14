<?php

namespace backend\modules\api\controllers;

use yii\data\ActiveDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;
use common\models\User;

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
