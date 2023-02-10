<?php

use yii\helpers\Html;
use yii\helpers\Url;

/** @var yii\web\View $this */
/** @var common\models\Categoria $model */

$this->title = 'Create Categoria';
$this->params['breadcrumbs'][] = ['label' => 'Categorias', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="categoria-create">

    <?php

    echo Html::a(
        'Cancelar',
        Url::to(['..\categoria\index', 'id' => Yii::$app->request->get('id')]),
        [
            'id' => 'grid-custom-button',
            'data-pjax' => true,
            'class' => 'btn btn-secondary',
        ]
    );
    ?>

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
