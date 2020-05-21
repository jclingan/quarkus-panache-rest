# Build, package, and deploy a simple greeting service to kubernetes

Simple Quarkus "Hello World" project that deploys native binary to kubernetes

## Package, Build and deploy to kubernetes

Instructions for Docker Desktop Kubernetes and Minikube

### Docker Desktop kubernetes (may work with other distributions too)
This will work with
[Docker Desktop's](https://www.docker.com/products/docker-desktop) built-in
kubernetes. Enable Kubeernetes with Preferences -> Kubernetes -> Enable
(Apply & Restart).

```
$ mvn \
    clean \
    package \
    -DskipTests \
    -Dnative \
    -Dquarkus.native.container-build=true \
    -Dquarkus.kubernetes.deploy=true
```
* **-Dnative**: Compile to native
* **-Dquarkus.native.container-build**: Native compiler should be for Containers (Linux binary)
* **-Dquarkus.kubernetes.deploy**: Deploy to kubernetes

### Minikube

For minikube, this is a multi-step process due to a
[minikube limitation](https://github.com/quarkusio/quarkus/issues/5133#issuecomment-565932366).
In short, minikube cannot mount volumes properly.

1. In the first terminal, build the container-friendly (Linux) binary using your local docker daemon
    ```
    # Terminal 1
    $ mvn package -Pnative -Dquarkus.native.container-build=true
    ```
1.  Switch to the minikube docker host
    ```
    # Terminal 2
    $ eval $(minikube docker-env)
    ```
1. In the second terminal, build the image
    ````
    # Terminal 2
    $ docker build -f src/main/docker/Dockerfile.native -t <your_username>/quarkus-deploy-k8s .
    ````

1. Deploy to kubernetes using the generated kubernetes deployment YAML.
    ````
    # Terminal 2
    $ kubectl apply -f target/kubernetes/kubernetes.yml
    ````
## Check endpoint

Get port number
```
# Termiinal 2
$ PORT=`kubectl get svc kubernetes-quickstart --output='jsonpath={.spec.ports..nodePort}'`
$ curl localhost:$PORT/greeting
Hello World!!
```
