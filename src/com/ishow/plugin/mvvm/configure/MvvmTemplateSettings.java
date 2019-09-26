package com.ishow.plugin.mvvm.configure;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.ishow.plugin.mvvm.template.MVVMTemple;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * Created by yuhaiyang on 2019/9/26.
 */
@State(name = "MvvpTemplateSetting", storages = {@Storage(file = "$APP_CONFIG$/format.xml")})
public class MvvmTemplateSettings implements PersistentStateComponent<Element> {
    private String viewTemplate;
    private String viewModelTemplate;
    private String layoutTemplate;

    public static MvvmTemplateSettings getInstance() {
        return ServiceManager.getService(MvvmTemplateSettings.class);
    }

    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("MvvmTemplateSettings");
        element.setAttribute("viewTemplate", getViewTemplate());
        element.setAttribute("viewModelTemplate", getViewModelTemplate());
        element.setAttribute("layoutTemplate", getLayoutTemplate());
        return element;
    }

    @Override
    public void loadState(@NotNull Element element) {
        setViewTemplate(element.getAttributeValue("viewTemplate"));
        setViewModelTemplate(element.getAttributeValue("viewModelTemplate"));
        setLayoutlTemplate(element.getAttributeValue("layoutTemplate"));
    }


    void setViewTemplate(String viewTemplate) {
        this.viewTemplate = viewTemplate;
    }

    public String getViewTemplate() {
        return viewTemplate == null ? MVVMTemple.VIEW : viewTemplate;
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
