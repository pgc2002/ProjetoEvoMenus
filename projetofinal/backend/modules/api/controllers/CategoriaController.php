<?php

namespace backend\modules\api\controllers;

use common\models\Categoria;
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

        return $dataProvider;
    }

    public function actionMenus($idCategoria){
        $categoria = Categoria::find()->where(['id' => $idCategoria])->one();
        $menus = $categoria->getMenus()->all();

        $dataProvider = new ArrayDataProvider([
            'allModels' => $menus,
            'pagination' => false
        ]);

        return $dataProvider;
    }

    public function actionItems($idCategoria){
        $categoria = Categoria::find()->where(['id' => $idCategoria])->one();
        $items = $categoria->getItems()->all();

        $dataProvider = new ArrayDataProvider([
            'allModels' => $items,
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
