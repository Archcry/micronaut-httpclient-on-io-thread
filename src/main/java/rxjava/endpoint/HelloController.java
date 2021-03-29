package rxjava.endpoint;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.reactivex.Single;
import rxjava.service.HelloService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.ExecutorService;

@Controller("/hello")
public class HelloController {

    private final HelloService helloService;
    private final ExecutorService executorService;

    @Inject
    public HelloController(final HelloService helloService,  @Named(TaskExecutors.IO) ExecutorService executorService) {
        this.helloService = helloService;
        this.executorService = executorService;
    }

    @Get
    public Single<String> hello() {
        return this.helloService.getHello();
    }

    @Get("/onIo")
    public Single<String> helloOnIoThreadPool() {
        return this.helloService.getHello2();
    }

    @Get("/2")
    public Single<String> hello2() {
        return Single.just("World!");
    }
}
