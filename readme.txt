Payment Tracker

Requirements
 Maven configured with JDK 8

Tested on OS X
	Apache Maven 3.2.3
	Java version: 1.8.0_60, vendor: Oracle Corporation

Execution
 mvn compile exec:java

Will run Payment Tracker with the default argument payments.txt which will load payments from the file. You can specify a different file by passing argument via Maven. I.e. -Dexec.args="foo.txt". If the file doesn't exist, application continues to run with clean transaction log and accepting command line input.If there are more arguments provided, only the first transaction file is loaded. You may provide a relative file name on the class path or specify complete path.
When an invalid currency-value pair is provided, it continues to run, but prints an error message with the stack trace.
Valid examples:
USD 10
USD +1
CZK -44

Where,
 first three letters are capital and alphabetical only. Followed by space and an integer value.

Thread safety ensured by Singleton design pattern.
The code could be improved by using Guava ImmutableList to prevent any changes in the list i.e. from another thread, but in this case it is not necessary.
