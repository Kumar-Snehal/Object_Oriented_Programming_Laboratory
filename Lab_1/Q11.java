public class Q11 {
    public static void main(String[] args) {
        char pattern[][]={
            {' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ','/','/','/',' ',' ',' '},
            {' ','/','/','/','/','/','/','/',' '},
            {' ','|',' ','o',' ','o',' ','|',' '},
            {'@','|',' ',' ','n',' ',' ','|','@'},
            {' ','|',' ','~','~','~',' ','|',' '},
            {' ',' ','-','-','-','-','-',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' ',' '}
        };
        for(int i=0;i<pattern.length;i++)
        {
            for(int j=0;j<pattern[i].length;j++)
                System.out.print(pattern[i][j]);
            System.out.println();
        }    
    }
}
