package hu.g14de;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class LogRecorder extends OutputStream {
	
	private PrintStream original;
	
	public LogRecorder(PrintStream original) {
		this.original = original;
	}
	
	@Override
	public void write(int b) throws IOException {
		original.write(b);
	}
	
	@Override
	public void write(@NotNull byte[] b) throws IOException {
		original.write(b);
	}
	
	@Override
	public void write(@NotNull byte[] b, int off, int len) throws IOException {
		original.write(b, off, len);
	}
	
	@Override
	public void flush() throws IOException {
		original.flush();
	}
	
	@Override
	public void close() throws IOException {
		original.close();
	}
}
