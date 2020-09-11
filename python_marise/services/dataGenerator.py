import psutil
from datetime import datetime


def getData():
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
    data = (round(cpu_media,1), memory, memory_percent, diskUsado, diskLivre, data_atual)

    return data


