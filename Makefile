

all: 
	clean
	do

do:
	javac src/rmi/SiteItf.java -cp src/ 
	javac src/rmi/SiteImpl.java -cp src/ 
	javac src/rmi/Server.java -cp src/
	javac src/rmi/client.java -cp src/
	cd src/ && \
	rmic rmi.SiteImpl && \
	rmiregistry & 
	
	
clean:
	kill  `ps -aux | grep rmiregistry | cut -d ' ' -f 4 | head -n 1` 