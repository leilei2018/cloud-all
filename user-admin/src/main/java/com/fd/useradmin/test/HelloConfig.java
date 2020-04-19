package com.fd.useradmin.test;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.cloud.client.loadbalancer.LoadBalancedRetryContext;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancedRetryFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

@Configuration
public class HelloConfig {

    @Bean
    public RibbonLoadBalancedRetryFactory listenerBbf(SpringClientFactory scf){
       return  new RibbonLoadBalancedRetryFactory(scf){
           @Override
           public RetryListener[] createRetryListeners(String service) {
               return getListener();
           }
       };
    }

    public RetryListener[] getListener(){
        RetryListener retryListener = new RetryListener() {
            @Override
            public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
                return true;
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {

            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
                if (context instanceof LoadBalancedRetryContext){
                    LoadBalancedRetryContext lbr = (LoadBalancedRetryContext)context;

                    System.err.println( lbr.getServiceInstance().getPort()+" # "+throwable.getMessage());
                }
            }
        };
        return new RetryListener[]{retryListener};
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for (int i=0;i<10;i++){
            new Thread(()->{

                try{
                    threadPoolExecutor.execute(()->{
                        System.out.println(Thread.currentThread().getName()+"doing");
                        try {
                            Thread.sleep(30000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }catch (Exception e){
                    System.out.println(e);
                }

            }).start();
        }
        LockSupport.park();
    }

}
