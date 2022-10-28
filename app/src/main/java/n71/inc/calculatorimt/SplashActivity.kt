package n71.inc.calculatorimt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.paperdb.Paper

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Paper.init(this)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}