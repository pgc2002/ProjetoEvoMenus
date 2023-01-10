<?php
use yii\helpers\Html;
/** @var yii\web\View $this */
$this->title = 'Evo Menus';
?>
<style type="text/css">
    .div-1 {
        background-image: url('../web/resources/backgroundindex2.jpg');
        background-size:initial;
        height:550px;
    }
    .body-content {
        text-align: center;
        margin: auto;
        padding:10px;
    }
    .explanation-content{
        text-align: center;
        padding: 1px;
        background-color:white;
        margin: 20px;
        border-radius:25px;
    }
    .btn-outline-secondary{
        background-color: steelblue;
        color:black;
    }
</style>
<div class="site-index">
    
    <div class="div-1" >
            <div class="jumbotron text-center bg-transparent" >
                <h1>Bem Vindo!!</h1>

            </div>
            

        <div class="body-content">
            <div class="center">
                    <div>
                        <h2>Queres inscrever o teu restaurante na nossa app??</h2>
                        <p><?=Html::a('Inscreve-te aqui!', ['pedidoinscricao/create'], ['data-method' => 'post', 'class' => 'btn btn-primary'])?></p>
                    </div>
            </div>
        </div>

        <div class="explanation-content">
            <div class="container">
                <div>
                    <h1>Sobre nós...</h1>
                    <h>O nosso sistema foca-se na gestão de diversos restaurantes.
                        Facilitamos a publicitação do seu negocio, fornecendo aos nossos utilizadores os dados relevantes do seu restaurante, bem como uma forma simples e eficaz de interagir com eles, desde a realização de reservas até a pedidos nas mesas ou ao domicilio.
                        Assim que for aceite a sua candidatura a gestão do restaurante na aplicação ficara a cargo do gestor de restaurante.
                    </h>
                </div>
            </div>
        </div>
    </div>
</div>
