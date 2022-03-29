// AUTOMATICALLY GENERATED at Tue Mar 29 18:23:18 MSK 2022
package com.otus.solid.first.war.of.tanks.classGenerator.generated;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.classGenerator.UObject;
import com.otus.solid.first.war.of.tanks.classGenerator.Vector;
import com.otus.solid.first.war.of.tanks.classGenerator.toGenerate.TankOperationsIMovable;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import java.util.Map;

public class TankOperationsIMovableAdapter implements TankOperationsIMovable {
    private final UObject uObject;

    public TankOperationsIMovableAdapter(UObject uObject) {
        this.uObject = uObject;
    }

    public void finish() {
        Action action = IoC.resolve(Map.of("dependencyName", "TankOperationsIMovable::finish", "uObject", uObject));
        action.execute();
    }

    public Vector getPosition() {
        return IoC.resolve(Map.of("dependencyName", "TankOperationsIMovable::getPosition", "uObject", uObject));
    }

    public Vector setPosition(Vector arg0) {
        return IoC.resolve(Map.of("dependencyName", "TankOperationsIMovable::setPosition", "uObject", uObject, "arg0", arg0));
    }

    public Vector getVelocity() {
        return IoC.resolve(Map.of("dependencyName", "TankOperationsIMovable::getVelocity", "uObject", uObject));
    }
}
