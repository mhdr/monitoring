#!/usr/bin/env bash

python3 -m venv venv
python_path=venv/bin/python3

${python_path} -m pip install --upgrade pika
${python_path} -m pip install --upgrade psutil
${python_path} -m pip install --upgrade uptime