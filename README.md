# vm-provider

Microservice that is responsible for providing vehicle manufacturer information.

## Installation

**1-) Download the project**

````text
git clone git@github.com:mryakar/vm-provider.git
````

**2-) Build the project**

````shell
mvn clean package
````

**3-) Create Docker image**

Run as root;

````shell
docker build --tag=vm-provider:latest .
````

**4-) Run**

Run as root;

````shell
docker run -d --name vm-provider -p 8090:8090 vm-provider:latest
````

**5-) Check**

Run as root;

````shell
docker container ls
````

**6-) Swagger**

Open the browser and go to this address;

http://localhost:8090/swagger-ui/

## Developers

* mryakar