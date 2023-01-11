<?php

use common\models\PedidoInscricao;
use common\models\user;
use common\models\Restaurante;

/** @var app\models\PedidoInscricaoSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Starter Page';
$this->params['breadcrumbs'] = [['label' => $this->title]];
$pedidosInscricao = Pedidoinscricao::find()->count();
$UserAdminCount = User::find()->where(['tipo'=>'Admin']) ->count();
$UserGestorCount = User::find()->where(['tipo'=>'Gestor']) ->count();
$UserFuncionarioCount = User::find()->where(['tipo'=>'Funcionario']) ->count();
$UserClientesCount = User::find()->where(['tipo'=>'Cliente']) ->count();
$RestaurantesCount = Restaurante::find()->count();

?>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 col-sm-6 col-12">
            <?= \hail812\adminlte\widgets\InfoBox::widget([
                'text' => 'Utilizadores Admin',
                'number' => $UserAdminCount,
                'icon' => 'fas fa-id-badge',
            ]) ?>
        </div>
        <div class="col-md-4 col-sm-6 col-12">
            <?= \hail812\adminlte\widgets\InfoBox::widget([
                'text' => 'Utilizadores Gestor',
                'number' => $UserGestorCount,
                'icon' => 'fas fa-id-badge',
            ]) ?>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-sm-6 col-12">
            <?= \hail812\adminlte\widgets\InfoBox::widget([
                'text' => 'Utilizadores Funcionarios',
                'number' => $UserFuncionarioCount,
                'icon' => 'fas fa-id-badge',
            ]) ?>
        </div>
        <div class="col-md-4 col-sm-6 col-12">
            <?= \hail812\adminlte\widgets\InfoBox::widget([
                'text' => 'Utilizadores Clientes',
                'number' => $UserClientesCount,
                'icon' => 'fas fa-id-badge',
            ]) ?>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-sm-6 col-12">
            <?= \hail812\adminlte\widgets\InfoBox::widget([
                'text' => 'Pedidos de inscrição',
                'number' => $pedidosInscricao,
                'icon' => 'far fa-envelope',
            ]) ?>
        </div>
        <div class="col-md-4 col-sm-6 col-12">
            <?= \hail812\adminlte\widgets\InfoBox::widget([
                'text' => 'Numero de restaurantes inscritos',
                'number' => $RestaurantesCount,
                'icon' => 'fas fa-glass-martini',
            ]) ?>
        </div>
    </div>



</div>