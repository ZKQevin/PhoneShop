package team.gfr.phone.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Utils {

    public static String md(String source,String salt){
        Md5Hash md5Hash = new Md5Hash(source,salt,1000);
        return md5Hash.toString();
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("admin","admin",1000);
        System.out.println(md5Hash.toString());
    }
}
