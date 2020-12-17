package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import configure.MVVMTemplateSettings;
import org.jetbrains.annotations.NotNull;
import template.MVVMTemple;
import utils.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yuhaiyang on 2019/9/26.
 */
@SuppressWarnings({"ComponentNotRegistered", "DialogTitleCapitalization"})
public class MVVMGeneratorAction extends AnAction {
    private Project project;

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        project = e.getData(PlatformDataKeys.PROJECT);
        final String path = getCurrentPath(e);
        final String moduleName = inputModuleName();
        try {
            create(path, moduleName);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //noinspection DialogTitleCapitalization
        Messages.showMessageDialog("文件已经生成完毕", "提示", Messages.getInformationIcon());
        refreshProject(e);
    }

    protected void create(String pathString, String targetName) throws IOException {

    }

    void createActivity(MVVMTemplateSettings setting, String pathString, String packageName, String targetName) throws IOException {
        final String fileName = StringUtils.plusString(targetName, MVVMTemple.ACTIVITY_SUFFIX, ".kt");
        String content = setting.getActivityTemplate();
        content = content.replaceAll("\\$\\{PACKAGE_NAME}", packageName);
        content = content.replaceAll("\\$\\{TARGET_NAME}", targetName);
        content = content.replaceAll("\\$\\{TARGET_NAME_LINE}", StringUtils.humpToLine(targetName));
        content = StringUtils.format(content);
        createFile(pathString, fileName, content);
    }


    void createFragment(MVVMTemplateSettings setting, String pathString, String packageName, String targetName) throws IOException {
        final String fileName = StringUtils.plusString(targetName, MVVMTemple.FRAGMENT_SUFFIX, ".kt");
        String content = setting.getFragmentTemplate();
        content = content.replaceAll("\\$\\{PACKAGE_NAME}", packageName);
        content = content.replaceAll("\\$\\{TARGET_NAME}", targetName);
        content = content.replaceAll("\\$\\{TARGET_NAME_LINE}", StringUtils.humpToLine(targetName));
        content = StringUtils.format(content);
        createFile(pathString, fileName, content);
    }

    void createViewModel(MVVMTemplateSettings setting, String pathString, String packageName, String targetName) throws IOException {
        final String fileName = StringUtils.plusString(targetName, MVVMTemple.VIEW_MODEL_SUFFIX, ".kt");
        String content = setting.getViewModelTemplate().replaceAll("\\$\\{PACKAGE_NAME}", packageName);
        content = content.replaceAll("\\$\\{TARGET_NAME}", targetName);
        content = StringUtils.format(content);
        createFile(pathString, fileName, content);
    }

    void createActivityLayout(MVVMTemplateSettings setting, String pathString, String packageName, String targetName) throws IOException {
        final String fileName = StringUtils.plusString("a", StringUtils.humpToLine(targetName), ".xml");
        String content = setting.getLayoutTemplate().replaceAll("\\$\\{PACKAGE_NAME}", packageName);
        content = content.replaceAll("\\$\\{TARGET_NAME}", targetName);
        content = StringUtils.format(content);
        createFile(pathString, fileName, content);
    }

    void createFragemtnLayout(MVVMTemplateSettings setting, String pathString, String packageName, String targetName) throws IOException {
        final String fileName = StringUtils.plusString("f", StringUtils.humpToLine(targetName), ".xml");
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
            //noinspection DialogTitleCapitalization
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
        final Project project = e.getProject();
        if (project == null) {
            Messages.showInfoMessage("获取Project失败，工程无法刷新", "提示");
            return;
        }
        final VirtualFile file = ProjectUtil.guessProjectDir(project);
        if (file == null) {
            Messages.showInfoMessage("获取VirtualFile失败，工程无法刷新", "提示");
            return;
        }
        file.refresh(false, true);
    }

    private String inputModuleName() {
        return Messages.showInputDialog(project,
                "请输入模块名称。",
                "输入模块名称",
                Messages.getQuestionIcon());
    }

    private String getCurrentPath(AnActionEvent e) {
        VirtualFile currentFile = PlatformDataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        if (currentFile != null) {
            return currentFile.getPath();
        }
        return null;
    }


    protected String getPackageName(String path) {
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

    protected String getLayoutPath(String path) {
        int index = path.indexOf("/java/");
        return path.substring(0, index) + "/res/layout/";
    }
}
