create database coldStock;
use coldStock;
drop database coldStock;

create table fornecedoras(
	idFornecedora int primary key auto_increment,
    nomeFornecedora varchar(20),
    emailFornecedora varchar(30),
    senhaFornecedora varchar(15)
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
    fkfornecedoraGeladeira int,
    fklocalidadeGeladeira int,
    foreign key (fkfornecedoraGeladeira) references	fornecedoras(idfornecedora),
    foreign key (fklocalidadegeladeira) references	localidades(idLocalidade)
);

create table consumidoresFinais(
	idConsumidor  int primary key auto_increment,
	emailConsumidor varchar(30),
    senhaConsumidor varchar(15),
    nomeConsumidor varchar(40),
    fkLocalidadeconsumidor int,
    foreign key (fklocalidadeconsumidor) references	localidades(idLocalidade)
);

create table produtos(
	idProduto int primary key auto_increment,
    nomeProduto varchar(20)
);

create table fileiras(
	idFileira int primary key auto_increment,
    sensor3 boolean,
    sensor2 boolean,
    sensor1 boolean,
    fkGeladeirafileira int,
    fkProduto int,
    foreign key (fkgeladeirafileira) references	geladeiras(idgeladeira),
    foreign key (fkproduto) references	produtos(idproduto)
);

create table abastecimentos(
	idAbastecimento int primary key auto_increment,
    nivelSensor char(5),
    dataAbastecimento date,
    fkFornecedora int,
    fkFileira int,
    check (nivelSensor ='medio' or nivelSensor ='vazio'),
     foreign key (fkfornecedora) references fornecedoras(idfornecedora),
     foreign key (fkfileira) references fileiras(idfileira)
);

insert into fornecedoras values
(null,'HassleFree Food','hassleFreefood@gmail.com','avatar447'),
(null,'Joaninha Food','joaninhafood@gmail.com','naruto123'),
(null,'Food Guerrilla','foodguerrilha@gmail.com','chaves4321');

insert into localidades values
(null,'OrbiPlan','Camaçari','Vila Alve Verde','Rua Cristo Redentor'),
(null,'EE João Carlos de Almeida','São Paulo','Jardins','Rua Conde de Alvor'),
(null,'Mercurio','Belém','Vila do Café','Avenida Dezesseis de Novembro');

insert into consumidoresFinais values
(null,'vitoria.cavalcanti@gmail.com','vitoria345','Vitória Cunha Cavalcanti',2),
(null,'lana.correia@gmail.com','lanaDias435','Lana Dias Correia',3),
(null,'gabrielly.castro@gmail.com','gabrielly3342','Gabrielly Santos Castro',3),
(null,'rebeca.cardoso@gmail.com','rebeca3094','Rebeca Rodrigues Cardoso',1),
(null,'felipe.pereira@gmail.com','felipe879','Felipe Ferreira Pereira',3);

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

select nomeProduto, idGeladeira, nomeLocalidade, idFileira, dataabastecimento from produtos, geladeiras,localidades, fileiras,abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira and nomeLocalidade='EE João Carlos de Almeida';

select  nomeProduto, idFileira, dataabastecimento, nivelSensor from produtos, geladeiras,localidades, fileiras,abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira and idLocalidade=1 and idProduto = 1;

select  nomeProduto, idFileira, dataabastecimento, nivelSensor, count(idAbastecimento) from produtos, geladeiras,localidades, fileiras,abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira and idLocalidade=1 and abastecimentos.fkFileira = idFileira and idProduto = 2;

select  idGeladeira, idProduto, nomeProduto, count(idAbastecimento) from produtos, geladeiras,localidades, fileiras,abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira and idLocalidade=2 and abastecimentos.fkFileira = idFileira group by idProduto order by idProduto;

select  idGeladeira, nomeProduto, count(idAbastecimento) from produtos, geladeiras,localidades, fileiras,abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira and idLocalidade=2 and abastecimentos.fkFileira = idFileira  and dataAbastecimento between '2020-03-01' and '2020-03-30' group by idProduto order by idProduto;