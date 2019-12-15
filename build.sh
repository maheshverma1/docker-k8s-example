#echo "mvn clean install"
#mvn clean install

docker build -t maheshkumarctl/docker-k8s-example:$(git rev-parse HEAD) .

echo "mk@verma1" | docker login -u "maheshkumarctl" --password-stdin

docker push maheshkumarctl/docker-k8s-example:$(git rev-parse HEAD)


#kubectl apply -f k8s
#kubectl set image deployments/server-deployment server=stephengrider/multi-server:$SHA

