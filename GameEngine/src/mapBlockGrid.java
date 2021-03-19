public class mapBlockGrid {

    private int width=100;
    private int height=30;

    private Block[][]blockMap;


    public mapBlockGrid(){
        blockMap=new Block[height][width];
        setBlockMap();
    }
    public void setBlockMap(){

        for(int r=0;r<height;r++)
            for(int c=0;c<width;c++){

            if(r>10)
                blockMap[r][c]=Block.randomBlock();
            else
                blockMap[r][c]=new Air();
            }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Block[][] getBlockMap() {
        return blockMap;
    }

    public void setBlockMap(Block[][] blockMap) {
        this.blockMap = blockMap;
    }
}
