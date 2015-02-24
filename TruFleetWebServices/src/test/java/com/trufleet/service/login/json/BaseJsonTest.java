package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.AccountEntity;
import io.dropwizard.jackson.Jackson;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
public class BaseJsonTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    public AccountEntity account = new AccountEntity();

    public BaseJsonTest() {
    }



}
