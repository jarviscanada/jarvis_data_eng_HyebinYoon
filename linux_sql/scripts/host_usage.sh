#!/bin/bash

# Hyebin(Heather) Yoon

# Set arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne 5 ]; then
  echo "Please enter correct number of parameters"
  echo "Example: psql_host psql_port db_name psql_user psql_password "
exit 1
fi

hostname=$(hostname -f)
vmstat_mb=$(vmstat --unit M)
meminfo_out=$(cat /proc/meminfo)

#usage info / in KB
memory_free=$(echo "$meminfo_out"  | egrep "^MemFree:" | awk '{print $2}' | xargs)

#cpu id /in percentage
cpu_idle=$(echo "$vmstat_mb" | awk '{print $15}' | tail -n1 | xargs)

#cpu sy /in percentage
cpu_kernel=$(echo "$vmstat_mb" | awk '{print $14}' | tail -n1 | xargs)

#the value you are looking for is IO|cur / cur: I/O in progress
disk_io=$(vmstat -d  | egrep "^sda" | awk '{print $10}' | xargs)

disk_available=$(df -BM / | awk '{print $4}' | tail -n1 | cut -d "M" -f 1 | xargs) #megabyte MB  root directory available disk

timestamp=$(date +"%Y-%m-%d %H:%M:%S")
#current timestamp in `2019-11-26 14:40:19` format

#Host usage database insertion

#host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

# Insert data command
insert_stmt="INSERT INTO host_usage(timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available) VALUES('$timestamp',(SELECT id FROM host_info WHERE hostname='$hostname'),'$memory_free','$cpu_idle','$cpu_kernel','$disk_io','$disk_available');"

export PGPASSWORD=$psql_password

# Insert data to database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?

