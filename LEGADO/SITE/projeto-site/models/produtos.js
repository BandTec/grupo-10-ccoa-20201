module.exports = (sequelize, DataTypes) => {
    let Produtos = sequelize.define('Produtos',{
		idProduto: {
			field: 'idProduto',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nomeProduto: {
			field: 'nomeProduto',
			type: DataTypes.STRING,
			allowNull: false
		}
	}, 
	{
		tableName: 'produtos', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Produtos;
};
