module.exports = (sequelize, DataTypes) => {
    let Fornecedoras = sequelize.define('Fornecedoras',{
		idFornecedoras: {
			field: 'idFornecedoras',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nomeFornecedora: {
			field: 'nomeFornecedora',
			type: DataTypes.STRING,
			allowNull: false
		},
		senhaFornecedora: {
			field: 'senhaFornecedora',
			type: DataTypes.STRING,
			allowNull: false
		},emailFornecedora: {
			field: 'emailFornecedora',
			type: DataTypes.STRING,
			allowNull: false
		}
	}, 
	{
		tableName: 'fornecedoras', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Fornecedoras;
};