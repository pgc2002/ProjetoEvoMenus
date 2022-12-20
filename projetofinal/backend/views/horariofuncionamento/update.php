<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\Horariofuncionamento $model */

$this->title = 'Update Horario Funcionamento: ' . $model->id;
?>
<div class="horario-funcionamento-update">

    <h2>Hórario de Funcionamento</h2>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
