FROM ubuntu:latest
MAINTAINER Orlando Leombruni o.leombruni@gmail.com
ADD entrypoint.sh /root/entrypoint.sh
RUN chmod +x /root/entrypoint.sh
RUN apt-get update && apt-get install -y cowsay && apt-get install -y fortune
ENTRYPOINT ["/root/entrypoint.sh"]

