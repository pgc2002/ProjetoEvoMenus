<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use common\models\Categoria;
use common\models\Item;
use yii\grid\GridView;
use yii\helpers\Url;
use yii\data\ActiveDataProvider;

/** @var yii\web\View $this */
/** @var common\models\Menu $model */
/** @var yii\widgets\ActiveForm $form */
$idRestaurante = Yii::$app->request->get('idRestaurante');
$idCategoria = Yii::$app->request->get('idCategoria');
$categorias = Categoria::findAll(['idRestaurante' => $idRestaurante]);

if(count($categorias) > 0)
    $items = Item::findAll(['idCategoria' => $categorias[0]->id]);
?>

<div class="menu-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'nome')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'fotografia')->textarea(['rows' => 1]) ?>

    <?= $form->field($model, 'desconto')->textInput() ?>

    <?php /*$form->field($model, 'idCategoria')->hiddenInput(['value' => $idCategoria->label(false))*/ ?>

    <div class="row">
        <div class="col-sm-3">
            <?php $url=Url::toRoute('item/lists'); ?>
            <?=
                /*
                echo '
                <select class="form-select" aria-label="Default select example" name="dropCat" id="dropCat">
                    <option selected>Categorias</option>';
                        foreach($categorias as $categoria){
                            echo'<option value='.$categoria->id.'>'.$categoria->nome.'</option>';
                        }
                echo '</select>';*/
                //$url=Url::toRoute('item/lists');
                //echo $url;
                $form->field($model, 'idCategoria')->dropDownList(
                    ArrayHelper::map(Categoria::find()->all(), 'id', 'nome'),
                    [
                        'prompt' => 'Selecionar categoria',
                        'onchange' => '$.post("'.$url.'?id='.'"+$(this).val(), function( data ) 
                            {
                                $("select#menu-items").html( data );
                            });'

                    ]
                );
            ?>



        </div>
        <div class="col-sm-7">
            <?=
                $form->field($model, 'items')->dropDownList(
                    ArrayHelper::map(Item::find()->all(), 'id', 'nome'),
                    [
                        'prompt' => 'Selecionar item',
                    ]
                );
            ?>
            <?php
                /*$form->field($model, 'items')->dropDownList(
                    ArrayHelper::map([], 'id', 'nome'),
                    [
                        'disabled' => 'disabled',
                        'prompt' => 'Escolha a categoria primeiro',
                        'style' => 'width: 100%;',
                    ]
                );*/
            ?>
        </div>
        <div class="col-sm-2">
            <?php
                echo Html::a(
                    'Adicionar item ao menu',
                    Url::to(['menu/create', 'idRestaurante' => $idRestaurante, 'idCategoria' => $idCategoria]),
                    [
                        'id'=>'grid-custom-button',
                        'data-pjax'=>true,
                        'class'=>'button btn btn-success',
                    ]
                );
                /*echo Html::dropDownList(ArrayHelper::map($categorias), 'id', 'nome',
                [
                    'prompt'=>'Escolha a categoria',
                ]);*/
            ?>
        </div>
    </div>

    <div class="form-group">
        <?= Html::submitButton('Guardar alterações', ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
