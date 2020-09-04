import psutil
from datetime import datetime


def getData():
    cpu_info = {
    'cpu': 0,
    'memory': 0,
    'disk': 0,
    'dataehora': 0
    }
    cpu = psutil.cpu_percent(interval=1, percpu=True)
    cpu_media = sum(cpu)/len(cpu)
    memory = (psutil.virtual_memory().used >> 30)
    memory_percent = (psutil.virtual_memory().percent)
    disk = psutil.disk_usage('/').percent
    data_atual = datetime.now().strftime('%Y-%m-%d %H:%M:%S')

    cpu_info['cpu'] = round(cpu_media,1)
    cpu_info['memory'] = memory
    cpu_info['memory_percent'] = memory_percent
    cpu_info['disk'] = disk
    cpu_info['dataehora'] =  data_atual

    #Objeto para visualização só
    print(cpu_info)
    #lista para envio no banco
    data = (round(cpu_media,1), memory, memory_percent, disk, data_atual)

    return data


