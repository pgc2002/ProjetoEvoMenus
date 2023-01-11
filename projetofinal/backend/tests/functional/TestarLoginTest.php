<?php
namespace backend\tests;
use common\models\User;
use common\fixtures\UserFixture;

class TestarLoginTest extends \Codeception\Test\Unit
{
    public function _fixtures()
    {
        return [
            'user' => [
                'class' => UserFixture::className(),
                'dataFile' => codecept_data_dir() . 'login_data.php',
            ],
        ];
    }
    
    protected function _before()
    {
        $I->amOnRoute('site/login');
    }

    protected function formParametros($login, $password)
    {
        return [
            'LoginForm[username]' => $login,
            'LoginForm[password]' => $password,
        ];
    }

    public function testCheckVazio(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParametros('', ''));
        $I->seeValidationError('Username não pode estar vazio.');
        $I->seeValidationError('Password não pode estar vazia.');
    }

    public function testCheckPasswordErrada(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParametros('admin', 'wrong'));
        $I->seeValidationError('Password ou utilizador incorreto.');
    }

    public function testCheckContaInativa(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParametros('test.test', 'Test1234'));
        $I->seeValidationError('Username ou password incorreta.');
    }

    public function testCheckLoginValid(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParametros('teste', '12345678'));
        $I->see('Logout (teste)', 'form button[type=submit]');
        $I->dontSeeLink('Login');
        $I->dontSeeLink('Signup');
    }

    
}