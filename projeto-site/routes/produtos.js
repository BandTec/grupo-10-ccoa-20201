var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Geladeira = require('../models').Geladeira;
var Produtos = require('../models').Produtos;
var Fileiras = require('../models').Fileiras;

router.post('/procurar', (req,res,next) =>{
	
	let localidade = req.body.localidade

	console.log(localidade);
    let instrucaoSql = `select idGeladeira from geladeira where fkLocalidadegeladeira =${localidade}`;

    sequelize.query(instrucaoSql,{ 
		model: Geladeira,
		mapToModel: true 
	}).then(resultado => {
		console.log(resultado);
		res.json(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
})

router.post('/fileiras', (req,res,next) =>{
	
	let idGeladeira = req.body.geladeira
	
    let instrucaoSql = `select nomeProduto,sensor3,sensor2,sensor1 from geladeira,produtos ,fileiras where idGeladeira = ${idGeladeira} and idGeladeira = fkGeladeiraFileira and idProduto = fkProduto;`;

    sequelize.query(instrucaoSql,{ 
		model: Geladeira,Fileiras,Produtos,
		mapToModel: true 
	}).then(resultado => {
		console.log(resultado);
		res.json(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
})


module.exports = router;