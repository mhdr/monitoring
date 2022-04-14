import datetime
import time
import psutil
import uptime
import json
import pika
import pprint
import os

credentials = pika.PlainCredentials('monitoringv5', 'monitoringv5')
parameters = pika.ConnectionParameters('localhost',
                                       5672,
                                       '/',
                                       credentials)
connection = pika.BlockingConnection(parameters)
channel = connection.channel()

while True:
    try:
        time.sleep(2)

        cpu = psutil.cpu_percent()
        ram = psutil.virtual_memory().percent
        swap = psutil.swap_memory().percent
        disk = psutil.disk_usage('/').percent
        sys_uptime = uptime.uptime()

        value1 = {"id": 1, "value": str(cpu)}
        value2 = {"id": 2, "value": str(ram)}
        value3 = {"id": 3, "value": str(swap)}
        value4 = {"id": 4, "value": str(disk)}
        value5 = {"id": 5, "value": str(sys_uptime)}

        dataList = []

        dataList.append(value1)
        dataList.append(value2)
        dataList.append(value3)
        dataList.append(value4)
        dataList.append(value5)

        now = datetime.datetime.now().isoformat()

        ReadValueDto = {"credential": "779e43d0-7e31-49e5-864f-26234abf3909", "time": now, "valueList": dataList}

        output = json.dumps(ReadValueDto)

        channel.basic_publish(exchange='',
                              routing_key='MonitoringV5_Queue2',
                              body=output)

        pprint.pprint(output)
    except Exception as ex:
        print(ex)
