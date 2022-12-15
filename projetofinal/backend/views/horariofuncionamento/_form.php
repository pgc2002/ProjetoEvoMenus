<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/** @var yii\web\View $this */
/** @var common\models\HorarioFuncionamento $model */
/** @var yii\widgets\ActiveForm $form */
?>

<div class="horario-funcionamento-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'segunda')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'terca')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'quarta')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'quinta')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'sexta')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'sabado')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'domingo')->textInput(['maxlength' => true]) ?>

    <div class="form-group">
        <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
