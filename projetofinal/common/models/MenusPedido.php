<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "menus_pedido".
 *
 * @property int $idMenu
 * @property int $idPedido
 *
 * @property Menu $idMenu0
 * @property Pedido $idPedido0
 */
class MenusPedido extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'menus_pedido';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['idMenu', 'idPedido'], 'required'],
            [['idMenu', 'idPedido'], 'integer'],
            [['idMenu'], 'exist', 'skipOnError' => true, 'targetClass' => Menu::class, 'targetAttribute' => ['idMenu' => 'id']],
            [['idPedido'], 'exist', 'skipOnError' => true, 'targetClass' => Pedido::class, 'targetAttribute' => ['idPedido' => 'id']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'idMenu' => 'Id Menu',
            'idPedido' => 'Id Pedido',
        ];
    }

    /**
     * Gets query for [[IdMenu0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getIdMenu0()
    {
        return $this->hasOne(Menu::class, ['id' => 'idMenu']);
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
