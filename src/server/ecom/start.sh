mvn install

cd ./target/wildfly-run/wildfly-10.1.0.Final/bin/

./jboss-cli.sh --connect --command=:shutdown
./standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 &
./add-user.sh ejbadmin ytreza999
