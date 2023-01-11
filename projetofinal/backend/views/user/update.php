<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var common\models\User $user */
/** @var common\models\Morada $morada */

$this->title = 'Update User: ' . $user->id;
?>
<div class="user-update">
    <h1><?= Html::encode($this->title) ?></h1>
    <?php
        if($user->tipo == 'Cliente'){
            return $this->render('formUpdateClientes', [
                'user' => $user,
                'morada' => $morada,
            ]);
        }else{
            return $this->render('formUpdate', [
                'user' => $user,
            ]);
        }
    ?>
</div>
