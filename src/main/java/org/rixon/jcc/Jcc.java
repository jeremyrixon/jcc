package org.rixon.jcc;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.rixon.jcc.antlr.JccLexer;
import org.rixon.jcc.antlr.JccParser;

import java.io.IOException;

import static org.rixon.jcc.antlr.JccParser.ProgramContext;

public class Jcc {

    public static void main(String[] args) throws IOException {
        String filename = args[0];
        String outname = args[1];
        JccLexer lexer = new JccLexer(CharStreams.fromFileName(filename));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JccParser parser = new JccParser(tokenStream);
        ProgramContext programContext = parser.program();
        System.out.println(programContext.toString());

    }

}
