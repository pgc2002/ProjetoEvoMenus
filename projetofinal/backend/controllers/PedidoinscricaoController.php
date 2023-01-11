<?php

namespace backend\controllers;

use common\models\Horariofuncionamento;
use common\models\PedidoInscricao;
use backend\models\PedidoInscricaoSearch;
use common\models\Morada;
use common\models\Restaurante;
use Yii;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * PedidoinscricaoController implements the CRUD actions for PedidoInscricao model.
 */
class PedidoinscricaoController extends Controller
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
     * Lists all PedidoInscricao models.
     *
     * @return string
     */
    public function actionIndex()
    {
        if(Yii::$app->user->can('acessoBackend')) {
            $searchModel = new PedidoInscricaoSearch();
            $dataProvider = $searchModel->search($this->request->queryParams);

            return $this->render('index', [
                'searchModel' => $searchModel,
                'dataProvider' => $dataProvider,
            ]);
        }
    }

    public function actionAceitar($id)
    {
        if(Yii::$app->user->can('acessoBackend')) {
            $pedidoInscricao = $this->findModel($id);
            $restaurante = new Restaurante();
            $morada = new Morada();
            $horario = new Horariofuncionamento();

            $restaurante->nome = $pedidoInscricao->nome;
            $restaurante->email = $pedidoInscricao->email;
            $restaurante->telemovel = $pedidoInscricao->telemovel;

            $arrayMorada = explode("!%$#%&()", $pedidoInscricao->morada);
            $morada->pais = $arrayMorada[0];
            $morada->cidade = $arrayMorada[1];
            $morada->rua = $arrayMorada[2];
            $morada->codpost = $arrayMorada[3];
            $morada->save(false);

            $horario->segunda = "12:00-15:00-19:00-23:00";
            $horario->terca = "12:00-15:00-19:00-23:00";
            $horario->quarta = "12:00-15:00-19:00-23:00";
            $horario->quinta = "12:00-15:00-19:00-23:00";
            $horario->sexta = "12:00-15:00-19:00-23:00";
            $horario->sabado = "12:00-15:00-19:00-23:00";
            $horario->domingo = "12:00-15:00-19:00-23:00";
            $horario->save(false);

            $restaurante->idMorada = $morada->id;
            $restaurante->idHorario = $horario->id;
            $restaurante->save(false);

            $pedidoInscricao->delete();
            return $this->redirect('index');
        }
    }

    public function actionRecusar($id)
    {
        if(Yii::$app->user->can('acessoBackend')) {
            $pedidoInscricao = $this->findModel($id);
            $pedidoInscricao->delete();

            return $this->redirect('index');
        }
    }

    /**
     * Displays a single PedidoInscricao model.
     * @param int $id ID
     * @return string
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionView($id)
    {
        if(Yii::$app->user->can('acessoBackend')) {
            return $this->render('view', [
                'model' => $this->findModel($id),
            ]);
        }
    }

    /**
     * Creates a new PedidoInscricao model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return string|\yii\web\Response
     */
    public function actionCreate()
    {
        if(Yii::$app->user->can('acessoBackend')) {
            $model = new PedidoInscricao();

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
    }

    /**
     * Updates an existing PedidoInscricao model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param int $id ID
     * @return string|\yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id)
    {
        if(Yii::$app->user->can('acessoBackend')) {
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
     * Deletes an existing PedidoInscricao model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param int $id ID
     * @return \yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionDelete($id)
    {
        if(Yii::$app->user->can('acessoBackend')) {
            $this->findModel($id)->delete();

            return $this->redirect(['index']);
        }
    }

    /**
     * Finds the PedidoInscricao model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id ID
     * @return PedidoInscricao the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = PedidoInscricao::findOne(['id' => $id])) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
