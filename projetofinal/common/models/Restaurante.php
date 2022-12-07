<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "restaurante".
 *
 * @property int $id
 * @property string $nome
 * @property string $email
 * @property int $lotacaoMaxima
 * @property string $telemovel
 * @property int|null $idMorada
 * @property int|null $idEmenta
 * @property int|null $idHorario
 *
 * @property Gestor[] $gestor
 * @property Ementa $ementa
 * @property HorarioFuncionamento $horario
 * @property Morada $morada
 * @property Mesa[] $mesas
 * @property Trabalhador[] $trabalhadors
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
            [['lotacaoMaxima', 'idMorada', 'idEmenta', 'idHorario'], 'integer'],
            [['nome', 'email'], 'string', 'max' => 100],
            [['telemovel'], 'string', 'max' => 13],
            [['idEmenta'], 'exist', 'skipOnError' => true, 'targetClass' => Ementa::class, 'targetAttribute' => ['idEmenta' => 'id']],
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
            'idEmenta' => 'Id Ementa',
            'idHorario' => 'Id Horario',
            'moradaFormatada' => 'Morada',
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
     * Gets query for [[IdEmenta0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getEmenta()
    {
        return $this->hasOne(Ementa::class, ['id' => 'idEmenta']);
    }

    /**
     * Gets query for [[IdHorario0]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getHorario()
    {
        return $this->hasOne(HorarioFuncionamento::class, ['id' => 'idHorario']);
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
     * Gets query for [[Mesas]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getMesas()
    {
        return $this->hasMany(Mesa::class, ['idRestaurante' => 'id']);
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
        return $this->morada ? $this->morada->cidade . ", " . $this->morada->rua : 'Sem morada';
    }
}
