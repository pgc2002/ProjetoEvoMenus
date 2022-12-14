<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "horario_funcionamento".
 *
 * @property int $id
 * @property string $segunda
 * @property string $terca
 * @property string $quarta
 * @property string $quinta
 * @property string $sexta
 * @property string $sabado
 * @property string $domingo
 *
 * @property Restaurante[] $restaurantes
 */
class Horariofuncionamento extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'horario_funcionamento';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['segunda', 'terca', 'quarta', 'quinta', 'sexta', 'sabado', 'domingo'], 'required'],
            [['segunda', 'terca', 'quarta', 'quinta', 'sexta', 'sabado', 'domingo'], 'string', 'max' => 23],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'segunda' => 'Segunda',
            'terca' => 'Terca',
            'quarta' => 'Quarta',
            'quinta' => 'Quinta',
            'sexta' => 'Sexta',
            'sabado' => 'Sabado',
            'domingo' => 'Domingo',
        ];
    }

    /**
     * Gets query for [[RestauranteId]].
     *
     * @return int
     */
    public function getRestauranteId()
    {
        return Restaurante::findOne(["idHorario" => $this->id])->id;
    }

    public function getHorario(){
        $dias = array(
            "segunda" => $this->segunda,
            "terca"   => $this->terca,
            "quarta"  => $this->quarta,
            "quinta"  => $this->quinta,
            "sexta"   => $this->sexta,
            "sabado"  => $this->sabado,
            "domingo" => $this->domingo
        );

        foreach ($dias as $key => $value) {
            $dias[$key] = explode("-", $value);
        }

        return $dias;
    }
}
