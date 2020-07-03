module.exports = (sequelize, DataTypes) => {
    let Consumidores = sequelize.define('Consumidores',{
		idConsumidor: {
			field: 'idConsumidor',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nomeConsumidor: {
			field: 'nomeConsumidor',
			type: DataTypes.STRING,
			allowNull: false
		},
		senhaConsumidor: {
			field: 'senhaConsumidor',
			type: DataTypes.STRING,
			allowNull: false
		},emailConsumidor: {
			field: 'emailConsumidor',
			type: DataTypes.STRING,
			allowNull: false
		},
		fklocalidadeConsumidor: {
			field: 'fklocalidadeConsumidor',
			type: DataTypes.INTEGER,
			allowNull: false
		}
	}, 
	{
		tableName: 'Consumidores', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Consumidores;
};