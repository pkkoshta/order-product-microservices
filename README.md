# order-product-microservices

* In this microservices project is developed on mac m1.

* it has the following concepts

1. Eureka server: Which is a service registry. is usfull for register all micro-services to it. from there we can call another
                  mi-svc bassed on there name, there we don't need to call by host and port number of micro-services.
                  
                  To create a eureka server we need a simple spring boot project having "Eureka Server" dependency
                  		<spring-cloud.version>2020.0.3</spring-cloud.version>

                 <dependency>
                  <groupId>org.springframework.cloud</groupId>
                  <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
                </dependency>
                  
                  and add following property in properties file
                  spring.application.name=eureka-server
                  server.port=8761
                  eureka.client.registerWithEureka=false
                  eureka.client.fatchRegistry=false
                  
                  And added @EnableEurekaClient
                  on the top of main class
                  
                  Note : all the MS should use @EnableDiscoveryClient on the top of there main class
                  and add dependency to MS
                  	<dependency>
                      <groupId>org.springframework.cloud</groupId>
                      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
                      <version>3.1.4</version>
                    </dependency>
                  
2. Api-Gateway:  It is a routing point of the application that is used to route to particular microservices based on the uri pattern 
                 which is configured. as to call order mi then we need to use "/order/**" and product "/product/**".
                 
                 To create a api gateway use dependency create a new spring-boot application
                 
                 implementation 'org.springframework.cloud:spring-cloud-starter-gateway'
                 implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
	
                 
                 add To application.yml
                 
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/

  instance:
    hostname: localhost                 
                 
                 
spring:
  ##GATEWAY CONFIGURATIONS
  cloud:
    gateway:
      routes:
        ## USER SERVICE
        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/order/**

        ## FUND TRANSFER SERVICE
        - id: product-service
          uri: lb://product-service
          predicates:
            - Path=/product/**

                 
                 
                 
3. Confgi-server: Config-server is a spring boot project which is usefull to locate all common properties in a center places like git, bitBucket ect.
                  common properties like db configuration, eureka service configuration etc.
                  
                  To create a config server
                  1. create a spring boot project with config-server dependency 
                  
                      <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-config-server -->
                      <dependency>
                        <groupId>org.springframework.cloud</groupId>
                        <artifactId>spring-cloud-config-server</artifactId>
                      </dependency>
                      
                     <dependency>
                      <groupId>org.springframework.cloud</groupId>
                      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
                      <version>3.1.4</version>
                    </dependency>
                    
                  2. add a following properties to application.yml file
                  server:
  port: 9098

spring:
  application:
    name: config-server

  cloud:
    config:
      server:
        git:
          uri: https://github.com/pkkoshta/server-config
          username: pkkoshta
          password: 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
          force-pull: true
        default-label: main

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/

  instance:
    hostname: localhost
                    
                  3. create a git repository  and add a application.properties file or yml file 
                   and add properties to it.
                   
                   
                  
4. hystrix: it is a dependancy which is usfull to call fallback method like if we a trying to call one service and for some reason that particular 
            service is down. then we get some exception. In order to avoid these exception we can send a dummuy response to user.
            Note : Now a days company are using Resiliance$J tool.
            
5. ELK : It is a tool which is for centralized log. Ejectly there have 3 tools in it one is logstash which is use to locate log file location, 2nd is a 
          elastic search which is usfull to search the indexes bassed on parameter pass from Kibana, 3rd is a Kibana which is use to see to log in the                                UI,there we can have a log file.    
          
          To create a elk 
          
          1. add a logs to to MSs
          
          and download elastic-search,logstash, and kibana zip file and extract it 
          2. change logstash and add local log path in it
          3. in elastic search change uncomment "elastic.host"to localhost:9200
          and for referance go to this video
          
          https://www.youtube.com/watch?v=5s9pR9UUtAU&t=2s
          
6. Zipkins: It is a tool to distributted logs machenisum and also it is tracks the request track like which controller accept the request and then which method is calling
            In a very simple UI, Is usfull when we have multiple MS. there tracking the request is bit complicate to aviod that need to use it.
            it is a combination of sluth, and zipkin.
            
            To work with zipkins need following 
            1. download a zipkin.jar file and run that jar
            2. add some logs to MS
            3. add dependencies to all MS.
            
            <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-zipkin -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-sleuth</artifactId>
		<version>2.2.2.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-zipkin</artifactId>
			<version>2.2.2.RELEASE</version>

		</dependency>
    
 Note : for referance go to : https://www.youtube.com/watch?v=M19XC0zJUrA&t=287s   
