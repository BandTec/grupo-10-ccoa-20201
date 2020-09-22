module.exports = (sequelize, DataTypes) => {
    let Abastecimento = sequelize.define('Abastecimento',{
		idAbastecimento: {
			field: 'idAbastecimento',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nivelSensor: {
			field: 'nivelSensor',
			type: DataTypes.STRING,
			allowNull: false
		},
		dataAbastecimento: {
			field: 'dataAbastecimento',
			type: DataTypes.DATE,
			allowNull: false
		},
		fkfornecedorasAbastecimento: {
			field: 'fkfornecedorasAbastecimento',
			type: DataTypes.INTEGER,
			allowNull: false
        },
        fkfileiras: {
			field: 'fkfileiras',
			type: DataTypes.INTEGER,
			allowNull: false
        }
	}, 
	{
		tableName: 'abastecimento', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Abastecimento;
};
