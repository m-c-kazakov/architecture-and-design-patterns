package com.otus.authorization.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * Танковый бой
 */
@Getter
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Battle {

    String battleId;
    Set<String> memberId;
}
