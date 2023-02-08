FROM debian:latest

RUN apt-get -y update

RUN apt-get -y install curl

RUN apt-get install -y wget

RUN wget https://packages.microsoft.com/config/debian/11/packages-microsoft-prod.deb -O packages-microsoft-prod.deb

RUN dpkg -i packages-microsoft-prod.deb

RUN rm packages-microsoft-prod.deb

RUN apt-get update

RUN apt-get install -y apt-transport-https

ENV DOTNET_CLI_TELEMETRY_OPTOUT=1

RUN apt-get install -y dotnet-sdk-6.0

RUN apt install -y default-jdk

RUN apt-get install -y maven

RUN export JAVA_HOME=/usr/lib/jvm/default-java
RUN export M2_HOME=/opt/maven
RUN export MAVEN_HOME=/opt/maven
RUN export PATH=${M2_HOME}/bin:${PATH}


CMD ["sudo ./bin/installdependencies.sh"]

ENV RUNNER_ALLOW_RUNASROOT = 1

RUN export RUNNER_ALLOW_RUNASROOT="1"

RUN curl -o actions-runner-linux-x64-2.301.1.tar.gz -L https://github.com/actions/runner/releases/download/v2.301.1/actions-runner-linux-x64-2.301.1.tar.gz

RUN tar xzf ./actions-runner-linux-x64-2.301.1.tar.gz

RUN ./config.sh --url https://github.com/Muz1kash1/Bstu-Multibot --token ALD4DM34DWA7RM2Z76ZPGRDD4OU6M --name linux --work _work --runasservice --disableupdate

CMD ["./run.sh"]


