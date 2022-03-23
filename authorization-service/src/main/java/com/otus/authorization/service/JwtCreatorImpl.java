package com.otus.authorization.service;

import com.otus.authorization.dto.PayLoad;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.keys.KeysParser;

import java.security.interfaces.RSAPrivateKey;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtCreatorImpl implements JwtCreator {

    RSAPrivateKey privateKey;

    public JwtCreatorImpl(@Value("${otus.auth.private}") String privateKey) {
        this.privateKey = KeysParser.getPrivateKeyFromString(privateKey);
    }

    @Override
    public String execute(PayLoad payLoad) {

        return Jwts.builder()
                .addClaims(payLoad.getPayload())
                .setExpiration(payLoad.getExpiration())
                .signWith(privateKey)
                .compact();
    }
}
