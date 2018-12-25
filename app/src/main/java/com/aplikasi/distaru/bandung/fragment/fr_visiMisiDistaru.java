package com.aplikasi.distaru.bandung.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aplikasi.distaru.bandung.FormDaftar;
import com.aplikasi.distaru.bandung.R;
import com.github.barteksc.pdfviewer.PDFView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HanifAlKamal on 05/12/2017.
 */

public class fr_visiMisiDistaru extends Fragment{
    PDFView pdfView;
    String tes;

    @BindView(R.id.kontenPdf2)PDFView kontenPdfDua;
    @BindView(R.id.button)Button btn;
    public static Fragment newInstance(){
        fr_visiMisiDistaru frg2 = new fr_visiMisiDistaru();
        return frg2;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment2, container, false);
        ButterKnife.bind(this, v);

        kontenPdfDua.fromAsset("II VISI MISI DISTARU.pdf").load();
        return v;

    }

    @OnClick(R.id.button) void klik(){

        //String tes = "http://www.iasiitenasbdg.com/kc/insert_siswa.php?macaddress=1234&nama=3343&kelas=11&jurusan=IPA&lokasi=ASDSAD";

        //FormDaftar fdf = new FormDaftar();

        //KirimLokasi kl = new KirimLokasi();
        //kl.exeUrl(fdf.getUrl());

        Intent in = new Intent(this.getActivity(), FormDaftar.class);
        startActivity(in);
    }
}
