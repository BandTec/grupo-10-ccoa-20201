import mysql.connector

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

    def cadastrarConversa(self, idMaquina, idConversa):
        query = ("insert into testeTelegram (idConversa, fkMaquina) "
                "values ('%s', '%s')" % (idConversa, idMaquina)
                )
        try:
            print('Registrando Conversa ...')
            self.cursor.execute(query)
            self.objSql.commit()
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()

    def removerConversa(self, idMaquina, idConversa):
        query=("delete from testeTelegram where "
                "idMaquina = %s and idConversa = %s" % (idMaquina, idConversa))
        try:
            print('Deletando ...')
            self.cursor.execute(query)
            self.objSql.commit()
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()

    def listarComponente(self, idServer):
        query = (
            "select nomeComponente, porcentagemMax from maquinas "
            "inner join configuracaoMaquina on idMaquina = fkMaquina "
            "inner join componentes on idComponente = fkComponente "
            "where idMaquina = %s "
        )

        try:
            print('Selecionando dados do server ID: ', idServer)
            self.cursor.execute(query, (idServer,))
            retorno = self.cursor.fetchall()

            print('Retorno do BD: ', retorno)
            print('Numero de registros:', len(retorno))
            self.objSql.commit()
            return retorno
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()

    def insert(self,valores):
        query = "insert into registros (dataHora, valor, fkMaquina, fkComponente) values (%s,%s,%s,%s)"
        try:
            print('Aguarde ...')
            self.cursor.executemany(query,valores)
            self.objSql.commit()
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()
            
    # def selectServer(self, idServer):
    #     query = (
    #         "SELECT * FROM servidores "
    #         "where idServidor = %s" % (idServer) 
    #     )

    #     try:
    #         print('Selecionando dados do server ID: ', idServer)
    #         self.cursor.execute(query)
    #         retorno = self.cursor.fetchall()

    #         print('Retorno do BD: ', retorno[0][3])
    #         self.objSql.commit()
    #         return retorno [0]
    #     except Exception as err:
    #         print(err)
    #         self.objSql.rollback()
    #         self.close()

    # Fechando conexão
    def close(self):
        self.objSql.close()

    def alterarCamada(self,camada, funcao, idFunc):
        query = """update funcionarios set camada = %s, funcao = %s where idFuncionario = %s"""
        valores = (camada,funcao,idFunc)
        print(type(valores))
        try:
            print('Aguarde ...')
            self.cursor.executemany(query,(valores,))
            self.objSql.commit()
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()

    def consultarCamada(self, idFunc):
        query = "select idFuncionario, nomeFuncionario, camada, funcao from funcionarios where idFuncionario = %s"
        try:
            print('Aguarde ...')
            self.cursor.execute(query,(idFunc,))
            print(self.cursor.fetchall())
            self.objSql.commit()
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()
    
    def consultarUsuario(self,email, senha):
        query = ("select * from funcionarios where emailFuncionario = '%s' and senhaFuncionario = '%s'" %(email, senha)) 
        print(query)
        try:
            print('Aguarde ...')
            self.cursor.execute(query)
            retorno = self.cursor.fetchall()
            self.objSql.commit()
            return retorno
        except Exception as err:
            print(err)
            self.objSql.rollback()
            self.close()
        

