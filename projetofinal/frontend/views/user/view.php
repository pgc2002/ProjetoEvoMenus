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

$this->title = 'Detalhes do Funcionário';
\yii\web\YiiAsset::register($this);
?>
<div class="user-view" id="siteBody">

    <br>

    <p>
        <?= Html::a('Voltar', ['index'], ['class' => 'btn btn-secondary']) ?>
        <?= Html::a('Update', ['update', 'id' => $model->id], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->id], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Tem a certeza que quer apagar este utilizador?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <h1><?= Html::encode($this->title) ?></h1>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            //'id',
            'username',
            'nome',
            //'password_reset_token',
            'email',
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
            //'idRestaurante',
            //'idMorada',
            //'idMesa',
        ],
    ]) ?>
</div>
