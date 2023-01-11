<?php
use common\models\Categoria;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;
use yii\data\ActiveDataProvider;
use common\models\Item;
use common\models\Menu;

/** @var yii\web\View $this */
/** @var backend\models\CategoriaSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$idRestaurante = Yii::$app->request->get('id');
$categorias = Categoria::findAll(['idRestaurante' => $idRestaurante]);
$idCategoria = Yii::$app->request->get('idCategoria');
$this->title = 'Ementa de: '.\common\models\Restaurante::findOne($idRestaurante)->nome;
?>
<style>
    h1 {
    font-weight: normal;
    margin: 0;
    padding: 0 20px;
    line-height: 2;
    position: relative;
    transition: all .3s ease-out;
    }
    .sidebar {
        position: absolute;
    width: 100%;
    height: 100%;
    background-color: #333;
    left: 0;
    overflow: scroll;
    -webkit-overflow-scrolling: touch;
    }
    h3{
    background-color: #555;
    color: #ccc;
    margin: 0;
    padding: 0 20px;
    font-weight: normal;
    line-height: 2;
    text-transform: uppercase;
    font-size: 90%;
    } 
    aside nav ul{
    list-style: none;
    padding: 0;
    margin: 0;
    background-color: #333;
    }
    aside nav ul li{
        padding: 20px;
        color: #ccc;
        background-color: #333;
        margin-bottom: 1px;
        border-left: 10px solid transparent;
    }
    #sidebarCategorias{
    width: 100%;
    height: 100%;
    background-color: #333;
    left: 0;
    }
    #navEmenta a{
        text-decoration: none;
        color: black;
    }
    #navEmenta{
        padding-right: 5px;
        cursor: pointer;
        font-size: 18px;
    }
    #navEmenta a:hover{
        border-bottom: 3px solid #0080ff;
    }
    #navEmenta a.active {
        border-bottom: 3px solid #0080ff;
    }
</style>
<div class="categoria-index" style="height: 100%;">
    <?= Html::a(
        'Voltar para restaurante',
        Url::to(['..\restaurante\view', 'id' => $idRestaurante]),
        [
            'class'=>'btn btn-secondary',
        ]); ?>
    <div class="row" >
        <h1><?= Html::encode($this->title) ?></h1>
    </div>
    <div class="row" name="rowEmenta" >
        <div class="col-sm-2">
            <aside class="sidebar" id="sidebarCategorias">
                <h3>Categorias</h3>
                <nav>
                    <ul>
                        <?php 
                            foreach($categorias as $categoria)
                                echo '<li><a class="active" href="index?id='.$idRestaurante.'&idCategoria='.$categoria->id.'">'.$categoria->nome.'</a></li>';
                        ?>
                    </ul>
                </nav>
            </aside>
        </div>
        <div class="col-sm-10">
            <div class="container-fluid">
                <ul class="nav nav-tabs">
                    <li class="nav-item" id="navEmenta" ><a class="active" id="navItems" onclick="escolhaEmenta(1)" >Items</a></li>
                    <li class="nav-item" id="navEmenta" ><a id="navMenus"  onclick="escolhaEmenta(2)">Menus</a></li>
                </ul>
                <div class="tab-content" style="min-height: 400px;">
                    <div id="items" style="display: block">
                        <?php
                            if($idCategoria != null){
                                $categoria = Categoria::findOne(['id' => $idCategoria]);
                                
                                $dataProvider = new ActiveDataProvider([
                                    'query' => Item::find()->where(['idCategoria' => $idCategoria]),
                                    'pagination' => [
                                        'pageSize' => 4,
                                    ],
                                ]);
                                echo ' <h4>'.$categoria->nome.'</h4>';
                                echo GridView::widget([
                                    'dataProvider' => $dataProvider,
                                    'layout' => '{items}{pager}',
                                    'columns' => [
                                        'nome',
                                        'precoFormatado',
                                        [
                                            'attribute' => 'imagemItem',
                                            'format'=>['image', ['width'=>'100', 'height'=>'100']]
                                        ],
                                        [
                                            'format' => 'raw',
                                            'value' => function($model, $key, $index, $column) {
                                                return Html::a(
                                                    '<i class="fa fa-pencil"></i>',
                                                    Url::to(['item/update', 'idCategoria' => $model->idCategoria, 'id' => $model->id]), 
                                                    [
                                                        'id'=>'grid-custom-button',
                                                        'data-pjax'=>true,
                                                        'action'=>Url::to(['item/update', 'idCategoria' => $model->idCategoria, 'id' => $model->id]),
                                                        'class'=>'button btn btn-default',
                                                    ]
                                                );
                                            }
                                        ],
                                    ],
                                ]);
                                echo Html::a(
                                    'Adicionar item',
                                    Url::to(['item/create', 'idRestaurante' => $idRestaurante, 'idCategoria' => $idCategoria]),
                                    [
                                        'id'=>'grid-custom-button',
                                        'data-pjax'=>true,
                                        'class'=>'button btn btn-success',
                                    ]
                                );
                            }
                        ?>
                    </div>
                    <div id="menus" style="display: none">
                        <?php
                            if($idCategoria != null){
                                $categoria = Categoria::findOne(['id' => $idCategoria]);
                                
                                $dataProvider = new ActiveDataProvider([
                                    'query' => Menu::find()->where(['idCategoria' => $idCategoria]),
                                    'pagination' => [
                                        'pageSize' => 4,
                                    ],
                                ]);
                        
                                echo ' <h4>'.$categoria->nome.'</h4>';
                                echo GridView::widget([
                                    'dataProvider' => $dataProvider,
                                    'layout' => '{items}{pager}',
                                    'columns' => [
                                        'nome',
                                        'desconto',
                                        [
                                            'attribute' => 'imagemMenu',
                                            'format'=>['image', ['width'=>'100', 'height'=>'100']]
                                        ],
                                        [
                                            'format' => 'raw',
                                            'value' => function($model, $key, $index, $column) {
                                                return Html::a(
                                                    '<i class="fa fa-pencil"></i>',
                                                    Url::to(['menu/update', 'idCategoria' => $model->idCategoria, 'id' => $model->id]), 
                                                    [
                                                        'id'=>'grid-custom-button',
                                                        'data-pjax'=>true,
                                                        'action'=>Url::to(['menu/update', 'idCategoria' => $model->idCategoria, 'id' => $model->id]),
                                                        'class'=>'button btn btn-default',
                                                    ]
                                                );
                                            }
                                        ],
                                    ],
                                ]);
                                
                                echo Html::a(
                                    'Adicionar menu',
                                    Url::to(['menu/create', 'idRestaurante' => $idRestaurante, 'idCategoria' => $idCategoria]),
                                    [
                                        'id'=>'grid-custom-button',
                                        'data-pjax'=>true,
                                        'class'=>'button btn btn-success',
                                    ]
                                );
                            }
                            
                        ?>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="row" name="rowEmenta" >
        <div class="col-sm-2" style="vertical-align: center;">
        <?php
            echo Html::a(
                'Adicionar categoria',
                Url::to(['create', 'id' => $idRestaurante]),
                [
                    'id'=>'grid-custom-button',
                    'data-pjax'=>true,
                    'class'=>'button btn btn-success',
                ]
            );
        ?>
        </div>
        <div class="col-sm-10"></div>
    </div>

</div>

<script>
    function escolhaEmenta(x){
        var items = document.getElementById("items");
        var menus = document.getElementById("menus");
        var navItems = document.getElementById("navItems");
        var navMenus = document.getElementById("navMenus");

        navItems.classList.remove('active');
        navMenus.classList.remove('active');

        items.style.display = "none";
        menus.style.display = "none";

        switch(x){
            case 1:
                items.style.display = "block";
                navItems.classList.add('active');
                break;
            case 2:
                menus.style.display = "block";
                navMenus.classList.add('active');
                break;
        }
    }
</script>