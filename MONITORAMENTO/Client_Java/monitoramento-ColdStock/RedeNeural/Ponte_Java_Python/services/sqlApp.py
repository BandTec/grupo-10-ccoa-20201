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

    def consultarQtdChamadosOntem(self, idMaquina, dataHoje, dataPassado, idComponente):
        query = ("select count(fkChamado) "
            "from coldstock.registros, coldstock.componentes where fkComponente = idComponente "
            "and fkMaquina = %s and dataHora between '%s' and '%s' and fkComponente = %s "
            "order by dataHora desc;")%(idMaquina, dataPassado, dataHoje, idComponente)

        try:
            self.cursor.execute(query)
            retorno = self.cursor.fetchall()

            print('Retorno do BD: ', retorno)
            self.objSql.commit()
            return retorno
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()

    def pegarRegistros(self, idMaquina, dataHoje, dataPassado, idComponente):
        query = ("select valor, nomeComponente "
            "from coldstock.registros, coldstock.componentes where fkComponente = idComponente "
            "and fkMaquina = %s and dataHora between '%s' and '%s' and fkComponente = %s "
            "order by dataHora desc;")%(idMaquina, dataPassado, dataHoje, idComponente)

        try:
            print('Selecionando registros do componente ID: ', idComponente)
            self.cursor.execute(query)
            retorno = self.cursor.fetchall()

            self.objSql.commit()
            return retorno
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()

    def consultarConfiguracao(self, idMaquina):
        self.connect()
        query = (
            "select idComponente, nomeComponente, capacidadeMax "
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