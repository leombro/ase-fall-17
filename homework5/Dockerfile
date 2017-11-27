FROM ubuntu:latest
MAINTAINER Orlando Leombruni o.leombruni@gmail.com
WORKDIR /root
ADD entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh
RUN apt-get update && apt-get install -y cowsay fortune
ENTRYPOINT ["/root/entrypoint.sh"]

