package lc7.train;

import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {


    @SneakyThrows
    public static void main(String[] args) {
        List<MsgHandler> handlers = new ArrayList<>();

        Reflections r = new Reflections(MsgHandler.class);

        Set<Class<?>> annotatedWith = r.getTypesAnnotatedWith(MyHandler.class);

        for (Class<?> aClass : annotatedWith) {
            Class<?>[] interfaces = aClass.getInterfaces();
            if (Arrays.asList(interfaces).contains(MsgHandler.class)){
                Object o = aClass.newInstance();
                Field n = aClass.getDeclaredField("n");
                n.setAccessible(true);
                n.set(o,"ggg");
                handlers.add((MsgHandler) o);
            }
        }

        handlers.forEach(el -> el.handle("Hello from"));

    }
}
