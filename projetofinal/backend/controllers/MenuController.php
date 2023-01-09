<?php

namespace backend\controllers;

use common\models\Categoria;
use common\models\Item;
use common\models\Menu;
use backend\models\MenuSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\web\UploadedFile;
use yii;

/**
 * MenuController implements the CRUD actions for Menu model.
 */
class MenuController extends Controller
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
     * Lists all Menu models.
     *
     * @return string
     */
    public function actionIndex()
    {
        $searchModel = new MenuSearch();
        $dataProvider = $searchModel->search($this->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single Menu model.
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
     * Creates a new Menu model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return string|\yii\web\Response
     */
    public function actionCreate()
    {
        $model = new Menu();

        if ($this->request->isPost) {
            if ($model->load($this->request->post()))
            {
                $image = UploadedFile::getInstance($model, 'fotografia');
                $imgName = 'img_'.$model->nome.'_Menu.'.$image->getExtension();
                $image->saveAs(Yii::getAlias('@fotografiaPath').'/'. $imgName);
                $model->fotografia = $imgName;
                $model->idCategoria = Yii::$app->request->post('idCategoriaHidden');
                $model->save();
                $idArray = explode(',' ,Yii::$app->request->post('idItems'));
                foreach($idArray as $id){
                    $item = Item::findOne($id);
                    $model->link('items', $item);
                }
                return $this->redirect(['..\categoria\index', 'id' => Yii::$app->request->get('idRestaurante')]);

            }
        } else {
            $model->loadDefaultValues();
        }

        return $this->render('create', [
            'model' => $model,
        ]);
    }

    /**
     * Updates an existing Menu model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param int $id ID
     * @return string|\yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id)
    {
        $model = $this->findModel($id);

        if ($this->request->isPost && $model->load($this->request->post())) {
            $image = UploadedFile::getInstance($model, 'fotografia');
            $imgName = 'img_' .$model->nome.'_Item_.'.$image->getExtension();
            $image->saveAs(Yii::getAlias('@fotografiaPath').'/'. $imgName);
            $model->fotografia = $imgName;
            $model->idCategoria = Yii::$app->request->post('idCategoriaHidden');
            $model->save();
            $model->unlinkAll('items', true);
            $categoria = Categoria::find()->where(['id' => $model->idCategoria])->one();
            $idArray = explode(',' ,Yii::$app->request->post('idItems'));
            foreach($idArray as $id){
                $item = Item::findOne($id);
                $model->link('items', $item);
            }
            return $this->redirect(['..\categoria\index', 'id' => $categoria->idRestaurante, 'idCategoria' => $model->idCategoria]);
        }

        return $this->render('update', [
            'model' => $model,
        ]);
    }

    /**
     * Deletes an existing Menu model.
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
     * Finds the Menu model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id ID
     * @return Menu the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Menu::findOne(['id' => $id])) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }

    public function actionAddItem($id)
    {

    }


}
