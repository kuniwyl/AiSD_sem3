package pl.edu.pw.ee;

class LongestCommonSubsequence {

    private Element[][] tab;
    private String[][] strTab;
    private String topStr;
    private String leftStr;
    private int topLen;
    private int leftLen;

    public LongestCommonSubsequence(String topStr, String leftStr) {
        validateString(topStr);
        validateString(leftStr);
        this.topStr = topStr;
        this.leftStr = leftStr;
        this.topLen = topStr.length() + 1;
        this.leftLen = leftStr.length() + 1;
        tab = new Element[leftLen][topLen];
        for (int i = 0; i < leftLen; i++) {
            for (int j = 0; j < topLen; j++) {
                if (i == 0) {
                    tab[i][j] = new Element(0, -1);
                } else if (j == 0) {
                    tab[i][j] = new Element(0, -1);
                } else {
                    if (leftStr.charAt(i - 1) == topStr.charAt(j - 1)) {
                        tab[i][j] = new Element(tab[i - 1][j - 1].getValue() + 1, 2);
                    } else if (tab[i - 1][j].getValue() >= tab[i][j - 1].getValue()) {
                        tab[i][j] = new Element(tab[i - 1][j].getValue(), 0);
                    } else {
                        tab[i][j] = new Element(tab[i][j - 1].getValue(), 1);
                    }
                }
            }
        }
    }

    public String findLCS() {
        String anser = "";
        int i = leftLen - 1;
        int j = topLen - 1;
        int x = tab[i][j].getValue();
        while (x > 0) {
            if (tab[i][j].getDirection() == 2) {
                anser = leftStr.charAt(i - 1) + anser;
                i--;
                j--;
                x = tab[i][j].getValue();
            } else if (tab[i][j].getDirection() == 1) {
                j--;
                x = tab[i][j].getValue();
            } else if (tab[i][j].getDirection() == 0) {
                i--;
                x = tab[i][j].getValue();
            }
        }
        return anser;
    }

    public void display() {
        int i = (leftLen + 1) * 4 + 1;
        int j = (topLen) * 6 + 9;
        strTab = new String[i][j];

        for (int y = 0; y < i; y++) {
            for (int x = 0; x < j; x++) {
                if (y % 4 == 0) {
                    if (x == 0 || ((x - 2) % 6 == 0 && x != 2)) {
                        strTab[y][x] = "+";
                    } else {
                        strTab[y][x] = "-";
                    }
                } else {
                    if ((x + 1) % 6 == 0) {
                        if (y == 2 && x > 16) {
                            addString(y, x);
                        } else if (x > 10 && (y - 2) % 4 == 0) {
                            strTab[y][x] = "0";
                        }
                    } else if ((y - 2) % 4 == 0 && y > 9) {
                        if (x == 4) {
                            addString(y, x);
                        } else if ((x + 1) % 6 == 0) {
                            strTab[y][x] = "0";
                        }
                    } else {
                    }
                    if (x == 0 || ((x - 2) % 6 == 0 && x != 2)) {
                        strTab[y][x] = "|";
                    } else {
                        if (strTab[y][x] == null) {
                            strTab[y][x] = " ";
                        }
                    }
                }
            }
        }
        strTab[2][11] = " ";

        for (int y = 0; y < leftLen - 1; y++) {
            for (int x = 0; x < topLen - 1; x++) {
                strTab[10 + y * 4][17 + x * 6] = String.valueOf(tab[y + 1][x + 1].value);
            }
        }

        int a = leftLen - 1;
        int b = topLen - 1;
        int val = tab[a][b].getValue();
        while (val > 0) {
            if (tab[a][b].getDirection() == 2) {
                strTab[6 + a * 4 - 1][11 + b * 6 - 2] = "\\";
                a--;
                b--;
                val = tab[a][b].getValue();
            } else if (tab[a][b].getDirection() == 1) {
                strTab[6 + a * 4][11 + b * 6 - 2] = "<";
                b--;
                val = tab[a][b].getValue();
            } else if (tab[a][b].getDirection() == 0) {
                strTab[6 + a * 4 - 1][11 + b * 6] = "^";
                a--;
                val = tab[a][b].getValue();
            }
        }

        String ans = "";
        for (int y = 0; y < i; y++) {
            for (int x = 0; x < j; x++) {
                ans += strTab[y][x];
            }
            ans += "\n";
        }
        System.out.print(ans);
    }

    private void addString(int y, int x) {
        String a;
        if (x > 16) {
            a = topStr.substring((x - 17) / 6, (x - 17) / 6 + 1);
        } else {
            a = leftStr.substring((y - 10) / 4, (y - 10) / 4 + 1);
        }
        a = checkString(a);

        if (a.length() == 1) {
            strTab[y][x] = a;
        } else if (a.length() == 2) {
            strTab[y][x - 1] = a.substring(0, 1);
            strTab[y][x] = a.substring(1, 2);
        } else if (a.length() == 3) {
            strTab[y][x - 2] = a.substring(0, 1);
            strTab[y][x - 1] = a.substring(1, 2);
            strTab[y][x] = a.substring(0, 3);
        }
    }

    private String checkString(String a) {
        int t = a.charAt(0);
        if (t == 9) {
            return "\\t";
        } else if (t == 8) {
            return "\\b";
        } else if (t == 10) {
            return "\\n";
        } else if (t == 13) {
            return "\\r";
        } else if (t == 12) {
            return "\\f";
        } else if (t == 39) {
            return "\\'";
        } else if (t == 34) {
            return "\\\"";
        } else if (t == 92) {
            return "\\\\";
        } else {
            return a;
        }
    }

    private class Element {
        private int value;
        private int direction;

        Element(int value, int direction) {
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

    private void validateString(String a) {
        if (a == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
    }
}
