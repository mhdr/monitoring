#!/bin/bash

deploy_dir=/opt/monitoringv5/web
service_name=monitoringv5_web.service
service_file=monitoringv5_web.service

parent_dir=$(cd ../ && pwd)
webui_dir=${parent_dir}/web_ui
echo $webui_dir

pushd ${webui_dir}
    ./build.sh
popd

rm -R src/main/resources/static/*
mkdir -p src/main/resources/static
cp -R ${webui_dir}/build/* src/main/resources/static

# build
gradle build

# deploy
sudo mkdir -p ${deploy_dir}
sudo cp build/libs/web_server.jar ${deploy_dir}/web_server.jar
sudo cp run.sh ${deploy_dir}/run.sh
sudo dos2unix ${deploy_dir}/run.sh
sudo chmod +x ${deploy_dir}/run.sh

# systemd
sudo systemctl stop ${service_name}
sudo systemctl disable ${service_name}
sudo rm /etc/systemd/system/${service_file}
sudo cp ${service_file} /etc/systemd/system/${service_file}
sudo systemctl enable ${service_name}
