# Sample Vespa Implementation for Forum Search
At _VerticalScope Inc._, we use Vespa for powering our search and recommendation engine for 1000+ internet forums. We're providing a sample app here to sort of mimic how we use it internally using a sample forum dataset.

This is a sample implementation of Vespa search on Forum Data. We're going to be using data from https://diy.stackexchange.com/ for this example.

## Setup instructions
- Currently it's based on local Docker images. Kubernetes/Helm implementation coming up soon!
- Meanwhile, here are the docker instructions to get this up and running:

### Setup Dataset in a SQL Instance
1. Run a docker instance for MySQL using the command (We know this is unsafe for now. This will change when we implement Kubernetes yaml files): 
   ```
   docker run --name vespamysql  -p 3306:3306 -p 33060:33060 -e MYSQL_ROOT_PASSWORD=<YOUR_PASSWORD> -e MYSQL_ROOT_HOST=% -d mysql:5.7
   ```
2. Follow the instructions in the [dataset/README.md](dataset/README.md) to download and upload the https://diy.stackexchange.com/ to the MySQL docker instance

### Setup Vespa docker
1. Follow the instructions in the [app/README.md](app/README.md) to setup the Vespa docker on localhost

### Index forum data into Vespa
1. Ensure that the above steps (dataset in SQL and Vespa docker setup) are complete and are running properly.
2. Follow the instructions in the [tools/vespaindexer/README.md](tools/vespaindexer/README.md) to load all the forum data into Vespa docker.