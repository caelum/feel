echo "buildando"

~/ambiente/ferramentas_build/apache-maven-3.2.3/bin/mvn clean package

echo "enviando novo war"
scp target/feel-1.0-SNAPSHOT.jar root@206.189.25.29:~