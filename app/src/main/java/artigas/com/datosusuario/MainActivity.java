package artigas.com.datosusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextInputLayout txtInputNombre;
    TextInputLayout txtInputTelefono;
    TextInputLayout txtInputEmail;
    TextInputLayout txtInputDescripcion;

    TextView txtDatesaved;

    DatePicker dateInput;
    int ano;
    int mes;
    int dia;

    private String savedNombre, savedFecha, savedTelefono, savedEmail, savedDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInputNombre = (TextInputLayout) findViewById(R.id.txtNombre);
        txtInputTelefono = (TextInputLayout) findViewById(R.id.txtTelefono);
        txtInputEmail = (TextInputLayout) findViewById(R.id.txtEmail);
        txtInputDescripcion = (TextInputLayout) findViewById(R.id.txtDescripcion);

        dateInput = (DatePicker) findViewById(R.id.pckFecha);

        // Volver y setar la fecha del dia
        Button butonPckCancel = findViewById(R.id.btn_Cancel);
        butonPckCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar today = Calendar.getInstance();
                ano = today.get(Calendar.YEAR);
                mes = today.get(Calendar.MONTH);
                dia = today.get(Calendar.DAY_OF_MONTH);
                dateInput.init(ano,mes,dia,null);

                txtDatesaved = findViewById(R.id.txtpckSaveDate);
                txtDatesaved.setText(DateFormat.getDateInstance().format(dateInput.getCalendarView().getDate()));
            }
        });

        Button butonPckOk = findViewById(R.id.btn_Ok);
        butonPckOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedFecha = DateFormat.getDateInstance().format(dateInput.getCalendarView().getDate());

                txtDatesaved = findViewById(R.id.txtpckSaveDate);
                txtDatesaved.setText(DateFormat.getDateInstance().format(dateInput.getCalendarView().getDate()));
            }
        });

        Button butonSiguinte = findViewById(R.id.btnSiguiente);
        butonSiguinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validateFecha() | !validateTelefono() | !validateEmail() | !validateDescripcion()) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.carga_default), Toast.LENGTH_SHORT).show();
                } else {
                    savedNombre = txtInputNombre.getEditText().getText().toString();
                    savedFecha = DateFormat.getDateInstance().format(dateInput.getCalendarView().getDate());
                    savedTelefono = txtInputTelefono.getEditText().getText().toString();
                    savedEmail = txtInputEmail.getEditText().getText().toString();
                    savedDescription = txtInputDescripcion.getEditText().getText().toString();

                    Intent cambioActivity = new Intent(MainActivity.this, ConfirmarDatos.class);
                    cambioActivity.putExtra("Nombre", savedNombre);
                    cambioActivity.putExtra("Fecha", savedFecha);
                    cambioActivity.putExtra("Telefono", savedTelefono);
                    cambioActivity.putExtra("Email", savedEmail);
                    cambioActivity.putExtra("Descripcion", savedDescription);
                    startActivityForResult(cambioActivity,1);
                }
            }
        });

    }

    private boolean validateName(){
        String nombreInput = txtInputNombre.getEditText().getText().toString().trim();

        if (nombreInput.isEmpty()) {
            txtInputNombre.getEditText().setText(getResources().getString(R.string.default_nombre));
            //txtInputNombre.setError("Hay que inserir un nombre");
            return false;
        } else if (nombreInput.length() > 30) {
            txtInputNombre.setError(getResources().getString(R.string.default_nombre_error_msg));
            return false;
        } else {
            txtInputEmail.setError(null);
            return true;
        }
    }

    private boolean validateFecha(){
        String FechaInput = DateFormat.getDateInstance().format(dateInput.getCalendarView().getDate());

        if (FechaInput.isEmpty()) {
            Calendar today = Calendar.getInstance();
            ano = today.get(Calendar.YEAR);
            mes = today.get(Calendar.MONTH);
            dia = today.get(Calendar.DAY_OF_MONTH);
            dateInput.init(ano,mes,dia,null);
            return false;
        } else return true;
    }

    private boolean validateTelefono(){
        String telefonoInput = txtInputTelefono.getEditText().getText().toString().trim();

        if (telefonoInput.isEmpty()) {
            txtInputTelefono.getEditText().setText(getResources().getString(R.string.default_telefono));
            return false;
        } else {
            txtInputTelefono.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String emailInput = txtInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            txtInputEmail.getEditText().setText(getResources().getString(R.string.default_email));
            return false;
        } else {
            txtInputEmail.setError(null);
            return true;
        }
    }

    private boolean validateDescripcion(){
        String descripcionInput = txtInputDescripcion.getEditText().getText().toString().trim();

        if (descripcionInput.isEmpty()) {
            txtInputDescripcion.getEditText().setText(getResources().getString(R.string.default_description));
            return false;
        } else {
            txtInputDescripcion.setError(null);
            return true;
        }
    }
}