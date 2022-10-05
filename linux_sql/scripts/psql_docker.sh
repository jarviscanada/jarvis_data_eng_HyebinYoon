#!/bin/sh

# Developer:  Hyebin Yoon
# Test : 3 (create/start/stop) done


# assigning CLI agruments to variables
cmd=$1
db_username=$2
db_password=$3

#starting docker
sudo systemctl status docker || sudo systemctl start docker

# if status =0, then command was successfully executed => container exist
# status=1 means command failed => no container
docker container inspect jrvs-psql
container_status=$?

# Switch case to check the prompt (create/stop/start)
case $cmd in
  # For create CLI
  create)

  # Check if the container is already created
  if [ $container_status -eq 0 ]; then
		echo 'Container already exists'
		exit 1
	fi

  #If CLI agrument is less than 3 then we need username/password
  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi

  #Create container
  docker pull postgres
	docker volume create pgdata
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=$db_password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine

	# already checked if container exist or not,
	# only check if container is running
	docker ps -f name=jrvs-psql

	exit $?
	;;

  # if $cmd is start/stop
  start|stop)

  # if container status = 1 then container has not been created exit 1
  if [ $container_status -eq 1 ]; then
    echo 'Container has not been created'
    exit 1
  fi


  #Start or stop the container
	docker container $cmd jrvs-psql
	exit $?
	;;

  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac

exit 0
