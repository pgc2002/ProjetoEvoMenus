<?php

namespace backend\modules\api;
class Connection
{
    public  $ip = 'localhost';
    public  $port = 1883;
    public  $clientId = 'sender';
    public  $topic = 'apiLog';
}