<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\Mesa $model */

$this->title = 'Criar Mesa';
?>
<div class="mesa-create" id="siteBody">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
