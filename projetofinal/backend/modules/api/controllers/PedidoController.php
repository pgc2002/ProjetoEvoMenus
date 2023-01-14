<?php

namespace backend\modules\api\controllers;

use common\models\Pagamento;
use common\models\Pedido;
use yii\data\ActiveDataProvider;
use yii\data\ArrayDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;

/**
 * Default controller for the `api` module
 */
class PedidoController extends ActiveController
{
    public $modelClass = 'common\models\Pedido';

    public function actionAll(){
        $query = Pedido::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        return $dataProvider;
    }

    public function actionMenus($idPedido){
        $pedido = Pedido::find()->where(['id' => $idPedido])->one();
        $menus = $pedido->getMenus()->all();

        $dataProvider = new ArrayDataProvider([
            'allModels' => $menus,
            'pagination' => false
        ]);

        return $dataProvider;
    }

    public function actionItems($idPedido)
    {
        $pedido = Pedido::find()->where(['id' => $idPedido])->one();
        $items = $pedido->getItems()->all();

        $dataProvider = new ArrayDataProvider([
            'allModels' => $items,
            'pagination' => false
        ]);

        return $dataProvider;
    }

    public function actionPagamentos($idPedido)
    {
        $query = Pagamento::find()->where(['idPedido' => $idPedido]);

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        return $dataProvider;
    }

    public function actionValorint($idPedido, $valor){
        $pedido = Pedido::findOne($idPedido);
        $pedido->valorTotal = $valor;
        $pedido->save();
    }

    public function actionValorfloat($idPedido, $valor, $valor2){
        $pedido = Pedido::findOne($idPedido);
        $valorFinal = $valor.".".$valor2;
        $pedido->valorTotal = floatval($valorFinal);
        $pedido->save();
    }

    /*public function actionConteudo($idPedido){
        $pedido = Pedido::find()->where(['id' => $idPedido])->one();

        $menus = $pedido->getMenus()->all();

        $items = $pedido->getItems()->all();

        $array= ["menus" => $menus, "items", $items];
        //array_push($array, $menus);

        $dataProvider = new ArrayDataProvider([
            'allModels' => $array,
        ]);

        return $dataProvider;
    }*/

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