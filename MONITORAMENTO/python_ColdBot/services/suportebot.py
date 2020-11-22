import requests

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
        chamadoCompleto = 'Chave: ' +self.chamado['chave'] + '\nTipo: ' + self.chamado['tipo'] + '\nLabel: '+ self.chamado['label'] + '\nResumo: ' + self.chamado['resumo'] + '\nDescrição: ' + self.chamado['descricao']
        return 'Estes dados estão corretos:\n'+chamadoCompleto+'\n(s/n)' 

    def confirmar(self, mensagem):
        if mensagem.upper() == 'S':
            return enviarChamado()

    def enviarChamado():
        return 'teste'    