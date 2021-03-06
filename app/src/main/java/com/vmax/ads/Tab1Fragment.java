package com.vmax.ads;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.vmax.android.ads.api.VmaxAdView;
import com.vmax.android.ads.common.VmaxAdListener;
import com.vmax.android.ads.exception.VmaxAdError;

/**
 * Created by P$ on 06-09-2017.
 */

public class Tab1Fragment extends Fragment {

    RecyclerView recyclerView;
    VmaxAdView vmaxAdView;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         rootView = inflater.inflate(R.layout.fragment_layout, container, false);

      InitializeRecycler();


        return rootView;
    }

    public void InitializeRecycler()
    {
        recyclerView=(RecyclerView) rootView.findViewById(R.id.recy_view);
        InitData initData=new InitData(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecycleAdapter recycleAdapter=new RecycleAdapter(initData.fillList(),getAdView());
        recyclerView.setAdapter(recycleAdapter);
    }





    public VmaxAdView getAdView()
    {

            vmaxAdView = new VmaxAdView(getActivity(), "9060b6e3", VmaxAdView.UX_NATIVE);


            RelativeLayout container2 = (RelativeLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.vmax_prog_custom_native_layout, null);
            vmaxAdView.setCustomNativeAdContainer(container2);


        vmaxAdView.setAdListener(new VmaxAdListener() {
                @Override
                public void onAdReady(VmaxAdView vmaxAdView) {

                    vmaxAdView.showAd();


                }

                @Override
                public void onAdError(VmaxAdError vmaxAdError) {


                    Toast.makeText(getContext(), "Failed To Load AD Please Try again", Toast.LENGTH_LONG).show();


                }

                @Override
                public void onAdClose() {

                }

                @Override
                public void onAdMediaEnd(boolean b, long l) {

                }
            });

            vmaxAdView.cacheAd();
            return vmaxAdView;

    }
}
