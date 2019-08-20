package io.pivotal.workshops.cnd.metricsloggingworkshop;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * DemoControllerTests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoControllerTests {

    private DemoController demoController;

    @Before
    public void setup() {
        this.demoController = new DemoController();
    }

    @Test
    public void returnsTheString()  {
        String expected = "This is a sample test message";
        assertEquals(expected, demoController.getMessage().block());
    }
    
}