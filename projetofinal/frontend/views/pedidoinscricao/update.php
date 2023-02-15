<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var frontend\models\PedidoInscricao $model */

$this->title = 'Update Pedido Inscricao: ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Pedido Inscricaos', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="pedido-inscricao-update" id="siteBody">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
