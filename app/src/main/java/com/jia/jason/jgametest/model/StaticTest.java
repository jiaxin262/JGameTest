package com.jia.jason.jgametest.model;

/**
 * Created by xin.jia
 * since 2016/7/29
 */
public class StaticTest {

    private StaticInnerClass s;

    public StaticTest() {
        s = new StaticInnerClass();
    }

    public StaticInnerClass getStaticInner() {
        return s;
    }

    final static class StaticInnerClass extends AbsStatic.AbsInnerStatic{

        int a = 1;

        public StaticInnerClass() {
            a = 2;
        }

    }
}
