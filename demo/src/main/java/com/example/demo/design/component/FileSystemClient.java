package com.example.demo.design.component;

/**
 * @author caozhixin
 * @date 2023/11/17 18:53
 */
public class FileSystemClient {
    public static void main(String[] args) {
        // 创建文件和文件夹
        FileSystemComponent file1 = new File("Document.txt");
        FileSystemComponent file2 = new File("Image.jpg");

        Folder folder1 = new Folder("Folder 1");
        folder1.add(file1);
        folder1.add(file2);

        FileSystemComponent file3 = new File("Spreadsheet.xlsx");
        FileSystemComponent file4 = new File("Presentation.pptx");

        Folder folder2 = new Folder("Folder 2");
        folder2.add(file3);
        folder2.add(file4);

        // 创建更大的组合节点，包含前面的文件和文件夹
        Folder rootFolder = new Folder("Root");
        rootFolder.add(folder1);
        rootFolder.add(folder2);

        // 客户端通过 Component 接口操作对象，无论是文件还是文件夹
        rootFolder.display();
    }
}
