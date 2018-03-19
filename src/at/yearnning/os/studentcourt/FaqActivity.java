package at.yearnning.os.studentcourt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public final class FaqActivity extends SherlockFragmentActivity {

	TextView mQuestionTv;
	TextView mAnswerTv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faq_activity);

		Intent mIntent = getIntent();

		int question = mIntent.getExtras().getInt("question");
		int answer = mIntent.getExtras().getInt("answer");

		/**
		 * actionbar setting
		 */
		getSupportActionBar().setDisplayOptions(
				ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_HOME_AS_UP
						| ActionBar.DISPLAY_SHOW_TITLE);

		mQuestionTv = (TextView) findViewById(R.id.question_tv);
		mAnswerTv = (TextView) findViewById(R.id.answer_tv);

		mQuestionTv.setText(question);
		mAnswerTv.setText(answer);

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
