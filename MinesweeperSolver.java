public class MinesweeperSolver{
    public MinesweeperSolver(){
    }

    public int predictCell(int i, int j, Minesweeper ms){
        //System.out.println("Predicting "+i+" "+j);
        if(ms.isCelltoDiscover(i,j)){ //if the cell is to be discovered
            for(int z=0;z<9;z++){
                if(i-1+z/3>=0 && i-1+z/3<ms.HEIGHT && j-1+z%3>=0 && j-1+z%3<ms.WIDTH){
                if(ms.getCellValue(i-1+z/3,j-1+z%3)!=-1 && ms.getCellValue(i-1+z/3,j-1+z%3)==0)return 1;
      }

        }
    }
         return -2;
}

    public int[] predictNearCell(int i, int j, Minesweeper ms){
      int[]res={-1,0};
        if(ms.getCellValue(i,j)>0 && ms.getCellValue(i,j)!=MINE ){ //if the cell has a number
          //System.out.println("Predicting "+i+" "+j);
          if(ms.getNumberToDiscover(i,j)==ms.getCellValue(i,j)-ms.getNumberFlag(i,j) ||ms.getCellValue(i,j)==ms.getNumberFlag(i,j)){  //if the number of cell to discover is equal to the number - flags 
            //System.out.println("cell "+i+" "+j+" is near somethng good; has "+ms.getNumberToDiscover(i,j)+" cell to discover");
            for(int z=0;z<9;z++){
                int ni=i-1+z/3;
                int nj=j-1+z%3;
                if(ni>=0 && ni<ms.HEIGHT && nj>=0 && nj<ms.WIDTH){
                  if(ms.isCelltoDiscover(ni,nj)){
                      int temp= nj*ms.MOLT_CONST;
                      temp+=ni;
                    if(ms.getNumberFlag(i,j)!=ms.getCellValue(i,j)){//safe cell
                      //System.out.print("returned "+temp);
                      res[0]=1;
                      res[1]=temp;
                      return res;
                    }
                    else{
                      //System.out.print("val to ret2 "+ ((temp))+" i,j: "+ni+" "+nj);
                      temp=-temp-1;
                      //System.out.println("returned2 "+ ((temp)));
                      res[0]=1;
                      res[1]=temp;
                      return res;
                    }
                  }
                }
              }
            }
      }
      return res;
  }

final int MINE=11;
}
