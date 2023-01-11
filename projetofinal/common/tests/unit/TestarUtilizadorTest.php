<?php
namespace common\tests;
use common\models\User;

class TestarUtilizadorTest extends \Codeception\Test\Unit
{
    
    protected function testReceberUsername()
    {
        $user = new User;
        $user->setUsername('teste');
        $this->assertEquals($user->getUsername(), 'teste');
    }
}