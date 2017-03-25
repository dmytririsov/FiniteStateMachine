package dev.dmytririsov.statemachine;

/**
 * @author Dmytri on 25.03.2017.
 */

public interface IOnTransitionListener {

    void onTransition(String currentState, String endState, String action, boolean isArmed);
}
