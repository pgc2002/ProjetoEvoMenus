<?php

use kartik\time\TimePicker;
use yii\helpers\Html;
use yii\widgets\ActiveForm;

/** @var yii\web\View $this */
/** @var common\models\Horariofuncionamento $model */
/** @var yii\widgets\ActiveForm $form */
?>

<style>
    .bootstrap-timepicker{
        width: 120px;
    }
</style>
<div class="horario-funcionamento-form">

    <?php $form = ActiveForm::begin(); ?>
    <h4>Segunda-feira</h4>
    <div class="row" style="padding-left: 10px;">
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Almoço', 'segunda_almoco_inicio')?>
            <?= TimePicker::widget([
                'name' => 'segunda_almoco_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '12:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'segunda_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'segunda_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '15:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'segunda_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'segunda_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '19:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'segunda_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'segunda_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '23:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('segunda_folga', false, ['label' => 'Folga']) ?>
        </div>
    </div>
    <h4>Terça-feira</h4>
    <div class="row" style="padding-left: 10px;">
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Almoço', 'terca_almoco_inicio')?>
            <?= TimePicker::widget([
                'name' => 'terca_almoco_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '12:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'terca_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'terca_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '15:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'terca_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'terca_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '19:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'terca_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'terca_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '23:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('terca_folga', false, ['label' => 'Folga']) ?>
        </div>
    </div>
    <h4>Quarta-feira</h4>
    <div class="row" style="padding-left: 10px;">
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Almoço', 'quarta_almoco_inicio')?>
            <?= TimePicker::widget([
                'name' => 'quarta_almoco_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '12:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'quarta_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'quarta_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '15:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'quarta_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'quarta_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '19:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'quarta_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'quarta_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '23:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('quarta_folga', false, ['label' => 'Folga']) ?>
        </div>
    </div>
    <h4>Quinta-feira</h4>
    <div class="row" style="padding-left: 10px;">
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Almoço', 'quinta_almoco_inicio')?>
            <?= TimePicker::widget([
                'name' => 'quinta_almoco_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '12:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'quinta_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'quinta_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '15:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'quinta_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'quinta_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '19:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'quinta_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'quinta_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '23:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('quinta_folga', false, ['label' => 'Folga']) ?>
        </div>
    </div>
    <h4>Sexta-feira</h4>
    <div class="row" style="padding-left: 10px;">
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Almoço', 'sexta_almoco_inicio')?>
            <?= TimePicker::widget([
                'name' => 'sexta_almoco_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '12:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'sexta_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'sexta_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '15:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'sexta_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'sexta_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '19:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'sexta_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'sexta_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '23:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('sexta_folga', false, ['label' => 'Folga']) ?>
        </div>
    </div>
    <h4>Sábado</h4>
    <div class="row" style="padding-left: 10px;">
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Almoço', 'sabado_almoco_inicio')?>
            <?= TimePicker::widget([
                'name' => 'sabado_almoco_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '12:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'sabado_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'sabado_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '15:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'sabado_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'sabado_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '19:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'sabado_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'sabado_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '23:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('sabado_folga', false, ['label' => 'Folga']) ?>
        </div>
    </div>
    <h4>Domingo</h4>
    <div class="row" style="padding-left: 10px;">
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Almoço', 'domingo_almoco_inicio')?>
            <?= TimePicker::widget([
                'name' => 'domingo_almoco_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '12:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'domingo_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'domingo_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '15:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'domingo_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'domingo_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '19:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'domingo_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'domingo_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => '23:00'
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('domingo_folga', false, ['label' => 'Folga']) ?>
        </div>
    </div>
    <?= $form->field($model, 'segunda')->hiddenInput(['value' => 'a'])->label(false); ?>

    <?php /*$form->field($model, 'terca')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'quarta')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'quinta')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'sexta')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'sabado')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'domingo')->textInput(['maxlength' => true])*/ ?>

    <div class="form-group">
        <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
