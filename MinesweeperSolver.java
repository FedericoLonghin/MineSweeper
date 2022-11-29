public class MinesweeperSolver{
    public MinesweeperSolver(){
    }

    public int predictCell(int i, int j, Minesweeper ms){
        System.out.println("Predicting "+i+" "+j);
        if(ms.isCelltoDiscover(i,j)){ //if the cell is to be discovered
            for(int z=0;z<9;z++){
                if(i-1+z/3>=0 && i-1+z/3<ms.HEIGHT && j-1+z%3>=0 && j-1+z%3<ms.WIDTH){
                if(ms.getCellValue(i-1+z/3,j-1+z%3)!=-1 && ms.getCellValue(i-1+z/3,j-1+z%3)==0)return 1;
      }

        }
    }
         return -2;
}

    public int predictNearCell(int i, int j, Minesweeper ms){
        System.out.println("Predicting "+i+" "+j);
        if(ms.getCellValue(i,j)>0){ //if the cell has a number
        if(ms.getNumberToDiscover(i,j)==ms.getCellValue(i,j))System.out.println("cell"+i+" "+j+"can be toggled");
            for(int z=0;z<9;z++){
                int ni=i-1+z/3;
                int nj=j-1+z%3;
                if(ni>=0 && ni<ms.HEIGHT && nj>=0 && nj<ms.WIDTH){
                if(ms.isCelltoDiscover(ni,nj))return ms.HEIGHT*ni+nj;
      }
        }
    }
         return -1;
    }

}