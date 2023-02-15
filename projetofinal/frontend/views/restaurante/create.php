<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\Restaurante $model */

$this->title = 'Create Restaurante';
$this->params['breadcrumbs'][] = ['label' => 'restaurante', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="restaurante-create" id="siteBody">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
