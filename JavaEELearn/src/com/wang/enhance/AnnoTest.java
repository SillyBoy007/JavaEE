package com.wang.enhance;


public class AnnoTest {
    //@Anno(name="wang",age=12,value = "we")
    //@Anno({"we","wes"})
    @Anno(name="wang")
    public void show(String str){
        System.out.println("show is running...");
    }
}
