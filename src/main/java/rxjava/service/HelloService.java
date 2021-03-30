package rxjava.service;

import io.micronaut.scheduling.TaskExecutors;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rxjava.client.HelloClient;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.ExecutorService;

@Singleton
public class HelloService {
    private final HelloClient helloClient;
    private final Scheduler scheduler;

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

    public HelloService(final HelloClient helloClient, final @Named(TaskExecutors.IO) ExecutorService executorService) {
        this.helloClient = helloClient;
        this.scheduler = Schedulers.from(executorService);
    }

    public Single<String> getHello() {
        return helloClient.getHello()
            .subscribeOn(scheduler)
            .toObservable()
            .doOnNext(a -> LOGGER.info(Thread.currentThread().getName()))
            .firstOrError();
    }

    public Single<String> getHello2() {
        return Single.fromCallable(helloClient::getHello2)
            .subscribeOn(scheduler)
            .toObservable()
            .doOnNext(a -> LOGGER.info(Thread.currentThread().getName()))
            .firstOrError();
    }
}
