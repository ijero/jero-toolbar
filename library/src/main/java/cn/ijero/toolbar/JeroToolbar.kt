package cn.ijero.toolbar

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.transition.TransitionManager
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.layout_toolbar.view.*

/**
 * Created by Jero on 2017/12/1.
 */
class JeroToolbar
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
    private var isShowTitle = true
    private var isShowLeftText = false
    private var isShowLeftIcon = false
    private var isShowRightText = false
    private var isShowRightIcon = false
    private var isTitleEnable = false
    private var isLeftTextEnable = false
    private var isLeftIconEnable = false
    private var isRightTextEnable = false
    private var isRightIconEnable = false

    private var bg: Drawable? = null

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
            toolbarTitleTextView.setOnClickListener(this@JeroToolbar)
            toolbarLeftTextView.setOnClickListener(this@JeroToolbar)
            toolbarRightTextView.setOnClickListener(this@JeroToolbar)
            toolbarLeftIconImageView.setOnClickListener(this@JeroToolbar)
            toolbarRightIconImageView.setOnClickListener(this@JeroToolbar)
        }
    }

    private fun loadDataForStyle() {
        setBg()
        setTitle()
        setLeft()
        setRight()
    }

    private fun setBg() {
        if (bg != null) {
            background = bg
        } else {
            setBackgroundColor(ContextCompat.getColor(context, R.color.toolbarBackground))
        }
    }

    private fun setTitle() {
        if (title != null) {
            title(title)
        }

        if (titleTextColor != NO_COLOR) {
            titleTextColor(titleTextColor)
        }

        if (titleTextSize != NO_DIMEN) {
            titleTextSize(titleTextSize)
        }

        enable(ItemType.TITLE, isTitleEnable)
        showTitleText(isShowTitle)
    }

    private fun setLeft() {
        if (leftText != null) {
            leftText(leftText)
        }

        if (leftTextColor != NO_COLOR) {
            leftTextColor(leftTextColor)
        }

        if (leftTextSize != NO_DIMEN) {
            leftTextSize(leftTextSize)
        }

        if (leftIconId != View.NO_ID) {
            leftIcon(leftIconId)
        }

        showLeftText(isShowLeftText)
        showLeftIcon(isShowLeftIcon)
        enable(ItemType.LEFT_TEXT, isLeftTextEnable)
        enable(ItemType.LEFT_ICON, isLeftIconEnable)
    }

    private fun setRight() {
        if (rightText != null) {
            rightText(rightText)
        }

        if (rightTextColor != NO_COLOR) {
            rightTextColor(rightTextColor)
        }

        if (rightTextSize != NO_DIMEN) {
            rightTextSize(rightTextSize)
        }

        if (rightIconId != View.NO_ID) {
            rightIcon(rightIconId)
        }

        showRightText(isShowRightText)
        showRightIcon(isShowRightIcon)
        enable(ItemType.RIGHT_TEXT, isRightTextEnable)
        enable(ItemType.RIGHT_ICON, isRightIconEnable)
    }


    private fun applyStyle(attrs: AttributeSet?) {
        attrs ?: return

        val ta = context.obtainStyledAttributes(attrs, R.styleable.JeroToolbar)
        title = ta.getString(R.styleable.JeroToolbar_jt_title)
        leftText = ta.getString(R.styleable.JeroToolbar_jt_leftText)
        rightText = ta.getString(R.styleable.JeroToolbar_jt_rightText)
        leftIconId = ta.getResourceId(R.styleable.JeroToolbar_jt_leftIcon, View.NO_ID)
        rightIconId = ta.getResourceId(R.styleable.JeroToolbar_jt_rightIcon, View.NO_ID)
        leftTextColor = ta.getColor(R.styleable.JeroToolbar_jt_leftTextColor, leftTextColor)
        rightTextColor = ta.getColor(R.styleable.JeroToolbar_jt_rightTextColor, rightTextColor)
        titleTextColor = ta.getColor(R.styleable.JeroToolbar_jt_titleTextColor, titleTextColor)
        leftTextSize = ta.getDimension(R.styleable.JeroToolbar_jt_leftTextSize, leftTextSize)
        titleTextSize = ta.getDimension(R.styleable.JeroToolbar_jt_titleTextSize, titleTextSize)
        rightTextSize = ta.getDimension(R.styleable.JeroToolbar_jt_rightTextSize, rightTextSize)
        isShowTitle = ta.getBoolean(R.styleable.JeroToolbar_jt_showTitle, isShowTitle)
        isShowLeftText = ta.getBoolean(R.styleable.JeroToolbar_jt_showLeftText, isShowLeftText)
        isShowLeftIcon = ta.getBoolean(R.styleable.JeroToolbar_jt_showLeftIcon, isShowLeftIcon)
        isShowRightText = ta.getBoolean(R.styleable.JeroToolbar_jt_showRightText, isShowRightText)
        isShowRightIcon = ta.getBoolean(R.styleable.JeroToolbar_jt_showRightIcon, isShowRightIcon)
        isTitleEnable = ta.getBoolean(R.styleable.JeroToolbar_jt_titleEnable, isTitleEnable)
        isLeftTextEnable = ta.getBoolean(R.styleable.JeroToolbar_jt_leftTextEnable, isLeftTextEnable)
        isLeftIconEnable = ta.getBoolean(R.styleable.JeroToolbar_jt_leftIconEnable, isLeftIconEnable)
        isRightTextEnable = ta.getBoolean(R.styleable.JeroToolbar_jt_rightTextEnable, isRightTextEnable)
        isRightIconEnable = ta.getBoolean(R.styleable.JeroToolbar_jt_rightIconEnable, isRightIconEnable)

        bg = ta.getDrawable(R.styleable.JeroToolbar_android_background)

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
            JeroToolbar.ItemType.LEFT_ICON -> {
                showLeftIcon()
                showLeftText(false)
            }
            JeroToolbar.ItemType.RIGHT_ICON -> {
                showRightIcon()
                showRightText(false)
            }
            JeroToolbar.ItemType.TITLE -> {
                showTitleText()
            }
            JeroToolbar.ItemType.LEFT_TEXT -> {
                showLeftText()
                showLeftIcon(false)
            }
            JeroToolbar.ItemType.RIGHT_TEXT -> {
                showRightText()
                showRightIcon(false)
            }
        }
        if (isAnimated) {
            TransitionManager.endTransitions(root.toolbarRootLayout)
        }
    }

    fun enable(itemType: ItemType, enable: Boolean) {
        when (itemType) {
            JeroToolbar.ItemType.LEFT_ICON -> {
                leftImageView().isEnabled = enable
            }
            JeroToolbar.ItemType.RIGHT_ICON -> {
                rightImageView().isEnabled = enable
            }
            JeroToolbar.ItemType.TITLE -> {
                titleTextView().isEnabled = enable
            }
            JeroToolbar.ItemType.LEFT_TEXT -> {
                leftTextView().isEnabled = enable
            }
            JeroToolbar.ItemType.RIGHT_TEXT -> {
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
            JeroToolbar.ItemType.LEFT_ICON -> {
                showLeftIcon(false)
            }
            JeroToolbar.ItemType.RIGHT_ICON -> {
                showRightIcon(false)
            }
            JeroToolbar.ItemType.TITLE -> {
                showTitleText(false)
            }
            JeroToolbar.ItemType.LEFT_TEXT -> {
                showLeftText(false)
            }
            JeroToolbar.ItemType.RIGHT_TEXT -> {
                showRightText(false)
            }
        }
        if (isAnimated) {
            TransitionManager.endTransitions(root.toolbarRootLayout)
        }
    }

    private fun showLeftText(isShown: Boolean = true) {
        leftTextView().visibility = if (isShown) View.VISIBLE else View.GONE
    }

    private fun showRightText(isShown: Boolean = true) {
        rightTextView().visibility = if (isShown) View.VISIBLE else View.GONE
    }

    private fun showLeftIcon(isShown: Boolean = true) {
        leftImageView().visibility = if (isShown) View.VISIBLE else View.GONE
    }

    private fun showRightIcon(isShown: Boolean = true) {
        rightImageView().visibility = if (isShown) View.VISIBLE else View.GONE
    }

    private fun showTitleText(isShown: Boolean = true) {
        titleTextView().visibility = if (isShown) View.VISIBLE else View.GONE
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