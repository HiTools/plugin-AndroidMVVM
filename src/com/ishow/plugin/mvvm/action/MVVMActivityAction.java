package com.ishow.plugin.mvvm.action;

import com.ishow.plugin.mvvm.configure.MvvmTemplateSettings;

import java.io.IOException;

/**
 * Created by yuhaiyang on 2019/10/31.
 */
public class MVVMActivityAction extends MvvmGengrenatorAction {

    @Override
    protected void create(String pathString, String targetName) throws IOException {
        final String packageName = getPackageName(pathString);
        final MvvmTemplateSettings setting = MvvmTemplateSettings.getInstance();
        createActivity(setting, pathString, packageName, targetName);
        createViewModel(setting, pathString, packageName, targetName);
        createActivityLayout(setting, getLayoutPath(pathString), packageName, targetName);
    }
}
