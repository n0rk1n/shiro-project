package cn.oriki.shiro.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void test() {
        String str = "hello";
        String salt = "salt";
        String md5 = new Md5Hash(str, salt).toString();
        System.out.println(md5);// 06decc8b095724f80103712c235586be

        // 也可以将数据执行完散列算法的数据进行编码
        String base64 = new Md5Hash(str, salt).toBase64();
        String hex = new Md5Hash(str, salt).toHex();
        System.out.println(base64);// Bt7MiwlXJPgBA3EsI1WGvg==
        System.out.println(hex);// 06decc8b095724f80103712c235586be
    }

    @Test
    public void test2() {
        String str = "hello";
        String salt = "salt";
        String md5 = new Sha256Hash(str, salt).toString();

        System.out.println(md5);// cd31b3b98ece60cb739c0bf770b2de892ae0ad133f645513c3d83f08757a843a

        // 也可以将数据执行完散列算法的数据进行编码
        String base64 = new Sha256Hash(str, salt).toBase64();
        String hex = new Sha256Hash(str, salt).toHex();
        System.out.println(base64);// zTGzuY7OYMtznAv3cLLeiSrgrRM/ZFUTw9g/CHV6hDo=
        System.out.println(hex);// cd31b3b98ece60cb739c0bf770b2de892ae0ad133f645513c3d83f08757a843a
    }

    @Test
    public void test3() {
        String str = "hello";
        String salt = "salt";
        // 内部使用MessageDigest
        String simpleHash = new SimpleHash("md5", str, salt).toString();

        System.out.println(simpleHash);// 06decc8b095724f80103712c235586be
    }


}
