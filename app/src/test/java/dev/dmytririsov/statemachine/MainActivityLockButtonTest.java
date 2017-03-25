package dev.dmytririsov.statemachine;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmytri on 25.03.2017.
 */

@RunWith(RobolectricGradleRunnerWithManifest.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityLockButtonTest {

    private static final String STATE_DISARMED = "disarmed";
    private static final String STATE_ARMED = "armed";
    private Button mButtonLock;
    private Button mButtonLockX2;
    private Button mButtonResetState;
    private TextView mTvAlarmState;

    @Before
    public void beforeTestFSM() {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        mButtonLock = (Button) activity.findViewById(R.id.btn_lock);
        mButtonLockX2 = (Button)  activity.findViewById(R.id.btn_lockx2);
        mTvAlarmState = (TextView) activity.findViewById(R.id.tv_alarm_state);
        mButtonResetState = (Button) activity.findViewById(R.id.btn_reset_state);
    }

    @Test
    public void testLockButtonClick_ShouldNotChangeState1() {
        mButtonLock.performClick();
        assertThat(mTvAlarmState.getText().toString()).isEqualToIgnoringCase(STATE_DISARMED);
        mButtonResetState.performClick();
    }

    @Test
    public void testLockX2ButtonClick_ShouldChangeState1() {
        mButtonLockX2.performClick();
        assertThat(mTvAlarmState.getText().toString()).isEqualToIgnoringCase(STATE_ARMED);
        mButtonResetState.performClick();
    }

    @Test
    public void testLockX2ButtonClick_ShouldChangeState2() {
        mButtonLockX2.performClick();
        assertThat(mTvAlarmState.getText().toString()).isEqualToIgnoringCase(STATE_ARMED);
        mButtonResetState.performClick();
    }

    @Test
    public void testLockButtonClick_ShouldNotChangeState2() {
        mButtonLock.performClick();
        assertThat(mTvAlarmState.getText().toString()).isEqualToIgnoringCase(STATE_DISARMED);
        mButtonResetState.performClick();
    }

    @Test
    public void testLockButtonClick_ShouldNotChangeState3() {
        mButtonLock.performClick();
        assertThat(mTvAlarmState.getText().toString()).isEqualToIgnoringCase(STATE_DISARMED);
        mButtonResetState.performClick();
    }

    @Test
    public void testLockX2ButtonClick_ShouldChangeState3() {
        mButtonLockX2.performClick();
        assertThat(mTvAlarmState.getText().toString()).isEqualToIgnoringCase(STATE_ARMED);
        mButtonResetState.performClick();
    }

}
