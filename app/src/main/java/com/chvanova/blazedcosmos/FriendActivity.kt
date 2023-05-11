package com.chvanova.blazedcosmos

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.chvanova.blazedcosmos.databinding.ActivityFriendBinding

class FriendActivity : AppCompatActivity() {

    private val viewModel : FriendActivityViewModel by viewModels()
    lateinit var binding: ActivityFriendBinding
    var dotImageList : MutableList<ImageView> = mutableListOf()
    var gameVsComp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        val decorView = window.decorView
        val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        decorView.systemUiVisibility = uiOptions
        val handler = Handler()
        val hideSystemUI = Runnable { decorView.systemUiVisibility = uiOptions }

        decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                // The system bars are visible. Schedule a task to hide the navigation and status bar after a few seconds.
                handler.postDelayed(hideSystemUI, 5000)
            }
        }

        if (intent.getStringExtra("Game type") == "p2c") gameVsComp = true

        binding.imageWinner.isVisible = false
        binding.againImageView.isVisible = false
        binding.imageViewMoveGreen.isVisible = false
        binding.imageViewMoveYellow.isVisible = false

        binding.imageViewStaticGreen.isVisible = true
        binding.imageViewStaticYellow.isVisible = true

        dotImageListadd()


        for (i in 0..41) dotImageList[i].setImageResource(R.drawable.empty)


        for (i in 0..41) {
            dotImageList[i].setOnClickListener {

                viewModel.columnPress = (i).div(6)
                viewModel.determinePlaceOfDot()

                for (ii in 0..41) {

                    if (viewModel.fieldOfDots[ii] == 1) dotImageList[ii].setImageResource(R.drawable.chip_green)
                    if (viewModel.fieldOfDots[ii] == 2) dotImageList[ii].setImageResource(R.drawable.chip_yellow)

                }
                if (viewModel.whoseTurn == 1) {
                    binding.imageViewMoveGreen.isVisible =
                        false; binding.imageViewMoveYellow.isVisible = true
                }
                if (viewModel.whoseTurn == 2) {
                    binding.imageViewMoveGreen.isVisible =
                        true; binding.imageViewMoveYellow.isVisible = false
                }

                viewModel.evaluateWinner()
                if (viewModel.winner == 1) {
                    binding.imageWinner.isVisible =
                        true; binding.imageWinner.setImageResource(R.drawable.win_green);binding.againImageView.isVisible =
                        true
                }
                if (viewModel.winner == 2) {
                    binding.imageWinner.isVisible =
                        true; binding.imageWinner.setImageResource(R.drawable.win_yellow);binding.againImageView.isVisible =
                        true
                }

                if ((gameVsComp) && (viewModel.winner == 0)) {
                    viewModel.compMove()
                    for (ii in 0..41) {

                        if (viewModel.fieldOfDots[ii] == 1) dotImageList[ii].setImageResource(R.drawable.chip_green)
                        if (viewModel.fieldOfDots[ii] == 2) dotImageList[ii].setImageResource(R.drawable.chip_yellow)

                    }
                    if (viewModel.whoseTurn == 1) {
                        binding.imageViewMoveGreen.isVisible =
                            false; binding.imageViewMoveYellow.isVisible = true
                    }
                    if (viewModel.whoseTurn == 2) {
                        binding.imageViewMoveGreen.isVisible =
                            true; binding.imageViewMoveYellow.isVisible = false
                    }
                    // viewModel.oldEqual()
                    viewModel.evaluateWinner()
                    if (viewModel.winner == 1) {
                        binding.imageWinner.isVisible =
                            true; binding.imageWinner.setImageResource(R.drawable.win_green);binding.againImageView.isVisible =
                            true
                    }
                    if (viewModel.winner == 2) {
                        binding.imageWinner.isVisible =
                            true; binding.imageWinner.setImageResource(R.drawable.win_yellow);binding.againImageView.isVisible =
                            true
                    }

                }


            }
            binding.againImageView.setOnClickListener {
                finish()
            }
        }


    }
    private fun dotImageListadd(){
        dotImageList.add(binding.imV0)
        dotImageList.add(binding.imV1)
        dotImageList.add(binding.imV2)
        dotImageList.add(binding.imV3)
        dotImageList.add(binding.imV4)
        dotImageList.add(binding.imV5)
        dotImageList.add(binding.imV6)
        dotImageList.add(binding.imV7)
        dotImageList.add(binding.imV8)
        dotImageList.add(binding.imV9)

        dotImageList.add(binding.imV10)
        dotImageList.add(binding.imV11)
        dotImageList.add(binding.imV12)
        dotImageList.add(binding.imV13)
        dotImageList.add(binding.imV14)
        dotImageList.add(binding.imV15)
        dotImageList.add(binding.imV16)
        dotImageList.add(binding.imV17)
        dotImageList.add(binding.imV18)
        dotImageList.add(binding.imV19)

        dotImageList.add(binding.imV20)
        dotImageList.add(binding.imV21)
        dotImageList.add(binding.imV22)
        dotImageList.add(binding.imV23)
        dotImageList.add(binding.imV24)
        dotImageList.add(binding.imV25)
        dotImageList.add(binding.imV26)
        dotImageList.add(binding.imV27)
        dotImageList.add(binding.imV28)
        dotImageList.add(binding.imV29)

        dotImageList.add(binding.imV30)
        dotImageList.add(binding.imV31)
        dotImageList.add(binding.imV32)
        dotImageList.add(binding.imV33)
        dotImageList.add(binding.imV34)
        dotImageList.add(binding.imV35)
        dotImageList.add(binding.imV36)
        dotImageList.add(binding.imV37)
        dotImageList.add(binding.imV38)
        dotImageList.add(binding.imV39)

        dotImageList.add(binding.imV40)
        dotImageList.add(binding.imV41)

    }

}
