FROM openjdk:8-jre-alpine

RUN apk update

# Install tesseract library
RUN apk add --no-cache tesseract-ocr


# Download last language package
RUN mkdir -p /usr/share/tessdata
ADD https://github.com/tesseract-ocr/tessdata/raw/master/spa.traineddata /usr/share/tessdata/spa.traineddata

ENV TESSDATA_PREFIX=/usr/share/tessdata/
ENV LC_ALL=C

COPY target/worker-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]