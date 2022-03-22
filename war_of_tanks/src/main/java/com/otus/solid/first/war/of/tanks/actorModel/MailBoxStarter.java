package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MailBoxStarter implements Action {

    String scopeId;

    /**
     * Написать команду, которая стартует код, написанный в пункте 1 в отдельном потоке.
     */
    @Override
    public void execute() {
        MailBoxExecutor mailBoxExecutor = IoC.resolve(Map.of("dependencyName", "MailBoxExecutor", "scopeId", scopeId));
        new Thread(mailBoxExecutor::execute).start();
    }
}
