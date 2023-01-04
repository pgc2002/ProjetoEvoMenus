<?php

namespace common\models;

use common\models\User;
use Yii;

/**
 * This is the model class for table "pedido".
 *
 * @property int $id
 * @property float $valorTotal
 * @property string $estado
 * @property int $idCliente
 *
 * @property User $idCliente0
 * @property ItemsPedido[] $itemsPedidos
 * @property MenusPedido[] $menusPedidos
 * @property Pagamento[] $pagamentos
 */
class Pedido extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'pedido';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['valorTotal', 'estado', 'idCliente'], 'required'],
            [['valorTotal'], 'number'],
            [['idCliente'], 'integer'],
            [['estado'], 'string', 'max' => 20],
            [['idCliente'], 'exist', 'skipOnError' => true, 'targetClass' => User::class, 'targetAttribute' => ['idCliente' => 'id']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'valorTotal' => 'Valor Total',
            'estado' => 'Estado',
            'idCliente' => 'Id Cliente',
            'nomeCliente' => 'Cliente',
        ];
    }

    /**
     * Gets query for [[IdCliente0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getIdCliente()
    {
        return $this->hasOne(User::class, ['id' => 'idCliente']);
    }

    /**
     * Gets query for [[ItemsPedidos]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getItems()
    {
        return $this->hasMany(Item::class, ['id' => 'idItem'])->viaTable('items_pedido', ['idPedido' => 'id']);
    }
    public function getItemsPedidos()
    {
        return $this->hasMany(ItemsPedido::class, ['idPedido' => 'id']);
    }

    /**
     * Gets query for [[MenusPedidos]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getMenus()
    {
        return $this->hasMany(Menu::class, ['id' => 'idMenu'])->viaTable('menus_pedido', ['idPedido' => 'id']);
    }

    public function getMenusPedidos()
    {
        return $this->hasMany(MenusPedido::class, ['idPedido' => 'id']);
    }

    /**
     * Gets query for [[Pagamentos]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getPagamentos()
    {
        return $this->hasMany(Pagamento::class, ['idPedido' => 'id']);
    }

    public function getNomeCliente()
    {
        return User::findOne($this->idCliente)->nome;
    }
}
