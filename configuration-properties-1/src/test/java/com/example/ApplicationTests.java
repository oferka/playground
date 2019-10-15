package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTests {

    @Autowired
    private EmailConfiguration emailConfiguration;

    @Test
    public void contextLoads() {
        log.info("Loading context");
    }

    @Test
    public void shouldLoadEmailConfiguration() {
        assertEquals("host@mail.com", emailConfiguration.getHostName());
        assertTrue(9000 == emailConfiguration.getPort());
        assertEquals("mailer@mail.com", emailConfiguration.getFrom());
        assertEquals("john", emailConfiguration.getCredentials().getUsername() );
        assertEquals("password", emailConfiguration.getCredentials().getPassword());
        assertEquals("SHA1", emailConfiguration.getCredentials().getAuthMethod());
        assertTrue(2 == emailConfiguration.getDefaultRecipients().size());
        assertEquals("admin@mail.com", emailConfiguration.getDefaultRecipients().get(0));
        assertEquals("owner@mail.com", emailConfiguration.getDefaultRecipients().get(1));
        assertTrue(2 == emailConfiguration.getAdditionalHeaders().size());
        assertEquals("true", emailConfiguration.getAdditionalHeaders().get("redelivery"));
        assertEquals("true", emailConfiguration.getAdditionalHeaders().get("secure"));
    }
}
