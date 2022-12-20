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

    <?php
        $form = ActiveForm::begin();
        $dias = $model->getHorario();
        $folgas = [
          "segunda" => false,
          "terca" => false,
          "quarta" => false,
          "quinta" => false,
          "sexta" => false,
          "sabado" => false,
          "domingo" => false,
        ];

        foreach($dias as $key => $value){
            if(in_array( "Folga", $dias[$key], true )){
                $folgas[$key] = true;
                $dias[$key][0] = "12:00";
                $dias[$key][1] = "15:00";
                $dias[$key][2] = "19:00";
                $dias[$key][3] = "23:00";
            }
        }
    ?>
    <h4>Segunda-feira</h4>
    <div class="row" style="padding-left: 10px;">
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Almoço', 'segunda_almoco_inicio')?>
            <?= TimePicker::widget([
                'name' => 'segunda_almoco_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['segunda'][0],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'segunda_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'segunda_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['segunda'][1],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'segunda_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'segunda_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['segunda'][2],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'segunda_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'segunda_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['segunda'][3],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('segunda_folga', $folgas['segunda'], ['label' => 'Folga']) ?>
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
                    'defaultTime' => $dias['terca'][0],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'terca_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'terca_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['terca'][1],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'terca_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'terca_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['terca'][2],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'terca_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'terca_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['terca'][3],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('terca_folga', $folgas['terca'], ['label' => 'Folga']) ?>
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
                    'defaultTime' => $dias['quarta'][0],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'quarta_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'quarta_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['quarta'][1],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'quarta_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'quarta_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['quarta'][2],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'quarta_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'quarta_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['quarta'][3],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('quarta_folga', $folgas['quarta'], ['label' => 'Folga']) ?>
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
                    'defaultTime' => $dias['quinta'][0],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'quinta_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'quinta_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['quinta'][1],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'quinta_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'quinta_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['quinta'][2],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'quinta_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'quinta_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['quinta'][3],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('quinta_folga', $folgas['quinta'], ['label' => 'Folga']) ?>
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
                    'defaultTime' => $dias['sexta'][0],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'sexta_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'sexta_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['sexta'][1],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'sexta_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'sexta_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['sexta'][2],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'sexta_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'sexta_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['sexta'][3],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('sexta_folga', $folgas['sexta'], ['label' => 'Folga']) ?>
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
                    'defaultTime' => $dias['sabado'][0],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'sabado_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'sabado_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['sabado'][1],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'sabado_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'sabado_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['sabado'][2],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'sabado_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'sabado_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['sabado'][3],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('sabado_folga', $folgas['sabado'], ['label' => 'Folga']) ?>
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
                    'defaultTime' => $dias['domingo'][0],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Almoço', 'domingo_almoco_fim')?>
            <?= TimePicker::widget([
                'name' => 'domingo_almoco_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['domingo'][1],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Início Jantar', 'domingo_jantar_inicio')?>
            <?= TimePicker::widget([
                'name' => 'domingo_jantar_inicio',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['domingo'][2],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px;">
            <?= Html::label('Fim Jantar', 'domingo_jantar_fim')?>
            <?= TimePicker::widget([
                'name' => 'domingo_jantar_fim',
                'pluginOptions' => [
                    'showMeridian' => false,
                    'defaultTime' => $dias['domingo'][3],
                ]
            ])?>
        </div>
        <div class="column" style="padding-left: 5px; padding-right: 5px; padding-top: 3.5%;">
            <?= Html::checkbox('domingo_folga', $folgas['domingo'], ['label' => 'Folga']) ?>
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
        <?= Html::submitButton('Guardar', ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
