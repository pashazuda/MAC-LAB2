package pr2;



import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.util.List;


class MyPlainArrListTest {
    private   List<String> l = new MyPlainArrList<>();
    private long ts;

    @Test
    @DisplayName("adding data test")
    void addTest1(){
        List<String> l = new MyPlainArrList<>();
        l.add("Mono");
        l.add("dsad");
        l.add("Naomi");

        Assertions.assertEquals(3, l.size());
        Assertions.assertEquals("Naomi", l.get(2));
    }

    @Test
    void removeTest1(){
        l.remove(2);
        Assertions.assertEquals(2, l.size());

        l.remove("Naomi");
        Assertions.assertEquals(1, l.size());
    }

    @RepeatedTest(10)
    void containsTest1(){
        Assertions.assertTrue(l.contains("Jack"));
    }

    @Test
    void setTest1(){
        l.set(1, "Polly");
        Assertions.assertEquals("Polly", l.get(1));
    }

    @Test
    void exceptionThrownTest1(){
        Assertions.assertThrows(RuntimeException.class, () -> l.iterator());
    }



//    @BeforeAll
//    static void beforeEach(){
//        ts = System.currentTimeMillis();
//    }

    @BeforeEach
    void initialize(){
        ts = System.currentTimeMillis();
        l.clear();
        l.add("Jack");
        l.add("Naomi");
        l.add("Naomi");
    }

    @AfterEach
    void afterEach(){

        System.out.println(" time : "+(System.currentTimeMillis() - ts));
    }

}