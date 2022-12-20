<?php

namespace backend\controllers;

use yii;
use common\models\Horariofuncionamento;
use yii\data\ActiveDataProvider;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;


/**
 * HorariofuncionamentoController implements the CRUD actions for HorarioFuncionamento model.
 */
class HorariofuncionamentoController extends Controller
{
    /**
     * @inheritDoc
     */
    public function behaviors()
    {
        return array_merge(
            parent::behaviors(),
            [
                'verbs' => [
                    'class' => VerbFilter::className(),
                    'actions' => [
                        'delete' => ['POST'],
                    ],
                ],
            ]
        );
    }

    /**
     * Lists all HorarioFuncionamento models.
     *
     * @return string
     */
    public function actionIndex()
    {
        $dataProvider = new ActiveDataProvider([
            'query' => Horariofuncionamento::find(),
            /*
            'pagination' => [
                'pageSize' => 50
            ],
            'sort' => [
                'defaultOrder' => [
                    'id' => SORT_DESC,
                ]
            ],
            */
        ]);

        return $this->render('index', [
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single HorarioFuncionamento model.
     * @param int $id ID
     * @return string
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionView($id)
    {
        return $this->render('view', [
            'model' => $this->findModel($id),
        ]);
    }

    /**
     * Creates a new HorarioFuncionamento model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return string|\yii\web\Response
     */
    public function actionCreate()
    {
        $model = new Horariofuncionamento();

        if ($this->request->isPost) {
            if ($model->load($this->request->post()) && $model->save()) {
                return $this->redirect(['view', 'id' => $model->id]);
            }
        } else {
            $model->loadDefaultValues();
        }

        return $this->render('create', [
            'model' => $model,
        ]);
    }

    /**
     * Updates an existing HorarioFuncionamento model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param int $id ID
     * @return string|\yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id)
    {
        $model = $this->findModel($id);

        if ($this->request->isPost && $model->load($this->request->post())) {

            if (!Yii::$app->request->post('segunda_folga'))
                $model->segunda = Yii::$app->request->post('segunda_almoco_inicio'). '-' .Yii::$app->request->post('segunda_almoco_fim').
                '-' .Yii::$app->request->post('segunda_jantar_inicio'). '-' .Yii::$app->request->post('segunda_jantar_fim');
            else
                $model->segunda = 'Folga';

            if (!Yii::$app->request->post('terca_folga'))
            $model->terca = Yii::$app->request->post('terca_almoco_inicio'). '-' .Yii::$app->request->post('terca_almoco_fim').
                '-' .Yii::$app->request->post('terca_jantar_inicio'). '-' .Yii::$app->request->post('terca_jantar_fim');
            else
                $model->terca = 'Folga';

            if (!Yii::$app->request->post('quarta_folga'))
            $model->quarta = Yii::$app->request->post('quarta_almoco_inicio'). '-' .Yii::$app->request->post('quarta_almoco_fim').
                '-' .Yii::$app->request->post('quarta_jantar_inicio'). '-' .Yii::$app->request->post('quarta_jantar_fim');
            else
                $model->quarta = 'Folga';

            if (!Yii::$app->request->post('quinta_folga'))
            $model->quinta = Yii::$app->request->post('quinta_almoco_inicio'). '-' .Yii::$app->request->post('quinta_almoco_fim').
                '-' .Yii::$app->request->post('quinta_jantar_inicio'). '-' .Yii::$app->request->post('quinta_jantar_fim');
            else
                $model->quinta = 'Folga';

            if (!Yii::$app->request->post('sexta_folga'))
            $model->sexta = Yii::$app->request->post('sexta_almoco_inicio'). '-' .Yii::$app->request->post('sexta_almoco_fim').
                '-' .Yii::$app->request->post('sexta_jantar_inicio'). '-' .Yii::$app->request->post('sexta_jantar_fim');
            else
                $model->sexta = 'Folga';

            if (!Yii::$app->request->post('sabado_folga'))
            $model->sabado = Yii::$app->request->post('sabado_almoco_inicio'). '-' .Yii::$app->request->post('sabado_almoco_fim').
                '-' .Yii::$app->request->post('sabado_jantar_inicio'). '-' .Yii::$app->request->post('sabado_jantar_fim');
            else
                $model->sabado = 'Folga';

            if (!Yii::$app->request->post('domingo_folga'))
            $model->domingo = Yii::$app->request->post('domingo_almoco_inicio'). '-' .Yii::$app->request->post('domingo_almoco_fim').
                '-' .Yii::$app->request->post('domingo_jantar_inicio'). '-' .Yii::$app->request->post('domingo_jantar_fim');
            else
                $model->domingo = 'Folga';

            $model->save(false);

            return $this->redirect(['view', 'id' => $model->id]);
        }

        return $this->render('update', [
            'model' => $model,
        ]);
    }

    /**
     * Deletes an existing HorarioFuncionamento model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param int $id ID
     * @return \yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionDelete($id)
    {
        $this->findModel($id)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the HorarioFuncionamento model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id ID
     * @return Horariofuncionamento the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Horariofuncionamento::findOne(['id' => $id])) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
