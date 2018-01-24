# Trol

## Konfiguracja środowiska

### System operacyjny

Instrukcja została stworzona dla linuxa, a dokładnie dystrybucji Fedora 27 Workstation.

#### Instalacja java

Do poprawnego działania programu niezbędna jest instajacja javy 8.

`wget http://download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-linux-x64.rpm
rpm -ivh jdk-8u151-linux-x64.rpm `

`wget http://download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-linux-x64.rpm
rpm -ivh jdk-8u151-linux-x64.rpm `

Dla ubuntu będzie to:

`sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer`

\subsubsection{Instalacja Gradle}
\begin{verbatim}
sudo -s
wget https://services.gradle.org/distributions/gradle-4.2.1-all.zip
mkdir /opt/gradle
unzip -d /opt/gradle gradle-4.2.1-all.zip
export PATH=$PATH:/opt/gradle/gradle-4.2.1/bin
\end{verbatim}

\subsubsection{Konfiguracja squid-a}

Dla systemów Fedora , Red Hat, CentOS (w najnowszej wersji) zalecana jest instalacja z Squida 4 z oficjalnego repozytorium.
\begin{verbatim}
sudo dnf install squid
\end{verbatim}
Dla systemów bazujących  na debianie (m.in. Debian, Ubuntu, LinuxMint) zaleca się pobranie Squida 4 z oficjalnej strony projektu, oraz zbudowanie go z flagami: 
\begin{lstlisting}[breaklines]
'--program-prefix='
'--prefix=/usr'
'--exec-prefix=/usr'
'--bindir=/usr/bin'
'--sbindir=/usr/sbin'
'--sysconfdir=/etc'
'--datadir=/usr/share'
'--includedir=/usr/include'
'--libdir=/usr/lib64'
'--libexecdir=/usr/libexec'
'--sharedstatedir=/var/lib'
'--mandir=/usr/share/man'
'--infodir=/usr/share/info'
'--exec_prefix=/usr'
'--libexecdir=/usr/lib64/squid'
'--localstatedir=/var'
'--datadir=/usr/share/squid'
'--sysconfdir=/etc/squid'
'--with-logdir=/var/log/squid'
'--with-pidfile=/var/run/squid.pid'
'--disable-dependency-tracking'
'--enable-eui'
'--enable-follow-x-forwarded-for'
'--enable-auth'
'--enable-auth-basic=DB,fake,getpwnam,LDAP,NCSA,NIS,PAM,POP3,RADIUS,SASL,SMB,SMB_LM'
'--enable-auth-ntlm=SMB_LM,fake'
'--enable-auth-digest=file,LDAP'
'--enable-auth-negotiate=kerberos'
'--enable-external-acl-helpers=LDAP_group,time_quota,session,unix_group,wbinfo_group,kerberos_ldap_group'
'--enable-storeid-rewrite-helpers=file'
'--enable-cache-digests'
'--enable-cachemgr-hostname=localhost'
'--enable-delay-pools'
'--enable-epoll'
'--enable-icap-client'
'--enable-ident-lookups'
'--enable-linux-netfilter'
'--enable-removal-policies=heap,lru'
'--enable-snmp'
'--enable-ssl'
'--enable-ssl-crtd'
'--enable-storeio=aufs,diskd,ufs,rock'
'--enable-diskio'
'--enable-wccpv2'
'--enable-esi'
'--enable-ecap'
'--with-aio'
'--with-default-user=squid'
'--with-dl'
'--with-openssl'
'--with-pthreads'
'--with-pic'
\end{lstlisting}

\begin{verbatim}
sudo -s
wget http://www.squid-cache.org/Versions/v4/squid-4.0.21.tar.gz
tar xvzf squid-4.0.21.tar.gz
cd squid-4.0.21
\end{verbatim}

Jako parametr poniższego configure należy podać powyższe flagi
\begin{verbatim}
./configure
make
make install
\end{verbatim}

\subsubsection{Konfiguracja dansguardian}

Ze względu na brak dansguardiana w oficjalnym repozytorium fedory 
\begin{lstlisting}[breaklines]
sudo -s
wget ftp://ftp.pbone.net/mirror/ftp5.gwdg.de/pub/opensuse/repositories/home:/Kenzy:/packages/CentOS_7/x86_64/dansguardian-2.12.0.3-1.1.x86_64.rpm
rpm -ivh dansguardian-2.12.0.3-1.1.x86_64.rpm
\end{lstlisting}

W przypadku ubuntu, debiana i linuxmint-a wystarczy wpisać
\begin{verbatim}
sudo apt-get install dansguardian
\end{verbatim}

W niektórych kompilacjach dansguardiana instnieje konieczność zakomentowania jednej z początkowych linii pliku 

\textit{/etc/dansguardian/dansguardian.conf} 
zawierającej 
\textbf{UNCONFIGURED}

\subsubsection{Instalacja MySQL}
\begin{verbatim}
sudo -s
dnf install https://dev.mysql.com/get/mysql57-community-release-fc27-10.noarch.rpm
dnf install mysql-community-server
dnf --enablerepo=mysql80-community install mysql-community-server
systemctl restart mysqld.service
systemctl enable mysqld.service
/usr/bin/mysql_secure_installation
mysqladmin -u root password [your_password_here]
\end{verbatim}

Nalezy stworzyć bazę danych trol
\begin{verbatim}
mysql> create database trol;
\end{verbatim}

Oraz wczytać plik ze skryptem z resources projektu
\begin{verbatim}
mysql -p trol -u root -p
mysql> source [path\_do\_skryptu];
\end{verbatim}

\subsubsection{Konfiguracja przechwytywania ruchu oraz ustawienie https}

W celu przechwycenia ruchu z sieci lokalnej należy wykonać instrukcję na serverze pełniącym rolę bramy w sieci lokalnej. Oraz dodać w mielscu 'http\_port 3128' 'transparent' w pliku squidHeader.conf w katalogu /filemanager w resources projektu

W celu ustawienia https należy wygenerować certyfikaty openssl

\begin{verbatim}
openssl genrsa 4096 > private_root.pem
openssl req -new -x509 -days 3650 -key private_root.pem -out my_rootCA.crt
openssl x509 -in my_rootCA.crt -outform DER -out my_rootCA.der
openssl genrsa 4096 > private_cert.pem
\end{verbatim}

oraz dodać do pliku squidFooter.conf w resources projektu w katalogu /filemanager
\begin{lstlisting}[breaklines]
http_port 3128 intercept
https_port 3129 intercept ssl-bump generate-host-certificates=on dynamic_cert_mem_cache_size=4MB cert=private_root.pem key=private_root.pem 
ssl_bump server-first all
\end{lstlisting}
gdzie w cert oraz key powinna znajdować się pełna ścierzka do wygenerowanych kluczy

\subsection{Instrukcja instalacji aplikacji}

Należy skonfigurować plik application.properties z resources projektu podmieniając wartości poniższych linii na

\begin{verbatim}
spring.datasource.url=jdbc:mysql://localhost:3306/trol
spring.datasource.username=root
spring.datasource.password=
\end{verbatim}

Jeżeli w trakcie instalacji servera mysql lub jego konfiguracji podano inne dane, należy podać w powyższych linijkach dane pozwalające połączyć się ze stworzoną bazą trol.

Następnie należy zbudować projekt.
Jeżeli w trakcie tej operacji wystąpią błędy należy upewnić się, że root oraz user z poziomu którego budujemy projekt mają stworzoną zmienną środowiskową \$JAVA\_HOME wskazującą na folder javy8 oraz dodaną TYLKO javę 8 do \$PATH.

\begin{verbatim}
git clone https://github.com/Ewastachow/trol-squid-configure.git
cd trol-squid-configure
gradle build -x test
gradle wrapper
sudo ./gradlew run
\end{verbatim}

W celach testowych należy wykonać próbę restartu squida oraz dansguardiana:
\begin{verbatim}
systemctl restart squid.service
systemctl restart dansguardian.service
\end{verbatim}
w przypadku problemów należy podmienić zawartość pliku /etc/squid/squid.conf /etc/dansguardian/dansguardian.conf oraz /etc/dansguardian/dansguardianf1.conf na zawartość plików w resources projektu /workingConfigFile

Należy ustawić proxy na port 8080 dla http oraz https