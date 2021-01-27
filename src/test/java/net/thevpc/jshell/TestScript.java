package net.thevpc.jshell;

import org.junit.jupiter.api.Test;

import java.io.File;

public class TestScript {
    @Test
    public void test() {
        JShell sh = new JShell();
        sh.setExternalExecutor(new JShellExternalExecutor() {
            @Override
            public void execExternalCommand(String[] command, JShellFileContext context) {
                try {
                    ProcessBuilder pb = new ProcessBuilder();
                    Process p = pb.command(command)
                            .directory(new File(context.getCwd()))
                            .start();
                    JShellContext.Watcher watcher = context.bindStreams(p.getInputStream(), p.getErrorStream(), p.getOutputStream());
                    p.waitFor();
                    Thread.sleep(50);
                    watcher.stop();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        sh.executeLine("echo $(dirname aa/$0)", true, sh.createNewContext(sh.getRootContext(),"example",new String[0]));
    }
}
