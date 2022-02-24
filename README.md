# MeBank
Coding Challenge
# Getting Started
Checkout the Git Repository on master
The project uses gradle 6.8.
# Build
The program is packaged as an executable JAR. This is achieved by
 ./gradlew clean build
 This will generate a jar in build/lib directory.
 
 # Running
 
 java -jar MeBank-1.0-SNAPSHOT.jar <path to .csv file>
  
  Once run, the program requests User Input:

1. accountID - The account to calculate the balance for.
2. fromDate - The From Date to begin the calculation at. Input Format dd/MM/yyyy HH:mm:ss
3. toDate - The To Date to begin the calculation at. Input Format dd/MM/yyyy HH:mm:ss
 
The program outputs the relative balance and number of transactions in the calculation as per the challenge requirements
