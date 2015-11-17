# strangers
Don't talk to strangers [ISTA](https://istacon.org/)


Prerequisites
-------------

To clone the project you'll need [git](https://git-scm.com/downloads)

To execute it locally and modify it:
* [java](https://java.com/en/download/)
* [maven](https://maven.apache.org/)

Running as container requires:
* Linux distro or [Docker Machine](https://docs.docker.com/machine/) on MacOS
* [docker](https://www.docker.com/)

Clone the project
-----------------

```
git clone https://github.com/hsiliev/strangers.git
cd strangers/fiscal
```


Running locally
---------------

```
mvn clean install
```


Run in container
----------------

```
docker build -t fiscal .
```

Start Concourse
---------------

From `strangers/fiscal` directory:

```
cd ../concourse
vagrant up
```

Request `http://192.168.100.4:8080` and download the Fly CLI from the bottom-right.


Upload the pipeline
-------------------

From `concourse` directory:
```
fly set-pipeline -p fiscal -c pipeline.yml
```

If you are runnig Windows try this:
```
fly set-pipeline /p fiscal /c pipeline.yml
```

Presentation
------------

Check the [presentation README](https://github.com/hsiliev/strangers/blob/master/presentation/README.md).
