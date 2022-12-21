<?php

use common\models\Mesa;
use common\models\Restaurante;
use yii\data\ArrayDataProvider;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var backend\models\MesaSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Mesas';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="mesa-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?php
        /*if(isset $_GET['idRestaurante'])*/

        Html::a('Create Mesa', ['create', 'idRestaurante' => $_GET['idRestaurante']], ['class' => 'btn btn-success']) ?>
    </p>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?php

    if(isset( $_GET['idRestaurante']))
    {
    $mesas = Mesa::findAll(["idRestaurante" => $_GET['idRestaurante']]);

    $dataProvider = new ArrayDataProvider([
        'allModels' => $mesas,]);
    }

    ?>


    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'numero',
            'capacidade',
            'estado',

            [
                'class' => ActionColumn::className(),

                'urlCreator' => function ($action, Mesa $mesas, $key, $index, $column) {
                    return Url::toRoute([$action, 'id' => $mesas->id, 'idRestaurante' => $mesas->idRestaurante]);
                 }

            ],
        ],
    ]); ?>




</div>
