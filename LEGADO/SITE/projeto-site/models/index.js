'use strict';

var fs        = require('fs');
var path      = require('path');
var Sequelize = require('sequelize');
var basename  = path.basename(__filename);
var env       = process.env.NODE_ENV || 'development';
var config    = require(__dirname + '/../config/config.js')[env];
var mysql = require(__dirname + '/../config/mySql.js')[env]
var db        = {};

console.warn(`\n===> env: ${env}\n`);

if (config.use_env_variable) {
  var sequelize = new Sequelize(process.env[config.use_env_variable], config);
} else {
  var sequelize = new Sequelize(config.database, config.username, config.password, config);
}

var sequelize2 = new Sequelize(mysql.database,mysql.username,mysql.password,mysql )

fs
  .readdirSync(__dirname)
  .filter(file => {
    return (file.indexOf('.') !== 0) && (file !== basename) && (file.slice(-3) === '.js');
  })
  .forEach(file => {
    if(file != 'avaliacoes.js'){
      var model = sequelize['import'](path.join(__dirname, file));
      db[model.name] = model;
    }
    else{
      var model = sequelize2['import'](path.join(__dirname, file));
      db[model.name] = model;
    }
  });

Object.keys(db).forEach(modelName => {
  if (db[modelName].associate) {
    db[modelName].associate(db);
  }
});

db.sequelize = sequelize;
db.Sequelize = Sequelize;
db.sequelize2 = sequelize2

module.exports = db;
