# Cross region repolication for Cache example 

## Abstraction 

This demo presents how to replicate cache date between different regions. This demo made to understand Netfilx's EVCache concept which could replicate their numerous data for low latency and highly available service. And, it also demonstrates how it's easy to implement this concept with Spring Boot and Spring Cloud. Developers can leverage Spring Cloud Stream to use Kafka in this demo. You may can find its information in followed links: 

- Announcing EVCache Distributed in Memory Database  https://medium.com/netflix-techblog/announcing-evcache-distributed-in-memory-datastore-for-cloud-c26a698c27f7 

- Cache Warming Agility for a Stateful service 
https://medium.com/netflix-techblog/cache-warming-agility-for-a-stateful-service-2d3b1da82642 

- Application Data Caching Using SSDs
https://medium.com/netflix-techblog/application-data-caching-using-ssds-5bf25df851ef 

- Evolution of Application Data Caching from RAM to SSD 
https://medium.com/netflix-techblog/evolution-of-application-data-caching-from-ram-to-ssd-a33d6fa7a690 




## Demo architecture diagram 

![Diagram](https://github.com/kpiljoong/spring-cloud-cross-region-replication-example/wiki/images/diagram.png) 



## Components 

### Producer 

Producer exists for get POST requests from the world and put the recieved data to local memcached. And then, it also stores data to Kafka message queue. 


### Relay 

Relay stands for send data to another region when there's new message in the kafka queue. When there's messgage event comes, then it pull out the data from kafka queue, then post it to a proxy in another region. 


### Proxy 

Proxy gets data from Relay, and then put the data to local memcached. 


## Future works 

We'd like to put databases, and put more resiliency and scalablity with ELBs, and other parts of Spring Clouds. 


Thanks! 
