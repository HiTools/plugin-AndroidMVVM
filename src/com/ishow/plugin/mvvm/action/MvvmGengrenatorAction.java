package com.ishow.plugin.mvvm.action;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.ishow.plugin.mvvm.configure.MvvmTemplateSettings;
import com.ishow.plugin.mvvm.template.MVVMTemple;
import com.ishow.plugin.mvvm.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yuhaiyang on 2019/9/26.
 */
public class MvvmGengrenatorAction extends AnAction {
    private Project project;
    private PsiDirectory psiDirectory;

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        project = e.getData(PlatformDataKeys.PROJECT);
        final IdeView view = e.getRequiredData(LangDataKeys.IDE_VIEW);
        psiDirectory = view.getOrChooseDirectory();

        final String path = getCurrentPath(e);
        final String moduleName = inputModuleName();
        try {
            create(path, moduleName);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Messages.showMessageDialog("文件已经生成完毕", "提示", Messages.getInformationIcon());
        refreshProject(e);
    }

    private void create(String pathString, String targetName) throws IOException {
        final String packageName = getPackageName(pathString);
        final MvvmTemplateSettings setting = MvvmTemplateSettings.getInstance();
        createView(setting, pathString, packageName, targetName);
        createViewModel(setting, pathString, packageName, targetName);
        createLayout(setting, getLayoutPath(pathString), packageName, targetName);
    }

    private void createView(MvvmTemplateSettings setting, String pathString, String packageName, String targetName) throws IOException {
        final String fileName = StringUtils.plusString(targetName, MVVMTemple.VIEW_SUFFIX, ".kt");
        String content = setting.getViewTemplate();
        content = content.replaceAll("\\$\\{PACKAGE_NAME}", packageName);
        content = content.replaceAll("\\$\\{TARGET_NAME}", targetName);
        content = content.replaceAll("\\$\\{TARGET_NAME_LINE}", StringUtils.humpToLine(targetName));
        content = StringUtils.format(content);
        createFile(pathString, fileName, content);
    }

    private void createViewModel(MvvmTemplateSettings setting, String pathString, String packageName, String targetName) throws IOException {
        final String fileName = StringUtils.plusString(targetName, MVVMTemple.VIEW_MODEL_SUFFIX, ".kt");
        String content = setting.getViewModelTemplate().replaceAll("\\$\\{PACKAGE_NAME}", packageName);
        content = content.replaceAll("\\$\\{TARGET_NAME}", targetName);
        content = StringUtils.format(content);
        createFile(pathString, fileName, content);
    }

    private void createLayout(MvvmTemplateSettings setting, String pathString, String packageName, String targetName) throws IOException {
        final String fileName = StringUtils.plusString("a", StringUtils.humpToLine(targetName), ".xml");
        String content = setting.getLayoutTemplate().replaceAll("\\$\\{PACKAGE_NAME}", packageName);
        content = content.replaceAll("\\$\\{TARGET_NAME}", targetName);
        content = StringUtils.format(content);
        createFile(pathString, fileName, content);
    }

    private void createFile(String pathString, String fileName, String content) throws IOException {
        File path = new File(pathString);
        if (path.isFile()) {
            path = path.getParentFile();
        }

        File file = new File(path, fileName);
        if (!path.exists()) {
            //noinspection ResultOfMethodCallIgnored
            path.mkdirs();
        }

        if (file.exists()) {
            Messages.showMessageDialog("文件已经存在", "提示", Messages.getInformationIcon());
            return;
        }

        //noinspection ResultOfMethodCallIgnored
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.flush();
        writer.close();
    }

    private void refreshProject(AnActionEvent e) {
        e.getProject().getBaseDir().refresh(false, true);
    }

    private String inputModuleName() {
        return Messages.showInputDialog(project,
                "请输入模块名称。",
                "输入模块名称",
                Messages.getQuestionIcon());
    }

    private String getCurrentPath(AnActionEvent e) {
        VirtualFile currentFile = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        if (currentFile != null) {
            return currentFile.getPath();
        }
        return null;
    }


    private String getPackageName(String path) {
        String[] strings = path.split("/");
        StringBuilder packageName = new StringBuilder();
        boolean packageBegin = false;
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            if ((string.equals("com")) || (string.equals("org")) || (string.equals("cn")) || (string.equals("pw"))) {
                packageBegin = true;
            }
            if (packageBegin) {
                packageName.append(string);
                if (i != strings.length - 1) {
                    packageName.append(".");
                }
            }
        }
        return packageName.toString();
    }

    private String getLayoutPath(String path) {
        int index = path.indexOf("/java/");
        return path.substring(0, index) + "/res/layout/";
    }
}
