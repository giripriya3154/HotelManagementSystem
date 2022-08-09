## Rating service
- For rating a room at MIU
- Follow the below steps to run this service:

## Docker

1. Build docker image
```
$ docker build --tag xocbayar/rate-service .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/rate-service
```

## Kubernetes
```
$ helm repo add bitnami https://charts.bitnami.com/bitnami
$ helm install hotel-mongodb --set auth.rootPassword=secretpassword bitnami/mongodb

$ kubectl create deployment rate-service --image=xocbayar/rate-service --dry-run=client -o=yaml > rate-deployment.yaml 

$ echo --- >> rate-deployment.yaml

$ kubectl create service loadbalancer rate-service --tcp=8099:8099 --dry-run=client -o=yaml >> rate-deployment.yaml

$ kubectl apply -f rate-deployment.yaml

$ kubectl port-forward svc/rate-service 8099:8099
```
### Application properties
```
spring.data.mongodb.uri=mongodb://hoteluser:hotelpass@hotel-rate-mongodb.default.svc.cluster.local:27017/rate_DB
<<<<<<< HEAD
```

## Rating Service CRUD

### POST
- POST endpoint: [ http://localhost:8088/]( http://localhost:8088/)
- You need to get roomId from :  [http://localhost:8088/room](http://localhost:8088/room) first
- userID will get from cookie of User (using jwt)
- Request body
```
{
    "roomId": "62a92e51b35c623b239e6488",
    "rating": 5
}
```
- Response
```
Rate has been added successfully.
```
- It will insert rating into room service : [ http://localhost:8088/room]( http://localhost:8088/room)
```
[
    {
        "roomId": "62a92e51b35c623b239e6488",
        "roomNumber": 5,
        "type": "VIP",
        "price": 10.0,
        "bedType": "goood",
        "numberOfBeds": 3,
        "maxNumberOfGuests": 10,
        "smoking": false,
        "description": "Good",
        "available": false,
        "roomRating": "5 star",
        "totalRatings": 5
    }
]
```


### GET
- GET endpoint: [ http://localhost:8088/]( http://localhost:8088/)
- Response of will be look like:
```
[
    {
        "id": "62a9c794ec530448782c6f32",
        "roomId": "62a92e51b35c623b239e6488",
        "userId": "62a9c6b673c22b657f6b1bef",
        "rating": 5
    },
    {
        "id": "62aa56ac4ce14d5e2d99cd01",
        "roomId": "62a92e51b35c623b27a98091",
        "userId": "62a9c6b673c22b657f6b1bef",
        "rating": 4
    }
]
```
### UPDATE
- UPDATE endpoint: [ http://localhost:8088/{rateId}]( http://localhost:8088/62aa56ac4ce14d5e2d99cd01)
- User cannot update other rating
- Request body
```
{
    "roomId": "62a92e51b35c623b239e6488",
    "rating": 3
}
```
- Response
```
Rate has been updated successfully.
```

### DELETE
- DELETE endpoint: [ http://localhost:8088/{rateId}]( http://localhost:8088/62aa56ac4ce14d5e2d99cd01)
- If you delete success you will get response:
```
Rate has been deleted successfully.
```


## License
[MIU](https://gitlab.com/miu3/sa/big-project/-/tree/main)
=======
