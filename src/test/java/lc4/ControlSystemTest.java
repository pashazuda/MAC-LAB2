package lc4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ControlSystemTest {
    private ControlSystem cs;

    @Test
    void test1(){
        double res = cs.regulate(5);
        Assertions.assertEquals(2.5, res, 0.001);
    }

    @BeforeEach
    void before(){
        DataContainer mock = new DataContainer() {
            Map<String, Double> container;
            {
                container = new HashMap<>();
                container.put("tag1", 2.0);
            }

            @Override
            public void set(String tag, double value) {
                container.put(tag, value);
            }

            @Override
            public double get(String tag) {
                return container.get(tag);
            }
        };
        cs = new ControlSystem(mock);
    }
}