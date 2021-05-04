package br.ufpe.cin.android.doguinhoHero.utils

import java.lang.Math.cos
import java.lang.Math.sin
import java.lang.Math.acos

fun calcularDistancia(latitudeX: Double, longitudeX: Double, latitudeY: Double, longitudeY: Double) :Double {
    // Fórmula de Haversine aplicada em Kotlin

    val precisao = 3

    val lat1 = deg2rad(latitudeX)
    val lat2 = deg2rad(latitudeY)
    val lon1 = deg2rad(longitudeX)
    val lon2 = deg2rad(longitudeY)

    val dist = (6371 * acos( cos( lat1 ) * cos( lat2 ) * cos( lon2 - lon1 ) + sin( lat1 ) * sin(lat2) ) )

    return dist
}

private fun deg2rad(deg: Double): Double {
    return (deg * Math.PI / 180.0);
}
