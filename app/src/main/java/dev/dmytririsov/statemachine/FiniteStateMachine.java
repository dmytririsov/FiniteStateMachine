package dev.dmytririsov.statemachine;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dmytri on 25.03.2017.
 */

public class FiniteStateMachine {

    private static final String ROOT_STATE = "AlarmDisarmed_AllUnlocked";

    private static IFiniteStateMachine mListener;
    private static String mState = ROOT_STATE;

    public static void changeFSMStatus(String action) {
        String endState = getFinalStateByPreviousState(action, mState);
        if (!endState.equals(mState)) {
            mState = endState;
        }
        mListener.onTransition(mState, endState);
    }

    public static String getFinalStateByPreviousState(String action, String state) {
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

    public static void subscribeToTransition(IFiniteStateMachine listener) {
        mListener = listener;
    }

    public static void unsubscribeToTransition() {
        mListener = null;
    }
}

