<?php

use common\models\Mesa;
use common\models\Restaurante;
use common\models\User;
use yii\data\ArrayDataProvider;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var backend\models\MesaSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */


$cookies = Yii::$app->request->cookies;
$idRestaurante = $cookies->getValue('idRestaurante');

if(isset($_GET['idRestaurante'])){
    $this->title = Restaurante::findOne($idRestaurante)->nome.' - Mesas';
}
else
    $this->title = 'Mesas';

$user = User::findOne(Yii::$app->user->identity->id);   
?>
<div class="mesa-index">
    <h1><?= Html::encode($this->title) ?></h1>
    <p>
        <?php
            if(Yii::$app->user->can('crudMesas'))
                echo " ".Html::a('Criar Mesa', ['create', 'idRestaurante' => $idRestaurante], ['class' => 'btn btn-success']);
        ?>
    </p>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?php
    $mesas = Mesa::findAll(["idRestaurante" => $idRestaurante]);

    $dataProvider = new ArrayDataProvider([
        'allModels' => $mesas,]);

    echo GridView::widget([
        'dataProvider' => $dataProvider,
        'layout' => '{items}{pager}',
        'columns' => [
            'numero',
            'nomeRestaurante',
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
    ?>
</div>
