package com.usyssoft.amazontranslatekotlin

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.translate.AmazonTranslateClient
import com.amazonaws.services.translate.model.TranslateTextRequest
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main("Makale örneği arayanlar için birbirinden güzel uzun ve kısa makale örneklerini bir araya getirdik. 7 farklı makaleyi aşağıda başlıklar halinde sizlere sunuyoruz. Okumanın önemi, sınav stresi, bilgisayarın zararları, televizyonun zararları ve diğer başlıklar altında makale örnekleri hazırladık.")


    }

    fun main(vararg params: String) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val result = doLongRunningTask(params)
            if (result != null) {
                println("Çevrilen metin: $result")
            }

        }
        executor.shutdown()
    }

    fun doLongRunningTask(params: Array<out String>): String? {

        try {
            val accessKey = "accessKey"
            val secretKey = "SecretKey"
            val region = Regions.EU_CENTRAL_1

            val credentials = BasicAWSCredentials(accessKey, secretKey)
            val translateClient = AmazonTranslateClient(credentials)
            translateClient.setRegion(Region.getRegion(region))

            val textToTranslate = params[0]

            val sourceLanguage = "tr"
            val targetLanguage = "en"

            val request = TranslateTextRequest()
                .withText(textToTranslate)
                .withSourceLanguageCode(sourceLanguage)
                .withTargetLanguageCode(targetLanguage)

            val result = translateClient.translateText(request)
            return result.translatedText
        } catch (e: Exception) {
            println("Hata: ${e.localizedMessage}")
            return null
        }

    }

}


