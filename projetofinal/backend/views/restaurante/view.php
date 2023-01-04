<?php

use common\models\HorarioFuncionamento;
use yii\data\ActiveDataProvider;
use yii\grid\GridView;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\widgets\DetailView;
use yii\grid\ActionColumn;
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
                'moradaFormatada',
                //'diasSemana',
            ],
        ]);

        $dataProvider = new \yii\data\SqlDataProvider([
            'sql' => 'SELECT segunda, terca, quarta, quinta, sexta, sabado, domingo FROM horario_funcionamento WHERE id=:id',
            'params' => [':id' => $model->idHorario],
        ]);

        echo '<h3>Horário de funcionamento</h3>';
        echo GridView::widget([
            'dataProvider' => $dataProvider,
            'layout' => '{items}{pager}',
            'columns' => [
                'segunda',
                'terca',
                'quarta',
                'quinta',
                'sexta',
                'sabado',
                'domingo',
            ],
        ]);

        echo Html::a(
            'Editar horário',
            Url::to(['..\horariofuncionamento\update', 'id' => $model->idHorario]),
            [
                'id'=>'grid-custom-button',
                'data-pjax'=>true,
                'class'=>'button btn btn-info',
            ]
        );
        
        echo '<br><br>';

        echo Html::a(
            'Visualizar ementas',
            Url::to(['..\categoria\index', 'id' => $model->id]),
            [
                'id'=>'grid-custom-button',
                'data-pjax'=>true,
                'class'=>'button btn btn-info',
            ]
        );
    ?>

</div>
