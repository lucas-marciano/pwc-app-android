package com.lucasmarciano.pwc.view.login

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import org.koin.android.viewmodel.ext.android.viewModel
import com.lucasmarciano.pwc.R
import com.lucasmarciano.pwc.utils.Logger
import com.lucasmarciano.pwc.utils.ext.isEmailValid
import com.lucasmarciano.pwc.utils.ext.toast
import kotlinx.android.synthetic.main.login_fragment.*

/**
 * Login screen.
 *
 * @project pwc
 * @create_at 2019-11-19
 * @author lucasmarciano
 */
class LoginFragment : Fragment() {

    private val TAG = Logger.tag
    private val MY_PERMISSIONS_REQUEST = 1111
    private val viewModel: LoginViewModel by viewModel()

    private var checkEmail: Boolean = false
    private var checkPassword: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (Logger.DEBUG) Log.d(TAG, "onCreateView")

        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Logger.DEBUG) Log.d(TAG, "onViewCreated")

        checkOnFocusFields()
        setupActions()
        checkRunTimePermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (Logger.DEBUG) Log.d(TAG, "onRequestPermissionsResult")

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    viewModel.getCurrentLocation()
                } else {
                    activity?.toast(R.string.message_error_permission_location)
                }
                return

            }
            else -> if (Logger.DEBUG) Log.d(TAG, "onRequestPermissionsResult else")

        }
    }


    /**
     *  Method to insert a focus listener to check the fields.
     */
    private fun checkOnFocusFields() {
        if (Logger.DEBUG) Log.d(TAG, "checkOnFocusFields")

        et_email.setOnFocusChangeListener { _, changed ->
            if (!changed) {
                setupErrors()
            }
        }

        et_password.setOnFocusChangeListener { _, changed ->
            if (!changed) {
                setupErrors()
            }
        }
    }

    /**
     * Method to setup errors in the EditText.
     */
    private fun setupErrors() {
        if (Logger.DEBUG) Log.d(TAG, "setupErrors")

        checkEmail = if (et_email.text?.isEmpty()!!) {
            ti_email.error = resources.getString(R.string.message_error_required)
            false
        } else {
            if (et_email.text.toString().isEmailValid()) {
                ti_email.isErrorEnabled = false
                true
            } else {
                ti_email.error = resources.getString(R.string.message_error_email_format)
                false
            }
        }

        checkPassword = if (et_password.text?.isEmpty()!!) {
            ti_password.error = resources.getString(R.string.message_error_required)
            ti_password.isErrorEnabled = true
            false
        } else {
            ti_password.isErrorEnabled = false
            true
        }
    }

    /**
     * Method to insert a listener click in the views.
     */
    private fun setupActions() {
        if (Logger.DEBUG) Log.d(TAG, "setupActions")

        bt_login.setOnClickListener {
            setupErrors()

            if (checkEmail && checkPassword) {

                if (viewModel.isInternetAvailable()) {
                    if (viewModel.login(et_email.text?.toString(), et_password.text?.toString())) {
                        checkRunTimePermission()

                        pb_location.visibility = View.VISIBLE
                        bt_login.visibility = View.INVISIBLE
                        activity?.toast(R.string.message_welcome)

                        it.findNavController()
                            .navigate(R.id.action_loginFragment_to_questionsFragment)
                    } else {
                        activity?.toast(R.string.message_error_credentials)
                    }
                } else {
                    activity?.toast(R.string.message_error_connection)
                }
            } else {
                setupErrors()
            }
        }
    }

    /**
     * Method to check and request the run time permission.
     */
    private fun checkRunTimePermission() {
        if (Logger.DEBUG) Log.d(TAG, "checkRunTimePermission")

        activity?.let { act ->
            context?.let { ctx ->
                if (ContextCompat.checkSelfPermission(
                        ctx,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        act,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST
                    )
                } else {
                    viewModel.getCurrentLocation()
                }
            }
        }

    }

}
