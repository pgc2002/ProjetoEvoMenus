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

    <?php
    $form = ActiveForm::begin(['options' => ['enctype' => 'multipart/form-data']]); ?>

    <?= $form->field($model, 'nome')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'fotografia')->fileInput()?>

    <?= $form->field($model, 'desconto')->textInput() ?>

    <input type="hidden" id="idItems" name="idItems" value=""></div>

    <?php /*$form->field($model, 'idCategoria')->hiddenInput(['value' => $idCategoria->label(false))*/ ?>

    <div class="row">
        <div class="col-sm-3">
            <?php $url=Url::toRoute('item/lists'); ?>
            <?=
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
        </div>
        <div class="col-sm-2 my-auto">
            <?php
               echo Html::button('Adicionar item',
                   [
                       'class' => 'btn btn-primary',
                       'id' => 'botaoItem',
                       'name' => 'botaoItem',
                   ]);
            ?>
        </div>
    </div>
    <table class="table" >
        <thead>
            <tr>
                <th scope="col">Nome</th>
                <th scope="col">Preço</th>
                <th scope="col">Categoria</th>
            </tr>
        </thead>
        <tbody id="tabelaItens">
        </tbody>
    </table>
    <div class="form-group">
        <?= Html::submitButton('Guardar alterações', ['class' => 'btn btn-success', 'id' => 'botaoSubmeter',]) ?>
    </div>
    <?php ActiveForm::end(); ?>
</div>


<script>
    var array = [];

    $('#botaoItem').on('click', function(e)
    {
        e.preventDefault();
        var id = $( "select#menu-items option:checked" ).val();
        if(id != "" && !array.includes(id))
        {
            array.push(id);
            console.log(array);
            const textoItem = $("select#menu-items option:checked").text().split(" | ");
            var table = document.getElementById("tabelaItens");
            var row = table.insertRow(0);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            cell1.innerHTML = textoItem[0];
            cell2.innerHTML = textoItem[1];
            cell3.innerHTML = $("select#menu-idcategoria option:checked").text();
            if(array.length != 1)
                $( "input#idItems" ).val($( "input#idItems" ).val() + "," + id);
            else
                $( "input#idItems" ).val($( "input#idItems" ).val() + id);
        }
    });
</script>
