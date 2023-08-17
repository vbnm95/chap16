package util;

import java.util.Objects;

public class Alpha {
	protected int line;
	protected int column;
	protected Color fg;
	protected Color bg;
	protected char ch;
	
	public Alpha() {
		line = (int)(Math.random()*20 + 1);
		column = (int)(Math.random()*40 + 1);
		
		do {
		fg = Color.values()[(int)(Math.random()*8)];
		bg = Color.values()[(int)(Math.random()*8)];
		} while(fg == bg);
		
		ch = (char)(Math.random()*26 + 'A');
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(line, column);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Alpha) {
			Alpha target = (Alpha) obj;
			return this.line == target.line && this.column == target.column;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("[%d, %d, %d, %d, %c]", line, column, fg.ordinal()+30, bg.ordinal()+40, ch);
	}
	
	public void show() {
		this.show(0, 0);
	}
	
	public void show(int offLine, int offColumn) {
		VT100.cursorMove(line + offLine, column + offColumn);
		VT100.setFore(fg);
		VT100.setBack(bg);
		VT100.print(ch);
	}
	
	public void hide() {
		VT100.cursorMove(line, column);
		VT100.reset();
		VT100.print(' ');
	}

	public int getLine() {
		return line;
	}
	
	public void setLine(int line) {
		this.line = line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}

	public Color getFg() {
		return fg;
	}

	public void setFg(Color fg) {
		this.fg = fg;
	}

	public Color getBg() {
		return bg;
	}

	public void setBg(Color bg) {
		this.bg = bg;
	}

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}
	
	
}
