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
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<div class="container-fluid">
    <ul class="nav nav-pills nav-justified" id="myTabs">
        <li><a href="#cliente" onclick="escolhaUtilizador(1)">Cliente</a></li>
        <li><a href="#funcionario" onclick="escolhaUtilizador(2)">Funcionário</a></li>
        <li><a href="#gestor" onclick="escolhaUtilizador(3)">Gestor</a></li>
        <li><a href="#admin" onclick="escolhaUtilizador(4)">Admin</a></li>
    </ul>


<div class="tab-content">
    <div id="cliente" style="display: block">

        <?php $form = ActiveForm::begin(); ?>

        <?= $form->field($user, 'username')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'password')->passwordInput() ?>

        <?php /*$form->field($user, 'auth_key')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'password_hash')->textInput(['maxlength' => true])*/ ?>

        <?= $form->field($user, 'password_reset_token')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'telemovel')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'nif')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'tipo')->hiddenInput(['value' => 'Cliente'])->label(false) ?>

        <?= $form->field($user, 'nome')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'email')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'status')->textInput() ?>

        <?= $form->field($user, 'idRestaurante')->dropDownList(ArrayHelper::map(Restaurante::find()->all(), 'id', 'nome'))->label('Restaurante'); ?>

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

        <?= $form->field($user, 'username')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'auth_key')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'password_hash')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'password_reset_token')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'email')->textInput(['maxlength' => true]) ?>

        <div class="form-group">
            <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
        </div>

        <?php ActiveForm::end(); ?>
    </div>
    <div id="gestor" style="display: none">

    </div>
    <div id="admin" style="display: none">

    </div>
</div>
</div>

<script>
    function escolhaUtilizador(x){
        var cliente = document.getElementById("cliente");
        var funcionario = document.getElementById("funcionario");
        var gestor = document.getElementById("gestor");
        var admin = document.getElementById("admin");

        cliente.style.display = "none";
        funcionario.style.display = "none";
        gestor.style.display = "none";
        admin.style.display = "none";

        switch(x){
            case 1:
                cliente.style.display = "block";
                break;
            case 2:
                funcionario.style.display = "block";
                break;
            case 3:
                gestor.style.display = "block";
                break;
            case 4:
                admin.style.display = "block";
                break;
        }
    }
</script>