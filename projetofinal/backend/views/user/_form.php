<?php

use common\models\Pais;
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

        <?= $form->field($user, 'username')->textInput(['maxlength' => true, "style" => "width:305px"]) ?>

        <?= Html::label('Password', 'username', ['class' => 'label username']) ?>
        <?= HTML::input('password', 'password', null, ['class' => 'form-control', 'maxlength' => 32, "style" => "width:305px"])?>
        <br>

        <?= $form->field($user, 'telemovel')->textInput(['type' => 'tel', 'maxlength' => true, 'pattern' => '[0-9]{9}', "style" => "width:130px"]) ?>

        <?= $form->field($user, 'nif')->textInput(['maxlength' => true, 'pattern' => '[0-9]{9}', "style" => "width:120px"]) ?>

        <?= $form->field($user, 'tipo')->hiddenInput(['value' => 'Cliente'])->label(false) ?>

        <?= $form->field($user, 'nome')->textInput(['type' => 'text', 'maxlength' => true, "style" => "width:450px"]) ?>

        <?= $form->field($user, 'email')->textInput(['type' => 'email', 'maxlength' => true, "style" => "width:450px"]) ?>

        <?= $form->field($user, 'status')->hiddenInput(['value' => 10])->label(false) ?>

        <?php
            $paises = ArrayHelper::map(Pais::find()->all(), 'paisNome','paisNome');
            $paises = array_map('formatarPais', $paises);

            function formatarPais($pais){
                return mb_convert_case($pais, MB_CASE_TITLE, "UTF-8");
            }
        ?>
        <?= Html::label('País', 'pais')?><br>
        <?= Html::dropDownList('pais', null, $paises, ['class' => 'form-control', 'required' => 'true', 'id' =>'pais', "style" => "width:310px"]) ?><br>

        <?= $form->field($morada, 'cidade')->textInput(['type' => 'text','maxlength' => true, "style" => "width:310px"]) ?>

        <?= $form->field($morada, 'rua')->textInput(['type' => 'text', 'maxlength' => true, "style" => "width:800px"])->label("Morada") ?>

        <?= Html::label('Código Postal', 'codpost', ['class' => 'control-label'])?>
        <div class="form-inline">
            <?= Html::input('text', 'codpost1', null, ['class' => 'form-control', 'required' => 'true', 'maxlength' => 5, 'pattern' => '[0-9]{5}', "style" => "width:70px;", 'id' =>'codpost1']) ?>
            <i class="fa fa-window-minimize" style="padding-left: 5px; padding-right: 5px; padding-bottom: 5px" aria-hidden="true"></i>
            <?= Html::input('text', 'codpost2', null, ['class' => 'form-control', 'required' => 'true', 'maxlength' => 3, 'pattern' => '[0-9]{3}', "style" => "width:50px", 'id' =>'codpost2']) ?>
        </div>
        <br>

        <div class="form-group">
            <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
        </div>

        <?php ActiveForm::end(); ?>
    </div>
    <div id="funcionario" style="display: none">
        <?php $form = ActiveForm::begin(); ?>

        <h3>Criar Funcionário</h3>

        <?= $form->field($user, 'username')->textInput(['maxlength' => true, "style" => "width:305px"]) ?>

        <?= Html::label('Password', 'username', ['class' => 'label username']) ?>
        <?= HTML::input('password', 'password', null, ['class' => 'form-control', 'maxlength' => 32, "style" => "width:305px"])?>
        <br>

        <?= $form->field($user, 'telemovel')->textInput(['type' => 'tel', 'maxlength' => true, 'pattern' => '[0-9]{9}', "style" => "width:130px"]) ?>

        <?= $form->field($user, 'nif')->textInput(['maxlength' => true, 'pattern' => '[0-9]{9}', "style" => "width:120px"]) ?>

        <?= $form->field($user, 'tipo')->hiddenInput(['value' => 'Funcionario'])->label(false) ?>

        <?= $form->field($user, 'nome')->textInput(['type' => 'text', 'maxlength' => true, "style" => "width:450px"]) ?>

        <?= $form->field($user, 'email')->textInput(['type' => 'email', 'maxlength' => true, "style" => "width:450px"]) ?>

        <?= $form->field($user, 'status')->hiddenInput(['value' => 10])->label(false) ?>

        <?= Html::label('Restaurante', 'restaurante')?><br>
        <?= Html::dropDownList('restaurante', null, ArrayHelper::map(Restaurante::find()->all(), 'id', 'nome'), ['class' => 'form-control', 'required' => 'true', 'id' =>'pais', "style" => "width:350px"]) ?><br>

        <div class="form-group">
            <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
        </div>
        <?php ActiveForm::end(); ?>
    </div>
    <div id="gestor" style="display: none">
        <?php $form = ActiveForm::begin(); ?>

        <h3>Criar Gestor</h3>

        <?= $form->field($user, 'username')->textInput(['maxlength' => true, "style" => "width:305px"]) ?>

        <?= Html::label('Password', 'username', ['class' => 'label username']) ?>
        <?= HTML::input('password', 'password', null, ['class' => 'form-control', 'maxlength' => 32, "style" => "width:305px"])?>
        <br>

        <?= $form->field($user, 'telemovel')->textInput(['type' => 'tel', 'maxlength' => true, 'pattern' => '[0-9]{9}', "style" => "width:130px"]) ?>

        <?= $form->field($user, 'nif')->textInput(['maxlength' => true, 'pattern' => '[0-9]{9}', "style" => "width:120px"]) ?>

        <?= $form->field($user, 'tipo')->hiddenInput(['value' => 'Gestor'])->label(false) ?>

        <?= $form->field($user, 'nome')->textInput(['type' => 'text', 'maxlength' => true, "style" => "width:450px"]) ?>

        <?= $form->field($user, 'email')->textInput(['type' => 'email', 'maxlength' => true, "style" => "width:450px"]) ?>

        <?= $form->field($user, 'status')->hiddenInput(['value' => 10])->label(false) ?>

        <?= Html::label('Restaurante', 'restaurante')?><br>
        <?= Html::dropDownList('restaurante', null, ArrayHelper::map(Restaurante::find()->all(), 'id', 'nome'), ['class' => 'form-control', 'required' => 'true', 'id' =>'pais', "style" => "width:350px"]) ?><br>

        <div class="form-group">
            <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
        </div>
        <?php ActiveForm::end(); ?>
    </div>
    <div id="admin" style="display: none">
        <?php $form = ActiveForm::begin(); ?>

        <h3>Criar Admin</h3>

        <?= $form->field($user, 'username')->textInput(['maxlength' => true, "style" => "width:305px"]) ?>

        <?= Html::label('Password', 'username', ['class' => 'label username']) ?>
        <?= HTML::input('password', 'password', null, ['class' => 'form-control', 'maxlength' => 32, "style" => "width:305px"])?>
        <br>

        <?= $form->field($user, 'telemovel')->textInput(['type' => 'tel', 'maxlength' => true, 'pattern' => '[0-9]{9}', "style" => "width:130px"]) ?>

        <?= $form->field($user, 'nif')->textInput(['maxlength' => true, 'pattern' => '[0-9]{9}', "style" => "width:120px"]) ?>

        <?= $form->field($user, 'tipo')->hiddenInput(['value' => 'Admin'])->label(false) ?>

        <?= $form->field($user, 'nome')->textInput(['type' => 'text', 'maxlength' => true, "style" => "width:450px"]) ?>

        <?= $form->field($user, 'email')->textInput(['type' => 'email', 'maxlength' => true, "style" => "width:450px"]) ?>

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