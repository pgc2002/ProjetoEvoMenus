<?php

use common\models\User;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;
use yii\data\ActiveDataProvider;

/** @var yii\web\View $this */
/** @var app\models\UserSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Funcionários';

?>

<div class="user-index" id="siteBody">
    <h1><?= Html::encode($this->title) ?></h1>

    <p><?php
    if(Yii::$app->user->can('crudFuncionarios'));
    echo Html::a('Criar Funcionários', ['create?c=1'], ['class' => 'btn btn-success']);
    ?></p>
    <?php 
        $cookies = Yii::$app->request->cookies;
        $idRestaurante = $cookies->getValue('idRestaurante');
        $dataProvider = new ActiveDataProvider([
            'query' => User::find()->where(['idRestaurante' => $idRestaurante, 'tipo' => 'Funcionario']),
            'pagination' => [
                'pageSize' => 4,
            ],
        ]);
    ?>




    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'layout' => '{items}{pager}',
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],
            'id',
            'username',
            [
                'attribute' => 'nome',
                'label' => 'Nome Completo',
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
                'urlCreator' => function ($action, User $model, $key, $index, $column) {
                    return Url::toRoute([$action, 'id' => $model->id]);
                 }
            ],
        ],
    ]); ?>
</div>
