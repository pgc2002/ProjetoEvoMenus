<?php

use common\models\Horariofuncionamento;
use common\models\Mesa;
use yii\data\ActiveDataProvider;
use yii\data\ArrayDataProvider;
use yii\grid\GridView;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\widgets\DetailView;
use yii\grid\ActionColumn;
use common\models\Morada;
use common\models\User;

/** @var yii\web\View $this */
/** @var common\models\Restaurante $model */

$this->title = $model->nome;
\yii\web\YiiAsset::register($this);
?>
<div class="restaurante-view" id="siteBody">

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
            'pagination' => false,
        ]);

        $horario = HorarioFuncionamento::findOne($model->idHorario);

        $horario = $horario->getHorario();

        //print_r($horario);
        foreach($horario as $key => $value){
            if(in_array( "Folga", $horario[$key], true )){
                $dias[$key][0] = "Folga";
                $dias[$key][1] = "Folga";
            }else{
                $dias[$key][0] = $horario[$key][0]."-".$horario[$key][1];
                $dias[$key][1] = $horario[$key][2]."-".$horario[$key][3];
            }
        }

        $data = [
            [
                'refeicao' => 'Almoço',
                'segunda' => $dias['segunda'][0],
                'terca' => $dias['terca'][0],
                'quarta' => $dias['quarta'][0],
                'quinta' => $dias['quinta'][0],
                'sexta' => $dias['sexta'][0],
                'sabado' => $dias['sabado'][0],
                'domingo' => $dias['domingo'][0],
            ],
            [
                'refeicao' => 'Jantar',
                'segunda' => $dias['segunda'][1],
                'terca' => $dias['terca'][1],
                'quarta' => $dias['quarta'][1],
                'quinta' => $dias['quinta'][1],
                'sexta' => $dias['sexta'][1],
                'sabado' => $dias['sabado'][1],
                'domingo' => $dias['domingo'][1],
            ]
        ];

        $dataProvider = new ArrayDataProvider([
            'allModels' => $data,
        ]);

        /*$dataProvider = new \yii\data\SqlDataProvider([
            'sql' => 'SELECT segunda, terca, quarta, quinta, sexta, sabado, domingo FROM horario_funcionamento WHERE id=:id',
            'params' => [':id' => $model->idHorario],
        ]);*/
        echo "<br><br>";
        echo '<h3>Horário de funcionamento</h3>';
        echo GridView::widget([
            'dataProvider' => $dataProvider,
            'layout' => '{items}{pager}',
            'columns' => [
                [
                    'attribute' => 'refeicao',
                    'label' => '',
                ],
                'segunda',
                'terca',
                'quarta',
                'quinta',
                'sexta',
                'sabado',
                'domingo',
            ],
        ]);
        $user = User::findOne(Yii::$app->user->identity->id);
        if(Yii::$app->user->can('Gestor'))
            echo Html::a(
                'Editar horário',
                Url::to(['..\horariofuncionamento\update', 'id' => $model->idHorario]),
                [
                    'id'=>'grid-custom-button',
                    'data-pjax'=>true,
                    'class'=>'button btn btn-info',
                ]
            );
    ?>

</div>
