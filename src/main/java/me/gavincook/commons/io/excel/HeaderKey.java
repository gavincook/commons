package me.gavincook.commons.io.excel;

/**
 * @author Divers King
 * @date 2018-10-24
 * @since 1.0.0
 **/
public class HeaderKey {

    private String key;

    private String name;

    public HeaderKey(){
    }

    public HeaderKey(String key, String name){
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
