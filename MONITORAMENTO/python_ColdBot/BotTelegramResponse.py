from services.sqlApp import Mysql
from services.suporte import SuporteBot
from services.maquinaConfig import MaquinaConfigBot
from services.usuario import Usuario
from services.arvoreDeDecisao import Menu
import requests
import time
import json
import os
from threading import Thread

class TelegramBot:
    def __init__(self):
        token = '1325809344:AAH4sVx4S9AMMdjKrvhx9Ogt6rdG500Ul_I'
        self.url_base = f'https://api.telegram.org/bot{token}/'
        self.flagImagem = False
        self.atualizacao = None
        self.chat_id = None
        self.update_id = None
        self.sessoesUsuario = []
    def Iniciar(self):
        update_id = None
        while True:
            atualizacao = self.obter_novas_mensagens(update_id)
            dados = atualizacao['result']
            self.atualizacao = atualizacao
            
            if dados:
                for dado in dados:
                    update_id = dado['update_id']
                    self.update_id = dado['update_id']
                    mensagem = ""
                    print("Mensagem obtida: ", dado)
                    try:
                        mensagem = str(dado["message"]["text"]).lower()
                    except:
                        mensagem = "formato"
                    chat_id = dado["message"]["from"]["id"]
                    self.chat_id = dado["message"]["from"]["id"]
                    usuario = None
                    if len(self.sessoesUsuario) == 0:
                        self.sessoesUsuario.append(Usuario(self.chat_id))
                    verificador = map( lambda x: x.idChat, self.sessoesUsuario )
                    if not (self.chat_id in list(verificador)):
                        self.sessoesUsuario.append(Usuario(self.chat_id))
                    for a in list(self.sessoesUsuario):
                        if(a.idChat == self.chat_id):
                            usuario = a;
                    
                    
                    resposta = self.criar_resposta(mensagem, chat_id, usuario)
                    self.responderTexto(resposta['texto'], chat_id)
                    if self.flagImagem:
                        self.flagImagem = False
                        self.responderImg(resposta['imagem'], chat_id)

    
    # Obter mensagens
    def obter_novas_mensagens(self, update_id):
        link_requisicao = f'{self.url_base}getUpdates?timeout=1000'
        if update_id:
            link_requisicao = f'{link_requisicao}&offset={update_id+1}'
            
        resultado = requests.get(link_requisicao)
        return json.loads(resultado.content)

    # Criar uma resposta
    def criar_resposta(self, mensagem, idChat, usuario):
        resposta = {
                'texto': "Num tendi ;-;",
                'imagem': None
            }
 
        if usuario.loginEstagio == 0: 
            if mensagem == '/start' or mensagem == 'comandos' or mensagem == 'ajuda' or mensagem == 'help':
                resposta['texto'] = ('Olá, qreeuck! Obrigado por escolher ColdStock, e seja muito bem-vindo ao nosso atendimento pessoal! Eu sou o Pingolino e estou aqui para te ajudar com suas dúvidas! Vamos começar? Qreeuck! \n'
                'Eu tenho algumas palavras chaves que você pode utilizar para conversar comigo! \n\n'

                'Use o comando notificacao-sim ＋ Número da máquina que será consultado para receber notificações sobre o estado de sua máquina!\n'
                'EX: notificacao-sim 1\n\n'
                'Se não quiser mais recebe-las, basta me enviar notificacao-nao ＋ Número da máquina que será consultado e eu vou parar de te enviar notícias sobre a máquina!\n'
                'EX: notificacao-nao 1\n\n'
                'Caso você queira receber informações do status atual de sua máquina, basta enviar status ＋ Número da máquina que será consultado e eu vou te listar tudinho!\n'
                'EX: status 1\n\n'

                'Caso você precise de ajuda, só digite help e eu te passo os comandos novamente!\n\n'

                'Fácil, né? Vamos lá, qreeuck!')

            elif mensagem == 'formato':
                resposta['texto'] = 'Formato de mensagem inválido! Qreeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeck!!! >:('


            elif mensagem == 'status':
                resposta['texto'] = 'O pc explodiu!'
                self.flagImagem = True
                resposta['imagem'] = 'AgACAgEAAxkBAAIBwV984H6_g0XSBusM0J6Kxg7uvwlXAAKrqDEbZNLoRxrYU-fRdgVhR-NuBgAEAQADAgADeAADCMwEAAEbBA'

            elif 'notificacao-sim' in mensagem or 'notificação-sim' in mensagem:
                mensagem = mensagem.replace('notificacao-sim', '')
                mensagem = mensagem.replace('notificação-sim', '')
                mensagem = mensagem.strip() #Após isso tudo que resta deve ser o idMaquina
                self.cadastrarNotificacao(mensagem, idChat)
                resposta['texto']='Cadastrando notificações para máquina %s'%(mensagem)

            elif mensagem == 'boa noite' or mensagem == 'boa tarde' or mensagem == 'bom dia' or mensagem == 'olá' or mensagem == 'oi':
                resposta['texto'] = 'Qreeeuck! (Bom diaa)'
            elif mensagem == "login": 
                if usuario.login == None and usuario.senha == None:
                    resposta['texto'] = self.logarUser(usuario, '')
                else:
                    resposta['texto'] = 'Você já efetuou o Login'
            elif mensagem == 'deslogin':
                if usuario.login == None and usuario.senha == None:
                    resposta['texto'] = 'Você não está logado'
                else:
                   usuario.login = None 
                   usuario.senha = None
                   resposta['texto'] = 'Você não está mais Logado'
        else:
            if usuario.login == None or usuario.senha == None:
                resposta['texto'] = self.logarUser(usuario, mensagem)
            else:
                print(usuario)
                resposta['texto'] = self.gerenciarMenu(usuario, mensagem)
                print(usuario)
                
        return resposta

    #Cadastrando notificações
    def cadastrarNotificacao(self, idMaquina, idConversa):
        mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
        mysql.connect()
        mysql.cadastrarConversa(idMaquina, idConversa)
    
    #login do usuario
    def logarUser(self, usuario, mensagem):

        if usuario.loginEstagio == 0:
            usuario.loginEstagio = 1
            return 'Digite o email:'
        elif usuario.loginEstagio == 1:
            usuario.loginEstagio = 2
            usuario.login = mensagem
            return 'Digite a senha:'
        elif usuario.loginEstagio == 2:
            usuario.loginEstagio = 3
            usuario.senha = mensagem
            retorno = usuario.Login()
            if len(retorno) == 0:
                
                usuario.loginEstagio = 0
                usuario.login = None
                usuario.senha = None
                return 'Login ou senha Inválido(s)'
            else:
                usuario.loginEstagio = 4
                return self.gerenciarMenu(usuario, mensagem)

    def gerenciarMenu(self, usuario, mensagem):
        menu = Menu(usuario, mensagem)
        return menu.mostrarMenu()
        # return 'Seleciona um dos itens abaixo \n1 - máquinas \n2 - suporte \n0 - voltar/deslogar'

    # Responder
    def responderTexto(self, resposta, chat_id):
        link_requisicao = f'{self.url_base}sendMessage?chat_id={chat_id}&text={resposta}'
        requests.get(link_requisicao)

    def responderImg(self, imagem, chat_id):
        link_requisicao = f'{self.url_base}sendPhoto?chat_id={chat_id}&photo={imagem}'
        requests.get(link_requisicao)

class ThreadTelegram(Thread):
    from BotTelegramResponse import TelegramBot
    def __init__(self):
        Thread.__init__(self)
    
    def run(self):
        telegram = TelegramBot()
        telegram.Iniciar()

print("Iniciando Thread para Telegram")
Telegram = ThreadTelegram()
Telegram.start()
# obj = TelegramBot()
# obj.Iniciar()