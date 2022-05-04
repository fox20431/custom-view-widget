# Android View

视图的初始化、测量、布局、加载整个流程是在主线程完成的。

主线程的入口所在的类为 `ZygoteInit` （Zygote：合子/受精卵）。

*并不知道为什么起这个名字，和当时生活状况有关，还是将程序拟人化了？*

## Learn the view workflow

### Initialization

当视图的XML布局被创建后，无论是Activity还是Fragment都会传递根视图到 `LayoutInflater#inflate(int resource, ViewGroup root, boolean attachToRoot)` 中调用视图初始化，你可以通过 debug 调试验证这个结论。

初始化的对象是根视图及其子视图，初始化要做的是将XML视图属性解析转换保存到实例的成员变量中，为后续作准备。

### Measure

由于view的attr中包含 `match_parent` 这种不可直观读取的长度，无法在初始化完成这个操作，需要对视图进行测量。

虽然测量仍然发生在主线程，但过程和 `LayoutInflater` 没有关系，是系统的 Java 框架调用，此外布局和绘制也是这个道理。

而这个测量遵循 `view tree` 结构，从根节点依次向下遍历测量，在遍历的过程中会传递宽和高的规格，这个会作为子视图的参考，类似于告诉子视图我把我的视图信息告诉你，你看着吧，规格在被定义为新的类 `View.MeasureSpec`，具体可见下文对 `MeasureSpec`的描述。

`view tree` 的最终的子节点 `View` 会生成宽高存储到实例的成员变量中。

*每次遍历应该都是从根视图向下传递，这个还真没有真真整整的探究过*

*至于有些视图为什么测量多次，这个我没具体了解*

#### Measure Specification

Android引入测量规格（Measure Specification）概念，并为其在 `View` 中创建了 `MeasureSpec` 静态内部类，让我们去看看这个静态内部类。

MeasureSpec用到了位运算，将Int类型拆分成高2位和低30位，并用掩码 `MODE_MASK` 来辅助这个位运算，高2位表示模式，低30位表示具体数值，有兴趣可以看下源代码。

```java
/**
 * Source code: {@link android.view.View.MeasureSpec}
 * The following code is the source code that is deleted some rows and added the other rows to make it readable.
 * It 
 */
public static class MeasureSpec {

    // `MODE_SHIFT` represent value shift distant.
    private static final int MODE_SHIFT = 30;
    // `0x` means the value is in hexadecimal, and `<< MODE_SHIFT` shift MODE_SHIFT(30) bits
    // `11` become `1100...00` in decimal that have thirty zero after shifting.
    private static final int MODE_MASK = 0x3 << MODE_SHIFT;

    /** @hide */
    @IntDef({UNSPECIFIED, EXACTLY, AT_MOST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MeasureSpecMode {
    }

    /**
     * Measure specification mode: The parent has not imposed any constraint
     * on the child. It can be whatever size it wants.
     */
    public static final int UNSPECIFIED = 0 << MODE_SHIFT;

    /**
     * Measure specification mode: The parent has determined an exact size
     * for the child. The child is going to be given those bounds regardless
     * of how big it wants to be.
     */
    public static final int EXACTLY = 1 << MODE_SHIFT;

    /**
     * Measure specification mode: The child can be as large as it wants up
     * to the specified size.
     */
    public static final int AT_MOST = 2 << MODE_SHIFT;

    /**
     * Creates a measure specification based on the supplied size and mode.
     *
     * The mode must always be one of the following:
     * <ul>
     *  <li>{@link android.view.View.MeasureSpec#UNSPECIFIED}</li>
     *  <li>{@link android.view.View.MeasureSpec#EXACTLY}</li>
     *  <li>{@link android.view.View.MeasureSpec#AT_MOST}</li>
     * </ul>
     *
     * <p><strong>Note:</strong> On API level 17 and lower, makeMeasureSpec's
     * implementation was such that the order of arguments did not matter
     * and overflow in either value could impact the resulting MeasureSpec.
     * {@link android.widget.RelativeLayout} was affected by this bug.
     * Apps targeting API levels greater than 17 will get the fixed, more strict
     * behavior.</p>
     *
     * @param size the size of the measure specification
     * @param mode the mode of the measure specification
     * @return the measure specification based on size and mode
     */
    public static int makeMeasureSpec(@IntRange(from = 0, to = (1 << MeasureSpec.MODE_SHIFT) - 1) int size,
                                      @MeasureSpecMode int mode) {
        if (sUseBrokenMakeMeasureSpec) {
            return size + mode;
        } else {
            // The mode is represented by high 2 bits, and the size is represented by low 30 bits
            return (size & ~MODE_MASK) | (mode & MODE_MASK);
        }
    }

    /**
     * Like {@link #makeMeasureSpec(int, int)}, but any spec with a mode of UNSPECIFIED
     * will automatically get a size of 0. Older apps expect this.
     *
     * @hide internal use only for compatibility with system widgets and older apps
     */
    @UnsupportedAppUsage
    public static int makeSafeMeasureSpec(int size, int mode) {
        if (sUseZeroUnspecifiedMeasureSpec && mode == UNSPECIFIED) {
            return 0;
        }
        return makeMeasureSpec(size, mode);
    }

    /**
     * Extracts the mode from the supplied measure specification.
     *
     * @param measureSpec the measure specification to extract the mode from
     * @return {@link android.view.View.MeasureSpec#UNSPECIFIED},
     *         {@link android.view.View.MeasureSpec#AT_MOST} or
     *         {@link android.view.View.MeasureSpec#EXACTLY}
     */
    @MeasureSpecMode
    public static int getMode(int measureSpec) {
        //noinspection ResourceType
        return (measureSpec & MODE_MASK);    }

    /**
     * Extracts the size from the supplied measure specification.
     *
     * @param measureSpec the measure specification to extract the size from
     * @return the size in pixels defined in the supplied measure specification
     */
    public static int getSize(int measureSpec) {
        return (measureSpec & ~MODE_MASK);
    }


    /**
     * Adjust the measure specification.
     * @param measureSpec measure specification(measure is composed of mode and size)
     * @param delta size to adjust
     * @return measure specification after adjusting
     */
    static int adjust(int measureSpec, int delta) {
        final int mode = getMode(measureSpec);
        int size = getSize(measureSpec);
        if (mode == UNSPECIFIED) {
            // No need to adjust size for UNSPECIFIED mode.
            return makeMeasureSpec(size, UNSPECIFIED);
        }
        size += delta;
        if (size < 0) {
            Log.e(VIEW_LOG_TAG, "MeasureSpec.adjust: new size would be negative! (" + size +
                    ") spec: " + toString(measureSpec) + " delta: " + delta);
            size = 0;
        }
        return makeMeasureSpec(size, mode);    
    }

    /**
     * Returns a String representation of the specified measure
     * specification.
     *
     * @param measureSpec the measure specification to convert to a String
     * @return a String with the following format: "MeasureSpec: MODE SIZE"
     */
    public static String toString(int measureSpec) {
        int mode = getMode(measureSpec);
        int size = getSize(measureSpec);

        StringBuilder sb = new StringBuilder("MeasureSpec: ");

        if (mode == UNSPECIFIED)
            sb.append("UNSPECIFIED ");
        else if (mode == EXACTLY)
            sb.append("EXACTLY ");
        else if (mode == AT_MOST)
            sb.append("AT_MOST ");
        else
            sb.append(mode).append(" ");

        sb.append(size);
        return sb.toString();    }
}
```

## Layout

`onLayout` 在 `View` 类中默认为空，`onLayout` 的主要是由 `ViewGroup` 实现，控制子视图在自己视图上如何放置。

`ViewGroup` 通过自己的逻辑计算出子视图左上右下位置，然后传递给子视图的 `onLayout` 方法，这个方法再把参数保存到本身实例成员变量中，这样就可以知道每个子视图在什么位置了。

## Draw

如果不是特殊需求，我个人认为很少会有人涉及到这个知识，他完成的就是在指定布局中绘制图像。

*因为不需要，我也没仔细研究，但不得不说用 Canvas 画图是一个需要艺术和编程结合的东西。*
