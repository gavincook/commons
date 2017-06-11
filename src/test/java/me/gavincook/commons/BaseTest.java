package me.gavincook.commons;

/**
 * @author GavinCook
 * @date 2017-06-11
 * @since 1.0.0
 */
public class BaseTest {

    /**
     * 获取当前测试类对应的测试资源所在文件夹路径
     * @return
     */
    public String getTestResourceDir(){
        String dir = getClass().getName().toLowerCase().replaceAll("\\.", "/");
        if(dir.endsWith("test")){
            dir = dir.substring(0, dir.length() - 4);
        }

        return dir.concat("/");
    }

}
