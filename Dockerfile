FROM hseeberger/scala-sbt
EXPOSE 1233
WORKDIR /wizard
ADD . /wizard
ENV DOCKERENV="TRUE"
CMD sbt root/run