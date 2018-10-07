package org.rixon.jcc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Jcc {

    static byte peek_byte;
    static boolean have_peek = false;

    static final int BUF = 1024;
    static byte[] buffer = new byte[BUF];
    static int len = 0;

    static FileInputStream in;
    static FileOutputStream out;
    static int linenumber = 0;

    static boolean eof = false;

    public static final int EOF            =  -1;
    public static final int LF             =  10;
    public static final int CR             =  13;
    public static final int PAREN_OPEN     =  40;
    public static final int PAREN_CLOSE    =  41;
    public static final int SEMICOLON      =  59;
    public static final int CAPITAL_A      =  65;
    public static final int CAPITAL_Z      =  90;
    public static final int LOWER_A        =  97;
    public static final int LOWER_Z        = 122;
    public static final int BRACE_OPEN     = 123;
    public static final int BRACE_CLOSE    = 125;
    public static final int KEYWORD_INT    = 128;
    public static final int KEYWORD_RETURN = 129;
    public static final int IDENTIFIER     = 130;
    public static final int LITERAL_INT    = 131;

    public static class Node {
        int type;
        Node parent;
        Node[] children;
    }

    static byte get_next_char() throws IOException {
        if (have_peek) {
            have_peek = false;
            return peek_byte;
        } else {
            return (byte) in.read();
        }
    }

    static byte peek() throws IOException {
        if (!have_peek) {
            peek_byte = (byte) in.read();
            have_peek = true;
        }
        return peek_byte;

    }

    static void swallow() {
        have_peek = false;
    }

    static void read_line() throws IOException {
        for (len = 0; len < BUF; len++) {
            byte b = get_next_char();

            if (b == EOF) {
                eof = true;
                return;
            }

            if (b == CR || b == LF) {
                while (peek() == CR || peek() == LF) {
                    swallow();
                }
                return;
            }

            switch (b) {
                default:
                    buffer[len] = b;
            }
        }
    }

    static void write_line() throws IOException {
        for (int i = 0; i < len; i++) {
            out.write(buffer[i]);
        }
        out.write(CR);
        out.write(LF);

    }

    public static void main(String[] args) throws IOException {
        String filename = args[0];
        String outname = args[1];
        in = new FileInputStream(filename);
        out = new FileOutputStream(outname);

        for (linenumber = 1; !eof; linenumber++) {
            read_line();
            write_line();
        }

    }

}
