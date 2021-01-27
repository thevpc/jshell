package net.thevpc.jshell;

import net.thevpc.jshell.parser.JShellParser;
import org.junit.jupiter.api.Test;

public class TestJShellParser {
    @Test
    public void test2(){
        JShellParser r= JShellParser.fromString(
                "a\\\nb\\nt"
        );
        for (String line : r.strReader().lines()) {
            System.out.println(line);
        }
    }
}
