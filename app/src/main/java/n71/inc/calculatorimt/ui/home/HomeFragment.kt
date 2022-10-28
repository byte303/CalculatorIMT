package n71.inc.calculatorimt.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import io.paperdb.Paper
import n71.inc.calculatorimt.model.Model
import n71.inc.calculatorimt.R
import n71.inc.calculatorimt.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.vm = homeViewModel

        MobileAds.initialize(requireActivity()) {}
        onLoadBanners()

        binding.btnMale.isChecked = true

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_favorite ->{
                    homeViewModel.clickFavorite(requireContext())
                }
            }
            true
        }
        return binding.root
    }

    private fun onLoadBanners(){
        val adView = AdView(requireActivity())
        adView.adSize = AdSize.BANNER
        adView.adUnitId = resources.getString(R.string.text_baner)

        val mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mAdView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }


            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }


            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }
}