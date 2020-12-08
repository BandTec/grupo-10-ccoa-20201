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
            self.usuario.funcao = 0
            return ('Perguntas Frequentes: \n\n'

            '1 - O que é o ColdBot?\n'
            'R: ColdBot é um Bot utilizado por nossos clientes para realizar consultas referentes a sua máquina/servidor e através das mesmas, realizar análises que garantiram um melhor funcionamento do equipamento \n\n' 
            
            '2 - Consigo usar o coldbot em qualquer máquina?\n'
            'R: Não, somente nas máquinas cadastradas junto a equipe ColdStock\n\n' 

            '3 - Quem tem acesso ao sistema de monitoramento Coldstock?\n'
            'R: Todos os funcinários que possuem as credênciais referentes ao suporte e desenvolvimento e também os clientes que optarem a terem esse acessado de forma restritiva, onde apenas certas funçõe estaram liberadas\n\n'

            '4 - O bot não está me respondendo. Como o inicializo?\n'
            'R: A princípio é necesário a inicialização do diretório dentro de um interpretador de códigos, recomendamos o VSCODE, dentro do diretório, procure pelo arquivo "BotTelegramResponse.py", dentro dele use o debug (f5) e em seguida aperte "ENTER", o bot irá iniciar  "\n\n' 
            
            '5 - Qual é a versão mínima de Java para usar a aplicação?\n'
            'R: Use as versões de 11 para cima\n\n' 

            '6 - Qual sistema operacional é suportado?\n'
            'R: Windows 7 e sucessores\n\n'
            
            '7 - Não estou conseguindo verificar a temperatura de minha máquina. O que devo fazer?\n'
            'R: Verifique se o web crawler OpenHardwareMonitor, está funcionando de forma correta e também verifique se a API do mesmo está em funcionamento \n\n' 
            
            '8 - Qual a porcentagem máxima recomendada para se colocar em minha CPU?\n'
            'R: Essa informação é totalmente dependente do usuário as configurações de sua máquina\n\n' 

            '9 - Para acessar as opções é obrigatório ter login? Como me cadastro?\n'
            'R: Assim que o cliente contatar a equipe, suas informações são coletadas e é feito o cadastro da máquina, para contato, use: "51sg4p@inbox.groovehq.com"\n\n'
            
            '10 - Estou com outras dúvidas, onde posso ter elas respondidas?\n'
            'R: Utilize este email para contato: "51sg4p@inbox.groovehq.com"\n\n'

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

        self.usuario.camada = 0
        self.usuario.funcao = 0

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
 
        print(json.dumps(json.loads(response.text), sort_keys=True, indent=4, separators=(",", ": ")))
        return ('Chamado Criado com sucesso\n\n'
        'Selecione outra opção: \n1 - máquinas \n2 - suporte \n0 - voltar/deslogar')
    