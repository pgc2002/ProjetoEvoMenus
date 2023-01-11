<?php
namespace frontend\tests\functional;

use frontend\tests\FunctionalTester;
use common\fixtures\UserFixture;

class TestarLoginTest extends \Codeception\Test\Unit
{
    /**
     * @var \frontend\tests\FunctionalTester
     */
    public function _fixtures()
    {
        return [
            'user' => [
                'class' => UserFixture::className(),
                'dataFile' => codecept_data_dir() . 'login_data.php',
            ],
        ];
    }

    protected function formParametros($login, $password)
    {
        return [
            'LoginForm[username]' => $login,
            'LoginForm[password]' => $password,
        ];
    }

    public function checkVazio(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParams('', ''));
        $I->seeValidationError('Username não pode estar vazio.');
        $I->seeValidationError('Password não pode estar vazia.');
    }

    public function checkPasswordErrada(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParams('admin', 'wrong'));
        $I->seeValidationError('Password ou utilizador incorreto.');
    }

    public function checkContaInativa(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParams('test.test', 'Test1234'));
        $I->seeValidationError('Username ou password incorreta.');
    }

    public function checkLoginValid(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParams('teste', '12345678'));
        $I->see('Logout (teste)', 'form button[type=submit]');
        $I->dontSeeLink('Login');
        $I->dontSeeLink('Signup');
    }

    protected function _before()
    {
        $I->amOnRoute('site/login');
    }
}