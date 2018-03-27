import java.io.*;

public class Convert {
    public static int[][] itemConversion = {{35, 11694},
            {667, 11696},
            {1107, 11724},
            {2904, 11732},
            {430, 10551},
            {1949, 10548},
            {2621, 11283},
            {746, 11700},
            {8100, 11730},
            {8245, 11728},
            {2402, 11698},
            {2775, 11286},
            {7611, 11335},
            {4827, 11235},
            {7811, 8007},
            {7813, 8008},
            {7814, 8009},
            {2518, 11663},
            {2524, 11664},
            {2526, 11665},
            {2520, 8839},
            {2522, 8840},
            {2516, 8842},
            {7608, 11720},
            {7609, 11722},
            {642, 10348},
            {652, 10346},
            {662, 10350},
            {2589, 10352},
            {640, 10338},
            {650, 10340},
            {660, 10342},
            {638, 10330},
            {648, 10332},
            {658, 10334},
            {8011, 10446},
            {8012, 10448},
            {8010, 10450},
            {8013, 10370},
            {8014, 10372},
            {8015, 10374},
            {8016, 10368},
            {8017, 10386},
            {8018, 10388},
            {8019, 10390},
            {8020, 10384},
            {8021, 10378},
            {8022, 10380},
            {8023, 10382},
            {8024, 10376},
            {8030, 10400},
            {8031, 10408},
            {8032, 10404},
            {8033, 10416},
            {8034, 10412},
            {8035, 10402},
            {8036, 10410},
            {8037, 10406},
            {8038, 10418},
            {8039, 10414},
            {3649, 11710},
            {3650, 11712},
            {3651, 11714},
            {3654, 11690},
            {3656, 11702},
            {3657, 11704},
            {3658, 11706},
            {3659, 11708},
            {3647, 11716},
            {13262, 8921},
            {7000, 10499},
            {1077, 11726},
            {553, 9075},
            {8052, 10460},
            {8051, 10468},
            {3095, 8844},
            {3096, 8845},
            {3097, 8846},
            {3098, 8847},
            {3099, 8848},
            {3100, 8849},
            {3101, 8850},
            {8220, 9176},
            {837, 9185},
            {8224, 9183},
            {8226, 9181},
            {8230, 9179},
            {8232, 9177},
            {8234, 9174},
            {2677, 9747},
            {4319, 9748},
            {2679, 9749},
            {2680, 9750},
            {2682, 9752},
            {2683, 9753},
            {4329, 9754},
            {2685, 9755},
            {2686, 9756},
            {4351, 9757},
            {2688, 9758},
            {2689, 9759},
            {4347, 9760},
            {2691, 9761},
            {2692, 9762},
            {4343, 9763},
            {2694, 9764},
            {2695, 9804},
            {4321, 9805},
            {2697, 9806},
            {2698, 9765},
            {4333, 9766},
            {2700, 9767},
            {2701, 9768},
            {4341, 9769},
            {2703, 9770},
            {2704, 9771},
            {4317, 9772},
            {2706, 9773},
            {2707, 9774},
            {4339, 9775},
            {2709, 9776},
            {2710, 9777},
            {4361, 9778},
            {2712, 9779},
            {2713, 9780},
            {4327, 9781},
            {2715, 9782},
            {2716, 9783},
            {4337, 9784},
            {2718, 9785},
            {2719, 9786},
            {4355, 9787},
            {2721, 9788},
            {2722, 9792},
            {4345, 9793},
            {2724, 9794},
            {2725, 9795},
            {4357, 9796},
            {2727, 9797},
            {2728, 9798},
            {4335, 9799},
            {2730, 9800},
            {2731, 9801},
            {4325, 9802},
            {2733, 9803},
            {2734, 9807},
            {4353, 9808},
            {2736, 9809},
            {2737, 9810},
            {4331, 9811},
            {2739, 9812},
            {2740, 9813},
            {2741, 9814},
            {2742, 9789},
            {2743, 9790},
            {2744, 9791},
            {4359, 9751},
            {12000, 2481}
    };

    private static File characters = new File("./characters/");

    public static void main(String[] args) {
        if (characters.exists() && characters.isDirectory()) {
            File[] charFiles = characters.listFiles();
            for (File charFile : charFiles) {
                convertItems(charFile);
            }
        }
    }

    private static void convertItems(File username) {
        try {
            String inCharData, outCharData, data;
            String[] item;
            int bankCount = 0, inventoryCount = 0;
            File converted = new File(characters.toString() + "working.tmp");
            DataInputStream fs = new DataInputStream(new FileInputStream(username));
            BufferedWriter out = new BufferedWriter(new FileWriter(converted));
            while ((inCharData = fs.readLine()) != null) {
                outCharData = inCharData.trim();
                if (inCharData.trim().startsWith("bank =")) {
                    item = inCharData.split("\t");
                    for (int i = 0; i < itemConversion.length; i++) {
                        int oldItem = itemConversion[i][0]+1;
                        int newItem = itemConversion[i][1]+1;
                        if (item[1].equals(String.valueOf(oldItem))) {
                            outCharData = item[0] + "\t" + newItem + "\t" + item[2];
                            bankCount++;
                        }
                    }
                }
                if (inCharData.trim().startsWith("item =")) {
                    item = inCharData.split("\t");
                    for (int i = 0; i < itemConversion.length; i++) {
                        int oldItem = itemConversion[i][0]+1;
                        int newItem = itemConversion[i][1]+1;
                        if (item[1].equals(String.valueOf(oldItem))) {
                            outCharData = item[0] + "\t" + newItem + "\t" + item[2];
                            inventoryCount++;
                        }
                    }
                }
                out.write(outCharData);
                out.newLine();
            }
            fs.close();
            out.close();
            username.delete();
            converted.renameTo(username);
            System.out.println("[" + username + "] Converted (" + bankCount + ") bank items and (" + inventoryCount + ") inventory items.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}