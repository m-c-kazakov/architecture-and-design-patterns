package com.otus.solid.first.war.of.tanks.actions;

import com.otus.solid.first.war.of.tanks.informations.Information;
import com.otus.solid.first.war.of.tanks.informations.MoveInformation;
import com.otus.solid.first.war.of.tanks.states.MoveState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("move")
public class Move implements Action {
    @Autowired
    MoveState moveState;

    @Override
    public <T extends Information> void execute(T information) {
        MoveInformation moveInformation = (MoveInformation) information;
    }
}
