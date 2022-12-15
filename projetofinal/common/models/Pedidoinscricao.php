<?php

namespace common\models;

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
            'telemovel' => 'Telemóvel',
            'morada' => 'Morada',
            'moradaFormatada' => 'Morada',
        ];
    }

    public function getMoradaFormatada(){
        $arrayMorada= explode("!%$#%&()", $this->morada );
        return $arrayMorada[0].', '.$arrayMorada[1].', '.$arrayMorada[2].', '.$arrayMorada[3];
    }
}
