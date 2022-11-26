import java.util.Scanner;
public class PlayMinesweeper{
  public static void main(String [] args){
    int w=12,h=20,m=40;
  Minesweeper MS=new Minesweeper(h,w,m);
  Scanner sc=new Scanner(System.in);
  MS.generateMines();
  MS.generateNumbers();
  do{
    System.out.print("\033\143"); //clear screen
    System.out.println("Console Minesweeper By Federico Longhin V1.1\nPress your key combination to reveal your cell, add the letter \"F\" to indicate a flag, type \"exit\" for closing the game.\nYou have "+m+" bomb to find!\n" );
    MS.printField();
    System.out.print("inserisci una coppia di cordinate (riga e colonna): ");
    String[] line= sc.nextLine().split(" ");
    if(line[0].equalsIgnoreCase("exit"))break;
    MS.toggleCellByName(line);
  
  }
  while(!MS.gameLosed());
  System.out.println("HAI PERSO");
  }
}
