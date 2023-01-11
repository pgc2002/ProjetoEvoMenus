<?php

/** @var \yii\web\View $this */
/** @var string $content */

use common\widgets\Alert;
use frontend\assets\AppAsset;
use yii\bootstrap4\Breadcrumbs;
use yii\bootstrap4\Html;
use yii\bootstrap4\Nav;
use yii\bootstrap4\NavBar;
use common\models\User;

AppAsset::register($this);

$cookies = Yii::$app->request->cookies;
?>
<?php $this->beginPage() ?>
<!DOCTYPE html>
<html lang="<?= Yii::$app->language ?>" class="h-100">
<head>
    <meta charset="<?= Yii::$app->charset ?>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <?php $this->registerCsrfMetaTags() ?>
    <title><?= Html::encode($this->title) ?></title>
    <?php $this->head() ?>
    <script src="https://kit.fontawesome.com/7809ee6006.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body class="d-flex flex-column h-100">
<?php $this->beginBody() ?>

<header>
    <?php
    $menuItems = null;
    $user = null;
    NavBar::begin([
        'brandLabel' => "Evo Menus",
        'brandUrl' => Yii::$app->homeUrl,
        'options' => [
            'class' => 'navbar navbar-expand-md navbar-dark bg-dark fixed-top',
        ],
    ]);
    if(!Yii::$app->user->isGuest){
        $user = User::findOne(Yii::$app->user->identity->id);
        switch($user->tipo){
            case 'Funcionario':
                $menuItems = [
                    ['label' => 'Home', 'url' => Yii::$app->homeUrl],
                    ['label' => 'Restaurante', 'url' => ['/restaurante/view?id='.$cookies->getValue('idRestaurante')]],
                    ['label' => 'Ementa', 'url' => ['/categoria/index']],
                    ['label' => 'Mesa', 'url' => ['/mesa/index?id='.$cookies->getValue('idRestaurante')]],
                    ['label' => 'Pedido', 'url' => ['/pedido/index']],
                ];
                break;
            case 'Gestor':
                $menuItems = [
                    ['label' => 'Home', 'url' => Yii::$app->homeUrl],
                    ['label' => 'Restaurante', 'url' => ['/restaurante/view?id='.$cookies->getValue('idRestaurante')]],
                    ['label' => 'Ementa', 'url' => ['/categoria/index']],
                    ['label' => 'Mesa', 'url' => ['/mesa/index?id='.$cookies->getValue('idRestaurante')]],
                    ['label' => 'Pedido', 'url' => ['/pedido/index']],
                    ['label' => 'Funcionários', 'url' => ['/user/index']],
                ];
                break;
        }
    $menuItems[] = '<li>'
        . Html::beginForm(['/site/logout'], 'post', ['class' => 'form-inline'])
        . Html::submitButton(
            'Logout (' . Yii::$app->user->identity->username . ')',
            ['class' => 'btn btn-link logout']
        )
        . Html::endForm()
        . '</li>';
    }else{
        $menuItems = [['label' => 'Home', 'url' => Yii::$app->homeUrl],
        ['label' => 'Restaurantes', 'url' => ['/restaurante/index']],
        ['label' => 'Contactos', 'url' => ['/site/contact']],
        ['label' => 'Login', 'url' => ['/site/login']]];
    }

    
    echo Nav::widget([
        'options' => ['class' => 'navbar-nav ml-auto'],
        'items' => $menuItems,
    ]);
    NavBar::end();
    ?>
</header>

<main role="main" class="flex-shrink-0">
    <div class="container-fluid">
        <?= Breadcrumbs::widget([
            'links' => isset($this->params['breadcrumbs']) ? $this->params['breadcrumbs'] : [],
        ]) ?>
        <?= Alert::widget() ?>
        <?= $content ?>
    </div>
</main>

<footer class="footer mt-auto py-3 text-white" style="background-color: #383c44;">
    <div class="container-fluid">
        <p class="float-left">&copy; Evo Menus <?= date('Y') ?></p>
    </div>
</footer>

<?php $this->endBody() ?>
</body>
</html>
<?php $this->endPage();
