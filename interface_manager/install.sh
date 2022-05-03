#!/usr/bin/env bash

deploy_dir=/opt/monitoringv5/interface_manager
service_name=monitoringv5_interface_manager.service
service_file=monitoringv5_interface_manager.service

# build
gradle build

# deploy
sudo mkdir -p ${deploy_dir}
sudo cp build/libs/interface_manager.jar ${deploy_dir}/interface_manager.jar
sudo cp run.sh ${deploy_dir}/run.sh
sudo dos2unix ${deploy_dir}/run.sh
sudo chmod +x ${deploy_dir}/run.sh

# systemd
sudo systemctl stop ${service_name}
sudo systemctl disable ${service_name}
sudo rm /etc/systemd/system/${service_file}
sudo cp ${service_file} /etc/systemd/system/${service_file}
sudo systemctl enable ${service_name}
