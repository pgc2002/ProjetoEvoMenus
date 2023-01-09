<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/** @var yii\web\View $this */
/** @var common\models\Item $model */
/** @var yii\widgets\ActiveForm $form */
?>

<div class="item-form">

    <?php $form = ActiveForm::begin(['options' => ['enctype' => 'multipart/form-data']]); ?>

    <?= $form->field($model, 'nome')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'preco')->input('number') ?> 

    <?= $form->field($model, 'fotografia')->fileInput()?> 

    <?= $form->field($model, 'idCategoria')->hiddenInput(['value' => Yii::$app->request->get('idCategoria')])->label(false) ?>

    <?= HTML::hiddenInput('idRestaurante', Yii::$app->request->get('idRestaurante'))?>

    <div class="form-group">
        <?= Html::submitButton('Guardar', ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
