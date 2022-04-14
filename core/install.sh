#!/usr/bin/env bash

user_config_file=$HOME/.monitoringv5/core/Config.java
project_config_file=src/main/java/net/pupli/monitoring/core/lib/Config.java
deploy_dir=/opt/monitoring/core
service_name=monitoring_core.service
service_file=monitoring_core.service

# copy config file
if [[ -f ${user_config_file} ]]
then
    cp ${user_config_file} ${project_config_file}

    if [[ $? -eq 0 ]]
    then
        echo "${user_config_file} copied to ${project_config_file}"
    fi
else
    echo "${user_config_file} not found"
fi

# build
gradle build

# deploy
sudo mkdir -p ${deploy_dir}
sudo cp build/libs/core.jar ${deploy_dir}/core.jar
sudo cp run.sh ${deploy_dir}/run.sh

# systemd
sudo systemctl stop ${service_name}
sudo systemctl disable ${service_name}
sudo rm /etc/systemd/system/${service_file}
sudo cp ${service_file} /etc/systemd/system/${service_file}
sudo systemctl enable ${service_name}
