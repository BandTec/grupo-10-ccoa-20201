import psutil, time, sys
import requests
from datetime import datetime


def getData(idServidor):
    cpu_info = {
    'cpu': 0,
    'memory': 0,
    'memory_percent': 0,
    'diskL': 0.0,
    'diskU': 0.0,
    'dataehora': 0
    }
    cpu = psutil.cpu_percent(interval=1, percpu=True)
    cpu_media = sum(cpu)/len(cpu)
    memory = (round(psutil.virtual_memory().used/1024**3, 2))
    memory_percent = (psutil.virtual_memory().percent)
    diskUsado = round(((psutil.disk_usage('/').used)/1024**3),2)
    diskLivre = round(((psutil.disk_usage('/').free)/1024**3),2)
    data_atual = datetime.now().strftime('%Y-%m-%d %H:%M:%S')

    cpu_info['cpu'] = round(cpu_media,1)
    cpu_info['memory'] = memory
    cpu_info['memory_percent'] = memory_percent
    cpu_info['diskU'] = diskUsado
    cpu_info['diskL'] = diskLivre
    cpu_info['dataehora'] =  data_atual

    #Objeto para visualização só
    print(cpu_info)
    #lista para envio no banco
    data = (idServidor,round(cpu_media,1), memory, memory_percent, diskUsado, diskLivre, data_atual)

    return data

def notificacaoSlack(values, parametros):
    url = 'https://hooks.slack.com/services/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'

    # if (values[0]>=65) or (values[1]>=60) or (values[4]>=120):
    notificacao = ''
    if(values[0]>=80):
        notificacao += "CPU em alto uso, "
    if(values[1]>=parametros[2]* 0.8):
        notificacao += "RAM em alto uso, "
    if(values[4]>=parametros[3] * 0.8):
        notificacao += "Disco em alto uso, "
    
    notificacao = notificacao[0:-2] + '.'
    pload = {'text': notificacao}
    requests.post(url, json = pload)
    return notificacao
    # else:
    #     return "Hardware ok :)"

# if __name__ == '__main__':
#     while True:
#         values = getData()
#         condicoes = notificacaoSlack(values)
#         print(condicoes)

