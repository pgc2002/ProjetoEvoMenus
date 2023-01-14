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
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET count' => 'count',
                        'GET username' => 'username',
                        'GET password' => 'password',
                        'GET email' => 'email',
                        'GET creationDate' => 'creationdate',
                        'GET telemovel' => 'telemovel',
                        'GET nif' => 'nif',
                        'GET {idUtilizador}/morada' => 'moradauser',
                        'GET {idUtilizador}/pedido' => 'pedidosuser',


                    ],
                    'tokens' =>[
                        '{idUtilizador}' => '<idUtilizador:\\d+>',
                    ]
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/pedido',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET menus/{idPedido}' => 'menus',
                        'GET items/{idPedido}' => 'items',
                        'GET pagamentos/{idPedido}' => 'pagamentos',
                        //'GET conteudo/{idPedido}' => 'conteudo',
                        'PUT {idPedido}/valor/{valor}' => 'valorint',
                        'PUT {idPedido}/valor/{valor}.{valor2}' => 'valorfloat',
                    ],
                    'tokens' =>[
                        '{idPedido}' => '<idPedido:\\d+>',
                        '{valor}' => '<valor:\\d+>', //'<valor:\\[0-9]*\.[0-9]+', //'<valor:\\d+>', '<valor:\\[+-]?([0-9]*[.])?[0-9]+>',
                        '{valor2}' => '<valor2:\\d+>',
                    ]
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/categoria',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET menus/{idCategoria}' => 'menus',
                        'GET items/{idCategoria}' => 'items',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/horariofuncionamento',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/item',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/itemspedido',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/menu',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/menuspedido',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/mesa',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET count/{idRestaurante}' => 'count',
                    ],
                    'tokens' =>[
                        '{idRestaurante}' => '<idRestaurante:\\d+>',
                    ]
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/morada',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/pagamento',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/pais',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/restaurante',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET count' => 'count',
                        'GET morada' => 'moradasrestaurantes',
                        'GET mesa' => 'mesasrestaurantes',


                    ],
                    'tokens' =>[
                        '{idRestaurante}' => '<idRestaurante:\\d+>',
                    ]
                ],
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