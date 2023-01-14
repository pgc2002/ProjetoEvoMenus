<?php

namespace backend\modules\api\controllers;

use common\models\Pagamento;
use common\models\Pedido;
use common\models\Item;
use common\models\Menu;
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

    public function actionCriar($valorTotal, $estado, $idCliente, $idRestaurante){
        $pedido = new Pedido();
        $pedido->valorTotal = $valorTotal;
        $pedido->estado = $estado;
        $pedido->idCliente = $idCliente;
        $pedido->idRestaurante = $idRestaurante;
        $pedido->save();
    }

    public function actionInseriritem($idPedido, $idItem){
        $pedido = Pedido::find()->where(['id' => $idPedido])->one();
        $item = Item::find()->where(['id' => $idItem])->one();

        $pedido->link('items', $item);
    }

    public function actionInserirmenu($idPedido, $idMenu){
        $pedido = Pedido::find()->where(['id' => $idPedido])->one();
        $menu = Menu::find()->where(['id' => $idMenu])->one();

        $pedido->link('menus', $menu);
    }
}
