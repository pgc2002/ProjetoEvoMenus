<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "morada".
 *
 * @property int $id
 * @property string $pais
 * @property string $cidade
 * @property string $rua
 * @property string $codpost
 *
 * @property Restaurante[] $restaurantes
 * @property User[] $users
 */
class Morada extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'morada';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['id', 'pais', 'cidade', 'rua', 'codpost'], 'required'],
            [['id'], 'integer'],
            [['pais', 'cidade'], 'string', 'max' => 50],
            [['rua'], 'string', 'max' => 100],
            [['codpost'], 'string', 'max' => 9],
            [['id'], 'unique'],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'pais' => 'Pais',
            'cidade' => 'Cidade',
            'rua' => 'Rua',
            'codpost' => 'Codpost',
        ];
    }

    /**
     * Gets query for [[Restaurantes]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getRestaurantes()
    {
        return $this->hasMany(Restaurante::class, ['idMorada' => 'id']);
    }

    /**
     * Gets query for [[Users]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getUsers()
    {
        return $this->hasMany(User::class, ['idMorada' => 'id']);
    }
}
