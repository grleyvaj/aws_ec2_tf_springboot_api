#!/bin/bash

# Generate SSH if not exist
if [ ! -f ~/.ssh/aws_tf_springboot_key ]; then
  ssh-keygen -t rsa -b 4096 -f ~/.ssh/aws_tf_springboot_key -N "" >/dev/null 2>&1
fi

# Get public key
ssh_public_key=$(cat ~/.ssh/aws_tf_springboot_key.pub)

# Give public key in JSON format
echo "{\"public_key\": \"$ssh_public_key\"}"