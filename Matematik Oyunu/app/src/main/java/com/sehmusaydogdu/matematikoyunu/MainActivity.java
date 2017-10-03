package com.sehmusaydogdu.matematikoyunu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private RadioButton seviye1;
    private RadioButton seviye2;
    private RadioButton seviye3;
    private int secilen=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seviye1=(RadioButton)findViewById(R.id.kolaySeviye);
        seviye2=(RadioButton)findViewById(R.id.ortaSeviye);
        seviye3=(RadioButton)findViewById(R.id.zorSeviye);
    }

    public void btnBasla(View view){

        if(seviye1.isChecked())secilen=1;  //Seviye : Başlangıç Seçilmiştir.
        if(seviye2.isChecked())secilen=2;  // Seviye : Orta Seçilmiştir.
        if(seviye3.isChecked())secilen=3;  //Seviye : Zor seçilmiştir

        if(secilen>0){
            Intent intent=new Intent(getApplicationContext(),OyunSayfasi.class);
            intent.putExtra("secilen", secilen);
            startActivity(intent);
        }
        else
            Toast.makeText(this, "Lütfen seviye seçimi yapınız !!!", Toast.LENGTH_SHORT).show();

    }

    public void btnCikis(View view){

        AlertDialog alertMessage = new AlertDialog.Builder(this).create();
        alertMessage.setTitle("Çıkış");
        alertMessage.setMessage("Çıkış Yapmak İstediğinizden Emin Misiniz ?");

        alertMessage.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });

        alertMessage.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertMessage.show();

    }

}
