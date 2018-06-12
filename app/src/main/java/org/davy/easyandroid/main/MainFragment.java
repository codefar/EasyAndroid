package org.davy.easyandroid.main;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import org.davy.easyandroid.R;
import org.davy.easyandroid.api.GankIoService;
import org.davy.easyandroid.base.BaseFragment;
import org.davy.easyandroid.bean.ApiResponse;
import org.davy.easyandroid.bean.FuliBean;
import org.davy.easyandroid.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import in.srain.cube.views.ptr.PtrFrameLayout;
import io.reactivex.functions.Consumer;

public class MainFragment extends BaseFragment {

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private PtrFrameLayout mPtrFrame;
    private RecyclerView mRecyclerView;
    private WaterFallAdapter mAdapter;

    @Inject
    GankIoService gankIoService;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPtrFrame = findViewById(R.id.store_house_ptr_frame);
        mRecyclerView = findViewById(R.id.listView);
    }


    protected void initData() {
        mRecyclerView.setLayoutManager(new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new WaterFallAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        getJsonData();
    }

    private void getJsonData(){
        gankIoService.getFuli(10, 1).compose(RxSchedulers.<ApiResponse<List<FuliBean>>>compose()).subscribe(new Consumer<ApiResponse<List<FuliBean>>>() {
            @Override
            public void accept(ApiResponse<List<FuliBean>> fuliBeanApiResponse) throws Exception {
                mAdapter.getList().addAll(fuliBeanApiResponse.getResults());
                mAdapter.getRandomHeight(fuliBeanApiResponse.getResults());
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
