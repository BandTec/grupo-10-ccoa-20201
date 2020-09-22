var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Geladeiras = require('../models').Geladeiras;
var Geladeira = require('../models').Geladeira;
var Produtos = require('../models').Produtos;
var Abastecimento = require('../models').Abastecimento;
var Fileiras = require('../models').Fileiras;
var Localidade = require('../models').Localidade;
var Fornecedoras = require('../models').Fornecedoras;

router.post('/procurar', (req,res,next) =>{
	
	let idGeladeira = req.body.Geladeira;
	
    let instrucaoSql = `SELECT idProduto, nomeProduto, count(idAbastecimento) as qtd_abastecimento from produtos, abastecimento, fileiras, geladeira where idproduto = fileiras.fkproduto and idgeladeira = fkGeladeiraFileira and idfileiras = fkFileiras and idgeladeira = ${idGeladeira} group by nomeProduto, idproduto;`;

    sequelize.query(instrucaoSql,{ 
		model: Abastecimento,Produtos,Geladeiras,Fileiras,
		mapToModel: true 
	}).then(resultado => {
		console.log(resultado[0].nomeProduto);
		res.json(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
})

router.post('/informacoes', (req,res,next) =>{
	
	let idGeladeira = req.body.Geladeira;
	
    let instrucaoSql = `SELECT nomeLocalidade from localidades, geladeira where idGeladeira = ${idGeladeira} and fklocalidadeGeladeira = idLocalidade;`;

    sequelize.query(instrucaoSql,{ 
		model: Geladeiras,Localidade,
		mapToModel: true 
	}).then(resultado => {
		console.log(resultado[0]);
		res.json(resultado[0]);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
})

router.post('/data', (req,res,next) =>{
	let idGeladeira = req.body.geladeira;
	let inicio = req.body.parametros.inicio;
	let fim = req.body.parametros.fim;
	let instrucaoSql = `SELECT nomeProduto, count(idAbastecimento) as qtd_abastecimento from produtos, abastecimento, fileiras, geladeira where idproduto = fileiras.fkproduto and idgeladeira = fkGeladeiraFileira and idfileiras = fkFileiras and idgeladeira = ${idGeladeira} and dataAbastecimento BETWEEN '2020-${inicio}' and '2020-${fim}' group by nomeProduto ;`;

    sequelize.query(instrucaoSql,{ 
		model: Geladeiras,Localidade,Produtos,Abastecimento,
		mapToModel: true 
	}).then(resultado => {
		console.log(resultado[0]);
		res.json(resultado[0]);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
})

router.post('/produtos', (req,res,next) =>{
	let idGeladeira = req.body.Geladeira;
	let idProduto = req.body.Produto;
	
    let instrucaoSql = `select dataAbastecimento, nivelSensor, idFileiras, nomeProduto from produtos, abastecimento, fileiras, geladeira where idproduto = fileiras.fkproduto and idgeladeira = fkGeladeiraFileira and idfileiras = fkFileiras and idgeladeira = ${idGeladeira} and idProduto = ${idProduto} ORDER BY dataAbastecimento DESC;`;

    sequelize.query(instrucaoSql,{ 
		model: Abastecimento,Produtos,Geladeiras,Fileiras,
		mapToModel: true 
	}).then(resultado => {
		console.log(resultado[0]);
		res.json(resultado);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
	});
})

router.post('/atual', (req,res,next) =>{
	let idGeladeira = req.body.geladeira
	
    let instrucaoSql = `select idFileiras,nomeProduto,sensor3,sensor2,sensor1 from geladeira,produtos ,fileiras where idGeladeira = ${idGeladeira} and idGeladeira = fkGeladeiraFileira and idProduto = fkProduto;`;

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