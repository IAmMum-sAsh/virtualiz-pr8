::Файл собирает все необходимые образы

cd EurekaService
xcopy /Y /F ..\..\EurekaService\target\eureka-1.0.0.jar .\eureka-1.0.0.jar
docker build . -t vkr-eureka-service
cd ..

cd AuthService
xcopy /Y /F ..\..\AuthService\target\authservice-0.0.1-SNAPSHOT.jar .\authservice-0.0.1-SNAPSHOT.jar
docker build . -t vkr-auth-service
cd ..

cd ApiGateway
xcopy /Y /F ..\..\APIGateway\target\apigateway-0.0.1-SNAPSHOT.jar .\apigateway-0.0.1-SNAPSHOT.jar
docker build . -t vkr-api-gateway
cd ..

cd MenuService
xcopy /Y /F ..\..\Menu\target\userservice-0.0.1-SNAPSHOT.jar .\userservice-0.0.1-SNAPSHOT.jar
docker build . -t menu-service
cd ..

cd BookServise
xcopy /Y /F ..\..\GameEngineControllerService\target\userservice-0.0.1-SNAPSHOT.jar .\userservice-0.0.1-SNAPSHOT.jar
docker build . -t book-service
cd ..