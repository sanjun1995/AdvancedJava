package com.example.demo.groovy;

import java.util.HashMap;
import java.util.Map;
import javax.script.*;

public class GroovyScriptExample {

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");

        // Execute a simple script
        String script = "println 'Hello, World!'";
        engine.eval(script);

        // Execute a script with variables
        Map<String, Object> variables = new HashMap<>();
        variables.put("a", 3);
        variables.put("b", 5);
        Object result = engine.eval("def sum = a + b; return sum;", new SimpleBindings(variables));
        if (result instanceof Number) {
            int sum = ((Number) result).intValue();
            System.out.println(sum);
        } else {
            System.err.println("The script did not return an integer value.");
        }
    }
}


