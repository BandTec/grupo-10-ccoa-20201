const sequelize = require('../models/').sequelize2;
var express = require('express');
var router = express.Router();
var modelo = require('../models/').avaliacoes;


router.post('/comentar', (req,res,next) =>{
	
	//let idGeladeira = req.body.Geladeira;
	
    let instrucaoSql = `select * from avaliacoes`;

    sequelize.query(instrucaoSql,{ 
		model: modelo,
		mapToModel: true 
	}).then(resultado => {
		console.log(resultado[0].nomeProduto);
		res.json(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
})

module.exports = router;