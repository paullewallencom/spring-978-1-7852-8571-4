package com.packtpub.springrest.availability;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:availability-test.xml")
public class WiringTest {

    @Autowired
    private AvailabilityService availabilityService;

    @Test
    public void test() {
        assertNotNull(availabilityService);
    }
}
