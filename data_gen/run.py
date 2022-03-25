import pprint
import time
import pika
import json
import datetime

import pytz

from values import *
import os

value1 = Value1()  # 10 Real
value2 = Value2()  # 11 Real
value3 = Value3()  # 12 Real
value4 = Value4()  # 13 Real
value5 = Value5()  # 14 Real
value6 = Value6()  # 15 Real
value7 = Value7()  # 16 Real
value8 = Value8()  # 17 Real
value9 = Value9()  # 18 Real
value10 = Value10()  # 19 Real
value11 = Value11()  # 20 Real
value12 = Value12()  # 21 Real
value13 = Value13()  # 22 Real
value14 = Value14()  # 23 Boolean
value15 = Value15()  # 24 Boolean
value16 = Value16()  # 25 Real

credentials = pika.PlainCredentials('monitoringv5', 'monitoringv5')
parameters = pika.ConnectionParameters('localhost', 5672, '/', credentials)
connection = pika.BlockingConnection(parameters)
channel = connection.channel()

while True:
    try:
        time.sleep(2)

        v1 = {"Id": 10, "Value": str(value1.next())}
        v2 = {"Id": 11, "Value": str(value2.next())}
        v3 = {"Id": 12, "Value": str(value3.next())}
        v4 = {"Id": 13, "Value": str(value4.next())}
        v5 = {"Id": 14, "Value": str(value5.next())}
        v6 = {"Id": 15, "Value": str(value6.next())}
        v7 = {"Id": 16, "Value": str(value7.next())}
        v8 = {"Id": 17, "Value": str(value8.next())}
        v9 = {"Id": 18, "Value": str(value9.next())}
        v10 = {"Id": 19, "Value": str(value10.next())}
        v11 = {"Id": 20, "Value": str(value11.next())}
        v12 = {"Id": 21, "Value": str(value12.next())}
        v13 = {"Id": 22, "Value": str(value13.next())}
        v14 = {"Id": 23, "Value": str(value14.next())}
        v15 = {"Id": 24, "Value": str(value15.next())}
        v16 = {"Id": 25, "Value": str(value16.next())}

        valueListReal = []
        valueListBoolean = []

        valueListReal.append(v1)
        valueListReal.append(v2)
        valueListReal.append(v3)
        valueListReal.append(v4)
        valueListReal.append(v5)
        valueListReal.append(v6)
        valueListReal.append(v7)
        valueListReal.append(v8)
        valueListReal.append(v9)
        valueListReal.append(v10)
        valueListReal.append(v11)
        valueListReal.append(v12)
        valueListReal.append(v13)
        valueListReal.append(v16)

        valueListBoolean.append(v14)
        valueListBoolean.append(v15)

        # using time zone
        # utc_now = pytz.utc.localize(datetime.datetime.utcnow())
        # pst_now = utc_now.astimezone(pytz.timezone("Asia/Tehran"))
        # now = pst_now.isoformat()

        now = datetime.datetime.now().isoformat()

        ReadValueDtoReal = {"Credential": "66ad9345-9e09-4629-aed2-e8684a9cf31d", "Time": now,
                            "DataList": valueListReal}
        ReadValueDtoBoolean = {"Credential": "66ad9345-9e09-4629-aed2-e8684a9cf31d", "Time": now,
                               "DataList": valueListBoolean}

        outputReal = json.dumps(ReadValueDtoReal)
        outputBoolean = json.dumps(ReadValueDtoBoolean)

        channel.basic_publish(exchange='',
                              routing_key='MonitoringV5_Queue1',
                              body=outputBoolean)
        channel.basic_publish(exchange='',
                              routing_key='MonitoringV5_Queue2',
                              body=outputReal)

        pprint.pprint(outputBoolean)
        pprint.pprint(outputReal)
    except Exception as ex:
        print(ex)
