<?php

use app\models\Restaurante;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var app\models\RestauranteSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Restaurantes';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="restaurante-index">

    <h1><?php Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Restaurante', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'nome',
            'email:email',
            'lotacaoMaxima',
            'telemovel',
            //'idMorada',
            //'idEmenta',
            //'idHorario',
            [
                'class' => ActionColumn::className(),
                'urlCreator' => function ($action, Restaurante $model, $key, $index, $column) {
                    return Url::toRoute([$action, 'id' => $model->id]);
                 }
            ],
        ],
    ]); ?>


</div>
