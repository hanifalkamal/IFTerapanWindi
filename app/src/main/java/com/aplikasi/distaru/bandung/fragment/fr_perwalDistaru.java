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

import static com.aplikasi.distaru.bandung.R.id.kontenPdfKamar;

/**
 * Created by HanifAlKamal on 05/12/2017.
 */

public class fr_perwalDistaru extends Fragment {
    PDFView pdfView;

    @BindView(kontenPdfKamar)PDFView kontenkamar;

    public static Fragment newInstance() {
        fr_perwalDistaru frgKamar = new fr_perwalDistaru();
        return frgKamar;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frg_kamar, container, false);
        ButterKnife.bind(this, v);

        kontenkamar.fromAsset("IV PERWAL DISTARU KOTA BANDUNG.pdf").load();

        return v;

    }
}
