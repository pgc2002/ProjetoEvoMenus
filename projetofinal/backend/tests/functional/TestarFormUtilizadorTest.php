<?php
namespace backend\tests\functional;

class TestarFormUtilizadorTest extends \Codeception\Test\Unit
{
    /**
     * @var \backend\tests\FunctionalTester
     */
    protected $tester;
    
    protected function _before(FunctionalTester $I)
    {
        $I->amOnRoute('site/login');
    }

    protected function _after()
    {
    }

    public function checkVazio(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParametros('', ''));
        $I->seeValidationError('Username não pode estar vazio.');
        $I->seeValidationError('Password não pode estar vazia.');
    }

    public function checkPasswordErrada(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParametros('admin', 'wrong'));
        $I->seeValidationError('Password ou utilizador incorreto.');
    }

    public function checkContaInativa(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParametros('test.test', 'Test1234'));
        $I->seeValidationError('Username ou password incorreta.');
    }

    public function checkLoginValid(FunctionalTester $I)
    {
        $I->submitForm('#login-form', $this->formParametros('teste', '12345678'));
        $I->see('Logout (teste)', 'form button[type=submit]');
        $I->dontSeeLink('Login');
        $I->dontSeeLink('Signup');
    }
}