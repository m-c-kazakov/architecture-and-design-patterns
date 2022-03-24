package com.otus.authorization.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PayLoad {

    String userId;
    Date expiration;
    Map<String, Object> optionalParams;

    public Map<String, Object> getPayload() {
        Map<String, Object> map = new HashMap<>(optionalParams);
        map.put("userId", userId);
        return map;
    }
}
