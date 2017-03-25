package dev.dmytririsov.statemachine;

/**
 * Created by Dmytri on 25.03.2017.
 */

public interface IFiniteStateMachine {

    void onTransition(String currentState, String endState);

}
