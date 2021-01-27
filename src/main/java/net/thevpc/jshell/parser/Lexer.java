package net.thevpc.jshell.parser;

public interface Lexer {
    boolean skipWhites();

    Token peekToken();

    Token nextToken();
}
