<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/** @var yii\web\View $this */
/** @var common\models\Mesa $model */

$this->title = $model->getNomeRestaurante().' - Mesa '.$model->numero;
\yii\web\YiiAsset::register($this);
?>
<div class="mesa-view" id="siteBody">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?php
            if(isset($_GET['idRestaurante']))
                echo Html::a('Voltar para mesas', ['index', 'idRestaurante' => $model->idRestaurante], ['class' => 'btn btn-secondary']);
            else
                echo Html::a('Voltar para mesas', ['index'], ['class' => 'btn btn-secondary']);
        ?>
        <?= Html::a('Update', ['update', 'id' => $model->id, 'idRestaurante' => $model->idRestaurante], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->id, 'idRestaurante' => $model->idRestaurante], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'capacidade',
            'estado',
        ],
    ]) ?>

</div>
