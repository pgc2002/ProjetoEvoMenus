<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\User $user */
/** @var common\models\Morada $morada */

$this->title = 'Criar Utilizador';


?>
<div class="user-create">
    <p>&nbsp&nbsp<?= Html::a('Voltar', ['index'], ['class' => 'btn btn-secondary']) ?></p>

    <?= $this->render('_form', [
        'user' => $user,
        'morada' => $morada,
    ]) ?>

</div>
