package com.otus.solid.first.war.of.tanks;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.informations.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Tank {

    @Autowired
    private Map<String, Action> tankActions;

    private final Integer health = 1;


    public void action(String action, Information information) {
        tankActions.get(action).execute(information);
    }

    public Integer hit(Integer damage) {
        return health - damage;
    }


}
