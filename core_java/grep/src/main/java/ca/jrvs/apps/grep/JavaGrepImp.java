package ca.jrvs.apps.grep;


import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JavaGrepImp implements JavaGrep{

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;


    public static void main(String[] args) {
        if (args.length !=3 ){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        //Use default logger config
        BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new  JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch(Exception ex){
            javaGrepImp.logger.error("Error: Unable to process", ex);

        }
    }

    /*
            matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
     */

    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<String>();
        for(File file  : listFiles(getRootPath())){
            for (String line : readLines(file)){
                if (containsPattern(line)){
                    matchedLines.add(line);
                }
            }
        }
        writeToFile(matchedLines);
    }

    @Override
    public List<File> listFiles(String rootDir) {
        List<File> fileList = new ArrayList<File>();

        // every files/directories
        File [] files = new File(rootDir).listFiles();

        // check if file is empty
        if (files == null){
            return null;
        }else{
            for (File file : files){

                if (file.isFile()){
                    // if element is file, then add to list
                    fileList.add(file);
                }else if (file.isDirectory()){
                    // else, the element is directory. Resursively find files within the directory and add all to the list
                    fileList.addAll(listFiles(file.getAbsolutePath()));
                }
            }
        }

        return fileList;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> lines = new ArrayList<String>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;

            while((line = reader.readLine()) != null){
                lines.add(line);
            }
            reader.close();

        } catch (IOException e){
            logger.error("Error: File Not Found ", e);
        }
        return lines;
    }

    @Override
    public boolean containsPattern(String line) {
        return Pattern.matches(getRegex(), line);
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        FileOutputStream fout = new FileOutputStream(getOutFile(), true);

        for( String line : lines){
            fout.write(line.getBytes());
            fout.write(10);
        }
        fout.close();
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}
