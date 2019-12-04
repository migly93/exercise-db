FROM mariadb:latest

MAINTAINER Eurovision Labs <labs@eurovision.net>

COPY ./data.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD admin
ENV MYSQL_DATABASE eurovisiondb
ENV MYSQL_USER admin
ENV MYSQL_PASSWORD admin

EXPOSE 3306