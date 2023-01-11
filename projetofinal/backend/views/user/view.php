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

$this->title = 'Detalhes do Utilizador: '.$model->username;
?>
<div class="user-view">
    <p>

        <?php
            if(isset($_GET['sb'])){
                /*switch (){
                    case 'homePage':
                        echo Html::a('Voltar', ['site/index'], ['class' => 'btn btn-secondary']);
                        break;
                }*/
            }else
                echo Html::a('Voltar', ['index'], ['class' => 'btn btn-secondary'])
        ?>
    </p>

    <h3><?= Html::encode($this->title) ?></h3>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            //'id',
            'username',
            'nome',
            //'auth_key',
            //'password_hash',
            //'password_reset_token',
            'email',
            //'status',
            //'updated_at',
            //'verification_token',
            'telemovel',
            'nif',
            'moradaFormatada',
            //'idRestaurante',
            //'idMorada',
            //'idMesa',
            'tipo',
            [
                'attribute' => 'created_at',
                'label' => 'Criádo em',
                'value' => Yii::$app->formatter->asDatetime($model->created_at, 'php:d-m-Y H:i:s'),
            ],
        ],
    ]) ?>

    <p>
        <?php
            if(isset($_GET['sb'])){
                echo Html::a('Editar', ['update', 'id' => $model->id, 'e' => 1, 'sb' => $_GET['sb']], ['class' => 'btn btn-primary']);
            }else{
                echo Html::a('Editar', ['update', 'id' => $model->id, 'e' => 1], ['class' => 'btn btn-primary']);
            }
        ?>

        <?php
            if(Yii::$app->user->identity->id != $model->id)
                echo Html::a('Apagar', ['delete', 'id' => $model->id], [
                'class' => 'btn btn-danger',
                'data' => [
                    'confirm' => 'Tem a certeza que quer apagar este utilizador?',
                    'method' => 'post',
                ],]);
        ?>
    </p>
</div>
