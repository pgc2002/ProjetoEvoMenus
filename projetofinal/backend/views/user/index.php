<?php

use common\models\User;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var app\models\UserSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Utilizadores';
?>

<div class="user-index">
    <h1><?= Html::encode($this->title) ?></h1>

    <p><?= Html::a('Criar Utilizador', ['create'], ['class' => 'btn btn-success']) ?></p>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'layout' => '{items}{pager}',
        'columns' => [
            'id',
            'username',
            [
                'attribute' => 'nome',
                'label' => 'Nome',
            ],
            //'auth_key',
            //'password_hash',
            //'password_reset_token',
            //'email:email',
            //'status',
            //'created_at',
            //'updated_at',
            //'verification_token',
            [
                'attribute' => 'telemovel',
                'label' => 'Telemóvel',
            ],
            //'nif',
            'tipo',
            //'idRestaurante',
            //'idMorada',
            //'idMesa',
            [
                'class' => ActionColumn::className(),
                'template' => '{view} {update} {delete}',
                'buttons' => [
                    'delete' => function ($url, $model, $key) {
                        if(Yii::$app->user->identity->id != $model->id) {
                            return Html::a('<span class="glyphicon glyphicon-trash"></span>', ['delete', 'id' => $model->id], [
                                'class' => 'fa fa-trash',
                                'data' => [
                                    'confirm' => 'Tem a certeza que quer apagar este utilizador?',
                                    'method' => 'post',
                                ],
                            ]);
                        }
                    }
                ],
                'urlCreator' => function ($action, User $model, $key, $index, $column) {
                    return Url::toRoute([$action, 'id' => $model->id]);
                 }
            ],
        ],
    ]); ?>
</div>
