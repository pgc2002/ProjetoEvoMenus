<?php

namespace backend\modules\api\controllers;

use common\models\Mesa;
use yii\data\ActiveDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;
use yii\data\ArrayDataProvider;

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

        return $dataProvider;
    }

    public function actionMesasassociadas($idRestaurante){
        $mesas = Mesa::find()->where(['idRestaurante' => $idRestaurante])->all();

        $dataProvider = new ArrayDataProvider([
            'allModels' => $mesas,
            'pagination' => false
        ]);

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
