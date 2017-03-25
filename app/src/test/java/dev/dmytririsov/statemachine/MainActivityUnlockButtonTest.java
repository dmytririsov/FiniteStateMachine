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
public class MainActivityUnlockButtonTest {

    private static final String STATE_DISARMED = "disarmed";
    private static final String STATE_ARMED = "armed";
    private Button mButtonUnlock;
    private Button mButtonUnlockX2;
    private Button mButtonResetState;
    private TextView mTvAlarmState;

    @Before
    public void beforeTestFSM() {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        mButtonUnlock = (Button)  activity.findViewById(R.id.btn_unlock);
        mButtonUnlockX2 = (Button)  activity.findViewById(R.id.btn_unlockx2);
        mButtonResetState = (Button) activity.findViewById(R.id.btn_reset_state);
        mTvAlarmState = (TextView) activity.findViewById(R.id.tv_alarm_state);
    }

    @Test
    public void testUnlockButtonClick_ShouldNotChangeState1() {
        mButtonUnlock.performClick();
        assertThat(mTvAlarmState.getText().toString()).isEqualToIgnoringCase(STATE_DISARMED);
        mButtonResetState.performClick();
    }

    @Test
    public void testUnlockX2ButtonClick_ShouldNotChangeState1() {
        mButtonUnlockX2.performClick();
        assertThat(mTvAlarmState.getText().toString()).isEqualToIgnoringCase(STATE_DISARMED);
        mButtonResetState.performClick();
    }

    @Test
    public void testUnlockButtonClick_ShouldNotChangeState2() {
        mButtonUnlock.performClick();
        assertThat(mTvAlarmState.getText().toString()).isEqualToIgnoringCase(STATE_DISARMED);
    }

    @Test
    public void testUnlockX2ButtonClick_ShouldNotChangeState2() {
        mButtonUnlockX2.performClick();
        assertThat(mTvAlarmState.getText().toString()).isEqualToIgnoringCase(STATE_DISARMED);
        mButtonResetState.performClick();
    }

    @Test
    public void testUnlockX2ButtonClick_ShouldNotChangeState3() {
        mButtonUnlockX2.performClick();
        assertThat(mTvAlarmState.getText().toString()).isEqualToIgnoringCase(STATE_DISARMED);
        mButtonResetState.performClick();
    }
}
