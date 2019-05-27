package br.com.lucasfsilva.exemplobarcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {
    private ImageView imgQRcode;
    private Button btnScan;
    private TextView tvMensagem;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgQRcode = (ImageView) findViewById(R.id.imgQRcode);
        tvMensagem = (TextView) findViewById(R.id.tvMensagem);
        btnScan = (Button) findViewById(R.id.btnScan);

        bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.qrcode);
        imgQRcode.setImageBitmap(bitmap);


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(getApplicationContext()).setBarcodeFormats(Barcode.QR_CODE).build();
                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<Barcode> barcodeSparseArray = barcodeDetector.detect(frame);
                Barcode barcode = barcodeSparseArray.valueAt(0);

                tvMensagem.setText(barcode.rawValue);
            }
        });
    }
}
