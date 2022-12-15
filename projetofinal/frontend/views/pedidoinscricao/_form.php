<?php

use common\widgets\Alert;
use yii\bootstrap4\Breadcrumbs;
use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use common\models\Pais;


/** @var yii\web\View $this */
/** @var common\models\PedidoInscricao $model */
/** @var yii\widgets\ActiveForm $form */
?>

<script>
    const input = document.querySelector('input');
    const log = document.getElementById('values');

    input.addEventListener('input', updateValue);

    function updateValue(e) {
        log.textContent = e.target.value;
    }
</script>

<div class="pedido-inscricao-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'nome')->textInput() ?>

    <?= $form->field($model, 'email')->textInput() ?>

    <?= $form->field($model, 'telemovel')->textInput(['maxlength' => true]) ?>

    <?php
        $paises = ArrayHelper::map(Pais::find()->all(), 'paisNome','paisNome');
        $paises = array_map('formatarPais', $paises);
        function formatarPais($pais){
            return mb_convert_case($pais, MB_CASE_TITLE, "UTF-8");
        }
    ?>
    <?= Html::label('País', 'pais')?><br>
    <?= Html::dropDownList('pais', null, $paises, ['class' => 'form-control', 'required' => 'true', 'id' =>'pais']) ?><br>

    <?= Html::label('Localidade', 'cidade', ['class' => 'control-label'])?>
    <?= Html::input('text', 'cidade', null, ['class' => 'form-control', 'required' => 'true', 'id' =>'cidade']) ?><br>
    <p id="values"></p>

    <?= Html::label('Código Postal', 'codpost', ['class' => 'control-label'])?>
    <?= Html::input('text', 'codpost', null, ['class' => 'form-control', 'required' => 'true', 'id' =>'codpost'])  ?><br>

    <?= Html::label('Morada', 'rua', ['class' => 'control-label'])?>
    <?= Html::input('text', 'rua', null, ['class' => 'form-control', 'required' => 'true', 'id' =>'rua'])  ?>

    <br>

    <div class="form-group">
        <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
