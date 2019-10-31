package com.ishow.plugin.mvvm.configure;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.ishow.plugin.mvvm.template.MVVMTemple;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by yuhaiyang on 2019/9/26.
 */
public class MvvmTemplateConfigure implements SearchableConfigurable {
    private MvvmTemplateSettings settings;
    private MVVMForm form;

    public MvvmTemplateConfigure() {
        settings = MvvmTemplateSettings.getInstance();
    }

    @NotNull
    @Override
    public String getId() {
        return MvvmTemplateConfigure.class.getName();
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return MvvmTemplateConfigure.class.getName();
    }

    @Override
    public void reset() {
        form.activityTemplate.setText(MVVMTemple.ACTIVITY);
        form.fragmentTemplate.setText(MVVMTemple.FRAGMENT);
        form.viewModelTemplate.setText(MVVMTemple.VIEW_MODEL);
        form.layoutTemplate.setText(MVVMTemple.LAYOUT);
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (form == null) {
            form = new MVVMForm();
        }
        return form.mainPanel;
    }

    @Override
    public boolean isModified() {

        return !settings.getActivityTemplate().equals(form.activityTemplate.getText()) ||
                !settings.getFragmentTemplate().equals(form.fragmentTemplate.getText()) ||
                !settings.getViewModelTemplate().equals(form.viewModelTemplate.getText()) ||
                !settings.getLayoutTemplate().equals(form.layoutTemplate.getText());
    }

    @Override
    public void apply() throws ConfigurationException {
        settings.setActivityTemplate(form.activityTemplate.getText());
        settings.setActivityTemplate(form.fragmentTemplate.getText());
        settings.setViewModelTemplate(form.viewModelTemplate.getText());
        settings.setLayoutlTemplate(form.layoutTemplate.getText());
    }
}