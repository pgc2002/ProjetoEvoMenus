<?php

use yii\helpers\Html;
use yii\helpers\Url;

/** @var yii\web\View $this */
/** @var common\models\Horariofuncionamento $model */
$restaurante = \common\models\Restaurante::findOne($model->getRestauranteId());

$this->title = 'Editar Horário Funcionamento: '. $restaurante->nome;
?>
<div class="horario-funcionamento-update" id="siteBody">

    <?= Html::a(
        'Voltar para restaurante',
        Url::to(['..\restaurante\view', 'id' => $model->getRestauranteId()]),
        [
            'class'=>'btn btn-secondary',
        ]); ?>

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
