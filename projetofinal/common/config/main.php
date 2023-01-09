<?php
return [
    'aliases' => [
        '@bower' => '@vendor/bower-asset',
        '@npm'   => '@vendor/npm-asset',
    ],
    'vendorPath' => dirname(dirname(__DIR__)) . '/vendor',
    'modules' => [
        'admin' => [ 'class' => 'mdm\admin\Module',]
    ],
    'components' => [
        'user' => [
            'identityClass' => 'mdm\admin\models\User',
            'loginUrl' => ['admin/user/login'],
        ],
        'authManager' => [
          'class' => 'yii\rbac\DBManager',
        ],
        'cache' => [
            'class' => 'yii\caching\FileCache',
        ],
    ],

];
