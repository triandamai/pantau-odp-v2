package com.trian.data.models.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MonitoringResponse (
    @SerializedName("ODP")
    val odp: String,
    @SerializedName("ODP-DALAMPEMANTAUAN")
    val odpDalamPemantauan: String,
    @SerializedName("ODP-SELESAIPEMANTAUAN")
    val odpSelesaiPemantauan: String,
    @SerializedName("PDP")
    val pdp: String,
    @SerializedName("PDP-HASILLABNEGATIF")
    val pdpHasilLabNegatif: String,
    @SerializedName("PDP-MENUNGGUHASILLAB")
    val pdpMenungguHasilLab: String,
    @SerializedName("PDP-MENINGGALDUNIA")
    val pdpMeninggalDunia: String,
    @SerializedName("POSITIFCORONA")
    val positifCorona: String,
    @SerializedName("POSITIF-DIRAWAT")
    val positifDirawat: String,
    @SerializedName("POSITIF-SEMBUH")
    val positifSembuh: String,
    @SerializedName("POSITIF-MENINGGAL")
    val positifMeninggal: String,
    @SerializedName("LASTUPDATE")
    val lastupdate: String
)
