<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "items_menu".
 *
 * @property int $idMenu
 * @property int $idItem
 *
 * @property Item $idItem0
 * @property Menu $idMenu0
 */
class ItemsMenu extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'items_menu';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['idMenu', 'idItem'], 'required'],
            [['idMenu', 'idItem'], 'integer'],
            [['idMenu'], 'exist', 'skipOnError' => true, 'targetClass' => Menu::class, 'targetAttribute' => ['idMenu' => 'id']],
            [['idItem'], 'exist', 'skipOnError' => true, 'targetClass' => Item::class, 'targetAttribute' => ['idItem' => 'id']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'idMenu' => 'Id Menu',
            'idItem' => 'Id Item',
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
     * Gets query for [[IdMenu0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getIdMenu0()
    {
        return $this->hasOne(Menu::class, ['id' => 'idMenu']);
    }
}
