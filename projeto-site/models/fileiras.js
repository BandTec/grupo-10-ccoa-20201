module.exports = (sequelize, DataTypes) => {
    let Fileiras = sequelize.define('Fileiras',{
		idFileiras: {
			field: 'idFileiras',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		sensor3: {
			field: 'sensor3',
			type: DataTypes.INTEGER,
			allowNull: false
		},
		sensor2: {
			field: 'sensor2',
			type: DataTypes.INTEGER,
			allowNull: false
		},
		sensor1: {
			field: 'sensor1',
			type: DataTypes.INTEGER,
			allowNull: false
		},
		fkproduto: {
			field: 'fkproduto',
			type: DataTypes.INTEGER,
			allowNull: false
        },
        fkgeladeiraFileira: {
			field: 'fkgeladeiraFileira',
			type: DataTypes.INTEGER,
			allowNull: false
        }
	}, 
	{
		tableName: 'fileiras', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Fileiras;
};
