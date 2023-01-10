<?php
use yii\helpers\Html;
/** @var yii\web\View $this */
$this->title = 'Evo Menus';
?>
<style type="text/css">
    .d-flex{
        background-image: url('../web/resources/image-from-rawpixel-id-3283405-jpeg.jpg');
        background-size:cover;
        height:auto;
    }
    .body-content {
        text-align: center;
        margin: auto;
        padding:10px;
    }
    .explanation-content{
        text-align: center;
        padding: 1px;
        background-color:darkorange;
        margin: 20px;
        border-radius:25px;
    }
    .btn-outline-secondary{
        background-color: darkorange;
        color:black;

    }

</style>
<div class="site-index">
    
    <div class="div-1" >
            <div class="jumbotron text-center bg-transparent" >
                    <h1 class="explanation-content">Bem Vindo!!<p>Queres inscrever o teu restaurante na nossa app??</p> </h1>
            </div>
        <div class="body-content">
            <div class="center">
                    <div>
                        <h1><?=Html::a('Inscreve-te aqui!', ['pedidoinscricao/create'], ['data-method' => 'post', 'class' => 'btn-outline-secondary'])?></h1>
                    </div>
            </div>
        </div>

        <div class="explanation-content">
            <div class="container">
                <div>
                    <h1 >Sobre nós...</h1>
                    <h>O nosso sistema foca-se na gestão de diversos restaurantes.
                        Facilitamos a publicitação do seu negocio, fornecendo aos nossos utilizadores os dados relevantes do seu restaurante, bem como uma forma simples e eficaz de interagir com eles, desde a realização de reservas até a pedidos nas mesas ou ao domicilio.
                        Assim que for aceite a sua candidatura a gestão do restaurante na aplicação ficara a cargo do gestor de restaurante.
                    </h>
                </div>
            </div>
        </div>
    </div>
</div>
