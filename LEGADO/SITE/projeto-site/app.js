process.env.NODE_ENV = 'production';

var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usuariosRouter = require('./routes/usuarios');
var leiturasRouter = require('./routes/leituras');
var produtosRouter = require('./routes/produtos');
var geladeirasRouter = require('./routes/geladeiras');
var avaliacoesRouter = require('./routes/avaliacoes');

var app = express();

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/usuarios', usuariosRouter);
app.use('/leituras', leiturasRouter);
app.use('/produtos', produtosRouter);
app.use('/geladeiras', geladeirasRouter);
app.use('/avaliacoes', avaliacoesRouter)
module.exports = app;
