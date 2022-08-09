## Credit card service

## Docker 

1. Build docker image
```
$ docker build --tag xocbayar/creditcard-service .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/creditcard-service
```

## Kubernetes
```
$ helm repo add cnieg https://cnieg.github.io/helm-charts
$ helm install creditcard-db cnieg/h2-database --version 1.0.3

$ kubectl create deployment creditcard-service --image=xocbayar/creditcard-service --dry-run=client -o=yaml > creditcard-deployment.yaml 

$ echo --- >> creditcard-deployment.yaml

$ kubectl create service loadbalancer creditcard-service --tcp=9001:9001 --dry-run=client -o=yaml >> creditcard-deployment.yaml

$ kubectl apply -f creditcard-deployment.yaml

$ kubectl port-forward svc/creditcard-service 9001:9001
```
### Application properties
```
spring.data.mongodb.uri=mongodb://hoteluser:hotelpass@hotel-creditcard-mongodb.default.svc.cluster.local:27017/creditcard_DB
```
#for the operation of credit card

#to save the credit card information
-------------------------
POST:http://localhost:3000/creditcards

{


        "cardNumber": "5525 4773 373",
        "cardName": "mi one",
        "ccv": "843",
        "cardLimit": 4000.0,
        "expiryDate": "2007-12-12T00:00:00.000+00:00",
        "balance": 2000.0
    }

#to get all the credit cards
GET:http://localhost:3000/creditcards


#to update credit card from other services
PUT:http://localhost:3000/creditcards/

{
"creditCardId": "62acaa98b0caeb20f05d3b15",
"userName": "username",
"cardNumber": "11111111",
"cardName": null,
"ccv": "1111",
"cardLimit": 7000.0,
"expiryDate": "2024-12-12T00:00:00.000+00:00",
"balance": 80009.0
}

#To update the credit card locally
PUT:http://localhost:3000/creditcards/{creditCardId}

{

    "userName": "username",
    "cardNumber": "11111111",
    "cardName": null,
    "ccv": "1111",
    "cardLimit": 7000.0,
    "expiryDate": "2024-12-12T00:00:00.000+00:00",
    "balance": 10000.0
}



