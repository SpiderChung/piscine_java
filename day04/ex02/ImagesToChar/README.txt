#1. Delete directories
    rm -rf target && rm -rf lib
#2. Create directory:
    mkdir target && mkdir lib
#2. Download libraries:
    echo "Download libraries"
    curl https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar -o lib/jcommander-1.82.jar
    curl https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar -o lib/JCDP-4.0.2.jar
#3. Extract files and replace to directory 'target':
    echo "Extract files and replace to directory 'target'"
    cd target
    jar xf ../lib/jcommander-1.82.jar
    jar xf ../lib/JCDP-4.0.2.jar
    rm -rf META-INF
    cd ..
#4. Compile files to the 'target' directory:
    echo "Compile files to the 'target' directory"
    javac -d target -cp lib/\* src/java/edu/school21/printer/*/*.java
#5. Copy resources:
    echo "Copy resources"
    cp -r src/resources ./target/resources
#6. Create jar file:
    echo "Create jar file"
    jar -cfm target/images-to-chars-printer.jar src/manifest.txt -C target .
#7. Run program:
    java -jar target/images-to-chars-printer.jar --white=BLUE --black=GREEN
