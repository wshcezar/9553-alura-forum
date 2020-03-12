sudo useradd -r -m -U -d /opt/tomcat -s /bin/false tomcat

sudo tar xf /tmp/apache-tomcat-8.5.47.tar.gz -C /opt/tomcat
sudo mv /opt/tomcat/apache-tomcat-8.5.47 /opt/tomcat/tomcat8
sudo mv /tmp/tomcat.service /etc/systemd/system
sudo mv /tmp/alura-forum.war /opt/tomcat/tomcat8/webapps

sudo ufw allow 8080
sudo chgrp -R tomcat /opt/tomcat
sudo chown -R tomcat /opt/tomcat

sudo systemctl daemon-reload
sudo systemctl start tomcat
sudo systemctl enable tomcat
