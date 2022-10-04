package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private TextView solution_tv,result;

    private MaterialButton clear_button,open_bracket,close_bracket,allclear_button;

    private MaterialButton divide_button,multiply_button,add_button,subtract_button,equal_button,button_dot;

    private MaterialButton button_nine,button_eight,button_seven,button_six,button_five,button_four,button_three,button_two,button_one,button_zero;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignid(clear_button,R.id.clear_button);
        assignid(allclear_button,R.id.allclear_button);
        assignid(open_bracket,R.id.open_bracket);
        assignid(close_bracket,R.id.close_bracket);

        assignid(divide_button,R.id.divide_button);
        assignid(subtract_button,R.id.subtract_button);
        assignid(multiply_button,R.id.multiply_button);
        assignid(add_button,R.id.add_button);
        assignid(equal_button,R.id.equal_button);
        assignid(button_dot,R.id.button_dot);

        assignid(button_nine,R.id.button_nine);
        assignid(button_eight,R.id.button_eight);
        assignid(button_seven,R.id.button_seven);
        assignid(button_six,R.id.button_six);
        assignid(button_five,R.id.button_five);
        assignid(button_four,R.id.button_four);
        assignid(button_three,R.id.button_three);
        assignid(button_two,R.id.button_two);
        assignid(button_one,R.id.button_one);
        assignid(button_zero,R.id.button_zero);

        solution_tv = findViewById(R.id.solution_tv);
        result = findViewById(R.id.result);

    }

    protected void assignid(MaterialButton btn,int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        MaterialButton button = (MaterialButton)view;
        String txt = button.getText().toString();

        String tocalcdata = solution_tv.getText().toString();

        if(txt.equals("AC"))
        {
            solution_tv.setText("0");
            result.setText("0");
        }
        else if(txt.equals("="))
        {
            result.setText(tocalcdata);
        }
        else if(txt.equals("C"))
        {
            if(tocalcdata.length()>1) {
                tocalcdata = tocalcdata.substring(0, tocalcdata.length() - 1);
                solution_tv.setText(tocalcdata);
            }
        }

        else {
            tocalcdata += txt;
            solution_tv.setText(tocalcdata);
        }

        String finalresult = getString(tocalcdata);

        if(!(finalresult.equals("err")))
        {
            result.setText(finalresult);
        }
    }

    String getString(String data)
    {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalresult.endsWith(".0"))
            {
                finalresult = finalresult.replace(".0","");
            }
            return finalresult;
        }
        catch (Exception e){
            return "err";
        }
    }
}