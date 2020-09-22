'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Usuario = sequelize.define('Usuario',{
		id: {
			field: 'idConsumidor',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nome: {
			field: 'nomeConsumidor',
			type: DataTypes.STRING,
			allowNull: false
		},
		login: {
			field: 'emailConsumidor',
			type: DataTypes.STRING,
			allowNull: false
		},
		senha: {
			field: 'senhaConsumidor',
			type: DataTypes.STRING,
			allowNull: false
		},
		localidade: {
			field: 'fkLocalidade',
			type: DataTypes.INTEGER,
			allowNull: false
		}
	}, 
	{
		tableName: 'consumidoresFinais', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Usuario;
};
