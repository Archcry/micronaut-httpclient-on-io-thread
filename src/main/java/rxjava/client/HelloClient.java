package rxjava.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client(value = "hello", path = "/hello")
public interface HelloClient {
    @Get("/2")
    Single<String> getHello();

    @Get("/2")
    String getHello2();
}
