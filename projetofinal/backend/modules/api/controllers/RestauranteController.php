<?php

namespace backend\modules\api\controllers;

use common\models\Mesa;
use common\models\Restaurante;
use yii\data\ActiveDataProvider;
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

    public function actionMesasdisponiveis($idRestaurante){
        $mesas = Mesa::find()->where(['idRestaurante' => $idRestaurante])->all();

        $count = 0;

        foreach($mesas as $mesa){
            if ($mesa->estado == "Livre")
                $count++;
        }

        return $count;
    }
}
