# Ktor

## Links
- [Ktor Project Generator](https://start.ktor.io/#/final?name=ktorsocialnetwork&website=exaaaample.com&artifact=com.exaaaample.ktorsocialnetwork&kotlinVersion=1.6.0&ktorVersion=1.6.5&buildSystem=GRADLE_KTS&engine=NETTY&configurationIn=CODE&addSampleCode=true&plugins=routing%2Cktor-websockets%2Ccontent-negotiation%2Cktor-gson%2Ccall-logging%2Cdefault-headers%2Ccors%2Cstatic-content%2Cauth%2Cauth-jwt)
- [Koin](https://insert-koin.io/)
    - DI for Kotlin project
- [KMongo](https://litote.org/kmongo/)
    - Kotlin toolkit for Mongo

## Memo

### Request
```sh
$ curl -X POST -H "Content-Type: application/json" -d "{'email':'test@test.com', 'username':'test','password':'test'}" localhost:8080/api/user/create
$ curl -X POST -H "Content-Type: application/json" -d "{'email':'test@test.com', 'username':'test','password':'test'}" localhost:8080/api/user/login
```
