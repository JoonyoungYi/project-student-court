package at.yearnning.os.studentcourt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.viewpagerindicator.UnderlinePageIndicator;

public final class MovieActivity extends SherlockFragmentActivity {

	/**
	 * Changable Fragment Initializing
	 */

	private ViewPager mPager;
	private int onPageNum = 0;
	
	private int window_width;
	private float window_density;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_activity);

		Intent mIntent = getIntent();

		String title = mIntent.getExtras().getString("title");
		String subtitle = mIntent.getExtras().getString("subtitle");

		/**
		 * actionbar setting
		 */
		getSupportActionBar().setDisplayOptions(
				ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_HOME_AS_UP
						| ActionBar.DISPLAY_SHOW_TITLE);

		getSupportActionBar().setTitle(title);
		getSupportActionBar().setSubtitle(subtitle);

		/**
		 * Initialize the Menu Fragment
		 */

		TestFragmentAdapter mAdapter = new TestFragmentAdapter(
				getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		mPager.setOffscreenPageLimit(2);
		mPager.setPageMargin(Math.round(getResources().getDimension(
				R.dimen.y_vp_spacing_int)));
		mPager.setPageMarginDrawable(R.color.y_null);

		UnderlinePageIndicator indicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(mPager, 0);
		indicator.setFades(false);
		indicator.setOnPageChangeListener(mPageChangeListener);

		/**
		 * Mesure window size
		 */

		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		window_width = displayMetrics.widthPixels;
		window_density = displayMetrics.density;

	}

	/**
	 * ViewPager Adapter
	 * 
	 * @author JoonYoungYi
	 * 
	 */

	class TestFragmentAdapter extends FragmentPagerAdapter {
		public TestFragmentAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			if (position == 0) {
				Fragment fragment = new MoviePostingFragment();
				return fragment;
			} else if (position == 1) {
				Fragment fragment = new MovieProfileFragment();
				return fragment;
			} else {
				Fragment fragment = new MoviePostingFragment();
				return fragment;
			}
		}

		@Override
		public int getCount() {
			return 2;
		}
	}

	/**
	 * ViewPager Indicator Changer
	 */

	ViewPager.SimpleOnPageChangeListener mPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

		@Override
		public void onPageSelected(int position) {
			Log.d("pos : ", Integer.toString(position));

			onPageNum = position;

			// mYMenuAbTabProfileBottom.setVisibility(View.INVISIBLE);
			// mListIndicator.setVisibility(View.INVISIBLE);
			// mNotiIndicator.setVisibility(View.INVISIBLE);

			// if (position == 2) {
			// mYMenuAbTabProfileBottom.setVisibility(View.VISIBLE);
			// } else
			if (position == 0) {
				// mListIndicator.setVisibility(View.VISIBLE);

				invalidateOptionsMenu();

				// menu.add("Save")
				// .setIcon(isLight ? R.drawable.ic_compose_inverse :
				// R.drawable.ic_compose)
				// .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

			} else if (position == 1) {
				// mNotiIndicator.setVisibility(View.VISIBLE);
				invalidateOptionsMenu();
			}
		}

	};

	/**
	 * Action Item Setting
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// MenuInflater inflater = getSupportMenuInflater();
		// inflater.inflate(R.menu.jinro_details_activity, menu);

		if (onPageNum == 0) {
			Log.d("onPageNum", "0");

			menu.add("Profile")
					.setOnMenuItemClickListener(menuItemClickListener)
					.setIcon(R.drawable.jinro_details_activity_profile_icon)
					.setShowAsAction(
							MenuItem.SHOW_AS_ACTION_ALWAYS
									| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		} else if (onPageNum == 1) {
			Log.d("onPageNum", "1");

		}

		return super.onCreateOptionsMenu(menu);
	}

	public OnMenuItemClickListener menuItemClickListener = new OnMenuItemClickListener() {

		@Override
		public boolean onMenuItemClick(MenuItem item) {

			Log.d("onOptionsItemSelected", "menu_profile selected");

			mPager.setCurrentItem(1);
			return false;
		}
	};

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
	 * JinroDetailsProfileFrag Btn Clicked
	 */

	public void onJinroDetailsProfileFragmentSendEmailBtnClicked(String email) {

		Uri emailUri = Uri.parse("mailto:" + email);
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, emailUri);

		startActivity(emailIntent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}
	
	/**
	 * JinroDetailsProfileFrag return window_width
	 */
	
	public int getWindowWidth(){
		return this.window_width;
	}
	
	public float getWindowDensity(){
		return this.window_density;
	}

	/**
	 * Back Key Listener
	 */
	@Override
	public void onBackPressed() {
		Log.d("CDA", "onBackPressed Called");

		if (onPageNum == 0) {
			finish();
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
		} else {
			mPager.setCurrentItem(onPageNum - 1);
		}

	}

}
