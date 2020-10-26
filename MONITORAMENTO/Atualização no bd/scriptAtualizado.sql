-- drop database coldstock;
create database coldstock;
use coldstock;

create table consumidoresFinais(
	idConsumidor  int primary key auto_increment,
	emailConsumidor varchar(30),
    senhaConsumidor varchar(15),
    nomeConsumidor varchar(40),
    fkLocalidade int
);


create table fornecedoras(
	idFornecedora int primary key auto_increment,
    nomeFornecedora varchar(20),
    email varchar(50),
    senha varchar(30),
    fkMaquina int
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

create table Maquinas(
	idMaquina int primary key auto_increment,
    nomeMaquina varchar(30),
    tipoMaquina varchar(30)
);

create table componentes(
	idComponente int primary key auto_increment,
    nomeComponente varchar(45),
    metrica varchar(10)
);

create table configuracaoMaquina(
	fkMaquina int,
    fkComponente int,
    capacidadeMax float,
    porcentagemMax int
);

create table registros(
	idRegistro int primary key auto_increment,
    dataHora datetime,
    valor float,
    fkComponente int,
    fkMaquina int
);

create table favoritos(
	fkProduto int,
    fkConsumidor int
);

create table funcionarios(
	idFuncionario int primary key auto_increment,
	emailFuncionario varchar(45),
    senhaFuncionario varchar(30),
    nomeFuncionario varchar(45),
    fkMaquina int
);

alter table consumidoresFinais add foreign key (fkLocalidade) references localidades(idLocalidade);
alter table geladeiras add foreign key (fkLocador) references localidades(idLocalidade);
alter table geladeiras add foreign key (fkDono) references fornecedoras(idFornecedora);
alter table fileiras add foreign key (fkGeladeira) references geladeiras(idGeladeira);
alter table fileiras add foreign key (fkProduto) references produtos(idProduto);
alter table abastecimentos add foreign key (fkFornecedora) references fornecedoras(idFornecedora);
alter table abastecimentos add foreign key (fkFileira) references fileiras(idFileira);
alter table fornecedoras add foreign key (fkMaquina) references Maquinas(idMaquina);
alter table configuracaoMaquina add foreign key (fkMaquina) references Maquinas(idMaquina);
alter table configuracaoMaquina add foreign key (fkComponente) references componentes(idComponente);
alter table registros add foreign key (fkComponente) references componentes(idComponente);
alter table registros add foreign key (fkMaquina) references Maquinas(idMaquina);
alter table favoritos add foreign key (fkProduto) references produtos(idProduto);
alter table favoritos add foreign key (fkConsumidor) references consumidoresFinais(idConsumidor);
alter table funcionarios add foreign key (fkMaquina) references Maquinas(idMaquina);

insert into Maquinas values
(null, "Servidor 1", "Server"),
(null,"Maquina 1","PC"),
(null,"Maquina 2","PC");

insert into fornecedoras values
(null, 'HassleFree Food', 'HFFood@gmail.com', 'senha123',1),
(null, 'Joaninha Food', 'JoanaFood@gmail.com', 'senha456',1),
(null, 'Food Guerrilla', 'GuerrilaFood@gmail.com', 'batman',2);

insert into localidades values
(null,'OrbiPlan','Camaçari','Vila Alve Verde','Rua Cristo Redentor'),
(null,'EE João Carlos de Almeida','São Paulo','Jardins','Rua Conde de Alvor'),
(null,'Mercurio','Belém','Vila do Café','Avenida Dezesseis de Novembro');

insert into funcionarios values
(null,'user@email.com','user123','usuario',3),
(null,'giovanna.pereira@gmail.com','giovanna743','Giovanna Oliveira Pereira',1),
(null,'gabrielle.cunha@gmail.com','gabrielle5743','Gabrielle Almeida Cunha',1),
(null,'rebeca.pereira@gmail.com','rebecaRP356','Rebeca Rocha Pereira',2),
(null,'mateus.alves@gmail.com','mateus79834','Mateus Pereira Alves',3);

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

insert into componentes (idComponente, nomeComponente, Metrica) values
(null, 'CPU', 'GHz'),
(null, 'RAM', 'GB'),
(null, 'Disco', 'GB'),
(null, 'conexaoD', 'Mbps'),
(null, 'conexaoU', 'Mbps'),
(null, 'temperatura', '°C');

insert into configuracaoMaquina (fkMaquina, fkComponente, capacidadeMax, porcentagemMax) values 
(1, 1, '3.5', 80),
(1, 2, '16', 78),
(1, 3, '2000', 90),
(1, 4, '1000', 45),
(1, 5, '800', 60),
(2, 1, '1.8', 85),
(2, 2, '6', 74),
(2, 3, '240', 87),
(3, 1, '2.5', 65),
(3, 2, '8', 80),
(1, 6, '90', 100);

select * from Maquinas;

-- Temos o id da maquina onde a API está rodando
-- A maquina já está registrada e com componentes relacionados
-- idMaquina = 1
select nomeComponente from Maquinas 
inner join configuracaoMaquina on idMaquina = fkMaquina
inner join componentes on idComponente = fkComponente
where idMaquina = 1;

insert into registros (idRegistro, dataHora, valor, fkMaquina, fkComponente) values
(null, '2020-09-23 17:45:00', 2.0, 1, 1),
(null, '2020-09-23 17:45:00', 8.6, 1, 2),
(null, '2020-09-23 17:45:00', 203.50, 1, 3),
(null, '2020-09-23 17:45:00', 705.47, 1, 4),
(null, '2020-09-23 17:45:00', 582.07, 1, 5);

select * from registros where fkMaquina = 1;


select * from componentes;
select idMaquina, nomeMaquina, nomeComponente, capacidadeMax, metrica from Maquinas 
inner join configuracaoMaquina on idMaquina = fkMaquina
inner join componentes on idComponente = fkComponente
where idMaquina = 1;

-- select nomeConsumidor,emailConsumidor,nomelocalidade from consumidoresFinais, localidades where fkLocalidade = idLocalidade;

-- select nomeFuncionario,emailFuncionario,nomeFornecedora from funcionarios, fornecedoras where fkFornecedora = idFornecedora;

-- select nomeProduto, idGeladeira, nomeLocalidade, idFileira, dataabastecimento from produtos, geladeiras,localidades, fileiras,abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira and nomeLocalidade='EE João Carlos de Almeida';

-- select  nomeProduto, idFileira, dataabastecimento, nivelSensor from produtos, geladeiras,localidades, fileiras,abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira and idLocalidade=1 and idProduto = 1;

-- select  nomeProduto, idFileira, dataabastecimento, nivelSensor, count(idAbastecimento) from produtos, geladeiras,localidades, fileiras,abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira and idLocalidade=1 and abastecimentos.fkFileira = idFileira and idProduto = 2;

-- select  idGeladeira, idProduto, nomeProduto, count(idAbastecimento) from produtos, geladeiras,localidades, fileiras,abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira and idLocalidade=2 and abastecimentos.fkFileira = idFileira group by idProduto order by idProduto;

-- select  idGeladeira, nomeProduto, count(idAbastecimento) from produtos, geladeiras,localidades, fileiras,abastecimentos where idProduto = fkProduto and fkGeladeira = idGeladeira and fkFileira = idFileira and idLocalidade=2 and abastecimentos.fkFileira = idFileira  and dataAbastecimento between '2020-03-01' and '2020-03-30' group by idProduto order by idProduto;

-- select count(idAbastecimento),nomeProduto from geladeiras,fileiras,produtos,abastecimentos where idGeladeira = 1 and idGeladeira=fkgeladeira and idProduto = fkProduto and fkfileira = idFileira;

-- select * from abastecimentos;

