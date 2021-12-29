package com.otus.solid.first.war.of.tanks.actions;

import com.otus.solid.first.war.of.tanks.informations.Information;

public interface Action {

    <T extends Information>void execute(T information);
}
