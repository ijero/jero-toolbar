package cn.ijero.jerotoolbardemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import cn.ijero.toolbar.JeroToolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), JeroToolbar.OnItemClickListener, RadioGroup.OnCheckedChangeListener {
    private var isAnimated = true
    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        when (group.id) {
            animatedRadioGroup.id -> {
                isAnimated = checkedId == animated.id
            }
            titleRadioGroup.id -> {
                if (checkedId == titleShow.id) {
                    toolbar.show(JeroToolbar.ItemType.TITLE, isAnimated)
                } else {
                    toolbar.hide(JeroToolbar.ItemType.TITLE, isAnimated)
                }
            }
            leftTextRadioGroup.id -> {
                if (checkedId == leftTextShow.id) {
                    toolbar.show(JeroToolbar.ItemType.LEFT_TEXT, isAnimated)
                } else {
                    toolbar.hide(JeroToolbar.ItemType.LEFT_TEXT, isAnimated)
                }
            }
            leftImageRadioGroup.id -> {
                if (checkedId == leftImageShow.id) {
                    toolbar.show(JeroToolbar.ItemType.LEFT_IMAGE, isAnimated)
                } else {
                    toolbar.hide(JeroToolbar.ItemType.LEFT_IMAGE, isAnimated)
                }
            }
            rightTextRadioGroup.id -> {
                if (checkedId == rightTextShow.id) {
                    toolbar.show(JeroToolbar.ItemType.RIGHT_TEXT, isAnimated)
                } else {
                    toolbar.hide(JeroToolbar.ItemType.RIGHT_TEXT, isAnimated)

                }
            }
            rightImageRadioGroup.id -> {
                if (checkedId == rightImageShow.id) {
                    toolbar.show(JeroToolbar.ItemType.RIGHT_IMAGE, isAnimated)
                } else {
                    toolbar.hide(JeroToolbar.ItemType.RIGHT_IMAGE, isAnimated)
                }
            }
        }
    }

    override fun onItemClick(view: View, itemType: JeroToolbar.ItemType) {
        Toast.makeText(this, "点击了$itemType", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.listener(this)

        animatedRadioGroup.setOnCheckedChangeListener(this)
        titleRadioGroup.setOnCheckedChangeListener(this)
        leftTextRadioGroup.setOnCheckedChangeListener(this)
        leftImageRadioGroup.setOnCheckedChangeListener(this)
        rightTextRadioGroup.setOnCheckedChangeListener(this)
        rightImageRadioGroup.setOnCheckedChangeListener(this)
    }
}
