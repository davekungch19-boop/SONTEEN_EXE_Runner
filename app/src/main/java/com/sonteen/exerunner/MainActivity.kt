package com.sonteen.exerunner

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var selectedExe: Uri? = null

    private val picker = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            selectedExe = uri
            contentResolver.takePersistableUriPermission(
                uri,
                android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            findViewById<TextView>(R.id.fileName).text =
                uri.lastPathSegment ?: uri.toString()
            findViewById<TextView>(R.id.status).text =
                "เลือกไฟล์แล้ว — กด RUN"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.selectButton).setOnClickListener {
            picker.launch(arrayOf("*/*"))
        }

        findViewById<Button>(R.id.runButton).setOnClickListener {
            val uri = selectedExe
            if (uri == null) {
                Toast.makeText(this, "กรุณาเลือกไฟล์ EXE ก่อน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            findViewById<TextView>(R.id.status).text =
                "ส่งไฟล์เข้า EXE runtime: $uri"
            ExeRuntime.launch(this, uri)
        }
    }
}
