from services.sqlApp import ClsSql

print("Conectando ao BD")
mysql = ClsSql('ColdUser', 'senha123', 'localhost', 'coldstock')

idMaquina = input(" Digite o id da máquina ")
# idMaquina = 1

data = input(" Digite a data de inicio para geração de dados (YYYY/MM/DD) ")

#RETORNO: [[id, nome, maxima], [1, 'cpu', 2.5], [2, 'ram', 8]]
configuracaoMaquina = mysql.consultarConfiguracao(idMaquina)