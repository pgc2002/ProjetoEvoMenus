<?php

namespace frontend\controllers;

use common\models\Categoria;
use common\models\Item;
use backend\models\ItemSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use Yii;
use yii\web\UploadedFile;

/**
 * ItemController implements the CRUD actions for Item model.
 */
class ItemController extends Controller
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
     * Lists all Item models.
     *
     * @return string
     */
    public function actionIndex()
    {
        if(Yii::$app->user->can('crudMenus') || Yii::$app->user->can('visualizarMenus')) {
            $searchModel = new ItemSearch();
            $dataProvider = $searchModel->search($this->request->queryParams);

            return $this->render('index', [
                'searchModel' => $searchModel,
                'dataProvider' => $dataProvider,
            ]);
        }
    }

    /**
     * Displays a single Item model.
     * @param int $id ID
     * @return string
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionView($id)
    {
        if(Yii::$app->user->can(Yii::$app->user->can('crudMenus')) || Yii::$app->user->can(Yii::$app->user->can('visualizarMenus'))) {
            return $this->render('view', [
                'model' => $this->findModel($id),
            ]);
        }
    }

    /**
     * Creates a new Item model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return string|\yii\web\Response
     */
    public function actionCreate()
    {
        if(Yii::$app->user->can('crudMenus')) {
            $model = new Item();

            if ($this->request->isPost) {
                if ($model->load($this->request->post())) {
                    $image = UploadedFile::getInstance($model, 'fotografia');
                    $imgName = 'img_' . $model->nome . '_Item_.' . $image->getExtension();
                    $image->saveAs(Yii::getAlias('@fotografiaPath') . '/' . $imgName);
                    $model->fotografia = $imgName;
                    $model->save();
                    //return $this->redirect(['..\categoria\index', 'id' => Yii::$app->request->get('idRestaurante')]);
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
     * Updates an existing Item model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param int $id ID
     * @return string|\yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id)
    {
        if(Yii::$app->user->can('crudMenus')) {
            $model = $this->findModel($id);

            if ($this->request->isPost && $model->load($this->request->post())) {
                $image = UploadedFile::getInstance($model, 'fotografia');
                $imgName = 'img_' . $model->nome . '_Item_.' . $image->getExtension();
                $image->saveAs(Yii::getAlias('@fotografiaPath') . '/' . $imgName);
                $model->fotografia = $imgName;
                $model->save();
                $categoria = Categoria::find()->where(['id' => $model->idCategoria])->one();
                return $this->redirect(['..\categoria\index', 'id' => $categoria->idRestaurante, 'idCategoria' => $model->idCategoria]);
            }

            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing Item model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param int $id ID
     * @return \yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionDelete($id)
    {
        if(Yii::$app->user->can(Yii::$app->user->can('crudMenus'))) {
            $this->findModel($id)->delete();

            return $this->redirect(['index']);
        }
    }

    /**
     * Finds the Item model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id ID
     * @return Item the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Item::findOne(['id' => $id])) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }

    public function actionLists($id)
    {
        $countItems = Item::find()
            ->where(['idCategoria' => $id])
            ->count();

        $items = Item::find()
            ->where(['idCategoria' => $id])
            ->all();

        if ($countItems > 0){
            foreach ($items as $item)
                echo "<option value='".$item->id."'>".$item->nome." | ".$item->preco."€</option>";
        }
        else
            echo "<option></option>";

    }

    /*public function actionUpload($id)
    {
        $model = $this->findModel($id);

        if (Yii::$app->request->isPost) {
            $model->ImagemPrato = UploadedFile::getInstance($model, 'ImagemPrato');
            if ($model->upload()) {
                return $this->redirect(['view', 'id' => $model->id]);

            }
        }

        return $this->render('upload', ['model' => $model]);
    }*/
}
