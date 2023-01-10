<?php

namespace backend\controllers;

use PHPUnit\Framework\Error\Error;
use yii;
use common\models\User;
use common\models\Morada;
use app\models\UserSearch;
use yii\web\Controller;
use yii\web\ForbiddenHttpException;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\base\Model;

/**
 * UserController implements the CRUD actions for User model.
 */
class UserController extends Controller
{
    public $password;

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
     * Lists all User models.
     *
     * @return string
     */
    public function actionIndex()
    {
        $searchModel = new UserSearch();
        $dataProvider = $searchModel->search($this->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single User model.
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
     * Creates a new User model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return string|\yii\web\Response
     */
    public function actionCreate()
    {
        $user = new User();
        $morada = new Morada();

        if ($this->request->isPost) {
            if ($user->load($this->request->post()) && $morada->load($this->request->post()) ) {
                $this->password = Yii::$app->request->post('password');

                $checkMorada = Morada::find()->where(['pais' => $morada->pais, 'cidade' => $morada->cidade,
                    'rua' => $morada->rua, 'codpost' => $morada->codpost])->one();

                if($checkMorada){
                    $idMorada = $checkMorada->id;
                }else {
                    $morada->save(false);
                    $idMorada = $morada->id;
                }

                $user->idMorada = $idMorada;
                $user->setPassword($this->password);
                $user->generateAuthKey();
                $user->save();

                $auth = \Yii::$app->authManager;
                //$role = $auth->getRole('Cliente');
                try {
                    $auth->assign($role, $user->id);
                } catch (\Exception $e) {
                }
                
                return $this->redirect(['view', 'id' => $user->id]);
            }else if($user->load($this->request->post())){

                $this->password = Yii::$app->request->post('password');
                $user->setPassword($this->password);
                $user->generateAuthKey();
                $user->save();

                /*
                $auth = \Yii::$app->authManager;
                switch ($user->tipo){
                    case 'Admin':
                        $role = $auth->getRole('Admin');
                        break;
                    case 'Gestor':
                        $role = $auth->getRole('Gestor');
                        break;
                    case 'Funcionario':
                        $role = $auth->getRole('Funcionario');
                        break;
                }
                try {
                    $auth->assign($role, $user->id);
                } catch (\Exception $e) {
                }*/

                return $this->redirect(['view', 'id' => $user->id]);
            }
            else if(!Model::validateMultiple([$user, $morada])){
                Yii::$app->session->setFlash('error', 'Ocorreu um erro ao criar um utilizador.');
                return $this->refresh();
            }
        } else {
            $user->loadDefaultValues();
            $morada->loadDefaultValues();
        }

        return $this->render('create', [
            'user' => $user,
            'morada' => $morada,
        ]);
    }

    /**
     * Updates an existing User model.
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
     * Deletes an existing User model.
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
     * Finds the User model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id ID
     * @return User the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = User::findOne(['id' => $id])) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
