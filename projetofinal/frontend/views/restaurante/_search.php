<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/** @var yii\web\View $this */
/** @var app\models\RestauranteSearch $model */
/** @var yii\widgets\ActiveForm $form */
?>

<div class="restaurante-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'id') ?>

    <?= $form->field($model, 'nome') ?>

    <?= $form->field($model, 'email') ?>

    <?= $form->field($model, 'lotacaoMaxima') ?>

    <?= $form->field($model, 'telemovel') ?>

    <?php // echo $form->field($model, 'idMorada') ?>

    <?php // echo $form->field($model, 'idEmenta') ?>

    <?php // echo $form->field($model, 'idHorario') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-outline-secondary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
