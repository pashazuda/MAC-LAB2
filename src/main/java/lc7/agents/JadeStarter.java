package lc7.agents;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.Properties;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JadeStarter {

    public static void main(String[] args) {
        Map<String, String> createdAgents = findRunAgents();
        ProfileImpl p = new ProfileImpl(createProps(createdAgents));
        Runtime.instance().setCloseVM(true);
        Runtime.instance().createMainContainer(p);
    }

    private static Map<String,String> findRunAgents(){
        Map<String,String> createdAgents = new HashMap<>();
        Reflections r = new Reflections(JadeStarter.class);
        Set<Class<?>> annotatedWith = r.getTypesAnnotatedWith(AutorunnableAgent.class);
        for (Class<?> aClass : annotatedWith) {
            AutorunnableAgent annotation = aClass.getAnnotation(AutorunnableAgent.class);
            String agentName = annotation.name();
            int count = annotation.copy();

            for(int i=0; i < count; i++){
                createdAgents.put(agentName+"_"+i, aClass.getName());
            }
        }
        return createdAgents;
    }

    /**
     *
     * @param createdAgents - map of Name and Class
     * @return
     */
    private static Properties createProps(Map<String, String> createdAgents){
        Properties props = new Properties();
        props.setProperty("gui", "true");

        StringBuilder agents = new StringBuilder();
        for (Map.Entry<String, String> entry : createdAgents.entrySet()) {
            agents.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
        }
        props.setProperty("agents", agents.toString());
        return props;
    }
}
