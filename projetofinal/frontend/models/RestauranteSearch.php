<?php

namespace app\models;

use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Restaurante;

/**
 * RestauranteSearch represents the model behind the search form of `app\models\Restaurante`.
 */
class RestauranteSearch extends Restaurante
{
    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['id', 'lotacaoMaxima', 'idMorada', 'idEmenta', 'idHorario'], 'integer'],
            [['nome', 'email', 'telemovel'], 'safe'],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     *
     * @return ActiveDataProvider
     */
    public function search($params)
    {
        $query = Restaurante::find();

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        // grid filtering conditions
        $query->andFilterWhere([
            'id' => $this->id,
            'lotacaoMaxima' => $this->lotacaoMaxima,
            'idMorada' => $this->idMorada,
            'idEmenta' => $this->idEmenta,
            'idHorario' => $this->idHorario,
        ]);

        $query->andFilterWhere(['like', 'nome', $this->nome])
            ->andFilterWhere(['like', 'email', $this->email])
            ->andFilterWhere(['like', 'telemovel', $this->telemovel]);

        return $dataProvider;
    }
}
