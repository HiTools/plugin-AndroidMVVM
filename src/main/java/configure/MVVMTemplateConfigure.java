package configure;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by yuhaiyang on 2019/9/26.
 */
public class MVVMTemplateConfigure implements SearchableConfigurable {
    private MVVMTemplateSettings settings;
    private MVVMForm form;

    public MVVMTemplateConfigure() {
        settings = MVVMTemplateSettings.getInstance();
    }

    @NotNull
    @Override
    public String getId() {
        return MVVMTemplateConfigure.class.getName();
    }

    @Override
    public String getDisplayName() {
        return "Android MVVM Generator";
    }

    @Override
    public void reset() {
        form.activityTemplate.setText(settings.getActivityTemplate());
        form.fragmentTemplate.setText(settings.getFragmentTemplate());
        form.viewModelTemplate.setText(settings.getViewModelTemplate());
        form.layoutTemplate.setText(settings.getLayoutTemplate());
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
        settings.setFragmentTemplate(form.fragmentTemplate.getText());
        settings.setViewModelTemplate(form.viewModelTemplate.getText());
        settings.setLayoutTemplate(form.layoutTemplate.getText());
    }
}
