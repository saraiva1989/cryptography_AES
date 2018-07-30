package com.danielsaraiva1.cryptoeas_ecb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.danielsaraiva1.cryptoeas_ecb.Helper.Criptografia;

public class MainActivity extends AppCompatActivity {

    Button btConverter;
    TextView txValorCript, txValorDescript;
    EditText edValorParaCript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btConverter = findViewById(R.id.btConverter);
        txValorCript = findViewById(R.id.txValorCript);
        txValorDescript = findViewById(R.id.txValorDescript);
        edValorParaCript = findViewById(R.id.edValorParaCript);

        btConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Criptografa o texto e retorna o hexadecimal
                byte[] valorCriptografado = Criptografia.Criptografar(edValorParaCript.getText().toString());
                String hexDisplayUser = Criptografia.BytesToHex(valorCriptografado);
                txValorCript.setText(hexDisplayUser);

                //recebe o hexadecimal e descriptografa o valor
                byte[] valorDescriptografar = Criptografia.HexStringToByteArray(hexDisplayUser);
                txValorDescript.setText(Criptografia.Descriptografar(valorDescriptografar));
            }
        });
    }
}


