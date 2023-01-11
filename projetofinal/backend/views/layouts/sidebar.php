<?php

use common\models\User;

?>
<aside class="main-sidebar sidebar-dark-primary elevation-4" >
    <!-- Brand Logo -->
    <a href="<?=\yii\helpers\Url::home().'site\index'?>" class="brand-link">
        <img src="../web/resources/logo.png" alt="Evo Menus" class="brand-image img-circle elevation-2" style="height:auto; width: auto;">
        <span class="brand-text font-weight-light">Evo Menus</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar" id="sidebarPrincipal">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="info">
                <?php
                    echo '<a href="../user/view?id='.Yii::$app->user->identity->id.'&sb=1"class="d-block">Perfil de Utilizador</a>'
                ?>
            </div>
        </div>

        <!--div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <input type="checkbox" id="button" style="display: none;">
            <div class="image">
                <label for="button" class="fa fa-moon-o " style="cursor: pointer; align: center;"></label>
            </div>

            <div class="info">
                <a class="d-block" id="buttonText">Dark Mode</a>
            </div>
        </div>-->

        <script>
            /*let $sidebar = $('#sidebarPrincipal');
            let $container = $('<div />', {
                class: 'p-3 control-sidebar-content'
            });
            var darkMode = 0;

            $sidebar.append($container);

            let $dark_mode_checkbox = $('#button').on('click', function () {
                if ($(this).is(':checked')) {
                    $('body').addClass('dark-mode');
                } else {
                    $('body').removeClass('dark-mode');
                }
            });*/

        </script>

        <!-- SidebarSearch Form -->
        <!-- href be escaped -->
        <!-- <div class="form-inline">
            <div class="input-group" data-widget="sidebar-search">
                <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
                <div class="input-group-append">
                    <button class="btn btn-sidebar">
                        <i class="fas fa-search fa-fw"></i>
                    </button>
                </div>
            </div>
        </div> -->

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <?php
            echo \hail812\adminlte\widgets\Menu::widget([
                'items' => [
                    /*[
                        'label' => 'Starter Pages',
                        'icon' => 'tachometer-alt',
                        'badge' => '<span class="right badge badge-info">2</span>',
                        'items' => [
                            ['label' => 'Active Page', 'url' => ['site/index'], 'iconStyle' => 'far'],
                            ['label' => 'Inactive Page', 'iconStyle' => 'far'],
                        ]
                    ],
                    ['label' => 'Simple Link', 'icon' => 'th', 'badge' => '<span class="right badge badge-danger">New</span>'],
                    ['label' => 'Yii2 PROVIDED', 'header' => true],
                    ['label' => 'Login', 'url' => ['site/login'], 'icon' => 'sign-in-alt', 'visible' => Yii::$app->user->isGuest],
                    ['label' => 'Gii',  'icon' => 'file-code', 'url' => ['/gii'], 'target' => '_blank'],
                    ['label' => 'Debug', 'icon' => 'bug', 'url' => ['/debug'], 'target' => '_blank'],
                    ['label' => 'MULTI LEVEL EXAMPLE', 'header' => true],
                    ['label' => 'Level1'],
                    [
                        'label' => 'Level1',
                        'items' => [
                            ['label' => 'Level2', 'iconStyle' => 'far'],
                            [
                                'label' => 'Level2',
                                'iconStyle' => 'far',
                                'items' => [
                                    ['label' => 'Level3', 'iconStyle' => 'far', 'icon' => 'dot-circle'],
                                    ['label' => 'Level3', 'iconStyle' => 'far', 'icon' => 'dot-circle'],
                                    ['label' => 'Level3', 'iconStyle' => 'far', 'icon' => 'dot-circle']
                                ]
                            ],
                            ['label' => 'Level2', 'iconStyle' => 'far']
                        ]
                    ],
                    ['label' => 'Level1'],
                    ['label' => 'LABELS', 'header' => true],
                    ['label' => 'Important', 'iconStyle' => 'far', 'iconClassAdded' => 'text-danger'],
                    ['label' => 'Warning', 'iconClass' => 'nav-icon far fa-circle text-warning'],
                    ['label' => 'Informational', 'iconStyle' => 'far', 'iconClassAdded' => 'text-info'],*/
                ],
            ]);
            ?>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>