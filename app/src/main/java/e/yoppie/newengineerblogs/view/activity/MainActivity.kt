package e.yoppie.newengineerblogs.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.view.adapter.CategoryFragmentPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testList: ArrayList<String> = ArrayList()
        testList.add("a")
        testList.add("s")
        testList.add("d")
        testList.add("f")
        testList.add("q")
        testList.add("w")
        testList.add("e")
        testList.add("r")
        testList.add("h")
        testList.add("j")
        testList.add("k")
        testList.add("l")

        val tabLayout = findViewById<View>(R.id.tabLayout) as TabLayout
        val viewPager = findViewById<View>(R.id.viewPager) as ViewPager
        viewPager.offscreenPageLimit = 2
        val adapter = CategoryFragmentPagerAdapter(supportFragmentManager, testList)

        viewPager.adapter = adapter
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        tabLayout.setupWithViewPager(viewPager)

    }
}
