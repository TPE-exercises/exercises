package chiffrier;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class CesarReader extends FilterReader { //Filter for Cesar Decoding
	
	
	public CesarReader (Reader out, int shift) {
	}
	
	public CesarReader (Reader out) {
	}
	
	private  int decode (int c) { // encodes a char by shifting shift-positions
	}
	
	public int read () throws IOException { // -- read #1 --
	}
	
	public int read (char[] cbuf, int off, int len) throws IOException { // -- read #2 --
	} 
	
	public int read (char[] cbuf) throws IOException { // -- read #3 --
	} 
} 
 
