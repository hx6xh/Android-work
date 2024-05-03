package com.example.hx

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LogAdapter(private val logEntries: MutableList<LogEntry>,  private val context: Context) : RecyclerView.Adapter<LogAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textDate: TextView = itemView.findViewById(R.id.textDate)
        val textEvent: TextView = itemView.findViewById(R.id.textEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val logEntry = logEntries[position]
        holder.textDate.text = logEntry.date
        holder.textEvent.text = logEntry.event
    }

    override fun getItemCount(): Int {
        return logEntries.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateLog(newLogEntries: List<LogEntry>) {
        logEntries.clear()
        logEntries.addAll(newLogEntries)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addLog(logEntry: LogEntry) {
        logEntries.add(logEntry)
        logEntries.sortBy { it.date }
        notifyDataSetChanged()
    }

    //获取当前滑动项目在数据库中的ID
    override fun getItemId(position:Int): Long{
        val databaseManager = DatabaseManager(context)
        val removedEntry = logEntries[position]
        return removedEntry.id
    }
}