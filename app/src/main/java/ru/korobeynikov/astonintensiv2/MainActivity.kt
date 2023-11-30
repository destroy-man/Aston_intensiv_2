package ru.korobeynikov.astonintensiv2

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import coil.load
import coil.memory.MemoryCache
import ru.korobeynikov.astonintensiv2.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var isFirstClick = false
    private var isAnimPaused = true
    private var urlSite = "https://loremflickr.com/640/360"
    private lateinit var anim: ObjectAnimator
    private lateinit var drumView: DrumView
    private lateinit var randomImage: ImageView
    private lateinit var customText: CustomText
    private lateinit var drumViewModel: DrumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.view = this
        drumViewModel = ViewModelProvider(this)[DrumViewModel::class.java]
        drumView = binding.drumView
        randomImage = binding.randomImage
        customText = binding.customText
        drumViewModel.getData()?.observe(this) {
            drumView.rotation = it[0] as Float
            isAnimPaused = it[1] as Boolean
            if (!isAnimPaused)
                rotateDrum()
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    fun clearCacheCoil() {
        val imageLoader = this.imageLoader
        imageLoader.diskCache?.remove(urlSite)
        imageLoader.memoryCache?.remove(MemoryCache.Key(urlSite))
    }

    fun rotateDrum() {
        if (!isFirstClick)
            anim = ObjectAnimator.ofFloat(drumView, "rotation", 0f, 360f)
        anim.duration = Random.nextLong(500, 2000)
        anim.repeatCount = Animation.INFINITE
        if (!isFirstClick) {
            anim.start()
            isFirstClick = true
        } else
            anim.resume()
        isAnimPaused = false
        Thread {
            TimeUnit.SECONDS.sleep(Random.nextLong(1, 10))
            anim.pause()
            isAnimPaused = true
            when (anim.animatedValue.toString().toFloat().toInt()) {
                in 0..40, in 351..360 -> {
                    runOnUiThread {
                        clearCacheCoil()
                        randomImage.visibility = View.VISIBLE
                        randomImage.load(urlSite)
                        customText.visibility = View.GONE
                    }
                }
                in 41..90 -> {
                    runOnUiThread {
                        randomImage.visibility = View.GONE
                        customText.visibility = View.VISIBLE
                    }
                }
                in 91..140 -> {
                    runOnUiThread {
                        clearCacheCoil()
                        randomImage.visibility = View.VISIBLE
                        randomImage.load(urlSite)
                        customText.visibility = View.GONE
                    }
                }
                in 141..190 -> {
                    runOnUiThread {
                        randomImage.visibility = View.GONE
                        customText.visibility = View.VISIBLE
                    }
                }
                in 191..240 -> {
                    runOnUiThread {
                        clearCacheCoil()
                        randomImage.visibility = View.VISIBLE
                        randomImage.load(urlSite)
                        customText.visibility = View.GONE
                    }
                }
                in 241..290 -> {
                    runOnUiThread {
                        randomImage.visibility = View.GONE
                        customText.visibility = View.VISIBLE
                    }
                }
                in 291..350 -> {
                    runOnUiThread {
                        randomImage.visibility = View.GONE
                        customText.visibility = View.VISIBLE
                    }
                }
            }
        }.start()
    }

    fun reset() {
        randomImage.visibility = View.GONE
        customText.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        if (isFirstClick)
            drumViewModel.loadData(arrayOf(anim.animatedValue, isAnimPaused))
    }
}