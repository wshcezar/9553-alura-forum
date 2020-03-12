Vagrant.configure("2") do |config|
  
  config.vm.define "alura-database" do |db|
    db.vm.box = "ubuntu/bionic64"

    db.vm.network "private_network", ip: "192.168.56.110"
    db.vm.network "forwarded_port", guest: 3306, host: 3307

    db.vm.provision "shell", path: "vagrant/database/install.sh"
    db.vm.provision "file", source: "vagrant/database/mysqld.cnf", destination: "/tmp/mysqld.cnf"
    db.vm.provision "file", source: "vagrant/database/script-inicial.sql", destination: "/tmp/script-inicial.sql"
    db.vm.provision "shell", path: "vagrant/database/post-install.sh"
  end

  config.vm.define "alura-web" do |web|
    web.vm.box = "ubuntu/bionic64"

    web.vm.network "private_network", ip: "192.168.56.105"
    web.vm.network "forwarded_port", guest: 8080, host: 8080

    web.vm.provision "shell", path: "vagrant/web/install.sh"
    web.vm.provision "file", source: "vagrant/web/apache-tomcat-8.5.47.tar.gz", destination: "/tmp/apache-tomcat-8.5.47.tar.gz"
    web.vm.provision "file", source: "vagrant/web/tomcat.service", destination: "/tmp/tomcat.service"
    web.vm.provision "file", source: "target/alura-forum-0.0.1-SNAPSHOT.war", destination: "/tmp/alura-forum.war"
    web.vm.provision "shell", path: "vagrant/web/post-install.sh"
  end

end
