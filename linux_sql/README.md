# Linux Cluster Monitoring Agent
# Introduction :wave:

The Linux Cluster Monitoring Agent (LCMA) is a cluster monitoring software tool created for the Linux Cluster Administration (LCA) team. The tool's purpose is to manage and record hardware specifications and resource usage of their Linux clusters. 
 The LCMA project was tested on a Jarvis Virtual Machine, created and hosted by a Google Cloud Platform. Each server is internally connected through a switch that enables them to communicate through an internal IPv4 address. The appropriate data are collected and recorded in a containered Relational Database Management System (RDBMS) in real-time for the LCA team to generate reports for future resource planning purposes. In addition, crontab was implemented as a monitor agent to acquire usage of each node in the network every minute. 

Finally, the hardware and usage data were tested using a Docker PostgresSQL container using a postgres:9.6-alpine image. 
Each script was developed in Jarvis Virtual Machine using IntelliJ IDEA as Integrated Development Environment(IDE). Used Git for version control and the source codes were uploaded to GitHub utilizing a Secure Shell Protocol(SSH) connection.

The following techonologies were used: 
- Linux (Centos 7)
- Bash
- Git / Github
- Docker
- Postgres SQL
- Google Cloud Platform
- Crontab

# Quick Start

1. Start a psql instance using `psql_docker.sh`
  ```
  # Create a PSQL docker container with the given username and password
  # Print error message if username/password is not given
  # Print error message if the container already exist
  ./scripts/psql_docker.sh create [db_username][db_password]
  ```
  ```
  # Start the PSQL docker container
  ./scripts/psql_docker.sh start 
  ```
  ```
  # Stop the running PSQL docker container
  ./scripts/psql_docker.sh stop 
  ```
2. Create tables using ddl.sql
  ```
  psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
  ```
3. Insert hardware specs data into the DB using host_info.sh
  ```
  ./scripts/host_info.sh [host] [port] [database] [username] [password]
  ```
4. Insert hardware usage data into the DB using host_usage.sh
  ```
  ./scripts/host_usage.sh [host] [port] [database] [username] [password]
  ```
6. Crontab Setup
  ```
  # Edit crontab jobs
  crontab -e
  
  # Add this to crontab
  * * * * * bash <PATH_TO_SCRIPT>/host_agent/scripts/host_usage.sh localhost 5432 host_agent Postgres password > /tmp/host_usage.log
  ```


# Implemenation
feature/psql_docker (:white_check_mark:):
- Provisioned PostgreSQL instance using Docker 9.6-alpine image.
- Bash script to create a PSQL database 

feature/creating_ddl (:white_check_mark:):
- Creating host_info table to store hardware specifications.
- Creating host_usage table to store usage data.

feature/sql_queries (:negative_squared_cross_mark:):
- Group hosts by CPU number and sort by their memory size in descending order
- Average used memory in percentage over 5min intervals for each host
- Detecting host failure within a 5-minute interval

## Architecture
![alt text](https://github.com/jarviscanada/jarvis_data_eng_HyebinYoon/blob/feature/readme_file/linux_sql/assets/linux_SQL_arch.jpg?raw=true)


## Scripts

`psql_docker.sh`
- Create PSQL docker container
  - `./scripts/psql_docker.sh [create/start/stop] [username] [password]` 
- Start the PSQL docker container
  - `./scripts/psql_docker.sh start`
- Stop the PSQL docker container
  - `./scripts/psql_docker.sh start` 
  
`PSQL instance`
- Connect to the PSQL instance
  - `psql -h localhost -U Postgres -W`
- List all database
  - `\l`
- Create a database
  - `CREATE DATABASE <db_name>;`
- Connect to the new database
  - `\c <db_name>;`

`host_info.sh`: this script collects hardware data and inserts the data into the appropriate table value.
- `./scripts/host_info.sh [host] [port] [database] [username] [password]`
  - Acquire the following information about node's hardware data: hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, and timestamp

`host_usage.sh`: this script collects server usage data and inserts the data into the appropriate table value.
- `bash scripts/host_usage.sh [host] [port] [database] [username] [password]`
  - Acquire following information of node's usage data: host_id(from host_info table), memory_free, cpu_idle, cpu_kernel, disk_io, disk_available, and timestamp 
  
  
- `crontab` : crontab script will execute every minute to insert the server usage data to PSQL database
  - `crontab -l` : list crontab jobs
  - `crontab -e` : edit crontab file
 
- `queries.sql` 
  - Under development 

## Database Modeling
- `host_info` Table 

| Attributes(Columns) | Data Type | Constraint | Description |
| ------------------- | --------- | --------- | -------------------------------- |
| id | SERIAL | Primary Key | Auto-incremented number to distinguish different computers(Primary Key) |
| hostname | VARCHAR  | UNIQUE & NOT NULL | Name of the computer |
| cpu_number | INTEGER | NOT NULL | Number of CPUs on the computer |
| cpu_archiecture| VARCHAR | NOT NULL | CPU archiecture type |
| cpu_model | VARCHAR | NOT NULL | CPU model |
| cpu_mhz | DECIMAL | NOT NULL | clock speed in MHz |
| L2_cache | INTEGER | NOT NULL | L2 cache size in kB |
| total_mem | INTEGER | NOT NULL | Total memory on the host node in kB |
| `"timestamp"`| TIMESTAMP | NOT NULL | time when the data was entered  |


- `host_usage` Table

| Attributes(Colums) | Data Type | Constraint | Description |
| ------------------- | --------- | --------- | -------------------------------- |
| host_id | SERIAL | Foreign Key | Foreign key referencing `id` from `host_info` table |
| memory_free | INTEGER | NOT NULL | Computer's available memory in kB |
| cpu_idle | INTEGER | NOT NULL | CPU processor idle time in % |
| disk_io | INTEGER | NOT NULL | Number of disk I/O |
| disk_available | INTEGER | NOT NULL | Total available disk space in MB |
| `"timestamp"`| TIMESTAMP | NOT NULL | time when the data was entered |


# Test

The LCMA was developed and tested on Jarvis Virtual Machin using CentOS 7 as the operating system and Google Cloud Platform as a host. The test cases were executed on the bash scripts : `psql_docker.sh`, `ddl.sql`, `host_info.sh`, `host_usage.sh`, and `queries.sql` with both valid and invalid arguments. The details of each test case were commented on top of each script. The data was carefully examined on PostgresSQL and DBeaver. 

![alt text](https://github.com/jarviscanada/jarvis_data_eng_HyebinYoon/blob/feature/readme_file/linux_sql/assets/example1.png?raw=true)

> Screenshot 1 is an example of the host_usage data table

# Deployment
The source code utilized Git to manage the project versions and was pushed to the GitHub repository following Gitflow. 
`main branch` : final product
`release branch` : prototype of the product
`develop branch` : developing product 
`feature branch` : adding a new feature to the product

# Improvements
- Implement the sql_queries feature (planned)
- Tracking Hardware and Software modification
- Adding more columns to the tables
- More detailed Architecture diagram
