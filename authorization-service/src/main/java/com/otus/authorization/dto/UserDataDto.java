package com.otus.authorization.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserDataDto {

    String userId;
    String battleId;
}
