public class Recursion {
    public static void main(String[] args) {

        int[] sumMe = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
        System.out.printf("Array Sum: %d\n", arraySum(sumMe, 0));

        int[] minMe = { 0, 1, 1, 2, 3, 5, 8, -42, 13, 21, 34, 55, 89 };
        System.out.printf("Array Min: %d\n", arrayMin(minMe, 0));

        String[] amISymmetric =  {
                "You can cage a swallow can't you but you can't swallow a cage can you",
                "I still say cS 1410 is my favorite class"
        };
        for (String test : amISymmetric) {
            String[] words = test.toLowerCase().split(" ");
            System.out.println();
            System.out.println(test);
            System.out.printf("Is word symmetric: %b\n", isWordSymmetric(words, 0, words.length - 1));
        }

        double[][] weights = {
                { 51.18 },
                { 55.90, 131.25 },
                { 69.05, 133.66, 132.82 },
                { 53.43, 139.61, 134.06, 121.63 }
        };
        System.out.println();
        System.out.println("--- Weight Pyramid ---");
        for (int row = 0; row < weights.length; row++) {
            for (int column = 0; column < weights[row].length; column++) {
                double weight = computePyramidWeights(weights, row, column);
                System.out.printf("%.2f ", weight);
            }
            System.out.println();
        }

        char[][] image = {
                {'*','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ','*','*',' ',' '},
                {' ','*',' ',' ','*','*','*',' ',' ',' '},
                {' ','*','*',' ','*',' ','*',' ','*',' '},
                {' ','*','*',' ','*','*','*','*','*','*'},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ','*','*','*',' ',' ','*',' '},
                {' ',' ',' ',' ',' ','*',' ',' ','*',' '}
        };
        int howMany = howManyOrganisms(image);
        System.out.println();
        System.out.println("--- Labeled Organism Image ---");
        for (char[] line : image) {
            for (char item : line) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.printf("There are %d organisms in the image.\n", howMany);

    }

    public static boolean isWordSymmetric(String[] words, int start, int end){
        if(start>=end){
            return true;
        }
        else{
            if(words[start].equalsIgnoreCase(words[end])){
                return isWordSymmetric(words,start+1,end-1);
            }
            else{
                return false;
            }
        }
    }


    public static long arraySum(int[] data, int position){
        if(position < data.length && position >= 0) {
            if (data.length - 1 == position) {
                return data[position];
            } else {
                return data[position] + arraySum(data, position + 1);
            }
        }
        else{
            return 0;
        }
    }

    public static int arrayMin(int[] data, int position){
        if(position+1==data.length){
            return data[position];
        }
        else{
            int min = arrayMin(data,position+1);
            if(min < data[position]){
                return min;
            }
            else{
                return data[position];
            }
        }
    }

    public static double computePyramidWeights(double [][] weights, int row, int column){
        if(RowColValid(weights, row, column)) {
            if (row < 0 || column < 0 || column >= weights[row].length) {
                return 0.0;
            }
            return weights[row][column]
                    + 0.5 * computePyramidWeights(weights, row-1, column)
                    + 0.5 * computePyramidWeights(weights, row-1, column-1);
            }
            return 0.0;
        }

    public static boolean RowColValid(double [][] weights, int row, int column) {
        return row >= 0 && column >= 0 && row < weights.length && column < weights[row].length;
    }


    static int howManyOrganisms(char[][] image)
    {
        int [][]dir={{-1,0},{1,0},{0,1},{0,-1}};
        char replace='a';
        int count=0;
        for (int i=0;i<image.length;i++)
        {
            for (int j=0;j<image[i].length;j++)
            {
                if(image[i][j]=='*')
                {
                    image[i][j]=replace;
                    recursive(image,dir,i,j,replace);
                    count++;
                    replace++;
                }
            }
        }
        return count;
    }


    static void recursive(char image[][],int [][] dir, int x,int y, char replace)
    {
        for(int i=0;i<4;i++)
        {
            int tempx=x+dir[i][0];
            int tempy=y+dir[i][1];
            if(tempx>=0 && tempx<image.length)
            {
                if(tempy>=0 && tempy<image[tempx].length)
                {
                    if(image[tempx][tempy]=='*')
                    {
                        image[tempx][tempy]=replace;
                        recursive(image,dir,tempx,tempy,replace);
                        }
                    }
                }
            }
        }
}
