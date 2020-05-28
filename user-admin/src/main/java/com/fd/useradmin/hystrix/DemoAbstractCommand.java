package com.fd.useradmin.hystrix;



public class DemoAbstractCommand {}/*extends AbstractHystrixCommand<String> {
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
}*/
