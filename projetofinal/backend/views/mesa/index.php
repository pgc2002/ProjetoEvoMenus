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

if(isset($_GET['idRestaurante'])){
    $this->title = Restaurante::findOne($_GET['idRestaurante'])->nome.' - Mesas';
}
else
$this->title = 'Mesas';
?>
<div class="mesa-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?php
        if(isset($_GET['idRestaurante'])){
            echo Html::a(
            'Voltar para restaurante',
            Url::to(['..\restaurante\view', 'id' => $_GET['idRestaurante']]),
            [
                'class'=>'btn btn-secondary',
            ]);
            echo " ".Html::a('Criar Mesa', ['create', 'idRestaurante' => $_GET['idRestaurante']], ['class' => 'btn btn-success']);
        }
        else
            echo Html::a('Criar Mesa', ['create'], ['class' => 'btn btn-success']);
        ?>
    </p>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?php
    if(isset( $_GET['idRestaurante']))
    {
    $mesas = Mesa::findAll(["idRestaurante" => $_GET['idRestaurante']]);

    $dataProvider = new ArrayDataProvider([
        'allModels' => $mesas,]);

    echo GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'layout' => '{items}{pager}',
        'columns' => [
            'numero',
            'capacidade',
            'estado',
            [
                'class' => 'yii\grid\ActionColumn',
                'urlCreator' => function ($action, Mesa $mesas, $key, $index, $column) {
                    //if ($action == "delete")
                        return Url::toRoute([$action, 'id' => $mesas->id, 'idRestaurante' => $mesas->idRestaurante]);
                    //else
                    //    return Url::toRoute([$action, 'id' => $mesas->id]);
                }
            ],
        ],
    ]);
    }else{
        echo GridView::widget([
            'dataProvider' => $dataProvider,
            'filterModel' => $searchModel,
            'layout' => '{items}{pager}',
            'columns' => [
                'nomeRestaurante',
                'numero',
                'capacidade',
                'estado',
                [
                    'class' => ActionColumn::className(),
                    'urlCreator' => function ($action, Mesa $mesas, $key, $index, $column) {
                        return Url::toRoute([$action, 'id' => $mesas->id]);
                    }
                ],
            ],
        ]);
    }
    ?>
</div>
