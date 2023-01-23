<?php

namespace backend\modules\api\controllers;

use backend\modules\api\Connection;
use common\models\Pagamento;
use common\models\Pedido;
use common\models\Restaurante;
use PhpMqtt\Client\MqttClient;
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

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET de todos os pedidos", 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionOne($idPedido){
        $query = Pedido::findOne($idPedido);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET do pedido ".$idPedido, 0);
        $mqtt->disconnect();

        return $query;
    }

    public function actionPedidosuser($idUser){
        $query = Pedido::find()->where(['idCliente' => $idUser]);

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET de todos os pedidos do utilizador ".$idUser, 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionMenus($idPedido){
        $pedido = Pedido::find()->where(['id' => $idPedido])->one();
        $menus = $pedido->getMenus()->all();

        $dataProvider = new ArrayDataProvider([
            'allModels' => $menus,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET dos menus do pedido ".$idPedido, 0);
        $mqtt->disconnect();

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

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET dos items do pedido ".$idPedido, 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionPagamentos($idPedido)
    {
        $query = Pagamento::find()->where(['idPedido' => $idPedido]);

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "GET dos pagamentos do pedido ".$idPedido, 0);
        $mqtt->disconnect();

        return $dataProvider;
    }

    public function actionValor_int($idPedido, $valor){
        $pedido = Pedido::findOne($idPedido);
        $pedido->valorTotal = $valor;
        $pedido->save();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "PUT do valor do pedido ".$idPedido, 0);
        $mqtt->disconnect();
    }

    public function actionValor_float($idPedido, $valor, $valor2){
        $pedido = Pedido::findOne($idPedido);
        $valorFinal = $valor.".".$valor2;
        $pedido->valorTotal = floatval($valorFinal);
        $pedido->save();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "PUT do valor do pedido ".$idPedido, 0);
        $mqtt->disconnect();
    }

    public function actionCriar($valorTotal, $estado, $idCliente, $idRestaurante){
        $pedido = new Pedido();
        $pedido->valorTotal = $valorTotal;
        $pedido->estado = $estado;
        $pedido->idCliente = $idCliente;
        $pedido->idRestaurante = $idRestaurante;
        $pedido->save();

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "POST de um pedido", 0);
        $mqtt->disconnect();

        return $pedido->id;
    }

    public function actionInserir_item($idPedido, $idItem){
        $pedido = Pedido::find()->where(['id' => $idPedido])->one();
        $item = Item::find()->where(['id' => $idItem])->one();

        $pedido->link('items', $item);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "POST de um item ".$idItem." no pedido ".$idPedido, 0);
        $mqtt->disconnect();
    }

    public function actionInserir_menu($idPedido, $idMenu){
        $pedido = Pedido::find()->where(['id' => $idPedido])->one();
        $menu = Menu::find()->where(['id' => $idMenu])->one();

        $pedido->link('menus', $menu);

        $connection = new Connection();
        $mqtt = new MqttClient($connection->ip, $connection->port, $connection->clientId);
        $mqtt->connect();
        $mqtt->publish($connection->topic, "POST de um menu ".$idMenu." no pedido ".$idPedido, 0);
        $mqtt->disconnect();
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
