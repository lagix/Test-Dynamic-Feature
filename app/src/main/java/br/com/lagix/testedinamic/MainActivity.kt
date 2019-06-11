package br.com.lagix.testedinamic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splitManager = SplitInstallManagerFactory.create(this)
        val linea = findViewById<LinearLayout>(R.id.linear)

        findViewById<Button>(R.id.btn_feat2).setOnClickListener {
            if (splitManager.installedModules.contains("dynamic_feature2")) {
                Toast.makeText(applicationContext,"Módulo Pronto",Toast.LENGTH_SHORT).show()

                var frag = Class.forName("br.com.lagix.dynamic_feature2.feat2_principal").newInstance() as Fragment

                supportFragmentManager.beginTransaction()
                    .disallowAddToBackStack()
                    .replace(R.id.linear,frag)
                    .commit()
            } else {

                val request = SplitInstallRequest.newBuilder()
                    .addModule("dynamic_feature2")
                    .build()


                val listener = SplitInstallStateUpdatedListener {
                    when (it.status()) {
                        SplitInstallSessionStatus.CANCELED -> Toast.makeText(
                            applicationContext,
                            "Cancelado",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.CANCELING -> Toast.makeText(
                            applicationContext,
                            "Cancelando",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.DOWNLOADED -> Toast.makeText(
                            applicationContext,
                            "Download Concluido",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.DOWNLOADING -> Toast.makeText(
                            applicationContext,
                            "Downloading",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.FAILED -> Toast.makeText(
                            applicationContext,
                            "Falha",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.INSTALLED -> Toast.makeText(
                            applicationContext,
                            "Instalado",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.INSTALLING -> Toast.makeText(
                            applicationContext,
                            "Instalando",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.PENDING -> Toast.makeText(
                            applicationContext,
                            "Pendente",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> Toast.makeText(
                            applicationContext,
                            "Confirmação do usuario",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.UNKNOWN -> Toast.makeText(
                            applicationContext,
                            "Desconhecido",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                splitManager.registerListener(listener)

                splitManager.startInstall(request)
            }
        }

        findViewById<Button>(R.id.btn_remove_feat1).setOnClickListener {
            if (splitManager.installedModules.contains("dynamic_feature1")) {
                splitManager.deferredUninstall(listOf("dynamic_feature1"))
                Toast.makeText(applicationContext,"FEAT 1 removido",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext,"Módulo inexistente",Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btn_feat1).setOnClickListener {
            if (splitManager.installedModules.contains("dynamic_feature1")) {
                Toast.makeText(applicationContext,"Módulo Pronto",Toast.LENGTH_SHORT).show()

                startActivity(Intent(this@MainActivity,Class.forName("br.com.lagix.dynamic_feature1.Feat1Main")))
            } else {

                val request = SplitInstallRequest.newBuilder()
                    .addModule("dynamic_feature1")
                    .build()


                val listener = SplitInstallStateUpdatedListener {
                    when (it.status()) {
                        SplitInstallSessionStatus.CANCELED -> Toast.makeText(
                            applicationContext,
                            "Cancelado",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.CANCELING -> Toast.makeText(
                            applicationContext,
                            "Cancelando",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.DOWNLOADED -> Toast.makeText(
                            applicationContext,
                            "Download Concluido",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.DOWNLOADING -> Toast.makeText(
                            applicationContext,
                            "Downloading",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.FAILED -> Toast.makeText(
                            applicationContext,
                            "Falha",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.INSTALLED -> Toast.makeText(
                            applicationContext,
                            "Instalado",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.INSTALLING -> Toast.makeText(
                            applicationContext,
                            "Instalando",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.PENDING -> Toast.makeText(
                            applicationContext,
                            "Pendente",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> Toast.makeText(
                            applicationContext,
                            "Confirmação do usuario",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallSessionStatus.UNKNOWN -> Toast.makeText(
                            applicationContext,
                            "Desconhecido",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                splitManager.registerListener(listener)

                splitManager.startInstall(request)
            }
        }
    }
}
