<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var frontend\models\PedidoInscricao $model */

$this->title = 'Criar Pedido de Inscricao';
?>
<div class="pedido-inscricao-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
