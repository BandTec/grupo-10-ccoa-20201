module.exports = (sequelize, DataTypes) => {
    let Geladeira = sequelize.define('Geladeira',{
		idGeladeira: {
			field: 'idGeladeira',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		fklocalidadeGeladeira: {
			field: 'fklocalidadeGeladeira',
			type: DataTypes.INTEGER,
			allowNull: false
        },
        fkfornecedoraGeladeira: {
			field: 'fkfornecedoraGeladeira',
			type: DataTypes.INTEGER,
			allowNull: false
        }
	}, 
	{
		tableName: 'geladeira', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Geladeira;
};
