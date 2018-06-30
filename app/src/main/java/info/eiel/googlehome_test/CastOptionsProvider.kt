package info.eiel.googlehome_test

import android.content.Context
import com.google.android.gms.cast.framework.CastOptions
import com.google.android.gms.cast.framework.SessionProvider
import com.google.android.gms.cast.framework.OptionsProvider


class CastOptionsProvider : OptionsProvider {
    override fun getCastOptions(context: Context): CastOptions =
        CastOptions.Builder()
            .setReceiverApplicationId(context.getString(R.string.app_id))
            .build()

    override fun getAdditionalSessionProviders(context: Context): List<SessionProvider>? = null
}
