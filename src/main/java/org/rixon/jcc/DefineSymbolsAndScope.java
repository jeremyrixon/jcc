package org.rixon.jcc;

import org.antlr.symtab.FunctionSymbol;
import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.LocalScope;
import org.antlr.symtab.Scope;
import org.antlr.symtab.VariableSymbol;
import org.antlr.v4.runtime.misc.NotNull;
import org.rixon.jcc.antlr.JccBaseListener;
import org.rixon.jcc.antlr.JccParser;

public class DefineSymbolsAndScope extends JccBaseListener {
    Scope currentScope;

    public Scope getCurrentScope() {
        return currentScope;
    }

    @Override
    public void enterProgram(JccParser.ProgramContext ctx) {
        GlobalScope g = new GlobalScope(null);
        pushScope(g);
    }

    @Override
    public void exitProgram(JccParser.ProgramContext ctx) {
        super.exitProgram(ctx);
    }

    @Override
    public void enterFunctionDeclaration(JccParser.FunctionDeclarationContext ctx) {
        super.enterFunctionDeclaration(ctx);
    }

    @Override
    public void exitFunctionDeclaration(JccParser.FunctionDeclarationContext ctx) {
        super.exitFunctionDeclaration(ctx);
    }

    @Override
    public void enterStatement(JccParser.StatementContext ctx) {
        super.enterStatement(ctx);
    }

    @Override
    public void exitStatement(JccParser.StatementContext ctx) {
        super.exitStatement(ctx);
    }

    @Override
    public void enterExpression(JccParser.ExpressionContext ctx) {
        super.enterExpression(ctx);
    }

    @Override
    public void exitExpression(JccParser.ExpressionContext ctx) {
        super.exitExpression(ctx);
    }

    private void pushScope(Scope s) {
        currentScope = s;
        System.out.println("entering: "+currentScope.getName()+":"+s);
    }

    private void popScope() {
        System.out.println("leaving: "+currentScope.getName()+":"+currentScope);
        currentScope = currentScope.getEnclosingScope();
    }

}
