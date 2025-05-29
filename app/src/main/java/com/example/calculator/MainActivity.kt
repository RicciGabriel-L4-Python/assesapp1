package com.example.calculator

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var translateButton: Button
    private lateinit var infoButton: ImageButton

    private val interpretations = mapOf(
        "Mirror" to "reflecting on your true self",
        "Ocean" to "overwhelmed by deep emotions",
        "Stairs" to "ascending toward a higher self",
        "Fire" to "burning with change",
        "Bird" to "longing for freedom",
        "Door" to "ready for opportunity",
        "Fog" to "walking through uncertainty",
        "Clock" to "troubled by time",
        "Tree" to "rooted and growing",
        "Moon" to "in tune with intuition"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
        translateButton = findViewById(R.id.translateButton)
        infoButton = findViewById(R.id.infoButton)

        val selectedSymbols = mutableListOf<String>()

        val symbols = listOf(
            R.id.button_mirror to "Mirror",
            R.id.button_ocean to "Ocean",
            R.id.button_stairs to "Stairs",
            R.id.button_fire to "Fire",
            R.id.button_bird to "Bird",
            R.id.button_door to "Door",
            R.id.button_fog to "Fog",
            R.id.button_clock to "Clock",
            R.id.button_tree to "Tree",
            R.id.button_moon to "Moon"
        )

        symbols.forEach { (id, name) ->
            findViewById<Button>(id).setOnClickListener {
                if (selectedSymbols.contains(name)) {
                    selectedSymbols.remove(name)
                    it.alpha = 1.0f
                } else {
                    if (selectedSymbols.size < 3) {
                        selectedSymbols.add(name)
                        it.alpha = 0.5f
                    } else {
                        Toast.makeText(this, "You can select up to 3 symbols.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        translateButton.setOnClickListener {
            if (selectedSymbols.isEmpty()) {
                Toast.makeText(this, "Please select at least one symbol.", Toast.LENGTH_SHORT).show()
            } else {
                val interpretation = selectedSymbols.joinToString(", ") {
                    interpretations[it] ?: it
                }
                resultTextView.text = interpretation.replaceFirstChar { it.uppercase() } + "."
            }
        }

        infoButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("What is Dream Translator?")
                .setMessage("Select up to 3 dream symbols to receive a creative interpretation. Just for fun!")
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
