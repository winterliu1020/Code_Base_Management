package cn.edu.nchu.software.util;

import java.io.*;

public class CMDUtil {

	//执行cmd命令，获取返回结果
    public static String execCMD(int type,String path,String file1,String file2,String similarity) {
        StringBuilder sb =new StringBuilder();
        try {
        	String[] cmds = {"sim_java -t ","sim_c -t ","sim_c++ -t ","sim_text -t "};
    		String[] cmd = new String[] {"cmd", "/c",cmds[type-1] + similarity + " -p " + file1 + " " + file2};
            Process process=Runtime.getRuntime().exec(cmd,null,new File(path));
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream(),"GB2312"));
            String line;
            while((line=bufferedReader.readLine())!=null) {
                sb.append(line+"\n");
            }
            bufferedReader.close();
            process.getOutputStream().close();
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }
}
