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
        query = ("select * from componentes")

        try:
            print('Selecionando todos os componentes cadastrados')
            self.cursor.execute(query)
            retorno = self.cursor.fetchall()

            print('Retorno do BD: ', retorno)
            self.objSql.commit()
            return retorno
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()

    def consultarMaquina(self, idMaquina):
        query = ("select dataHora, valor, nomeComponente, fkChamado "
            "from coldstock.registros INNER JOIN coldstock.componentes on fkComponente = idComponente "
            "where fkMaquina = %s order by dataHora ")%idMaquina

        try:
            print('Selecionando componentes cadastrados')
            self.cursor.execute(query)
            retorno = self.cursor.fetchall()

            print('Retorno do BD: ', retorno)
            self.objSql.commit()
            return retorno
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()

    def consultarConfiguracao(self, idMaquina):
        self.connect()
        query = (
            "select nomeComponente, capacidadeMax "
            "from configuracaoMaquina, componentes "
            "where fkMaquina = %s and idComponente = fkComponente;"
        )%idMaquina

        try:
            print('Selecionando configuracoes da maquina ID: ', idMaquina)
            self.cursor.execute(query)
            retorno = self.cursor.fetchall()

            self.objSql.commit()
            return retorno
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()
                
    def close(self):
        self.objSql.close()