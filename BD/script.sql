create database coldStock;
use coldStock;

create table consumidorFinal(
	emailConsumidor varchar(30) primary key,
    senhaConsumidor varchar(15),
    nomeConsumidor varchar(40),
    fkLocalidade int
);

create table funcionario(
	emailFuncionario varchar(30) primary key,
    senhaFuncionario varchar(15),
    nomeFuncionario varchar(40),
    fkFornecedora int
);

create table fornecedoras(
	idFornecedora int primary key auto_increment,
    nomeFornecedora varchar(20)
);
create table localidades(
	idLocalidade int primary key auto_increment,
    nomeLocalidade varchar(40),
    cidade varchar(40),
    bairro varchar(20),
    rua varchar(40)
);

create table geladeiras(
	idGeladeira int primary key auto_increment,
    fkLocador int,
    fkDono int
);

create table fileiras(
	idFileira int primary key auto_increment,
    sensorCheio boolean,
    sensorMedio boolean,
    sensorVazio boolean,
    fkGeladeira int,
    fkProduto int
);

create table produtos(
	idProduto int primary key auto_increment,
    nomeProduto varchar(20)
);

create table abastecimentos(
	dataAbastecimento date,
    situacaoSensor varchar(10),
    fkFileira int,
    fkFornecedor int
);

alter table consumidorFinal add foreign key (fkLocalidade) references localidades(idLocalidade);
alter table funcionario add foreign key (fkFornecedora) references fornecedoras(idFornecedora);
alter table geladeiras add foreign key (fkLocador) references localidades(idLocalidade);
alter table geladeiras add foreign key (fkDono) references fornecedoras(idFornecedora);
alter table fileiras add foreign key (fkGeladeira) references geladeiras(idGeladeira);
alter table fileiras add foreign key (fkProduto) references produtos(idProduto);
alter table abastecimentos add foreign key (fkFornecedor) references fornecedoras(idFornecedora);

insert into fornecedoras values
(null,'HassleFree Food'),
(null,'Joaninha Food'),
(null,'Food Guerrilla'),
(null,'Dr.Food');

insert into localidades values
(null,'OrbiPlan','Camaçari','Vila Alve Verde','Rua Cristo Redentor'),
(null,'EE João Carlos de Almeida','São Paulo','Jardins','Rua Conde de Alvor'),
(null,'Mercurio','Belém','Vila do Café','Avenida Dezesseis de Novembro');

insert into funcionario values
('giovanna.pereira@gmail.com','giovanna743','Giovanna Oliveira Pereira',1),
('gabrielle.cunha@gmail.com','gabrielle5743','Gabrielle Almeida Cunha',1),
('rebeca.pereira@gmail.com','rebecaRP356','Rebeca Rocha Pereira',2),
('mateus.alves@gmail.com','mateus79834','Mateus Pereira Alves',3);

insert into consumidorFinal values
('vitoria.cavalcanti@gmail.com','vitoria345','Vitória Cunha Cavalcanti',2),
('lana.correia@gmail.com','lanaDias435','Lana Dias Correia',3),
('gabrielly.castro@gmail.com','gabrielly3342','Gabrielly Santos Castro',3),
('rebeca.cardoso@gmail.com','rebeca3094','Rebeca Rodrigues Cardoso',1),
('felipe.pereira@gmail.com','felipe879','Felipe Ferreira Pereira',3);

insert into geladeiras values
(null,1,1),
(null,1,1),
(null,2,3),
(null,2,3),
(null,3,2),
(null,3,2);

insert into produtos values
(null,'Achocolatado'),
(null,'Sanduiche de Frango'),
(null,'Bebida Energética'),
(null,'Sanduiche Natural'),
(null,'Lata de Guaraná'),
(null,'Garrafa de Água'),
(null,'Salada de Frutas');

insert into fileiras values
(null,1,1,1,1,2),
(null,0,1,1,1,1),
(null,0,0,1,2,3),
(null,0,1,1,1,2),
(null,1,1,1,2,7),
(null,0,0,1,3,5),
(null,1,1,1,2,4),
(null,0,0,0,3,4),
(null,1,1,1,3,6),
(null,0,1,1,2,6),
(null,1,1,1,1,3),
(null,0,0,1,2,5);

insert into abastecimentos values
('2020-04-14','vazio',1,1),
('2020-04-14','meio',2,1),
('2020-04-14','vazio',3,3),
('2020-03-25','meio',1,1),
('2020-05-16','vazio',7,3),
('2020-05-16','vazio',1,2),
('2020-02-24','cheio',5,3),
('2020-04-04','vazio',6,2);

select nomeConsumidor,emailConsumidor,nomelocalidade from consumidorFinal, localidades where fkLocalidade = idLocalidade;

select nomeFuncionario,emailFuncionario,nomeFornecedora from funcionario, fornecedoras where fkFornecedora = idFornecedora;

select nomeProduto, idGeladeira, nomeLocalidade, idFileira, situacaoSensor from produtos, geladeiras,localidades, fileiras, abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira;