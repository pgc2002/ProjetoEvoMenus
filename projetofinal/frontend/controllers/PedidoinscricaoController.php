<?php

namespace frontend\controllers;

use common\models\Pedidoinscricao;
use common\models\Restaurante;
use app\models\PedidoinscricaoSearch;
use common\widgets\Alert;
use Yii;
use yii\helpers\Html;
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
        $searchModel = new PedidoinscricaoSearch();
        $dataProvider = $searchModel->search($this->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single PedidoInscricao model.
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
     * Creates a new PedidoInscricao model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return string|\yii\web\Response
     */
    public function actionCreate()
    {
        $model = new Pedidoinscricao();

        try {
            if ($this->request->isPost) {
                if ($model->load($this->request->post())) {
                    if(Restaurante::find()->where(['nome' => $model->nome])->one()){
                        Yii::$app->session->setFlash('error', 'Já existe um restaurante com este nome!!');
                        return $this->refresh();
                    }

                    $pais = mb_convert_case(Yii::$app->request->post('pais'), MB_CASE_TITLE, "UTF-8");
                    $cidade = Yii::$app->request->post('cidade');
                    $codpost = Yii::$app->request->post('codpost');
                    $rua = Yii::$app->request->post('rua');
                    $morada = $pais . '!%$#%&()' . $cidade . '!%$#%&()' . $rua . '!%$#%&()' . $codpost;
                    $model->morada = $morada;

                    if ($model->save()) {
                        Yii::$app->session->setFlash('success', 'Inscreveu-se com sucesso.');
                        return $this->goHome();
                    } else {
                        Yii::$app->session->setFlash('error', 'Ocorreu um erro ao se inscrever.');
                    }
                }
            } else {
                $model->loadDefaultValues();
            }
            return $this->render('create', [
                'model' => $model,
            ]);
        }catch (\yii\db\Exception $e){
            Yii::$app->session->setFlash('error', 'Já existe um restaurante com este nome!!');
            return $this->refresh();
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
        $model = $this->findModel($id);

        if ($this->request->isPost && $model->load($this->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        }

        return $this->render('update', [
            'model' => $model,
        ]);
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
        $this->findModel($id)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the PedidoInscricao model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id ID
     * @return Pedidoinscricao the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Pedidoinscricao::findOne(['id' => $id])) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
