package net.thevpc.jshell.parser.ctx;

import net.thevpc.jshell.parser.AbstractContext;
import net.thevpc.jshell.parser.JShellParser;
import net.thevpc.jshell.parser.StrReader;
import net.thevpc.jshell.parser.Token;

public class SimpleQuotedContext extends AbstractContext {
    boolean processed=false;
    public SimpleQuotedContext(JShellParser jshp) {
        super(jshp);
    }

    @Override
    public Token nextToken() {
        if(processed){
            return null;
        }
        StrReader reader = this.reader.strReader();
        StringBuilder sb=new StringBuilder();
        while(true){
            int r = reader.read();
            if (r < 0) {
                if(sb.length()==0){
                    return null;
                }
                break;
            }
            char rc=(char)r;
            if (rc == '\'') {
                break;
            }else{
                sb.append(rc);
            }
        }
        processed=true;
        return new Token("'",sb.toString());
    }
}
