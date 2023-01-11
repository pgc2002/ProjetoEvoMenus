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

<?php $form = ActiveForm::begin(); ?>
    <?php
        switch ($user->tipo){
            case 'Admin':
                $this->title = 'Editar Administrador: ' . $user->username;
                break;
            case 'Gestor':
                $this->title = 'Editar Gestor: ' . $user->username;
                break;
            case 'Funcionario':
                $this->title = 'Editar Funcionário: ' . $user->username;
                break;
        }
    ?>
<?php /**/ ?>

<?php
/*
echo $form->field($user, 'username')->textInput(['maxlength' => true]);
echo Html::label('Password', 'username', ['class' => 'label username'])
echo HTML::input('password', 'password', '', ['class' => 'form-control'])
echo <br>*/

if(isset($_GET['e']) && isset($_GET['sb']))
    echo '<p>'. Html::a('Voltar', ['view', 'id' => $user->id, 'sb' => $_GET['sb']], ['class' => 'btn btn-secondary']) .'</p>';
else if(isset($_GET['e']))
    echo '<p>'. Html::a('Voltar', ['view', 'id' => $user->id], ['class' => 'btn btn-secondary']) .'</p>';
else
    echo '<p>'. Html::a('Voltar', ['index'], ['class' => 'btn btn-secondary']) .'</p>';
?>
        <h3><?= Html::encode($this->title) ?></h3>

        <?= $form->field($user, 'telemovel')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'nif')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'nome')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'email')->textInput(['maxlength' => true]) ?>

        <?= $form->field($user, 'status')->hiddenInput(['value' => 10])->label(false) ?>

        <?php
            if($user->tipo != 'Admin'){
                $form->field($user, 'idRestaurante')->dropDownList(ArrayHelper::map(Restaurante::find()->all(), 'id', 'nome'))->label('Restaurante');
            }
        ?>

    <div class="form-group">
        <?= Html::submitButton('Save', ['class' => 'btn btn-success']) ?>
    </div>
<?php ActiveForm::end(); ?>