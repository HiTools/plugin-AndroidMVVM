package action;

import configure.MVVMTemplateSettings;

import java.io.IOException;

/**
 * Created by yuhaiyang on 2019/10/31.
 */
public class MVVMFragmentAction extends MVVMGeneratorAction {

    @Override
    protected void create(String pathString, String targetName) throws IOException {
        final String packageName = getPackageName(pathString);
        final MVVMTemplateSettings setting = MVVMTemplateSettings.getInstance();
        createFragment(setting, pathString, packageName, targetName);
        createViewModel(setting, pathString, packageName, targetName);
        createFragemtnLayout(setting, getLayoutPath(pathString), packageName, targetName);
    }
}
