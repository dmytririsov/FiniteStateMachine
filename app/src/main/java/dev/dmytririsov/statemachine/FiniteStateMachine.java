package dev.dmytririsov.statemachine;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Dmytri on 25.03.2017.
 */

public class FiniteStateMachine implements IFiniteStateMachine {

    private static final String ARMED_CONST = "Armed";
    private static final String ROOT_STATE = "AlarmDisarmed_AllUnlocked";

    private IOnTransitionListener mListener;
    private String mState = ROOT_STATE;

    public FiniteStateMachine(IOnTransitionListener listener) {
        mListener = listener;
    }

    private String getFinalStateByPreviousState(String action, String state) {
        JSONObject jsonObjectStates = JsonLoader.getStateDependencyByAction(action);
        if (jsonObjectStates != null) {
            try {
                return jsonObjectStates.getString(state);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @Override
    public void changeState(String action) {
        String endState = getFinalStateByPreviousState(action, mState);
        String debugLogState = mState;
        if (!endState.equals(mState)) {
            mState = endState;
        }
        boolean isArmed = mState.contains(ARMED_CONST);
        mListener.onTransition(debugLogState, endState, action, isArmed);
    }

    @Override
    public void resetState() {
        mState = ROOT_STATE;
    }
}

