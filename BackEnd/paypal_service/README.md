## Paypal service

## Docker

1. Build docker image

```
$ docker build --tag xocbayar/paypal-service .
```

2. Push docker image to docker hub

```
$ docker push --all-tags xocbayar/paypal-service
```

## Kubernetes

```
$ helm repo add bitnami https://charts.bitnami.com/bitnami
$ helm install hotel-mongodb --set auth.rootPassword=secretpassword bitnami/mongodb

$ kubectl create deployment paypal-service --image=xocbayar/paypal-service --dry-run=client -o=yaml > paypal-deployment.yaml 

$ echo --- >> paypal-deployment.yaml

$ kubectl create service loadbalancer paypal-service --tcp=9002:9002 --dry-run=client -o=yaml >> paypal-deployment.yaml

$ kubectl apply -f paypal-deployment.yaml

$ minikube tunnel

$ kubectl port-forward svc/paypal-service 9002:9002
```

### Application properties

```
spring.data.mongodb.uri=mongodb://root:secretpassword@hotel-mongodb.default.svc.cluster.local:27017/paypal_DB?authSource=admin
```

#to save the paypal information
-------------------------
POST:http://localhost:3000/paypals

{

        "cardNumber": "5525 4773 373",
        "cardName": "mi one",
        "ccv": "843",
        "paypalNumber":"1234",
         "emailAddress":"giriPriya@gmail.com",
        "balance": 2000.0
    }

# to get all the paypal

GET:http://localhost:3000/paypals

# to update paypal from other services

PUT:http://localhost:3000/paypals/

{
"paypalId": "62acaa98b0caeb20f05d3b15",
"userName": "username",
"cardNumber": "11111111",
"cardName": null,
"emailAddress":"giriPriya@gmail.com"
"paypalNumber":"1234"
"balance": 80009.0 }

# To update the credit card locally

PUT:http://localhost:3000/paypals/{paypalId}

{

    "userName": "username",
    "cardNumber": "11111111",
    "cardName": null,
    "emailAddress":"giriPriya@gmail.com"

"paypalNumber":"1234"
"balance": 10000.0 
}