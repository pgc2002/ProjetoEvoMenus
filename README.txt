Passos para ter o projeto funcional:


1º - Meter a pasta "ProjetoFinal_PedroCavalheiroJoelMateusAndreAfoito" na pasta "www" do Wamp
2º - instale e execute o wamp
3ª - Verificar se o composer está instalado no seu computador
4º - Abrir consola na pasta do projetofinal e fazer php init e composer update
5ª - Correr o ficheiro sql que se encontra nesta pasta no phpMyAdmin
6º - Alterar o nome da base de dados no ficheiro main-local.php no caminho common/config/ para evo_menu
7º - abra o ficheiro "httpd-vhosts.conf" em "C:\wamp64\bin\apache\apache2.4.54.2\conf\extra\" e onde diz require substitua por "Require all granted"
8º - desligue a firewall ou adicione uma regra para o ficheiro httpd.exe em "C:\wamp64\bin\apache\apache2.4.54.2\bin"
9º - instale o mosquitto mqtt broker
10º - abra a consola em modo administrador e utilize o comando "net start mosquitto"
11º - Mudar url da api na classe UrlApi na aplicação android e mudar o ip