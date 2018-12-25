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

import static com.aplikasi.distaru.bandung.R.id.kontenPdfShalat;

/**
 * Created by HanifAlKamal on 05/12/2017.
 */

public class teknopolis extends Fragment {
    PDFView pdfView;

    @BindView(kontenPdfShalat)PDFView kontenShalat; //1

    public static Fragment newInstance() {
        teknopolis frgShalat = new teknopolis(); //2
        return frgShalat;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frg_salat, container, false); //3
        ButterKnife.bind(this, v);

        kontenShalat.fromAsset("VII BANDUNG TEKNOPOLIS PRESENTATION.pdf").load(); //4

        return v;

    }
}
