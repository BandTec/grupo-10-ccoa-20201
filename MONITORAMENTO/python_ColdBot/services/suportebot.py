import requests
from requests.auth import HTTPBasicAuth
import json

class SuporteBot:
    def __init__(self, usuario):
        self.usuario = usuario
        self.chamado = {
        "chave": None,
        "keyNumber": None,
        "tipo" : None,
        "label": None,
        "resumo" : None,
        "descricao" : None
        }

    def callOuFaq(self, mensagem):
        if mensagem == '1':
            self.usuario.camada += 1
            return 'Digite a chave do seu projeto: '
        elif mensagem == '2':
            self.usuario.camada = 0
            return ('Perguntas Frequentes: \n\n'

            '1 - Qual prof tá com corona?\n'
            'R: Não sabemos \n\n' 
            
            '2 - Qual uma pergunta frequente?\n'
            'R: Mermão se vira\n\n' 

            '3 - Já não sei mais perguntas\n'
            'R: Pode pá\n\n'

            'Selecione outra opção: \n1 - máquinas \n2 - suporte \n0 - voltar/deslogar'    
            ) 
    
    def pegarChave(self, mensagem):
        if mensagem.upper() == 'CK':
            self.chamado["chave"] = 'CK'
            self.chamado["keyNumber"] = '10003'
            self.usuario.camada += 1
            return 'Escolha o tipo de chamado\n 1 - Chamado Comum'
        else:
            return 'Não existe nenhum projeto com esse nome \nDigite Corretamente a chave do projeto'

    def pegarTipoChamado(self, mensagem):
        if mensagem == '1':
            self.usuario.camada += 1
            self.chamado["tipo"] = 'Chamado comum'
            return 'Escolha uma label: \n1 - Alerta-CPU \n2 - Alerta-Memória \n3 - Alerta-Disco'

        else: 
            return 'Não existe esse tipo de chamado \n\nDigite novamente o tipo de chamado'

    def pegarLabel(self, mensagem):
        if mensagem == '1':
            self.usuario.camada += 1
            self.chamado["label"] = "Alerta-CPU"
            return 'Digite um breve resumo do seu chamado'

        elif mensagem == "2":
            self.usuario.camada += 1
            self.chamado["label"] = "Alerta-Memória"
            return 'Digite um breve resumo do seu chamado'

        elif mensagem == "3":
            self.usuario.camada += 1
            self.chamado["label"] = "Alerta-Disco"
            return 'Digite um breve resumo do seu chamado'

        else: return 'Escolha uma label válida'
        
    def pegarResumo(self, mensagem):
        self.usuario.camada += 1
        self.chamado["resumo"] = mensagem
        return "Digite por fim uma descrição do seu chamado"

    def pegarDescricao(self, mensagem):
        self.usuario.camada += 1
        self.chamado["descricao"] = mensagem
        chamadoCompleto = 'Chave: ' +self.chamado['chave'] + '\n\nTipo: ' + self.chamado['tipo'] + '\n\nLabel: '+ self.chamado['label'] + '\n\nResumo: ' + self.chamado['resumo'] + '\n\nDescrição: ' + self.chamado['descricao']
        return 'Estes dados estão corretos:\n\n'+chamadoCompleto+'\n\n (s/n)?' 

    def confirmar(self, mensagem):
        if mensagem.upper() == 'S':
            return self.usuario.suporte.enviarChamado(self.chamado)

        elif mensagem.upper() == 'N':
            self.usuario.camada = 1
            return '\n1 - Abrir Chamado \n2 - FAQ \n0 - Voltar'




    def enviarChamado(self, chamado):

        if chamado["tipo"] == 'Chamado comum':
            chamado["tipo"] = '10003'

        url = "https://coldstock.atlassian.net/rest/api/3/issue"

        auth = HTTPBasicAuth("201grupo10c@bandtec.com.br", "xYT7D7fZZRvpKWl0svMyC6C9")

        headers = {
        "Accept": "application/json",
        "Content-Type": "application/json"
        }

        payload = json.dumps( {
        "update": {},
        "fields": {

            "summary": chamado["resumo"],
            
            "issuetype": {
            "id": chamado["tipo"]
            },

            "project": {
            "id": chamado["keyNumber"]
            },

            "description": {
            "type": "doc",
            "version": 1,
            "content": [
                {
                "type": "paragraph",
                "content": [
                    {
                    "text": chamado["descricao"],
                    "type": "text"
                    }
                ]
                }
            ]
            },
            
            "labels": [chamado["label"]],
        }
        })



        response = requests.request(
        "POST",
        url,
        data=payload,
        headers=headers,
        auth=auth
        )

        oPrint = json.dumps(json.loads(response.text), sort_keys=True, indent=4, separators=(",", ": "))
        print(oPrint["key"])
        return 'Chamado Criado com sucesso'
    