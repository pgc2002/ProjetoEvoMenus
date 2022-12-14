<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "pedidoinscricao".
 *
 * @property int $id
 * @property string $nome
 * @property string $email
 * @property string $telemovel
 * @property string|null $morada
 */
class Pedidoinscricao extends \yii\db\ActiveRecord
{
    public $pais;
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'pedidoinscricao';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['nome', 'email', 'telemovel'], 'required'],
            [['nome', 'email'], 'string', 'max' => 100],
            [['telemovel'], 'string', 'max' => 13],
            [['morada'], 'string', 'max' => 200],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'nome' => 'Nome',
            'email' => 'Email',
            'telemovel' => 'Telemovel',
            'morada' => 'Morada',
        ];
    }
}
