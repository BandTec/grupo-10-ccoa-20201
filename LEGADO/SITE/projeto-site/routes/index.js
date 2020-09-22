var express = require('express');
const app = express();
var router = express.Router();

app.use(express.static('img'));
app.use(express.static('stylesheets'));

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

module.exports = router;
