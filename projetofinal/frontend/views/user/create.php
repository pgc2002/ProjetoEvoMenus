<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\User $user */
/** @var common\models\Morada $morada */

$this->title = 'Criar Funcionário';
?>
<div class="user-create" id="siteBody">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'user' => $user,
    ]) ?>

</div>
