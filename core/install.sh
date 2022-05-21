#!/bin/bash

deploy_dir=/opt/monitoringv5/core
service_name=monitoringv5_core.service
service_file=monitoringv5_core.service

# build
gradle build

# deploy
sudo mkdir -p ${deploy_dir}
sudo cp build/libs/core.jar ${deploy_dir}/core.jar
sudo cp run.sh ${deploy_dir}/run.sh
sudo dos2unix ${deploy_dir}/run.sh
sudo chmod +x ${deploy_dir}/run.sh

# systemd
sudo systemctl stop ${service_name}
sudo systemctl disable ${service_name}
sudo rm /etc/systemd/system/${service_file}
sudo cp ${service_file} /etc/systemd/system/${service_file}
sudo systemctl enable ${service_name}
