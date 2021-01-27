package net.thevpc.jshell;

public interface JShellArgumentNode extends JShellNode {
    String[] evalString(JShellFileContext context);
}
