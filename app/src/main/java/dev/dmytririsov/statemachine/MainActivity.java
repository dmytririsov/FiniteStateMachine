package dev.dmytririsov.statemachine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IFiniteStateMachine{

    private TextView mTvAlarmState;
    private Button buttonLock, buttonLockX2, buttonUnlock, buttonUnlockX2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvAlarmState = (TextView) findViewById(R.id.tv_alarm_state);
        buttonLock = (Button) findViewById(R.id.btn_lock);
        buttonLockX2 = (Button) findViewById(R.id.btn_lockx2);
        buttonUnlock = (Button) findViewById(R.id.btn_unlock);
        buttonUnlockX2 = (Button) findViewById(R.id.btn_unlockx2);

        buttonLock.setOnClickListener(this);
        buttonLockX2.setOnClickListener(this);
        buttonUnlock.setOnClickListener(this);
        buttonUnlockX2.setOnClickListener(this);
        FiniteStateMachine.subscribeToTransition(this);
        JsonLoader.initJsonStates(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_lock: {
                FiniteStateMachine.changeFSMStatus(buttonLock.getText().toString());
                break;
            }
            case R.id.btn_lockx2: {
                FiniteStateMachine.changeFSMStatus(buttonLockX2.getText().toString());
                break;
            }
            case R.id.btn_unlock: {
                FiniteStateMachine.changeFSMStatus(buttonUnlock.getText().toString());
                break;
            }
            case R.id.btn_unlockx2: {
                FiniteStateMachine.changeFSMStatus(buttonUnlockX2.getText().toString());
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onTransition(String current, String next) {
        Log.d("TAG", "Old state " + current + " next state " + next);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FiniteStateMachine.unsubscribeToTransition();
    }
}
