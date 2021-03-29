# Vespa app for Forum search
## Setup
- Setup environment variables (assuming you're working from the current directory):
  ```
  export VESPA_APP_PATH=$(pwd)
  ```
- Run Vespa docker:
  ```
  docker run --detach --name vespa --privileged --volume $VESPA_APP_PATH:/vespa-app --publish 8080:8080 --publish 5005:5005 vespaengine/vespa
  ```
- Wait for Vespa engine to be ready:
  ```
  watch "docker exec vespa bash -c 'curl -s --head http://localhost:19071/ApplicationStatus'"
  ```
  Exit the watch once you get 200 (OK) status
- Deploy the Vespa search app
  ```
  mvn package && docker exec vespa bash -c '/opt/vespa/bin/vespa-deploy prepare /vespa-app/target/application.zip && /opt/vespa/bin/vespa-deploy activate
  ```
- Wait for the Vespa application to be ready
  ```
  watch "curl -s --head http://localhost:8080/ApplicationStatus"
  ```
  Exit once you get 200 (OK) status
- Vespa docker is ready!
