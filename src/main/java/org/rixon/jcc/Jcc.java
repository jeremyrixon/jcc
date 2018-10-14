package org.rixon.jcc;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.rixon.jcc.antlr.JccLexer;
import org.rixon.jcc.antlr.JccParser;

import java.io.IOException;
import java.io.PrintStream;

import static org.rixon.jcc.antlr.JccParser.*;


public class Jcc {

    private static void doStatement(StatementContext s, PrintStream out) {
        out.format(" movl    $%s, %%eax%n", s.expression().LITERAL_INT().toString());
        out.format(" ret%n", s.expression().LITERAL_INT().toString());
    }

    private static void doFunction(FunctionDeclarationContext f, PrintStream out) {
        String name = f.IDENTIFIER().toString();
        out.format(" .globl _%s%n", name);
        out.format("_%s:%n", name);

        doStatement(f.statement(), out);
    }

    public static void main(String[] args) throws IOException {
        String filename = args[0];
        JccLexer lexer = new JccLexer(CharStreams.fromFileName(filename));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JccParser parser = new JccParser(tokenStream);
        ProgramContext tree = parser.program();

        if (parser.getNumberOfSyntaxErrors() == 0) {
            String outname = args[1];
            if (outname == null) {
                outname = filename.replaceAll("\\.c", ".s");
            }
            try (PrintStream out = new PrintStream(outname)) {
                doFunction(tree.functionDeclaration(), out);
            }
        }

    }

}
