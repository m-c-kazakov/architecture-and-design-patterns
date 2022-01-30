package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;

import java.util.Map;

public class MailBoxStarter implements Action {
    // todo task Добавить реализацию после проверки задачи на IoC
    private IoC ioC;

    /**
     * Написать команду, которая стартует код, написанный в пункте 1 в отдельном потоке.
     */
    @Override
    public void execute() {
        MailBoxExecutor mailBoxExecutor = ioC.resolve(Map.of("dependencyName", "MailBoxExecutor"));
        new Thread(mailBoxExecutor::execute).start();
    }
}
