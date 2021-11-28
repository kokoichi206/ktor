# Ktor

## Links
- [Ktor Project Generator](https://start.ktor.io/#/final?name=ktorsocialnetwork&website=exaaaample.com&artifact=com.exaaaample.ktorsocialnetwork&kotlinVersion=1.6.0&ktorVersion=1.6.5&buildSystem=GRADLE_KTS&engine=NETTY&configurationIn=CODE&addSampleCode=true&plugins=routing%2Cktor-websockets%2Ccontent-negotiation%2Cktor-gson%2Ccall-logging%2Cdefault-headers%2Ccors%2Cstatic-content%2Cauth%2Cauth-jwt)
- [Koin](https://insert-koin.io/)
    - DI for Kotlin project
- [KMongo](https://litote.org/kmongo/)
    - Kotlin toolkit for Mongo

## Memo

### Request
``` sh
# Login
$ curl -X POST -H "Content-Type: application/json" -d "{'email':'test@test.com', 'username':'test','password':'test'}" localhost:8080/api/user/create
$ curl -X POST -H "Content-Type: application/json" -d "{'email':'test@test.com', 'username':'test','password':'test'}" localhost:8080/api/user/login

curl -X POST -H "Content-Type: application/json" -d "{'email':'test_token@test.com', 'username':'token','password':'test'}" localhost:8001/api/user/login
```

``` sh
# Post comment
curl -X POST -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJtYWluIiwiaXNzIjoiaHR0cDovLzAuMC4wLjA6ODAwMSIsImV4cCI6MTY2OTYxNTQ2OCwiZW1haWwiOiJ0ZXN0X3Rva2VuQHRlc3QuY29tIn0.wLKv_L_2-7DSC6JQaaIAC9abJrDVdos-EiI1f_d5QV4' -H "Content-Type: application/json" -d "{'userId':'61a317bf211c770cb30e451d', 'description':'post test test'}" localhost:8001/api/post/create

curl -f -X POST -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJtYWluIiwiaXNzIjoiaHR0cDovLzAuMC4wLjA6ODAwMSIsImV4cCI6MTY2OTYyNDcxNiwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIn0.2plW-z21m2cIkVJOa4DKip0CRYFLoSIdV91t8ih4KaE' -H "Content-Type: application/json" -d "{'userId':'61a246883eee374e403451a7', 'description':'post test person different'}" localhost:8001/api/post/create
```

``` sh
# Follow
curl -f -X POST -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJtYWluIiwiaXNzIjoiaHR0cDovLzAuMC4wLjA6ODAwMSIsImV4cCI6MTY2OTYyNDcxNiwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIn0.2plW-z21m2cIkVJOa4DKip0CRYFLoSIdV91t8ih4KaE' -H "Content-Type: application/json" -d "{'followingUserId':'61a246883eee374e403451a7', 'followedUserId':'61a317bf211c770cb30e451d'}" localhost:8001/api/following/follow
```

```sh
# Get Posts for followers
curl -f -X GET -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJtYWluIiwiaXNzIjoiaHR0cDovLzAuMC4wLjA6ODAwMSIsImV4cCI6MTY2OTYyNDcxNiwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIn0.2plW-z21m2cIkVJOa4DKip0CRYFLoSIdV91t8ih4KaE' -H "Content-Type: application/json" localhost:8001/api/post/get?userId=61a246883eee374e403451a7

# Comment
curl -f -X POST -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJtYWluIiwiaXNzIjoiaHR0cDovLzAuMC4wLjA6ODAwMSIsImV4cCI6MTY2OTYyNDcxNiwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIn0.2plW-z21m2cIkVJOa4DKip0CRYFLoSIdV91t8ih4KaE' -H "Content-Type: application/json" -d "{'comment':'Hello world, this is a test comment', 'postId':'61a31e547272f465eb6f0f19', 'userId':'61a246883eee374e403451a7'}" localhost:8001/api/comment/create
```

### curl memo
- -f オプションで、エラーの時も軽く表示してくれる
