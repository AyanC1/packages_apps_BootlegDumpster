package com.bootleggers.dumpster.fragments;

import com.android.internal.logging.nano.MetricsProto;
import android.content.ContentResolver;
import android.os.Bundle;
import com.android.settings.R;
import android.support.v14.preference.SwitchPreference;
import com.android.settings.SettingsPreferenceFragment;

public class GestureSettings extends SettingsPreferenceFragment {
   private static final String USE_GESTURE_NAVIGATION = "use_bottom_gesture";
   private SwitchPreference mGestureNavigation;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.bootleg_dumpster_gestures);
        ContentResolver resolver = getActivity().getContentResolver();
        mGestureNavigation = (SwitchPreference) findPreference(USE_GESTURE_NAVIGATION);
        mGestureNavigation.setChecked(Settings.System.getInt(resolver,
               Settings.System.USE_BOTTOM_GESTURE_NAVIGATION, 0) == 1);
        mGestureNavigation.setOnPreferenceChangeListener(this);

    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.BOOTLEG;
    }
    public boolean onPreferenceChange(Preference preference, Object newValue) {
	ContentResolver resolver = getActivity().getContentResolver();
        if (preference == mGestureNavigation) {
            boolean value = (Boolean) newValue;
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.USE_BOTTOM_GESTURE_NAVIGATION, value ? 1 : 0);
            return true;
	}
	return false;
}
