
import java.util.ArrayList;


public class Truck {
    
    private int height;
    private int width;
    private int length;
    private int[][][] truck;
    private int counter=0;
    public int curX=0;
    public int curY=0;
    public int curZ=0;
    private ArrayList<ParcelAtPlace> parcels = new ArrayList<ParcelAtPlace>();
    public Truck(int h,int l,int w)
    {
        
        width=w;
        height=h;
        length=l;
        truck=new int[height][length][width];
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public void printTruck()
    {
        for (int i=0;i<height;i++)
        {
            for (int j=0;j<length;j++)
            {
                for (int k=0;k<width;k++)
                {
                    System.out.print(truck[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
       
    }
    
    public boolean isFull()
    {
        for (int i=0;i<truck.length;i++)
        {
            for (int j=0;j<truck[i].length;j++)
            {
                for (int k=0;k<truck[i][j].length;k++)
                {
                    if (truck[i][j][k]==0)
                    {
                        counter++;
                    }
                }
            }
        }
        if (counter==0)
        {
            counter=0;
            return true;
        }
        return false;
    }
    
    public boolean FitsTruck(Parcel aParcel)
    {
        if (width-curX>=aParcel.getX()&&height-curY>=aParcel.getY()&&length-curZ>=aParcel.getZ()&&CurrentSpaceEmpty(aParcel))
        {
            return true;
        }
        return false;
    }

    public boolean CurrentSpaceEmpty(Parcel aParcel)
    {
        for (int i=0;i<aParcel.getY();i++)
        {
            for (int j=0;j<aParcel.getZ();j++)
            {
                for (int k=0;k<aParcel.getX();k++)
                {
                    if(truck[curY+i][curZ+j][curX+k]!=0)
                        return false;
                }
            }
        }
        return true;
    }
    
    
    public void NextEmptySpace()
    {
        for (int i=0;i<truck.length;i++)
        {
            for (int j=0;j<truck[i].length;j++)
            {
                for (int k=0;k<truck[i][j].length;k++)
                {
                    if (!isFilled(i,j,k))
                    {
                        curY=i;
                        curZ=j;
                        curX=k;
                        return;
                    }
                }
            }
        }
    }
    
    
    public int[][][] returnWholeTruck()
    {
        return truck;
    }
    
    public int getCurX()
    {
        return curX;
    }
    
    public int getCurY()
    {
        return curY;
    }
    
    public int getCurZ()
    {
        return curZ;
    }
    
    
    public void SetUnusable()
    {
         truck[curY][curZ][curX] = 9;
    }
    
    public void PutParcel(Parcel a)
    {
        for (int i=0;i<a.getY();i++)
        {
            for (int j=0;j<a.getZ();j++)
            {
                for (int k=0;k<a.getX();k++)
                {
                    truck[curY+i][curZ+j][curX+k]=a.getValue();
                }
            }
        }
        
        parcels.add(new ParcelAtPlace(a,curX,curY,curZ));
    }
     
    
    
    
    public void putTruck(int[][][] truck2)
    {
        for (int i=0;i<truck2.length;i++)
        {
            for (int j=0;j<truck2[i].length;j++)
            {
                for (int k=0;k<truck2[i][j].length;k++)
                {
                    truck[curY+i][curZ+j][curX+k]=truck2[i][j][k];
                }
            }
        }
    }
    
    
    
    public void putParcel(ArrayList<ParcelAtPlace> a) {
        parcels = a;
    }
    
    
    public boolean isFilled(int y, int z, int x) {
        if (truck[y][z][x] == 0) {
            return false;
        }
        return true;
    }
   
    
    public void makeEmpty()
    {
        truck = new int[height][length][width];
        counter = 0;
        curX = 0;
        curY = 0;
        curZ = 0;

        parcels = new ArrayList<ParcelAtPlace>();
    }
   
    
    
     public Object[] getParcels() {
        return parcels.toArray();
    }
     
     
    public ArrayList<ParcelAtPlace> getRawParcels() {
        return parcels;
    }

    public int TruckValueGetter()
    {
        int value=0;
        for (ParcelAtPlace a :parcels)
        {
            value+=a.parcel.getValue();
        }
        return value;
    }
    
    
}
