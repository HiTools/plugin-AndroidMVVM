package configure;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import template.MVVMTemple;


/**
 * Created by yuhaiyang on 2019/9/26.
 */
@State(name = "MVVMTemplateSetting", storages = {@Storage(value = "mvvm_generator_config.xml")})
public class MVVMTemplateSettings implements PersistentStateComponent<Element> {
    private String activityTemplate;
    private String fragmentTemplate;
    private String viewModelTemplate;
    private String layoutTemplate;

    public static MVVMTemplateSettings getInstance() {
        return ServiceManager.getService(MVVMTemplateSettings.class);
    }

    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("MvvmTemplateSettings");
        element.setAttribute("activityTemplate", getActivityTemplate());
        element.setAttribute("fragmentTemplate", getFragmentTemplate());
        element.setAttribute("viewModelTemplate", getViewModelTemplate());
        element.setAttribute("layoutTemplate", getLayoutTemplate());
        return element;
    }

    @Override
    public void loadState(@NotNull Element element) {
        setActivityTemplate(element.getAttributeValue("activityTemplate"));
        setFragmentTemplate(element.getAttributeValue("fragmentTemplate"));
        setViewModelTemplate(element.getAttributeValue("viewModelTemplate"));
        setLayoutlTemplate(element.getAttributeValue("layoutTemplate"));
    }


    void setActivityTemplate(String activityTemplate) {
        this.activityTemplate = activityTemplate;
    }

    public void setFragmentTemplate(String fragmentTemplate) {
        this.fragmentTemplate = fragmentTemplate;
    }

    public String getActivityTemplate() {
        return activityTemplate == null ? MVVMTemple.ACTIVITY : activityTemplate;
    }

    public String getFragmentTemplate() {
        return fragmentTemplate == null ? MVVMTemple.FRAGMENT : fragmentTemplate;
    }

    void setViewModelTemplate(String template) {
        viewModelTemplate = template;
    }

    public String getViewModelTemplate() {
        return viewModelTemplate == null ? MVVMTemple.VIEW_MODEL : viewModelTemplate;
    }

    void setLayoutlTemplate(String template) {
        layoutTemplate = template;
    }

    public String getLayoutTemplate() {
        return layoutTemplate == null ? MVVMTemple.LAYOUT : layoutTemplate;
    }
}
