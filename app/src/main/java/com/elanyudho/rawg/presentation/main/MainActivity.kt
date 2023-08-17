package com.elanyudho.rawg.presentation.main

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.elanyudho.core.abstraction.BaseActivityBinding
import com.elanyudho.core.abstraction.BaseFragmentBinding
import com.elanyudho.rawg.R
import com.elanyudho.rawg.databinding.ActivityMainBinding
import com.elanyudho.rawg.presentation.favorite.FavoriteFragment
import com.elanyudho.rawg.presentation.home.HomeFragment
import com.elanyudho.rawg.presentation.main.adapter.BottomNavAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivityBinding<ActivityMainBinding>() {

    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val favoriteFragment: FavoriteFragment by lazy { FavoriteFragment() }

    private val fragments: List<BaseFragmentBinding<out ViewBinding>> = listOf(homeFragment, favoriteFragment)

    private lateinit var bottomNavAdapter: BottomNavAdapter

    private val mBottomNavigationListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuHome -> {
                    binding.containerMain.setCurrentItem(fragments.indexOf(homeFragment), false)
                    true
                }
                R.id.menuFavorite -> {
                    binding.containerMain.setCurrentItem(fragments.indexOf(favoriteFragment), false)
                    true
                }

                else -> false
            }
        }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = { ActivityMainBinding.inflate(layoutInflater) }

    override fun setupView() {
        with(binding) {
            bottomNavAdapter = BottomNavAdapter(fragments, this@MainActivity)
            containerMain.adapter = bottomNavAdapter
            containerMain.isUserInputEnabled = false
            containerMain.offscreenPageLimit = fragments.size

            btmNavMain.setOnNavigationItemSelectedListener(mBottomNavigationListener)
        }
    }

}