package dev.dmytririsov.statemachine.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dev.dmytririsov.statemachine.R;
import dev.dmytririsov.statemachine.fsm.FiniteStateMachine;
import dev.dmytririsov.statemachine.fsm.IOnTransitionListener;
import dev.dmytririsov.statemachine.json.JsonLoader;

/**
 * @author Dmytri on 25.03.2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IOnTransitionListener {

    private static final String STATES_JSON = "states.json";
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTvAlarmState;
    private Button mButtonLock;
    private Button mButtonLockX2;
    private Button mButtonUnlock;
    private Button mButtonUnlockX2;

    private FiniteStateMachine mFiniteStateMachine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvAlarmState = (TextView) findViewById(R.id.tv_alarm_state);
        mButtonLock = (Button) findViewById(R.id.btn_lock);
        mButtonLockX2 = (Button) findViewById(R.id.btn_lockx2);
        mButtonUnlock = (Button) findViewById(R.id.btn_unlock);
        mButtonUnlockX2 = (Button) findViewById(R.id.btn_unlockx2);
        Button mButtonResetState = (Button) findViewById(R.id.btn_reset_state);

        mButtonLock.setOnClickListener(this);
        mButtonLockX2.setOnClickListener(this);
        mButtonUnlock.setOnClickListener(this);
        mButtonUnlockX2.setOnClickListener(this);
        mButtonResetState.setOnClickListener(this);
        mFiniteStateMachine = new FiniteStateMachine(this);
        JsonLoader.initJsonStates(this, STATES_JSON);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_lock: {
                mFiniteStateMachine.changeState(mButtonLock.getText().toString());
                break;
            }
            case R.id.btn_lockx2: {
                mFiniteStateMachine.changeState(mButtonLockX2.getText().toString());
                break;
            }
            case R.id.btn_unlock: {
                mFiniteStateMachine.changeState(mButtonUnlock.getText().toString());
                break;
            }
            case R.id.btn_unlockx2: {
                mFiniteStateMachine.changeState(mButtonUnlockX2.getText().toString());
                break;
            }
            case R.id.btn_reset_state: {
                mFiniteStateMachine.resetState();
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onTransition(String current, String next, String action, boolean isArmed) {
        Log.i(TAG, "Action: " + action + ", old state " + current + " next state " + next);
        mTvAlarmState.setText(isArmed ? getString(R.string.armed) : getString(R.string.disarmed));
        mTvAlarmState.setBackgroundColor(isArmed ? getResources().getColor(R.color.red) : getResources().getColor(R.color.green));
    }
}
