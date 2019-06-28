package cn.edu.nchu.software.editor.info;

import lombok.Data;


@Data
public class FileInfo {
    private int success = 1;
    private String message = "上传成功!";
    private String url;
    
    public FileInfo(int success,String message,String url) {
    	this.success = success;
    	this.message = message;
    	this.url = url;
    }

}
