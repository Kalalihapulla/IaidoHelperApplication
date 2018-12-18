package com.jebu.iaidohelperapplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;


//Static singleton global model class for populating and accessing the data stored in the runtime memory.
public class KataModel extends Application {
    private static final KataModel kataInstance = new KataModel();

    private static ArrayList<Kata> kataList = new ArrayList<>();

    private static Context context;


    public void onCreate() {
        super.onCreate();
        KataModel.context = getApplicationContext();
        Log.d("info", "APPLICATION STARTED!");
    }


    public static KataModel getInstance() {

        return kataInstance;
    }

    public static Context getAppContext() {
        return KataModel.context;
    }

    public void populate() {

        ArrayList<GradingPoint> maeGradingPoints = new ArrayList<>();
        {

            {
                maeGradingPoints.add(new GradingPoint(0, "10000", "a. Does the performer do enough Sayabiki when they cut the opponent's\n" +
                        "face with Nukitsuke? ", false));
                maeGradingPoints.add(new GradingPoint(1, "12000", "b. Is the sword taken into Furikaburi with a feeling of thrusting to behind\n" +
                        "the left ear?", false));
                maeGradingPoints.add(new GradingPoint(2, "13000", "c. Is the tip of the sword above the horizontal position when in Furikaburi?", false));
                maeGradingPoints.add(new GradingPoint(3, "13000", "d. Is the sword brought down without hesitation during Kirioroshi?", false));
                maeGradingPoints.add(new GradingPoint(4, "14000", "e. Is the tip of the sword slightly below horizontal at the end of Kirioroshi?", false));
                maeGradingPoints.add(new GradingPoint(5, "16000", "f. Is the shape and form of chiburi correct?", false));
                maeGradingPoints.add(new GradingPoint(6, "30000", "g. Is Noto performed correctly?", false));
            }
        }
        this.kataList.add(new Kata("1. Ipponme - Mae", 1, "Detecting the harmful intention of the person in front of you, forestall it by using the sword tip to cut their temple in a horizontal action and then bring the sword downwards from above the head in a vertical action.", "TcddN7OjJBk", maeGradingPoints));

        ArrayList<GradingPoint> ushiroGradingPoints = new ArrayList<>();
        {
            {
                ushiroGradingPoints.add(new GradingPoint(0, "7000", "a. When the turn is made, is the left foot moved sufficiently to the front left?", false));
                ushiroGradingPoints.add(new GradingPoint(1, "8000", "b. Is the horizontal cut made to the opponent's temple?", false));

            }
        }
        this.kataList.add(new Kata("2. Nihonme - Ushiro", 2, "Detecting the harmful intention of the person behind you, forestall it by using\n" +
                "the sword tip to cut their temple in a horizontal action and then bring the sword\n" +
                "downwards from above the head in a vertical action.", "QoS8Evjc5iY", ushiroGradingPoints));

        ArrayList<GradingPoint> ukenagashiGradingPoints = new ArrayList<>();
        {
            {
                ukenagashiGradingPoints.add(new GradingPoint(0, "10000", "a. When the parry is made, does it protect the upper body?", false));
                ukenagashiGradingPoints.add(new GradingPoint(1, "11000", "b. Is the left foot brought back behind the right toot and the cut made along the Kesa line?", false));
                ukenagashiGradingPoints.add(new GradingPoint(2, "12000", "c. After the cut has been made, is the left hand is in front of the navel and\n" +
                        "the sword tip a little below horizontal?", false));
            }
        }
        this.kataList.add(new Kata("3. Sanbonme - Ukenagashi", 3, "When the person at your left suddenly stands up and intends to cut you\n" +
                "downwards, you parry their sword with the ridge on the side of the sword and\n" +
                "cut them from above the head in a downwards diagonal action.", "0AK3xrrdu_g", ukenagashiGradingPoints));

        ArrayList<GradingPoint> tsukaatePoints = new ArrayList<>();
        {
            {
                tsukaatePoints.add(new GradingPoint(0, "6000", "a. Is the Tsukagashira surely pointed at the opponent's solar plexus?", false));
                tsukaatePoints.add(new GradingPoint(1, "7000", "b. When the rear opponent is thrust, is this done with the right elbow\n" +
                        "extended fully and does the left hand bring the Koiguchi to the navel?", false));
                tsukaatePoints.add(new GradingPoint(2, "8000", "c. When the cut is made, is it on the vertical centerline and from the correct\n" +
                        "position above the head?", false));

            }
        }
        this.kataList.add(new Kata("4. Yonhonme - Tsuka Ate", 4, "Two people, one in front and the other behind intend to attack you. Thrust\n" +
                "the pommel of your sword into the front opponent's solar plexus, then draw and\n" +
                "stab the rear opponent. While turning back to the front bring the sword\n" +
                "downward from above the head in a vertical action to defeat the first opponent.", "3doETQpHoIM", tsukaatePoints));

        ArrayList<GradingPoint> kesagiriGradingPoints = new ArrayList<>();
        {
            {
                kesagiriGradingPoints.add(new GradingPoint(0, "6000", "a. When the initial upper cut is made, is the right hand above the right\n" +
                        "shoulder when the sword is rotated?", false));
                kesagiriGradingPoints.add(new GradingPoint(1, "14000", "b. When Chiburi is performed, is it at the correct angle while the person\n" +
                        "steps back with the left foot at the same time when their left hand takes\n" +
                        "hold of the Koiguchi?", false));

            }
        }
        this.kataList.add(new Kata("5. Gohonme - Kesagiri", 5, "While you are walking along, an opponent comes towards you and tries to cut\n" +
                "you by bringing their sword from above their head. Immediately cut them\n" +
                "diagonally, first up through the right waist and then down from the left shoulder\n" +
                "through the Kesa line.", "PCqkW1Cv7gU", kesagiriGradingPoints));

        ArrayList<GradingPoint> morotezukiGradingPoints = new ArrayList<>();
        {
            {
                morotezukiGradingPoints.add(new GradingPoint(0, "6000", "a. Is the initial cut correctly made from the opponent's upper head down to\n" +
                        "their chin when making Nuki Uchi ? ", false));
                morotezukiGradingPoints.add(new GradingPoint(1, "7000", "b. Does the performer bring their left foot up behind their right? Is Chudan\n" +
                        "No Kamae correctly made and the sword thrust into the correct target of\n" +
                        "the body? Is the thrust made with certainty?", false));
                morotezukiGradingPoints.add(new GradingPoint(2, "10000", "c. Does the performer bring their sword above their head in a parrying\n" +
                        "action after pulling it out from the first opponent?", false));

            }
        }
        this.kataList.add(new Kata("6. Ropponme - Morotezuki", 6, "While walking along three people - two in front and one at the rear intend to\n" +
                "attack you. Forestall the front attack by drawing the sword and making a cut to\n" +
                "the top right side of the first opponents head followed by a two-handed thrust to\n" +
                "the solar plexus. Turning next to the rear opponent, bring the sword from above\n" +
                "your head and cut down vertically. Lastly turn once again to the front and cut\n" +
                "the last opponent in the same manner.", "uUDk1nIFbV0", morotezukiGradingPoints));

        ArrayList<GradingPoint> sanpogiriGradingPoints = new ArrayList<>();
        {
            {
                sanpogiriGradingPoints.add(new GradingPoint(0, "11000", "a. Is the initial cut to the first opponent made through the correct diagonal\n" +
                        "angle from the top right side of the head down to the base of the chin?", false));
                sanpogiriGradingPoints.add(new GradingPoint(1, "12000", "b. Is the cut to the opponent on the left performed without hesitation", false));
                sanpogiriGradingPoints.add(new GradingPoint(2, "13000", "Is the sword brought up to Furikaburi with a parrying action and does the last cut finish at the horizontal?", false));

            }
        }
        this.kataList.add(new Kata("7. Nanahonme - Sanpogiri", 7, "Opponents in front, to your right and left intend to attack you while you are\n" +
                "walking along. Draw your sword and immediately cut the first opponent at\n" +
                "your right, from the top of their head down to their chin. Cut the next opponent\n" +
                "to your left by turning to face them and lastly turn back to the front and cut the\n" +
                "last opponent vertically downwards.", "ZC2Zx9DkYG0", sanpogiriGradingPoints));

        ArrayList<GradingPoint> ganmenateGradingPoints = new ArrayList<>();
        {
            {
                ganmenateGradingPoints.add(new GradingPoint(0, "8000", "a. Is the initial strike with the Tsukagashira made between the eyes? ", false));
                ganmenateGradingPoints.add(new GradingPoint(1, "10000", "b. When turning to face the opposite direction, is the right hand placed on\n" +
                        "the hip?", false));
                ganmenateGradingPoints.add(new GradingPoint(2, "11000", "c. When facing the rear opponent, is the body turned completely to the rear\n" +
                        "with the rear heel slightly raised and in a straight line?", false));
                ganmenateGradingPoints.add(new GradingPoint(3, "12000", "d. Is the thrust performed without the rear knee bent?", false));

            }
        }
        this.kataList.add(new Kata("8. Happonme - Ganmenate", 8, "While walking along opponents to the front and behind intend to attack you.\n" +
                "First hit the face of the opponent in front of you with the end of the Tsuka, then,\n" +
                "thrust the tip of the sword into the solar plexus of the opponent at your rear.\n" +
                "Turn back around and cut the first opponent vertically downwards from above\n" +
                "your head.", "R8koKybXMVs", ganmenateGradingPoints));

        ArrayList<GradingPoint> soetezukiGradingPoints = new ArrayList<>();
        {
            {
                soetezukiGradingPoints.add(new GradingPoint(0, "9000", "a. When the initial diagonal cut is made from the opponent's right shoulder\n" +
                        "down through to the waist, is the right hand at the height of the navel and\n" +
                        "the sword tip slightly above the horizontal level?", false));
                soetezukiGradingPoints.add(new GradingPoint(1, "10000", "b. Is the sword held securely between the left thumb and forefinger with the\n" +
                        "right hand near the hip?", false));
                soetezukiGradingPoints.add(new GradingPoint(2, "11000", "c. Does the right hand finish in front of the navel after making the thrust\n" +
                        "and does the thrusting action adequately reach the opponent's body?", false));
                soetezukiGradingPoints.add(new GradingPoint(3, "15000", "d. When showing Zanshin, is the right elbow naturally straight and the right\n" +
                        "hand not higher or lower than the chest level?", false));

            }
        }
        this.kataList.add(new Kata("9. Kyuhonme - Soetezuki", 9, "You are walking along when a person suddenly appears from the left with the\n" +
                "intention to attack you. Stall it by taking the initiative of drawing out your\n" +
                "sword and cutting diagonally through their right shoulder. Then thrust the\n" +
                "sword into their abdomen as you step forward.", "ho219Wq3UUU", soetezukiGradingPoints));

        ArrayList<GradingPoint> shihogiriGradingPoints = new ArrayList<>();
        {
            {
                shihogiriGradingPoints.add(new GradingPoint(0, "4000", "a. Is the strike to the first opponent's hand done firmly and effectively with\n" +
                        "the flat side of the Tsuka?", false));
                shihogiriGradingPoints.add(new GradingPoint(1, "6000", "b. In making Sayabiki, is the Mune near the Monouchi of the sword on the\n" +
                        "chest and is the thrust made surely into the solar plexus of the opponent?", false));
                shihogiriGradingPoints.add(new GradingPoint(2, "7000", "c. When the thrust is made, is the left hand brought to the center of the\n" +
                        "navel and do both arms aid the technique with the correct tension?", false));
                shihogiriGradingPoints.add(new GradingPoint(3, "11000", "d. Is the final cut made by going through Waki Gamae without hesitation or\n" +
                        "pause?", false));

            }
        }
        this.kataList.add(new Kata("10. Ju-pponme - Shihogiri", 10, "You encounter four enemies who intend to attack you. Forestall the first by\n" +
                "using a Tsuka Ate strike to their right hand at the right diagonal front. Then\n" +
                "thrust into the solar plexus of the next opponent at your left diagonal rear.\n" +
                "Continue to vertically cut down the other three by turning 180 degrees to your\n" +
                "right, then 90 degrees to your right and finally 180 degrees to your left.", "MQRiZVpVMBE", shihogiriGradingPoints));

        ArrayList<GradingPoint> sougiriGradingPoints = new ArrayList<>();
        {
            {
                sougiriGradingPoints.add(new GradingPoint(0, "8000", "a. When the sword is drawn up, is it in a correct position to parry an attack?", false));
                sougiriGradingPoints.add(new GradingPoint(1, "10000", "b. When moving forwards, does the performer use Okuri Ashi footwork?", false));
                sougiriGradingPoints.add(new GradingPoint(2, "11000", "c. When making the horizontal cut, is it performed horizontally with the\n" +
                        "correct angle of the blade?", false));

            }
        }
        this.kataList.add(new Kata("11. Ju-ipponme - Sougiri", 11, "While walking along you encounter a person in front of you who intends to attack you. Receive and parry their attack then make a diagonal cut to the left\n" +
                "side of their face. Then continue cutting diagonally through their right shoulder\n" +
                "to the solar plexus and then the left torso. Cut their abdomen across the waist\n" +
                "horizontally from left to right and finally finish with a vertical downwards cut.", "hyGP4n5jQEw", sougiriGradingPoints));

        ArrayList<GradingPoint> nukiuchiGradingPoints = new ArrayList<>();
        {
            {
                nukiuchiGradingPoints.add(new GradingPoint(0, "4000", "a. When the sword is drawn up and out, have both feet moved back\n" +
                        "adequately to evade the downward cut of the opponent?", false));
                nukiuchiGradingPoints.add(new GradingPoint(1, "5000", "b. When the right hand is taken upwards, is it in the center line of the body\n" +
                        "and is the step forwards with the right foot sufficient to enable the sword\n" +
                        "to reach the target? ", false));
            }
        }
        this.kataList.add(new Kata("12. Ju-nihonme - Nukiuchi", 12, "When standing face to face with a person they suddenly try to cut you. You let\n" +
                "them cut the air by stepping back while you draw the sword. Make a vertical downward cut to defeat them.", "GR56Hzs6xcA", nukiuchiGradingPoints));


    }


    public ArrayList<GradingPoint> getGradingPoints(int kataNumber) {
        return kataList.get(kataNumber).getGradingPoints();
    }

    public ArrayList<Kata> getKataList() {
        return this.kataList;
    }

    public int getKataNumber(int i) {
        return kataList.get(i).getKataNumber();
    }

}
