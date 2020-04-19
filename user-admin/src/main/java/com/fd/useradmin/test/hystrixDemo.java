package com.fd.useradmin.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixTimeoutException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.RejectedExecutionException;

public class hystrixDemo extends HystrixCommand<String> {

    Random r = new Random();

    protected hystrixDemo(Setter setter) {
        super(setter);
    }

    @Override
    protected String run() throws Exception {

        System.out.println(Thread.currentThread().getName()+"haha");
       Thread.sleep(1000);

        return "SS";
    }


    @Override
    protected String getFallback() {


        Throwable executionException = super.getExecutionException();
       /* if (executionException == null) throw new ProcessException("调用失败，未知错误");
        //非Hystrix造成的异常
        if (super.isFailedExecution()) {
            if (executionException instanceof ProcessException) throw (ProcessException) executionException;
            throw new ProcessException("调用失败: " + executionException.getMessage());
        }
        //Hystrix引起的异常
        if (executionException instanceof HystrixTimeoutException)    throw new ProcessException("hystrix调用超时");
        if (executionException instanceof RejectedExecutionException) throw new ProcessException("hystrix触发限流");
        if (executionException instanceof RuntimeException)           throw new ProcessException("hystrix触发短路");
        if (executionException instanceof HystrixBadRequestException) throw new ProcessException("hystrix请求失败");
        throw new ProcessException("hystrix调用失败");*/

        System.out.println(Thread.currentThread().getName()+"fallback=>"+executionException.toString());

        return "qq";
    }


    @com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand(
            groupKey = "",
            threadPoolProperties={
                    @HystrixProperty(name="",value = "")
            }

    )
    public static void main(String[] args) throws IOException {
        //Setter setter = Setter.withGroupKey(new HystrixCommandGroupDefault("g1"));

        //execution.isolation.strategy

        for (int i=0;i<1;i++){
           new Thread(()->{
               hystrixDemo myfirstHy = new hystrixDemo(
                       Setter
                               .withGroupKey(HystrixCommandGroupKey.Factory.asKey("myfirstHy"))
                               .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                      // .withExecutionTimeoutInMilliseconds(2000)
                                        //.withExecutionIsolationThreadTimeoutInMilliseconds()
                                       .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                                       .withExecutionIsolationSemaphoreMaxConcurrentRequests(5)
                               )
                               .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(4))
               );
               String execute = myfirstHy.execute();
               System.out.println("as"+execute);
           }).start();
        }


        //#executeCommandAndObserve
        //#executeCommandWithSpecifiedIsolation
        System.in.read();
    }
}
