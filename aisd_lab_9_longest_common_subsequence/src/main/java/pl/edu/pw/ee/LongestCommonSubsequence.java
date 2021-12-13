package pl.edu.pw.ee;

class LongestCommonSubsequence {

    private String firstStr;
    private String secondStr;
    private Element[][] tab;
    private int firstLen;
    private int secondLen;
    
    public LongestCommonSubsequence(String firstStr, String secondStr){
        validateString(firstStr);
        validateString(secondStr);
        this.firstStr = firstStr;
        this.secondStr = secondStr;
        firstLen = firstStr.length() + 1;
        secondLen = secondStr.length() + 1;
        tab = new Element[firstLen][secondLen];
        
        for(int i = 0; i < firstLen; i++){
            for(int j = 0; j < secondLen; j++){
                if(i == 0){
                    tab[i][j] = new Element(0, -1);
                } else if(j == 0){
                    tab[i][j] = new Element(0, -1);
                } else {
                    if(firstStr.charAt(i - 1) == secondStr.charAt(j - 1)){
                        tab[i][j] = new Element(tab[i - 1][j - 1].getValue() + 1, 2);
                    } else if(tab[i - 1][j].getValue() >= tab[i][j - 1].getValue()){
                        tab[i][j] = new Element(tab[i -1][j].getValue(), 0);
                    } else {
                        tab[i][j] = new Element(tab[i][j - 1].getValue(), 1);
                    }
                }
            }
        }
    }

    public String findLCS(){
        String anser = "";
        int i = firstLen - 1;
        int j = secondLen - 1;
        int x = tab[i][j].getValue();
        while(x > 0){
            if(firstStr.charAt(i - 1) == secondStr.charAt(j - 1)){
                anser = firstStr.charAt(i - 1) + anser;
            }
            if(tab[i][j].getDirection() == 2){
                i--;
                j--;
                x = tab[i][j].getValue();
            } else if(tab[i][j].getDirection() == 1){
                j--;
                x = tab[i][j].getValue();
            } else if(tab[i][j].getDirection() == 0){
                i--;
                x = tab[i][j].getValue();
            }
        }
	    return anser;
    }

    //public void display(){
    public String[][] display(){
        int i = firstLen * 2 + 2;
        int j = secondLen * 2 + 2;
        String [][] strTab = new String[i][j];
        for (int y = 0; y < i; y++){
            for(int x = 0; x < j; x++){
                if (y % 2 == 0){
                    if( x % 2 == 0){
                        strTab[y][x] = " ";
                    } else {
                        strTab[y][x] = "|";
                    }
                } else if (y % 2 == 1){
                    if( x % 2 == 0){
                        strTab[y][x] = " ";
                    } else {
                        strTab[y][x] = "+";
                    }
                }
            }
        }
        return strTab;
    }

    public class Element{
        private int value;
        private int direction;
        // 0 - góra
        // 1 - lewo
        // 2 - lewy górny
        Element(int value, int direction){
            this.value = value;
            this.direction = direction;
        }

        public int getValue() {
            return value;
        }

        public int getDirection() {
            return direction;
        }
    }

    private void validateString(String a){
        if(a == null || a == ""){
            throw new IllegalArgumentException("String cannot be null or empty");
        }
    }


    //do testowania
    public int getFirstLen() {
        return firstLen;
    }

    public int getSecondLen() {
        return secondLen;
    }

    public Element[][] getTab() {
        return tab;
    }

    public String getFirstStr() {
        return firstStr;
    }

    public String getSecondStr() {
        return secondStr;
    }

}
