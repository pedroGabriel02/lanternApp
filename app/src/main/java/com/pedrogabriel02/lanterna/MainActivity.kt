package com.pedrogabriel02.lanterna

import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.pedrogabriel02.lanterna.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var estado = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.lanterna.setOnClickListener {
            if (!estado) {
                binding.lanterna.setImageResource(R.drawable.ic_lanterna_ligada)
                estado = true
                luzDaLanterna(estado)
            } else {
                binding.lanterna.setImageResource(R.drawable.ic_lanterna_desligada)
                estado = false
                luzDaLanterna(estado)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun luzDaLanterna(estado: Boolean){

        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        val cameraId: String?

        try {
            cameraId = cameraManager.cameraIdList[0] //Index 0 indica câmera traseira, 1 indica câmera frontal
            cameraManager.setTorchMode(cameraId,estado)
        }catch (e: Exception){

        }
    }
}