#echo "mvn clean install"
#mvn clean install

docker build -t maheshkumarctl/docker-k8s-example:$(git rev-parse HEAD) .

docker login -u maheshkumarctl -p mk@verma1

docker push maheshkumarctl/docker-k8s-example:$(git rev-parse HEAD)


#kubectl apply -f k8s
#kubectl set image deployments/server-deployment server=stephengrider/multi-server:$SHA

