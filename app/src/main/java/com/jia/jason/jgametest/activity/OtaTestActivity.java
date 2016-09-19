package com.jia.jason.jgametest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.helper.BitmapHelper;
import com.jia.jason.jgametest.model.JListItem;
import com.jia.jason.jgametest.view.FlightTabTagHost;
import com.jia.jason.jgametest.view.JListViewAdapter;
import com.jia.jason.jgametest.view.SlideLayoutContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin.jia
 * since 2016/5/18
 */
public class OtaTestActivity extends BaseActivity implements AdapterView.OnItemClickListener, FlightTabTagHost.QOnSelectedItemListener{

    private static final int LIST_VIEW_COUNTS = 3;
    private static final int FIRST_PAGE_SIZE = 1;
    private static final int FIRST_PAGE_ITAM_SIZE = 10;
    private static final int SECOND_PAGE_ITAM_SIZE = 10;
    private String[] titles = {"去程", "返程", "第一程", "第二程", "第三程"};
    private String[] prices = {"100", "200", "300", "400", "500"};

    private SlideLayoutContainer slideLayoutContainer;
    private LinearLayout firstContainer;
    private LinearLayout secondContainer;
    private FrameLayout rlContinuePull;
    private TextView tvContinuePull;
    private View continuePullLine;
    private View continuePullView, continuePullFooterLine;  //两页之间文案layout及左右的横线，用于添加在ListView的footer中
    private TextView tvContinuePullFooter;
    private FlightTabTagHost tbv;

    private List<ListView> otaListViewPages0;
    private List<ListView> otaListViewPages1;
    private List<ListView> currOtaListViewPages;
    private boolean hasBottomPage = true;
    private String bringUpText = "继续拖动，分开购买省3元";
    private String pullDownText = "释放返回特价包";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ota_test);

        slideLayoutContainer = (SlideLayoutContainer) findViewById(R.id.atom_flight_ota_list_layer_container);
        firstContainer = (LinearLayout) findViewById(R.id.atom_flight_tbv_container_first);
        secondContainer = (LinearLayout) findViewById(R.id.atom_flight_tbv_container_second);
        rlContinuePull = (FrameLayout) findViewById(R.id.atom_flight_ota_continue_pull_rl);
        tvContinuePull = (TextView) findViewById(R.id.atom_flight_tv_continue_pull);
        continuePullLine = findViewById(R.id.atom_flight_ota_continue_pull_line);

        continuePullView = getLayoutInflater().inflate(R.layout.atom_flight_ota_continue_pull_item, null);
        tvContinuePullFooter = (TextView) continuePullView.findViewById(R.id.atom_flight_tv_continue_pull);
        continuePullFooterLine = continuePullView.findViewById(R.id.atom_flight_ota_continue_pull_line);

        slideLayoutContainer.setActionAfterAnim(new Action<Integer>() {
            @Override
            public void execute(Integer args) {
                setDataByPageLayer(args);
                if (args == 1) {
                    modifyContinuePullView(pullDownText, View.GONE);
                } else {
                    modifyContinuePullView(bringUpText, View.VISIBLE);
                }
            }
        });

        otaListViewPages0 = genListViews(0, 1);
        currOtaListViewPages = otaListViewPages0;
        initListViewPage(0);

        otaListViewPages1 = genListViews(1, LIST_VIEW_COUNTS);
        currOtaListViewPages = otaListViewPages1;
        initListViewPage(1);

        setDataByPageLayer(0);
    }

    private void initListViewPage(int layer) {
        setAdapters(layer, currOtaListViewPages);
        initListView(layer, currOtaListViewPages);
    }

    private List<ListView> genListViews(int layer, int num) {
        if (num == 0) {
            num = 3;
        }
        List<ListView> listViews = new ArrayList<ListView>();
        for (int i = 0; i < num; i++) {
            ListView listView = new ListView(this);
            if (hasBottomPage) {
                listView.setOnScrollListener(slideLayoutContainer.getOnScrollListener(layer));
            }
            if (i < FIRST_PAGE_SIZE) {
                listView.setTag(0);
            } else {
                listView.setTag(1);
            }
            listViews.add(listView);
        }
        return listViews;
    }

    private void setAdapters(int layer, List<ListView> otaListViewPages) {
        List<JListItem> itemList;
        for (int i = 0; i < otaListViewPages.size(); i++) {
            if ((Integer)otaListViewPages.get(i).getTag() == 0) {
                itemList = JListItem.mockItems(FIRST_PAGE_ITAM_SIZE);
            } else {
                itemList = JListItem.mockItems2(SECOND_PAGE_ITAM_SIZE);
            }
            JListViewAdapter listViewAdapter = new JListViewAdapter(this, itemList);
            setContinuePullView(layer, i);
            otaListViewPages.get(i).setAdapter(listViewAdapter);
        }
    }

    private void initListView(int layer, List<ListView> otaListViewPages) {
        tbv = (FlightTabTagHost) getLayoutInflater().inflate(R.layout.atom_flight_ota_tab_host, null);
        tbv.setSelectedListener(this);
        tbv.setbodyLayoutId(layer == 0 ? R.id.atom_flight_tbv_container_first : R.id.atom_flight_tbv_container_second);
        LinearLayout.LayoutParams tbvLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.atom_flight_otatab_height));
        FrameLayout f = new FrameLayout(this);
        if (layer == 0) {
            firstContainer.addView(tbv, tbvLayoutParam);
            firstContainer.addView(f);
        } else {
            secondContainer.addView(tbv, tbvLayoutParam);
            secondContainer.addView(f);
        }
        if (otaListViewPages.size() > 1) {
            tbv.setVisibility(View.VISIBLE);
        } else {
            tbv.setVisibility(View.GONE);
        }
        for (int i = 0; i < otaListViewPages.size(); i ++) {
            f.addView(otaListViewPages.get(i));
            tbv.addItem(new FlightTabTagHost.TabItem(titles[i], "", i + 1, otaListViewPages.get(i)), BitmapHelper.px(14));
        }
        tbv.setCurrentIndex(1);
    }

    public void setDataByPageLayer(int layer) {
        if (layer == 0) {
            currOtaListViewPages = otaListViewPages0;
        } else {
            currOtaListViewPages = otaListViewPages1;
        }
    }

    private void modifyContinuePullView(String text, int linesVisibility) {
        if (!TextUtils.isEmpty(text)) {
            tvContinuePull.setText(text);
            tvContinuePullFooter.setText(text);
        }
        continuePullLine.setVisibility(linesVisibility);
        continuePullFooterLine.setVisibility(linesVisibility);
    }

    private void setContinuePullView(int layer, int index) {
        if (hasBottomPage && layer == 0) {
            final ListView listView = currOtaListViewPages.get(index);
            listView.post(new Runnable() {
                @Override
                public void run() {
                    slideLayoutContainer.setViewHeight(slideLayoutContainer.getBottom() - slideLayoutContainer.getTop());
                    int lastVisiblePosition = listView.getLastVisiblePosition();
                    int lastItemBottomPos = 0;
                    if (listView.getChildAt(lastVisiblePosition) != null) {
                        lastItemBottomPos = listView.getChildAt(lastVisiblePosition).getBottom();
                    }
                    if (lastItemBottomPos + BitmapHelper.dip2px(50) >= slideLayoutContainer.getViewHeight()) {   //一屏显示不完
                        rlContinuePull.setVisibility(View.GONE);
                        tvContinuePullFooter.setText(bringUpText);
                        listView.addFooterView(continuePullView);
                        slideLayoutContainer.setCanTopViewShowFully(false);
                    } else {
                        rlContinuePull.setVisibility(View.VISIBLE);
                        tvContinuePull.setText(bringUpText);
                        slideLayoutContainer.setCanTopViewShowFully(true);
                    }
                }
            });
        }
    }

    @Override
    public void onItemSelected(View v, int layoutId) {
        updateTabContent(layoutId - 1);
        //checkCanPullDown();
    }

    private void updateTabContent(int index) {
        int size = currOtaListViewPages.size();
        for (int i = 0; i < size; i++) {
            setTabFormatText(i, titles[i], prices[i], index == i);
        }
    }

    /**
     * 格式化tab内容
     *
     * @param index       当前tab索引
     * @param topText     tab上部文本内容
     * @param bottomPrice tab下部价格内容
     * @param hasColor    价格内容是否要改变颜色
     */
    private void setTabFormatText(int index, String topText, String bottomPrice, boolean hasColor) {
        if (!TextUtils.isEmpty(bottomPrice)) {
            StringBuilder sb = new StringBuilder();
            sb.append(topText);
            sb.append("\n");
            sb.append(bottomPrice);
            SpannableString ss = new SpannableString(sb.toString());
            int start = sb.toString().indexOf(bottomPrice);
            int end = sb.toString().length();
            ss.setSpan(new AbsoluteSizeSpan(12, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (hasColor) {
                ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.atom_flight_blue_common_color)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.atom_flight_text_secondarytitle)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            tbv.setItemLabelByIndex(index, ss);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}