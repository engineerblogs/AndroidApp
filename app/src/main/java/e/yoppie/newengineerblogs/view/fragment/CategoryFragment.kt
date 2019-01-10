package e.yoppie.newengineerblogs.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.view.adapter.ArticleRecyclerAdapter

class CategoryFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

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

        val view = inflater.inflate(R.layout.category_fragment, container, false)
        val articleRecyclerView = view.findViewById<View>(R.id.articleRecyclerView) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(activity)
        articleRecyclerView.layoutManager = linearLayoutManager
        articleRecyclerView.adapter = ArticleRecyclerAdapter(testList)
        return view
    }

}