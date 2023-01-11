<?php
$params = array_merge(
    require __DIR__ . '/../../common/config/params.php',
    require __DIR__ . '/../../common/config/params-local.php',
    require __DIR__ . '/params.php',
    require __DIR__ . '/params-local.php'
);

return [
    'id' => 'app-backend',
    'basePath' => dirname(__DIR__),
    'controllerNamespace' => 'backend\controllers',
    'bootstrap' => ['log'],
    'modules' => ['api' => ['class' => 'backend\modules\api\ModuleAPI',],],
    'components' => [
        'request' => [
            'csrfParam' => '_csrf-backend',
            'parsers' => ['application/json' => 'yii\web\JsonParser',],

        ],
        'user' => [
            'identityClass' => 'common\models\User',
            'enableAutoLogin' => true,
            'identityCookie' => ['name' => '_identity'/*'_identity-backend'*/, 'httpOnly' => true],
        ],
        'session' => [
            // this is the name of the session cookie used for login on the backend
            /*'name' => 'advanced-backend',*/
            'name' => 'advanced',
        ],
        'log' => [
            'traceLevel' => YII_DEBUG ? 3 : 0,
            'targets' => [
                [
                    'class' => 'yii\log\FileTarget',
                    'levels' => ['error', 'warning'],
                ],
            ],
        ],
        'authManager' => [
            'class' => 'yii\rbac\DbManager',
            'defaultRoles' => ['Admin'],
        ],
        'errorHandler' => [
            'errorAction' => 'site/error',
        ],
        'urlManager' => [
            'enablePrettyUrl' => true,
            'showScriptName' => false,
            'rules' => [
                ['class' => 'yii\rest\UrlRule','controller' => 'api/user',
                    'extraPatterns' => [
                        'GET count' => 'count',
                        'GET username' => 'username',
                        'GET password' => 'password',
                        'GET email' => 'email',
                        'GET creationDate' => 'creationdate',
                        'GET telemovel' => 'telemovel',
                        'GET nif' => 'nif', ],],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/pedido'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/categoria'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/horariofuncionamento'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/item'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/itemspedido'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/menu'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/menuspedido'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/mesa'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/morada'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/pagamento'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/pais'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/pedido'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/pedidoinscricao'],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/restaurante'],
            ],
        ],
        /*'view' => [
            'theme' => [
                'pathMap' => [
                    '@app/views' => '@vendor/hail812/yii2-adminlte3/src/views'
                ],
            ],
        ],*/
],
    'params' => $params,
];
