package com.example.gestionresiduosapp.ui.Calendario;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestionresiduosapp.R;
import com.example.gestionresiduosapp.ui.PantallaPrincipal;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class PantallaCalendario extends AppCompatActivity {

    //Declaración de las vistas
    private Button botonVolver, botonAñadirRecordatorio;
    private CalendarView calendarView;
    private LinearLayout linearLayoutRecordatorios;
    private int contadorRecordatorios = 0;
    private static final int MAX_RECORDATORIOS = 5;
    private static final String PREFERENCES_FILE = "recordatorios";
    private static final String RECORDATORIOS_KEY = "recordatorios";

    //Método onCreate para inicializar la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_calendario);

        //Inicialización de las vistas
        calendarView = findViewById(R.id.calendarView);
        botonVolver = findViewById(R.id.boton_volver);
        botonAñadirRecordatorio = findViewById(R.id.botonAñadirRecordatorio);
        linearLayoutRecordatorios = findViewById(R.id.linearLayoutRecordatorios);

        //Obtención de la fecha actual
        Calendar calendar = Calendar.getInstance();
        long currentDate = calendar.getTimeInMillis();
        calendarView.setDate(currentDate, true, true);

        //Configuración del botón volver
        botonVolver.setOnClickListener(v -> {startActivity(new Intent(PantallaCalendario.this, PantallaPrincipal.class));});

        //Configuración del botón añadir recordatorio con verificación del límite de recordatorios
        botonAñadirRecordatorio.setOnClickListener(v -> {
            if (contadorRecordatorios >= MAX_RECORDATORIOS) {
                Toast.makeText(PantallaCalendario.this, "No se pueden añadir más de 5 recordatorios.", Toast.LENGTH_SHORT).show();
            } else {
                mostrarDialogoAñadir();
            }
        });

        //Cargar recordatorios guardados al iniciar la pantalla
        cargarRecordatoriosGuardados();
    }

    //Método para mostrar el diálogo de entrada
    private void mostrarDialogoAñadir() {
        //Lanzar el layout del diálogo
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_input, null);
        EditText nombreInput = dialogView.findViewById(R.id.editTextNombre);
        EditText fechaInput = dialogView.findViewById(R.id.editTextFecha);
        EditText horaInput = dialogView.findViewById(R.id.editTextHora);

        //Configurar el diálogo
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Añadir Recordatorio");
        dialogBuilder.setPositiveButton("Guardar", null);
        dialogBuilder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        //Mostrar el diálogo
        AlertDialog dialog = dialogBuilder.create();

        //Configurar el botón "Guardar"
        dialog.setOnShowListener(dialogInterface -> {
            Button buttonGuardar = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            //Función lambda para guardar el recordatorio cuando se hace clic en el botón "Guardar"
            buttonGuardar.setOnClickListener(v -> {
                String nombre = nombreInput.getText().toString();
                String fecha = fechaInput.getText().toString();
                String hora = horaInput.getText().toString();

                //Si los campos están vacíos, mostrar un mensaje de error
                if (nombre.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
                    Toast.makeText(PantallaCalendario.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                } else {
                    //Agregar y guardar el recordatorio
                    agregarRecordatorio(nombre, fecha, hora);
                    guardarRecordatorioEnSharedPreferences(nombre + " - " + fecha + " - " + hora);
                    contadorRecordatorios++;
                    Toast.makeText(PantallaCalendario.this, "Recordatorio añadido correctamente.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        });

        // Mostrar el diálogo
        dialog.show();
        //Configuración de los campos de fecha y hora
        fechaInput.setOnClickListener(v -> showDatePickerDialog(fechaInput));
        horaInput.setOnClickListener(v -> showTimePickerDialog(horaInput));
    }

    //Método para agregar un recordatorio a la vista
    private void agregarRecordatorio(String nombre, String fecha, String hora) {

        //Crear un TextView para mostrar el recordatorio
        String recordatorioTexto = nombre + " - " + fecha + " - " + hora;
        TextView textViewRecordatorio = new TextView(this);
        textViewRecordatorio.setText(recordatorioTexto);
        textViewRecordatorio.setTypeface(null, Typeface.BOLD);
        textViewRecordatorio.setTextColor(getResources().getColor(android.R.color.black));
        textViewRecordatorio.setTextSize(20);

        //Crear un LinearLayout para el recordatorio
        LinearLayout layoutRecordatorio = new LinearLayout(this);
        layoutRecordatorio.setOrientation(LinearLayout.VERTICAL);

        //Configurar el LinearLayout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(90, 12, 90, 12);
        layoutRecordatorio.setLayoutParams(layoutParams);
        layoutRecordatorio.setPadding(16, 8, 16, 8);
        layoutRecordatorio.setBackgroundResource(R.drawable.recordatorio_border);

        //Agregar el TextView al LinearLayout
        layoutRecordatorio.addView(textViewRecordatorio);

        //Configurar el LinearLayout para eliminar el recordatorio
        layoutRecordatorio.setOnClickListener(v -> {mostrarDialogoEliminar(layoutRecordatorio, recordatorioTexto);});

        //Agregar el LinearLayout al LinearLayout principal
        linearLayoutRecordatorios.addView(layoutRecordatorio);
    }

    //Método para cargar los recordatorios guardados en SharedPreferences
    private void cargarRecordatoriosGuardados() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        Set<String> recordatorios = sharedPreferences.getStringSet(RECORDATORIOS_KEY, new HashSet<>());

        //Crear una copia del conjunto recuperado
        Set<String> copiaRecordatorios = new HashSet<>(recordatorios);

        //Limpiar la vista antes de cargar los recordatorios
        linearLayoutRecordatorios.removeAllViews();
        contadorRecordatorios = 0;

        //Agregar cada recordatorio a la vista
        for (String recordatorio : copiaRecordatorios) {
            String[] partes = recordatorio.split(" - ");
            agregarRecordatorio(partes[0], partes[1], partes[2]);
            contadorRecordatorios++;
        }
    }

    //Eliminar un recordatorio de la vista y de SharedPreferences
    private void mostrarDialogoEliminar(LinearLayout recordatorio, String textoRecordatorio) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Recordatorio");
        builder.setMessage("¿Estás seguro de que quieres eliminar este recordatorio?");
        //Función lambda para eliminar el recordatorio cuando se hace clic en el botón "Eliminar"
        builder.setPositiveButton("Eliminar", (dialog, which) -> {
            linearLayoutRecordatorios.removeView(recordatorio);
            contadorRecordatorios--;
            eliminarRecordatorioDeSharedPreferences(textoRecordatorio);
            Toast.makeText(this, "Recordatorio eliminado", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    //Método para guardar un recordatorio en SharedPreferences
    private void guardarRecordatorioEnSharedPreferences(String recordatorio) {
        //Crear un conjunto
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        //Recuperar el conjunto existente
        Set<String> recordatorios = sharedPreferences.getStringSet(RECORDATORIOS_KEY, new HashSet<>());
        //Crear una copia del conjunto
        Set<String> copiaRecordatorios = new HashSet<>(recordatorios);
        //Agregar el nuevo recordatorio
        copiaRecordatorios.add(recordatorio);
        //Guardar el conjunto actualizado
        sharedPreferences.edit().putStringSet(RECORDATORIOS_KEY, copiaRecordatorios).apply();
    }

    //Método para eliminar un recordatorio de SharedPreferences
    private void eliminarRecordatorioDeSharedPreferences(String recordatorio) {
        //Crear un conjunto
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        //Recuperar el conjunto existente
        Set<String> recordatorios = sharedPreferences.getStringSet(RECORDATORIOS_KEY, new HashSet<>());
        //Crear una copia del conjunto
        Set<String> copiaRecordatorios = new HashSet<>(recordatorios);
        //Eliminar el recordatorio
        copiaRecordatorios.remove(recordatorio);
        //Guardar el conjunto actualizado
        sharedPreferences.edit().putStringSet(RECORDATORIOS_KEY, copiaRecordatorios).apply();
    }

    //Método para mostrar DatePickerDialog y restringir la selección a fechas futuras
    private void showDatePickerDialog(EditText fechaInput) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //Crear el DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, monthOfYear, dayOfMonth) -> {
            String fechaSeleccionada = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
            fechaInput.setText(fechaSeleccionada);
        }, year, month, day);
        //Configurar la fecha mínima como la fecha actual para evitar la selección de fechas pasadas
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    //Método para mostrar TimePickerDialog
    private void showTimePickerDialog(EditText horaInput) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //Crear el TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute1) -> {
            String horaSeleccionada = hourOfDay + ":" + String.format("%02d", minute1);
            horaInput.setText(horaSeleccionada);
        }, hour, minute, true);
        timePickerDialog.show();
    }
}