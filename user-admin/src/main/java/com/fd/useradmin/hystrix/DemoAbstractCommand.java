package com.fd.useradmin.hystrix;

import com.netflix.hystrix.contrib.javanica.command.AbstractHystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.GenericSetterBuilder;
import com.netflix.hystrix.contrib.javanica.command.HystrixCommandBuilder;

public class DemoAbstractCommand extends AbstractHystrixCommand<String> {
    protected DemoAbstractCommand(HystrixCommandBuilder builder) {
        super(builder);
    }

    @Override
    protected String run() throws Exception {
        System.out.println("gg");
        return null;
    }

    public static void main(String[] args) {
        GenericSetterBuilder.Builder builder = GenericSetterBuilder.builder()
                .groupKey("qaz")
                .commandKey("main");
        HystrixCommandBuilder build = HystrixCommandBuilder.builder()
                .setterBuilder(builder.build())
                .build();
        new DemoAbstractCommand(build).execute();
    }
}
