<?php

use common\models\Pedido;

use yii\data\ArrayDataProvider;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

$cookies = Yii::$app->request->cookies;
$idRestaurante = $cookies->getValue('idRestaurante');
//$idRestaurante = \common\models\User::findOne(Yii::$app->user->id)->idRestaurante;

/** @var yii\web\View $this */
/** @var backend\models\PedidoSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Pedidos';
?>
<div class="pedidos-index">
    <style>
        .navUsers a{
            text-decoration: none;
            color: black;
        }
        .navUsers{
            padding-right: 5px;
        }
        .navUsers a:hover{
            border-bottom: 3px solid #0080ff;
        }
        .navUsers a.active {
            border-bottom: 3px solid #0080ff;
        }
    </style>
    <h1><?= Html::encode($this->title) ?></h1>

    <ul class="nav">
        <li class="navUsers"><a class="active" id="navRecebidos" href="#recebidos" onclick="escolhaPedidos(1)" >Recebidos</a></li>
        <li class="navUsers"><a id="navExpedidos" href="#expedidos" onclick="escolhaPedidos(2)">Expedidos</a></li>
        <li class="navUsers"><a id="navConcluidos" href="#concluidos" onclick="escolhaPedidos(3)">Concluidos</a></li>
    </ul>

    <?php
        $pedidos = Pedido::find()->where(['estado' => 'recebido', 'idRestaurante' => $idRestaurante])->all();


        $dataProvider = new ArrayDataProvider([
            'allModels' => $pedidos,
        ]);

        echo '<div id="tabelaRecebidos" style="display: block">';
        echo GridView::widget([
            'dataProvider' => $dataProvider,
            'layout' => '{items}{pager}',
            'columns' => [
                [
                    'class' => 'yii\grid\ActionColumn',
                    'template' => '{detalhes}{expedir}',
                    'buttons' => [
                        'detalhes' => function($url, $model, $key) {
                            return Html::a('Ver detalhes', ['pedido/view', 'id' => $model->id], ['class' => 'btn btn-success btn-xs', 'data-pjax' => 0]);
                        },
                        'expedir' => function($url, $model, $key) {
                            return Html::a('Expedir', ['pedido/expedir', 'id' => $model->id], ['class' => 'btn btn-success btn-xs', 'data-pjax' => 0]);
                        }
                    ]
                ],
                [
                    'attribute' => 'usernameCliente',
                    'label' => 'Cliente',
                ],
                'valorTotal',
                [
                    'label' => 'Conteudos',
                    'value' => function($data){
                        $output ="";
                        $menus = $data->getMenus()->select('nome')->all();
                        foreach($menus as $key => $menu){
                            if($key == 0)
                                $output .= $menu->nome;
                            else
                                $output .= " + ".$menu->nome;
                        }
                        $items = $data->getItems()->select('nome')->all();
                        foreach($items as $item){
                            $output .= " + ".$item->nome;
                        }
                        return $output;
                    }
                ],
            ]
        ]);
        echo '</div>';
        $pedidos = Pedido::find()->where(['estado' => 'expedido', 'idRestaurante' => $idRestaurante])->all();
        

        $dataProvider = new ArrayDataProvider([
            'allModels' => $pedidos,
        ]);

        echo '<div id="tabelaExpedidos" style="display: none">';
        echo GridView::widget([
            'dataProvider' => $dataProvider,
            'layout' => '{items}{pager}',
            'columns' => [
                [
                    'class' => 'yii\grid\ActionColumn',
                    'template' => '{detalhes}{concluir}',
                    'buttons' => [
                        'detalhes' => function($url, $model, $key) {
                            return Html::a('Ver detalhes', ['pedido/view', 'id' => $model->id], ['class' => 'btn btn-success btn-xs', 'data-pjax' => 0]);
                        },
                        'concluir' => function($url, $model, $key) {
                            return Html::a('Concluir', ['pedido/concluir', 'id' => $model->id], ['class' => 'btn btn-success btn-xs', 'data-pjax' => 0]);
                        }
                    ]
                ],
                [
                    'attribute' => 'usernameCliente',
                    'label' => 'Cliente',
                ],
                'valorTotal',
                [
                    'label' => 'Conteudos',
                    'value' => function($data){
                        $output ="";
                        $menus = $data->getMenus()->select('nome')->all();
                        foreach($menus as $key => $menu){
                            if($key == 0)
                                $output .= $menu->nome;
                            else
                                $output .= " + ".$menu->nome;
                        }
                        $items = $data->getItems()->select('nome')->all();
                        foreach($items as $item){
                            $output .= " + ".$item->nome;
                        }
                        return $output;
                    }
                ],
            ],
        ]);
        echo '</div>';

        $pedidos = Pedido::find()->where(['estado' => 'concluido', 'idRestaurante' => $idRestaurante])->all();

        $dataProvider = new ArrayDataProvider([
            'allModels' => $pedidos,
        ]);

        echo '<div id="tabelasConcluidos" style="display: none">';
        echo GridView::widget([
            'dataProvider' => $dataProvider,
            'layout' => '{items}{pager}',
            'columns' => [
                [
                    'class' => 'yii\grid\ActionColumn',
                    'template' => '{detalhes}',
                    'buttons' => [
                        'detalhes' => function($url, $model, $key) {
                            return Html::a('Ver detalhes', ['pedido/view', 'id' => $model->id], ['class' => 'btn btn-success btn-xs', 'data-pjax' => 0]);
                        },
                    ]
                ],
                [
                    'attribute' => 'usernameCliente',
                    'label' => 'Cliente',
                ],
                'valorTotal',
                [
                    'label' => 'Conteudos',
                    'value' => function($data){
                        $output ="";
                        $menus = $data->getMenus()->select('nome')->all();
                        foreach($menus as $key => $menu){
                            if($key == 0)
                                $output .= $menu->nome;
                            else
                                $output .= " + ".$menu->nome;
                        }
                        $items = $data->getItems()->select('nome')->all();
                        foreach($items as $item){
                            $output .= " + ".$item->nome;
                        }
                        return $output;
                    }
                ],
            ],
        ]);
        echo '</div>';
    ?>
    </div>
    <script>
        $(document).ready(function() {
            if (window.location.href.indexOf("#recebidos") > -1)
                escolhaPedidos(1);
            else if (window.location.href.indexOf("#expedidos") > -1)
                escolhaPedidos(2);
            else if(window.location.href.indexOf("#concluidos") > -1)
                escolhaPedidos(3);
        });

        function escolhaPedidos(x){
            var tabelaRecebidos = document.getElementById("tabelaRecebidos");
            var tabelaExpedidos = document.getElementById("tabelaExpedidos");
            var tabelaConcluidos = document.getElementById("tabelasConcluidos");
            
            var navRecebidos = document.getElementById("navRecebidos");
            var navExpedidos = document.getElementById("navExpedidos");
            var navConcluidos = document.getElementById("navConcluidos");

            tabelaRecebidos.style.display = "none";
            tabelaExpedidos.style.display = "none";
            tabelaConcluidos.style.display = "none";

            navRecebidos.classList.remove('active');
            navExpedidos.classList.remove('active');
            navConcluidos.classList.remove('active');

            switch(x){
                case 1:
                    tabelaRecebidos.style.display = "block";
                    navRecebidos.classList.add('active');
                    break;
                case 2:
                    tabelaExpedidos.style.display = "block";
                    navExpedidos.classList.add('active');
                    break;
                case 3:
                    tabelaConcluidos.style.display = "block";
                    navConcluidos.classList.add('active');
                    break;
            }
        }
    </script>


