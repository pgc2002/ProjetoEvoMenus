<?php

namespace backend\modules\api\controllers;

use common\models\Item;
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
