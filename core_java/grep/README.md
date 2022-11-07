# Introduction
The Grep Application imitates Linux `grep` command. From a given root directory, the grep app search through all the files in the folder
and the sub-folders to find text patterns and output matched lines to a specific file. The application was 
tested using various test cases on the main method and utilized JUNIT testing. The application was dockerized and deployed on the Docker Hub registry.


### Technologies Used: 
Core Java, Maven, SLF4J/Log4j2 libraries, JUnit/Stream API, Lambda, IntelliJ IDE, and Docker

# Quick Start
Application accepts the following three arguments: regex rootPath outFile
- `regex` : a special text string for describing a search pattern
- `rootPath` : root directory path
- `outFile` : output file name, located in out folder 

To run the application:
1. Using Jar File
```
mvn clean compile package
java -jar target/grep-1.0-SNAPSHOT.jar ${regex_pattern} ${src_dir} ./out/${outfile}
```
2. Using [Docker Image](https://hub.docker.com/r/binyoon99/grep)
```
docker pull binyoon99/grep
docker run --rm -v `pwd`/data:/data -v `pwd`/out:/out binyoon99/grep ${regex_pattern} ${src_dir} /out/${outfile}
```

# Implemenation
## Pseudocode
```
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```
## Performance Issue
The performance issue can occur when the application is performed on a large file. The application threw an OutOfMemoryError exception 
as an output when the file in the directory exceeds the heap memory of the Java Virtual Machine. 


# Test
The manual test was done on the application by executing multiple sample data to the program. 
##### These sample data include
- use of different regex, root path, and output file name 
- modification of file names and their content
- changing heap memory size based on a maximum and minimum file size
- creating empty files and directories 
- creating duplicate files and directories names with lowercase and uppercase letters
- passing incorrect agruments 


# Deployment
The grep app was dockerized and distributed on Docker Hub. The Docker image can be explored on [Docker Hub](https://hub.docker.com/r/binyoon99/grep) or pull directly ``` docker pull binyoon99/grep```.

# Improvement
- Implement a visual representation of the application for users
  - Use GUI(Graphic User Interface). 
- Implement Stream API to every component
