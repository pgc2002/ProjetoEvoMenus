<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "items_pedido".
 *
 * @property int $idItem
 * @property int $idPedido
 *
 * @property Item $idItem0
 * @property Pedido $idPedido0
 */
class ItemsPedido extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'items_pedido';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['idItem', 'idPedido'], 'required'],
            [['idItem', 'idPedido'], 'integer'],
            [['idItem'], 'exist', 'skipOnError' => true, 'targetClass' => Item::class, 'targetAttribute' => ['idItem' => 'id']],
            [['idPedido'], 'exist', 'skipOnError' => true, 'targetClass' => Pedido::class, 'targetAttribute' => ['idPedido' => 'id']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'idItem' => 'Id Item',
            'idPedido' => 'Id Pedido',
        ];
    }

    /**
     * Gets query for [[IdItem0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getIdItem0()
    {
        return $this->hasOne(Item::class, ['id' => 'idItem']);
    }

    /**
     * Gets query for [[IdPedido0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getIdPedido0()
    {
        return $this->hasOne(Pedido::class, ['id' => 'idPedido']);
    }
}
