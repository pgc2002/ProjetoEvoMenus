<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\Pedido $model */

$this->title = 'Create Pedido';
$this->params['breadcrumbs'][] = ['label' => 'Pedido', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="pedidos-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
