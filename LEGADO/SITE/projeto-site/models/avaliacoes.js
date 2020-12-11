const {DataTypes}  = require('sequelize');
const  sequelize = require("../config/mySql.js")


module.exports = (sequelize, DataTypes) => sequelize.define('avaliacoes', {
    idAvaliacao: {
        primaryKey: true,
        type: DataTypes.INTEGER(1),
        allowNull: false
    },
    texto: {
        type: DataTypes.CHAR(200)
    }
    ,
    fkConsumidores:{
        type : DataTypes.INTEGER,
        references: {model:"consumidoresFinais", key:"idConsumidor"}
    }    
},
    {
        timestamps: false,
    })