package template;

/**
 * Created by yuhaiyang on 2019/9/26.
 */
public class MVVMTemple {

    public static final String ACTIVITY_SUFFIX = "Activity";
    public static final String FRAGMENT_SUFFIX = "Fragment";
    public static final String VIEW_MODEL_SUFFIX = "ViewModel";

    public static final String ACTIVITY =
            "package ${PACKAGE_NAME}\n" +
                    "\n" +
                    "import android.os.Bundle\n" +
                    "import com.ishow.noah.R\n" +
                    "import com.ishow.noah.modules.base.mvvm.view.AppBindActivity\n" +
                    "\n" +
                    "/**\n" +
                    " * Created by yuhaiyang on ${DATE}.\n" +
                    " */\n" +
                    "class ${TARGET_NAME}" + ACTIVITY_SUFFIX + " : AppBindActivity<A${TARGET_NAME}Binding, ${TARGET_NAME}" + VIEW_MODEL_SUFFIX + ">() {\n" +
                    "\n" +
                    "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                    "        super.onCreate(savedInstanceState)\n" +
                    "        bindContentView(R.layout.a${TARGET_NAME_LINE})\n" +
                    "    }\n" +
                    "\n" +
                    "    override fun initViews() {\n" +
                    "        super.initViews()\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "}";

    public static final String FRAGMENT =
            "package ${PACKAGE_NAME}\n" +
                    "\n" +
                    "import com.ishow.noah.R\n" +
                    "import com.ishow.noah.modules.base.mvvm.view.AppBindFragment\n" +
                    "\n" +
                    "/**\n" +
                    " * Created by yuhaiyang on ${DATE}.\n" +
                    " */\n" +
                    "class ${TARGET_NAME}" + FRAGMENT_SUFFIX + " : AppBindFragment<F${TARGET_NAME}Binding, ${TARGET_NAME}" + VIEW_MODEL_SUFFIX + ">() {\n" +
                    "\n" +
                    "    override fun getLayout(): Int = R.layout.f${TARGET_NAME_LINE}" +
                    "\n" +
                    "}";

    public static final String VIEW_MODEL =
            "package ${PACKAGE_NAME}\n" +
                    "\n" +
                    "import android.app.Application\n" +
                    "import androidx.lifecycle.LiveData\n" +
                    "import androidx.lifecycle.MutableLiveData\n" +
                    "import com.ishow.noah.modules.base.mvvm.viewmodel.AppBaseViewModel\n" +
                    "\n" +
                    "/**\n" +
                    " * Created by yuhaiyang on ${DATE}.\n" +
                    " */\n" +
                    "class ${TARGET_NAME}" + VIEW_MODEL_SUFFIX + " (app: Application) : AppBaseViewModel(app) {\n" +
                    "\n" +
                    "    private val _test = MutableLiveData<String>()\n" +
                    "    val test: LiveData<String>\n" +
                    "        get() = _test\n" +
                    "\n" +
                    "}";

    public static final String LAYOUT =
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<layout xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                    "\n" +
                    "    <data>\n" +
                    "\n" +
                    "        <variable\n" +
                    "            name=\"vm\"\n" +
                    "            type=\"${PACKAGE_NAME}.${TARGET_NAME}" + VIEW_MODEL_SUFFIX + "\" />\n" +
                    "    </data>\n" +
                    "\n" +
                    "    <LinearLayout\n" +
                    "        android:layout_width=\"match_parent\"\n" +
                    "        android:layout_height=\"match_parent\"\n" +
                    "        android:orientation=\"vertical\">\n" +
                    "\n" +
                    "    </LinearLayout>\n" +
                    "</layout>";

}
