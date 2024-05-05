package com.example.hx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment(){

    lateinit var logAdapter: LogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logAdapter = LogAdapter(mutableListOf(), requireContext())
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.test_main_container, container, false)
    }

    fun tLogToLogEntry(tLog: TLog): LogEntry {
        return LogEntry(tLog.id,tLog.time, tLog.event)
    }



    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = logAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.addLogFragment)
        }
        val db = Room.databaseBuilder(requireContext(), TLogDatabase::class.java, "events_log")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        val tLogDao = db.TLogDao()

        GlobalScope.launch { 
            val allLogs = tLogDao.queryAllLogs()
            val logEntries = allLogs.map { tLogToLogEntry(it) }
            withContext(Dispatchers.Main) { // 切换到主线程更新UI
                (recyclerView.adapter as? LogAdapter)?.updateLog(logEntries)
            }
        }


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val logId = (recyclerView.adapter as? LogAdapter)?.getItemId(position) // 获取被滑动条目的ID
                AlertDialog.Builder(requireContext())
                    .setMessage("确定要删除这条事件吗？")
                    .setPositiveButton("确定") { _, _ ->
                        tLogDao.deleteLog(logId)
                        val updatedLogs = tLogDao.queryAllLogs() // 重新查询数据
                        (recyclerView.adapter as? LogAdapter)?.updateLog(updatedLogs.map { tLogToLogEntry(it) }) // 更新界面

                    }
                    .setNegativeButton("取消") { _, _ ->
                        logAdapter.notifyItemChanged(position) // 点击取消恢复原状
                    }
                    .setOnCancelListener {
                        logAdapter.notifyItemChanged(position) // 点击空白区域恢复原状
                    }
                    .show()
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
