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

import static com.aplikasi.distaru.bandung.R.id.kontenPdfPanitia;

/**
 * Created by HanifAlKamal on 05/12/2017.
 */

public class fr_baganDistaru extends Fragment {
    PDFView pdfView;

    @BindView(kontenPdfPanitia)PDFView kontenPanitia;

    public static Fragment newInstance() {
        fr_baganDistaru frgPanitia = new fr_baganDistaru();
        return frgPanitia;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frg_panitia, container, false);
        ButterKnife.bind(this, v);

        kontenPanitia.fromAsset("III BAGAN STRUKTUR ORGANISASI DINAS PENATAAN RUANG.pdf").load();

        return v;

    }
}
