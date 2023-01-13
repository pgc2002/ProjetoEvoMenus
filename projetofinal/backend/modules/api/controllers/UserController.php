<?php

namespace backend\modules\api\controllers;

use yii\data\ActiveDataProvider;
use yii\filters\ContentNegotiator;
use yii\rest\ActiveController;
use yii\web\Response;
use common\models\User;

/**
 * Default controller for the `api` module
 */
class UserController extends ActiveController
{
    public $modelClass = 'common\models\User';

    public function actionAll(){
        $query = User::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
            'pagination' => false
        ]);

        return $dataProvider;
    }

    public function actionCount(){
        $recs = User::find()->all();
        return count($recs);
    }

    public function actionUsername()
    {
        $recs = User::find()->select(['username'])->all();
        return $recs;
    }
    public function actionPassword()
    {
        $Usermodel = new $this->modelClass;
        $recs = $Usermodel::find()->select(['password'])->all();
        return $recs;
    }
    public function actionEmail()
    {
        $Usermodel = new $this->modelClass;
        $recs = $Usermodel::find()->select(['email'])->all();
        return $recs;
    }

    public function actionCreationdate()
    {
        $Usermodel = new $this->modelClass;
        $recs = $Usermodel::find()->select(['creationdate'])->all();
        return $recs;
    }

    public function actionTelemovel()
    {
        $Usermodel = new $this->modelClass;
        $recs = $Usermodel::find()->select(['telemovel'])->all();
        return $recs;
    }

    public function actionNif()
    {
        $Usermodel = new $this->modelClass;
        $recs = $Usermodel::find()->select(['nif'])->all();
        return $recs;
    }

    /*public function behaviors() {
        return [
            [
                'class' => ContentNegotiator::className(),
                'only' => ['index', 'view'],
                'formats' => [
                    'application/json' => Response::FORMAT_JSON,
                ],
            ],
        ];
    }*/

}
