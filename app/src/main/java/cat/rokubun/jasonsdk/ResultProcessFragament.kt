package cat.rokubun.jasonsdk

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import butterknife.BindView
import butterknife.ButterKnife

/**
 * A simple [Fragment] subclass.
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
            Log.d("spp",it.processResult!!.getSppCsvUrl())
            Log.d("spp",it.processResult!!.getSppKmlUrl())
            Log.d("type",it.processResult!!.getProcessingType())
            Log.d("sky",it.processResult!!.getSkyPlotUrl())

            sppCsvUrl.text = it.processResult!!.getSppCsvUrl()
            sppKmlUrl.text = it.processResult!!.getSppKmlUrl()
            processType.text = it.processResult!!.getProcessingType()
            skyPlotUrl.text = it.processResult!!.getSkyPlotUrl()
        })

    }
}
