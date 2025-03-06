package com.okproject.flowless

import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.okproject.flowless.editor.NoteEditorScreen
import com.okproject.flowless.role.RoleViewModel
import com.okproject.flowless.ui.theme.FlowlessTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    private val roleViewModel: RoleViewModel by viewModel()

    private val intentLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            roleViewModel.onRoleRequested()
        }

    private val roleManager: RoleManager by lazy {
        getSystemService(Context.ROLE_SERVICE) as RoleManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotesRole()
        enableEdgeToEdge()
        setContent {
            KoinContext {
                FlowlessTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPaddings ->
                        NoteEditorScreen(
                            modifier = Modifier
                                .padding(top = innerPaddings.calculateTopPadding())
                        )
                    }
                }
            }
        }
    }

    private fun requestNotesRole() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            requestRole(RoleManager.ROLE_NOTES)
        }
    }

    private fun requestRole(role: String) {
        if (hasToRequestRole(role)) {
            intentLauncher.launch(
                roleManager.createRequestRoleIntent(role)
            )
        }
    }

    private fun hasToRequestRole(role: String): Boolean =
        !roleViewModel.isRoleRequested.value
                && roleManager.isRoleAvailable(role)
                && !roleManager.isRoleHeld(role)
}