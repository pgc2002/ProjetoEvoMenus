<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\Item $model */

$this->title = 'Criar Item';

?>
<div class="item-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
