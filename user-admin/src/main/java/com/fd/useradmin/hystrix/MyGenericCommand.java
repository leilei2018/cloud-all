package com.fd.useradmin.hystrix;


public class MyGenericCommand {}/*extends GenericCommand {
    public MyGenericCommand(HystrixCommandBuilder builder) {
        super(builder);
    }

    public static void main(String[] args) {
        GenericSetterBuilder.Builder builder = GenericSetterBuilder.builder()
                .groupKey("qaz")
                .commandKey("main");

        CommandActions actions = CommandActions.builder()
                 .commandAction(new CommandAction(){
                     @Override
                     public MetaHolder getMetaHolder() {
                         return null;
                     }

                     @Override
                     public Object execute(ExecutionType executionType) throws CommandActionExecutionException {
                         System.out.println(Thread.currentThread().getName()+"do haha");
                         int a  = 1/0;
                         return null;
                     }

                     @Override
                     public Object executeWithArgs(ExecutionType executionType, Object[] args) throws CommandActionExecutionException {
                         return null;
                     }

                     @Override
                     public String getActionName() {
                         return null;
                     }
                 })
                 .fallbackAction(new CommandAction() {
                     @Override
                     public MetaHolder getMetaHolder() {
                         return null;
                     }

                     @Override
                     public Object execute(ExecutionType executionType) throws CommandActionExecutionException {
                         System.out.println(Thread.currentThread().getName()+"fallback haha");
                         return null;
                     }

                     @Override
                     public Object executeWithArgs(ExecutionType executionType, Object[] args) throws CommandActionExecutionException {
                         System.out.println(Thread.currentThread().getName()+"fallback haha args");
                         return null;
                     }

                     @Override
                     public String getActionName() {
                         return null;
                     }
                 })
                .build();

        HystrixCommandBuilder build = HystrixCommandBuilder.builder()
                .setterBuilder(builder.build())
                .commandActions(actions)
                .build();



        MyGenericCommand t = new MyGenericCommand(build);
        t.execute();


    }
}
*/