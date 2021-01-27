package net.thevpc.jshell;

public interface JShellCommandNode extends JShellNode {
    void eval(JShellFileContext context);
}
