package artigas.com.datosusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceStateConfirmarDatos) {
        super.onCreate(savedInstanceStateConfirmarDatos);
        setContentView(R.layout.activity_confirmar_datos);

        String savedExtraNombre = getIntent().getStringExtra("Nombre");
        TextView nombreText = (TextView) findViewById(R.id.txtNombreshow);
        nombreText.setText(savedExtraNombre);

        String savedExtraFecha = getIntent().getStringExtra("Fecha");
        TextView fechaText = (TextView) findViewById(R.id.txtFechashow);
        fechaText.setText(savedExtraFecha);

        String savedExtraTelefono = getIntent().getStringExtra("Telefono");
        TextView telText = (TextView) findViewById(R.id.txtTelshow);
        telText.setText(savedExtraTelefono);

        String savedExtraEmail = getIntent().getStringExtra("Email");
        TextView emailText = (TextView) findViewById(R.id.txtEmailshow);
        emailText.setText(savedExtraEmail);

        String savedExtraDescripcion = getIntent().getStringExtra("Descripcion");
        TextView descripcionText = (TextView) findViewById(R.id.txtDescripcionshow);
        descripcionText.setText(savedExtraDescripcion);

        Button butonEditar = findViewById(R.id.btnEditar);
        butonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverMain = new Intent();
                setResult(RESULT_CANCELED,volverMain);
                finish();
            }
        });

    }

}