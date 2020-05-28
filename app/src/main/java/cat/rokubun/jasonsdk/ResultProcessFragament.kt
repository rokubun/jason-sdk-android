package cat.rokubun.jasonsdk

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

/**
 * Show some Process results
 */
class ResultProcessFragament : Fragment() {

    @BindView(R.id.sppCsvUrlTextView)
    lateinit var sppCsvUrl: TextView
    @BindView(R.id.sppKmlUrlTextView)
    lateinit var sppKmlUrl: TextView
    @BindView(R.id.processTypeTextView)
    lateinit var processType: TextView
    @BindView(R.id.skyplotUrlTextView)
    lateinit var skyPlotUrl: TextView

    val args: ResultProcessFragamentArgs by navArgs()


    private val processViewModel: ProcessViewModel by lazy {
        ViewModelProvider(this).get(ProcessViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result_process, container, false)
        ButterKnife.bind(this, view)

        return view
    }

    override fun onStart() {
        super.onStart()
        setResultsTextView()

    }

    fun setResultsTextView(){

        processViewModel.getProcessStatus(args.idProcess, 1000)
        processViewModel.processResultLivedata.observe(viewLifecycleOwner, Observer {
            sppCsvUrl.text = it.processResult!!.getSppCsvUrl()
            sppKmlUrl.text = it.processResult!!.getSppKmlUrl()
            processType.text = it.processResult!!.getProcessingType()
            skyPlotUrl.text = it.processResult!!.getSkyPlotUrl()
        })

    }

    @OnClick(R.id.retryButton)
    fun onRetryButtonClick() {
        processViewModel.retryProcess(Integer.parseInt(args.idProcess))
        processViewModel.retryLiveData.observe(viewLifecycleOwner, Observer {
            if (it.message.equals("success")) {
                Toast.makeText(context, "Retry succesful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
