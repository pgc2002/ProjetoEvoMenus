<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use common\models\Restaurante;

/** @var yii\web\View $this */
/** @var common\models\User $user */
/** @var yii\widgets\ActiveForm $form */
?>
<style>
    #navUsers a{
        text-decoration: none;
        color: black;
    }
    #navUsers{
        padding-right: 5px;
    }
    #navUsers a:hover{
        border-bottom: 3px solid #0080ff;
    }
    #navUsers a.active {
        border-bottom: 3px solid #0080ff;
    }
</style>

<div class="tab-content">
    <div id="funcionario" style="display: block">
        <?php $form = ActiveForm::begin(); ?>
        <br>
        <?= Html::a('Voltar', ['index'], ['class' => 'btn btn-secondary']) ?>

        <?php
            if(isset($_GET['c'])){

            }
        ?>

        <?= $form->field($user, 'username')->textInput(['maxlength' => true]) ?>
        <?php
            if(isset($_GET['c'])){
                echo Html::label('Password', 'username', ['class' => 'label username']);
                echo HTML::input('password', 'password', '', ['class' => 'form-control']);
            }
        ?>

        <br>

        <?= $form->field($user, 'telemovel')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'nif')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'tipo')->hiddenInput(['value' => 'Funcionario'])->label(false) ?>

        <?= $form->field($user, 'nome')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'email')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'status')->hiddenInput(['value' => 10])->label(false) ?>

        <?= $form->field($user, 'idRestaurante')->hiddenInput(['value' => \common\models\User::findOne(Yii::$app->user->id)->idRestaurante])->label(false) ?>

        <div class="form-group">
            <?= Html::submitButton('Guardar', ['class' => 'btn btn-success']) ?>
        </div>
        <?php ActiveForm::end(); ?>
    </div>
</div>
