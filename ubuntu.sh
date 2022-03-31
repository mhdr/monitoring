#!/bin/bash


# installing packages for ubuntu 20.04

#################################################################### general ################################################################################

mkdir -p /root/.monitoringv5

#################################################################### required ###############################################################################

apt install -y dos2unix build-essential curl zip unzip

#################################################################### config ubuntu ##########################################################################

if ! grep -q "fs.inotify.max_user_watches=524288" "/etc/sysctl.conf" ; then
         echo fs.inotify.max_user_watches=524288 | tee -a /etc/sysctl.conf ;  
fi

if ! grep -q "fs.file-max=65535" "/etc/sysctl.conf" ; then
         echo fs.file-max=65535 | tee -a /etc/sysctl.conf ;  
fi

if ! grep -q "* soft     nproc          65535" "/etc/security/limits.conf" ; then
         echo '* soft     nproc          65535' | tee -a /etc/security/limits.conf ;  
fi

if ! grep -q "* hard     nproc          65535" "/etc/security/limits.conf" ; then
         echo '* hard     nproc          65535' | tee -a /etc/security/limits.conf ;  
fi

if ! grep -q "* soft     nofile         65535" "/etc/security/limits.conf" ; then
         echo '* soft     nofile         65535' | tee -a /etc/security/limits.conf ;  
fi

if ! grep -q "* hard     nofile         65535" "/etc/security/limits.conf" ; then
         echo '* hard     nofile         65535' | tee -a /etc/security/limits.conf ;  
fi

if ! grep -q "root soft     nproc          65535" "/etc/security/limits.conf" ; then
         echo 'root soft     nproc          65535' | tee -a /etc/security/limits.conf ;  
fi

if ! grep -q "root hard     nproc          65535" "/etc/security/limits.conf" ; then
         echo 'root hard     nproc          65535' | tee -a /etc/security/limits.conf ;  
fi

if ! grep -q "root soft     nofile         65535" "/etc/security/limits.conf" ; then
         echo 'root soft     nofile         65535' | tee -a /etc/security/limits.conf ;  
fi

if ! grep -q "root hard     nofile         65535" "/etc/security/limits.conf" ; then
         echo 'root hard     nofile         65535' | tee -a /etc/security/limits.conf ;  
fi

if ! grep -q "session required pam_limits.so" "/etc/pam.d/common-session" ; then
         echo 'session required pam_limits.so' | tee -a /etc/pam.d/common-session ;  
fi

#################################################################### mongodb ################################################################################

apt install gnupg
wget -qO - https://www.mongodb.org/static/pgp/server-5.0.asc | apt-key add -
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/5.0 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-5.0.list
apt update
apt install -y mongodb-org
systemctl enable mongod.service

mongo
sleep 1s
use admin
sleep 1s
db.createUser({user: "monitoringv5",pwd: "monitoringv5", roles: [ { role: "userAdminAnyDatabase", db: "admin" }, "readWriteAnyDatabase" ]})
sleep 1s
exit
sleep 1s
if ! grep -q "authorization: enabled" "/etc/mongod.conf" ; then
         echo 'security:' | tee -a /etc/mongod.conf ;  
         echo '  authorization: enabled' | tee -a /etc/mongod.conf ;
fi
systemctl daemon-reload
systemctl start mongod.service

#################################################################### nodejs #################################################################################

curl -fsSL https://deb.nodesource.com/setup_lts.x | -E bash -
apt-get install -y nodejs

#################################################################### yarn ###################################################################################

curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list
sudo apt update && sudo apt install yarn

#################################################################### dotnet #################################################################################

wget https://packages.microsoft.com/config/ubuntu/20.04/packages-microsoft-prod.deb -O packages-microsoft-prod.deb
dpkg -i packages-microsoft-prod.deb
rm packages-microsoft-prod.deb
apt-get update
apt-get install -y apt-transport-https
apt-get update
apt-get install -y dotnet-sdk-6.0

#################################################################### python #################################################################################

apt install -y python3-dev python3-all python3-pip python3-venv

#################################################################### sdkman #################################################################################

curl -s "https://get.sdkman.io" | bash
source "/root/.sdkman/bin/sdkman-init.sh"

#################################################################### java #################################################################################

sdk install java 18-open
sdk default java 18-open

#################################################################### gradle ###############################################################################

sdk install gradle 7.4.2
sdk default gradle 7.4.2

#################################################################### rabbitmq ##############################################################################

apt-get install curl gnupg apt-transport-https -y

## Team RabbitMQ's main signing key
curl -1sLf "https://keys.openpgp.org/vks/v1/by-fingerprint/0A9AF2115F4687BD29803A206B73A36E6026DFCA" | gpg --dearmor | tee /usr/share/keyrings/com.rabbitmq.team.gpg > /dev/null
## Cloudsmith: modern Erlang repository
curl -1sLf https://dl.cloudsmith.io/public/rabbitmq/rabbitmq-erlang/gpg.E495BB49CC4BBE5B.key | gpg --dearmor | tee /usr/share/keyrings/io.cloudsmith.rabbitmq.E495BB49CC4BBE5B.gpg > /dev/null
## Cloudsmith: RabbitMQ repository
curl -1sLf https://dl.cloudsmith.io/public/rabbitmq/rabbitmq-server/gpg.9F4587F226208342.key | gpg --dearmor | tee /usr/share/keyrings/io.cloudsmith.rabbitmq.9F4587F226208342.gpg > /dev/null

## Add apt repositories maintained by Team RabbitMQ
tee /etc/apt/sources.list.d/rabbitmq.list <<EOF
## Provides modern Erlang/OTP releases
##
deb [signed-by=/usr/share/keyrings/io.cloudsmith.rabbitmq.E495BB49CC4BBE5B.gpg] https://dl.cloudsmith.io/public/rabbitmq/rabbitmq-erlang/deb/ubuntu bionic main
deb-src [signed-by=/usr/share/keyrings/io.cloudsmith.rabbitmq.E495BB49CC4BBE5B.gpg] https://dl.cloudsmith.io/public/rabbitmq/rabbitmq-erlang/deb/ubuntu bionic main

## Provides RabbitMQ
##
deb [signed-by=/usr/share/keyrings/io.cloudsmith.rabbitmq.9F4587F226208342.gpg] https://dl.cloudsmith.io/public/rabbitmq/rabbitmq-server/deb/ubuntu bionic main
deb-src [signed-by=/usr/share/keyrings/io.cloudsmith.rabbitmq.9F4587F226208342.gpg] https://dl.cloudsmith.io/public/rabbitmq/rabbitmq-server/deb/ubuntu bionic main
EOF

## Update package indices
apt-get update -y

## Install Erlang packages
apt-get install -y erlang-base \
                        erlang-asn1 erlang-crypto erlang-eldap erlang-ftp erlang-inets \
                        erlang-mnesia erlang-os-mon erlang-parsetools erlang-public-key \
                        erlang-runtime-tools erlang-snmp erlang-ssl \
                        erlang-syntax-tools erlang-tftp erlang-tools erlang-xmerl

## Install rabbitmq-server and its dependencies
apt-get install rabbitmq-server -y --fix-missing
systemctl enable rabbitmq-server
systemctl start rabbitmq-server
rabbitmq-plugins enable rabbitmq_management
rabbitmqctl delete_user monitoringv5
rabbitmqctl add_user monitoringv5 monitoringv5
rabbitmqctl set_user_tags monitoringv5 administrator
rabbitmqctl set_permissions -p / monitoringv5 ".*" ".*" ".*"
rabbitmqctl delete_user guest
systemctl restart rabbitmq-server

#################################################################### mysql ##############################################################################

apt install mysql-server
mysql -u root
sleep 1s
CREATE USER 'monitoringv5'@'localhost' IDENTIFIED BY 'monitoringv5';
sleep 1s
GRANT ALL PRIVILEGES ON * . * TO 'monitoringv5'@'localhost';
sleep 1s
ALTER USER 'monitoringv5'@'localhost' IDENTIFIED WITH mysql_native_password BY 'monitoringv5';
sleep 1s
FLUSH PRIVILEGES;
sleep 1s
exit

#################################################################### redis ##############################################################################

apt install redis-server
if ! grep -q "requirepass monitoringv5" "/etc/redis/redis.conf" ; then
         echo 'requirepass monitoringv5' | tee -a /etc/redis/redis.conf ;  
fi
systemctl restart redis.service
