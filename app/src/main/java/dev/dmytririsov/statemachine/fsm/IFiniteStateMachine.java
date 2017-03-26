package dev.dmytririsov.statemachine.fsm;

/**
 * @author Dmytri on 25.03.2017.
 */

public interface IFiniteStateMachine {

    void changeState(String action);

    void resetState();
}
