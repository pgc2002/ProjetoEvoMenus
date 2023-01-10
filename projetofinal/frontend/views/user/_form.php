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

<div class="container-fluid">
    <ul class="nav nav-tabs" id="myTabs">
        <li class="nav-item" id="navUsers" ><a class="active" id="navFuncionario" href="#funcionario" onclick="escolhaUtilizador(1)">Funcionário</a></li>
        <li class="nav-item" id="navUsers" ><a id="navGestor" href="#gestor" onclick="escolhaUtilizador(2)">Gestor</a></li>
    </ul>
<div class="tab-content">
    <div id="funcionario" style="display: block">
        <?php $form = ActiveForm::begin(); ?>

        <h3>Criar Funcionário</h3>

        <?= $form->field($user, 'username')->textInput(['maxlength' => true]) ?>

        <?= Html::label('Password', 'username', ['class' => 'label username']) ?>
        <?= HTML::input('password', 'password', '', ['class' => 'form-control'])?>
        <br>

        <?= $form->field($user, 'telemovel')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'nif')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'tipo')->hiddenInput(['value' => 'Funcionario'])->label(false) ?>

        <?= $form->field($user, 'nome')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'email')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'status')->hiddenInput(['value' => 10])->label(false) ?>

        <?= $form->field($user, 'idRestaurante')->dropDownList(ArrayHelper::map(Restaurante::find()->all(), 'id', 'nome'))->label('Restaurante'); ?>

        <div class="form-group">
            <?= Html::submitButton('Guardar', ['class' => 'btn btn-success']) ?>
        </div>
        <?php ActiveForm::end(); ?>
    </div>
    <div id="gestor" style="display: none">
        <?php $form = ActiveForm::begin(); ?>

        <h3>Criar Gestor</h3>

        <?= $form->field($user, 'username')->textInput(['maxlength' => true]) ?>

        <?= Html::label('Password', 'username', ['class' => 'label username']) ?>
        <?= HTML::input('password', 'password', '', ['class' => 'form-control'])?>
        <br>

        <?= $form->field($user, 'telemovel')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'nif')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'tipo')->hiddenInput(['value' => 'Gestor'])->label(false) ?>

        <?= $form->field($user, 'nome')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'email')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'status')->hiddenInput(['value' => 10])->label(false) ?>

        <?= $form->field($user, 'idRestaurante')->dropDownList(ArrayHelper::map(Restaurante::find()->all(), 'id', 'nome'))->label('Restaurante'); ?>

        <div class="form-group">
            <?= Html::submitButton('Guardar', ['class' => 'btn btn-success']) ?>
        </div>
        <?php ActiveForm::end(); ?>
    </div>
</div>
</div>

<script>
    function escolhaUtilizador(x){
        var funcionario = document.getElementById("funcionario");
        var gestor = document.getElementById("gestor");
        var navFuncionario = document.getElementById("navFuncionario");
        var navGestor = document.getElementById("navGestor");

        funcionario.style.display = "none";
        gestor.style.display = "none";

        navFuncionario.classList.remove('active');
        navGestor.classList.remove('active');

        switch(x){
            case 1:
                funcionario.style.display = "block";
                navFuncionario.classList.add('active');
                break;
            case 2:
                gestor.style.display = "block";
                navGestor.classList.add('active');
                break;
        }
    }
</script>