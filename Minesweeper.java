import java.util.Random;
public class Minesweeper{
  public Minesweeper(int HEIGHT, int WIDTH, int MINE_NUMBER){
    this.HEIGHT=HEIGHT;
    this.WIDTH=WIDTH;
    this.MINE_NUMBER=MINE_NUMBER;
    field=new int[HEIGHT+2][WIDTH+2];
    fieldVis=new boolean[HEIGHT][WIDTH];
  }
  
  public void firstToggle(int i, int j){
    do{
      field=new int[HEIGHT+2][WIDTH+2];
      generateMines();
      generateNumbers();
    }
    while(field[i+1][j+1]!=0);
    toggleCell(i,j,true);
  }

  public void generateNumbers(){
    for(int i=1;i<HEIGHT+1;i++){
      for (int j=1;j<WIDTH+1;j++){
        if(field[i][j]!=MINE){
          int nearMine=0;
          
          for(int zi=i-1;zi<i+2;zi++){
            for(int zj=j-1;zj<j+2;zj++){
              //System.out.println("Ceck cell: " + i+","+j+" - sub: "+zi+","+zj);
              if( field[zi][zj]==MINE)nearMine++;       
            }
          }
          field[i][j]=nearMine;
        }
      }
    }
  }

  public void generateMines(){
    Random rand=new Random();
    int placedMines=0,i,j;
    while(placedMines<MINE_NUMBER){
      i=rand.nextInt(HEIGHT);
      j=rand.nextInt(WIDTH);
      if(field[i+1][j+1]==0){
        field[i+1][j+1]=MINE;
        placedMines++;
      }
    }
  }

  public void printField(){
    printBar(WIDTH);
    for(int i=0;i<HEIGHT;i++){
      System.out.print("|");
      for(int j=0;j<WIDTH;j++){
        System.out.print(getFieldCell(i,j));
        //if(j<WIDTH-1)System.out.print("  ");
      }
      System.out.println("|");
    }
    printBar(WIDTH);
  }
  
  public void printBar(int width){
    System.out.print(" ");
      for(int i=0;i<width*4;i++){
        System.out.print("-");
      }
      System.out.println();
  }
  
  public String getFieldCell(int i, int j){
    String cell="";
    if(!fieldVis[i][j])cell=getFormattedCellName(i,j);  //default number
    else if(field[i+1][j+1]==MINE)cell="M   ";                //mine
    else if (field[i+1][j+1]==0) cell="    ";
    else if(field[i+1][j+1]<10) cell +="\033[32m"+field[i+1][j+1]+"   \033[0m";
    else cell ="E";
    if(field[i+1][j+1]==FLAG)cell="\033[36m?   \033[0m"; //flag

    if(gameLosed()&&field[i+1][j+1]==MINE)cell="\033[31mM   \033[0m";
    return cell;
  }

 public String getFormattedCellName(int i, int j){
    String name="";
    char ch=(char)(i+65);
    name+=ch;
    name+=j;
    if(name.length()<3)name+=" ";
    if(name.length()<4)name+=" ";
    return name;
  }
  
  public void toggleCell(int i, int j, boolean mode){
    if(!fieldVis[i][j]){
      if(mode==NORMAL_MODE){
        if(field[i+1][j+1]==MINE){
          lose=true;
        }
        expandVisibility(i,j);
      }
      else{ //flag mode
        field[i+1][j+1]=FLAG;      
      }
    }
  }

 public  void toggleCellByName(String []name,boolean firstTry){
    boolean mode=true;
    int i=0,j=0;
    boolean correctFormat=true;
    try{

      if(name.length==2 && name[1].equalsIgnoreCase("F"))mode=false;
      char firstChar=name[0].charAt(0);
      if(firstChar>='A' && firstChar<='Z')i=firstChar-'A';
      else if(firstChar>='a'&&firstChar<='z')i=firstChar-'a';
      else correctFormat=false;
      if(correctFormat){
        j=Integer.parseInt(name[0].substring(1));
      }
    }
    catch(Exception e){
      System.out.println("Errore nell'inserimento dei parametri!");
      correctFormat=false;
    }
    if(correctFormat){
     if(firstTry){
      firstToggle(i,j);
     }
      toggleCell(i,j,mode);
    }
  }
  
 public boolean gameLosed(){
    return lose;
  }
  
  public void expandVisibility(int i, int j){
  //System.out.println("ceck for: "+i+" "+j);
    if(field[i+1][j+1]==0&&!fieldVis[i][j]){
    fieldVis[i][j]=true;  
      if(i>0)expandVisibility(i-1,j);
      if(i<HEIGHT-1)expandVisibility(i+1,j);
      if(j>0)expandVisibility(i,j-1);
      if(j<WIDTH-1)expandVisibility(i,j+1);
    }
       fieldVis[i][j]=true;   
  }
  public boolean ceckForWin(){
    int visCell=0;
    for(int i=0;i<WIDTH*HEIGHT;i++)visCell+=fieldVis[i/WIDTH][i%WIDTH]?1:0;
    if(visCell>=WIDTH*HEIGHT-MINE)return true;
    return false;
  }

final int MINE=11;
int FLAG=12;
int HEIGHT;
int WIDTH;
int MINE_NUMBER;
int[][]field;
boolean[][]fieldVis;
boolean lose=false;
final boolean NORMAL_MODE=true;

}
