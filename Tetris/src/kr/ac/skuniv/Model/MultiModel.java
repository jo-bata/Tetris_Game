package kr.ac.skuniv.Model;

import java.util.ArrayList;
import java.util.Random;

public class MultiModel {
	/***************************************************************/
	// �������
	private int nowBlock;  // ���� �� : LongBlock, TBlock, SquareBlock, SBlock, ZBlock, LBlock, JBlock
	private int nextBlock;  // ���� ��
	private int blockNum = 0;  // �� ����
	private int boardX = 10;  // �迭  10
	private int boardY = 20;  // �迭  20
	private int blockSize = 37;  // �� ������
	private int startBlockX = 630;  // �� x ���� ��ǥ
	private int startBlockY = 140;  // �� y ���� ��ǥ
	private int nowBlockX = 630 + (blockSize * 4);  // ���� �� x ��ǥ
	private int nowBlockY = 140;  // ���� �� y ��ǥ
	private int turnNum = 0;  // ���� ȸ�� ��
	private int startRow = 4;  // �� ������ (401 + (39 * 4))
	private int startCol = 0;  // �� ������ (63)
	private int nowRow = 4;  // ���� ��
	private int nowCol = 0;  // ���� ��
	private int checkSpace = 0;  // �����̽� üũ
	private boolean isStart = false;  // ��ŸƮ üũ
	private boolean isOver = false;  // ���ӿ��� üũ
	private int gameScore = 0;  // ���� ���ھ�
	private int[][] gameBoard = new int[boardY][boardX];  // ��Ʈ���� ������ �迭
	private int[][] gameBoard2 = new int[boardY][boardX];  // ��Ʈ���� ������ �迭 ����
	private int[][][][] blockArr =  // [ȸ����][������][��][��]  // �� �迭
		{
			/***************************************************************/
			// Block �ʱ����
			{
				{{0, 1, 0, 0}, 
				 {0, 1, 0, 0},
				 {0, 1, 0, 0},
				 {0, 1, 0, 0}},  // LongBlock
				{{0, 0, 2, 0},
			     {0, 2, 2, 2},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // TBlock
				{{0, 3, 3, 0},
			     {0, 3, 3, 0},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // SquareBlock
				{{0, 4, 0, 0},
			     {0, 4, 4, 0},
			     {0, 0, 4, 0},
			     {0, 0, 0, 0}},  // SBlock
				{{0, 0, 5, 0},
			     {0, 5, 5, 0},
			     {0, 5, 0, 0},
			     {0, 0, 0, 0}},  // ZBlock
				{{0, 6, 0, 0},
			     {0, 6, 0, 0},
			     {0, 6, 6, 0},
			     {0, 0, 0, 0}},  // LBlock
				{{0, 0, 7, 0},
			     {0, 0, 7, 0},
			     {0, 7, 7, 0},
			     {0, 0, 0, 0}}   // JBlock 
			},
			/***************************************************************/
			// Block 1ȸ�� ����
			{
				{{1, 1, 1, 1}, 
				 {0, 0, 0, 0},
				 {0, 0, 0, 0},
				 {0, 0, 0, 0}},  // LongBlock
				{{0, 2, 0, 0},
			     {0, 2, 2, 0},
			     {0, 2, 0, 0},
			     {0, 0, 0, 0}},  // TBlock
				{{0, 3, 3, 0},
			     {0, 3, 3, 0},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // SquareBlock
				{{0, 0, 4, 4},
			     {0, 4, 4, 0},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // SBlock
				{{0, 5, 5, 0},
			     {0, 0, 5, 5},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // ZBlock
				{{0, 6, 6, 6},
			     {0, 6, 0, 0},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // LBlock
				{{0, 7, 0, 0},
			     {0, 7, 7, 7},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}}   // JBlock 
			},
			/***************************************************************/
			// Block 2ȸ�� ����
			{
				{{0, 1, 0, 0}, 
				 {0, 1, 0, 0},
				 {0, 1, 0, 0},
				 {0, 1, 0, 0}},  // LongBlock
				{{0, 2, 2, 2},
			     {0, 0, 2, 0},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // TBlock
				{{0, 3, 3, 0},
			     {0, 3, 3, 0},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // SquareBlock
				{{0, 4, 0, 0},
			     {0, 4, 4, 0},
			     {0, 0, 4, 0},
			     {0, 0, 0, 0}},  // SBlock
				{{0, 0, 5, 0},
			     {0, 5, 5, 0},
			     {0, 5, 0, 0},
			     {0, 0, 0, 0}},  // ZBlock
				{{0, 6, 6, 0},
			     {0, 0, 6, 0},
			     {0, 0, 6, 0},
			     {0, 0, 0, 0}},  // LBlock
				{{0, 7, 7, 0},
			     {0, 7, 0, 0},
			     {0, 7, 0, 0},
			     {0, 0, 0, 0}}   // JBlock 
			},
			/***************************************************************/
			// Block 3ȸ�� ����
			{
				{{1, 1, 1, 1}, 
				 {0, 0, 0, 0},
				 {0, 0, 0, 0},
				 {0, 0, 0, 0}},  // LongBlock
				{{0, 0, 2, 0},
			     {0, 2, 2, 0},
			     {0, 0, 2, 0},
			     {0, 0, 0, 0}},  // TBlock
				{{0, 3, 3, 0},
			     {0, 3, 3, 0},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // SquareBlock
				{{0, 0, 4, 4},
			     {0, 4, 4, 0},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // SBlock
				{{0, 5, 5, 0},
			     {0, 0, 5, 5},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // ZBlock
				{{0, 0, 0, 6},
			     {0, 6, 6, 6},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}},  // LBlock
				{{0, 7, 7, 7},
			     {0, 0, 0, 7},
			     {0, 0, 0, 0},
			     {0, 0, 0, 0}}   // JBlock 
			}
		};
	private int[][] blockFirstX =  // �� �� ù x
		{
				{ 1, 0, 1, 0 },
				{ 1, 1, 1, 1 },
				{ 1, 1, 1, 1 },
				{ 1, 1, 1, 1 },
				{ 1, 1, 1, 1 },
				{ 1, 1, 1, 1 },
				{ 1, 1, 1, 1 }
		};
	private int[][] blockFirstY =  // �� �� ù y
		{
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 }
		};
	private int[][] blockLastX =  // �� �� ������ x
		{
				{ 1, 3, 1, 3 },
				{ 3, 2, 3, 2 },
				{ 2, 2, 2, 2 },
				{ 2, 3, 2, 3 },
				{ 2, 3, 2, 3 },
				{ 2, 3, 2, 3 },
				{ 2, 3, 2, 3 }
		};
	private int[][] blockLastY =  // �� �� ������ y
		{
				{ 3, 0, 3, 0 },
				{ 1, 2, 1, 2 },
				{ 1, 1, 1, 1 },
				{ 2, 1, 2, 1 },
				{ 2, 1, 2, 1 },
				{ 2, 1, 2, 1 },
				{ 2, 1, 2, 1 }
		};
	SoundModel soundModel;
	public MultiModel(SoundModel soundModel) {
		this.soundModel = soundModel;
	}
	/***************************************************************/
	// ���� ������ �ʵ�
	private int startBlockX2 = 205;
	private int nowBlock2 = 0;
	private int nextBlock2 = 0;
	private int turnNum2 = 0;
	private int nowRow2 = 4;
	private int nowCol2 = 0;
	private int gameScore2 = 0;
	private int[][] gameBoard3 = new int[20][10];
	public int getStartBlockX2() { return startBlockX2;	}
	public void setStartBlockX2(int startBlockX2) {	this.startBlockX2 = startBlockX2; }
	public int getNowBlock2() {	return nowBlock2; }
	public void setNowBlock2(Object nowBlock2) { this.nowBlock2 = Integer.parseInt((nowBlock2.toString())); }
	public int getNextBlock2() { return nextBlock2;	}
	public void setNextBlock2(Object nextBlock2) {	this.nextBlock2 = Integer.parseInt((nextBlock2.toString())); }
	public int getTurnNum2() { return turnNum2;	}
	public void setTurnNum2(Object turnNum2) {	this.turnNum2 = Integer.parseInt((turnNum2.toString())); }
	public int getNowRow2() { return nowRow2; }
	public void setNowRow2(Object nowRow2) { this.nowRow2 = Integer.parseInt((nowRow2.toString())); }
	public int getNowCol2() { return nowCol2; }
	public void setNowCol2(Object nowCol2) { this.nowCol2 = Integer.parseInt((nowCol2.toString())); }
	public int getGameScore2() { return gameScore2; }
	public void setGameScore2(Object gameScore2) { this.gameScore2 = Integer.parseInt((gameScore2.toString())); }
	public int[][] getGameBoard3() { return gameBoard3; }
	public void setGameBoard3(ArrayList<Integer> gameBoard3) {
		int x = 0;
		for(int i = 0; i < boardY; i++)
			for(int j = 0; j < boardX; j++)
				this.gameBoard3[i][j] = gameBoard3.get(x++);
	}
	/***************************************************************/
	// getter & setter
	synchronized public int getNowBlock() { return nowBlock; }
	synchronized public void setNowBlock(int nowBlock) { this.nowBlock = nowBlock; }
	synchronized public int getNextBlock() { return nextBlock; }
	synchronized public void setNextBlock(int nextBlock) { this.nextBlock = nextBlock; }
	synchronized public int getBlockNum() { return blockNum; }
	synchronized public void setBlockNum(int blockNum) { this.blockNum = blockNum; }
	synchronized public int getBoardX() { return boardX; }
	synchronized public int getBoardY() { return boardY; }
	synchronized public int getBlockSize() {	return blockSize; }
	synchronized public int getStartBlockX() { return startBlockX; }
	synchronized public int getStartBlockY() { return startBlockY; }
	synchronized public int getNowBlockX() { return nowBlockX; }
	synchronized public void setNowBlockX(int nowBlockX) { this.nowBlockX = nowBlockX; }
	synchronized public int getNowBlockY() { return nowBlockY; }
	synchronized public void setNowBlockY(int nowBlockY) { this.nowBlockY = nowBlockY; }
	synchronized public int getTurnNum() { return turnNum; }
	synchronized public void setTurnNum(int turnNum) {
		if(turnNum == 4)
			this.turnNum = 0;
		else
			this.turnNum = turnNum;
	}
	synchronized public int getStartRow() { return startRow;	}
	synchronized public int getStartCol() { return startCol;	}
	synchronized public int getNowRow() { return nowRow; }
	synchronized public void setNowRow(int nowRow) {	this.nowRow = nowRow; }
	synchronized public int getNowCol() { return nowCol; }
	synchronized public void setNowCol(int nowCol) { this.nowCol = nowCol; }
	synchronized public int getCheckSpace() { return checkSpace; }
	synchronized public void setCheckSpace(int checkSpace) { this.checkSpace = checkSpace; }
	synchronized public boolean getIsStart() { return isStart; }
	synchronized public void setIsStart(boolean isStart) {	this.isStart = isStart;	}
	synchronized public boolean getIsOver() { return isOver; }
	synchronized public void setIsOver(boolean isOver) {	this.isOver = isOver; }
	synchronized public int getGameScore() { return gameScore; }
	synchronized public void setGameScore(int gameScore) { this.gameScore = gameScore; }
	synchronized public int[][] getBlockFirstX() { return blockFirstX; }
	synchronized public int[][] getBlockFirstY() { return blockFirstY;}
	synchronized public int[][] getBlockLastX() { return blockLastX; }
	synchronized public int[][] getBlockLastY() { return blockLastY;	}
	synchronized public int[][] getGameBoard() { return gameBoard; }
	synchronized public int[][] getGameBoard2() { return gameBoard2; }
	synchronized public void setGameBoard(int[][] gameBoard) { this.gameBoard = gameBoard; }
	synchronized public void setGameBoard(int nowCol, int nowRow, int nowBlock, int turnNum) {
		for(int i = blockFirstY[nowBlock][turnNum]; i <= blockLastY[nowBlock][turnNum]; i++)
			for(int j = blockFirstX[nowBlock][turnNum]; j <= blockLastX[nowBlock][turnNum]; j++)
				if(blockArr[turnNum][nowBlock][i][j] > 0)
					if((nowCol + i >= 0 && nowCol + i <= 19) && (nowRow + j >= 0 && nowRow + j <= 9))
						gameBoard[nowCol + i][nowRow + j] = blockArr[turnNum][nowBlock][i][j];
	}
	synchronized public int[][][][] getBlockArr() { return blockArr; }
	/***************************************************************/
	// ����, ���� �� �������� �����ϴ� �޼ҵ�
	synchronized public void setRandomBlock() {
		if(blockNum == 0) {
			Random r = new Random();
			nowBlock = Math.abs(r.nextInt()) % 7;
			nextBlock = Math.abs(r.nextInt()) % 7;
			blockNum = 1;
			turnNum = 0;
		}
		else {
			nowBlock = nextBlock;
			Random r = new Random();
			nextBlock = Math.abs(r.nextInt()) % 7;
			blockNum++;
			turnNum = 0;
		}
	}
	/***************************************************************/
	// ���� �����̴��� ���������� üũ�ϴ� �޼ҵ�
	synchronized public void checkBlockLast(int nowBlock, int turnNum) {
		if(!isMove(nowCol, nowRow, nowBlock, turnNum)) {
			setGameBoard(nowCol, nowRow, nowBlock, turnNum);
			checkLine();
			setCheckSpace(0);
			setRandomBlock();
			setNowBlockX(startBlockX + (blockSize * 4));
			setNowRow(startRow);
			setNowBlockY(startBlockY);
			setNowCol(startCol);
			if(isOver())
				setIsOver(true);
		}	
		else if(nowCol < startCol + (blockSize * (boardY - 1)) && isStart == true) {
			setNowBlockY(nowBlockY + blockSize);
			setNowCol(nowCol + 1);
		}
	}
	/***************************************************************/
	// ���� ������ �� �ִ��� üũ�ϴ� �޼ҵ�
	synchronized public boolean isMove(int nowCol, int nowRow, int nowBlock, int turnNum) {
		for(int i = blockFirstY[nowBlock][turnNum]; i <= blockLastY[nowBlock][turnNum]; i++)
			for(int j = blockFirstX[nowBlock][turnNum]; j <= blockLastX[nowBlock][turnNum]; j++)
				if(blockArr[turnNum][nowBlock][i][j] > 0)
					if((nowCol + i + 1 > 19) || (nowRow + j < 0) || (nowRow + j > 9))
						return false;
					else if(gameBoard[nowCol + i + 1][nowRow + j] > 0)
						return false;
		return true;
	}
	/***************************************************************/
	// ���� ������ �� �ִ��� üũ�ϴ� �޼ҵ�
	synchronized public boolean isMove(int nowCol, int nowRow, int nowBlock, int turnNum, int key) {
		switch(key) {
			case 38:  // up
				if(turnNum < 3) {
					for(int i = blockFirstY[nowBlock][turnNum + 1]; i <= blockLastY[nowBlock][turnNum + 1]; i++)
						for(int j = blockFirstX[nowBlock][turnNum + 1]; j <= blockLastX[nowBlock][turnNum + 1]; j++)
							if(blockArr[turnNum + 1][nowBlock][i][j] > 0)
								if(nowRow + j < 0 || nowRow + j > 9)
									return false;
								else if(gameBoard[nowCol + i][nowRow + j] > 0) {
									return false;
								}
				}
				else {
					for(int i = blockFirstY[nowBlock][0]; i <= blockLastY[nowBlock][0]; i++)
						for(int j = blockFirstX[nowBlock][0]; j <= blockLastX[nowBlock][0]; j++)
							if(blockArr[0][nowBlock][i][j] > 0)
								if(nowRow + j < 0 || nowRow + j > 9)
									return false;
								else if(gameBoard[nowCol + i][nowRow + j] > 0)
									return false;
				}
				break;
			case 37:  // left
				for(int i = blockFirstY[nowBlock][turnNum]; i <= blockLastY[nowBlock][turnNum]; i++)
					for(int j = blockFirstX[nowBlock][turnNum]; j <= blockLastX[nowBlock][turnNum]; j++)
						if(blockArr[turnNum][nowBlock][i][j] > 0)
							if(nowRow + j - 1 < 0)
								return false;
							else if(gameBoard[nowCol + i][nowRow + j - 1] > 0)
								return false;
				break;
			case 39:  // right
				for(int i = blockFirstY[nowBlock][turnNum]; i <= blockLastY[nowBlock][turnNum]; i++)
					for(int j = blockFirstX[nowBlock][turnNum]; j <= blockLastX[nowBlock][turnNum]; j++)
						if(blockArr[turnNum][nowBlock][i][j] > 0)
							if(nowRow + j + 1 > 9)
								return false;
							else if(gameBoard[nowCol + i][nowRow + j + 1] > 0)
								return false;
				break;
			case 40:  // down
				for(int i = blockFirstY[nowBlock][turnNum]; i <= blockLastY[nowBlock][turnNum]; i++)
					for(int j = blockFirstX[nowBlock][turnNum]; j <= blockLastX[nowBlock][turnNum]; j++)
						if(blockArr[turnNum][nowBlock][i][j] > 0)
							if(nowCol + i + 1 > 19 || (nowRow + j < 0) || (nowRow + j > 9))
								return false;
							else if(gameBoard[nowCol + i + 1][nowRow + j] > 0)
								return false;
				break;
		}
		return true;
	}
	/***************************************************************/
	// ���� Ŭ���� üũ �޼ҵ�
	synchronized public void checkLine() {
		int cnt[] = new int[20];
		int clearLine = 20;
		for(int i = boardY - 1; i >= 0; i--)
			for(int j = 0; j < boardX; j++)
				if(gameBoard[i][j] == 0) {
					cnt[i] = 1;
					continue;
				}
		for(int i = 0; i < boardY; i++)
			clearLine -= cnt[i];
		if(clearLine > 0)
			soundModel.clearBlockPlay();
		gameScore += clearLine * 100;
		if(clearLine < 1){
			for(int i = 0; i < boardY; i++)
				for(int j = 0; j < boardX; j++)
					gameBoard2[i][j] = gameBoard[i][j];
		}
		else {
			for(int i = 0; i < boardY; i++)
				for(int j = 0; j < boardX; j++) {
					if(i < clearLine)
						gameBoard2[i][j] = 0;
					else {
						if(cnt[i - clearLine] == 1)
							gameBoard2[i][j] = gameBoard[i - clearLine][j];
						else {
							if(clearLine <=0) {
								clearLine = 0;
							}
							else {
								clearLine -= 1;
								i--;
							}
						}
					}
				}
		}
		for(int i = 0; i < boardY; i++)
			for(int j = 0; j < boardX; j++)
				gameBoard[i][j] = gameBoard2[i][j];
	}
	/***************************************************************/
	// ��Ʈ���� ������ �ܼ�â ����Ʈ�ϴ� �޼ҵ�
	synchronized public void printGameBoard() {
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 10; j++)
				System.out.print(gameBoard[i][j]);
			System.out.println();
		}
	}
	/***************************************************************/
	// isOver �޼ҵ� : ���� ���� üũ
	synchronized public boolean isOver() {
		int[][] board = new int[boardY][boardX];
		int cnt = 0;
		for(int i = 0; i < boardY; i++)
			for(int j = 0; j < boardX; j++)
				board[i][j] = gameBoard[i][j];
		for(int i = blockFirstY[nowBlock][turnNum]; i <= blockLastY[nowBlock][turnNum]; i++)
			for(int j = blockFirstX[nowBlock][turnNum]; j <= blockLastX[nowBlock][turnNum]; j++)
				if(blockArr[turnNum][nowBlock][i][j] > 0)
					if((nowCol + i >= 0 && nowCol + i <= 19) && (nowRow + j >= 0 && nowRow + j <= 9))
						board[nowCol + i][nowRow + j] = blockArr[turnNum][nowBlock][i][j];
		for(int i = 0; i < 10; i++) {
			if(cnt == 5)
				return true;
			else
				cnt = 0;
			for(int j = 0; j < 5; j++) {
				if(board[j][i] > 0)
					cnt++;
			}
		}
		return false;
	}
}
