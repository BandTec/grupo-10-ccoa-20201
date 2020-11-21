import requests

class SuporteBot:
    def __init__(self, usuario):
        self.usuario = usuario
        self.chamado = {
        "chave": None,
        "tipo" : None,
        "resumo" : None,
        "label": None,
        "descricao" : None
        }

    def pegarChave(self, mensagem):
        if mensagem.upper() == 'CK':
            self.chamado["chave"] = '10003'
            self.usuario.camada += 1
            return 'Escolha o tipo de chamado\n 1 - Chamado Comum'
        else:
            return 'Não existe nenhum projeto com esse nome \nDigite Corretamente a chave do projeto'

    def pegarTipoChamado(self, mensagem):
        if mensagem == '1':
            self.usuario.camada += 1
            self.chamado["tipo"] = mensagem
            return 'Escolha uma label: \n1 - Alerta-CPU \n2 - Alerta-Memória \n3 - Alerta-Disco'

        else: 
            return 'Não existe esse tipo de chamado \nDigite novamente o tipo de chamado'

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
        self.chamado["resumo"]
        return "Digite por fim uma descrição do seu chamado"














    def criandoChamado(self, mensagem):
        self.chamado["tipo"] = mensagem
        self.usuario.camada += 1
        return 'Insira o titulo'
    
    def criandoTipo(self, mensagem):
        self.chamado["tipo"] = mensagem
        self.usuario.camada += 1
        return 'Insira a descricao'

    def criandoTitulo(self, mensagem):
        self.chamado["titulo"] = mensagem
        self.usuario.camada += 1
        return "ok"

    def criandoDescricao(self, mensagem):
        self.chamado["descricao"] = mensagem
        self.usuario.camada += 1
        chamadoFinal = self.chamado["tipo"] + "\n" + self.chamado["titulo"] + "\n" + self.chamado["descricao"]
        return "Esses dados estão corretos? " + chamadoFinal



        