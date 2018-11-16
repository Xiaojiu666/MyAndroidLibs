package com.xiaojiu.studylibs.manager;

public class AlgorithmManager {


    public static AlgorithmManager getInstance() {
        AlgorithmManager algorithmManager = null;
        if (algorithmManager == null) {
            algorithmManager = new AlgorithmManager();
        }
        return algorithmManager;
    }

    //Q:给出两个整数a和b, 求他们的和, 但不能使用 + 等数学运算符。
    //A:不能使用数学运算符，第一时间想到使用其他的运算符，例如 位运算符
       /* 位运算符复习
        与:		a&b  ab都为1 结果为1
        或:		a|b	 只要有一个为1 就为1
        非:		~	 操作数相反
        异或：	a^b	 相同得0 不同得1*/
     /*情况1举例：
                3 = 0011
            +	4 =	0100		^					&
            -----------------------------------------------------
            7 = 0111 			7 = 0111			0 = 0000*/
        // 因为 两个数 与的特性是 必须都为真，结果才是真，导致的结果就是 异或 结果都为1
        // 当两个数的与为0时,说明他已或的值  必为他俩的和
        //则如果前提为a&b = 0的时候 ， a+b=a^b
     /*情况2举例：
                5 = 0101
            +	4 =	0100		^					&
            -----------------------------------------------------
            9 = 1001 			1 = 0001			4 = 0100*/
        // 递归左移 与 的结果，让 递归的值和 上一次异或结果继续 做与计算，

    public int addOperation(int a, int b) {
        int yu = a & b;
        int huo = a ^ b;
        return yu == 0 ? huo : addOperation(yu << 1, huo);
    }
}
