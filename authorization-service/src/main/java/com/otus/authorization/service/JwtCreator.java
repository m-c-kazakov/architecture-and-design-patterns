package com.otus.authorization.service;

import com.otus.authorization.dto.PayLoad;

public interface JwtCreator {

    String execute(PayLoad payLoad);
}
