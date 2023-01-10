<?php

use yii\helpers\Html;
use backend\assets\AppAsset;
use common\widgets\Alert;
use yii\bootstrap4\Breadcrumbs;
use yii\bootstrap4\Nav;
use yii\bootstrap4\NavBar;
use common\models\User;

?>
<!-- Navbar -->
<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="<?=\yii\helpers\Url::home()?>" class="nav-link">Home</a>
        </li>
        <?php 
            if (!Yii::$app->user->isGuest) {
                $cookies = Yii::$app->request->cookies;
                $idRestaurante = $cookies->getValue('idRestaurante');
                $user = User::findOne(Yii::$app->user->identity->id);
                switch($user->tipo){
                    case "Cliente":
                        $link = \yii\helpers\Url::home()."restaurante/index";
                        echo '<li class="nav-item d-none d-sm-inline-block">
                            <a href="'.$link.'" class="nav-link">Restaurantes</a>
                        </li>';
                        break;
                    case "Funcionario":
                        $link = \yii\helpers\Url::home()."restaurante/view?id=".$idRestaurante;
                        echo '<li class="nav-item d-none d-sm-inline-block">
                            <a href="'.$link.'" class="nav-link">Restaurante</a>
                        </li>';
                        $link = \yii\helpers\Url::home()."categoria/index";
                        echo '<li class="nav-item d-none d-sm-inline-block">
                            <a href="'.$link.'" class="nav-link">Menu</a>
                        </li>';
                        $link = \yii\helpers\Url::home()."mesa/index";
                        echo '<li class="nav-item d-none d-sm-inline-block">
                            <a href="'.$link.'" class="nav-link">Mesas</a>
                        </li>';
                        $link = \yii\helpers\Url::home()."pedido/index";
                        echo '<li class="nav-item d-none d-sm-inline-block">
                            <a href="'.$link.'" class="nav-link">Pedidos</a>
                        </li>';
                        break;
                    case "Gestor":
                        $link = \yii\helpers\Url::home()."restaurante/view?id=".$idRestaurante;
                        echo '<li class="nav-item d-none d-sm-inline-block">
                            <a href="'.$link.'" class="nav-link">Restaurante</a>
                        </li>';
                        $link = \yii\helpers\Url::home()."user/index";
                        echo '<li class="nav-item d-none d-sm-inline-block">
                            <a href="'.$link.'" class="nav-link">Funcionários</a>
                        </li>';
                        $link = \yii\helpers\Url::home()."categoria/index";
                        echo '<li class="nav-item d-none d-sm-inline-block">
                            <a href="'.$link.'" class="nav-link">Menu</a>
                        </li>';
                        $link = \yii\helpers\Url::home()."mesa/index";
                        echo '<li class="nav-item d-none d-sm-inline-block">
                            <a href="'.$link.'" class="nav-link">Mesas</a>
                        </li>';
                        $link = \yii\helpers\Url::home()."pedido/index";
                        echo '<li class="nav-item d-none d-sm-inline-block">
                            <a href="'.$link.'" class="nav-link">Pedidos</a>
                        </li>';
                        break;
                    case "Admin":
                        Yii::$app->user->logout();
                        Yii::$app->response->redirect(['../../backend/web']);
                }
            }
            else{
                $link = \yii\helpers\Url::home()."restaurante/index";
                        echo '<li class="nav-item d-none d-sm-inline-block">
                            <a href="'.$link.'" class="nav-link">Restaurantes</a>
                        </li>';
            }
        ?>
        
    </ul>

    <ul class="navbar-nav ml-auto">
        <?php 
            if(Yii::$app->user->isGuest){
                echo '
                <li class="nav-item">'.
                    Html::a(
                        'Login',
                        ['/site/login'],
                        ['class' => 'btn btn-primary']
                    ).'
                </li>';
            }else{
                $tag = '<i class="fas fa-sign-out-alt"></i>';
                echo "
                <li class='nav-item'>
                    ".Html::a($tag, ['/site/logout'], ['data-method' => 'post', 'class' => 'nav-link']) ."
                </li>";
            }
            
            
        ?>
    </ul>
</nav>
<!-- /.navbar -->