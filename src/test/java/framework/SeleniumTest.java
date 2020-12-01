package framework;


import java.util.ArrayList;

public class SeleniumTest extends TestCase{

    public void run(){
        steps.forEach(step -> {
            String key = step.keySet().iterator().next();
            if (key.contains("init")){
                ArrayList<String> value = (ArrayList<String>) getValue(step, "init");
                try {
                    BasePage.getInstance().poInit(value.get(0), value.get(1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (key.contains(".")){
                String[] objectMethod = key.split("\\.");
                String object = objectMethod[0];
                String method = objectMethod[1];
                //解决了找方法的难题
                BasePage.getInstance().getPO(object).stepRun(method);
            }
        });
    }
}
