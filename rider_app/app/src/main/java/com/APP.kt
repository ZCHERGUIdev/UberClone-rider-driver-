package com

import android.app.Application
import com.parse.Parse
import com.parse.ParseACL
import com.zcbilarabi.wasalni.BuildConfig

class APP:Application(){
    override fun onCreate() {
        super.onCreate()
        //id:95ZPtq6K2hGJQtodSl2kwRoZ3o0helaL6rfcaLwm
        //key: MHq0HQRkzwZQ6Pda3st10qeBMpgRZ2BoaEtXzkoK

        Parse.enableLocalDatastore(this)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("95ZPtq6K2hGJQtodSl2kwRoZ3o0helaL6rfcaLwm")
                .clientKey("MHq0HQRkzwZQ6Pda3st10qeBMpgRZ2BoaEtXzkoK")
                .server("https://parseapi.back4app.com")
                .build()
        )
        var parseAcl= ParseACL()
        parseAcl.publicWriteAccess=true
        parseAcl.publicReadAccess=true
        ParseACL.setDefaultACL(parseAcl,true)




    }
}