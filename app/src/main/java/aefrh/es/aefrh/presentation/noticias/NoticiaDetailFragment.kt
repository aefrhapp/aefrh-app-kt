package aefrh.es.aefrh.presentation.noticias

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FragmentNoticiaDetailBinding
import aefrh.es.aefrh.domain.RssFeedSingle
import aefrh.es.aefrh.domain.Status
import aefrh.es.aefrh.presentation.base.BaseFragment
import aefrh.es.aefrh.utils.Result
import aefrh.es.aefrh.utils.reformatNoticia
import android.annotation.SuppressLint
import android.net.http.SslError
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_noticia_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class NoticiaDetailFragment: BaseFragment<FragmentNoticiaDetailBinding, NoticiasViewModel>() {

    override val viewModel: NoticiasViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_noticia_detail
    private val args: NoticiaDetailFragmentArgs by navArgs()

    override fun init(view: View) {
        initWebView()
        setWebClient()
        viewModel.onGetSingleNoticia(args.noticiaId)
        viewModel.noticia.observe(this, Observer { bindNoticia(it) })
    }

    private fun bindNoticia(result: Result<RssFeedSingle>) {
        when(result.status) {
            Status.LOADING -> {
                showProgress()
            }
            Status.ERROR -> {
                displayErrorInt(R.string.error2)
                Timber.e(result.message)
            }
            else -> {
                val res = result.data?.item
                val xmlHtml = res?.reformatNoticia()
                Timber.e(xmlHtml)
                xmlHtml?.let { loadUrl(it, res.link) }

//                val fin = res?.reformatNoticia()?.let { loadUrl(it, res.link) }
            }
        }
    }

    private fun loadUrl(contentXml: String, link: String) {
        wb_noticias.loadDataWithBaseURL(link, contentXml, "text/html", "UTF-8", "")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        wb_noticias.settings.javaScriptEnabled = true
        wb_noticias.settings.loadWithOverviewMode = true
        wb_noticias.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
    }

    private fun setWebClient() {
        wb_noticias.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {
                super.onProgressChanged(view, newProgress)
                if (newProgress < MAX_PROGRESS) {
                    showProgress()
                }
                if (newProgress == MAX_PROGRESS) {
                    hideProgress()
                }
            }
        }
    }

    companion object {
        const val MAX_PROGRESS = 100
    }

}
