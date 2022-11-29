import java.util.Scanner;
public class PlayMinesweeper{
  public static void main(String [] args){
    int h=20,w=12,m=40;
  Minesweeper MS=new Minesweeper(h,w,m);
  MinesweeperSolver MSS=new MinesweeperSolver();
  Scanner sc=new Scanner(System.in);
  boolean gameStat=false;
  boolean firstCycle=true;
  System.out.println("flagnumber: "+MS.getNumberFlag(19,11));
  do{
    if(MS.gameLosed()||MS.ceckForWin())gameStat=true;
  // System.out.print("\033\143"); //clear screen
    System.out.println("Console Minesweeper By Federico Longhin V2.1\nPress your key combination to reveal your cell, add the letter \"F\" to indicate a flag, type \"exit\" for closing the game.\nYou have "+m+" bomb to find!\n" );
    MS.printField();
    if(!gameStat){
    System.out.print("inserisci una coppia di cordinate (riga e colonna): ");
    String[] line= sc.nextLine().split(" ");
    if(line[0].equalsIgnoreCase("exit"))break;
    else if(line[0].equalsIgnoreCase("pre1")){
      for(int i=0;i<h;i++){
        for (int j=0;j<w;j++){
          if(MSS.predictCell(i,j,MS)==1){
            MS.toggleCell(i,j,true);
            MS.printField();
            }
        }
      }
    }
    else if(line[0].equalsIgnoreCase("pre2")){
      for(int i=0;i<h;i++){
        for (int j=0;j<w;j++){
          int val=MSS.predictNearCell(i,j,MS);
          System.out.println(val);
          if(val!=-1){
            if(val>=0){//flag type
              MS.toggleCell(val%MS.MOLT_CONST,val/MS.MOLT_CONST,false);
            System.out.println("toggling: "+val%MS.MOLT_CONST+" "+val/MS.MOLT_CONST );
            }
            //else{
            //  MS.toggleCell(val/MS.HEIGHT,val%MS.HEIGHT,true);
            // }
          }
        }
      }
    //System.out.println(MSS.predictCell(Integer.parseInt(line[1]),Integer.parseInt(line[2]),MS));
    } 
    else MS.toggleCellByName(line,firstCycle);
     firstCycle=false;
    }
 }
  while(!gameStat);
  if(!MS.ceckForWin())System.out.println("HAI PERSO");
else System.out.println("HAI VINTO");
 }
  
  

  
}
