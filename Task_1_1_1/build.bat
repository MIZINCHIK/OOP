javadoc -author -d .\script\ -sourcepath .\src\main\java -subpackages io.github.mizinchik

javac -d .\script\bin\ -sourcepath .\src\main\java .\src\main\java\io\github\mizinchik\HeapSort.java

mkdir .\script\jar
jar cf .\script\jar\HeapSort.jar -C .\script\bin .