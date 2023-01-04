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
                'header' => 'Detalhes',
                'template' => '{viewButton}',
                'buttons' => [
                    'viewButton' => function($url, $model, $key) {
                        return Html::a('Ver',"view?id=$model->id");
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
