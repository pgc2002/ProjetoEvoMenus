<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "pagamento".
 *
 * @property int $id
 * @property float $valor
 * @property string $metodo
 * @property int $idPedido
 *
 * @property Pedido $idPedido0
 */
class Pagamento extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'pagamento';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['valor', 'metodo', 'idPedido'], 'required'],
            [['valor'], 'number'],
            [['idPedido'], 'integer'],
            [['metodo'], 'string', 'max' => 50],
            [['idPedido'], 'exist', 'skipOnError' => true, 'targetClass' => Pedido::class, 'targetAttribute' => ['idPedido' => 'id']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'valor' => 'Valor',
            'metodo' => 'Metodo',
            'idPedido' => 'Id Pedido',
        ];
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
