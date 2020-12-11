const sequelize = require('../models/').sequelize2;
var express = require('express');
var router = express.Router();
var modelo = require('../models/').avaliacoes;


router.post('/comentar/:idConsumidor', (req,res,next) =>{
	
	
    //let instrucaoSql = `insert into avaliacoes() values(null, '${texto}', ${idConsumidor})`;

	modelo.create({
		texto: req.body.avaliacao,
        fkConsumidores:req.params.idConsumidor,
	}).then(resultado => {
		console.log(`Registro criado: ${resultado}`)
        res.send(resultado);
    }).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
    // sequelize.query(instrucaoSql,{ 
	// 	model: modelo,
	// 	mapToModel: false 
	// }).then(resultado => {
	// 	console.log(resultado[0]);
	// 	res.json(resultado);
	// }).catch(erro => {
	// 	console.error(erro);
	// 	res.status(500).send(erro.message);
	// });
})

module.exports = router;