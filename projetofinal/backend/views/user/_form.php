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

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<div class="container-fluid">
    <ul class="nav nav-tabs" id="myTabs">
        <li class="nav-item" id="navUsers" ><a class="active" id="navCliente" href="#cliente" onclick="escolhaUtilizador(1)" >Cliente</a></li>
        <li class="nav-item" id="navUsers" ><a id="navFuncionario" href="#funcionario" onclick="escolhaUtilizador(2)">Funcionário</a></li>
        <li class="nav-item" id="navUsers" ><a id="navGestor" href="#gestor" onclick="escolhaUtilizador(3)">Gestor</a></li>
        <li class="nav-item" id="navUsers" ><a id="navAdmin" href="#admin" onclick="escolhaUtilizador(4)">Admin</a></li>
    </ul>
<div class="tab-content">

    <div id="cliente" style="display: block">

        <?php $form = ActiveForm::begin(); ?>

        <h3>Criar Cliente</h3>

        <?= $form->field($user, 'username')->textInput(['maxlength' => true]) ?>

        <?= Html::label('Password', 'username', ['class' => 'label username']) ?>
        <?= HTML::input('password', 'password', '', ['class' => 'form-control'])?>
        <br>

        <?= $form->field($user, 'telemovel')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'nif')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'tipo')->hiddenInput(['value' => 'Cliente'])->label(false) ?>

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
    </div>
    <div id="funcionario" style="display: none">
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
            <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
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
            <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
        </div>
        <?php ActiveForm::end(); ?>
    </div>
    <div id="admin" style="display: none">
        <?php $form = ActiveForm::begin(); ?>

        <h3>Criar Admin</h3>

        <?= $form->field($user, 'username')->textInput(['maxlength' => true]) ?>

        <?= Html::label('Password', 'username', ['class' => 'label username']) ?>
        <?= HTML::input('password', 'password', '', ['class' => 'form-control'])?>
        <br>

        <?= $form->field($user, 'telemovel')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'nif')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'tipo')->hiddenInput(['value' => 'Admin'])->label(false) ?>

        <?= $form->field($user, 'nome')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'email')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'status')->hiddenInput(['value' => 10])->label(false) ?>

        <div class="form-group">
            <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
        </div>
        <?php ActiveForm::end(); ?>
    </div>
</div>
</div>

<script>
    function escolhaUtilizador(x){
        var cliente = document.getElementById("cliente");
        var funcionario = document.getElementById("funcionario");
        var gestor = document.getElementById("gestor");
        var admin = document.getElementById("admin");
        var navCliente = document.getElementById("navCliente");
        var navFuncionario = document.getElementById("navFuncionario");
        var navGestor = document.getElementById("navGestor");
        var navAdmin = document.getElementById("navAdmin");

        cliente.style.display = "none";
        funcionario.style.display = "none";
        gestor.style.display = "none";
        admin.style.display = "none";

        navCliente.classList.remove('active');
        navFuncionario.classList.remove('active');
        navGestor.classList.remove('active');
        navAdmin.classList.remove('active');

        switch(x){
            case 1:
                cliente.style.display = "block";
                navCliente.classList.add('active');
                break;
            case 2:
                funcionario.style.display = "block";
                navFuncionario.classList.add('active');
                break;
            case 3:
                gestor.style.display = "block";
                navGestor.classList.add('active');
                break;
            case 4:
                admin.style.display = "block";
                navAdmin.classList.add('active');
                break;
        }
    }
</script>