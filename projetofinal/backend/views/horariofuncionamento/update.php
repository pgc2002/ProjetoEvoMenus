<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\Horariofuncionamento $model */

$this->title = 'Update Horario Funcionamento: ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Horario Funcionamentos', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="horario-funcionamento-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
