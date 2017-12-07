package cn.ijero.toolbar

import android.content.Context
import android.support.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.layout_toolbar.view.*

/**
 * Created by Jero on 2017/12/1.
 */
class Toolbar
@JvmOverloads
constructor(ctx: Context, attrs: AttributeSet? = null) : FrameLayout(ctx, attrs), View.OnClickListener {
    private val root = LayoutInflater.from(ctx).inflate(R.layout.layout_toolbar, this, true)
    private var listener: OnItemClickListener? = null
    private var title: String? = null
    private var leftText: String? = null
    private var rightText: String? = null
    private var rightIconId = NO_ID
    private var leftIconId = NO_ID
    private var leftTextColor = NO_COLOR
    private var rightTextColor = NO_COLOR
    private var titleTextColor = NO_COLOR
    private var leftTextSize = NO_DIMEN
    private var rightTextSize = NO_DIMEN
    private var titleTextSize = NO_DIMEN

    companion object {
        const val NO_DIMEN = -1F
        const val NO_ID = View.NO_ID
        const val NO_COLOR = -1
    }

    override fun onClick(v: View) {
        root.apply {
            when (v.id) {
                toolbarTitleTextView.id -> {
                    listener?.onItemClick(v, ItemType.TITLE)
                }
                toolbarLeftTextView.id -> {
                    listener?.onItemClick(v, ItemType.LEFT_TEXT)
                }
                toolbarLeftIconImageView.id -> {
                    listener?.onItemClick(v, ItemType.LEFT_ICON)
                }
                toolbarRightIconImageView.id -> {
                    listener?.onItemClick(v, ItemType.RIGHT_ICON)
                }
                toolbarRightTextView.id -> {
                    listener?.onItemClick(v, ItemType.RIGHT_TEXT)
                }
            }
        }
    }

    init {
        applyStyle(attrs)
        loadDataForStyle()
        setListeners()
    }

    private fun setListeners() {
        root.apply {
            toolbarTitleTextView.setOnClickListener(this@Toolbar)
            toolbarLeftTextView.setOnClickListener(this@Toolbar)
            toolbarRightTextView.setOnClickListener(this@Toolbar)
            toolbarLeftIconImageView.setOnClickListener(this@Toolbar)
            toolbarRightIconImageView.setOnClickListener(this@Toolbar)
        }
    }

    private fun loadDataForStyle() {
        setTitle()
        setLeft()
        setRight()
    }

    private fun setTitle() {
        if (title != null) {
            show(ItemType.TITLE)
            title(title)
        }

        if (titleTextColor != NO_COLOR) {
            titleTextColor(titleTextColor)
        }

        if (titleTextSize != NO_DIMEN) {
            titleTextSize(titleTextSize)
        }
    }

    private fun setLeft() {
        if (leftText != null) {
            show(ItemType.LEFT_TEXT)
            leftText(leftText)
        }
        if (leftTextColor != NO_COLOR) {
            leftTextColor(leftTextColor)
        }

        if (leftTextSize != NO_DIMEN) {
            leftTextSize(leftTextSize)
        }

        if (leftIconId != View.NO_ID) {
            show(ItemType.LEFT_ICON)
            leftIcon(leftIconId)
        }
    }

    private fun setRight() {
        if (rightText != null) {
            show(ItemType.RIGHT_TEXT)
            rightText(rightText)
        }

        if (rightTextColor != NO_COLOR) {
            rightTextColor(rightTextColor)
        }

        if (rightTextSize != NO_DIMEN) {
            rightTextSize(rightTextSize)
        }

        if (rightIconId != View.NO_ID) {
            show(ItemType.RIGHT_ICON)
            rightIcon(rightIconId)
        }
    }


    private fun applyStyle(attrs: AttributeSet?) {
        attrs ?: return

        val ta = context.obtainStyledAttributes(attrs, R.styleable.JeroToolbar)
        title = ta.getString(R.styleable.JeroToolbar_title)
        leftText = ta.getString(R.styleable.JeroToolbar_leftText)
        rightText = ta.getString(R.styleable.JeroToolbar_rightText)
        leftIconId = ta.getResourceId(R.styleable.JeroToolbar_leftIcon, View.NO_ID)
        rightIconId = ta.getResourceId(R.styleable.JeroToolbar_rightIcon, View.NO_ID)
        leftTextColor = ta.getColor(R.styleable.JeroToolbar_leftTextColor, leftTextColor)
        rightTextColor = ta.getColor(R.styleable.JeroToolbar_rightTextColor, rightTextColor)
        titleTextColor = ta.getColor(R.styleable.JeroToolbar_titleTextColor, titleTextColor)
        leftTextSize = ta.getDimension(R.styleable.JeroToolbar_leftTextSize, leftTextSize)
        titleTextSize = ta.getDimension(R.styleable.JeroToolbar_titleTextSize, titleTextSize)
        rightTextSize = ta.getDimension(R.styleable.JeroToolbar_rightTextSize, rightTextSize)

        ta.recycle()
    }

    /**
     * 设置标题字体颜色
     *
     * @param textColor 字体颜色值
     *
     * @author Jero
     */
    fun titleTextColor(textColor: Int) {
        titleTextView().setTextColor(textColor)
    }

    /**
     * 设置左边字体颜色
     *
     * @param textColor 字体颜色值
     *
     * @author Jero
     */
    fun leftTextColor(textColor: Int) {
        leftTextView().setTextColor(textColor)
    }

    /**
     * 设置右边字体颜色
     *
     * @param textColor 字体颜色值
     *
     * @author Jero
     */
    fun rightTextColor(textColor: Int) {
        rightTextView().setTextColor(textColor)
    }

    /**
     * 设置左边字体大小
     *
     * @param textSize 字体的大小
     *
     * @author Jero
     */
    fun leftTextSize(textSize: Float) {
        leftTextView().paint.textSize = textSize
    }

    /**
     * 设置右边字体大小
     *
     * @param textSize 字体的大小
     *
     * @author Jero
     */
    fun rightTextSize(textSize: Float) {
        rightTextView().paint.textSize = textSize
    }

    /**
     * 设置标题字体大小
     *
     * @param textSize 字体的大小
     *
     * @author Jero
     */
    fun titleTextSize(textSize: Float) {
        titleTextView().paint.textSize = textSize
    }

    /**
     * 设置点击监听器
     *
     * @author Jero
     */
    fun listener(listener: OnItemClickListener) {
        this.listener = listener
    }

    /**
     * 设置标题的文本内容
     *
     * @param text 文本
     *
     * @author Jero
     */
    fun title(text: String?) {
        titleTextView().text = text
    }

    /**
     * 设置左边的文本内容
     *
     * @param text 文本
     *
     * @author Jero
     */
    fun leftText(text: String?) {
        leftTextView().text = text
    }

    /**
     * 设置右边的文本内容
     *
     * @param text 文本
     *
     * @author Jero
     */
    fun rightText(text: String?) {
        rightTextView().text = text
    }

    /**
     * 设置左边的图片资源
     *
     * @param resId 资源id
     *
     * @author Jero
     */
    fun leftIcon(resId: Int) {
        leftImageView().setImageResource(resId)
    }

    /**
     * 设置右边的图片资源
     *
     * @param resId 资源id
     *
     * @author Jero
     */
    fun rightIcon(resId: Int) {
        rightImageView().setImageResource(resId)
    }

    /**
     * 获取左边的TextView
     *
     * @author Jero
     */
    fun leftTextView() = root.toolbarLeftTextView!!

    /**
     * 获取右边的TextView
     *
     * @author Jero
     */
    fun rightTextView() = root.toolbarRightTextView!!

    /**
     * 获取标题的TextView
     *
     * @author Jero
     */
    fun titleTextView() = root.toolbarTitleTextView!!

    /**
     * 获取左边的ImageView
     *
     * @author Jero
     */
    fun leftImageView() = root.toolbarLeftIconImageView!!

    /**
     * 获取右边的ImageView
     *
     * @author Jero
     */
    fun rightImageView() = root.toolbarRightIconImageView!!

    /**
     * 显示条目
     *
     * @param itemType 参考[ItemType]
     * @param isAnimated 是否以动画形式显示，默认为false
     *
     * @author Jero
     */
    fun show(itemType: ItemType, isAnimated: Boolean = false) {
        if (isAnimated) {
            TransitionManager.beginDelayedTransition(root.toolbarRootLayout)
        }

        when (itemType) {
            Toolbar.ItemType.LEFT_ICON -> {
                showLeftImage()
                hideLeftText()
            }
            Toolbar.ItemType.RIGHT_ICON -> {
                showRightImage()
                hideRightText()
            }
            Toolbar.ItemType.TITLE -> {
                showTitleText()
            }
            Toolbar.ItemType.LEFT_TEXT -> {
                showLeftText()
                hideLeftImage()
            }
            Toolbar.ItemType.RIGHT_TEXT -> {
                showRightText()
                hideRightImage()
            }
        }
        if (isAnimated) {
            TransitionManager.endTransitions(root.toolbarRootLayout)
        }
    }

    fun enable(itemType: ItemType, enable: Boolean) {
        when (itemType) {
            Toolbar.ItemType.LEFT_ICON -> {
                leftImageView().isEnabled = enable
            }
            Toolbar.ItemType.RIGHT_ICON -> {
                rightImageView().isEnabled = enable
            }
            Toolbar.ItemType.TITLE -> {
                titleTextView().isEnabled = enable
            }
            Toolbar.ItemType.LEFT_TEXT -> {
                leftTextView().isEnabled = enable
            }
            Toolbar.ItemType.RIGHT_TEXT -> {
                rightTextView().isEnabled = enable
            }
        }
    }

    /**
     * 隐藏条目
     *
     * @param itemType 参考[ItemType]
     * @param isAnimated 是否以动画形式隐藏，默认为false
     *
     * @author Jero
     */
    fun hide(itemType: ItemType, isAnimated: Boolean = false) {
        if (isAnimated) {
            TransitionManager.beginDelayedTransition(root.toolbarRootLayout)
        }
        when (itemType) {
            Toolbar.ItemType.LEFT_ICON -> {
                hideLeftImage()
            }
            Toolbar.ItemType.RIGHT_ICON -> {
                hideRightImage()
            }
            Toolbar.ItemType.TITLE -> {
                hideTitleText()
            }
            Toolbar.ItemType.LEFT_TEXT -> {
                hideLeftText()
            }
            Toolbar.ItemType.RIGHT_TEXT -> {
                hideRightText()
            }
        }
        if (isAnimated) {
            TransitionManager.endTransitions(root.toolbarRootLayout)
        }
    }

    private fun showLeftText() {
        leftTextView().visibility = View.VISIBLE
    }

    private fun showRightText() {
        rightTextView().visibility = View.VISIBLE
    }

    private fun showLeftImage() {
        leftImageView().visibility = View.VISIBLE
    }

    private fun showRightImage() {
        rightImageView().visibility = View.VISIBLE
    }

    private fun showTitleText() {
        titleTextView().visibility = View.VISIBLE
    }

    private fun hideLeftText() {
        leftTextView().visibility = View.GONE
    }

    private fun hideRightText() {
        rightTextView().visibility = View.GONE
    }

    private fun hideLeftImage() {
        leftImageView().visibility = View.GONE
    }

    private fun hideRightImage() {
        rightImageView().visibility = View.GONE
    }

    private fun hideTitleText() {
        titleTextView().visibility = View.GONE
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, itemType: ItemType)
    }


    /**
     * [ItemType.LEFT_TEXT] - 左边的文本条目
     *
     * [ItemType.LEFT_ICON] - 左边的Image条目
     *
     * [ItemType.TITLE] - 标题条目
     *
     * [ItemType.RIGHT_TEXT] - 右边的文本条目
     *
     * [ItemType.RIGHT_ICON] - 右边的Image条目
     *
     * @author Jero
     */
    enum class ItemType {
        LEFT_TEXT,
        LEFT_ICON,
        TITLE,
        RIGHT_TEXT,
        RIGHT_ICON
    }

}