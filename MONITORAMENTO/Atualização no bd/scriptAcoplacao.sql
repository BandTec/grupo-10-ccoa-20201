use coldstock;

create table servidores (
	idServidor int primary key auto_increment,
    nomeServidor varchar(45),
    qtdRAM float (6),
    qtdDisco float (10),
    qtdCPU float (4)
);

CREATE TABLE registros( 
    idRegistro int NOT NULL AUTO_INCREMENT, 
    fkServidor int NOT NULL,
    CPU float(5) NOT NULL, 
    RAM float(5) NOT NULL, 
    RAMpercent float(4) NOT NULL, 
    diskU float(12) NOT NULL, 
    diskL float(12) NOT NULL, 
    dataHora datetime NOT NULL, 
    PRIMARY KEY (idRegistro),
    foreign key (fkServidor) references servidores(idServidor)
    );
    
alter table fornecedoras add column 
fkServidor int;
    
alter table fornecedoras add foreign key 
(fkServidor) references servidores(idServidor);

insert into servidores values (null, 'Server 3232', 4.0, 120.00, 1.8);