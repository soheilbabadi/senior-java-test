To run application: just run: 
docker compose up –build

And now you can access API documentation on:
http://localhost:8080/swagger-ui/index.html 

Metrics and actuator exposed on:
http://localhost:8080/actuator 
http://localhost:8080/actuator/health 

Get execution time of main functions:
http://localhost:8080/actuator/metrics/get_book_execution_time
http://localhost:8080/actuator/metrics/get_music_execution_time 

Implementation of cache system deployed by Redis cache. Redis service runs by Docker compose and then getMusic() & getBook() use cache on searched keyword.
For resilience I Used Spring retry and configured backoff and retry.
For concurrency I added synchronized keyword to prevent typical race condition.

Technology choose:
Spring boot: allowing developers to quickly get started with their projects without spending time on boilerplate code and configuration
Redis: an in-memory data store, which means it stores data in RAM, making it extremely fast for read and write operations.
Spring Retry: simplifies the implementation of retry logic by providing annotations and configuration options that integrate seamlessly with Spring-based applications and Transparent Retry Mechanism
RestTemplate: provides a straightforward API for making HTTP requests and handling responses. It requires less configuration and setup compared to WebClient, making it easier to use for basic RESTful interactions.

