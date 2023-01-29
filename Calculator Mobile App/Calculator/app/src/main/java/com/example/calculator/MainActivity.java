package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView WorkingPlace;
    TextView ResultsPlace;
    String workings = "";
    String Formula = "";
    String TempFormula = "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INITTexts();







    }



    public void INITTexts(){

         WorkingPlace = (TextView) findViewById(R.id.WorkingTextView);
         ResultsPlace = (TextView) findViewById(R.id.ReslutsTextView);
    }

    public void setworkings (String gvenValues){
        workings = workings + gvenValues;
        WorkingPlace.setText(workings);
    }





    Boolean leftBraket = true;
    public void ParntehsisButton(View view) {
        if(leftBraket == true){
            setworkings("(");
            leftBraket = false;
        }
        else{
            setworkings(")");
            leftBraket = true;
        }
    }




    public void ClearButton(View view) {
        WorkingPlace.setText("");
        workings = "";
        ResultsPlace.setText("");
        leftBraket = true;

    }







    public void ButtonEquals(View view) {

        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        CheckForPower();

        try {
            result = (double)engine.eval(Formula);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invlid input",Toast.LENGTH_SHORT).show();
        }

        if (result !=  null){
            ResultsPlace.setText(String.valueOf(result.doubleValue()));
        }

    }

    private void CheckForPower() {
        ArrayList <Integer> indexOfPowers = new ArrayList<>();

        for(int i =0; i < workings.length(); i++)
        {
            if(workings.charAt(i) == '^')
                indexOfPowers.add(i);

        }
        Formula = workings;
        TempFormula =workings;

        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        Formula = TempFormula;



    }

    private void changeFormula(Integer index) {
        String numberLeft = "";
        String numberRight = "";


        for(int i = index + 1; i< workings.length(); i++ ){
            if(isNumeric(workings.charAt(i))) {
                numberRight = numberRight + workings.charAt(i);
            }else
                break;
            }


        for(int i = index - 1; i >= 0; i-- ){
            if(isNumeric(workings.charAt(i))) {
                numberLeft = numberLeft + workings.charAt(i);
            }else
                break;
        }
        String Original = numberLeft+"^"+numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        TempFormula = TempFormula.replace(Original,changed);


    }


    private  boolean isNumeric(char c){
        if((c <= '9' && c >= '0')|| c =='.')
            return true;
        return false;

    }


    public void PowerButton(View view) {
        setworkings("^");


    }

    public void devisionButton(View view) {
        setworkings("/");

    }

    public void Button7(View view) {
        setworkings("7");

    }

    public void Button8(View view) {
        setworkings("8");


    }

    public void Button9(View view) {
        setworkings("9");

    }

    public void ButtonMultiplication(View view) {
        String X = "*";
        setworkings(X);

    }

    public void Button4(View view) {
        setworkings("4");

    }

    public void Button5(View view) {
        setworkings("5");

    }

    public void Button6(View view) {
        setworkings("6");

    }

    public void ButtonSubstraction(View view) {
        setworkings("-");
    }

    public void Button1(View view) {
        setworkings("1");
    }

    public void Button2(View view) {
        setworkings("2");
    }

    public void Button3(View view) {
        setworkings("3");
    }

    public void ButtonAddition(View view) {
        setworkings("+");
    }

    public void ButtonDot(View view) {
        setworkings(".");
    }

    public void Butto0(View view) {
        setworkings("0");
    }


}