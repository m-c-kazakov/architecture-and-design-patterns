// AUTOMATICALLY GENERATED at Tue Mar 29 17:41:22 MSK 2022
package com.otus.solid.first.war.of.tanks.classGenerator.generated;

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

  public Vector getPosition() {
    return IoC.resolve(Map.of("dependencyName", "TankOperationsIMovable::getPosition", "uObject", uObject));
  }

  public Vector setPosition(Vector newValue) {
    return IoC.resolve(Map.of("dependencyName", "TankOperationsIMovable::setPosition", "uObject", uObject, "newValue", newValue));
  }

  public Vector getVelocity() {
    return IoC.resolve(Map.of("dependencyName", "TankOperationsIMovable::getVelocity", "uObject", uObject));
  }
}
