package cn.ijero.jerotoolbardemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import cn.ijero.toolbar.JeroToolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), JeroToolbar.OnItemClickListener {
    override fun onItemClick(view: View, itemType: JeroToolbar.ItemType) {
        Toast.makeText(this,"点击了$itemType",Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.listener(this)
        toolbar.enable(JeroToolbar.ItemType.RIGHT_TEXT, false)
    }
}
