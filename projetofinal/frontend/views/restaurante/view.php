<?php

use yii\helpers\Html;
use yii\widgets\DetailView;
use common\models\Morada;

/** @var yii\web\View $this */
/** @var common\models\Restaurante $model */

$this->title = $model->nome;
\yii\web\YiiAsset::register($this);
?>
<div class="restaurante-view">

    <?php

        echo DetailView::widget([
            'model' => $model,
            'attributes' => [
                'nome',
                'lotacaoMaxima',
                'email',
                'telemovel',
                'moradaFormatada'
            ],
        ]);
    ?>

</div>
