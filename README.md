This works:
```
$ curl -H "Content-Type: application/json" localhost:8080/person
{"id":1,"age":25,"name":"Duke"}
```

This returns an error, but why? The ".delete" at the end of the impl class looks suspicious
```
$ curl -X POST -H "Content-Type: application/json" -d  '{"name":"demo","age":"10"}' localhost:8080/person
RESTEASY012020: Discovery failed for method org.acme.rest.PersonControllerImpl_772bba7f3925f61a49c0d93159012a40b2de28d6.delete: RESTEASY012005: Cannot guess resource type for service discovery
```

A PUT also works:
```
$  curl -X PUT -H "Content-Type: application/json" -d  '{"name":"demo", "age":"100"}' localhost:8080/person/1
$  curl -H "Content-Type: application/json" localhost:8080/person/1 
{"id":1,"age":100,"name":"demo"}
```

