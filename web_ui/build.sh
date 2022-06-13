#!/bin/bash

# install packages
npm install

# build
npm run build --verbose && gzipper c build --verbose
