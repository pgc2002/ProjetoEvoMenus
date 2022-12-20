<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "menu".
 *
 * @property int $id
 * @property string $nome
 * @property string $fotografia
 * @property float $desconto
 * @property int $idCategoria
 *
 * @property Categoria $idCategoria0
 * @property ItemsMenu[] $itemsMenus
 * @property MenusPedido[] $menusPedidos
 */
class Menu extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'menu';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['nome', 'fotografia', 'desconto', 'idCategoria'], 'required'],
            [['fotografia'], 'string'],
            [['desconto'], 'number'],
            [['idCategoria'], 'integer'],
            [['nome'], 'string', 'max' => 100],
            [['idCategoria'], 'exist', 'skipOnError' => true, 'targetClass' => Categoria::class, 'targetAttribute' => ['idCategoria' => 'id']],
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
            'fotografia' => 'Fotografia',
            'desconto' => 'Desconto',
            'idCategoria' => 'Id Categoria',
        ];
    }

    /**
     * Gets query for [[IdCategoria0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getIdCategoria0()
    {
        return $this->hasOne(Categoria::class, ['id' => 'idCategoria']);
    }

    /**
     * Gets query for [[ItemsMenus]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getItemsMenus()
    {
        return $this->hasMany(ItemsMenu::class, ['idMenu' => 'id']);
    }

    /**
     * Gets query for [[MenusPedidos]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getMenusPedidos()
    {
        return $this->hasMany(MenusPedido::class, ['idMenu' => 'id']);
    }
}
