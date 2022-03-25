#!/usr/bin/env bash

dos2unix manager.sh
chmod +x manager.sh
git update-index --chmod=+x manager.sh

dos2unix install.sh
chmod +x install.sh
git update-index --chmod=+x install.sh

dos2unix run.sh
chmod +x run.sh
git update-index --chmod=+x run.sh