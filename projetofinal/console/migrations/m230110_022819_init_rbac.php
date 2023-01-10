<?php
use yii\db\Migration;

/**
 * Class m230110_022819_init_rbac
 */
class m230110_022819_init_rbac extends Migration
{
    /**
     * {@inheritdoc}
     */
    /*public function safeUp()
    {

    }*/

    /**
     * {@inheritdoc}
     */
    /*public function safeDown()
    {
        echo "m230110_022819_init_rbac cannot be reverted.\n";

        return false;
    }*/

    public function up()
    {
        $auth = Yii::$app->authManager;

        // Permissoes
            // Mesas
            $visualizarMesas = $auth->createPermission('visualizarMesas');
            $visualizarMesas->description = 'Permite visualizar as mesas';
            $auth->add($visualizarMesas);

            $estadoMesa = $auth->createPermission('alterarEstadoMesa');
            $estadoMesa->description = 'Permite alterar o estado das mesas';
            $auth->add($estadoMesa);

            $crudMesas = $auth->createPermission('crudMesas');
            $crudMesas->description = 'Permite gerir as mesas';
            $auth->add($crudMesas);

            // Menus
            $visualizarMenus = $auth->createPermission('visualizarMenus');
            $visualizarMenus->description = 'Permite visualizar os menus';
            $auth->add($visualizarMenus);

            $estadoMenu = $auth->createPermission('alterarEstadoMenu');
            $estadoMenu->description = 'Permite alterar o estado dos menus';
            $auth->add($estadoMenu);

            $crudMenus = $auth->createPermission('crudMenus');
            $crudMenus->description = 'Permite gerir os menus';
            $auth->add($crudMenus);

            // Pedidos
            $visualizarPedidos = $auth->createPermission('visualizarPedidos');
            $visualizarPedidos->description = 'Permite visualizar os pedidos';
            $auth->add($visualizarPedidos);

            $editarPedidos = $auth->createPermission('editarPedidos');
            $editarPedidos->description = 'Permite editar os pedidos';
            $auth->add($editarPedidos);

            // Pagamentos
            $visualizarPagamentos = $auth->createPermission('visualizarPagamentos');
            $visualizarPagamentos->description = 'Permite visualizar os pagamentos';
            $auth->add($visualizarPagamentos);

            // Funcionarios
            $crudFuncionarios = $auth->createPermission('crudFuncionarios');
            $crudFuncionarios->description = 'Permite gerir os funcionarios';
            $auth->add($crudFuncionarios);

            // Backend
            $acessoBackend = $auth->createPermission('acessoBackend');
            $acessoBackend->description = 'Permite o acesso ao backend';
            $auth->add($acessoBackend);
        //

        // Roles
        $admin = $auth->createRole('Admin');
        $admin->description = 'Tem permissões para tudo';
        $auth->add($admin);
        $auth->addChild($admin, $acessoBackend);

        $gestor = $auth->createRole('Gestor');
        $gestor->description = 'Tem permissões para gerir o seu restaurante';
        $auth->add($gestor);
        $auth->addChild($gestor, $crudMesas);
        $auth->addChild($gestor, $crudMenus);
        $auth->addChild($gestor, $visualizarPedidos);
        $auth->addChild($gestor, $editarPedidos);
        $auth->addChild($gestor, $visualizarPagamentos);
        $auth->addChild($gestor, $crudFuncionarios);

        $funcionario = $auth->createRole('Funcionario');
        $funcionario->description = 'Tem permissões para gerir os pedidos do seu restaurante';
        $auth->add($funcionario);
        $auth->addChild($funcionario, $visualizarMesas);
        $auth->addChild($funcionario, $estadoMesa);
        $auth->addChild($funcionario, $visualizarMenus);
        $auth->addChild($funcionario, $estadoMenu);
        $auth->addChild($funcionario, $visualizarPedidos);
        $auth->addChild($funcionario, $editarPedidos);
        $auth->addChild($funcionario, $visualizarPagamentos);

        $cliente = $auth->createRole('Cliente');
        $cliente->description = 'Só tem permissão para visualizar as páginas inicias';
        $auth->add($cliente);
    }

    public function down()
    {
        echo "m230109_183409_init_rbac cannot be reverted.\n";

        return false;
    }
}
