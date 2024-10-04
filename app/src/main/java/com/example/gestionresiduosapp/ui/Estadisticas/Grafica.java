package com.example.gestionresiduosapp.ui.Estadisticas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class Grafica extends View {
    //Declaración de variables
    private ArrayList<Pair<String, Integer>> recyclingData = new ArrayList<>();
    private Paint barPaint;
    private Paint textPaint;
    private int[] barColors = {
            Color.parseColor("#689F38"), // Verde oscuro (orgánico)
            Color.parseColor("#00BCD4"), // Azul claro (papel y cartón)
            Color.parseColor("#009688"), // Verde (vidrio)
            Color.parseColor("#FFC107"), // Amarillo (envases y plásticos)
            Color.parseColor("#795548")  // Marrón (otros)
    };

    //Constructor
    public Grafica(Context context) {
        super(context);
        init();
    }

    //Constructor
    public Grafica(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //Método para inicializar las variables
    private void init() {
        barPaint = new Paint();
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30); // Tamaño de texto reducido
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    //Método para establecer los datos de reciclaje
    public void setRecyclingData(ArrayList<Pair<String, Integer>> data) {
        recyclingData = data;
        invalidate();
    }

    //Método para dibujar la gráfica
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (recyclingData == null || recyclingData.isEmpty()) {
            return;
        }

        int barCount = recyclingData.size();
        float barWidth = getWidth() / (barCount * 2f);
        float barSpacing = 20;
        float maxValue = getMaxValue();

        //Ajustar adicionales para la gráfica
        float graphHeight = getHeight() - 150;
        float yScale = graphHeight / maxValue;
        float margin = 30;

        //Dibujar cuadrícula
        drawGrid(canvas, graphHeight, yScale, margin);

        //Dibujar barras y etiquetas
        for (int i = 0; i < barCount; i++) {
            Pair<String, Integer> data = recyclingData.get(i);
            float barHeight = data.second * yScale;
            float x = barWidth * (i * 2 + 1);

            barPaint.setColor(barColors[i % barColors.length]);
            canvas.drawRect(x, graphHeight - barHeight, x + barWidth - barSpacing, graphHeight, barPaint);

            canvas.drawText(data.first, x + barWidth / 2 - barSpacing / 2, graphHeight + 40, textPaint);
        }
    }

    //Método para dibujar la cuadrícula
    private void drawGrid(Canvas canvas, float graphHeight, float yScale, float margin) {
        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.LTGRAY);
        gridPaint.setStrokeWidth(2);

        //Líneas horizontales con margen
        for (int i = 0; i <= 10; i++) {
            float y = graphHeight - i * (graphHeight / 10);
            canvas.drawLine(margin, y, getWidth() - margin, y, gridPaint);

            //Etiquetas del eje Y con margen
            if (i % 2 == 0) {
                int value = (int) (i * (90 / 10));
                canvas.drawText(String.valueOf(value), margin, y - 10, textPaint);
            }
        }
    }

    //Método para obtener el valor máximo de los datos
    private float getMaxValue() {
        float maxValue = 0;
        for (Pair<String, Integer> pair : recyclingData) {
            if (pair.second > maxValue) {
                maxValue = pair.second;
            }
        }
        return maxValue + 10;
    }
}