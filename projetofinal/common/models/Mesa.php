<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "mesa".
 *
 * @property int $id
 * @property int $numero
 * @property int $capacidade
 * @property string $estado
 * @property int $idRestaurante
 *
 * @property Restaurante $idRestaurante0
 * @property User[] $users
 */
class Mesa extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'mesa';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['numero', 'capacidade', 'estado', 'idRestaurante'], 'required'],
            [['numero', 'capacidade', 'idRestaurante'], 'integer'],
            [['estado'], 'string', 'max' => 20],
            [['idRestaurante'], 'exist', 'skipOnError' => true, 'targetClass' => Restaurante::class, 'targetAttribute' => ['idRestaurante' => 'id']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'numero' => 'Numero',
            'capacidade' => 'Capacidade',
            'estado' => 'Estado',
            'idRestaurante' => 'Id Restaurante',
        ];
    }

    /**
     * Gets query for [[IdRestaurante0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getIdRestaurante0()
    {
        return $this->hasOne(Restaurante::class, ['id' => 'idRestaurante']);
    }

    /**
     * Gets query for [[Users]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getUsers()
    {
        return $this->hasMany(User::class, ['idMesa' => 'id']);
    }
}
