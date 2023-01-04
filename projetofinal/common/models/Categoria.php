<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "categoria".
 *
 * @property int $id
 * @property string $nome
 * @property int $idRestaurante
 *
 * @property Restaurante $idRestaurante0
 * @property int $idEmenta
 *
 * @property Ementa $idEmenta0
 * @property Item[] $items
 * @property Menu[] $menus
 */
class Categoria extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'categoria';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['nome', 'idRestaurante'], 'required'],
            [['idRestaurante'], 'integer'],
            [['nome'], 'string', 'max' => 100],
            [['idRestaurante'], 'exist', 'skipOnError' => true, 'targetClass' => Restaurante::class, 'targetAttribute' => ['idRestaurante' => 'id']],

            [['nome', 'idEmenta'], 'required'],
            [['idEmenta'], 'integer'],
            [['nome'], 'string', 'max' => 100],
            [['idEmenta'], 'exist', 'skipOnError' => true, 'targetClass' => Ementa::class, 'targetAttribute' => ['idEmenta' => 'id']],

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
            'idRestaurante' => 'Id Restaurante',
            'idEmenta' => 'Id Ementa',
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
     * Gets query for [[IdEmenta0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getIdEmenta0()
    {
        return $this->hasOne(Ementa::class, ['id' => 'idEmenta']);
    }

    /**
     * Gets query for [[Items]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getItems()
    {
        return $this->hasMany(Item::class, ['idCategoria' => 'id']);
    }

    /**
     * Gets query for [[Menus]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getMenus()
    {
        return $this->hasMany(Menu::class, ['idCategoria' => 'id']);
    }
}
