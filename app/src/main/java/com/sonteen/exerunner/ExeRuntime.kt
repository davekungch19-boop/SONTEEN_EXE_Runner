package com.sonteen.exerunner

import android.content.Context
import android.net.Uri
import android.widget.Toast

object ExeRuntime {

    fun launch(context: Context, exe: Uri) {
        // TODO: เชื่อม Wine + Box64/Box86 runtime ที่นี่
        Toast.makeText(
            context,
            "Runtime ยังไม่ได้ติดตั้ง: ต้องเชื่อม Wine + Box64/Box86 ก่อน",
            Toast.LENGTH_LONG
        ).show()
    }
}
