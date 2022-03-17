package ayo.semangat.tahfidzv2.ui.chapter

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ayo.semangat.tahfidzv2.R
import ayo.semangat.tahfidzv2.utils.MyService
import ayo.semangat.tahfidzv2.utils.showLoading
import ayo.semangat.tahfidzv2.viewmodel.ViewModelFactory
import ayo.semangat.tahfidzv2.vo.Status
import kotlinx.android.synthetic.main.activity_chapter.*

class ChapterActivity : AppCompatActivity() {

    private var mServiceBound = false
    private lateinit var myService: MyService

    private val mServiceConnection = object : ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val myBinder = p1 as MyService.MyBinder
            myService = myBinder.getService
            mServiceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mServiceBound = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        val factory = ViewModelFactory.getInstance(this, "quran")
        val viewModel = ViewModelProvider(this, factory)[ChapterViewModel::class.java]

//        val boundSErviceIntent = Intent(this, MyService::class.java)
//        bindService(boundSErviceIntent, mServiceConnection, Context.BIND_AUTO_CREATE)

        val chapterAdapter = ChapterAdapter()
        viewModel.getChapter().observe(this, Observer { chapter ->
            if (chapter != null){
                when(chapter.status){
                    Status.LOADING -> showLoading(true, progress_bar)
                    Status.SUCCESS -> {
                        showLoading(false, progress_bar)
                        chapterAdapter.setChapters(chapter.data)
                        chapterAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        showLoading(false, progress_bar)
                        Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(rvChapter){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = chapterAdapter
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        if (mServiceBound){
//            unbindService(mServiceConnection)
//        }
//    }
}