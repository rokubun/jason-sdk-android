package cat.rokubun.jasonsdk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import cat.rokubun.jasonsdk.adapter.ProcessAdapter
import kotlin.collections.ArrayList
import androidx.navigation.fragment.navArgs as navArgs

/**
 * A simple [Fragment] subclass.
 */
class LogFrament : Fragment() {

    @BindView(R.id.outputRecyclerView)
    lateinit var recyclerView: RecyclerView
    @BindView(R.id.resultsButton)
    lateinit var resultButton: Button

    private var processAdapter: ProcessAdapter? =null
    val args: LogFramentArgs by navArgs()

    private val processViewModel: ProcessViewModel by lazy {
        ViewModelProvider(this).get(ProcessViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_log_process, container, false)
        ButterKnife.bind(this, view)
        setupRecyclerView()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        processViewModel.getProcessStatus(args.idProcess, 60000)
        processViewModel.processResultLivedata.observe(viewLifecycleOwner, Observer {
            if(it.processLog!!.size > 0){
                processAdapter!!.addItems(it.processLog!!)
                recyclerView.scrollToPosition(it.processLog!!.size -1)
            }
        })
    }

    private fun setupRecyclerView() {

        recyclerView.layoutManager = LinearLayoutManager(context)
        processAdapter = ProcessAdapter(ArrayList())
        recyclerView.adapter = processAdapter


    }
    @OnClick(R.id.resultsButton)
    fun onResultClick(){
        processViewModel.processResultLivedata.observe(viewLifecycleOwner, Observer {
            if(!it.processResult!!.listResultsResponse.isEmpty()){
                val action = LogFramentDirections.actionLogFramentToResultProcessFragament(args.idProcess!!)
                Navigation.findNavController(this.view!!).navigate(action)
            }else{
                resultButton.isEnabled = false
            }
        })
    }

}
