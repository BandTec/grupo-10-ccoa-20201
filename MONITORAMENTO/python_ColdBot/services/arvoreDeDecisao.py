from services.sqlApp import Mysql

class Menu:
    def __init__(self, usuario, mensagem):
        self.usuario = usuario
        self.mensagem = mensagem

        self.menu = [
            ['\n1 - máquinas \n2 - suporte \n0 - voltar/deslogar'],
            [self.usuario.maquina.iniciarMaquinas,  'retonro de texto camda 1'],
            [self.usuario.maquina.maquinasId,       '\n1 - abrir chamado \n2 - FAQ \n0 - voltar'],
            ['',                                    self.usuario.suporte.criandoTipo],
            ['',                                    self.usuario.suporte.criandoTitulo],
            ['',                                    self.usuario.suporte.criandoDescricao]
            ] # matriz para controlar o menu. 

    def mostrarMenu(self):       
        retorno = 'Selecione um dos itens abaixo' # variavel que retorna texto.

        if self.testeDeCamadas(0,0) and not self.mensagem.isnumeric(): # teste para ver se é a primeira mensagem.
            retorno += self.menu[0][0]

        #Saindo da camada 0,0
        elif self.testeDeCamadas(0,0) and self.mensagem.isnumeric():
            if self.mensagem == '0':
                self.usuario.login = None
                self.usuario.senha = None
                self.usuario.loginEstagio = 0
                self.usuario.anterior = []
                retorno = 'Você foi deslogado com sucesso' 

            #Setando camada para config
            elif self.mensagem == '1':
                self.usuario.funcao = 1
                self.usuario.camada = 1
                retorno += self.menu[1][0]('1')

            #Setando camada para suporte
            elif self.mensagem == '2':
                self.usuario.funcao = 2 
                self.usuario.camada = 1
                retorno += self.menu[1][1] 
            else:
                retorno = self.textoErro()
                
        # MaquinaDeConfig
        elif self.usuario.funcao == 1:
            
            if self.mensagem == '0':
                retorno = self.voltar() 
                
            elif self.mensagem.isnumeric():
                #Chamando o arquivo das maquinas para responder
                retorno = self.menu[self.usuario.camada][self.usuario.funcao - 1](self.mensagem)

            else:
                retorno = self.textoErro()
        
        # Suporte  
        elif self.testeDeCamadas(2,1):
            if self.mensagem == '0':
                retorno = self.voltar() 
            
            elif self.mensagem == '1':
                self.usuario.camada += 1
                retorno = self.menu[self.usuario.camada][self.usuario.funcao-1]
                print(self.menu)
            # elif self.mensagem == '2': 
            #     self.usuario.camada = 2
            #     retorno += self.menu[self.usuario.camada][self.usuario.funcao - 1](self.mensagem)
        elif self.testeDeCamadas(2,2):
            retorno = self.menu[self.usuario.camada][self.usuario.funcao-1]
            
        elif self.testeDeCamadas(2,3):
            if self.mensagem == 'incident' or self.mensagem == 'problem':
                retorno = self.menu[self.usuario.camada][self.usuario.funcao-1]
                
        elif self.testeDeCamadas(2,4):
            #self.usuario.suporte.criandoDescricao(self.mensagem)
            retorno = self.menu[self.usuario.camada][self.usuario.funcao-1] 
            
        elif self.testeDeCamadas(2,5):
            retorno = "Ok"
        else:
                retorno = self.textoErro()
        if not retorno in self.usuario.anterior: # ele verifica se o array contem o mesmo texto que o retorno.
            self.usuario.anterior.append(retorno) # caso não tenha ele adiciona
        return retorno
    
    #Verifica onde esta o usuario.
    def testeDeCamadas(self, funcao, camada):
        teste = self.usuario.funcao == funcao and self.usuario.camada == camada
        print(teste)
        return teste

    def voltar(self):
        self.usuario.camada -=1
        if self.usuario.camada == 0:
            self.usuario.funcao = 0 
            # return self.menu[self.usuario.camada][self.usuario.funcao]
        self.usuario.anterior.pop() # retira o ultimo item do array.
        return self.usuario.anterior[len(self.usuario.anterior) - 1] # pega o ultimo item, no caso a mensagem anterior e retorna ela.

    def textoErro(self):
        return 'Erro - Selecione um dos itens abaixo' + self.menu[self.usuario.camada - 1]
