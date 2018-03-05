package kr.ac.skuniv.Model;

public class ServerModel {
	/***************************************************************/
	// 멤버 필드
	private int startState = 0;  // 0 : 접속중, 1 : 꽉참 -> 게임스타트  2, 2 : 더이상 받지 않음 
	private boolean nameState = false;  // userName 상태
	private int boardX = 10;  // 10열
	private int boardY = 20;  // 20행
	/***************************************************************/
	// User 1 data
	private String userName1;
	private int nowBlock1;
	private int nextBlock1;
	private int turnNum1;
	private int nowRow1;
	private int nowCol1;
	private int[][] gameBoard1 = new int[20][10];
	/***************************************************************/
	// User 2 data
	private String userName2;
	private int nowBlock2;
	private int nextBlock2;
	private int turnNum2;
	private int nowRow2;
	private int nowCol2;
	private int[][] gameBoard2 = new int[20][10];
	/***************************************************************/
	// getter & setter
	public int getStartState() { return startState; }
	public void setStartState(int startState) { this.startState = startState; }
	public boolean getNameState() { return nameState; }
	public void setNameState(boolean nameState) { this.nameState = nameState; }
	public int getBoardX() { return boardX; }
	public int getBoardY() { return boardY; }
	public String getUserName1() { return userName1; }
	public void setUserName1(String userName1) { this.userName1 = userName1; }
	public int getNowBlock1() {	return nowBlock1; }
	public void setNowBlock1(int nowBlock1) { this.nowBlock1 = nowBlock1; }
	public int getNextBlock1() { return nextBlock1; }
	public void setNextBlock1(int nextBlock1) {	this.nextBlock1 = nextBlock1; }
	public int getTurnNum1() { return turnNum1;	}
	public void setTurnNum1(int turnNum1) {	this.turnNum1 = turnNum1; }
	public int getNowRow1() { return nowRow1; }
	public void setNowRow1(int nowRow1) { this.nowRow1 = nowRow1; }
	public int getNowCol1() { return nowCol1; }
	public void setNowCol1(int nowCol1) { this.nowCol1 = nowCol1; }
	public int[][] getGameBoard1() { return gameBoard1; }
	public void setGameBoard1(int[][] gameBoard1) {
		for(int i = 0; i < boardY; i++)
			for(int j = 0; j < boardX; j++)
				this.gameBoard1[i][j] = gameBoard1[i][j];
	}
	public String getUserName2() { return userName2; }
	public void setUserName2(String userName2) { this.userName2 = userName2; }
	public int getNowBlock2() { return nowBlock2; }
	public void setNowBlock2(int nowBlock2) { this.nowBlock2 = nowBlock2; }
	public int getNextBlock2() { return nextBlock2;	}
	public void setNextBlock2(int nextBlock2) {	this.nextBlock2 = nextBlock2; }
	public int getTurnNum2() { return turnNum2;	}
	public void setTurnNum2(int turnNum2) {	this.turnNum2 = turnNum2; }
	public int getNowRow2() { return nowRow2; }
	public void setNowRow2(int nowRow2) { this.nowRow2 = nowRow2; }
	public int getNowCol2() { return nowCol2; }
	public void setNowCol2(int nowCol2) { this.nowCol2 = nowCol2; }
	public int[][] getGameBoard2() { return gameBoard2; }
	public void setGameBoard2(int[][] gameBoard2) {
		for(int i = 0; i < boardY; i++)
			for(int j = 0; j < boardX; j++)
				this.gameBoard2[i][j] = gameBoard2[i][j];
	}
	/***************************************************************/
	// 메소드
}
