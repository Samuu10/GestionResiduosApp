package com.example.gestionresiduosapp.ui.Grafica;

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
    private ArrayList<Pair<String, Integer>> recyclingData = new ArrayList<>();
    private Paint barPaint;
    private Paint textPaint;
    private int[] barColors = {
            Color.GRAY, Color.LTGRAY, Color.BLUE, Color.DKGRAY, Color.BLACK, Color.CYAN
    };

    public Grafica(Context context) {
        super(context);
        init();
    }

    public Grafica(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        barPaint = new Paint();
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30); // Tamaño de texto reducido
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setRecyclingData(ArrayList<Pair<String, Integer>> data) {
        recyclingData = data;
        invalidate();
    }

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

        // Ajustar altura del gráfico para dejar espacio para la leyenda y el margen superior
        float graphHeight = getHeight() - 150;
        float yScale = graphHeight / maxValue;

        // Dibujar cuadrícula
        drawGrid(canvas, graphHeight, yScale);

        // Dibujar barras y etiquetas de semana
        for (int i = 0; i < barCount; i++) {
            Pair<String, Integer> data = recyclingData.get(i);
            float barHeight = data.second * yScale;
            float x = barWidth * (i * 2 + 1);

            barPaint.setColor(barColors[i % barColors.length]);
            canvas.drawRect(x, graphHeight - barHeight, x + barWidth - barSpacing, graphHeight, barPaint);

            canvas.drawText(data.first, x + barWidth / 2 - barSpacing / 2, graphHeight + 40, textPaint);
        }

        // Dibujar leyenda
        drawLegend(canvas, graphHeight);
    }

    // Método para dibujar la cuadrícula
    private void drawGrid(Canvas canvas, float graphHeight, float yScale) {
        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.LTGRAY);
        gridPaint.setStrokeWidth(2);

        // Líneas horizontales
        for (int i = 0; i <= 10; i++) {
            float y = graphHeight - i * (graphHeight / 10);
            canvas.drawLine(0, y, getWidth(), y, gridPaint);

            // Etiquetas del eje Y
            if (i % 2 == 0) { // Mostrar etiquetas cada 2 líneas
                int value = (int) (i * (getMaxValue() / 10));
                canvas.drawText(String.valueOf(value), 10, y - 10, textPaint);
            }
        }

        // Líneas verticales (opcional)
        // Puedes añadir líneas verticales si lo deseas
    }

    // Método para dibujar la leyenda si es necesario
    private void drawLegend(Canvas canvas, float graphHeight) {
        // No hacer nada
    }

    private float getMaxValue() {
        float maxValue = 0;
        for (Pair<String, Integer> pair : recyclingData) {
            if (pair.second > maxValue) {
                maxValue = pair.second;
            }
        }
        return maxValue;
    }
}
