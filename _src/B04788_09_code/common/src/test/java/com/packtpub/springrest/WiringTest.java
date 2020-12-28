package com.packtpub.springrest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Test that the Spring wiring can be loaded.
 *
 * @author Ludovic Dewailly
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:hibernate-test.xml")
public class WiringTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void test() {
        assertNotNull(sessionFactory);
    }
}
