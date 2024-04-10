package app05;

import android.webkit.JavascriptInterface;

public class Text {
    public static void main(String[] args){
        String s1 = "java";
        String s2 = "java";

        if (s1 == s2) System.out.println("yes");//JAVA 的优化机制，会s2指向s1堆"Java"的位置，这样省内存
        else System.out.println("no");

        String ss1 = new String("Android");
        String ss2 = new String("Android");

        if (ss1 == ss2) System.out.println("yes");
        else System.out.println("no");

        if (ss1.equals(ss2))System.out.println("yes");//取出ss1和ss2的值进行比较
        else System.out.println("no");

        String sn = null;
//        if(sn.equals("java"))System.out.println("yes");//比较字符串的值用equals
//        else System.out.println("no");

        if("java".equals(sn)) System.out.println("yes");//字符串在equals前面，否则会报错
        else System.out.println("no");

        String ssss1 = "java \n is good program!";
        String ssss2 = "java \\ is good program!";
        System.out.println(ssss1);
        System.out.println(ssss2);

        String sss1 = "java is good program!";
        System.out.println(sss1.charAt(3));
        System.out.println(sss1.charAt(sss1.length()-1));//python的-1

        System.out.println(sss1.endsWith("am!"));//判断是否以"am！"为结尾

        System.out.println(sss1.indexOf("oo"));//第一个o在下标9开始
        System.out.println(sss1.indexOf("oa"));//-1

        System.out.println("last:"+sss1.lastIndexOf("a"));

        System.out.println(sss1.substring(10));
        System.out.println(sss1.substring(3,8));

        System.out.println(sss1.replace('a', 'e'));

        String sssss1 = "马兆丰";
        String sssss2 = "马兆丰aaa";
        System.out.println("length:"+sssss1.length());
        System.out.println("length:"+sssss2.length());

        String[] sa = sss1.split(" ");
        for(String b : sa) System.out.println(b);//为迭代器

        System.out.println("startsWith:"+sss1.startsWith("java"));

        char[] sa1 =  sss1.toCharArray();//字符串转为字符数组

        System.out.println("toLowerCase:"+sss1.toLowerCase());
        System.out.println("toUpperCase:"+sss1.toUpperCase());

        System.out.println(sss1.trim());//去除首位的空格

        System.out.println("valueOf:"+String.valueOf(1233.33));
        System.out.println("valueOf:"+String.valueOf(1233.33f));

        StringBuilder sb = new StringBuilder();
        sb.append("java ");
        sb.append("is ");
        sb.append("阿伟");
        System.out.println(sb.toString());
        int ab = 12;
        Integer value = ab;
        Float value1 = 12.34f;

        Integer.valueOf("123");//把字符串转换成int且对象





    }
}
