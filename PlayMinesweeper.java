import java.util.Scanner;

public class PlayMinesweeper{
  public static void main(String [] args){
    int h=12,w=20,m=40;
  Minesweeper MS=new Minesweeper(h,w,m);
  MinesweeperSolver MSS=new MinesweeperSolver();
  Scanner sc=new Scanner(System.in);
  boolean gameStat=false;
  boolean firstCycle=true;
  do{
    if(MS.gameLosed()||MS.ceckForWin())gameStat=true;
    System.out.print("\033\143"); //clear screen
    System.out.println("Console Minesweeper By Federico Longhin V2.5\nPress your key combination to reveal your cell, add the letter \"F\" to indicate a flag, type \"exit\" for closing the game.\nYou have "+m+" bomb to find!\n" );
    MS.printField();
    if(!gameStat){
      System.out.print("inserisci il nome della cella: ");
      String[] line={"",""};
      if(!preAutomation){
        line=sc.nextLine().split(" ");
      }
      notKeyword=true;
      pre1Done=false;
      pre2Done=false;
      if(line[0].equalsIgnoreCase("exit")){
        notKeyword=false;
        break;
      }
       if(line[0].equalsIgnoreCase("prea")){
        notKeyword=false;
        preAutomation=true;
      }
       if(line[0].equalsIgnoreCase("pre1")||preAutomation){
        notKeyword=false;
        for(int i=0;i<h;i++){
          for (int j=0;j<w;j++){
            if(MSS.predictCell(i,j,MS)==1){
              MS.toggleCell(i,j,true);
              MS.printField();
              pre1Done=true;
            }
          }
        }
      }
       if(line[0].equalsIgnoreCase("pre2")||preAutomation){
        notKeyword=false;
        for(int i=0;i<h;i++){
          for (int j=0;j<w;j++){
            int val=MSS.predictNearCell(i,j,MS);
            //System.out.println(val);
            if(val!=-1){
              if(val>=0){//flag type
                MS.toggleCell(val%MS.MOLT_CONST,val/MS.MOLT_CONST,false);
                //System.out.println("toggling: "+val%MS.MOLT_CONST+" "+val/MS.MOLT_CONST );
              }
              else{
                val++;
                val=val*-1;
                MS.toggleCell(val%MS.MOLT_CONST,val/MS.MOLT_CONST,true);
                //System.out.println("toggling2: "+val%MS.MOLT_CONST+" "+val/MS.MOLT_CONST );
              }
              pre2Done=true;
            }
          }
        }
      //System.out.println(MSS.predictCell(Integer.parseInt(line[1]),Integer.parseInt(line[2]),MS));
      }
      if(notKeyword){
        MS.toggleCellByName(line,firstCycle);
        firstCycle=false;
      }
    }
    if(!(pre1Done||pre2Done))preAutomation=false;
    if(preAutomation)try {Thread.sleep(100);}
    catch(InterruptedException ex){
    Thread.currentThread().interrupt();
    }
    }
  while(!gameStat);
  if(!MS.ceckForWin())System.out.println("HAI PERSO");
else System.out.println("HAI VINTO");
 }
  
  

  static boolean notKeyword=true;
  static boolean preAutomation=false;
  static boolean pre1Done,pre2Done;

}
