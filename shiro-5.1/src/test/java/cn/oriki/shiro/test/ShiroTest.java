package cn.oriki.shiro.test;

import junit.framework.Assert;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void test() {
        String str = "hello";

        // base64编码
        String base64Encoded = Base64.encodeToString(str.getBytes());
        System.out.println(base64Encoded);// aGVsbG8=

        // base64解码
        String str2 = Base64.decodeToString(base64Encoded);
        System.out.println(str2);// hello

        Assert.assertEquals(str, str2);
    }

    @Test
    public void test2() {
        String str = "hello";

        // 16进制编码
        String hexEncoded = Hex.encodeToString(str.getBytes());
        System.out.println(hexEncoded);// 68656c6c6f

        // 16进制解码
        String str2 = new String(Hex.decode(hexEncoded.getBytes()));
        System.out.println(str2);// hello

        Assert.assertEquals(str, str2);
    }


    @Test
    public void test3() {
        java.lang.String str = "hello";

        // string转bytes
        byte[] bytes = CodecSupport.toBytes(str);

        // bytes转string
        String str2 = CodecSupport.toString(bytes);

        Assert.assertEquals(str, str2);
    }


}
