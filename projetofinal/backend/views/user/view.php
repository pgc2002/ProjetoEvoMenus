<?php

use yii\helpers\Html;
use yii\widgets\DetailView;
use common\models\Morada;

/** @var yii\web\View $this */
/** @var common\models\User $model */

/*$morada = Morada::find()
    ->where(['id' == $model->idMorada])
    ->all();*/

$morada = Morada::findOne([
    'id' => $model->idMorada,
]);

$this->title = $model->id;
\yii\web\YiiAsset::register($this);
?>
<div class="user-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->id], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->id], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            //'id',
            'username',
            'nome',
            'auth_key',
            'password_hash',
            //'password_reset_token',
            'email:email',
            //'status',
            [
                'attribute' => 'created_at',
                'label' => 'Criádo em',
                'value' => Yii::$app->formatter->asDatetime($model->created_at, 'php:d-m-Y H:i:s'),
            ],
            //'updated_at',
            //'verification_token',
            'telemovel',
            'nif',
            'tipo',
            'moradaFormatada',
            //'idRestaurante',
            //'idMorada',
            //'idMesa',
        ],
    ]) ?>
</div>
