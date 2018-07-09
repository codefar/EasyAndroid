package org.greenleaf.easyandroid.main;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import org.greenleaf.easyandroid.R;
import org.greenleaf.easyandroid.api.GankIoService;
import org.greenleaf.easyandroid.base.BaseFragment;
import org.greenleaf.easyandroid.bean.Fuli;
import org.greenleaf.easyandroid.core.utils.ApiResponse;
import org.greenleaf.easyandroid.core.utils.RxSchedulers;
import org.greenleaf.easyandroid.di.ComponentManager;
import org.greenleaf.easyandroid.di.component.DaggerFragmentComponent;
import org.greenleaf.easyandroid.di.component.FragmentComponent;
import org.greenleaf.easyandroid.di.module.FragmentModule;

import java.util.List;

import javax.inject.Inject;

import in.srain.cube.views.ptr.PtrFrameLayout;
import io.reactivex.functions.Consumer;

public class MainFragment extends BaseFragment {

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private PtrFrameLayout mPtrFrame;
    private RecyclerView mRecyclerView;
    private WaterFallAdapter mAdapter;
    private FragmentComponent mFragmentComponent;

    @Inject
    GankIoService gankIoService;

    public MainFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentComponent = DaggerFragmentComponent.builder().fragmentModule(new FragmentModule(this)).applicationComponent(ComponentManager.appComponent()).build();
        mFragmentComponent.inject(this);
    }

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
        gankIoService.getFuli(100, 1).compose(RxSchedulers.<ApiResponse<List<Fuli>>>compose()).subscribe(new Consumer<ApiResponse<List<Fuli>>>() {
            @Override
            public void accept(ApiResponse<List<Fuli>> fuliBeanApiResponse) throws Exception {
                mAdapter.getList().addAll(fuliBeanApiResponse.getResults());
                mAdapter.getRandomHeight(fuliBeanApiResponse.getResults());
                mAdapter.notifyDataSetChanged();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mLogger.error("getJsonData", throwable);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
