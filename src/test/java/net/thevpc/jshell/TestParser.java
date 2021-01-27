package net.thevpc.jshell;

import net.thevpc.jshell.parser.JShellParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestParser {
    public static void main(String[] args) {
//        for (JShellNode t : JShellParser.fromString("H\\ ello  \n \"w orld $Hello\" \n `bye`").yaccer().nodes()) {
//            System.out.println(t);
//        }
//        for (Yaccer.JShellNode t : JShellParser.fromString(
//                "#!/bin/nuts\n" +
//                "ntemplate -p .dir-template\n" +
//                "ndocusaurus -d website build\n" +
//                "cp website/nuts/build docs\n" +
//                "_nuts_version=$(nuts nprops -f METADATA apiVersion)\n" +
//                "cp ~/.m2/repository/net/thevpc/nuts/nuts/${_nuts_version}/nuts-${_nuts_version}.jar nuts.jar\n" +
//                "\n").yaccer().commands()) {
//            System.out.println(t);
//        }
//        for (JShellNode t : JShellParser.fromString("a=(ddd)$() fdglk").yaccer().nodes()) {
//            System.out.println(t);
//        }
//        for (Token t : JShellParser.fromString("$() fdglk").tokens()) {
//            System.out.println(t);
//        }
    }
    @Test
    public void test1(){
        JShellNode node = JShellParser.fromString("a='aa' ; b=\"bb\" ;c=`cc$(ls)` ;").parse();
        Assertions.assertEquals(
                "ArgumentsLine{[[WORD(a), =, <SQTE>([<SQTE>(aa)])]]} ; ArgumentsLine{[[WORD(b), =, <DQTE>([STR(bb)])]]} ; ArgumentsLine{[[WORD(c), =, <AQTE>([WORD(cc), $<OPAR>([WORD(ls)])])]]} ;",
                String.valueOf(node)
        );
        System.out.println(node);
    }

    @Test
    public void test2(){
        JShellNode node = JShellParser.fromString("b=\"dd\" aa;").parse();
        Assertions.assertEquals(
                "ArgumentsLine{[[WORD(b), =, <DQTE>([STR(dd)])], WORD(aa)]} ;",
                String.valueOf(node)
        );
        System.out.println(node);
    }

    @Test
    public void test3(){
        JShellNode node = JShellParser.fromString("$a/..a;").parse();
        System.out.println(node);
    }
}
