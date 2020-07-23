package com.ishow.plugin.mvvm.action;

import com.ishow.plugin.mvvm.configure.MvvmTemplateSettings;

import java.io.IOException;

/**
 * Created by yuhaiyang on 2019/10/31.
 */
public class MVVMFragmentAction extends MvvmGenerateAction {


    @Override
    protected void create(String pathString, String targetName) throws IOException {
        final String packageName = getPackageName(pathString);
        final MvvmTemplateSettings setting = MvvmTemplateSettings.getInstance();
        createFragment(setting, pathString, packageName, targetName);
        createViewModel(setting, pathString, packageName, targetName);
        createFragemtnLayout(setting, getLayoutPath(pathString), packageName, targetName);
    }
}
