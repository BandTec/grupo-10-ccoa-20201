import mysql.connector
from datetime import datetime

class Mysql:
    def __init__(self, user, password, host, database):
        self.user = user
        self.password = password
        self.host = host
        self.database = database
        self.objSql = None
        self.cursor = None

    #Estabelecendo uma conexão
    def connect(self):
        try:
            self.objSql = mysql.connector.connect(
            user=self.user, password=self.password, host=self.host, database=self.database)
            #Criando cursor para manipulação do banco.
            print(self.objSql)
            self.cursor = self.objSql.cursor()
        except Exception as err:
            print(err)
            raise

    def listarComponente(self, idServer):
        query = (
            "select nomeComponente, porcentagemMax, idComponente from maquinas "
            "inner join configuracaoMaquina on idMaquina = fkMaquina "
            "inner join componentes on idComponente = fkComponente "
            "where idMaquina = %s " % (idServer)
        )
        try:
            print('Selecionando dados do server ID: ', idServer)
            self.cursor.execute(query)
            retorno = self.cursor.fetchall()
            print('Retorno do BD: ', retorno)
            print('Numero de registros:', len(retorno))
            self.objSql.commit()
            return retorno
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()

    def insert(self,listaInsert):
        query = "insert into registros (dataHora, valor, fkMaquina, fkComponente) values (%s,%s,%s,%s)"
        try:
            print('Aguarde ...')
            self.cursor.executemany(query,listaInsert)
            self.objSql.commit()
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()

    # Fechando conexão
    def close(self):
        self.objSql.close()

        


