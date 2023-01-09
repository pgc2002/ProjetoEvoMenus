<?php

use common\models\Pedido;
use yii\data\ArrayDataProvider;
use yii\grid\GridView;
use yii\helpers\Html;
use yii\widgets\DetailView;

/** @var yii\web\View $this */
/** @var common\models\Pedido $model */

$this->title = "Detalhes do pedido ".$model->id;
\yii\web\YiiAsset::register($this);
?>
<div class="pedidos-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Voltar para pedidos', ['index#'.$model->estado.'s'], ['class' => 'btn btn-secondary']) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'usernameCliente',
            'valorTotal',
            'estado',
        ],
    ]) ?>
    
    <h2>Conteudos do pedido</h2>

    <?php
        $pedido = Pedido::find()->where(['id' => $model])->one();

        $menus = $pedido->getMenus()->select('nome')->all();

        $dataProvider = new ArrayDataProvider([
            'allModels' => $menus,
        ]);

        echo GridView::widget([
        'dataProvider' => $dataProvider,
        'layout' => '{items}{pager}',
        'columns' => [
                [
                    'label' => 'Menus',
                    'attribute' => 'nome'
                ],
            ]
        ]);

        $items = $pedido->getItems()->select('nome')->all();

        $dataProvider = new ArrayDataProvider([
            'allModels' => $items,
        ]);

        echo GridView::widget([
            'dataProvider' => $dataProvider,
            'layout' => '{items}',
            'columns' => [
                [
                    'label' => 'Items Individuais',
                    'attribute' => 'nome'
                ],
            ]
        ]);
    ?>


</div>
