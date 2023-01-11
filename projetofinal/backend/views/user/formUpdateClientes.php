<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use common\models\Restaurante;

/** @var yii\web\View $this */
/** @var common\models\User $user */
/** @var common\models\Morada $morada */
/** @var yii\widgets\ActiveForm $form */
?>

<?php $form = ActiveForm::begin();
    $this->title = 'Editar Cliente: ' . $user->username;
?>

<h3><?= Html::encode($this->title) ?></h3>

<?php
/*
echo $form->field($user, 'username')->textInput(['maxlength' => true]);
echo Html::label('Password', 'username', ['class' => 'label username'])
echo HTML::input('password', 'password', '', ['class' => 'form-control'])
echo <br>*/
if(isset($_GET['e']))
    echo '<p>'. Html::a('Voltar', ['view', 'id' => $user->id], ['class' => 'btn btn-secondary']) .'</p>';
else
    echo '<p>'. Html::a('Voltar', ['index'], ['class' => 'btn btn-secondary']) .'</p>';
?>

<?= $form->field($user, 'telemovel')->textInput(['maxlength' => true]) ?>

<?= $form->field($user, 'nif')->textInput(['maxlength' => true]) ?>

<?= $form->field($user, 'nome')->textInput(['maxlength' => true]) ?>

<?= $form->field($user, 'email')->textInput(['maxlength' => true]) ?>

<?= $form->field($user, 'status')->hiddenInput(['value' => 10])->label(false) ?>

<?= $form->field($morada, 'pais')->textInput() ?>

<?= $form->field($morada, 'cidade')->textInput() ?>

<?= $form->field($morada, 'rua')->textInput() ?>

<?= $form->field($morada, 'codpost')->textInput() ?>

<div class="form-group">
    <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
</div>

<?php ActiveForm::end(); ?>
