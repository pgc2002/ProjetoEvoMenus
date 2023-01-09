<?php

namespace common\models;

use Yii;
use common\models\Mesa;


/**
 * This is the model class for table "restaurante".
 *
 * @property int $id
 * @property string $nome
 * @property string $email
 * @property int $lotacaoMaxima
 * @property string $telemovel
 * @property int|null $idMorada
 * @property int|null $idHorario
 *
 * @property Gestor[] $gestor
 * @property HorarioFuncionamento $horario
 * @property Morada $morada
 * @property Mesa[] $mesas
 * @property Trabalhador[] $trabalhadores
 */
class Restaurante extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'restaurante';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['nome', 'email', 'telemovel', 'idMorada'], 'required'],
            [['lotacaoMaxima', 'idMorada', 'idHorario'], 'integer'],
            [['nome', 'email'], 'string', 'max' => 100],
            [['telemovel'], 'string', 'max' => 13],
            [['idHorario'], 'exist', 'skipOnError' => true, 'targetClass' => HorarioFuncionamento::class, 'targetAttribute' => ['idHorario' => 'id']],
            [['idMorada'], 'exist', 'skipOnError' => true, 'targetClass' => Morada::class, 'targetAttribute' => ['idMorada' => 'id']],
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
            'email' => 'Email',
            'lotacaoMaxima' => 'Lotacao Maxima',
            'telemovel' => 'Telemovel',
            'idMorada' => 'Id Morada',
            'idHorario' => 'Id Horario',
            'moradaFormatada' => 'Morada',
            'numeroMesas' => 'Número de mesas',
        ];
    }

    /**
     * Gets query for [[Gestors]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getGestor()
    {
        return $this->hasMany(Gestor::class, ['idRestaurante' => 'id']);
    }

    /**
     * Gets query for [[IdHorario0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getHorario()
    {
        return $this->hasOne(Horariofuncionamento::class, ['id' => 'idHorario']);
    }

    /**
     * Gets query for [[IdMorada0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getMorada()
    {
        return $this->hasOne(Morada::class, ['id' => 'idMorada']);
    }

    /**
     * Gets query for [[count Mesas]].
     *
     * @return int
     */
    /*public function getMesas()
    {
        return $this->hasMany(Mesa::class, ['id' => 'idMesa']);
    }*/

    public function getNumeroMesas()
    {
        $mesas = Mesa::findAll(['idRestaurante' => $this->id]);
        return count($mesas);
    }

    /**
     * Gets query for [[Trabalhadors]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getTrabalhadores()
    {
        return $this->hasMany(Trabalhador::class, ['idRestaurante' => 'id']);
    }

    public function getMoradaFormatada(){
        return $this->morada ? $this->morada->pais.', '. $this->morada->cidade .', '. $this->morada->rua. ', '.$this->morada->codpost : 'Sem morada';
    }
}
