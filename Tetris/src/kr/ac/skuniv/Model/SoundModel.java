package kr.ac.skuniv.Model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundModel {
	/***************************************************************/
	// ¸â¹öº¯¼ö
	AudioInputStream ais;
	Clip clip;
	/***************************************************************/
	// intro bgm
	public void introBgmPlay() {
		try {
			ais = AudioSystem.getAudioInputStream(new File("sound/bgm.wav"));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/***************************************************************/
	// intro bgm stop
	public void intoBgmStop() {
		clip.stop();
	}
	/***************************************************************/
	// victory
	public void victoryPlay() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/victory.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/***************************************************************/
	// lose
	public void losePlay() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/lose.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/***************************************************************/
	// menu click
	public void menuClickPlay() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/menu_click.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/***************************************************************/
	// move block
	public void moveBlockPlay() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/move_block.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/***************************************************************/
	// clear block
	public void clearBlockPlay() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/clear_block.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
