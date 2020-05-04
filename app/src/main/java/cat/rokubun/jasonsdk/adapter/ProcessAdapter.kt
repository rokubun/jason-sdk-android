package cat.rokubun.jasonsdk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import cat.rokubun.jason.ProcessLog
import cat.rokubun.jasonsdk.R

/**
 * Adapter for the process logs
 */
class ProcessAdapter(private val processLog: ArrayList<ProcessLog>): RecyclerView.Adapter<ProcessAdapter.ProcessLogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProcessLogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.process_log, parent, false)

        return ProcessLogViewHolder(view)
    }

    override fun getItemCount() = processLog.size


    override fun onBindViewHolder(holder: ProcessLogViewHolder, position: Int) {
        holder.messageTextView.text = processLog[position].message
    }

    fun addItems(processLogList: List<ProcessLog>) {
        processLog.clear()
        processLog.addAll(processLogList)
        notifyDataSetChanged()
    }
    fun addItem(processLog: ProcessLog){
        this.processLog.add(processLog)
        notifyDataSetChanged()
    }


    class ProcessLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.messageTextView)
        lateinit var messageTextView: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}



