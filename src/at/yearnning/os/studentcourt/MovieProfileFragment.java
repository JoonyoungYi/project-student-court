package at.yearnning.os.studentcourt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public final class MovieProfileFragment extends SherlockFragment {

	/**
	 * UI Reference
	 */

	// private static final int MENU_SET_MODE = 0;
	private FrameLayout mProfileIvFrame;
	private ImageView mProfileIv;
	private TextView mNameTv;
	private TextView mSpecTv;
	private TextView mIntroTv;
	private Button mSendEmailBtn;
	private Button mShowKtidBtn;

	/**
	 * Data Reference
	 */

	private String name;
	private String univ;
	private String major;
	private String email;

	private String profile_img_url;

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.movie_profile_fragment,
				container, false);

		/**
		 * Data Importing
		 */
/*
		MovieActivity mJinroDetailsActivity = (MovieActivity) getActivity();
		Intent mIntent = mJinroDetailsActivity.getIntent();

		name = mIntent.getExtras().getString("name");
		univ = mIntent.getExtras().getString("univ");
		major = mIntent.getExtras().getString("major");
		email = mIntent.getExtras().getString("email");

		profile_img_url = mIntent.getExtras().getString("profile_img_url");

		int mProfileIvFrame_width = mJinroDetailsActivity.getWindowWidth()
				- Math.round(50 * mJinroDetailsActivity.getWindowDensity());
		/**
		 * UI reference Initializing
		 */
/*
		mProfileIvFrame = (FrameLayout) v.findViewById(R.id.profile_iv_frame);
		mProfileIv = (ImageView) v.findViewById(R.id.profile_iv);
		mNameTv = (TextView) v.findViewById(R.id.name_tv);
		mSpecTv = (TextView) v.findViewById(R.id.spec_tv);
		mIntroTv = (TextView) v.findViewById(R.id.intro_tv);
		mSendEmailBtn = (Button) v.findViewById(R.id.send_email_btn);
		mShowKtidBtn = (Button) v.findViewById(R.id.show_ktid_btn);

		/**
		 * UI Setting
		 */
/*
		mProfileIvFrame.setLayoutParams(new LinearLayout.LayoutParams(
				mProfileIvFrame_width, mProfileIvFrame_width));
		mSpecTv.setSelected(true);
		if (email != null) {
			String color = "#444444";
			mSendEmailBtn.setTextColor(Color.parseColor(color));
		}

		/**
		 * Data Setting
		 */
/*
		mNameTv.setText(name);
		mSpecTv.setText(univ + " " + major);
		mIntroTv.setText("안녕하세요. " + name + "입니다.");

		/**
		 * Btn Click Listener
		 */
/*
		mSendEmailBtn.setOnClickListener(onClickListener);
		mShowKtidBtn.setOnClickListener(onClickListener);

		/**
		 * Async Task Setting
		 */

		//new ProfileImageLoadingTask().execute(profile_img_url);

		return v;
	}

	/**
	 * Btn OnClickListener
	 */

	OnClickListener onClickListener = new OnClickListener() {
		public void onClick(View v) {
			clickListenerHandler(v.getId());
		}
	};

	private void clickListenerHandler(int id) {

		switch (id) {
		case R.id.send_email_btn:
			if (email != null) {
				MovieActivity mJinroDetailsActivity = (MovieActivity) getActivity();
				mJinroDetailsActivity
						.onJinroDetailsProfileFragmentSendEmailBtnClicked(email);

			} else {
				Toast.makeText(getActivity().getApplicationContext(),
						"입력된 이메일이 없습니다.", Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.show_ktid_btn:
			Toast.makeText(getActivity().getApplicationContext(),
					"입력된 카카오톡 아이디가 없습니다.", Toast.LENGTH_SHORT).show();
			// mPager.setCurrentItem(0);
			break;
		// case R.id.add_comment_btn:
		// Intent mStTpAct = new Intent(this, JinroDetailsActivity.class);
		// startActivity(mStTpAct);
		// break;
		default:
			break;
		}
	};

	/**
	 * Load Img Bitmap in Url
	 */

	private class ProfileImageLoadingTask extends
			AsyncTask<String, Bitmap, Bitmap> {
		protected Bitmap doInBackground(String... str) {

			// Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			URL url = null;

			try {

				url = new URL(str[0]);

			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			Bitmap bitmap = getRemoteImage(url);

			return bitmap;
		}

		protected void onPostExecute(Bitmap bitmap) {

			mProfileIv.setImageBitmap(bitmap);

		}
	}

	private Bitmap getRemoteImage(final URL url) {
		Bitmap bitmap = null;

		try {
			URLConnection conn = url.openConnection();
			conn.connect();

			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			bitmap = BitmapFactory.decodeStream(bis);
			bis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return bitmap;
	}

}
