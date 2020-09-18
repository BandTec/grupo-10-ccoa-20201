import mysql.connector


class Mysql:
    def __init__(self, user, password, host, database):
        self.user = user
        self.password = password
        self.host = host
        self.database = database
        self.mysql = None
        self.cursor = None

    #Estabelecendo uma conexão
    def connect(self):
        try:
            self.mysql = mysql.connector.connect(
            user=self.user, password=self.password, host=self.host, database=self.database)
            #Criando cursor para manipulação do banco.
            print(self.mysql)
            self.cursor = self.mysql.cursor()
        except Exception as err:
            print(err)
            raise

    def insert(self, data):
        query = (
            "INSERT INTO `registros`(fkServidor, cpu, ram, rampercent, disku, diskl, datahora)"
            "VALUES (%s,%s, %s, %s, %s, %s, %s)"
        )
        values = data
        try:
            print('Aguarde ...')
            self.cursor.execute(query,values)
            self.mysql.commit()
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.close()

            
    def selectServer(self, idServer):
        query = (
            "SELECT * FROM servidores "
            "where idServidor = %s" % (idServer) 
        )

        try:
            print('Selecionando dados do server ID: ', idServer)
            self.cursor.execute(query)
            retorno = self.cursor.fetchall()

            print('Retorno do BD: ', retorno[0][3])
            self.mysql.commit()
            return retorno [0]
        except Exception as err:
            print(err)
            self.mysql.rollback()
            self.close()

    # Fechando conexão
    def close(self):
        self.mysql.close()

        


