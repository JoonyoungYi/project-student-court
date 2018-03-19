package at.yearnning.os.studentcourt;

import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public final class SettingsInfoActivity extends SherlockFragmentActivity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_info_activity);

		/**
		 * actionbar setting
		 */
		getSupportActionBar().setDisplayOptions(
				ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_HOME_AS_UP
						| ActionBar.DISPLAY_SHOW_TITLE);

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;

		}
		return false;
	}

	/**
	 * Back Key Listener
	 */
	@Override
	public void onBackPressed() {
		Log.d("CDA", "onBackPressed Called");

		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

	}

}
