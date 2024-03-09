package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {
	
	public int x, y, width, height, id;
	private String text;
	
	private Rectangle bounds;
	
	private boolean mouseOver, mousePressed;

	// For normal buttons
	public MyButton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		
		initBounds();
		
	}
	
	// For textured buttons
	public MyButton(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		
		initBounds();
		
	}
	
	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public void draw(Graphics g) {
		
		// Body
		drawBody(g);
		
		// Border
		drawBorder(g);
		
		// Text
		drawText(g);
				
	}
	
	public void drawDisabled(Graphics g) {
		g.setColor(new Color(194, 132, 132));
		g.fillRect(x,  y,  width, height);
		
		// Border
		drawBorder(g);
				
		// Text
		drawText(g);
	}
	
	private void drawBorder(Graphics g) {

		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		
		if(mousePressed) {
			g.drawRect(x +1, y +1, width -2, height -2);
			g.drawRect(x +2, y +2, width -4, height -4);
		}
			
		
		
	}

	private void drawBody(Graphics g) {
		if(mouseOver) {
			g.setColor(Color.lightGray);
			if(mousePressed)
				g.setColor(Color.gray);
		}		
		else
			g.setColor(Color.white);
		
		g.fillRect(x,  y,  width, height);
		
	}
	
	

	private void drawText(Graphics g) {
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		
		g.drawString(text, x - w / 2 + width / 2, y + h / 4 + height / 2);

	}
	
	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}
	
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	
	public boolean isMouseOver() {
		return mouseOver;
	}
	
	public boolean isMousePressed() {
		return mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public int getId() {
		return id;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}
