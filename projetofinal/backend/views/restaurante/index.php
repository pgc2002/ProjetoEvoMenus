<?php

use common\models\Restaurante;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var app\models\RestauranteSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Restaurantes';

?>
<div class="restaurante-index">
    <h1><?= Html::encode($this->title) ?></h1>

    <?php /*echo $this->render('_search', ['model' => $searchModel]);*/ ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'layout' => '{items}{pager}',
        'filterModel' => $searchModel,
        'columns' => [
            [
                'class' => 'yii\grid\ActionColumn',
                'template' => '{detalhes}',
                'buttons' => [
                    'detalhes' => function($url, $model, $key) {
                        return Html::a('Detalhes','view?id='.$model->id, ['class' => 'btn btn-success', 'data-pjax' => 0]);
                    }
                ]
            ],
            'nome',
            'lotacaoMaxima',
            'email',
            'telemovel',
            'moradaFormatada',
        ],
    ]); ?>


</div>
