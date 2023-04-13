package com.example.demo.extension;

public class MainProcess {
    private Extension extension;

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public void run() {
        // 执行一些操作
        if (extension != null) {
            extension.execute("some parameter");
        }
        // 执行其他操作
        System.out.println("Main Process");
    }

    public static void main(String[] args) throws Exception {
        MainProcess mainProcess = new MainProcess();
        PluginManager pluginManager = new PluginManager();

        String sourceCode = "package com.example.demo.extension;\n"
                + "import com.example.demo.extension.Extension;\n\n"
                + "public class BusinessExtension implements Extension {\n"
                + "    @Override\n"
                + "    public void execute(String param) {\n"
                + "        System.out.println(\"Business logic: \" + param);\n"
                + "    }\n"
                + "}";

        pluginManager.uploadPlugin("com.example.demo.extension.BusinessExtension", sourceCode);

        // 主流程加载插件并执行
        Extension extension = pluginManager.loadPlugin("com.example.demo.extension.BusinessExtension");
        mainProcess.setExtension(extension);
        mainProcess.run();
    }
}
