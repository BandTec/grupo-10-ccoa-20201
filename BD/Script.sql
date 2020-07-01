use bdColdStock;


  
create table localidades(
	idLocalidade int identity primary key,
	nomeLocalidade varchar(45),
	cidade varchar(45),
	bairro varchar(45)
);

create table consumidores(
	idConsumidor int identity primary key,
	nomeConsumidor varchar(45),
	senhaConsumidor varchar(16),
	emailConsumidor varchar(45),
	fkLocalidadeConsumidor int,
	foreign key (fkLocalidadeConsumidor) references localidades(idLocalidade)
);

create table fornecedoras(
	idFornecedora int identity primary key,
	nomeFornecedora varchar(45),
	senhaFornecedora varchar(20),
	emailFornecedora varchar(45)
);


create table produtos(
	idProduto int identity primary key,
	nomeProduto varchar(45)
);

create table geladeira(
	idGeladeira int identity primary key,
	fkFornecedorasGeladeira int,
	foreign key (fkFornecedorasGeladeira) references fornecedoras(idFornecedoras),
	fkLocalidadeGeladeira int,
	foreign key (fkLocalidadeGeladeira) references localidades(idLocalidade)
);

create table fileiras(
	idFileiras int identity primary key,
	fkGeladeiraFileira int,
	foreign key (fkGeladeiraFileira) references geladeira(idGeladeira),
	sensor3 int,
	sensor2 int,
	sensor1 int,
	fkProduto int,
	foreign key (fkProduto) references produtos(idProduto)
);


create table abastecimento(
	idAbastecimento int identity primary key,
	nivelSensor varchar(45),
	dataAbastecimento datetime,
	fkFornecedorasAbastecimento int,
	foreign key (fkFornecedorasAbastecimento) references fornecedoras(idFornecedoras),
	fkFileiras int,
	foreign key (fkFileiras) references fileiras (idFileiras)
);



insert into fornecedoras(nomeFornecedora, senhaFornecedora, emailFornecedora) values
('HassleFree Food','adminh','adminH@gmail.com'),
('Joaninha Food','joaninhaadmin','joaninhaFood@gmail.com'),
('Food Guerrilla','guerrillaadmin','food_guerrilla@gmail.com');

insert into localidades (nomeLocalide, cidade, bairro, rua) values
('OrbiPlan','Camaçari','Vila Alve Verde','Rua Cristo Redentor'),
('EE João Carlos de Almeida','São Paulo','Jardins','Rua Conde de Alvor'),
('Mercurio','Belém','Vila do Café','Avenida Dezesseis de Novembro');

insert into consumidores (nomeConsumidor, senhaConsumidor, emailConsumidor,	fkLocalidadeConsumidor) values
('Vitória Cunha Cavalcanti','consumidor1','consumidor@gmail.com',2),
('Lana Dias Correia','lanaDias435','lana.correia@gmail.com',3),
('Gabrielly Santos Castro','gabrielly3342','gabrielly.castro@gmail.com',3),
('Rebeca Rodrigues Cardoso','rebeca3094','rebeca.cardoso@gmail.com',1),
('Felipe Ferreira Pereira','felipe879','felipe.pereira@gmail.com',3);

insert into geladeira (fkFornecedorasGeladeira, fkLocalidadeGeladeira) values
(1,1),
(1,1),
(2,3),
(2,3),
(3,2),
(3,2);

insert into produtos (nomeProduto) values
('Achocolatado'),
('Sanduiche de Frango'),
('Bebida Energética'),
('Sanduiche Natural'),
('Lata de Guaraná'),
('Garrafa de Água'),
('Salada de Frutas');

insert into fileiras (fkGeladeiraFileira, sensor3, sensor2,	sensor1, fkProduto) values
(1,1,1,1,2),
(1,0,1,1,1),
(2,0,0,1,3),
(1,0,1,1,2),
(2,1,1,1,7),
(3,0,0,1,5),
(2,1,1,1,4),
(3,0,0,0,4),
(3,1,1,1,6),
(2,0,1,1,6),
(1,1,1,1,3),
(2,0,0,1,5);

insert into fileiras (fkGeladeiraFileira, sensor3, sensor2,	sensor1, fkProduto) values
(5,1,1,1,2),
(5,0,1,1,1),
(5,0,0,1,3),
(5,0,1,1,2),
(6,1,1,1,7),
(6,0,0,1,5),
(5,1,1,1,4),
(6,0,0,0,4),
(6,1,1,1,6),
(5,0,1,1,6),
(6,1,1,1,3),
(5,0,0,1,5);



insert into abastecimento (nivelSensor, dataAbastecimento,	fkFornecedorasAbastecimento, fkFileiras) values
('medio','2020-04-14 12:30:46',1,2),
('medio','2020-04-30 14:05:46',1,2),
('vazio','2020-03-20 11:23:46',1,2),
('vazio','2020-04-14 13:30:06',1,1),
('vazio','2020-04-14 12:30:46',2,3),
('vazio','2020-03-25 15:45:46',1,2),
('medio','2020-03-25 00:30:46',3,4),
('vazio','2020-04-04 12:45:46',2,5);



select nomeLocalidade,idGeladeira from localidades,geladeira where idLocalidade= 2 and idLocalidade = fklocalidadeGeladeira;

SELECT idproduto, nomeProduto, count(idAbastecimento) as qtd_abastecimento from produtos, abastecimento, fileiras, geladeira where idproduto = fileiras.fkproduto and idgeladeira = fkGeladeiraFileira and idfileiras = fkFileiras and idgeladeira = 1 group by nomeProduto, idproduto;

select idProduto, nomeProduto from produtos, abastecimento, fileiras, geladeira where idproduto = fileiras.fkproduto and idgeladeira = fkGeladeiraFileira and idfileiras = fkFileiras and idgeladeira = 1;

SELECT dataAbastecimento, nomeProduto, count(idAbastecimento) as qtd_abastecimento from produtos, abastecimento, fileiras, geladeira where idproduto = fileiras.fkproduto and idgeladeira = fkGeladeiraFileira and idfileiras = fkFileiras and idgeladeira = 1 and dataAbastecimento BETWEEN '2020-03-01' and '2020-03-31' group by nomeProduto, dataAbastecimento ;

select dataAbastecimento, nivelSensor, idFileiras, nomeProduto from produtos, abastecimento, fileiras, geladeira where idproduto = fileiras.fkproduto and idgeladeira = fkGeladeiraFileira and idfileiras = fkFileiras and idgeladeira = 1 and idProduto = 1;