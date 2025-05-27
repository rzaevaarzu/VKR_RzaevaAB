package com.example.vkr_rzaevaab

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.vkr_rzaevaab.api.Api
import com.example.vkr_rzaevaab.api.viewmodels.UserViewModel
import com.example.vkr_rzaevaab.app.App
import com.example.vkr_rzaevaab.databinding.FragmentAuthorizationBinding
import com.example.vkr_rzaevaab.entities.LoginDto
import com.example.vkr_rzaevaab.entities.User
import com.example.vkr_rzaevaab.sharedpreferences.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AuthorizationFragment : Fragment() {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding: FragmentAuthorizationBinding get() = _binding!!
    val projectApi = App.retrofit.create(Api::class.java)
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)


        val context = requireContext()
        var sharedPreference = SharedPref(context)
        var edt_email = binding.editEmail
        var edt_password = binding.editPassword


        binding.btnLogin.setOnClickListener {
            val str_email_id = edt_email.text.toString()
            val str_password = edt_password.text.toString()

            if(str_email_id.equals("") || str_password.equals("")){
                Toast.makeText(context,"Пожалуйста, введите почту и пароль", Toast.LENGTH_SHORT).show()
            }else {
                lifecycleScope.launch(Dispatchers.IO){
//                    Log.w("test", logUser.toString())
                    val logUser = projectApi.login(LoginDto(str_email_id, str_password))
                    val userExist: Boolean = (logUser != null)
                    if (!logUser.id!!.equals(0)) {
                        withContext(Dispatchers.Main){
                            Log.w("tag_log", logUser.fio)
                            sharedPreference.saveUser(logUser!!.id.toString(), logUser!!.fio, logUser!!.birth_date, logUser!!.telephone, logUser.email, logUser.is_admin, "login_status","1")
//                            sharedPreference.saveUser(logUser!!.id.toString(), logUser!!.fio, logUser!!.birthDate, logUser!!.telephone, logUser.email, "login_status","1")
                            sharedPreference.sharedUser = logUser

////////////////////////////////////////////////////////////////////

                            val bundle = Bundle()
                            val control = findNavController()
//                            control.navigate(R.id.action_authorizationFragment_to_homeFragment)
                            val nav = arguments?.getString("nav")
                            if (nav == "toDeviceList"){

                                control.navigate(R.id.action_authorizationFragment_to_deviceListFragment)
                            }
                            else if(nav == "toEventList"){
                                control.navigate(R.id.action_authorizationFragment_to_eventListFragment)
                            }

                            else if(nav == "toHome"){
                                control.navigate(R.id.action_authorizationFragment_to_homeFragment)
                            }

                            else if(nav == "toProfile"){
                                control.navigate(R.id.action_authorizationFragment_to_profileFragment)
                            }

                        }
//////////////////////////////////////////////////////////////////
                    } else {
                        withContext(Dispatchers.Main){
                            Toast.makeText(context,"Введите корректные логин и пароль", Toast.LENGTH_SHORT).show()
                        }

                    }
                }

            }
        }


        binding.btnSignUp.setOnClickListener {
            fun_SignUp_PopupWindow()
        }

        return binding.root
    }


    private fun fun_SignUp_PopupWindow() {

        val layoutInflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popView: View = layoutInflater.inflate(R.layout.sign_up_window, null)
        val popupWindow: PopupWindow
        popupWindow = PopupWindow(popView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT,true)
        popupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0)

        val btn_dia_submit = popView.findViewById(R.id.btn_dia_submit) as Button
        btn_dia_submit.setOnClickListener {
            val str_dia_email_id = popView.findViewById<EditText>(R.id.edt_dia_email_id).text.toString()
            val str_dia_password = popView.findViewById<EditText>(R.id.edt_dia_password).text.toString()
            val str_dia_fio = popView.findViewById<EditText>(R.id.edt_dia_fio).text.toString()
            val str_dia_birthdate = popView.findViewById<EditText>(R.id.edt_dia_birthdate).text.toString()
            val str_dia_telephone = popView.findViewById<EditText>(R.id.edt_dia_telephone).text.toString()

            if(str_dia_email_id.equals("") || str_dia_password.equals("") ||str_dia_birthdate.equals("")  || str_dia_fio.equals("") || str_dia_telephone.equals("")){
                Toast.makeText(requireContext(),"Пожалуйста, введите данные", Toast.LENGTH_SHORT).show()
            }else {
                lifecycleScope.launch(Dispatchers.IO) {
                    val checkEmail = projectApi.checkEmail(str_dia_email_id)
                    if (checkEmail.message.equals("true")){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(),
                                "Пользователь с такой почтой уже существует",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        val newUser = User(
                            null,
                            str_dia_fio,
                            str_dia_birthdate,
                            str_dia_telephone,
                            str_dia_email_id,
                            str_dia_password,
                            false
                        )

                        userViewModel.createUser(newUser)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(),
                                "Ваш аккаунт успешно создан",
                                Toast.LENGTH_SHORT
                            ).show()
                            popupWindow.dismiss()
                        }


                    }
                }

            }
        }
    }

}