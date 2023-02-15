<?php

use common\models\Categoria;
use yii\helpers\Html;
use yii\helpers\Url;

/** @var yii\web\View $this */
/** @var common\models\Menu $model */

$this->title = 'Editar Menu: ' . $model->id;
?>
<div class="menu-update">

    <?php
        $categoria = Categoria::find()->where(['id' => $_GET['idCategoria']])->one();
        echo Html::a(
            'Cancelar',
            Url::to(['..\categoria\index', 'id' => $categoria->idRestaurante, 'idCategoria' => $categoria->id]),
            [
                'id' => 'grid-custom-button',
                'data-pjax' => true,
                'class' => 'btn btn-secondary',
            ]
        );
    ?>

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
