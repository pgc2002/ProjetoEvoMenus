<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "pedidoinscricao".
 *
 * @property int $id
 * @property int $nome
 * @property int $email
 * @property string $telemovel
 * @property string|null $morada
 */
class Pedidoinscricao extends \yii\db\ActiveRecord
{
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
            [['nome', 'email'], 'integer'],
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
