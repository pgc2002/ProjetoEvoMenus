<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\User $user */

$this->title = 'Editar Funcionário';
?>
<div class="user-update" id="siteBody">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'user' => $user,
    ]) ?>
</div>
