<?php

namespace frontend\controllers;

use common\models\Mesa;
use common\models\Restaurante;
use backend\models\MesaSearch;
use Yii;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * MesaController implements the CRUD actions for Mesa model.
 */
class MesaController extends Controller
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
     * Lists all Mesa models.
     *
     * @return string
     */
    public function actionIndex()
    {
        if(Yii::$app->user->can('visualizarMesas') || Yii::$app->user->can('crudMesas')) {
            $searchModel = new MesaSearch();
            $dataProvider = $searchModel->search($this->request->queryParams);

            return $this->render('index', [
                'searchModel' => $searchModel,
                'dataProvider' => $dataProvider,
            ]);
        }
    }

    /**
     * Displays a single Mesa model.
     * @param int $id ID
     * @return string
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionView($id)
    {
        if(Yii::$app->user->can('visualizarMesas') || Yii::$app->user->can('crudMesas')) {
            return $this->render('view', [
                'model' => $this->findModel($id),
            ]);
        }
    }

    /**
     * Creates a new Mesa model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return string|\yii\web\Response
     */
    public function actionCreate()
    {
        if(Yii::$app->user->can(Yii::$app->user->can('crudMesas'))) {
        $model = new Mesa();
        $restaurante = new Restaurante();

        if ($this->request->isPost) {
            if ($model->load($this->request->post())) {
                $restaurante = Restaurante::findOne($model->idRestaurante);
                $model->estado = "Vazia";
                $model->numero = $restaurante->getNumeroMesas() + 1;
                $model->save();
                $restaurante->lotacaoMaxima += $model->capacidade;
                $restaurante->save(false);
                return $this->redirect(['restaurante/view', 'id' => $model->idRestaurante]);
            }
        } else {
            $model->loadDefaultValues();
        }

        return $this->render('create', [
            'model' => $model,
        ]);

        }
    }

    /**
     * Updates an existing Mesa model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param int $id ID
     * @return string|\yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id)
    {
        if(Yii::$app->user->can(Yii::$app->user->can('crudMesas'))) {
            $model = $this->findModel($id);

            if ($this->request->isPost && $model->load($this->request->post()) && $model->save()) {
                return $this->redirect(['view', 'id' => $model->id]);
            }

            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing Mesa model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param int $id ID
     * @return \yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionDelete($id)
    {
        if(Yii::$app->user->can(Yii::$app->user->can('crudMesas'))) {
            $idRestaurante = $this->findModel($id)->idRestaurante;
            $restaurante = Restaurante::findOne($idRestaurante);
            $mesa = Mesa::findOne($id);
            $restaurante->lotacaoMaxima -= $mesa->capacidade;
            $restaurante->save(false);
            $this->findModel($id)->delete();

            if (isset($_GET['idRestaurante'])) {
                return $this->redirect(['index', 'idRestaurante' => $idRestaurante]);
            } else {
                return $this->redirect(['index']);
            }
        }
    }

    /**
     * Finds the Mesa model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id ID
     * @return Mesa the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Mesa::findOne(['id' => $id])) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
