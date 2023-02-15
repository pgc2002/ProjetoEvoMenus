<?php

/** @var yii\web\View $this */
/** @var yii\bootstrap4\ActiveForm $form */
/** @var \frontend\models\SignupForm $model */

use yii\bootstrap4\Html;
use yii\bootstrap4\ActiveForm;

$this->title = 'Inscrição';
$this->params['breadcrumbs'][] = $this->title;
?>
<style>
    .site-signup{
        padding:10px;
    }
</style>

<div class="site-signup">
    <h1><?= Html::encode($this->title) ?></h1> <p>


    <h5>Por favor preencha os seguintes campos do requerimento:</h5><p>

    </p><div class="row" id="siteBody">
        <div class="col-lg-5">
            <?php $form = ActiveForm::begin(['id' => 'form-signup']); ?>

                <?php /*$form->field($model, 'username')->textInput(['autofocus' => true])*/?>

                <?= $form->field($model, 'NomeRestaurante')->label('Nome do restaurante') ?>

                <?= $form->field($model, 'email')->label('Email do restaurante') ?>

                <?php /*$form->field($model, 'password')->passwordInput()*/?>

                <?= $form->field($model, 'Pais')->label('País do restaurante')?>

                <?= $form->field($model, 'Cidade')->label('Cidade do restaurante')?>

                <?= $form->field($model, 'Rua')->label('Rua do restaurante')?>

                <?= $form->field($model, 'CodPostal')->label('Código Postal')?>

                <?= $form->field($model, 'Telemovel')->label('Contacto telefónico do restaurante')?>


            <div class="form-group">
                    <?= Html::submitButton('Signup', ['class' => 'btn btn-primary', 'name' => 'signup-button']) ?>
                </div>

            <?php ActiveForm::end(); ?>
        </div>
    </div>
</div>
