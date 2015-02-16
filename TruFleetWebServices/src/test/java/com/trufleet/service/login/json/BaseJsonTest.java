package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.AccountEntity;
import io.dropwizard.jackson.Jackson;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
public class BaseJsonTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();


    public BaseJsonTest() {
    }


    public AccountEntity account = new AccountEntity();

    account.setName("something")


}
