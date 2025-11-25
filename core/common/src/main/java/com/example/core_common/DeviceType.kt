package com.example.core_common

import android.content.Context

object DeviceType
{
    fun isTV(context: Context):Boolean
    {
        return context.packageManager.hasSystemFeature("android.software.leanback")
    }
}