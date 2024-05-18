#!/bin/sh

echo "Installing localstack"
docker-compose up -d


echo "Installing awscli"
pip3 install awscli

echo "Installing awslocal"
pip3 install awscli-local

echo "Creating topics"
awslocal sns create-topic --name ms-users-topic
awslocal sns create-topic --name ms-notifier-topic
awslocal sns create-topic --name ms-open-banking-topic


echo "Creating queues"
awslocal sqs create-queue --queue-name ms-users-queue
awslocal sqs create-queue --queue-name ms-notifier-queue
awslocal sqs create-queue --queue-name ms-open-banking-queue

echo "Creating suscriptions"
awslocal sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:ms-users-topic  --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:ms-open-banking-queu
awslocal sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:ms-open-banking-topic  --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:ms-notifier-queue


