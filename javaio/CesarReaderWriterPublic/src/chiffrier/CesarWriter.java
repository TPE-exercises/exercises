package chiffrier;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class CesarWriter extends FilterWriter { //Filter for Cesar Chiffre
	
	public CesarWriter(Writer out, int shift) {  // constructor with number of shifts
	}
	
	public CesarWriter(Writer out) { // constructor with 0 shift
	}
	
	private char encode (char c) { // encodes a char by shifting shift-positions
	}
	
	public void write(int c) throws IOException {  // -- write #1 --
	}
	
	public void write(char[] cbuf, int off, int len) throws IOException {  // -- write #2 --
	}

	public void write(String str, int off, int len) throws IOException {  // -- write #3 --
	}
	
	public void write(String str) throws IOException {
	}
} 

