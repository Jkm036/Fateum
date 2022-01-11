package com.fourTen;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.fourTen.Event.Action;
import com.fourTen.Event.ActionListener;
import com.fourTen.Event.Layer;
import com.fourTen.Inputs.Keyboard;
import com.fourTen.Inputs.Mouse;
import com.fourTen.Mob.Enemy;
import com.fourTen.Mob.MainPlayer;
import com.fourTen.UIManager.UIManager;

public class game extends Canvas implements Runnable,ActionListener{
	private static final long serialVersionUID = 1L;
	public static int width=320;
	public static int height = width/12*9;
	public static int scale=4;
	Dimension dimension = new Dimension(width*scale, height*scale);
	Thread thread;
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	public boolean running;
	public int ticks,frames;
	Screen screen;
	Level level;
	int col;
	Keyboard key;
	Mouse mouse;
	public static int xScroll;
	public static int yScroll;
	MainPlayer player;
	private List<Layer> layers = new ArrayList<Layer>();
	Color c = new Color(0xff0000);
	
	JFrame frame;
	game(){
		frame= new JFrame("Fateum");
		frame.setSize(dimension);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this,BorderLayout.CENTER);
		frame.setVisible(true);
		screen = new Screen(width, height);
		level= new SpawnLevel(screen, "/map.png","/mapWithEntities.png");
		
		layers.add(level);
		
		key=new Keyboard(this);
		addKeyListener(key);
		mouse=new Mouse(this);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		player= new MainPlayer(10*16,8*16);
		level.addPlayer(player);
		xScroll=(((int)player.x)-(screen.width/2));
		yScroll=(((int)player.x)-(screen.height/2));
		level.init();
		
		
		
	}

	public void start() {
		running=true;
		thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		long startTime = System.nanoTime();
		long startMilSecond=System.currentTimeMillis();
		double tickPerSecond=60.0;
		double timeIntervalPerTick = 1000000000.0/tickPerSecond;
		double numOfTIPT=0;
		int tick=0;
		int frames=0;
		
					while(running) {
							long now=System.nanoTime();
							numOfTIPT=(now-startTime)/timeIntervalPerTick;
							//start of tick calculation
								while(numOfTIPT>=1) {
									tick();
									tick++;
									numOfTIPT--;
									startTime=now;
								}
								//end tick calculation
							frames++;
							render();
							if(System.currentTimeMillis()-startMilSecond>=1000) {
								startMilSecond=System.currentTimeMillis();
								//System.out.println("Frames: "+ frames+" ticks: "+tick);
								frame.setTitle("Frames: "+ frames+" ticks: "+tick);
								frames=0;
								tick=0;
								
							}
					}
		
	}
	
	public void tick(){
	level.tick();
	xScroll=(((int)player.x)-(screen.width/2));
	yScroll=(((int)player.y)-(screen.height/2));
		
	}
	
	public void render(){
		BufferStrategy bs= getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		level.render(screen, xScroll, yScroll);
		for(int i=0;i<pixels.length;i++) {
			pixels[i]=screen.pixels[i];
		}
		
		
		
		g.drawImage(image, 0, 0, width*scale, height*scale, null);
		level.uimanager.render(g);
		bs.show();
		g.dispose();
		
	}


	@Override
	public void listenToEvent(Action action) {
		for(int i=0;i<layers.size();i++) {
			layers.get(i).listenToEvent(action);
		}
		
	}
	
	public static void main (String[] args) {
		 game game = new game();
		game.start();
		
	}

}
