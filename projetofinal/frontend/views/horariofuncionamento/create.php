<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\Horariofuncionamento $model */

$this->title = 'Create Horario Funcionamento';
$this->params['breadcrumbs'][] = ['label' => 'Horario Funcionamentos', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="horario-funcionamento-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
