package info.eiel.googlehome_test

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.support.v7.media.MediaRouter
import android.util.Log
import android.view.View
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.CastState


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupCastContext()
        val textToSpeech = makeTextToSpeeach()
        setupButton(textToSpeech)
    }

    private fun setupCastContext(): CastContext = CastContext.getSharedInstance(this).also {
        Log.v(localClassName, "initial cast state: ${CastState.toString(it.castState)}")
        it.addCastStateListener {
            Log.v(localClassName, "change cast state: ${CastState.toString(it)}")
        }
    }

    private fun makeTextToSpeeach(): TextToSpeech =
        TextToSpeech(this, {
            Log.v(localClassName, "setup TextToSpeech")
        })

    private fun selectGoogleHome(name: String) {
        val routes = MediaRouter.getInstance(this).routes
        val route = routes.find { it.description == name }
        route?.select() ?: run {
            Log.v(localClassName, "指定した名前のデバイスはなかったで。name: ${name}, routes: ${routes}")
        }
    }

    private fun setupButton(textToSpeech: TextToSpeech) {
        val button = findViewById<View>(R.id.button)
        button.setOnClickListener {
            Log.v(localClassName, "buttonClicked")
            selectGoogleHome("Dining room Speaker")
            textToSpeech.speak("hello", TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

}
