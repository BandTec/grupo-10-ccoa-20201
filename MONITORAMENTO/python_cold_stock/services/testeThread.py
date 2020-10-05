import requests
import time
import json
import os
from threading import Thread


class TelegramBot(Thread):

    def __init__(self):
        Thread.__init__(self)
        token = '1325809344:AAH4sVx4S9AMMdjKrvhx9Ogt6rdG500Ul_I'
        self.url_base = f'https://api.telegram.org/bot{token}/'
        self.Iniciar()

    def Iniciar(self):
        update_id = None
        while True:
            atualizacao = self.obter_novas_mensagens(update_id)
            dados = atualizacao["result"]
            if dados:
                for dado in dados:
                    update_id = dado['update_id']
                    mensagem = str(dado["message"]["text"]).lower()
                    chat_id = dado["message"]["from"]["id"]
                    resposta = self.criar_resposta(mensagem)
                    self.responder(resposta, chat_id)

    def QuickPlay(self):
        update_id = None
        atualizacao = self.obter_novas_mensagens(update_id)
        dados = atualizacao["result"]
        if dados:
            for dado in dados:
                update_id = dado['update_id']
                mensagem = str(dado["message"]["text"]).lower()
                chat_id = dado["message"]["from"]["id"]
                resposta = self.criar_resposta(mensagem)
                self.responder(resposta, chat_id)

    # Obter mensagens
    def obter_novas_mensagens(self, update_id):
        link_requisicao = f'{self.url_base}getUpdates?timeout=100'
        if update_id:
            link_requisicao = f'{link_requisicao}&offset={update_id + 1}'
        resultado = requests.get(link_requisicao)
        return json.loads(resultado.content)

    # Criar uma resposta
    def criar_resposta(self, mensagem):
        resposta = 'Num tendi'

        if mensagem == '/start':
            resposta = 'Bom dia!'

        if mensagem == 'ping':
            resposta = 'Pong!'

        if mensagem == 'status':
            resposta = 'O pc explodiu!'

        return resposta

    # Responder
    def responder(self, resposta, chat_id):
        link_requisicao = f'{self.url_base}sendMessage?chat_id={chat_id}&text={resposta}'
        requests.get(link_requisicao)
