<?php

namespace backend\modules\api\controllers;

use common\models\Pagamento;
use yii\data\ActiveDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;

/**
 * Default controller for the `api` module
 */
class PagamentoController extends ActiveController
{
    public $modelClass = 'common\models\Pagamento';

    public function actionAll(){
        $query = Pagamento::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        return $dataProvider;
    }

    public function actionCriar($idPedido, $valor, $metodo){
        $pagamento = new Pagamento();
        $pagamento->idPedido = $idPedido;
        $pagamento->valor = $valor;
        $pagamento->metodo = $metodo;
        $pagamento->save();
    }
}
