package at.yearnning.os.studentcourt;

import java.util.ArrayList;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class FaqFragment extends SherlockFragment {

	static final int MENU_SET_MODE = 0;

	private LinkedList<Integer> mQuestions;
	private LinkedList<Integer> mAnswers;
	
	// private PullToRefreshGridView mPullRefreshGridView;
	private GridView mGridView;
	/**
	 * UI Reference
	 */

	// private GridView mGv;
	private YGvAdapter mYGvAdapter;

	private TextView mTitleTv;
	private TextView mSubtitleTv;

	private TextView mFooterDivider;
	private TextView mFooterNomore;

	// private ImageView mYGvIv;
	// private TextView mYGvTitleTv;
	// private TextView mYGvContentTv;
	// private TextView mYGvSummaryTv;
	// private FrameLayout mYGvContainer;
	// private ProgressBar mYGvIvPb;

	// private TextView mNicknameTv;
	// private TextView mTsTv;

	// private ImageView mIv;

	// private Button mLikeBtn;
	// private Button mDislikeBtn;
	// private Button mAddCommentBtn;

	/**
	 * Image Loading Que
	 **/

	private ArrayList<Integer> waiting_img_positions = new ArrayList<Integer>();
	private int updating_pos = 0;

	// private static Boolean waiting_img_isExits = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.faq_fragment, container, false);

		/**
		 * GridView Default Setting
		 */

		mGridView = (GridView) v.findViewById(R.id.gv);

		/**
		 * Set on pulldown to refresh // Set a listener to be invoked when the
		 * list should be refreshed.
		 */

		/**
		 * GridView Item Setting
		 */
		mQuestions = new LinkedList<Integer>();
		mAnswers = new LinkedList<Integer>();
		
		mQuestions.add(R.string.faq_q1);
		mAnswers.add(R.string.faq_a1);
		mQuestions.add(R.string.faq_q2);
		mAnswers.add(R.string.faq_a2);
		mQuestions.add(R.string.faq_q3);
		mAnswers.add(R.string.faq_a3);
		mQuestions.add(R.string.faq_q4);
		mAnswers.add(R.string.faq_a4);
		mQuestions.add(R.string.faq_q5);
		mAnswers.add(R.string.faq_a5);
		mQuestions.add(R.string.faq_q6);
		mAnswers.add(R.string.faq_a6);
		mQuestions.add(R.string.faq_q7);
		mAnswers.add(R.string.faq_a7);
		mQuestions.add(R.string.faq_q8);
		mAnswers.add(R.string.faq_a8);
		mQuestions.add(R.string.faq_q9);
		mAnswers.add(R.string.faq_a9);
		mQuestions.add(R.string.faq_q10);
		mAnswers.add(R.string.faq_a10);
		
		
		/**
		 * GridView Setting
		 */

		mYGvAdapter = new YGvAdapter(getActivity(), R.layout.faq_fragment_gv,
				mQuestions);

		mGridView.setAdapter(mYGvAdapter);

		/**
		 * Grid View Click Listener
		 */
		mGridView.setOnItemClickListener(onItemClickListener);
		// mPullRefreshGridView
	

		return v;
	}

	/**
	 * GridView Apdater Setting
	 */

	private class YGvAdapter extends ArrayAdapter<Integer> {

		private Context context;
		LinkedList<Integer> JinroData;
		int textViewResourceId;

		public YGvAdapter(Activity context, int textViewResourceId,
				LinkedList<Integer> JinroData) {
			super(context, textViewResourceId, JinroData);

			this.context = context;
			this.JinroData = JinroData;
			this.textViewResourceId = textViewResourceId;
		}

		@Override
		public int getCount() {
			return JinroData.size();
		}

		@Override
		public Integer getItem(int position) {
			return JinroData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			/**
			 * UI Initiailizing
			 */

			// if (convertView == null) {
			convertView = getActivity().getLayoutInflater().inflate(
					textViewResourceId, null);
			// mYGvIv = (ImageView) convertView.findViewById(R.id.y_gv_iv);

			mTitleTv = (TextView) convertView.findViewById(R.id.name_tv);
			mSubtitleTv = (TextView) convertView.findViewById(R.id.univ_tv);

			mFooterDivider = (TextView) convertView
					.findViewById(R.id.footer_divider);
			mFooterNomore = (TextView) convertView
					.findViewById(R.id.footer_nomore);

			if (position == JinroData.size() - 1) {
				mFooterDivider.setVisibility(View.VISIBLE);
				mFooterNomore.setVisibility(View.VISIBLE);

			} else {

				mFooterDivider.setVisibility(View.GONE);
				mFooterNomore.setVisibility(View.GONE);

			}

			// mTsTv = (TextView) convertView.findViewById(R.id.ts_tv);

			// mYGvSummaryTv = (TextView) convertView
			// .findViewById(R.id.y_gv_summary_tv);
			// mYGvContainer = (FrameLayout) convertView
			// .findViewById(R.id.y_gv_container);
			// mYGvIvPb = (ProgressBar)
			// convertView.findViewById(R.id.y_gv_iv_pb);

			// }

			// mIv = (ImageView) convertView.findViewById(R.id.iv);

			// mAddCommentBtn = (Button) convertView
			// .findViewById(R.id.add_comment_btn);
			// mAddCommentBtn.setOnClickListener(onClickListener);
			// mLikeBtn = (Button) convertView.findViewById(R.id.like_btn);
			// mDislikeBtn = (Button)
			// convertView.findViewById(R.id.dislike_btn);

			/**
			 * Data Setting
			 */

			// int dislike_num = MbSpData.get(position).getDislikeNum();
			// int like_num = MbSpData.get(position).getLikeNum();

			// int id = MbSpData.get(position).getId();
			// int ts = MbSpData.get(position).getTs();
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd. HH:mm",
			// Locale.KOREA);

			// ArrayList<String> img_urls = MbSpData.get(position).getImgUrls();

			mTitleTv.setText(JinroData.get(position));
			mSubtitleTv.setText(mAnswers.get(position));
			// mTsTv.setText(sdf.format(new Date(ts)));

			// mLikeBtn.setText("+" + Integer.toString(like_num));
			// mDislikeBtn.setText("-" + Integer.toString(dislike_num));

			// if (from_whom.getName() != "") {

			// mYGvTitleTv.setText(title);

			// }

			// mYGvSummaryTv.setText(CpFpData.get(position).getSummary());
			// mYGvIv.setImageResource(CpFpData.get(position).getImg());

			// if (img_urls.size() != 0) {

			// for (int i = 0; i < img_urls.size(); i++) {
			// if (MbSpData.get(position).getImgState(i) == false) {
			// Log.e("img_url", img_urls.get(i));
			// new
			// ImageLoadingTask().execute(img_urls.get(i).toString());
			// waiting_img_urls.add(img_urls.get(i));

			// int[] waiting_img_location = { position, i };

			// waiting_img_locations.add(waiting_img_location);

			// if (waiting_img_isExits == false) {
			// new ImageLoadingTask().execute(waiting_img_urls
			// .get(0));
			// waiting_img_isExits = true;
			// }

			// }

			// }

			// mIv.setVisibility(View.VISIBLE);

			// if (MbSpData.get(position).getImgState(0)) {
			// mIv.setImageBitmap(MbSpData.get(position).getImgBitmap(0));
			// }

			// } else {

			// mIv.setVisibility(View.GONE);

			// }

			return convertView;
		}

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
		// case R.id.profile_btn:
		// // mPager.setCurrentItem(0);
		// Toast.makeText(getApplicationContext(), "아직 준비중인 기능입니다.",
		// Toast.LENGTH_SHORT).show();
		// break;
		// case R.id.list_btn:
		// mPager.setCurrentItem(0);
		// break;
		// case R.id.add_comment_btn:
		// Intent mStTpAct = new Intent(this, JinroDetailsActivity.class);
		// startActivity(mStTpAct);
		// break;
		default:
			break;
		}
	};

	

	/**
	 * GridView Click Event Setting
	 */

	private OnItemClickListener onItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			int question = mQuestions.get(position);
			int answer = mAnswers.get(position);
			
			MainActivity mMainActivity = (MainActivity) getActivity();
			mMainActivity.onFaqFragmentGvItemPressed(question, answer);

		}
	};

	/**
	 * 
	 * Save and Restore
	 */

}
