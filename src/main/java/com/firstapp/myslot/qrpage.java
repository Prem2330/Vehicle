package com.firstapp.myslot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qrpage extends AppCompatActivity {
    EditText etInput;
    Button btGenerate;
    ImageView ivOutput;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpage);
        etInput=findViewById(R.id.et_input);
        btGenerate=findViewById(R.id.bt_generate);
        ivOutput=findViewById(R.id.iv_output);
        txt=(TextView) findViewById(R.id.txt);
        MaterialButton Home=(MaterialButton) findViewById(R.id.home);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Homepage.class);
                startActivity(intent);
                finish();
            }
        });

        btGenerate.setOnClickListener(new View.OnClickListener(){
            @Override
             public void onClick(View view){
                String sText=etInput.getText().toString().trim();
                MultiFormatWriter writer = new MultiFormatWriter();

                try {
                    BitMatrix matrix =writer.encode(sText, BarcodeFormat.QR_CODE,350,350);
                    BarcodeEncoder encoder=new BarcodeEncoder();
                    Bitmap bitmap=encoder.createBitmap(matrix);
                    ivOutput.setImageBitmap(bitmap);
                    InputMethodManager manager=(InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE
                    );

                    manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(),0);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
                txt.setText("Thank you for Booking your slot!!.Your slot number is 72");
            }
        });


    }
}