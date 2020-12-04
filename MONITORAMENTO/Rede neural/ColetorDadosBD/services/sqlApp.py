# -*- coding: UTF-8 -*-
import mysql.connector

class ClsSql:
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

    def listarComponentes(self):
        query = "select * from componentes; "

        try:
            print('Selecionando componentes cadastrados')
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

    def consultarChamados(self, idMaquina):
        query = (
            "select idChamado, idRegistro, fkComponente, valor "
            "from registros, chamados "
            "where fkChamado = idChamado and fkMaquina = %s;"
        )

        try:
            print('Selecionando dados da maquina ID: ', idMaquina)
            self.cursor.execute(query, (idMaquina))
            retorno = self.cursor.fetchall()

            print('Retorno do BD: ', retorno)
            print('Numero de registros:', len(retorno))
            self.objSql.commit()
            return retorno
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()
            
    def close(self):
        self.objSql.close()