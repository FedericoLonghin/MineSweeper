import java.util.Scanner;
public class PlayMinesweeper{
  public static void main(String [] args){
    int w=12,h=20,m=40;
  Minesweeper MS=new Minesweeper(h,w,m);
  Scanner sc=new Scanner(System.in);
  MS.generateMines();
  MS.generateNumbers();
  boolean gameStat=false;
  do{
    if(MS.gameLosed()||MS.ceckForWin())gameStat=true;
    System.out.print("\033\143"); //clear screen
    System.out.println("Console Minesweeper By Federico Longhin V1.2\nPress your key combination to reveal your cell, add the letter \"F\" to indicate a flag, type \"exit\" for closing the game.\nYou have "+m+" bomb to find!\n" );
    MS.printField();
    if(!gameStat){


    System.out.print("inserisci una coppia di cordinate (riga e colonna): ");
    String[] line= sc.nextLine().split(" ");
    if(line[0].equalsIgnoreCase("exit"))break;
    MS.toggleCellByName(line);
    }
 }
  while(!gameStat);
  if(MS.gameLosed())System.out.println("HAI PERSO");
else System.out.println("HAI VINTO");
 }
  
  

  
}
