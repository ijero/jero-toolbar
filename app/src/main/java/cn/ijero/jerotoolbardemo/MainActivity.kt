package cn.ijero.jerotoolbardemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import cn.ijero.toolbar.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Toolbar.OnItemClickListener {
    override fun onItemClick(view: View, itemType: Toolbar.ItemType) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.listener(this)
        toolbar.enable(Toolbar.ItemType.RIGHT_TEXT, false)
    }
}
