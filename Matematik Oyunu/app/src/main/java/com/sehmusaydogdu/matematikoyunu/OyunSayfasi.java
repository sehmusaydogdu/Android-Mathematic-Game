package com.sehmusaydogdu.matematikoyunu;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class OyunSayfasi extends AppCompatActivity {


    private TextView txt1,txt2,Isaret,txtHak,txtHighSkor,txtSkor;
    private Button button1,button2,button3,button4;

    private double sonuc;    //İşlem sonucunu atayacak.
    private double sayi1,sayi2; //Uretilen sayiari gecici olarak bu değişkene atayacak.
    private int secilenSeviye; // Kullanıcının seçtiği seviyeyi değişkene attım.
    private int rndButtonSec; // Sonucu rastgele bir butona atmak için gecici kullanıyorum.

    private Random rnd=new Random(); //Random nesnesi tanımlandı.*/
    private int limit=0;
    private int tikladim=0;
    private int txtSkorInt,txtHighSkorInt,txtHakInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_sayfasi);

        Intent intent=getIntent();
        secilenSeviye=intent.getIntExtra("secilen",0);

        NesneleriYakala();
        OyunuBaslat();

    }

    private void NesneleriYakala(){
        button1=(Button)findViewById(R.id.btn1);
        button2=(Button)findViewById(R.id.btn2);
        button3=(Button)findViewById(R.id.btn3);
        button4=(Button)findViewById(R.id.btn4);


        txtHak=(TextView)findViewById(R.id.Hak);
        txtHakInt=Integer.parseInt(txtHak.getText().toString());

        txtHighSkor=(TextView)findViewById(R.id.HighSkor);
        txtHighSkorInt=Integer.parseInt(txtHighSkor.getText().toString());

        txtSkor=(TextView)findViewById(R.id.Skor);
        txtSkorInt=Integer.parseInt(txtSkor.getText().toString());

        txt1=(TextView)findViewById(R.id.txtSayi1); //TextView1 elemanının id'sini kullanarak yakaladım.
        txt2=(TextView)findViewById(R.id.txtSayi2); //TextView2 elemanının id'sini kullanarak yakaladım.

        txtSkor.setText("Skorunuz :  "+String.valueOf(txtSkorInt));
        txtHighSkor.setText("En Yüksek Skor :  "+String.valueOf(txtHighSkorInt));
    }

    public void OyunuBaslat(){
        buttonRenkDefault();
        rastgeleSayiUret(secilenSeviye);
        rastgeleIsaretUret(secilenSeviye);
        rndButonaSonucYaz();
    }

    private void rastgeleIsaretUret(int seviye){
        seviye++;
        Isaret=(TextView)findViewById(R.id.txtIsaret);
        switch (1+rnd.nextInt(seviye)) {
            case 1:
                sonuc=sayi1+sayi2;
                Isaret.setText("+");
                break;
            case 2:
                sonuc=sayi1-sayi2;
                Isaret.setText("-");
                break;
            case 3:
                sonuc=sayi1*sayi2;
                Isaret.setText("*");
                break;
            case 4:
                sonuc=sayi1/sayi2;
                Isaret.setText("/");
                break;
        }
    }

    private void rastgeleSayiUret(int seviye){

        switch (seviye){
            case 1:limit=10;break;
            case 2:limit=50;break;
            case 3:limit=100;break;
        }
        sayi1=1+rnd.nextInt(limit); //Rastgele 1 ile (limit) arasında sayı üretildi.(sayi 1)
        sayi2=1+rnd.nextInt(limit); //Rastgele 1 ile (limit) arasında sayı üretildi.(sayi 2)

        txt1.setText(String.valueOf((int)sayi1)); //Uretilen sayiyi TextView ' e atadım.
        txt2.setText(String.valueOf((int)sayi2)); //Uretilen sayiyi TextView 'e atadım.

    }

    private void HakKontrolEt(){

        txtHakInt--;

        String message;
        if(txtHakInt>0){
            txtHak.setText(String.valueOf(txtHakInt));
        }
        else {

            txtHak.setText(String.valueOf("0"));

            if (txtSkorInt>txtHighSkorInt){

                message="Tebrikler, Skoru Geçtiniz  :  "+String.valueOf(txtSkorInt);
                Toast.makeText(this,"Tebrikler, Skoru Geçtiniz.",Toast.LENGTH_LONG).show();
                txtHighSkor.setText("En Yüksek Skor :  "+String.valueOf(txtSkorInt));
                txtHighSkorInt=txtSkorInt;
            }
            else {
                message="Üzgünüm, Skoru geçemediniz  :  "+txtSkorInt;
                Toast.makeText(this,"Üzgünüm, Skoru geçemediniz",Toast.LENGTH_SHORT).show();
            }
            txtSkorInt=0;
            txtSkor.setText("Skorunuz :  "+String.valueOf("0"));

            Intent intent=new Intent(this,BitisSayfasi.class);
            intent.putExtra("Message",message);
            startActivity(intent);
            finish();

        }
    }

    public void btn1_Clicked(View view) throws InterruptedException {

        tikladim++;
        if (tikladim==1){

            if (Double.parseDouble(button1.getText().toString()) == sonuc){
                txtSkorInt+=10;
                txtSkor.setText("Skorunuz :  "+String.valueOf(txtSkorInt));
                button1.setBackgroundColor(Color.parseColor("#89ff00"));
                Thread.sleep(500);
                OyunuBaslat();
            }
            else
            {
                button1.setBackgroundColor(Color.RED);
                HakKontrolEt();
            }
        }
        else {
            button1.setBackgroundColor(Color.RED);
            HakKontrolEt();
        }
        tikladim=0;
    }

    public void btn2_Clicked(View view)throws InterruptedException {
        tikladim++;
        if (tikladim==1){

            if (Double.parseDouble(button2.getText().toString()) == sonuc){
                txtSkorInt+=10;
                txtSkor.setText("Skorunuz :  "+String.valueOf(txtSkorInt));
                button2.setBackgroundColor(Color.parseColor("#89ff00"));
                Thread.sleep(500);
                OyunuBaslat();
            }
            else
            {
                button2.setBackgroundColor(Color.RED);
                HakKontrolEt();
            }
        }
        else {
            button2.setBackgroundColor(Color.RED);
            HakKontrolEt();
        }
        tikladim=0;
    }

    public void btn3_Clicked(View view)throws InterruptedException {
        tikladim++;
        if (tikladim==1){

            if (Double.parseDouble(button3.getText().toString()) == sonuc){
                txtSkorInt+=10;
                txtSkor.setText("Skorunuz :  "+String.valueOf(txtSkorInt));
                button3.setBackgroundColor(Color.parseColor("#89ff00"));
                Thread.sleep(500);
                OyunuBaslat();
            }
            else
            {
                button3.setBackgroundColor(Color.RED);
                HakKontrolEt();
            }
        }
        else {
            button3.setBackgroundColor(Color.RED);
            HakKontrolEt();
        }
        tikladim=0;
    }

    public void btn4_Clicked(View view)throws InterruptedException {
        tikladim++;
        if (tikladim==1){

            if (Double.parseDouble(button4.getText().toString()) == sonuc){
                txtSkorInt+=10;
                txtSkor.setText("Skorunuz :  "+String.valueOf(txtSkorInt));
                button4.setBackgroundColor(Color.parseColor("#89ff00"));
                Thread.sleep(500);
                OyunuBaslat();
            }
            else
            {
                button4.setBackgroundColor(Color.RED);
                HakKontrolEt();
            }
        }
        else {
            button4.setBackgroundColor(Color.RED);
            HakKontrolEt();
        }
        tikladim=0;
    }

    private void rndButonaSonucYaz(){

        double gecici=sonuc;

        rndButtonSec=1+rnd.nextInt(4);

        for (int i=1;i<=4;i++){
            if(rndButtonSec==i){
                SwitchSonucYaz(i,sonuc);
            }
            else{
                gecici=gecici+5;
                SwitchSonucYaz(i,gecici);
            }
        }
    }
    private void SwitchSonucYaz(int i,double gecici){
        switch (i){
            case 1:button1.setText(String.valueOf(gecici));break;
            case 2:button2.setText(String.valueOf(gecici));break;
            case 3:button3.setText(String.valueOf(gecici));break;
            case 4:button4.setText(String.valueOf(gecici));break;
        }
    }
    private void buttonRenkDefault(){
        button1.setBackgroundColor(Color.parseColor("#c2dad7"));
        button2.setBackgroundColor(Color.parseColor("#c2dad7"));
        button3.setBackgroundColor(Color.parseColor("#c2dad7"));
        button4.setBackgroundColor(Color.parseColor("#c2dad7"));

    }
}
