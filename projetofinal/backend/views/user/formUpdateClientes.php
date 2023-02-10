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

<?php $form = ActiveForm::begin();
    $this->title = 'Editar Cliente: ' . $user->username;
?>

<h3><?= Html::encode($this->title) ?></h3>

<?php
/*
echo $form->field($user, 'username')->textInput(['maxlength' => true]);
echo Html::label('Password', 'username', ['class' => 'label username'])
echo HTML::input('password', 'password', '', ['class' => 'form-control'])
echo <br>*/
if(isset($_GET['e']))
    echo '<p>'. Html::a('Voltar', ['view', 'id' => $user->id], ['class' => 'btn btn-secondary']) .'</p>';
else
    echo '<p>'. Html::a('Voltar', ['index'], ['class' => 'btn btn-secondary']) .'</p>';
?>

<?= $form->field($user, 'telemovel')->textInput(['type' => 'tel', 'maxlength' => true, 'pattern' => '[0-9]{9}', "style" => "width:130px"]) ?>

<?= $form->field($user, 'nif')->textInput(['maxlength' => true, 'pattern' => '[0-9]{9}', "style" => "width:100px"]) ?>

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
<?= Html::dropDownList('pais', strtoupper($morada->pais), $paises, ['class' => 'form-control', 'required' => 'true', 'id' =>'pais', "style" => "width:310px"]) ?><br>

<?= $form->field($morada, 'cidade')->textInput(['type' => 'text','maxlength' => true, "style" => "width:310px"]) ?>

<?= $form->field($morada, 'rua')->textInput(['type' => 'text', 'maxlength' => true, "style" => "width:800px"])->label("Morada") ?>

<?= Html::label('Código Postal', 'codpost', ['class' => 'control-label'])?>
<div class="row" style="padding-left: 8px;">
    <?php
        if($morada->codpost != null){
            $codpost = explode('-', $morada->codpost);
                echo Html::input('text', 'codpost1', $codpost[0], ['class' => 'form-control', 'required' => 'true', 'maxlength' => 5, 'pattern' => '[0-9]{5}', "style" => "width:70px;", 'id' =>'codpost1']);
                echo '<i class="fa fa-window-minimize" style="padding: 5px" aria-hidden="true"></i>';
                echo Html::input('text', 'codpost2', $codpost[1], ['class' => 'form-control', 'required' => 'true', 'maxlength' => 3, 'pattern' => '[0-9]{3}', "style" => "width:50px", 'id' =>'codpost2']);
        }else{
            echo Html::input('text', 'codpost1', null, ['class' => 'form-control', 'required' => 'true', 'maxlength' => 5, 'pattern' => '[0-9]{5}', "style" => "width:70px;", 'id' =>'codpost1']);
            echo '<i class="fa fa-window-minimize" style="padding: 5px" aria-hidden="true"></i>';
            echo Html::input('text', 'codpost2', null, ['class' => 'form-control', 'required' => 'true', 'maxlength' => 3, 'pattern' => '[0-9]{3}', "style" => "width:50px", 'id' =>'codpost2']);
        }
    ?>
</div>
<br>

<div class="form-group">
    <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
</div>

<?php ActiveForm::end(); ?>
