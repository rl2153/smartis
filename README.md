# smartis

koraki:
1. zapakiramo quarkus aplikacijo v jar:
mvn clean package 

2. zgradimo docker image (v tem primeru je smartis ime slike - ime mora biti enako kot v docker-compose.yml datoteki)
docker build -f ./src/main/docker/Dockerfile.jvm -t smartis .

3. pozenemo docker-compose.yml datoteko z ukazom:
docker-compose up --build

4. aplikacije je dostopna na povezavi:
http://localhost:8080/q/graphql-ui