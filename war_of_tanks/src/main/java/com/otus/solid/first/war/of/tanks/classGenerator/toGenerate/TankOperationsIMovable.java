package com.otus.solid.first.war.of.tanks.classGenerator.toGenerate;

import com.otus.solid.first.war.of.tanks.classGenerator.Vector;

public interface TankOperationsIMovable {
    Vector getPosition();
    Vector setPosition(Vector newValue);
    Vector getVelocity();
}
