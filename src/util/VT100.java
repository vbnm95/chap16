package util;

/*
System.out.print("\033[2J");        // 1. Clear Screen
System.out.print("\033[10;10H");    // 2. Cursor Move
System.out.print("\033[31m");       // 3. Foreground Color
System.out.print("\033[44m");       // 4. Background Color
System.out.print("Hello VT100!");
System.out.print("\033[0m");        // 5. Reset Color
*/

public interface VT100 {
	
	public static void clearScreen() {
		System.out.print("\033[2J");
	}
	
	public static void reset() {
		System.out.print("\033[0m");
	}
	
	public static void cursorMove(int line, int column) {
		if(line <= 0) {
			throw new InvalidCursorException("line = " + line + " 은 허용되지 않습니다.");
		}
		if(column <= 0) {
			throw new InvalidCursorException("column = " + column + " 은 허용되지 않습니다.");
		}
		System.out.printf("\033[%d;%dH", line, column);
	}
	
	public static void setFore(int fg) {
		System.out.printf("\033[%dm", fg);
	}
	
	public static void setFore(Color fg) {
		System.out.printf("\033[%dm", fg.ordinal() + 30);
	}
	
	public static void setBack(int bg) {
		System.out.printf("\033[%dm", bg);
	}
	
	public static void setBack(Color bg) {
		System.out.printf("\033[%dm", bg.ordinal() + 40);
	}
	
	public static void print(char ch) {
		System.out.print(ch);
	}
}
