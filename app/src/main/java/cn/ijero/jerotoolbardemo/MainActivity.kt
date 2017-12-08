package cn.ijero.jerotoolbardemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import cn.ijero.toolbar.JeroToolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), JeroToolbar.OnItemClickListener {
    override fun onItemClick(view: View, itemType: JeroToolbar.ItemType) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.listener(this)
        toolbar.enable(JeroToolbar.ItemType.RIGHT_TEXT, false)
    }
}
