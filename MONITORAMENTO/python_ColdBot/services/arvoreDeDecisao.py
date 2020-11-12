from services.sqlApp import Mysql




class Menu:
    def __init__(self, usuario, mensagem):
        self.usuario = usuario
        self.mensagem = mensagem

        self.menu = [
            ['\n1 - máquinas \n2 - suporte \n0 - voltar/deslogar', ''],
            ['\n1 - escolher máquina \n2 - maquina BD \n0 - voltar','\n1 - abrir chamado \n2 - FAQ \n0 - voltar'],
            [self.usuario.maquina.maquinasId ,'\n1 - abrir chamado \n2 - FAQ \n0 - voltar'],
            [self.usuario.maquina.maquinasId, '']
            ]
    def mostrarMenu(self):
        
        retorno = 'Selecione um dos itens abaixo'
        if self.testeDeCamadas(0,0) and not self.mensagem.isnumeric(): 
            retorno += self.menu[0][0]
        elif self.testeDeCamadas(0,0) and  self.mensagem.isnumeric():
            if self.mensagem == '0':
                self.usuario.login = None
                self.usuario.senha = None
                self.usuario.loginEstagio = 0
                retorno = 'Você foi deslogado com sucesso'
            elif self.mensagem == '1':
                self.usuario.funcao = 1
                self.usuario.camada = 2
                retorno += self.menu[1][0]
            elif self.mensagem == '2':
                self.usuario.funcao = 2 
                self.usuario.camada = 2
                retorno += self.menu[1][1] 
            else:
                retorno = self.textoErro()
        # MaquinaDeConfig

        elif self.testeDeCamadas(1,1):
            
            if self.mensagem == '0':
                
                retorno += self.voltar() 
            elif self.mensagem == '1' or self.mensagem == '2':
                self.usuario.camada += 1
                retorno = self.menu[self.usuario.camada][self.usuario.funcao - 1](self.mensagem)
            else:
                retorno = self.textoErro()
        
        elif self.testeDeCamadas(1,2):
            if self.mensagem == '0':
                if self.usuario.maquina.pedirId: 
                    self.usuario.maquina.pedirId = False
                    retorno += self.voltar()
                else:
                    retorno += self.voltar()
            else:
                retorno = self.menu[self.usuario.camada][self.usuario.funcao - 1](self.mensagem)
        
        # Suporte  
        elif self.testeDeCamadas(2,1):
            if self.mensagem == '0':
                retorno += self.voltar()
            elif self.mensagem == '1':
                self.usuario.camada = 2
                retorno += self.menu[1]
            elif self.mensagem == '2': 
                self.usuario.camada = 2
                retorno += self.menu[2] 
            else:
                retorno = self.textoErro()
        return retorno
    
    def testeDeCamadas(self, funcao, camada):
        teste = self.usuario.funcao == funcao and self.usuario.camada == camada
        print(teste)
        return teste
    def voltar(self):
        self.usuario.camada -=1
        if self.usuario.camada == 0:
            self.usuario.funcao = 0 
            return self.menu[self.usuario.camada][self.usuario.funcao]
        return self.menu[self.usuario.camada][self.usuario.funcao - 1]
    def textoErro(self):
        return 'Erro - Selecione um dos itens abaixo' + self.menu[self.usuario.camada - 1]