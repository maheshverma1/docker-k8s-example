#echo "mvn clean install"
#mvn clean install


docker build -t maheshkumarctl/docker-k8s-example:latest .

docker login -u maheshkumarctl -p mk@verma1

docker push maheshkumarctl/docker-k8s-example:latest


#kubectl apply -f k8s
#kubectl set image deployments/server-deployment server=stephengrider/multi-server:$SHA
