-- drop database coldstock;
create database coldstock;
use coldstock;

create table localidades(
	idLocalidade int primary key auto_increment,
    nomeLocalidade varchar(40),
    cidade varchar(40),
    bairro varchar(20),
    rua varchar(40)
);

create table maquinas(
	idMaquina int primary key auto_increment,
    nomeMaquina varchar(30),
    tipoMaquina varchar(30)
);

create table fornecedoras(
	idFornecedora int primary key auto_increment,
    nomeFornecedora varchar(20),
    email varchar(50),
    senha varchar(30),
    fkMaquina int,
    foreign key (fkMaquina) references maquinas(idMaquina)
);

create table consumidoresFinais(
	idConsumidor  int primary key auto_increment,
	emailConsumidor varchar(30),
    senhaConsumidor varchar(15),
    nomeConsumidor varchar(40),
    fkLocalidade int,
    foreign key (fkLocalidade) references localidades(idLocalidade)
);

create table avaliacoes(
	idAvaliacao int primary key auto_increment,
    texto varchar(200),
    fkConsumidores int,
    foreign key (fkConsumidores) references consumidoresFinais(idConsumidor)
);

create table geladeiras(
	idGeladeira int primary key auto_increment,
    fkLocador int,
    fkDono int,
    foreign key (fkLocador) references localidades(idLocalidade),
    foreign key (fkDono) references fornecedoras(idFornecedora)
);

create table produtos(
	idProduto int primary key auto_increment,
    nomeProduto varchar(20)
);

create table fileiras(
	idFileira int primary key auto_increment,
    sensorCheio boolean,
    sensorMedio boolean,
    sensorVazio boolean,
    fkGeladeira int,
    fkProduto int,
    foreign key (fkGeladeira) references geladeiras(idGeladeira),
    foreign key (fkProduto) references produtos(idProduto)
);

create table abastecimentos(
	idAbastecimento int primary key auto_increment,
    nivelSensor char(5),
    dataAbastecimento date,
    fkFornecedora int,
    fkFileira int,
    check (nivelSensor ='medio' or nivelSensor ='vazio'),
    foreign key (fkFornecedora) references fornecedoras(idFornecedora),
    foreign key (fkFileira) references fileiras(idFileira)
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
    porcentagemMax int,
    foreign key (fkMaquina) references maquinas(idMaquina),
    foreign key (fkComponente) references componentes(idComponente)
);

create table chamados(
	idChamado int primary key auto_increment,
    dataChamado datetime,
    descricao varchar(1000)
);

create table registros(
	idRegistro int primary key auto_increment,
    dataHora datetime,
    valor float,
    fkComponente int,
    fkMaquina int,
    fkChamado int,
    foreign key (fkComponente) references componentes(idComponente),
    foreign key (fkMaquina) references maquinas(idMaquina),
    foreign key (fkChamado) references chamados(idChamado)
);

create table favoritos(
	fkProduto int,
    fkConsumidor int,
    foreign key (fkProduto) references produtos(idProduto),
    foreign key (fkConsumidor) references consumidoresFinais(idConsumidor)
);

create table funcionarios(
	idFuncionario int primary key auto_increment,
	emailFuncionario varchar(45),
    senhaFuncionario varchar(30),
    nomeFuncionario varchar(45),
    fkMaquina int,
    foreign key (fkMaquina) references maquinas(idMaquina)
);

insert into localidades values
(null,'OrbiPlan','Camaçari','Vila Alve Verde','Rua Cristo Redentor'),
(null,'EE João Carlos de Almeida','São Paulo','Jardins','Rua Conde de Alvor'),
(null,'Mercurio','Belém','Vila do Café','Avenida Dezesseis de Novembro');

insert into maquinas values
(null, "Servidor 1", "Server"),
(null,"Maquina 1","PC"),
(null,"Maquina 2","PC");

insert into fornecedoras values
(null, 'HassleFree Food', 'HFFood@gmail.com', 'senha123',1),
(null, 'Joaninha Food', 'JoanaFood@gmail.com', 'senha456',1),
(null, 'Food Guerrilla', 'GuerrilaFood@gmail.com', 'batman',2);

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
(1, 2, '16', 80),
(1, 3, '2000', 90),
(1, 4, '1000', 90),
(1, 5, '800', 60),
(1, 6, '90', 100),
(2, 1, '1.8', 85),
(2, 2, '6', 74),
(2, 3, '240', 87),
(3, 1, '2.5', 65),
(3, 2, '8', 80);

insert into chamados (idChamado, dataChamado, descricao) values
(null, '2020-09-23 17:45:00', 'CPU sobrecarregando!'),
(null, '2020-09-23 17:50:00', 'CPU sobrecarregando!'),
(null, '2020-09-23 17:52:00', 'CPU sobrecarregando!'),
(null, '2020-09-23 17:55:00', 'CPU sobrecarregando!'),
(null, '2020-09-23 17:57:00', 'CPU sobrecarregando!'),
(null, '2020-09-23 17:59:00', 'CPU sobrecarregando!');

insert into registros (idRegistro, dataHora, valor, fkMaquina, fkComponente) values
(null, '2020-09-23 17:45:00', 3.24, 1, 1), 	
(null, '2020-09-23 17:45:00', 8.1, 1, 2),
(null, '2020-09-23 17:45:00', 200.00, 1, 3),
(null, '2020-09-23 17:45:00', 658.32, 1, 4),
(null, '2020-09-23 17:45:00', 600.02, 1, 5),
(null, '2020-09-23 17:45:00', 76.5, 1, 6),

(null, '2020-09-23 17:50:00', 3.43, 1, 1),		
(null, '2020-09-23 17:50:00', 8.6, 1, 2),
(null, '2020-09-23 17:50:00', 203.50, 1, 3),
(null, '2020-09-23 17:50:00', 705.47, 1, 4),
(null, '2020-09-23 17:50:00', 582.07, 1, 5),
(null, '2020-09-23 17:50:00', 73.3, 1, 6),

(null, '2020-09-23 17:52:00', 3.28, 1, 1),		
(null, '2020-09-23 17:52:00', 8.6, 1, 2),
(null, '2020-09-23 17:52:00', 205.00, 1, 3),
(null, '2020-09-23 17:52:00', 780.25, 1, 4),
(null, '2020-09-23 17:52:00', 605.70, 1, 5),
(null, '2020-09-23 17:52:00', 78.3, 1, 6),

(null, '2020-09-23 17:55:00', 2.9, 1, 1),		
(null, '2020-09-23 17:55:00', 5.1, 1, 2),
(null, '2020-09-23 17:55:00', 203.50, 1, 3),
(null, '2020-09-23 17:55:00', 800.47, 1, 4),
(null, '2020-09-23 17:55:00', 580.07, 1, 5),
(null, '2020-09-23 17:55:00', 58, 1, 6),

(null, '2020-09-23 17:57:00', 3.1, 1, 1),
(null, '2020-09-23 17:57:00', 4.5, 1, 2),
(null, '2020-09-23 17:57:00', 203.50, 1, 3),
(null, '2020-09-23 17:57:00', 705.47, 1, 4),
(null, '2020-09-23 17:57:00', 579.50, 1, 5),
(null, '2020-09-23 17:57:00', 60, 1, 6),

(null, '2020-09-23 17:59:00', 2.8, 1, 1), 
(null, '2020-09-23 17:59:00', 8.3, 1, 2),
(null, '2020-09-23 17:59:00', 203.50, 1, 3),
(null, '2020-09-23 17:59:00', 700.98, 1, 4),
(null, '2020-09-23 17:59:00', 578.99, 1, 5),
(null, '2020-09-23 17:59:00', 59, 1, 6);

insert into favoritos values
(1,1),
(3,2),
(2,2),
(4,3),
(5,2),
(1,2);

insert into funcionarios values
(null,'user@email.com','user123','usuario',3),
(null,'giovanna.pereira@gmail.com','giovanna743','Giovanna Oliveira Pereira',1),
(null,'gabrielle.cunha@gmail.com','gabrielle5743','Gabrielle Almeida Cunha',1),
(null,'rebeca.pereira@gmail.com','rebecaRP356','Rebeca Rocha Pereira',2),
(null,'mateus.alves@gmail.com','mateus79834','Mateus Pereira Alves',3),
(null,'gabriel.gameiro@gmail.com','senha123','Gabriel Gameiro',1);
