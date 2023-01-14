<?php

namespace backend\modules\api\controllers;

use common\models\Mesa;

use common\models\Morada;
use common\models\Restaurante;
use common\models\User;
use yii\data\ActiveDataProvider;
use yii\data\ArrayDataProvider;


use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;

/**
 * Default controller for the `api` module
 */
class RestauranteController extends ActiveController
{
    public $modelClass = 'common\models\Restaurante';

    public function actionAll(){
        $query = Restaurante::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        return $dataProvider;
    }

    public function actionCount(){
        $recs = Restaurante::find()->all();
        return count($recs);
    }

    public function actionMoradasrestaurantes(){
        $Moradas = [];
        $Restaurantes = Restaurante::find()->all();
        foreach($Restaurantes as $restaurante) {
            $morada = Morada::findOne($restaurante->idMorada) ;
            array_push($Moradas, $morada);
        }
        $provider = new ArrayDataProvider([
            'allModels' => $Moradas,
            'pagination' => false,
        ]);
        return $provider;
    }

    public function actionMesasrestaurantes(){
        $Mesas = [];
        $Restaurantes = Restaurante::find()->all();
        foreach($Restaurantes as $restaurante) {
            $mesa = Mesa::find()->where(['idRestaurante'=> $restaurante->id])->all();
            array_push($Mesas, $mesa);
        }
        $provider = new ArrayDataProvider([
            'allModels' => $Mesas,
            'pagination' => false,
        ]);
        return $provider;

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

    public function actionMesasdisponiveis($idRestaurante){
        $mesas = Mesa::find()->where(['idRestaurante' => $idRestaurante])->all();

        $count = 0;

        foreach($mesas as $mesa){
            if ($mesa->estado == "Livre")
                $count++;
        }

        return $count;
    }

    public function actionMesas($idRestaurante){
        $recs = Mesa::find()->where(['idRestaurante' => $idRestaurante])->all();

        return count($recs);
    }
}
