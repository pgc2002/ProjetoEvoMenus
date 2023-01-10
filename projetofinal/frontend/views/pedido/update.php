<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\Pedido $model */

$this->title = 'Alterar Pedido: ' . $model->id;
?>
<div class="pedidos-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <p><?= Html::a('Voltar para pedidos', ['index'], ['class' => 'btn btn-secondary']) ?></p>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
