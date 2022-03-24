package com.otus.solid.first.war.of.tanks.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameUser {
    String userId;
    String message;
}
