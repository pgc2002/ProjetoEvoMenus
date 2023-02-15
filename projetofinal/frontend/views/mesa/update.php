<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\Mesa $model */

$this->title = 'Alterar Mesa ' . $model->numero . ' de ' . \common\models\Restaurante::findOne($model->idRestaurante)->nome;
?>
<div class="mesa-update" id="siteBody">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
