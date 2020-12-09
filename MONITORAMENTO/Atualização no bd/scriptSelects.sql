use coldstock;

select nomeComponente from maquinas 
inner join configuracaoMaquina on idMaquina = fkMaquina
inner join componentes on idComponente = fkComponente
where idMaquina = 2;

select idChamado, idRegistro, fkComponente, valor 
from registros, chamados 
where fkChamado = idChamado and fkMaquina = 1 order by idChamado;

select idComponente, nomeComponente, capacidadeMax, porcentagemMax 
from componentes, configuracaoMaquina 
where fkcomponente = idComponente and fkMaquina = 1
order by fkMaquina;

select * from registros where fkMaquina = 1 order by idRegistro;

select * from registros;

select * from componentes;

select idMaquina, nomeMaquina, nomeComponente, capacidadeMax, metrica from maquinas 
inner join configuracaoMaquina on idMaquina = fkMaquina
inner join componentes on idComponente = fkComponente
where idMaquina = 2;

select * from configuracaoMaquina;

select dataHora, valor, nomeComponente, fkChamado from coldstock.registros INNER JOIN coldstock.componentes on fkComponente = idComponente where fkMaquina = 1 order by dataHora;

select max(fkchamado) from registros where fkmaquina = 1;

select * from configuracaoMaquina where fkmaquina = 1;

update configuracaoMaquina set capacidadeMax = 1000, porcentagemMax = 20 where fkmaquina = 1 and fkComponente = 4;