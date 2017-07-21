package com.example.ranim.abode;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.MessageQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private EditText row, col;
    private Button submit1,submit2;
    private LinearLayout main;
    List<List<EditText>> list2_D = new ArrayList<List<EditText>>();
    List<EditText> list1_D = new ArrayList<EditText>();
    List<String>Quest1;
    List<String>Quest2;
    int rows=1;
    int column=1;
    int numberOfGame=1;
    int getOptimal=1;
    int numberOfTry = 0;

    EditText E1;
    TextView T2, T3, T4;
    List<TheQuestionObject> list= new ArrayList<TheQuestionObject>();
    List<List<NumericWord>> ListOfNumericalWord = new ArrayList<List<NumericWord>>();
    List<NumericWord> list_2 = new ArrayList<NumericWord>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //E1 = (EditText)findViewById(R.id.E1);
        T2 = (TextView) findViewById(R.id.T2);
        T3 = (TextView) findViewById(R.id.T3);
        T4 = (TextView) findViewById(R.id.T4);
        main = (LinearLayout) findViewById(R.id.ll);
        main.setOrientation(LinearLayout.VERTICAL);





        NumericWord NumericWordVar = new NumericWord();
        TheQuestionObject NumberOfWord = new TheQuestionObject();
        for(int i = 0; i < 22; i++){
            ListOfNumericalWord.add(new ArrayList<NumericWord>());
        }
        int kCounter = 0;
        for(List<NumericWord> var:ListOfNumericalWord){
            String Name = "Letter" + (kCounter + 2);
            try {
                ListOfNumericalWord.set(kCounter, read_NumericList(Name));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            kCounter++;
        }
        CopyTwoDimensionalArray copyTwoDimensionalArray = new CopyTwoDimensionalArray();
        try {
            copyTwoDimensionalArray = read_TowDimensinalArray();
            NumericWord.NumberOfWord = copyTwoDimensionalArray.getInfo();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        CopyTwoDimensionalArray copyTwoDimensionalArray2 = new CopyTwoDimensionalArray();
        try {
            copyTwoDimensionalArray2 = read_OneDimensinalArray();
            TheQuestionObject.NumberOfWord = copyTwoDimensionalArray2.getInfoOfListNumber();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

           list=read_Temp();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
////////////////////////////////////////////////////////////////////////////////////////////////////


    public void Load_And_Store(View view) {        //read from exsiting file that consiste of dictionary struture and analyse it and save information in new file

        list.clear();
        for(List<NumericWord> var:ListOfNumericalWord){
            var.clear();
        }
        NumericWord.clear();

        TheQuestionObject.clearTheNumber();
        TheQuestionObject w1=new TheQuestionObject();
        List<String> list2;

        String cur="";String pre=" "; String s="";
        String string="";
        try {
            int i = 1;
            int k = 1;
            for (int j = 1; j < 2; j++){
                String nameOfFile1 = new String(Integer.toString(j));
                String Path = new String(nameOfFile1 + ".txt");

                InputStream fis = getAssets().open(Path);
                int size = fis.available();
                byte[] b = new byte[size];
                fis.read(b);
                fis.close();
                string = new String(b);
                Scanner scanFirstWord = new Scanner(string);
                FileOutputStream   foss = this.openFileOutput("PrintContent", this.MODE_PRIVATE);
                ObjectOutputStream ooss = new ObjectOutputStream(foss);
                while (!(cur = scanFirstWord.next()).equals("#")) {
                    char[] charr = cur.toCharArray();
                    if (charr[0] == '(' && (charr[charr.length - 1] == ')' || charr[charr.length - 1] == '.')) {
                        list2 = new ArrayList<String>();
                        s = "";
                        while (!(cur = scanFirstWord.next()).equals("$")) {
                            char[] charrr = cur.toCharArray();
                            if (charrr[charrr.length - 1] == ')') {
                                cur = scanFirstWord.next();
                            }
                            if (cur.indexOf(';') >= 0) { // record the current translate an start a new one
                                s += cur + " ";
                                list2.add(s);
                                s = "";
                                continue;
                            }
                            s += cur + " ";
                        }
                        list2.add(s);
                        w1 = new TheQuestionObject(pre,list2,0);
                        ListOfNumericalWord.get(w1.NumberOfLetter - 2).add(new NumericWord(w1.getWord(), w1.NumberOfLetter));
                        list.add(w1);
                    }
                    pre = cur;
                }
                for (TheQuestionObject var : list) {
                    System.out.println("word : " + i + var.getWord() + " : and the translate : " + var.getTranslate_w());
                    i++;
                }
                i=0;
                save_1(list, Path);
                fis.close();
            }
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            Write_Temp(list);
            Write_OneDimensinalArray(new CopyTwoDimensionalArray(TheQuestionObject.NumberOfWord));
            int kk = 0;

            System.out.println("ListOfNumericalWord : " + ListOfNumericalWord.size());
            for(List<NumericWord> var:ListOfNumericalWord){
                String Name = "Letter" + (kk + 2);
                System.out.println("now : " + var.size());
                Write_NumericList(var, Name);
                kk++;
            }
            int o = 0;
            Write_TowDimensinalArray(new CopyTwoDimensionalArray(NumericWord.NumberOfWord));
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            System.out.println("=======================");
            for(int ii = 0; ii < 22; ii++){
                for(int j = 0; j < 26; j++){
                    System.out.println("Number[" + ii + "], Letter[" + (char)(65+j) + "] = " + NumericWord.NumberOfWord[ii][j]);
                }
            }
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            for(int var:TheQuestionObject.NumberOfWord){
                System.out.println("Number = " + var);
            }
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            System.out.println("********************************************************");
        } catch(Exception e){System.out.println(e.getMessage());}
        //T2.setText(string);
    }

    public List<NumericWord>  read_NumericList(String FileName) throws IOException, ClassNotFoundException {
        NumericWord temp=null;
        List<NumericWord>list1=new ArrayList<NumericWord>();
        FileInputStream fis=this.openFileInput(FileName);
        ObjectInputStream ois=new ObjectInputStream(fis);
        while (fis.available()>0)
        {
            temp=(NumericWord)ois.readObject();
            list1.add(temp);
        }
        fis.close();
        return list1;
    }

    public void Write_NumericList(List<NumericWord> list, String FileName) throws IOException {
        FileOutputStream fos=this.openFileOutput(FileName,this.MODE_PRIVATE);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        for(NumericWord var:list)
        {
            oos.writeObject(var);
            //System.out.println(var.getWord()+"^^");
        }
        fos.close();
    }

    public void Write_Temp(List<TheQuestionObject> list) throws IOException {
        FileOutputStream fos=this.openFileOutput("Temp",this.MODE_PRIVATE);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        for(TheQuestionObject var:list)
        {
            oos.writeObject(var);
            //System.out.println(var.getWord()+"^^");
        }
        fos.close();
    }

    public void Write_TowDimensinalArray(CopyTwoDimensionalArray Obj) throws IOException {
        FileOutputStream fos=this.openFileOutput("Number",this.MODE_PRIVATE);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(Obj);
        fos.close();
    }

    public void Write_OneDimensinalArray(CopyTwoDimensionalArray Obj) throws IOException {
        FileOutputStream fos=this.openFileOutput("Number2",this.MODE_PRIVATE);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(Obj);
        fos.close();
    }

    public CopyTwoDimensionalArray  read_OneDimensinalArray() throws IOException, ClassNotFoundException {
        CopyTwoDimensionalArray temp=null;
        //List<TheQuestionObject>list1=new ArrayList<TheQuestionObject>();
        FileInputStream fis=this.openFileInput("Number2");
        ObjectInputStream ois=new ObjectInputStream(fis);
        if (fis.available()>0)
        {
            temp=(CopyTwoDimensionalArray)ois.readObject();
        }
        fis.close();
        return temp;
    }

    public CopyTwoDimensionalArray  read_TowDimensinalArray() throws IOException, ClassNotFoundException {
        CopyTwoDimensionalArray temp=null;
        //List<TheQuestionObject>list1=new ArrayList<TheQuestionObject>();
        FileInputStream fis=this.openFileInput("Number");
        ObjectInputStream ois=new ObjectInputStream(fis);
        if (fis.available()>0)
        {
            temp=(CopyTwoDimensionalArray)ois.readObject();
        }
        fis.close();
        return temp;
    }

    public List<TheQuestionObject>  read_Temp() throws IOException, ClassNotFoundException {
        TheQuestionObject temp=null;
        List<TheQuestionObject>list1=new ArrayList<TheQuestionObject>();
        FileInputStream fis=this.openFileInput("Temp");
        ObjectInputStream ois=new ObjectInputStream(fis);
        while (fis.available()>0)
        {
                temp=(TheQuestionObject)ois.readObject();
                list1.add(temp);
        }
        fis.close();
        return list1;
    }

    public void save_1(List<TheQuestionObject> list, String Path) {
        int i=0;
        try
        {
            FileOutputStream fos = this.openFileOutput(Path,this.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            i=1;
            for (TheQuestionObject var: list)
            {
                var.setId(i);
                oos.writeObject(var);i++;
            }
            fos.close();
            Load_And_Create(Path);
        } catch (Exception e){}
    }

    public void Show(View view) {
        TheQuestionObject temp = null;
        String s = "";
        List<TheQuestionObject> list = new ArrayList<TheQuestionObject>();
        try {
            FileInputStream fis = this.openFileInput("A1");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() != 0) {
                while (fis.available() > 0) {
                    temp = (TheQuestionObject) ois.readObject();
                    list.add(temp);
                }
            }
            for (TheQuestionObject var:list){
                Log.d("the Translation : " , var.getWord() + " : " + var.getTranslate_w());
            }
        } catch (Exception e) {//T2.setText(e.getMessage());
             }
    }

    //public void Load_And_Create(View view)
    public void Load_And_Create(String Path) {
        int i=0;
        TheQuestionObject temp = null;
        List<TheQuestionObject> list = new ArrayList<TheQuestionObject>();

        try {
            FileInputStream fis = this.openFileInput(Path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available() != 0) {
                while (fis.available() > 0) {
                    temp = (TheQuestionObject) ois.readObject();
                    list.add(temp);
                }
            }

            i=1;
            for(TheQuestionObject var : list) {
                FileOutputStream fos = this.openFileOutput(var.getWord(), this.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(var);
                fos.close();
            }
            fis.close();
        }

        catch (Exception e){//T2.setText(e.getMessage());
             }
    }

    public void Fetch(View view) {
        String name=E1.getText().toString();
        TheQuestionObject temp = null;
        try
        {
            FileInputStream fis = this.openFileInput(name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(fis.available()>0)
            {
                temp = (TheQuestionObject) ois.readObject();
                Log.d("Object word: ",temp.getWord()+" translatr : "+temp.getTranslate_w()+ " id = "+temp.getId());
                //T2.setText("Object word: "+temp.getWord()+"\n"+"translatr : "+temp.getTranslate_w()+"\n"+ "id = "+temp.getId());
                System.out.println(serach_1("Ab").getTranslate_w());
            }
        }
        catch(Exception e){//T2.setText(e.getMessage());
             }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////

    public TheQuestionObject serach_1( String name ) {
        TheQuestionObject temp = null;
        try
        {
                FileInputStream fis = this.openFileInput(name);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while(fis.available()>0)
                {
                    temp = (TheQuestionObject) ois.readObject();
                }
                fis.close();
                if(temp.getWord() == "#"){
                    System.out.println("here");
                }
        }
        catch(Exception e){//T2.setText(e.getMessage());
            List<String> tempNull = new ArrayList<String>();
            temp = new TheQuestionObject("#", tempNull, -1);
        }
        return temp;
    }

    public TheQuestionObject serach_2(int length) {
        TheQuestionObject temp=null;
        String res=randomString(length);
        temp=serach_1(res);
        return temp;
    }

    public String randomString(int length){
        char char1=' ';
        char[] arrya_char=new char[length];
        int i=0;
        Boolean b=true;
        String  res = "";

        if(length == 0) {
            return res;
        }
        Random random =new Random();
        int  r = random.nextInt()%(27);
        res= res+ ((char)(r+'A'));
        for (char c:res.toCharArray()) {
            while (b) {
                if (c >= 'A' && c <= 'Z') {
                    arrya_char[i] = c;
                    b = false;
                } else {
                    r = random.nextInt() % (27);
                    char1 = ((char) (r + 'A'));
                    if (char1 >= 'A' && char1 <= 'Z') {
                        arrya_char[i] = char1;
                        b = false;
                    }

                }
            }
        }
        for( i=1 ; i < length ; i++){
            r = random.nextInt()%(27);
            res=res +( (char)(r+'a'));
            for(char c:res.toCharArray()) {

                if (c >= 'a' && c <= 'b') {
                    arrya_char[i] = c;
                }
                else
                {
                    r = random.nextInt() % (27);
                    char1 = ((char) (r + 'a'));
                    if (char1 >= 'a' && char1 <= 'z')
                    {
                        arrya_char[i] = char1;
                    }
                    else
                    {
                        r = random.nextInt() % (27);
                        char1 = ((char) (r + 'a'));
                        if(char1 >= 'a' && char1 <= 'z')
                        {
                            arrya_char[i]=char1;
                        }
                    }
                }
            }
        }
        res="";
        for(int k=0 ; k < arrya_char.length ; k++ )
        {
            res += arrya_char[k];
        }
        return  res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////



    int[] FindThePlaceThatWeNeedToAdd_A_New_Word(TheGame GameObject){
        int MaximumSizeOfWord = 24;
        int[] Row_FirstColumns_LastColumns = new int[3];
        for(int i = 0; i < GameObject.Rows; i++){
            for(int j = 0; j < GameObject.Columns; j++){
                if(GameObject.TheGame[i][j] == '$'){
                    Row_FirstColumns_LastColumns[0] = i;
                    Row_FirstColumns_LastColumns[1] = j;
                    int k;
                    for(k = j+1; k < GameObject.Columns && ((k - Row_FirstColumns_LastColumns[1] + 1) < MaximumSizeOfWord); k++){
                        if(GameObject.TheGame[i][k] != '$'){
                            Row_FirstColumns_LastColumns[2] = k-1;
                            return Row_FirstColumns_LastColumns;
                        }
                    }
                    Row_FirstColumns_LastColumns[2] = k-1;
                    return Row_FirstColumns_LastColumns;
                }
            }
        }
        return Row_FirstColumns_LastColumns;
    }

    static String reverseIt(String source) {
        int i, len = source.length();
        StringBuilder dest = new StringBuilder(len);
        for (i = (len - 1); i >= 0; i--){
            dest.append(source.charAt(i));
        }

        return dest.toString();
    }

    static String getStringRepresentation(List<Character> list){
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list){
            builder.append(ch);
        }
        return builder.toString();
    }

    String[] FindThePrefixWord(int[] PlaceToAddIn, TheGame GameObject){
        String[] Words = new String[PlaceToAddIn[2] - PlaceToAddIn[1] + 1];
        int count = 0;
        for (int i = PlaceToAddIn[1]; i <= PlaceToAddIn[2]; i++){
            List<Character> Obj = new ArrayList<Character>();
            for (int j = PlaceToAddIn[0] - 1; j >= 0; j--){
                if(GameObject.TheGame[j][i] == '#'){
                    break;
                }
                Obj.add(GameObject.TheGame[j][i]);
            }
            //String temp = getStringRepresentation(Obj);
            Words[count++] = reverseIt(getStringRepresentation(Obj));
            //Words[i] = reverseIt(Words[i]);
        }
        return Words;
    }

    char comparefirst(char[] CurrentWord, char[] OurWord){
        if(CurrentWord.length <= OurWord.length) return '#';
        int i = 0;
        boolean ok = true;
        for (char var:OurWord){
            if(var != CurrentWord[i++]){
                ok = false;
                return '#';
            }
        }
        return CurrentWord[i];
    }

    ArrayList<ArrayList<Character>> GetWordThatWeCanUse(String[] PrefixWords){

        ArrayList<ArrayList<Character>> list1 = new ArrayList<ArrayList<Character>>();
        String[] Clone = new String[PrefixWords.length];
        Clone = PrefixWords.clone();
        int numberOfPorccessedWord = 0;

        for (String var:Clone){

            boolean goToTheDict = false;
            ArrayList<Character> list2 = new ArrayList<Character>();
            TheQuestionObject Ques = new TheQuestionObject();

            if(var.length() == 0 || var.length() == 1){
                for(int i = 0; i < 26; i++) {
                    list2.add((char)(i + 97));
                }
                numberOfPorccessedWord++;
                list1.add(list2);
                continue;
            }
            int j = 0;
            do{
                var = var.substring(0, var.length()-j);
                if(var.length() == 2){
                    goToTheDict = true;break;
                }
                Ques = new TheQuestionObject(serach_1(var));
                j = 1;
            } while (Ques.getWord().equals("#"));

            if(!goToTheDict) {              // if the prefix has been found in dictionary
                char c;
                boolean firstTime = true, goOut = false;

                char[] ourWord = PrefixWords[numberOfPorccessedWord].toCharArray();
                for (int i = Ques.getId() - 10; i < list.size(); i++) {
                    if (i < 0) {
                        i = 0;
                        firstTime = false;
                    }
                    char[] currWord = list.get(i).getWord().toCharArray();
                    c = comparefirst(currWord, ourWord);
                    if (c == '#') { // Not Similar
                        if (firstTime == true) {
                            firstTime = false;
                        }
                        if (goOut == true) {
                            break;
                        }
                    }
                    if (c != '#') { // Similar
                        if (firstTime == true) {
                            i -= 10;
                        }
                        if (firstTime != true) {
                            goOut = true;
                            list2.add(c);
                        }
                    }
                }
            }else{
                char c;
                boolean firstTime = true, goOut = false, dontGoFront = false, secondBack = false;

                char[] ourWord = PrefixWords[numberOfPorccessedWord].toCharArray();

                int[] numberOfLetter = new int[26];
                for(int i = 0; i < list.size(); i+= 1){
                    char[] currWord = list.get(i).getWord().toCharArray();
                    while(currWord[0] != ourWord[0]) {
                        int iii = currWord[0] - 97;
                        if(iii >= 0 && iii <= 25) {
                            if (numberOfLetter[iii] == 0) {
                                numberOfLetter[iii] = 1;
                                i += TheQuestionObject.NumberOfWord[iii];
                                if (i >= list.size()) {
                                    break;
                                } else {
                                    currWord = list.get(i).getWord().toCharArray();
                                }
                            } else {
                                i++;
                                if (i >= list.size()) {
                                    i--;
                                    break;
                                } else {
                                    currWord = list.get(i).getWord().toCharArray();
                                }
                            }
                        }
                    }
                    int base = i;
                    if(i >= list.size()){
                        break;
                    }else {
                        c = comparefirst(currWord, ourWord);
                        int differance = list.get(i).getWord().compareTo(PrefixWords[numberOfPorccessedWord]);

                        if (c == '#') { // Not Similar
                            if (goOut == true) {
                                break;
                            }
                            if (differance > 0) {     //  if cuurent word is after the our word ((((((*********)))))))
                                if(secondBack) break;
                                secondBack = true;
                                i -= 100;
                                if (i < 0) i = 0;
                                dontGoFront = true;
                            }
                            if (differance < 0 && !dontGoFront) {     //  if current word is before the our word
                                i += 100;
                            }
                        }
                        if (c != '#') { // Similar
                            if (!dontGoFront) {
                                i -= 100;
                                if (i < 0) i = 0;
                                dontGoFront = true;
                            } else {
                                goOut = true;
                                list2.add(c);
                            }
                        }
                    }
                }
            }

            list1.add(list2);
            Set<Character> Sett = new HashSet<>();
            Sett.addAll(list2);
            list2.clear();
            list2.addAll(Sett);

            numberOfPorccessedWord++;
        }
        return list1;
    }

    List<String> FindCompatibleWord(PropableChar propableChar, int NumberOfEmptyBox, List<BlankPoint> BlankPoints, TheGame GameObject, int[] Row_FirstColumns_LastColumns){

        List<String> CompatibleWord = new ArrayList<String>();
        int close = 0;
        boolean closefromEnd = true;
        for(int i = 0; i < ListOfNumericalWord.get(NumberOfEmptyBox - 2).size(); i++){
            closefromEnd = false;
            char[] charr = ListOfNumericalWord.get(NumberOfEmptyBox - 2).get(i).Word.toCharArray();
            boolean TheWordIsCompatible = true;
            boolean[] TheCompatibleLetter = new boolean[NumberOfEmptyBox];
            for(int j = 0; j < NumberOfEmptyBox; j++) {
                if(j > close){
                    close = j;
                }
                for (char var : propableChar.listOLists.get(j)) {
                    if (charr[j] == var) {
                        TheCompatibleLetter[j] = true;
                        break;
                    }
                }
                if(j != 0 && TheCompatibleLetter[j] != true){
                    break;
                }
                if(j == 0 && TheCompatibleLetter[j] != true){
                    int k = ((int)charr[0]) - 97;
                    if(k >= 0 && k <= 25)
                        i += NumericWord.NumberOfWord[NumberOfEmptyBox - 2][k] - 1;
                    break;
                }
            }
            for(boolean var:TheCompatibleLetter){
                if(!var){
                    TheWordIsCompatible = false;
                }
            }
            if(TheWordIsCompatible){
                CompatibleWord.add(ListOfNumericalWord.get(NumberOfEmptyBox - 2).get(i).Word);
                if(CompatibleWord.size() > 8) break;/////////////////////////////////////////////////////
            }
        }

        if(CompatibleWord.size() == 0 && closefromEnd == false){
            GameObject.TheGame[Row_FirstColumns_LastColumns[0]][Row_FirstColumns_LastColumns[1] + close] = '#';
            BlankPoints.add(new BlankPoint(Row_FirstColumns_LastColumns[0], Row_FirstColumns_LastColumns[1] + close));
        }
        if(CompatibleWord.size() == 0 && closefromEnd == true){
            GameObject.TheGame[Row_FirstColumns_LastColumns[0]][Row_FirstColumns_LastColumns[2]] = '#';
            BlankPoints.add(new BlankPoint(Row_FirstColumns_LastColumns[0], Row_FirstColumns_LastColumns[2]));
        }
        return CompatibleWord;
    }

    int numberOfBox(TheGame GameObject, int[] Row_FirstColumns_LastColumns){
        return Row_FirstColumns_LastColumns[2] - Row_FirstColumns_LastColumns[1] + 1;
    }

    PropableChar GetPropableChar(TheGame GameObject, int[] Row_FirstColumns_LastColumns){
        String[] PrefixWords = FindThePrefixWord(Row_FirstColumns_LastColumns, GameObject);
        return new PropableChar(GetWordThatWeCanUse(PrefixWords));
    }

    boolean checkRepitedly(TheGame GameObject, String currentWord){
        //GameObject.ValidAswer.get(0)
        String s = new String();
        for(int i = 0; i < GameObject.TheGame.length; i++){
            for(int j = 0; j < GameObject.TheGame[i].length; j++){
                if(GameObject.TheGame[i][j] != '$' && GameObject.TheGame[i][j] != '#'){
                    s += GameObject.TheGame[i][j];
                }else if (GameObject.TheGame[i][j] == '#'){
                    if(s.length() > 0) {
                        if(currentWord.equals(s)){
                            return false;
                        }
                        s = new String();
                    }
                }
            }
        }
        int o = 0;
        return true;
    }

    TheQuestionObject[] GetTheQuestionToAdd(PropableChar propableChar, int NumberOfEmptyBox, List<BlankPoint> BlankPoints, TheGame GameObject, int[] Row_FirstColumns_LastColumns){
        List<String> tempVar = new ArrayList<String>();
        tempVar = FindCompatibleWord(propableChar, NumberOfEmptyBox, BlankPoints, GameObject, Row_FirstColumns_LastColumns);

        TheQuestionObject[] theQuestionObjects = new TheQuestionObject[tempVar.size()];

        for(int i = 0; i < tempVar.size(); i++){
            System.out.println("hey");
            String temp = tempVar.get(i);
            theQuestionObjects[i] = serach_1(tempVar.get(i));
        }

        return theQuestionObjects;
    }

    boolean checkIfThisAnswerIsAvailable(int array[], int index){
        for (int j = 0 ; j<=3 ; j++){
            if (array[j] == index){
                return false;
            }
        }
        return true;
    }

    boolean checkIfThisAnAnswer(TheGame GameObject){
        for (int i = 1; i < GameObject.Rows; i++){
            for (int j = 0; j < GameObject.Columns; j++){
                if(GameObject.TheGame[i][j] == '$') return false;
            }
        }
        return true;
    }

    void AddWord(TheGame GameObject, TheQuestionObject theQuestionObject, int[] Row_FirstColumns_LastColumns, List<BlankPoint> BlankPoints){
        char[] charr = theQuestionObject.getWord().toCharArray();
        for(int i = Row_FirstColumns_LastColumns[1], j = 0; i <= Row_FirstColumns_LastColumns[2]; i++, j++){
            GameObject.TheGame[Row_FirstColumns_LastColumns[0]][i] = charr[j];
        }

        if(Row_FirstColumns_LastColumns[2] < GameObject.Columns - 2){
            GameObject.TheGame[Row_FirstColumns_LastColumns[0]][Row_FirstColumns_LastColumns[2] + 1] = '#';
            BlankPoints.add(new BlankPoint(Row_FirstColumns_LastColumns[0], Row_FirstColumns_LastColumns[2] + 1));
        }
    }

    void backTheLatestAnswer(TheGame GameObject, int[] Row_FirstColumns_LastColumns, List<BlankPoint> BlankPoints){
        for(int i = Row_FirstColumns_LastColumns[1], j = 0; i <= Row_FirstColumns_LastColumns[2]; i++, j++){
            GameObject.TheGame[Row_FirstColumns_LastColumns[0]][i] = '$';
        }
        for(BlankPoint var:BlankPoints){
            GameObject.TheGame[var.x][var.y] = '$';
        }
        BlankPoints.clear();
    }

    boolean checkNumberOfEmptyBox(int NumberOfEmptyBox, int[] Row_FirstColumns_LastColumns, TheGame GameObject, List<BlankPoint> BlankPoints){
        if(NumberOfEmptyBox == 1){
            GameObject.TheGame[Row_FirstColumns_LastColumns[0]][Row_FirstColumns_LastColumns[1]] = '#';
            BlankPoints.add(new BlankPoint(Row_FirstColumns_LastColumns[0], Row_FirstColumns_LastColumns[1]));
            return true;
        }
        return false;
    }
    boolean checkPropableChar(PropableChar propableChar, int[] Row_FirstColumns_LastColumns, TheGame GameObject, List<BlankPoint> BlankPoints) {
        int columns = 0;
        boolean in = false;
        for (ArrayList<Character> var : propableChar.listOLists) {
            if(var.size() == 0){
                GameObject.TheGame[Row_FirstColumns_LastColumns[0]][Row_FirstColumns_LastColumns[1] + columns] = '#';
                in = true;
                BlankPoints.add(new BlankPoint(Row_FirstColumns_LastColumns[0], Row_FirstColumns_LastColumns[1] + columns));
                //break;
            }
            columns++;
        }
        if(in)return true;
        return false;
    }

    void optimalSolution(char[][] latestArray, TheGame GameObject, int numberOfBlankBox){
        int max = 0, maxIndex = 0;
        for(int i = 0; i < GameObject.ValidAswer.size(); i++){
            if (max < GameObject.numberOfValidSolution.get(i)){
                max = GameObject.numberOfValidSolution.get(i);
                maxIndex = i;
            }
        }
        if(numberOfBlankBox < GameObject.numberOfValidSolution.get(maxIndex)) {
            char[][] New = TheGame.Clone(latestArray);
            GameObject.ValidAswer.remove(maxIndex);
            GameObject.ValidAswer.add(New);
        }
    }

    boolean checkIfTheAddingFromZeroRow(TheQuestionObject[] ObjLetterToAdd, int[] Row_FirstColumns_LastColumns){
        if (Row_FirstColumns_LastColumns[0] == 0) {
            ObjLetterToAdd = new TheQuestionObject[0];
            return true;
        }
        return false;
    }

    boolean checkNumberOfBlankBox(int num1, int num2){
        if(num1 > num2)return true;
        return false;
    }

    boolean checkIfAllIsValid(TheQuestionObject[] ObjLetterToAdd){
        boolean bool = true;
        for (TheQuestionObject var : ObjLetterToAdd) {
            if (!var.getWord().equals("#")) {
                bool = false;
            }
        }
        return bool;
    }

    void tryy(TheGame GameObject){
        if(GameObject.numberOfBlankBox < GameObject.foundedSolution && numberOfTry < ((numberOfGame*rows*column*5*4) / 1000)) {
            List<BlankPoint> BlankPoints = new ArrayList<BlankPoint>();
            int k = 0;
            TheQuestionObject[] ObjLetterToAdd = null;
            int NumberOfEmptyBox = 0;
            int[] Row_FirstColumns_LastColumns = {0, 0, 0};
            boolean returnBack;
            do {
                do {
                    returnBack = false;

                    Row_FirstColumns_LastColumns = FindThePlaceThatWeNeedToAdd_A_New_Word(GameObject);


                    if (Row_FirstColumns_LastColumns[0] == 0) {
                        ObjLetterToAdd = new TheQuestionObject[0];
                        break;
                    }

                    NumberOfEmptyBox = numberOfBox(GameObject, Row_FirstColumns_LastColumns);

                    if (returnBack = checkNumberOfEmptyBox(NumberOfEmptyBox, Row_FirstColumns_LastColumns, GameObject, BlankPoints))
                        continue;

                    PropableChar propableChar = GetPropableChar(GameObject, Row_FirstColumns_LastColumns);

                    if (returnBack = checkPropableChar(propableChar, Row_FirstColumns_LastColumns, GameObject, BlankPoints))
                        continue;

                    do {
                        if (GameObject.numberOfBlankBox >= GameObject.foundedSolution) break;
                        Row_FirstColumns_LastColumns[2] = Row_FirstColumns_LastColumns[2] - k;
                        if (Row_FirstColumns_LastColumns[2] < Row_FirstColumns_LastColumns[1])
                            break;
                        NumberOfEmptyBox = NumberOfEmptyBox - k;
                        if (returnBack = checkNumberOfEmptyBox(NumberOfEmptyBox, Row_FirstColumns_LastColumns, GameObject, BlankPoints))
                            continue;
                        int numberOfBlankBox = BlankPoints.size();
                        ObjLetterToAdd = GetTheQuestionToAdd(propableChar, NumberOfEmptyBox, BlankPoints, GameObject, Row_FirstColumns_LastColumns);
                        int numberOfBlankBox1 = BlankPoints.size();

                        if(returnBack = checkNumberOfBlankBox(numberOfBlankBox1, numberOfBlankBox)) break;

                        boolean nothing = checkIfAllIsValid(ObjLetterToAdd);

                        if (nothing) {
                            GameObject.TheGame[Row_FirstColumns_LastColumns[0]][Row_FirstColumns_LastColumns[2]] = '#';
                            BlankPoints.add(new BlankPoint(Row_FirstColumns_LastColumns[0], Row_FirstColumns_LastColumns[2]));
                            returnBack = true;
                            break;
                        }
                        propableChar.listOLists.remove(propableChar.listOLists.size() - 1);
                        k++;
                    } while (ObjLetterToAdd.length == 0);

                } while (returnBack);

                for (TheQuestionObject var : ObjLetterToAdd) {
                    if (!var.getWord().equals("#")) {
                        if (checkRepitedly(GameObject, var.getWord())) {
                            System.out.println("here : " + var.getWord());
                            AddWord(GameObject, var, Row_FirstColumns_LastColumns, BlankPoints);
                            tryy(GameObject);
                            backTheLatestAnswer(GameObject, Row_FirstColumns_LastColumns, BlankPoints);
                        }
                    }
                }
                numberOfTry++;
                System.out.println("numberOfTry = " + numberOfTry + " (numberOfGame : " + numberOfGame + ") (rows : " + rows + ") (column : " + column + ") (((numberOfGame*rows*column*4) / 100) : " + ((numberOfGame*rows*column*4) / 100));
                if (checkIfThisAnAnswer(GameObject)) {
                    System.out.println("valid : ");
                    GameObject.PrinInfo();
                    if (GameObject.ValidAswer.size() < GameObject.OptimalSolution) {
                        GameObject.addd(GameObject.TheGame);
                    } else {
                        GameObject.numberOfBlankBox++;
                        optimalSolution(GameObject.TheGame, GameObject, GameObject.numberOfBlankBox(GameObject.TheGame));
                    }
                }

                int nn = Row_FirstColumns_LastColumns[2];
                int nnn = NumberOfEmptyBox;
                if (nnn < 3) {
                    break;
                }
                if (nnn >= 3) {
                    GameObject.TheGame[Row_FirstColumns_LastColumns[0]][Row_FirstColumns_LastColumns[2]] = '#';
                    BlankPoints.add(new BlankPoint(Row_FirstColumns_LastColumns[0], Row_FirstColumns_LastColumns[2]));
                }

            }while(GameObject.numberOfBlankBox < GameObject.foundedSolution && numberOfTry < ((numberOfGame*rows*column*5*4) / 1000));

        }
    }
    void GetQuestion(TheGame GameObject){
        GameObject.PosArray1 = new int[GameObject.Rows][GameObject.Columns];
        GameObject.PosArray2 = new int[GameObject.Rows][GameObject.Columns];
        int[][] PosArray3 = new int[GameObject.Columns][GameObject.Rows];

        Quest1 = new ArrayList<String>();
        Quest2 = new ArrayList<String>();

        for(char[][] var:GameObject.ValidAswer){
            /**************/
            int count1 = 0;
            for (int i = 0; i < GameObject.Rows; i++){
                String s = new String();
                for(int j = 0; j < GameObject.Columns; j++){
                    if(var[i][j] != '#'){
                        GameObject.PosArray1[i][j] = count1;
                        s += var[i][j];
                    }else{
                        if(s.length() > 1) {
                            GameObject.OneGameQues1.add(s);
                            TheQuestionObject ss = serach_1(s);
                            if(ss.getTranslate_w().get(0) != "#"){
                                Quest1.add(ss.getTranslate_w().get(0));
                            }else{
                                Quest1.add(Quest1.get(count1-1));
                            }
                            count1++;
                        }
                        s = new String();
                        GameObject.PosArray1[i][j] = -1;
                    }
                }
            }
            GameObject.Ques1.add(GameObject.OneGameQues1);
            GameObject.OneGameQues1 = new ArrayList<String>();
            /**************/
            int count2 = 0;
            for (int i = 0; i < GameObject.Columns; i++){
                String s = new String();
                for(int j = 0; j < GameObject.Rows; j++){
                    if(var[j][i] != '#'){
                        PosArray3[i][j] = count2;
                        s += var[j][i];
                    }else{
                        if(s.length() > 1) {
                            GameObject.OneGameQues2.add(s);
                            TheQuestionObject ss = serach_1(s);
                            if(ss.getTranslate_w().size() == 0){
                                if(count2 == 0){
                                    Quest2.add(serach_1("acceptedly").getTranslate_w().get(0));
                                }else {
                                    Quest2.add(Quest2.get(count2 - 1));
                                }
                            }else if(ss.getTranslate_w().get(0) != "#"){
                                Quest2.add(ss.getTranslate_w().get(0));
                            }else{
                                if(count2 == 0){
                                    Quest2.add(serach_1("acceptedly").getTranslate_w().get(0));
                                }else {
                                    Quest2.add(Quest2.get(count2 - 1));
                                }
                            }
                            count2++;
                        }
                        s = new String();
                        PosArray3[i][j] = -1;
                    }
                }
            }
            GameObject.Ques2.add(GameObject.OneGameQues2);
            GameObject.OneGameQues2 = new ArrayList<String>();
            /**************/
        }
        for(int i = 0; i < GameObject.Rows; i++){
            for(int j = 0; j < GameObject.Columns; j++){
                GameObject.PosArray2[i][j] = PosArray3[j][i];
            }
        }
        int o = 0;
    }

    public void Draw( int row, int col, TheGame GameObject){
        int o = 0;
        if(GameObject.ValidAswer.size() > 0) {
            char[][] theGame = GameObject.ValidAswer.get(0);
            Display display = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            int width = display.getWidth() / 20;
            for (int i = 1; i < row-1; i++) {

                LinearLayout l = new LinearLayout(this);
                l.setOrientation(LinearLayout.HORIZONTAL);
                l.setPadding(5, 5, 0, 0);
                list1_D = new ArrayList<EditText>();
                for (int j = 1; j < col-1;j++) {
                    EditText e = new EditText(this);

                    int maxLength = 1;
                    InputFilter[] FilterArray = new InputFilter[1];
                    FilterArray[0] = new InputFilter.LengthFilter(maxLength);
                    e.setFilters(FilterArray);

                    e.setId(set_index(i, j));
                    e.setBackgroundResource(R.drawable.style_animation2);
                    if (theGame[i][j] == '#')
                    {
                        e.setTextColor(getResources().getColor(R.color.black));
                        e.setEnabled(false);
                        e.setText(""+theGame[i][j]);
                        e.setBackgroundResource(R.drawable.black);
                    }
                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, FrameLayout.LayoutParams.WRAP_CONTENT);
                    l.addView(e, lp);
                    list1_D.add(e);
                }
                list2_D.add(list1_D);
                main.addView(l);
            }
        }
    }
    public void Comperto (char[][] c) {
        int tureVal = 0, wrongVal = 0;
        for(int i = 1; i < rows + 1; i++)
        {
            for (int j = 1; j < column + 1; j++)
            {
                char[] s = new char[1];
                EditText e =(EditText)list2_D.get(i-1).get(j-1);
                if(e.getText().toString().matches("")){
                    list2_D.get(i-1).get(j-1).setBackgroundResource(R.drawable.red);
                    wrongVal++;
                }else {
                    s = e.getText().toString().toCharArray();

                    if ((c[i][j] == s[0]) && (c[i][j] != '#')) {
                        list2_D.get(i - 1).get(j - 1).setBackgroundResource(R.drawable.green);
                        tureVal++;
                    } else {
                        list2_D.get(i - 1).get(j - 1).setBackgroundResource(R.drawable.red);
                        wrongVal++;
                    }
                    if ((c[i][j] == s[0]) && (c[i][j] == '#')) {
                        wrongVal--;
                        list2_D.get(i - 1).get(j - 1).setBackgroundResource(R.drawable.black);
                    }
                }
            }
        }
        int all = tureVal + wrongVal;
        float trueValPerCent = (100 * tureVal) / all;
        float wrongValPerCent = (100 * wrongVal) / all;
        System.out.println("game " + rows + "x" + column + " : all : (" + all + " ) numberOfTrue : (" + tureVal + " )" + "in PerCent : ( " + trueValPerCent + " ) numberOfWrong : ( " + wrongVal + " ) in PerCent : ( " + wrongValPerCent + " )**");
        /////////////////////////////////////////////////////////////////////////////////////////////////
        String content = "\n\ngame " + rows + "x" + column + " : all : (" + all + " ) numberOfTrue : (" + tureVal + " )" + "in PerCent : ( " + trueValPerCent + " ) numberOfWrong : ( " + wrongVal + " ) in PerCent : ( " + wrongValPerCent + " )";
        String temp = "";
        List <String>list=new ArrayList<String>();

        try {

            FileInputStream fis = this.openFileInput("PrintContent");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available() != 0) {
                while (fis.available() > 0) {
                    temp = (String) ois.readObject();
                    list.add(temp);
                }
            }

            FileOutputStream   fos = this.openFileOutput("PrintContent", this.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for(String var : list) {
                oos.writeObject(var);
            }
            oos.writeObject(content);
            fos.close();
            fis.close();
        }
        catch (Exception e){//T2.setText(e.getMessage());
             }
    }

    public void fetchToFile() {
        String temp = null;
        String s = "";
        List<String> listtt = new ArrayList<String>();
        try {
            FileInputStream fis = this.openFileInput("PrintContent");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() != 0) {
                while (fis.available() > 0) {
                    temp = (String) ois.readObject();
                    listtt.add(temp);
                }
            }
            T2.setText(""+listtt);
        } catch (Exception e) {
        }
    }

    public void  textView (){
        TextView T1=new TextView(this);
        T1.setTextColor(getResources().getColor(R.color.white));
        T1.setGravity(Gravity.CENTER);
        T1.setText("Rows : " +rows+"  Cols : "+column);
        T1.setBackgroundResource(R.drawable.black);
        LinearLayout.LayoutParams lp_t = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_t.setMargins(10,10,10,10);
        T1.setLayoutParams(lp_t);
        main.addView(T1);

    }
    public Button  submit (){
        Button submit=new Button(this);
        submit.setGravity(Gravity.CENTER);
        submit.setBackgroundResource(R.drawable.style1);
        submit.setText("Done");
        LinearLayout.LayoutParams lp_b = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_b.setMargins(225, 10, 10, 10);
        submit.setLayoutParams(lp_b);
        main.addView(submit);
        return submit;
    }
    public EditText E(){
        EditText editText1 =new EditText(this);
        editText1.setId(set_index(2,2));
        main.addView(editText1);
        return editText1;
    }

    char[][] getTheGame(int row, int col){
        char k='#';
        char[][] to = new char[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                to[i][j]=k;
                k++;
            }
        }
        return to;
    }
    void color(TheGame GameObject, int index){
        int x1 = index / 1000;
        int x2 = index % 1000;
        int vertical = GameObject.PosArray1[x1][x2];
        int horizontal = GameObject.PosArray2[x1][x2];
        for(int i = 1; i < GameObject.Rows - 1; i++){
            for(int j = 1; j < GameObject.Columns - 1; j++){
                //list2_D.get(i - 1).get(j - 1).setBackgroundResource(R.drawable.style_animation2);
                if(GameObject.ValidAswer.get(0)[i][j] != '#'){
                    //list2_D.get(i - 1).get(j - 1).setBackgroundResource(R.drawable.red);
                    list2_D.get(i - 1).get(j - 1).setBackgroundResource(R.drawable.style_animation2);
                }else{
                    list2_D.get(i - 1).get(j - 1).setBackgroundResource(R.drawable.black);
                }
            }
        }
        for(int i = 0; i < GameObject.Rows; i++){
            for(int j = 0; j < GameObject.Columns; j++){
                //list2_D.get(i - 1).get(j - 1).setBackgroundResource(R.drawable.style_animation2);
                if(vertical == GameObject.PosArray1[i][j] || horizontal == GameObject.PosArray2[i][j]){
                    //list2_D.get(i-1).get(j-1).setBackgroundResource(R.drawable.green);
                    list2_D.get(i-1).get(j-1).setBackgroundResource(R.drawable.style_animation3);
                }
            }
        }
    }
    void writeQues(TheGame GameObject, int index){
        int x1 = index / 1000;
        int x2 = index % 1000;
        int vertical = GameObject.PosArray1[x1][x2];
        int horizontal = GameObject.PosArray2[x1][x2];
        T3.setText("Verticaly : " + Quest1.get(vertical));
        T4.setText("Horizontaly : " + Quest2.get(horizontal));
    }


    public int set_index(int i, int j)
    {
        return i * 1000 + j ;
    }

    public void transmission(View view) {
        main.removeAllViews();
        main.setBackgroundResource(R.drawable.back2);

        final EditText row = new EditText(this);
        row.setBackgroundResource(R.drawable.style2);
        row.setHint("Enter the number of Rows");
        row.setGravity(Gravity.CENTER);
        row.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams lp_r = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_r.setMargins(10, 100, 10, 10);
        row.setLayoutParams(lp_r);

        final EditText col = new EditText(this);
        col.setBackgroundResource(R.drawable.style2);
        col.setHint("Enter the Number of Columns");
        col.setGravity(Gravity.CENTER);
        col.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams lp_c = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_c.setMargins(10, 10, 10, 10);
        col.setLayoutParams(lp_c);

        final Button submit1 = new Button(this);
        row.setGravity(Gravity.CENTER);
        submit1.setBackgroundResource(R.drawable.style2);
        submit1.setText("Begin to play");
        LinearLayout.LayoutParams lp_b = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_b.setMargins(150, 10, 10, 10);
        submit1.setLayoutParams(lp_b);
        /**************************************************************************************/
        final EditText numberofget = new EditText(this);
        numberofget.setBackgroundResource(R.drawable.style2);
        numberofget.setHint("Enter the number of ...");
        numberofget.setGravity(Gravity.CENTER);
        numberofget.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams lp_n = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_r.setMargins(10, 100, 10, 10);
        numberofget.setLayoutParams(lp_r);
        main.addView(numberofget);

        final EditText gameoptimal = new EditText(this);
        gameoptimal.setBackgroundResource(R.drawable.style2);
        gameoptimal.setHint("Enter the Number of ....");
        gameoptimal.setGravity(Gravity.CENTER);
        gameoptimal.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams lp_g = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_c.setMargins(10, 10, 10, 10);
        gameoptimal.setLayoutParams(lp_c);
        main.addView(gameoptimal);

        Button submit2 = new Button(this);
        row.setGravity(Gravity.CENTER);
        submit2.setBackgroundResource(R.drawable.style2);
        submit2.setText("Next to page ");
        LinearLayout.LayoutParams lp_s = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_b.setMargins(225, 10, 10, 10);
        submit2.setLayoutParams(lp_b);
        main.addView(submit2);

        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                numberOfGame = Integer.parseInt(numberofget.getText().toString());
                getOptimal = Integer.parseInt(gameoptimal.getText().toString());
                main.removeAllViews();
                main.addView(row);
                main.addView(col);
                main.addView(submit1);
                submit1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        main.setBackgroundResource(R.drawable.black);
                        rows = Integer.parseInt(row.getText().toString());
                         column = Integer.parseInt(col.getText().toString());
                         final TheGame GameObject = new TheGame(rows, column, getOptimal, numberOfGame);
                         tryy(GameObject);
                         GameObject.PrintListOfValidGame();
                         main.removeAllViews();
                         textView();
                         Draw(rows + 2, column + 2, GameObject);
                         GetQuestion(GameObject);
                         Button submit2 = submit();
                submit2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<List<EditText>> finall = new ArrayList<List<EditText>>();
                        if(GameObject.ValidAswer.size() > 0) {
                            char[][] theGame = GameObject.ValidAswer.get(0);
                            Comperto(theGame);
                        }
                    }
                });
                        Button submit3 = submit();
                        submit3.setText("Score");
                        submit3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                fetchToFile();
                            }
                        });

                main.addView(T3);
                main.addView(T4);
                main.addView(T2);
                T2.setText(" ");
                T3.setText(" ");
                T4.setText(" ");


                for(int i = 1; i < rows + 1; i++) {
                    for (int j = 1; j < column + 1; j++) {
                        final EditText e =(EditText)list2_D.get(i-1).get(j-1);
                        System.out.println("hhhh : ( " + e.getId() + " )");
                        e.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                color(GameObject, e.getId());
                                writeQues(GameObject, e.getId());
                                int o = 0;
                                System.out.println("hhhh : ( " + e.getId() + " )");
                                //T2.setText("hhhh : ( " + e.getId() + " )");
                                return false;

                            }
                       });
                    }
                }
/*
                final EditText editText1 = E();

                editText1.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        T2.setText(""+editText1.getId());
                        return false;

                    }
                });
*/
                    }
                });

            }

        });
       }
}






