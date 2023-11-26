package ru.korobeynikov.astonintensiv2

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import ru.korobeynikov.astonintensiv2.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.view=this
    }

    fun rotateDrum(){
        val drumView=binding.drumView
        val anim=ObjectAnimator.ofFloat(drumView,"rotation",0f,360f)

        //val duration=Random.nextLong(500,2000)
        //val countSeconds=Random.nextLong(1,10)
        val duration=800L
        val countSeconds=3L

        anim.duration=duration
        anim.repeatCount=Animation.INFINITE
        anim.start()
        Thread{
            TimeUnit.SECONDS.sleep(countSeconds)
            anim.pause()

            drumView.selectColor()
//            runOnUiThread {
//                Toast.makeText(this,drumView.selectColor().toString(),Toast.LENGTH_SHORT).show()
//            }
        }.start()
    }
}