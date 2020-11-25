package marcinchrapowicz.application

import android.app.Application
import marcinchrapowicz.application.user.di.datasource_module
import marcinchrapowicz.application.user.di.repository_module
import marcinchrapowicz.application.user.di.usecase_module
import marcinchrapowicz.application.user.di.user_profile_module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication.applicationContext)
            modules(datasource_module + repository_module + usecase_module + user_profile_module)
        }
    }
}
