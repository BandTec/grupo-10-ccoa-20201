create database coldStock;
use coldStock;

create table consumidoresFinais(
	emailConsumidor varchar(30) primary key,
    senhaConsumidor varchar(15),
    nomeConsumidor varchar(40),
    fkLocalidade int
);

create table funcionarios(
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
	idAbastecimento int primary key auto_increment,
    nivelSensor char(5),
    dataAbastecimento date,
    fkFornecedora int,
    fkFileira int,
    check (nivelSensor ='medio' or nivelSensor ='vazio')
);


alter table consumidoresFinais add foreign key (fkLocalidade) references localidades(idLocalidade);
alter table funcionarios add foreign key (fkFornecedora) references fornecedoras(idFornecedora);
alter table geladeiras add foreign key (fkLocador) references localidades(idLocalidade);
alter table geladeiras add foreign key (fkDono) references fornecedoras(idFornecedora);
alter table fileiras add foreign key (fkGeladeira) references geladeiras(idGeladeira);
alter table fileiras add foreign key (fkProduto) references produtos(idProduto);
alter table abastecimentos add foreign key (fkFornecedora) references fornecedoras(idFornecedora);
alter table abastecimentos add foreign key (fkFileira) references fileiras(idFileira);


insert into fornecedoras values
(null,'HassleFree Food'),
(null,'Joaninha Food'),
(null,'Food Guerrilla');

insert into localidades values
(null,'OrbiPlan','Camaçari','Vila Alve Verde','Rua Cristo Redentor'),
(null,'EE João Carlos de Almeida','São Paulo','Jardins','Rua Conde de Alvor'),
(null,'Mercurio','Belém','Vila do Café','Avenida Dezesseis de Novembro');

insert into funcionarios values
('giovanna.pereira@gmail.com','giovanna743','Giovanna Oliveira Pereira',1),
('gabrielle.cunha@gmail.com','gabrielle5743','Gabrielle Almeida Cunha',1),
('rebeca.pereira@gmail.com','rebecaRP356','Rebeca Rocha Pereira',2),
('mateus.alves@gmail.com','mateus79834','Mateus Pereira Alves',3);

insert into consumidoresFinais values
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
(null,'medio','2020-04-14',1,2),
(null,'vazio','2020-04-14',1,1),
(null,'vazio','2020-04-14',2,3),
(null,'vazio','2020-03-25',1,2),
(null,'medio','2020-03-25',3,4),
(null,'vazio','2020-04-04',2,5);




select nomeConsumidor,emailConsumidor,nomelocalidade from consumidoresFinais, localidades where fkLocalidade = idLocalidade;

select nomeFuncionario,emailFuncionario,nomeFornecedora from funcionarios, fornecedoras where fkFornecedora = idFornecedora;

select nomeProduto, idGeladeira, nomeLocalidade, idFileira, dataabastecimento from produtos, geladeiras,localidades, fileiras,abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira;