#!/usr/bin/env bash

python3 -m venv venv
python_path=venv/bin/python3

${python_path} -m pip install --upgrade matplotlib
${python_path} -m pip install --upgrade numpy
${python_path} -m pip install --upgrade pandas
${python_path} -m pip install --upgrade python-dateutil
${python_path} -m pip install --upgrade pytz
${python_path} -m pip install --upgrade scipy
${python_path} -m pip install --upgrade pika
