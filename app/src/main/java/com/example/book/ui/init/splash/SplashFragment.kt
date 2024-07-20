package com.example.book.ui.init.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.book.databinding.FragmentOnboardingBinding
import com.example.book.databinding.FragmentSplashBinding
import com.example.book.ui.account.AccountActivity
import com.example.book.ui.init.onboarding.OnboardingFragment
import com.example.book.ui.main.MainActivity
import com.example.book.utils.getPreferenceData
import com.github.gcacace.signaturepad.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDelay()
//        initWebView("https://gerenciamento5g.com.br/#/")
    }
//
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private fun initWebView(url: String){
//        val webview = binding.paymentView
//        webview?.webViewClient = WebViewClient()
//        webview?.webChromeClient = WebChromeClient()
//        webview?.settings?.javaScriptEnabled = true
//        webview?.settings?.domStorageEnabled = true  // Habilitar armazenamento local
//        webview?.loadUrl(url)
//
//        if (BuildConfig.DEBUG) {
//            WebView.setWebContentsDebuggingEnabled(true)
//        }
//
//    }

    private fun initDelay(){
        val t = Timer()
        var counter = 0
        val tt: TimerTask = object : TimerTask() {
            override fun run() {
                counter++
                if (counter == 10){
                    CoroutineScope(Dispatchers.Main).launch {
                        if(requireContext().getPreferenceData().checkLogin()){
                            startActivity(Intent(requireContext(), AccountActivity::class.java))
                        } else {
                            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnboardingFragment())
                        }
                    }
                    t.cancel()
                }
            }
        }
        t.schedule(tt, 0, 50)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}