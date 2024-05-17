package ar.edu.ejercicio4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.PI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextConversionType: EditText = findViewById(R.id.editTextConversionType)
        val editTextAngle: EditText = findViewById(R.id.editTextAngle)
        val buttonConvert: Button = findViewById(R.id.buttonConvert)
        val textViewResult: TextView = findViewById(R.id.textViewResult)

        // Botón para manejar la conversión :)
        buttonConvert.setOnClickListener {
            val conversionType = editTextConversionType.text.toString().trim().uppercase()
            val angleInput = editTextAngle.text.toString().trim()

            if (angleInput.isEmpty()) {
                textViewResult.text = "Por favor, ingrese un ángulo."
                return@setOnClickListener
            }

            val angle = angleInput.toDoubleOrNull()
            if (angle == null) {
                textViewResult.text = "Por favor, ingrese un valor numérico válido."
                return@setOnClickListener
            }

            val result = when (conversionType) {
                "GAR" -> convertDegreesToGradians(angle)
                "RAG" -> convertRadiansToDegrees(angle)
                else -> {
                    "Tipo de conversión no válida. Use GAR o RAG."
                }
            }

            textViewResult.text = result.toString()
        }
    }

    private fun convertDegreesToGradians(degrees: Double): Double {
        return degrees * (PI / 180.0)
    }

    private fun convertRadiansToDegrees(radians: Double): Double {
        return radians * (180.0 / PI)
    }
}
