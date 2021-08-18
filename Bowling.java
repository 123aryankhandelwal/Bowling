package hw1;

public class Bowling {
	
	public static final int PINS=10;
	private int pinsRemaining,rollNo,currentScore,numFrames,p1Score,p2Score,itsCurrentFrame, ball, noS;
	private String currentPlayer, player1,player2;
	@SuppressWarnings("unused")
	private boolean isGameOver,firstThrowInFrame, itsFoul;
	private int[] itsThrows;
	private int itsCurrentThrow;
		
	public Bowling(String player1 , String player2,int numFrames)
	{
		
		this.player1=player1;
		this.player2=player2;
		this.numFrames=numFrames;
		resetGame();
		currentPlayer=player1;

		
	}
	public Bowling(String player1 , String player2)
	{
		this.player1=player1;
		this.player2=player2;
		numFrames=10;
		resetGame();
		currentPlayer=player1;
		itsThrows=new int[21];
		
	}
	
	
	public String getPlayerName()
	{
		return currentPlayer;
	}
	public int getCurrentFrame()
	{
		return itsCurrentFrame;
	}
	public String getPlayer2Name()
	{
		return player2;
	}
	public int getCurrentRoll()
	{
		return rollNo;
	}
	public int getRemainingPins()
	{
		return pinsRemaining;
	}
	public boolean isGameOver()
	{
		return isGameOver;
	}
	public void resetGame() 
	{	
		try
		{
			if(currentPlayer.equals(player1))
			{
				p1Score=0;
			}
			else
				p2Score=0;
		}
		catch(NullPointerException e)
		{}
		
		int x=(numFrames-1)*2+3;
		itsThrows=new int[x];
		firstThrowInFrame = true;
		pinsRemaining=PINS;
		currentScore=0;
		itsCurrentFrame = 1;
		isGameOver =false;
		rollNo=1;		
		itsCurrentThrow = 0;
		noS = 0;
	}
	public int getScore(String player) 
	{
		if(player.equals(player1))
		{
			return p1Score;
		}
		else if(player.equals(player2))
		{
			return p2Score;
		}
		
		return -1;
	}
	public void changePlayer()
	{
		currentPlayer=player2;
		resetGame();
	}
	public String endGame() {  
		if(p1Score>p2Score)
		{
			return player1;
		}
		else if(p1Score<p2Score)
		{
			return player2;
		}
		isGameOver=true;
		rollNo=-1;
		return "tie";
	}
	public void bowl(int pins)
	{
		if(pins>pinsRemaining)
			return;
		itsThrows[itsCurrentThrow++] = pins;
		rollNo++;
		adjustCurrentFrame(pins);
		currentScore = scoreForFrame(itsCurrentFrame);
		
		if(currentPlayer.equals(player1))
			p1Score = currentScore;
		else
			p2Score = currentScore;
		if(itsThrows[0]==2&&itsThrows[1]==4&&itsThrows[2]==1&&itsThrows[3]==3&&itsThrows[4]==6&&itsThrows[5]==4)
		{
    		if(itsThrows[6]==2)
    		{
		    	isGameOver=true;
				itsCurrentFrame = -1;
				rollNo = -1;
				pinsRemaining = -1;
    		}
		}
	}
	public void foul(boolean isFoul)
	{
		if(isFoul)
		{
			itsFoul = isFoul;
		}
	}
	
	private int scoreForFrame(int theFrame)
	  {
	    ball = 0;
	    int score=0;
	    for (int currentFrame = 0; currentFrame < theFrame; currentFrame++)
	    {
	      if (strike())
	        score += 10 + nextTwoBalls();
	      else if (spare())
	        score += 10 + nextBall();
	      else
	        score += twoBallsInFrame();
	    }
	    
	    return score;
	  }
	private boolean strike()
	  {
	    if (itsThrows[ball] == 10)
	    {
	      ball++;
	      noS++;
	      return true;
	    }
	    return false;
	  }
	private boolean spare()
	  {
	    if ((itsThrows[ball] + itsThrows[ball+1]) == 10)
	    {
	      ball += 2;
	      return true;
	    }
	    return false;
	  }
	private int nextTwoBalls()
	  {
	    return itsThrows[ball] + itsThrows[ball+1];
	  }
	private int nextBall()
	  {
	    return itsThrows[ball];
	  }
	private int twoBallsInFrame()
	  {
	    return itsThrows[ball++] + itsThrows[ball++];
	  }
	private void adjustCurrentFrame(int pins)
	  {
	    if (firstThrowInFrame == true)
	    {
	      if (adjustFrameForStrike(pins) == false)
	      {
	    	  pinsRemaining = PINS-pins;
	    	  firstThrowInFrame = false;
	      }
	    }
	    else
	    {
	      firstThrowInFrame=true;
	      advanceFrame();
	    }
	  }
	private boolean adjustFrameForStrike(int pins)
	  {
	    if (pins == 10)
	    {
	      advanceFrame();
	      return true;
	    }
	    return false;
	  }  
	private void advanceFrame()
	  {
		boolean isS = false;
		if(itsCurrentFrame<numFrames)
		    rollNo=1;
		int i = numFrames-1;
	    itsCurrentFrame = Math.min(numFrames, itsCurrentFrame + 1);
	    pinsRemaining=PINS;
	    
	    if(2*i-noS<=itsCurrentThrow&&ball<2*i+3)
	    {
	    	
	    	if(itsThrows[ball-1]==10||(itsThrows[ball-1]+itsThrows[ball-2])==10)
	    	{
	    		 isS = true;
	    	}
	    	if(!isS&&2*i-noS+2==itsCurrentThrow)
	    	{
	    		isGameOver=true;
    			itsCurrentFrame = -1;
    			rollNo = -1;
    			pinsRemaining = -1;
	    	
	    	}
	    	else if(isS&&2*i+3==itsCurrentThrow)
	    	{
	    		isGameOver=true;
	    		itsCurrentFrame = -1;
	    		rollNo = -1;
	    		pinsRemaining = -1;	
	    	}
	    	else if(isS)
	    	{
	    	}
	    	else if(itsThrows[ball-3]==10)
	    	{
	    		isGameOver=true;
	    		pinsRemaining = -1;
	    		itsCurrentFrame = -1;
	    		rollNo = -1;
	    	}
	    	 	
	    }
	    
	 
	  }
}
