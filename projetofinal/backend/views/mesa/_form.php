<?php

use common\models\Restaurante;
use yii\helpers\ArrayHelper;
use yii\helpers\Html;
use yii\widgets\ActiveForm;


/** @var yii\web\View $this */
/** @var common\models\Mesa $model */
/** @var yii\widgets\ActiveForm $form */
?>

<div class="mesa-form">

    <?php $form = ActiveForm::begin(); ?>

    <?php
        if(isset($_GET['idRestaurante'])) {
            echo Html::a('Voltar para mesas', ['index', 'idRestaurante' => $_GET['idRestaurante']], ['class' => 'btn btn-secondary']);
            echo $form->field($model, 'idRestaurante')->hiddenInput(['value' => $_GET['idRestaurante']])->label(false);
        }
        else{
            echo Html::a('Voltar para mesas', ['index'], ['class' => 'btn btn-secondary']);
            $items = ArrayHelper::map(Restaurante::find()->all(), 'id', 'nome');
            echo $form->field($model, 'idRestaurante')->dropDownList($items)->label("Restaurante");
        }
    ?>

    <?= $form->field($model, 'capacidade')->textInput() ?>

    <?= $form->field($model, 'estado')->textInput(['maxlength' => true]) ?>

    <div class="form-group">
        <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
