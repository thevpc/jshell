/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.jshell;

/**
 *
 * @author thevpc
 */
public interface JShellErrorHandler {

    int errorToCode(Throwable th);
    
    String errorToMessage(Throwable th);

    void onErrorImpl(String message, Throwable th, JShellFileContext context);

    boolean isRequireExit(Throwable th);
}
