package com.mrapaport.unlu.sdypp.splitter.service;

import java.util.Map;

public interface JWTProvider {

    String createToken(Map<String, String> payload);

}
