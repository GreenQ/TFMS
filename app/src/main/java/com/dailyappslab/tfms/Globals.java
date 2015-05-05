package com.dailyappslab.tfms;

/**
 * Created by GreenQ on 05.05.2015.
 */
public class Globals {
    public static Package CurrentPackage;
    public static int CurrentLevel;
    public static int CurrentGold;

    public static Package[] GetPackages()
    {
        Package[] packages = new Package[50];

        packages[0] = new Package(1, "Уровень 1", 1, 10);
        packages[1] = new Package(2, "Уровень 2", 11, 20);
        packages[2] = new Package(3, "Уровень 3", 21, 30);
        packages[3] = new Package(4, "Уровень 4", 31, 40);
        packages[4] = new Package(5, "Уровень 5", 41, 50);
        packages[5] = new Package(6, "Уровень 6", 51, 60);
        packages[6] = new Package(7, "Уровень 7", 61, 70);
        packages[7] = new Package(8, "Уровень 8", 71, 80);
        packages[8] = new Package(9, "Уровень 9", 81, 90);
        packages[9] = new Package(10, "Уровень 10", 91, 100);
        packages[10] = new Package(11, "Уровень 11", 101, 110);
        packages[11] = new Package(12, "Уровень 12", 111, 120);
        packages[12] = new Package(13, "Уровень 13", 121, 130);
        packages[13] = new Package(14, "Уровень 14", 131, 140);
        packages[14] = new Package(15, "Уровень 15", 141, 150);
        packages[15] = new Package(16, "Уровень 16", 151, 160);
        packages[16] = new Package(17, "Уровень 17", 161, 170);
        packages[17] = new Package(18, "Уровень 18", 171, 180);
        packages[18] = new Package(19, "Уровень 19", 181, 190);
        packages[19] = new Package(20, "Уровень 20", 191, 200);
        packages[20] = new Package(21, "Уровень 21", 201, 210);
        packages[21] = new Package(22, "Уровень 22", 211, 220);
        packages[22] = new Package(23, "Уровень 23", 221, 230);
        packages[23] = new Package(24, "Уровень 24", 231, 240);
        packages[24] = new Package(25, "Уровень 25", 241, 250);
        packages[25] = new Package(26, "Уровень 26", 251, 260);
        packages[26] = new Package(27, "Уровень 27", 261, 270);
        packages[27] = new Package(28, "Уровень 28", 271, 280);
        packages[28] = new Package(29, "Уровень 29", 281, 290);
        packages[29] = new Package(30, "Уровень 30", 291, 300);
        packages[30] = new Package(31, "Уровень 31", 301, 310);
        packages[31] = new Package(32, "Уровень 32", 311, 320);
        packages[32] = new Package(33, "Уровень 33", 321, 330);
        packages[33] = new Package(34, "Уровень 34", 331, 340);
        packages[34] = new Package(35, "Уровень 35", 341, 350);
        packages[35] = new Package(36, "Уровень 36", 351, 360);
        packages[36] = new Package(37, "Уровень 37", 361, 370);
        packages[37] = new Package(38, "Уровень 38", 371, 380);
        packages[38] = new Package(39, "Уровень 39", 381, 390);
        packages[39] = new Package(40, "Уровень 40", 391, 400);
        packages[40] = new Package(41, "Уровень 41", 401, 410);
        packages[41] = new Package(42, "Уровень 42", 411, 420);
        packages[42] = new Package(43, "Уровень 43", 421, 430);
        packages[43] = new Package(44, "Уровень 44", 431, 440);
        packages[44] = new Package(45, "Уровень 45", 441, 450);
        packages[45] = new Package(46, "Уровень 46", 451, 460);
        packages[46] = new Package(47, "Уровень 47", 461, 470);
        packages[47] = new Package(48, "Уровень 48", 471, 480);
        packages[48] = new Package(49, "Уровень 49", 481, 490);
        packages[49] = new Package(50, "Уровень 50", 491, 500);
        return packages;
    }
}
//