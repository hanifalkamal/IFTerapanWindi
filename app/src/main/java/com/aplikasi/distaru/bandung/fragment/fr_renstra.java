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

public class fr_renstra extends Fragment {
    PDFView pdfView;

    @BindView(R.id.kontenPdfRenstra)PDFView kontenPdfBali;
    public static Fragment newInstance(){
        fr_renstra frgRenstra = new fr_renstra();
        return frgRenstra;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frg_renstra, container, false);
        ButterKnife.bind(this, v);

        kontenPdfBali.fromAsset("VI RENSTRA DISTARU KOTA BANDUNG.pdf").load();

        return v;

    }
}