package io.pivotal.workshops.cnd.scalingworkshop;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * DemoControllerTests
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("default,local")
public class DemoControllerTests {

    private DemoController demoController;

    @Before
    public void setup() {
        RequestCounter counter = mock(RequestCounter.class);
        this.demoController = new DemoController(counter);
    }

    @Test
    public void returnsTheString()  {
        String expected = "Number of received requests: 0";
        assertEquals(expected, demoController.getMessage().block());
    }
    
}