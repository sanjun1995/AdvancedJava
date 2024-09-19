package com.example.demo.b2c.statedemo;

/**
 * @author caozhixin
 * @date 2024/9/19 10:46
 */
import java.util.HashMap;
import java.util.Map;

public class SimpleStateMachine {

    private final Map<String, String> stateTransitions = new HashMap<>();
    private String currentState;

    public SimpleStateMachine(String initialState) {
        this.currentState = initialState;
    }

    public void addTransition(String fromState, String toState) {
        stateTransitions.put(fromState, toState);
    }

    public void transition(String event) {
        String nextState = stateTransitions.get(currentState);
        if (nextState != null) {
            System.out.println("Transitioning from " + currentState + " to " + nextState + " on event: " + event);
            currentState = nextState;
        } else {
            throw new IllegalStateException("No transition defined for state: " + currentState);
        }
    }

    public String getCurrentState() {
        return currentState;
    }
}
