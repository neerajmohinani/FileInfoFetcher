# FileInfoFetcher

Run Executable jar to run the program, dependencies are in the folder FileInfoFetcher/Executablejar/Fileinfofetcher_lib/ 
Make sure Fileinfofetcher_lib and Fileinfofetcher.jar are in same folder to run the program.
run from command line: java - jar <path-of jar>/Fileinfofetcher.jar


Set up the code by copying the src folder in your project and add jars in the library folder to classpath.

Input:
there are two ways to provide input:
 1. give path of the xlsx file.
     file should have each filename(along with extension) in the first column of different rows.
     
 2. give the path of directory to scan all the files in that directory or sub directories of that directory.
 
 output:
    xlsx report will be generated as output.
