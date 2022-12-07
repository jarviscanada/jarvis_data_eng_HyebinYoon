-- Hyebin(Heather) Yoon
-- github.com/binyoon99
-- binyoon99@gmail.com

-- connect to host_agent
\c host_agent

-- DDL
-- id,hostname,cpu_number,cpu_architecture,cpu_model,cpu_mhz,L2_cache,total_mem,timestamp
CREATE TABLE IF NOT EXISTS PUBLIC.host_info(
    id                  SERIAL NOT NULL,
    hostname            VARCHAR UNIQUE NOT NULL,
    cpu_number          INTEGER NOT NULL,
    cpu_architecture    VARCHAR NOT NULL,
    cpu_model           VARCHAR NOT NULL,
    cpu_mhz             DECIMAL NOT NULL,
    L2_cache            INTEGER NOT NULL,   --KB
    total_mem           INTEGER NOT NULL, -- KB
    "timestamp"         TIMESTAMP NOT NULL, -- current time in UTC time zone

    PRIMARY KEY (id)
);

-- DML
-- Insert statement



-- DDL
-- timestamp,host_id,memory_free,cpu_idle,cpu_kernel,disk_io,disk_available
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage(
    "timestamp"         TimeSTAMP NOT NULL, -- UTC time zone
    host_id             SERIAL NOT NULL,    -- host id from host_info table
    memory_free         INTEGER NOT NULL,
    cpu_idle            INTEGER NOT NULL,   -- in percentage
    cpu_kernel          INTEGER NOT NULL,   -- in percentage
    disk_io             INTEGER NOT NULL,
    disk_available      INTEGER NOT NULL,
    FOREIGN KEY (host_id) REFERENCES host_info(id)

);

-- DML
-- Insert statement
