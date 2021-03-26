# Sample Vespa Implementation for Forum Search

This is a sample implementation of Vespa search on Forum Data. We're going to be using data from https://diy.stackexchange.com/ for this example.

## Setup instructions
- Currently it's based on local Docker images. Kubernetes/Helm implementation coming up soon!
- Meanwhile, here are the docker instructions to get this up and running:

### Setup Dataset in a SQL Instance
1. Run a docker instance for MySQL using the command (We know this is unsafe for now. This will change when we implement Kubernetes yaml files): 
   ```
   docker run --name vespamysql  -p 3306:3306 -p 33060:33060 -e MYSQL_ROOT_PASSWORD=<YOUR_PASSWORD> -e MYSQL_ROOT_HOST=% -d mysql:5.7
   ```
2. Follow the instructions in the dataset/README.md to download and upload the https://diy.stackexchange.com/ to the MySQL docker instance