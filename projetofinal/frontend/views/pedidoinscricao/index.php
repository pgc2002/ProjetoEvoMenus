<?php

use app\models\Pedidoinscricao;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var app\models\PedidoinscricaoSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Pedido Inscricaos';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="pedido-inscricao-index" id="siteBody">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Pedido Inscricao', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'nome',
            'email:email',
            'telemovel',
            'morada',
            [
                'class' => ActionColumn::className(),
                'urlCreator' => function ($action, Pedidoinscricao $model, $key, $index, $column) {
                    return Url::toRoute([$action, 'id' => $model->id]);
                 }
            ],
        ],
    ]); ?>


</div>
