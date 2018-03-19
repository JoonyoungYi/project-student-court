package at.yearnning.os.studentcourt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

	/**
	 * Changable Fragment Initializing
	 */

	private Fragment mContent;
	private int mContent_id;

	/**
	 * UI Reference
	 */
	MenuAdapter mMenuAdapter;
	private ListView mMenu;

	TextView mMenuParentTv;
	TextView mMenuChildTv;
	TextView mMenuChildDivider;

	/**
	 * Menu DATA
	 */

	private static final int[][] MENU_DATA = { { 1, R.string.menu_about },
			{ 1, R.string.menu_newsfeed }, { 1, R.string.menu_faq },
			{ 1, R.string.menu_settings } };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		/**
		 * check if the content frame contains the menu frame I changed Lib in
		 * SlidingActivityHelper class In onKeyUp method. This makes toggle
		 * conversely.
		 */

		setBehindContentView(R.layout.main_activity_menu);
		getSlidingMenu().setSlidingEnabled(true);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		/**
		 * Initialize the Content Fragment
		 */

		// if (savedInstanceState != null) {
		// mContent = getSupportFragmentManager().getFragment(
		// savedInstanceState, "mContent");
		// }
		if (mContent == null) {
			mContent = new AboutFragment();
			mContent_id = R.string.menu_about;
			getSupportActionBar().setTitle(R.string.menu_about);
		}

		// Fragment mMenu = new StHelpTpStartFrag();

		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.y_menu_frame, mMenu).commit();

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_activity_frame, mContent).commit();

		/**
		 * Menu Setting
		 */

		mMenu = (ListView) findViewById(R.id.menu);
		mMenuAdapter = new MenuAdapter(this);
		mMenu.setAdapter(mMenuAdapter);
		mMenu.setOnItemClickListener(onMenuItemClickListener);

		/**
		 * customize the SlidingMenu
		 */
		SlidingMenu sm = getSlidingMenu();
		sm.setBehindWidthRes(R.dimen.slidingmenu_offset);
		// sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);

		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindScrollScale(0.25f);
		sm.setFadeDegree(1.0f);
		setSlidingActionBarEnabled(false);

		sm.setOnOpenListener(new OnOpenListener() {
			@Override
			public void onOpen() {
				// frameLayout.setVisibility(View.GONE);
				getSupportActionBar().setDisplayHomeAsUpEnabled(false);

				getSupportActionBar().setTitle(R.string.app_name);
			}
		});

		sm.setOnCloseListener(new SlidingMenu.OnCloseListener() {
			@Override
			public void onClose() {
				// TODO Auto-generated method stub
				getSupportActionBar().setDisplayHomeAsUpEnabled(true);

				getSupportActionBar().setTitle(mContent_id);

			}
		});

		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showMenu();
			}
		}, 50);

	}

	/**
	 * Switch Content
	 * 
	 * @param fragment
	 */

	public void switchContent(final Fragment fragment, final int fragment_id) {
		mContent = fragment;
		mContent_id = fragment_id;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_activity_frame, fragment).commit();
		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
			}
		}, 50);
	}

	/**
	 * Menu Item Click Listener
	 */

	/**
	 * click List Item.
	 */

	public OnItemClickListener onMenuItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			/**
			 * Click Event Setting
			 */

			if (MENU_DATA[position][1] == R.string.menu_newsfeed) {
				Fragment mJinroFragment = new MovieFragment();
				switchContent(mJinroFragment, R.string.menu_newsfeed);
			} else if (MENU_DATA[position][1] == R.string.menu_faq) {
				Fragment mEventFragment = new FaqFragment();
				switchContent(mEventFragment, R.string.menu_faq);
			} else if (MENU_DATA[position][1] == R.string.menu_about) {
				Fragment mAboutFragment = new AboutFragment();
				switchContent(mAboutFragment, R.string.menu_about);
			} else if (MENU_DATA[position][1] == R.string.menu_settings) {
				Fragment mSettingsFragment = new SettingsFragment();
				switchContent(mSettingsFragment, R.string.menu_settings);
			}

			/*
			 * for (int i = 0; i < MENU_DATA.length; i++) { if (MENU_DATA[i][0]
			 * == 0) { TextView mChildTv_unselected = (TextView)
			 * lv.getChildAt(i) .findViewById(R.id.child_tv);
			 * 
			 * mChildTv_unselected.setBackgroundResource(R.color.y_null);
			 * 
			 * mChildTv_unselected.setTextColor(getResources()
			 * .getColorStateList(R.color.py_unselected)); } }
			 * 
			 * TextView mChildTv_selected = (TextView)
			 * v.findViewById(R.id.child_tv); mChildTv_selected
			 * .setBackgroundResource(R.drawable.x_menu_list_lv_selected);
			 * mChildTv_selected.setTextColor(getResources().getColorStateList(
			 * R.color.py_selected));
			 */
		}
	};

	/**
	 * Menu Part
	 */

	/**
	 * insert data
	 * 
	 * @author JoonYoungYi
	 * 
	 */
	@SuppressWarnings("rawtypes")
	class MenuAdapter extends ArrayAdapter {
		Activity context;

		@SuppressWarnings("unchecked")
		MenuAdapter(Activity context) {
			super(context, R.layout.main_activity_menu_lv, MENU_DATA);

			this.context = context;
		}

		/**
		 * visible session setting, DATA Setting
		 */

		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();

			/**
			 * UI Initializing
			 */
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.main_activity_menu_lv,
						null);
				mMenuParentTv = (TextView) convertView
						.findViewById(R.id.parent_tv);
				mMenuParentTv.setPaintFlags(mMenuParentTv.getPaintFlags()
						| Paint.FAKE_BOLD_TEXT_FLAG);
				mMenuChildTv = (TextView) convertView
						.findViewById(R.id.child_tv);
				mMenuChildTv.setPaintFlags(mMenuChildTv.getPaintFlags()
						| Paint.FAKE_BOLD_TEXT_FLAG);
				mMenuChildDivider = (TextView) convertView
						.findViewById(R.id.child_divider);

			}

			/**
			 * Visibillity Setting
			 */

			if (MENU_DATA[position][0] == 0) {
				mMenuParentTv.setVisibility(View.VISIBLE);
				mMenuChildTv.setVisibility(View.GONE);
				mMenuChildDivider.setVisibility(View.GONE);

			} else if (MENU_DATA[position][0] == 1) {
				mMenuParentTv.setVisibility(View.GONE);
				mMenuChildTv.setVisibility(View.VISIBLE);

				if (position == MENU_DATA.length - 1) {
					mMenuChildDivider.setVisibility(View.VISIBLE);
				} else if (MENU_DATA[position + 1][0] == 0) {
					mMenuChildDivider.setVisibility(View.GONE);
				} else {
					mMenuChildDivider.setVisibility(View.VISIBLE);
				}

			}

			/**
			 * Data Setting
			 */
			if (MENU_DATA[position][0] == 0) {
				mMenuParentTv.setText(MENU_DATA[position][1]);

			} else if (MENU_DATA[position][0] == 1) {
				mMenuChildTv.setText(MENU_DATA[position][1]);

			}

			return convertView;
		}

		/**
		 * Category Click event Disabled
		 */

		public boolean isEnabled(int position) {
			if (MENU_DATA[position][0] == 0) {
				return false;
			} else if (MENU_DATA[position][0] == 1) {
				return true;
			} else {
				return false;
			}
		}

	}

	/**
	 * Faq Part
	 * 
	 */
	// TODO: 이 부분운 작동이 안됨. 아마도 OS적인 문제로 필요없어지게 만들어야 할듯 이 부분 대체해야함.

	public void onFaqFragmentGvItemPressed(int question, int answer) {
		// Intent mStTpAct = StHelpTpAct.newInstance(this, position);

		Intent mFaqActivity = new Intent(this, FaqActivity.class);
		mFaqActivity.putExtra("question", question);
		mFaqActivity.putExtra("answer", answer);

		startActivity(mFaqActivity);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

	}

	/**
	 * Jinro Part
	 * 
	 * @param name
	 * @param univ
	 * @param major
	 * @param answer_where
	 * @param answer_how
	 * @param answer_else
	 * @param answer_what
	 */
	// TODO: 이 부분운 작동이 안됨. 아마도 OS적인 문제로 필요없어지게 만들어야 할듯 이 부분 대체해야함.

	public void onJinroFragmentGvItemPressed(String url) {
		// Intent mStTpAct = StHelpTpAct.newInstance(this, position);

		// Intent mJinroDetailsActivity = new Intent(this, MovieActivity.class);
		// mJinroDetailsActivity.putExtra("title", title);
		// mJinroDetailsActivity.putExtra("subtitle", subtitle);
		// mJinroDetailsActivity.putExtra("url", url);
		// mJinroDetailsActivity.putExtra("thumbnail_img_url",
		// thumbnail_img_url);
		// mJinroDetailsActivity.putExtra("intro", intro);

		// startActivity(mJinroDetailsActivity);
		// overridePendingTransition(R.anim.slide_in_right,
		// R.anim.slide_out_left);

		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		intent.setPackage("com.google.android.youtube");
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

	}

	/**
	 * Setting Part
	 */

	public void onSettingsFragmentSendEmailBtnClicked(String email) {

		Uri emailUri = Uri.parse("mailto:" + email);
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, emailUri);

		startActivity(emailIntent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	public void onSettingsFragmentAppInfoBtnPressed() {

		Intent mSettingsInfoActivity = new Intent(this,
				SettingsInfoActivity.class);

		startActivity(mSettingsInfoActivity);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

	}

	/**
	 * Chk internet connection
	 */

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	/**
	 * Option Item Click Listener
	 */

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keycode, KeyEvent e) {
		switch (keycode) {
		case KeyEvent.KEYCODE_MENU:
			toggle();
			return true;
		}

		return super.onKeyDown(keycode, e);
	}

}
