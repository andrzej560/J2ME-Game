import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.util.Random;import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 *
 * @author Andrzej
 */
class Canv extends Canvas implements CommandListener{

int xx,yy,herox,heroy,redx,redy,bluex,bluey;
int xpos,ypos, blockx, blocky, bx,by, wall_1,wall_2,timer,score;
 Random rand = new Random();
   

Command cmd,ext;
boolean isAlive=false;
Image block, hero1, hero2, red, blue, tlo;



    public Canv() {
		ext = new Command("Restart",Command.EXIT,0);
        cmd = new Command("Restart",Command.OK,0);
        addCommand(cmd);
        setCommandListener(this);
        HeartBeat t = new HeartBeat();
        isAlive = true;
        t.start();
		score=0;
		herox = 22;
		heroy=22;
		timer=300;
		redx = (rand.nextInt(13)+1)*11;
		redy = (rand.nextInt(13)+1)*11;
    }

    protected void keyPressed(int keyCode){
        int gameAction = getGameAction(keyCode);
        switch(gameAction){
            case UP:  gora(); check();  break;
            case DOWN:   dol(); check(); break;
            case LEFT:  lewo(); check();break;
            case RIGHT:  prawo(); check();break;
			
        }
    }
	
	
	public void check(){
		if((herox == redx)&&(heroy==redy)){ 
		redx=(rand.nextInt(13)+1)*11;
		redy=(rand.nextInt(13)+1)*11;
		if (timer>0)score+=1;
		}

	}
	
	public void gora(){
		if (!(heroy==11))
			heroy-=11;
	}
	
	public void dol(){
		if (!(heroy==176-22)) heroy+=11;
	}
	
	public void lewo(){
		if (!(herox==11)) herox-=11;
	}
	
	public void prawo(){
		if (!(herox==176-22)) herox+=11;
	}
    
    protected void update(){
   
        repaint();
        serviceRepaints();
    }

    protected void paint(Graphics g) {
        try {
            block = Image.createImage("/block.PNG");
			hero1 = Image.createImage("/hero1.PNG");
			
			red = Image.createImage("/red.PNG");

        } catch (IOException ex) {
            System.out.println("Wystapil blad");
        }
		
		if (timer<=0) timer=0;
		g.setColor(126,60,30);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(255,0,0);
		g.drawString(timer+"",12,150,0);
		g.drawString(score+"",12,138,0);
	
		for(int i=0; i<16; i++){ //rysuje sciane
			g.drawImage(block,i*11,0,0);
			g.drawImage(block,i*11,220-(11*5),0);
		}
		
		for(int i=0; i<20; i++){ 
			g.drawImage(block,0,i*11,0);
			g.drawImage(block,176-11,i*11,0);
		}

		g.drawImage(red,redx,redy,0);
		g.drawImage(hero1,herox,heroy,0);
    }

    public void commandAction(Command c, Displayable d) {
		if (c==cmd){
	
			score=0;
			herox = 22;
			heroy=22;
			timer=300;
			redx = (rand.nextInt(13)+1)*11;
			redy = (rand.nextInt(13)+1)*11;
		}
		

    }



	class HeartBeat extends Thread{
		
		public void run(){
			
			while (isAlive==true){
				try{
					sleep(100);
					timer-=1;
				   update();
				}catch(Exception e){
					isAlive = false;
				}
			}
		}
	}


}

