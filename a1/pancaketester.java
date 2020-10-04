import java.util.Arrays;
public class pancaketester {
    public static void main(String[] args){
        System.out.println("Printing all possible flips of arrays");

        int[] array1 = {1,0,3,5,2,4};
        // 9 3 2 0 1
        // 1 9 3 2 0
        // 1 0 9 3 2
        // 1 0 2 9 3
        // 1 0 2 3 9
        int[] ss;
        int arraysize = (array1.length); //the biggest pancake
        int flipcount = arraysize;
        int cur = 0;
        System.out.println(flipcount);
        while (flipcount != 0 ){
            int array2[] = {1,0,3,5,2,4};
            ss= array2;
            //System.out.println("Current array " + Arrays.toString(ss));
            for(int i=cur, j = 0; i<(arraysize/2 +cur); i++, j++){ 
                System.out.println("i = " +  i + " arraysize = " + arraysize);
                if (i == arraysize-1){
                    System.out.println("careful");
                    break;
                }
                int temp = ss[i]; 
                System.out.println("Retrieved element"  + ss[i] + "flipping with " + ss[arraysize - j -1]);
                ss[i] = ss[arraysize -j -1]; 
                ss[arraysize -j -1] = temp; 
                
            }
            System.out.println(Arrays.toString(ss));
            cur++;
            flipcount--;
        }
        //System.out.println(Arrays.toString(array1));
    }
}