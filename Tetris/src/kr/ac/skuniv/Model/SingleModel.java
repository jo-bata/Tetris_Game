package kr.ac.skuniv.Model;

import java.util.Random;

public class SingleModel {
	/***************************************************************/
	// �������
	private int nowBlock;  // ���� �� : LongBlock, TBlock, SquareBlock, SBlock, ZBlock, LBlock, JBlock
	private int nextBlock;  // ���� ��
	private int blockNum = 0;  // �� ����
	private int boardX = 10;  // �迭  10
	private int boardY = 20;  // �迭  20
	private int blockSize = 39;  // �� ������
	private int startBlockX = 401;  // �� x ���� ��ǥ
	private int startBlockY = 63;  // �� y ���� ��ǥ
	private int nowBlockX = 401 + (blockSize * 4);  // ���� �� x ��ǥ
	private int nowBlockY = 63;  // ���� �� y ��ǥ
	private int turnNum = 0;  // ���� ȸ�� ��
	private int startRow = 4;  // �� ������ (401 + (39 * 4))
	private int startCol = 0;  // �� ������ (63)
	private int nowRow = 4;  // ���� ��
	private int nowCol = 0;  // ���� ��
	private int checkSpace = 0;  // �����̽� üũ
	private int gameStage = 1;  // ��������
	private int gameScore = 0;  // ���ھ�
	private boolean isOver = false;  // ���� ���� üũ
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
	public SingleModel(SoundModel soundModel) {
		this.soundModel = soundModel;
	}
	/***************************************************************/
	// getter & setter
	public int getNowBlock() { return nowBlock; }
	public void setNowBlock(int nowBlock) { this.nowBlock = nowBlock; }
	public int getNextBlock() { return nextBlock; }
	public void setNextBlock(int nextBlock) { this.nextBlock = nextBlock; }
	public int getBlockNum() { return blockNum; }
	public void setBlockNum(int blockNum) { this.blockNum = blockNum; }
	public int getBoardX() { return boardX; }
	public int getBoardY() { return boardY; }
	public int getBlockSize() {	return blockSize; }
	public int getStartBlockX() { return startBlockX; }
	public int getStartBlockY() { return startBlockY; }
	public int getNowBlockX() { return nowBlockX; }
	public void setNowBlockX(int nowBlockX) { this.nowBlockX = nowBlockX; }
	public int getNowBlockY() { return nowBlockY; }
	public void setNowBlockY(int nowBlockY) { this.nowBlockY = nowBlockY; }
	public int getTurnNum() { return turnNum; }
	public void setTurnNum(int turnNum) {
		if(turnNum == 4)
			this.turnNum = 0;
		else
			this.turnNum = turnNum;
	}
	public int getStartRow() { return startRow;	}
	public int getStartCol() { return startCol;	}
	public int getNowRow() { return nowRow; }
	public void setNowRow(int nowRow) {	this.nowRow = nowRow; }
	public int getNowCol() { return nowCol; }
	public void setNowCol(int nowCol) { this.nowCol = nowCol; }
	public int getCheckSpace() { return checkSpace; }
	public void setCheckSpace(int checkSpace) { this.checkSpace = checkSpace; }
	public int getGameStage() {	return gameStage; }
	public void setGameStage(int gameStage) { this.gameStage = gameStage; }
	public int getGameScore() {	return gameScore; }
	public void setGameScore(int gameScore) { this.gameScore = gameScore; }
	public boolean getIsOver() { return isOver;	}
	public void setIsOver(boolean isOver) {	this.isOver = isOver; }
	public int[][] getBlockFirstX() { return blockFirstX; }
	public int[][] getBlockFirstY() { return blockFirstY;}
	public int[][] getBlockLastX() { return blockLastX; }
	public int[][] getBlockLastY() { return blockLastY;	}
	public int[][] getGameBoard() { return gameBoard; }
	public int[][] getGameBoard2() { return gameBoard2; }
	public void setGameBoard(int[][] gameBoard) { this.gameBoard = gameBoard; }
	public void setGameBoard(int nowCol, int nowRow, int nowBlock, int turnNum) {
		for(int i = blockFirstY[nowBlock][turnNum]; i <= blockLastY[nowBlock][turnNum]; i++)
			for(int j = blockFirstX[nowBlock][turnNum]; j <= blockLastX[nowBlock][turnNum]; j++)
				if(blockArr[turnNum][nowBlock][i][j] > 0)
					if((nowCol + i >= 0 && nowCol + i <= 19) && (nowRow + j >= 0 && nowRow + j <= 9))
						gameBoard[nowCol + i][nowRow + j] = blockArr[turnNum][nowBlock][i][j];
	}
	public int[][][][] getBlockArr() { return blockArr; }
	/***************************************************************/
	// ����, ���� �� �������� �����ϴ� �޼ҵ�
	public void setRandomBlock() {
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
	public void checkBlockLast(int nowBlock, int turnNum) {
		if(!isMove(nowCol, nowRow, nowBlock, turnNum)) {
			setGameBoard(nowCol, nowRow, nowBlock, turnNum);
			printGameBoard();
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
		else if(nowCol < startCol + (blockSize * (boardY - 1))) {
			setNowBlockY(nowBlockY + blockSize);
			setNowCol(nowCol + 1);
		}
	}
	/***************************************************************/
	// ���� ������ �� �ִ��� üũ�ϴ� �޼ҵ�
	public boolean isMove(int nowCol, int nowRow, int nowBlock, int turnNum) {
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
	public boolean isMove(int nowCol, int nowRow, int nowBlock, int turnNum, int key) {
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
	public void checkLine() {
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
		gameStage = (gameScore / 1000) + 1;
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
	public void printGameBoard() {
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 10; j++)
				System.out.print(gameBoard[i][j]);
			System.out.println();
		}
	}
	/***************************************************************/
	// isOver �޼ҵ� : ���� ���� üũ
	public boolean isOver() {
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
