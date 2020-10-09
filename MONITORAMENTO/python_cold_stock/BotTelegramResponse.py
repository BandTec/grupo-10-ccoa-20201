from services.sqlApp import Mysql
import requests
import time
import json
import os

class TelegramBot:
    def __init__(self):
        token = '1325809344:AAH4sVx4S9AMMdjKrvhx9Ogt6rdG500Ul_I'
        self.url_base = f'https://api.telegram.org/bot{token}/'
        self.flagImagem = False

    def Iniciar(self):
        update_id = None
        while True:
            atualizacao = self.obter_novas_mensagens(update_id)
            dados = atualizacao["result"]
            if dados:
                for dado in dados:
                    update_id = dado['update_id']
                    mensagem = ""
                    print("Mensagem obtida: ", dado)
                    try:
                        mensagem = str(dado["message"]["text"]).lower()
                    except:
                        mensagem = "formato"
                    chat_id = dado["message"]["from"]["id"]
                    resposta = self.criar_resposta(mensagem, chat_id)
                    self.responderTexto(resposta['texto'], chat_id)
                    if self.flagImagem:
                        self.flagImagem = False
                        self.responderImg(resposta['imagem'], chat_id)

    # Obter mensagens
    def obter_novas_mensagens(self, update_id):
        link_requisicao = f'{self.url_base}getUpdates?timeout=100'
        if update_id:
            link_requisicao = f'{link_requisicao}&offset={update_id + 1}'
        resultado = requests.get(link_requisicao)
        return json.loads(resultado.content)

    # Criar uma resposta
    def criar_resposta(self, mensagem, idChat):
        resposta = {
                'texto': "Num tendi ;-;",
                'imagem': None
            }

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

        elif mensagem == 'ping':
            resposta['texto'] = 'Pong!'

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

        return resposta

    #Cadastrando notificações
    def cadastrarNotificacao(self, idMaquina, idConversa):
        mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
        mysql.connect()
        mysql.cadastrarConversa(idMaquina, idConversa)

    # Responder
    def responderTexto(self, resposta, chat_id):
        link_requisicao = f'{self.url_base}sendMessage?chat_id={chat_id}&text={resposta}'
        requests.get(link_requisicao)

    def responderImg(self, imagem, chat_id):
        link_requisicao = f'{self.url_base}sendPhoto?chat_id={chat_id}&photo={imagem}'
        requests.get(link_requisicao)

# obj = TelegramBot()
# obj.Iniciar()