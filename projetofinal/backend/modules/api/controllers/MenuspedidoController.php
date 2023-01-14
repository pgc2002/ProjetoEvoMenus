<?php

namespace backend\modules\api\controllers;

use common\models\MenusPedido;
use yii\data\ActiveDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;

/**
 * Default controller for the `api` module
 */
class MenuspedidoController extends ActiveController
{
    public $modelClass = 'common\models\MenusPedido';

    public function actionAll(){
        $query = MenusPedido::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
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
