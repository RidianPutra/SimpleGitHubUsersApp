package com.ridianputra.core.di

import androidx.room.Room
import com.ridianputra.core.data.GitHubUsersRepository
import com.ridianputra.core.data.source.local.LocalDataSource
import com.ridianputra.core.data.source.local.room.GitHubUsersDatabase
import com.ridianputra.core.data.source.remote.RemoteDataSource
import com.ridianputra.core.data.source.remote.network.ApiService
import com.ridianputra.core.domain.repository.IGitHubUsersRepository
import com.ridianputra.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<GitHubUsersDatabase>().gitHubUsersDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("ridianputra".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            GitHubUsersDatabase::class.java, "GitHubUsers.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.github.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/1UPHAdcUbUoOcd5rDTD/0oMSnngCU6YzXzpByO4CCp4=")
            .add(hostname, "sha256/RQeZkB42znUfsDIIFWIRiYEcKl7nHwNFwWCrnMMJbVc=")
            .add(hostname, "sha256/Jg78dOE+fydIGk19swWwiypUSR6HWZybfnJG/8G7pyM=")
            .add(hostname, "sha256/e0IRz5Tio3GA1Xs4fUVWmH1xHDiH2dMbVtCBSkOIdqM=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGitHubUsersRepository> {
        GitHubUsersRepository(
            get(),
            get(),
            get()
        )
    }
}
