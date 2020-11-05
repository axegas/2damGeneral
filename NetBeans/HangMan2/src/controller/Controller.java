/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.ImageIcon;

/**
 *
 * @author axegas
 */
public final class Controller {

    private ImageIcon[] images;
    private int index;
    private String wordToGuess;
    private String wordResult;
    private boolean isLetter;
    private int result;

    public Controller() {
        start();
    }

    public void start() {
        index = -1;
        result = 0;
        isLetter = false;
        images = new ImageIcon[5];
        for (int i = 0; i < images.length; i++) {
            images[i] = new ImageIcon(getClass().getResource("/images/foto" + i + ".png"));
        }
    }

    public String firstPlayer(char[] wordToGuessArray) {
        wordResult = "";
        wordToGuess = "";
        for (int i = 0; i < wordToGuessArray.length; i++) {
            wordResult += "-";
            wordToGuess += wordToGuessArray[i];
        }
        wordToGuess = wordToGuess.toUpperCase();
        return wordResult;
    }

    public String secondPlayer(char c) {
        isLetter = false;
        result = 0;
        String aux = "";
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == c) {
                isLetter = true;
                aux += c;
            } else {
                aux += wordResult.charAt(i);
            }
        }
        wordResult = aux;
        setResult();      
        return wordResult;
    }

    public ImageIcon nextImage() {
        index++;
        return images[index];
    }
    
    public int getResult(){
        return result;
    }

    private void setResult() {
         if (isLetter) {
            if (wordResult.equals(wordToGuess)) {
                result = 1;
            } 
        } else {
            if(index != 3){
                result = 2;
            }else{
                result = 3;
            }
        }
    }
}
