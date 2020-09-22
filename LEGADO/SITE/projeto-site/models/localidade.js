module.exports = (sequelize, DataTypes) => {
    let Localidade = sequelize.define('Localidade',{
		idLocalidade: {
			field: 'idLocalidade',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nomeLocalidade: {
			field: 'nomeLocalidade',
			type: DataTypes.STRING,
			allowNull: false
		},
		cidade: {
			field: 'cidade',
			type: DataTypes.STRING,
			allowNull: false
		},
		bairro: {
			field: 'bairro',
			type: DataTypes.STRING,
			allowNull: false
		},
		rua: {
			field: 'rua',
			type: DataTypes.STRING,
			allowNull: false
		}
	}, 
	{
		tableName: 'localidades', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Localidade;
};
