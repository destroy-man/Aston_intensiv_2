package ru.korobeynikov.astonintensiv2

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
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


    lateinit var anim:ObjectAnimator
    lateinit var drumView: DrumView
    lateinit var randomImage: ImageView
    lateinit var customText: CustomText
    lateinit var drumViewModel: DrumViewModel
    var isFirstClick=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.view=this
        drumViewModel=ViewModelProvider(this)[DrumViewModel::class.java]
        drumView=binding.drumView
        randomImage=binding.randomImage
        customText=binding.customText

        val drumLiveData = drumViewModel.getData()
//        drumLiveData?.observe(this) {
//            drumView = it[0] as DrumView
//            anim = it[1] as ObjectAnimator
//        }
    }

    @OptIn(ExperimentalCoilApi::class)
    fun clearCacheCoil(){
        val imageLoader=this.imageLoader
        imageLoader.diskCache?.remove("https://loremflickr.com/640/360")
        imageLoader.memoryCache?.remove(MemoryCache.Key("https://loremflickr.com/640/360"))
    }

    fun rotateDrum(){
        if(!isFirstClick)
            anim=ObjectAnimator.ofFloat(drumView,"rotation",0f,360f)

        //val duration=Random.nextLong(500,2000)
        //val countSeconds=Random.nextLong(1,10)
        val duration=800L
        val countSeconds=3L

        anim.duration=duration
        anim.repeatCount=Animation.INFINITE
        if(!isFirstClick) {
            anim.start()
            isFirstClick=true
        }
        else
            anim.resume()
        Thread{
            TimeUnit.SECONDS.sleep(countSeconds)
            anim.pause()

            val angleRotation=anim.animatedValue.toString().toFloat().toInt()
            when(angleRotation){
                in 0..40, in 331..360->{
                    runOnUiThread {
                        clearCacheCoil()
                        randomImage.visibility=View.VISIBLE
                        randomImage.load("https://loremflickr.com/640/360")
                        customText.visibility=View.GONE
                    }
                }
                in 41..90->{
                    runOnUiThread {
                        randomImage.visibility=View.GONE
                        customText.visibility=View.VISIBLE
                    }
                }
                in 91..120->{
                    runOnUiThread {
                        clearCacheCoil()
                        randomImage.visibility=View.VISIBLE
                        randomImage.load("https://loremflickr.com/640/360")
                        customText.visibility=View.GONE
                    }
                }
                in 121..180->{
                    runOnUiThread {
                        randomImage.visibility=View.GONE
                        customText.visibility=View.VISIBLE
                    }
                }
                in 181..250->{
                    runOnUiThread {
                        clearCacheCoil()
                        randomImage.visibility=View.VISIBLE
                        randomImage.load("https://loremflickr.com/640/360")
                        customText.visibility=View.GONE
                    }
                }
                in 251..270->{
                    runOnUiThread {
                        randomImage.visibility=View.GONE
                        customText.visibility=View.VISIBLE
                    }
                }
                in 271..330->{
                    runOnUiThread {
                        randomImage.visibility=View.GONE
                        customText.visibility=View.VISIBLE
                    }
                }
            }
        }.start()
    }

    fun reset(){
        randomImage.visibility=View.GONE
        customText.visibility=View.GONE
    }

    override fun onStop() {
        super.onStop()
        drumViewModel.loadData(arrayOf(drumView,anim))
    }
}