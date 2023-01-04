<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "item".
 *
 * @property int $id
 * @property string $nome
 * @property string $fotografia
 * @property float $preco
 * @property int $idCategoria
 *
 * @property Categoria $idCategoria0
 * @property ItemsMenu[] $itemsMenus
 * @property ItemsPedido[] $itemsPedidos
 */
class Item extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'item';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['nome', 'fotografia', 'preco', 'idCategoria'], 'required'],
            [['fotografia'], 'string'],
            [['preco'], 'number'],
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
            'preco' => 'Preço',
            'idCategoria' => 'Id Categoria',
            'precoFormatado' => 'Preço',
        ];
    }


    public function getPrecoFormatado(){
        return $this->preco . ' €';
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
     * 
     * public function getItemsMenus()
     * {
     *   return $this->hasMany(ItemsMenu::class, ['idItem' => 'id']);
     * }
     * 
     */


    public function getMenus()
    {
        return $this->hasMany(Menu::class, ['id' => 'idMenu'])->viaTable('items_menu', ['idItem' => 'id']);
    }

    /**
     * Gets query for [[ItemsPedidos]].
     *
     * @return \yii\db\ActiveQuery
     * 
     * public function getItemsPedidos()
     * {
     *   return $this->hasMany(ItemsPedido::class, ['idItem' => 'id']);
     * }
     */

    public function getPedidos()
    {
        return $this->hasMany(Pedido::class, ['id' => 'idPedido'])->viaTable('items_pedido', ['idItem' => 'id']);
    }

}
