package cn.ijero.toolbar

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.transition.Fade
import android.support.transition.Transition
import android.support.transition.TransitionManager
import android.support.transition.Visibility
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
constructor(ctx: Context, attrs: AttributeSet? = null) : FrameLayout(ctx, attrs) {
    private val root = LayoutInflater.from(ctx).inflate(R.layout.layout_toolbar, this, true)
    private var listener: OnItemClickListener? = null
    private var title: String? = null
    private var leftText: String? = null
    private var rightText: String? = null
    private var rightImageId = NO_ID
    private var leftImageId = NO_ID
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
    private var isTitleEnable = true
    private var isLeftTextEnable = true
    private var isLeftImageEnable = true
    private var isRightTextEnable = true
    private var isRightImageEnable = true

    private var bg: Drawable? = null

    companion object {
        const val NO_DIMEN = -1F
        const val NO_ID = View.NO_ID
        const val NO_COLOR = -1
    }


    init {
        applyStyle(attrs)
        loadDataForStyle()
        setListeners()
    }

    private fun setListeners() {
        root.apply {
            titleTextView().setOnClickListener {
                listener?.onItemClick(it, ItemType.TITLE)
            }
            leftTextView().setOnClickListener {
                listener?.onItemClick(it, ItemType.LEFT_TEXT)
            }
            rightTextView().setOnClickListener {
                listener?.onItemClick(it, ItemType.RIGHT_TEXT)
            }
            leftImageView().setOnClickListener {
                listener?.onItemClick(it, ItemType.LEFT_IMAGE)
            }
            rightImageView().setOnClickListener {
                listener?.onItemClick(it, ItemType.RIGHT_IMAGE)
            }
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

        if (leftImageId != View.NO_ID) {
            leftImage(leftImageId)
        }

        showLeftText(isShowLeftText)
        showLeftImage(isShowLeftIcon)
        enable(ItemType.LEFT_TEXT, isLeftTextEnable)
        enable(ItemType.LEFT_IMAGE, isLeftImageEnable)
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

        if (rightImageId != View.NO_ID) {
            rightImage(rightImageId)
        }

        showRightText(isShowRightText)
        showRightImage(isShowRightIcon)
        enable(ItemType.RIGHT_TEXT, isRightTextEnable)
        enable(ItemType.RIGHT_IMAGE, isRightImageEnable)
    }


    private fun applyStyle(attrs: AttributeSet?) {
        attrs ?: return

        val ta = context.obtainStyledAttributes(attrs, R.styleable.JeroToolbar)
        title = ta.getString(R.styleable.JeroToolbar_jt_title)
        leftText = ta.getString(R.styleable.JeroToolbar_jt_leftText)
        rightText = ta.getString(R.styleable.JeroToolbar_jt_rightText)
        leftImageId = ta.getResourceId(R.styleable.JeroToolbar_jt_leftImage, View.NO_ID)
        rightImageId = ta.getResourceId(R.styleable.JeroToolbar_jt_rightImage, View.NO_ID)
        leftTextColor = ta.getColor(R.styleable.JeroToolbar_jt_leftTextColor, leftTextColor)
        rightTextColor = ta.getColor(R.styleable.JeroToolbar_jt_rightTextColor, rightTextColor)
        titleTextColor = ta.getColor(R.styleable.JeroToolbar_jt_titleTextColor, titleTextColor)
        leftTextSize = ta.getDimension(R.styleable.JeroToolbar_jt_leftTextSize, leftTextSize)
        titleTextSize = ta.getDimension(R.styleable.JeroToolbar_jt_titleTextSize, titleTextSize)
        rightTextSize = ta.getDimension(R.styleable.JeroToolbar_jt_rightTextSize, rightTextSize)
        isShowTitle = ta.getBoolean(R.styleable.JeroToolbar_jt_showTitle, isShowTitle)
        isShowLeftText = ta.getBoolean(R.styleable.JeroToolbar_jt_showLeftText, isShowLeftText)
        isShowLeftIcon = ta.getBoolean(R.styleable.JeroToolbar_jt_showLeftImage, isShowLeftIcon)
        isShowRightText = ta.getBoolean(R.styleable.JeroToolbar_jt_showRightText, isShowRightText)
        isShowRightIcon = ta.getBoolean(R.styleable.JeroToolbar_jt_showRightImage, isShowRightIcon)
        isTitleEnable = ta.getBoolean(R.styleable.JeroToolbar_jt_titleEnable, isTitleEnable)
        isLeftTextEnable = ta.getBoolean(R.styleable.JeroToolbar_jt_leftTextEnable, isLeftTextEnable)
        isLeftImageEnable = ta.getBoolean(R.styleable.JeroToolbar_jt_leftImageEnable, isLeftImageEnable)
        isRightTextEnable = ta.getBoolean(R.styleable.JeroToolbar_jt_rightTextEnable, isRightTextEnable)
        isRightImageEnable = ta.getBoolean(R.styleable.JeroToolbar_jt_rightImageEnable, isRightImageEnable)

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
    fun leftImage(resId: Int) {
        leftImageView().setImageResource(resId)
    }

    /**
     * 设置右边的图片资源
     *
     * @param resId 资源id
     *
     * @author Jero
     */
    fun rightImage(resId: Int) {
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
    fun leftImageView() = root.toolbarLeftImageView!!

    /**
     * 获取右边的ImageView
     *
     * @author Jero
     */
    fun rightImageView() = root.toolbarRightImageView!!

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
            TransitionManager.beginDelayedTransition(root.toolbarRootLayout, Fade())
        }
//        else{
//            TransitionManager.endTransitions(root.toolbarRootLayout)
//        }
        when (itemType) {
            JeroToolbar.ItemType.LEFT_IMAGE -> {
                showLeftImage()
                showLeftText(false)
            }
            JeroToolbar.ItemType.RIGHT_IMAGE -> {
                showRightImage()
                showRightText(false)
            }
            JeroToolbar.ItemType.TITLE -> {
                showTitleText()
            }
            JeroToolbar.ItemType.LEFT_TEXT -> {
                showLeftText()
                showLeftImage(false)
            }
            JeroToolbar.ItemType.RIGHT_TEXT -> {
                showRightText()
                showRightImage(false)
            }
        }
    }

    fun enable(itemType: ItemType, enable: Boolean) {
        when (itemType) {
            JeroToolbar.ItemType.LEFT_IMAGE -> {
                leftImageView().isEnabled = enable
            }
            JeroToolbar.ItemType.RIGHT_IMAGE -> {
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
            TransitionManager.beginDelayedTransition(root.toolbarRootLayout, Fade())
        }
//        else{
//            TransitionManager.endTransitions(root.toolbarRootLayout)
//        }
        when (itemType) {
            JeroToolbar.ItemType.LEFT_IMAGE -> {
                showLeftImage(false)
            }
            JeroToolbar.ItemType.RIGHT_IMAGE -> {
                showRightImage(false)
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
    }

    private fun showLeftText(isShown: Boolean = true) {
        leftTextView().visibility = if (isShown) View.VISIBLE else View.GONE
    }

    private fun showRightText(isShown: Boolean = true) {
        rightTextView().visibility = if (isShown) View.VISIBLE else View.GONE
    }

    private fun showLeftImage(isShown: Boolean = true) {
        leftImageView().visibility = if (isShown) View.VISIBLE else View.GONE
    }

    private fun showRightImage(isShown: Boolean = true) {
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
     * [ItemType.LEFT_IMAGE] - 左边的Image条目
     *
     * [ItemType.TITLE] - 标题条目
     *
     * [ItemType.RIGHT_TEXT] - 右边的文本条目
     *
     * [ItemType.RIGHT_IMAGE] - 右边的Image条目
     *
     * @author Jero
     */
    enum class ItemType {
        LEFT_TEXT,
        LEFT_IMAGE,
        TITLE,
        RIGHT_TEXT,
        RIGHT_IMAGE
    }

}