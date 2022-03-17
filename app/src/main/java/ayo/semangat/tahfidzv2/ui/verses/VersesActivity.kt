package ayo.semangat.tahfidzv2.ui.verses

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ayo.semangat.tahfidzv2.R
import ayo.semangat.tahfidzv2.data.source.remote.response.VersesItem
import ayo.semangat.tahfidzv2.utils.milliSecondToTimer
import ayo.semangat.tahfidzv2.utils.showLoading
import ayo.semangat.tahfidzv2.viewmodel.ViewModelFactory
import ayo.semangat.tahfidzv2.vo.Status
import kotlinx.android.synthetic.main.activity_verses.*

class VersesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private var verseItem: List<VersesItem> = listOf()
    private lateinit var mp: MediaPlayer
    private var handler = Handler()
    private var count: Int = 0
    private var listSpinner: MutableList<String> = ArrayList()
    private var start: Int = 0
    private var stop: Int = 0
    private var state: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verses)

        val factory = ViewModelFactory.getInstance(this, "quran")
        val viewModel = ViewModelProvider(this, factory)[VersesViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(EXTRA_ID)
            if (id != null) {
                viewModel.id = id
            }
        }
        val verseAdapter = VersesAdapter()
        viewModel.getVerses().observe(this, { verse ->
            when (verse.status) {
                Status.LOADING -> showLoading(true, progress_bar)
                Status.SUCCESS -> {
                    showLoading(false, progress_bar)
                    verseAdapter.setVerses(verse.data?.verses)
                    verseAdapter.notifyDataSetChanged()
                    verseItem = verse.data!!.verses
                    spinner()
                    setActionBar(verse?.data?.name.transliteration.id)
                }
                Status.ERROR -> {
                    showLoading(false, progress_bar)
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_LONG).show()
                }
            }
        })

        with(rvVerses) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = verseAdapter
        }

        initialize()
    }

    private fun initialize(){
        mp = MediaPlayer()
        fabPlay.setOnClickListener {
            if(start > stop){
                Toast.makeText(this, "Start harus lebih kecil dari berhenti", Toast.LENGTH_SHORT).show()
            }else{
                if (mp.isPlaying){
                    handler.removeCallbacks(runnable)
                    mp.pause()
                    fabPlay.setImageResource(R.drawable.ic_play)
                }else{
                    if (state){
                        preparedMediaPlayer()
                        state = false
                    }
                    mp.start()
                    fabPlay.setImageResource(R.drawable.ic_stop)
                    update()
                }
            }
        }
    }

    private fun update(){
        if (mp.isPlaying){
            rvVerses.smoothScrollToPosition(count)
            handler.postDelayed(runnable, 500)
        }
    }

    private val runnable = Runnable {
        kotlin.run {
            update()
            if (milliSecondToTimer(mp.currentPosition) == milliSecondToTimer(mp.duration)){
                mp.reset()
                if (count == stop){
                    count = start
                }else{
                    count++
                }
                preparedMediaPlayer()
                mp.start()
                update()
            }
        }
    }

    private fun preparedMediaPlayer(){
        try {
            mp.setDataSource(verseItem[count-1].audio.primary)
            mp.prepare()
        }catch (e: InterruptedException){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun spinner(){
        listSpinner.clear()
        for (i in verseItem.indices){
            listSpinner.add("${i + 1}")
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listSpinner)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMulai.adapter = adapter
        spinnerBerhenti.adapter = adapter

        spinnerMulai.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                start = p0?.getItemAtPosition(p2).toString().toInt()
                count = p0?.getItemAtPosition(p2).toString().toInt()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                start = 1
            }
        }

        spinnerBerhenti.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                stop = p0?.getItemAtPosition(p2).toString().toInt()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                stop = 1
            }
        }
    }

    private fun setActionBar(name: String) {
        supportActionBar?.title = name
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
        mp.pause()
    }

}