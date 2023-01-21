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
                        'GET {idUser}' => 'one',
                        'GET count' => 'count',
                        'GET validar/{username, password}' => 'validar',
                        'GET {idUser}/morada' => 'morada',
                        'GET {idUser}/pedidos' => 'pedidos',
                        'PUT alterarperfil/{idUser, username, nome, password, email, telemovel, nif}' => 'alterarperfil',
                        'PUT alterarmorada/{idUser, pais, cidade, rua, codpost}' => 'alterarmorada',
                        'POST criar/{username, nome, password, email, telemovel, nif, pais, cidade, rua, codpost}' => 'criar',
                    ],
                    'tokens' =>[
                        '{idUser}' => '<idUser:\\d+>',
                        '{username}' => '<username:\\s+>',
                        '{nome}' => '<nome:\\s+>',
                        '{password}' => '<password:\\s+>',
                        '{email}' => '<email:\\s+>',
                        '{telemovel}' => '<telemovel:\\s+>',
                        '{nif}' => '<nif:\\s+>',
                        '{pais}' => '<pais:\\s+>',
                        '{cidade}' => '<cidade:\\s+>',
                        '{rua}' => '<rua:\\s+>',
                        '{codpost}' => '<codpost:\\s+>',
                    ]
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/pedido',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET {idPedido}' => 'one',
                        'GET {idPedido}/menus' => 'menus',
                        'GET {idPedido}/items' => 'items',
                        'GET {idPedido}/pagamentos' => 'pagamentos',
                        //'GET {idPedido}/conteudo' => 'conteudo',
                        'PUT {idPedido}/valor/{valor}' => 'valor_int',
                        'PUT {idPedido}/valor/{valor}.{valor2}' => 'valor_float',
                        'POST criar/{valor, estado, idCliente, idRestaurante}' => 'criar',
                        'POST inserir_item/{idPedido, idItem}' => 'inserir_item',
                        'POST inserir_menu/{idPedido, idMenu}' => 'inserir_menu',
                    ],
                    'tokens' =>[
                        '{idPedido}' => '<idPedido:\\d+>',
                        '{valor}' => '<valor:\\d+>',
                        '{valor2}' => '<valor2:\\d+>',
                        '{estado}' => '<estado:\\s+>',
                        '{metodo}' => '<metodo:\\s+>',
                        '{idCliente}' => '<idCliente:\\d+>',
                        '{idRestaurante}' => '<idRestaurante:\\d+>',
                        '{idItem}' => '<idItem:\\d+>',
                        '{idMenu}' => '<idMenu:\\d+>',
                    ]
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/categoria',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET {idCategoria}' => 'one',
                        'GET {idCategoria}/menus' => 'menus',
                        'GET {idCategoria}/items' => 'items',
                    ],
                    'tokens' =>[
                        '{idCategoria}' => '<idCategoria:\\d+>',
                    ]
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/item',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET {id}' => 'one',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/menu',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET {id}' => 'one',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/mesa',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET {id}' => 'one',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/morada',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET {id}' => 'one',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/pagamento',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET {id}' => 'one',
                        'POST criar/{idPedido, valor, metodo}' => 'criar',
                    ],
                    'tokens' =>[
                        '{idPedido}' => '<idPedido:\\d+>',
                        '{valor}' => '<valor:\\d+>',
                        '{metodo}' => '<metodo:\\s+>',
                    ]
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/pais',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET {id}' => 'one',
                    ],
                ],
                ['class' => 'yii\rest\UrlRule','controller' => 'api/restaurante',
                    'pluralize' => false,
                    'extraPatterns' => [
                        'GET' => 'all',
                        'GET {id}' => 'one',
                        'GET count' => 'count',
                        'GET {id}/morada' => 'morada',
                        'GET moradas' => 'moradas',
                        'GET {id}/mesas' => 'mesas',
                        'GET {id}/num_mesas' => 'num_mesas',
                        'GET {id}/num_mesas_disponiveis' => 'num_mesas_disponiveis',
                        'GET {id}/horario' => 'horario',
                    ],
                ],
            ],
        ],
    ],
    'params' => $params,
];