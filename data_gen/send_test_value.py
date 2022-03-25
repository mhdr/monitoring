import pika
import json
import datetime
import time
import pprint
import sys

credentials = pika.PlainCredentials('monitoring', 'shematic')
parameters = pika.ConnectionParameters('localhost', 5672, '/', credentials)
connection = pika.BlockingConnection(parameters)
channel = connection.channel()

time.sleep(5)

while True:
    value = str(sys.argv[1])
    v1 = {"id": 1, "value": value}

    valueList = []

    valueList.append(v1)

    now = datetime.datetime.now().isoformat()

    ReadValueDto = {"time": now, "valueList": valueList}

    output = json.dumps(ReadValueDto)

    channel.basic_publish(exchange='',
                          routing_key='monitoring.readValue',
                          body=output)

    pprint.pprint(ReadValueDto)
    time.sleep(2)
