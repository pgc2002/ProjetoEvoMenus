<?php

use common\models\PedidoInscricao;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var app\models\PedidoInscricaoSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Pedido Inscrição';
?>
<div class="pedido-inscricao-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'layout' => '{items}{pager}',
        'filterModel' => $searchModel,
        'columns' => [
            [
                'attribute' => 'nome',
                'contentOptions' => ['style'=>'vertical-align: middle;'],
            ],
            [
                'attribute' => 'email',
                'contentOptions' => ['style'=>'vertical-align: middle;'],
            ],
            [
                'attribute' => 'telemovel',
                'contentOptions' => ['style'=>'vertical-align: middle;'],
            ],
            [
                'attribute' => 'moradaFormatada',
                'contentOptions' => ['style'=>'vertical-align: middle;'],
            ],
            [
                'format' => 'raw',
                'contentOptions' => ['style'=>'vertical-align: middle;'],
                'value' =>
                    function($model, $key, $index, $column) {
                        return Html::a(
                            '<i class="fa fa-check fa-xs" aria-hidden="true"></i>',
                            Url::to(['aceitar', 'id' => $model->id]),
                            [
                                'id'=>'grid-custom-button',
                                'data-pjax'=>true,
                                'class'=>'button btn btn-success',
                            ]
                        );
                    }
            ],
            [
                'format' => 'raw',
                'contentOptions' => ['style'=>'vertical-align: middle;'],
                'value' => function($model, $key, $index, $column) {
                    return Html::a(
                        '<i class="fa fa-times" aria-hidden="true"></i>',
                        Url::to(['recusar', 'id' => $model->id]),
                        [
                            'id'=>'grid-custom-button',
                            'data-pjax'=>true,
                            'class'=>'button btn btn-danger',
                        ]
                    );
                }
            ],
        ],
    ]); ?>


</div>
