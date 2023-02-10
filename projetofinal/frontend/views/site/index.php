<?php
use yii\helpers\Html;
use common\models\Restaurante;
use yii\helpers\Url;
/** @var yii\web\View $this */
$this->title = 'Evo Menus';
$ContagemRestaurantes = Restaurante::find()->count();
?>

<style type="text/css">
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
    .header-eight {
        position: relative;
        padding:160px 0 100px 0;
        background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 0%, rgba(0,212,255,1) 100%);
    }
  
  
  .header-eight .header-image img {
        width: 100%;
        border-radius: 8px;
    }
    .header-eight .header-content h1 {
        font-weight: 700;
        color: var(--white);
        text-shadow: 0px 3px 8px #00000017;
        text-transform: capitalize;
    }
    .header-eight .header-content p {
        margin-top: 30px;
        color: var(--white);
        opacity: 0.7;
    }
    .header-eight .button {
        margin-top: 40px;
    }
    .header-eight .primary-btn {
        margin-right: 12px;
        background-color: var(--white);
        color: var(--primary);
        border: 1px solid transparent;
    }
    .header-eight .primary-btn:hover {
        background-color: transparent;
        color: var(--white);
        border-color: var(--white);
    }
    .header-eight .video-button {
        display: inline-flex;
        align-items: center;
    }
    @media (max-width: 767px) {
        .header-eight .video-button {
        margin-top: 20px;
        }
    }
    .header-eight .video-button .text {
        display: inline-block;
        margin-left: 15px;
        color: var(--white);
        font-weight: 600;
    }
    .header-eight .video-button .icon-btn {
        background: var(--white);
        color: var(--primary);
    }
    
    .about-five {
  background-color: var(--light-3);
  padding-top: 120px;
  padding-bottom: 90px;
  }
  .about-five-content{
    padding-left: 50px;
  }
  .about-five-content .small-title {
    position: relative;
    padding-left: 30px;
  }
  .about-five-content .small-title::before {
    position: absolute;
    content: "";
    left: 0;
    top: 50%;
    background-color: var(--primary);
    height: 2px;
    width: 20px;
    margin-top: -1px;
  }
  .about-five-content .main-title {
    margin-top: 20px;
  }
  .about-five-content .about-five-tab {
    margin-top: 40px;
  }
  .about-five-content .about-five-tab nav {
    border: none;
    background-color: var(--light-1);
    padding: 15px;
    border-radius: 5px;
  }
  .about-five-content .about-five-tab nav .nav-tabs {
    border: none;
  }
  .about-five-content .about-five-tab nav button {
    border: none;
    color: var(--dark-1);
    font-weight: 600;
    padding: 0;
    margin-right: 20px;
    position: relative;
    background-color: var(--white);
    padding: 10px 18px;
    border-radius: 4px;
    text-transform: capitalize;
  }
  @media (max-width: 767px) {
    .about-five-content .about-five-tab nav button {
      margin: 0;
      margin-bottom: 10px;
      width: 100%;
    }
    .about-five-content .about-five-tab nav button:last-child {
      margin: 0;
    }
  }
  .about-five-content .about-five-tab nav button:last-child {
    margin-right: 0;
  }
  .about-five-content .about-five-tab .tab-content {
    border: none;
    padding-top: 30px;
  }
  .about-five-content .about-five-tab .tab-content p {
    margin-bottom: 20px;
  }
  .about-five-content .about-five-tab .tab-content p:last-child {
    margin: 0;
  }

  .about-image-five {
    padding-left: 60px;
    
    position: relative;
    z-index: 2;
  }
  @media only screen and (min-width: 768px) and (max-width: 991px) {
    .about-image-five {
      margin-bottom: 70px;
      padding-left: 30px;
    }
  }
  @media (max-width: 767px) {
    .about-image-five {
      margin-bottom: 60px;
      padding-left: 0;
      
    }
  }
  .about-image-five .shape {
    position: absolute;
    left: 30px;
    top: -30px;
    z-index: -1;
  }
  @media only screen and (min-width: 768px) and (max-width: 991px) {
    .about-image-five .shape {
      left: 0;
    }
  }
  @media only screen and (min-width: 768px) and (max-width: 991px) {
    .about-image-five::before {
      right: -15px;
      bottom: -15px;
    }
  }
  @media (max-width: 767px) {
    .about-image-five::before {
      display: none;
    }
  }
  .about-image-five img {
    width: 100%;
    z-index: 2;
    border-radius: 8px;
  }

  .single_advisor_profile {
      position: relative;
      margin-bottom: 50px;
      -webkit-transition-duration: 500ms;
      transition-duration: 500ms;
      z-index: 1;
      border-radius: 15px;
      -webkit-box-shadow: 0 0.25rem 1rem 0 rgba(47, 91, 234, 0.125);
      box-shadow: 0 0.25rem 1rem 0 rgba(47, 91, 234, 0.125);
      padding: 5px
  }

  .single_advisor_profile .advisor_thumb {
      position: relative;
      z-index: 1;
      border-radius: 15px 15px 0 0;
      margin: 0 auto;
      padding: 30px 30px 0 30px;
      background: #3131a3;
      overflow: hidden;
  }

  .single_advisor_profile .advisor_thumb::after {
      -webkit-transition-duration: 500ms;
      transition-duration: 500ms;
      position: absolute;
      width: 150%;
      height: 80px;
      bottom: -45px;
      left: -25%;
      content: "";
      background-color: #ffffff;
      -webkit-transform: rotate(-15deg);
      transform: rotate(-15deg);
  }

  @media only screen and (max-width: 575px) {
      .single_advisor_profile .advisor_thumb::after {
          height: 160px;
          bottom: -90px;
      }
  }

  .single_advisor_profile .advisor_thumb .social-info {
      position: absolute;
      z-index: 1;
      width: 100%;
      bottom: 0;
      right: 30px;
      text-align: right;
  }

  .single_advisor_profile .advisor_thumb .social-info a {
      font-size: 14px;
      color: #020710;
      padding: 0 5px;
  }

  .single_advisor_profile .advisor_thumb .social-info a:hover,
  .single_advisor_profile .advisor_thumb .social-info a:focus {
      color: #3f43fd;
  }

  .single_advisor_profile .advisor_thumb .social-info a:last-child {
      padding-right: 0;
  }

  .single_advisor_profile .single_advisor_details_info {
      position: relative;
      z-index: 1;
      padding: 30px;
      text-align: right;
      -webkit-transition-duration: 500ms;
      transition-duration: 500ms;
      border-radius: 0 0 15px 15px;
      background-color: #ffffff;
  }

  .single_advisor_profile .single_advisor_details_info::after {
      -webkit-transition-duration: 500ms;
      transition-duration: 500ms;
      position: absolute;
      z-index: 1;
      width: 50px;
      height: 3px;
      background-color: #3f43fd;
      content: "";
      top: 12px;
      right: 30px;
  }

  .single_advisor_profile .single_advisor_details_info h6 {
      margin-bottom: 0.25rem;
      -webkit-transition-duration: 500ms;
      transition-duration: 500ms;
  }
  @media only screen and (min-width: 768px) and (max-width: 991px) {
      .single_advisor_profile .single_advisor_details_info h6 {
          font-size: 14px;
      }
  }
  .single_advisor_profile .single_advisor_details_info p {
      -webkit-transition-duration: 500ms;
      transition-duration: 500ms;
      margin-bottom: 0;
      font-size: 14px;
  }
  @media only screen and (min-width: 768px) and (max-width: 991px) {
      .single_advisor_profile .single_advisor_details_info p {
          font-size: 12px;
      }
  }
  .single_advisor_profile:hover .advisor_thumb::after,
  .single_advisor_profile:focus .advisor_thumb::after {
      background-color: #070a57;
  }
  .single_advisor_profile:hover .advisor_thumb .social-info a,
  .single_advisor_profile:focus .advisor_thumb .social-info a {
      color: #ffffff;
  }
  .single_advisor_profile:hover .advisor_thumb .social-info a:hover,
  .single_advisor_profile:hover .advisor_thumb .social-info a:focus,
  .single_advisor_profile:focus .advisor_thumb .social-info a:hover,
  .single_advisor_profile:focus .advisor_thumb .social-info a:focus {
      color: #ffffff;
  }
  .single_advisor_profile:hover .single_advisor_details_info,
  .single_advisor_profile:focus .single_advisor_details_info {
      background-color: #070a57;
  }
  .single_advisor_profile:hover .single_advisor_details_info::after,
  .single_advisor_profile:focus .single_advisor_details_info::after {
      background-color: #ffffff;
  }
  .single_advisor_profile:hover .single_advisor_details_info h6,
  .single_advisor_profile:focus .single_advisor_details_info h6 {
      color: #ffffff;
  }
  .single_advisor_profile:hover .single_advisor_details_info p,
  .single_advisor_profile:focus .single_advisor_details_info p {
      color: #ffffff;
  }
  .utilizadores{text-align: center}
</style>

<!-- Start header Area -->
<section id="hero-area" class="header-area header-eight">
    <div class="container">
        <div class="row align-items-center">
        <div class="col-lg-6 col-md-12 col-12">
            <div class="header-content">
            <h1>Bem Vindo!!</h1>
            <p>
                Queres inscrever o teu restaurante na nossa app??
            </p>
            <div class="button">
                <a href=<?=Url::toRoute(['pedidoinscricao/create', 'id' => 42])?> class="btn primary-btn">Inscreve-te aqui!</a>
            </div>
            </div>
        </div>
        <div class="col-lg-6 col-md-12 col-12">
            <div class="header-image">
            <img src="../web/resources/pexels-photo-9961851.jpeg" alt="#" />
            </div>
        </div>
        </div>
    </div>
</section>
<!-- End header Area -->
<section class="about-area about-five">
    <div class="container">
<div class="row align-items-center">
    <div class="col-lg-6 col-12">
        <div class="about-image-five">
            <img src="../web/resources/about-img1.jpg" alt="about" />
        </div>
    </div>
    <div class="col-lg-6 col-12">
        <div class="about-five-content">
            <h6 class="small-title text-lg">SOBRE NÓS...</h6>
            <h2 class="main-title fw-bold">O nosso sistema foca-se na gestão de diversos restaurantes</h2>
            <div class="about-five-tab">
                
                <div class="tab-pane fade show active" id="nav-who" role="tabpanel" aria-labelledby="nav-who-tab">
                    <p>Facilitamos a publicitação do seu negocio, fornecendo aos nossos utilizadores os dados relevantes do seu restaurante, bem como uma forma simples e eficaz de interagir com eles para a realização de pedidos nas mesas.</p>
                    <p>Assim que for aceite a sua candidatura a gestão do restaurante na aplicação ficara a cargo do gestor de restaurante.</p>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- container -->
</section>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-sm-8 col-lg-6">
            <!-- Section Heading-->
            <div class="section_heading text-center wow fadeInUp" data-wow-delay="0.2s" style="visibility: visible; animation-delay: 0.2s; animation-name: fadeInUp;">
                <h3>A nossa equipa de desenvolvimento</h3>
                <p>Prontos para resolver problemas até as 9 da manha</p>
                <div class="line"></div>
            </div>
        </div>
        <div class="row">
        <!-- Single Advisor-->
        <div class="utilizadores">
            <div class="single_advisor_profile wow fadeInUp" data-wow-delay="0.2s" style="visibility: visible; animation-delay: 0.2s; animation-name: fadeInUp;">
                <!-- Team Thumb-->
                <div class="advisor_thumb"><img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt=""></div>
                <!-- Team Details-->
                <div class="single_advisor_details_info">
                    <h6>André Afoito</h6>
                    <p class="designation">2211891@my.ipleiria.pt</p>
                </div>
            </div>
        </div>
        <!-- Single Advisor-->
        <div class="utilizadores">
            <div class="single_advisor_profile wow fadeInUp" data-wow-delay="0.3s" style="visibility: visible; animation-delay: 0.3s; animation-name: fadeInUp;">
                <!-- Team Thumb-->
                <div class="advisor_thumb"><img src="https://bootdey.com/img/Content/avatar/avatar6.png" alt=""></div>
                <!-- Team Details-->
                <div class="single_advisor_details_info">
                    <h6>Pedro Cavalheiro</h6>
                    <p class="designation">2211896@my.ipleiria.pt</p>
                </div>
            </div>
        </div>
        <!-- Single Advisor-->
        <div class="utilizadores">
            <div class="single_advisor_profile wow fadeInUp" data-wow-delay="0.4s" style="visibility: visible; animation-delay: 0.4s; animation-name: fadeInUp;">
                <!-- Team Thumb-->
                <div class="advisor_thumb"><img src="https://bootdey.com/img/Content/avatar/avatar5.png" alt=""></div>
                <!-- Team Details-->
                <div class="single_advisor_details_info">
                    <h6>Joel Mateus</h6>
                    <p class="designation">2211928@my.ipleiria.pt</p>
                </div>
            </div>
        </div>
    </div>
</div>