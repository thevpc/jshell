package net.thevpc.jshell;

import net.thevpc.jshell.util.DirectoryScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class DirectoryScannerTest {
    static ArrayDirectoryScannerFS fs = new ArrayDirectoryScannerFS();
    @Test
    public void test2() {
        DirectoryScanner sc = null;
//        sc=new DirectoryScanner("/aa/bb/*/dd", fs);
//        Assertions.assertArrayEquals(
//                new String[]{
//                        "/aa/bb/aa/dd",
//                        "/aa/bb/cc/dd",
//                },
//                sc.stream().toArray(String[]::new)
//        );
//        sc = new DirectoryScanner("/aa/bb/a*/dd", fs);
//        Assertions.assertArrayEquals(
//                new String[]{
//                        "/aa/bb/aa/dd",
//                },
//                sc.stream().toArray(String[]::new)
//        );
        sc = new DirectoryScanner("/aa/**/dd", fs);
        System.out.println(String.join("\n",sc.stream().toArray(String[]::new)));
        Assertions.assertArrayEquals(
                new String[]{
                        "/aa/bb/aa/dd",
                        "/aa/bb/cc/dd",
                        "/aa/cc/cc/dd",
                },
                sc.stream().toArray(String[]::new)
        );
    }

    private static class ArrayDirectoryScannerFS extends DirectoryScanner.SimpleDirectoryScannerFS {
        String[] all = new String[]{
                "/aa/bb/aa/dd",
                "/aa/bb/cc/dd",
                "/aa/cc/cc/dd",
                "/aa/cc/cc/ee"
        };

        public ArrayDirectoryScannerFS() {
            super("/");
        }

        @Override
        public boolean exists(String dir) {
            if(dir.equals("/")){
                return true;
            }
            for (int i = 0; i < all.length; i++) {
                if (all[i].equals(dir)) {
                    return true;
                }
                if (all[i].startsWith(dir+"/")) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Stream<String> dirImmediateStream(String dir) {
            Set<String> children = new HashSet<>();
            String d2=dir.endsWith("/")?dir:dir+"/";
            for (int i = 0; i < all.length; i++) {
                if (all[i].startsWith(d2)) {
                    int z=all[i].indexOf('/',dir.length()+1);
                    String substring = z>=0?all[i].substring(0, z):all[i];
                    children.add(substring);
                }
            }
            return children.stream();
        }
    }
}
