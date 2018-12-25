package com.aplikasi.distaru.bandung.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aplikasi.distaru.bandung.R;
import com.github.barteksc.pdfviewer.PDFView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by HanifAlKamal on 05/12/2017.
 */

public class fr_home extends Fragment{
    PDFView pdfView;

    @BindView(R.id.kontenPdf1)PDFView kontenPdf1;
    public static Fragment newInstance(){
        fr_home frg1 = new fr_home();
        return frg1;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment1, container, false);
        ButterKnife.bind(this, v);

        kontenPdf1.fromAsset("INFORMATIKA ITENAS.pdf").load();

        return v;

    }
}
