#!/usr/bin/env bash

deploy_dir=/opt/monitoringv5/sys
service_name=monitoringv5_interface_sys.service
service_file=monitoringv5_interface_sys.service

# deploy
sudo mkdir -p ${deploy_dir}
sudo cp run.py ${deploy_dir}/run.py
sudo cp run.sh ${deploy_dir}/run.sh

# env
pushd ${deploy_dir}
sudo python3 -m venv venv
popd

# install packages
pushd ${deploy_dir}
python_path=venv/bin/python3
sudo ${python_path} -m pip install --upgrade pika
sudo ${python_path} -m pip install --upgrade psutil
sudo ${python_path} -m pip install --upgrade uptime
popd

# systemd
sudo systemctl stop ${service_name}
sudo systemctl disable ${service_name}
sudo rm /etc/systemd/system/${service_file}
sudo cp ${service_file} /etc/systemd/system/${service_file}
sudo systemctl enable ${service_name}
