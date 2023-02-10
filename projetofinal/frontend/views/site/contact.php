<?php

/** @var yii\web\View $this */
/** @var yii\bootstrap4\ActiveForm $form */
/** @var \frontend\models\ContactForm $model */

use yii\bootstrap4\Html;
use yii\bootstrap4\ActiveForm;
use yii\captcha\Captcha;

$this->title = 'Contactos';
?>

<style>
    body{margin-top:20px;
        background:#eee;
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
    }
    .single_advisor_profile .advisor_thumb {
        position: relative;
        z-index: 1;
        border-radius: 15px 15px 0 0;
        margin: 0 auto;
        padding: 30px 30px 0 30px;
        background-color: #3f43fd;
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
    .contactos{background-color: #cccccc}
</style>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />

<div class="container contactos">
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
