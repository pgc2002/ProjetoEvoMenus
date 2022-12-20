<?php

namespace backend\modules\api\controllers;

use yii\rest\ActiveController;
use yii\web\Response;

/**
 * Default controller for the `api` module
 */
class UserController extends ActiveController
{
    public $modelClass = 'common\models\User';

    public function actionUsername()
    {
        $Usermodel = new $this->modelClass;
        $recs = $Usermodel::find()->select(['username'])->all();
        return $recs;
    }
    public function actionPassword()
    {
        $Usermodel = new $this->modelClass;
        $recs = $Usermodel::find()->select(['password'])->all();
        return $recs;
    }
    public function actionemail()
    {
        $Usermodel = new $this->modelClass;
        $recs = $Usermodel::find()->select(['email'])->all();
        return $recs;
    }

    public function actioncreationdate()
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

    public function actionnif()
    {
        $Usermodel = new $this->modelClass;
        $recs = $Usermodel::find()->select(['nif'])->all();
        return $recs;
    }





    public function behaviors() {
        return [
            [
                'class' => \yii\ filters\ ContentNegotiator::className(),
                'only' => ['index', 'view'],
                'formats' => [
                    'application/json' => \yii\ web\ Response::FORMAT_JSON,
                ],
            ],
        ];
    }

}
