package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView Result,Solution;
    MaterialButton btnC,btnopenbracket,btnclosebracket;
    MaterialButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    MaterialButton btndiv,btnmul,btnadd,btnsub,btnequals;
    MaterialButton btnAC,btndot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Result=findViewById(R.id.result);
        Solution=findViewById(R.id.input);
        assignid(btnC,R.id.clear);
        assignid(btnopenbracket,R.id.open_bracket);
        assignid(btnclosebracket,R.id.close_bracket);
        assignid(btndiv,R.id.divide);
        assignid(btnmul,R.id.multiply);
        assignid(btnadd,R.id.add);
        assignid(btnsub,R.id.sub);
        assignid(btnequals,R.id.ans);
        assignid(btn0,R.id.no0);
        assignid(btn1,R.id.no1);
        assignid(btn2,R.id.no2);
        assignid(btn3,R.id.no3);
        assignid(btn4,R.id.no4);
        assignid(btn5,R.id.no5);
        assignid(btn6,R.id.no6);
        assignid(btn7,R.id.no7);
        assignid(btn8,R.id.no8);
        assignid(btn9,R.id.no9);
        assignid(btnAC,R.id.clearall);
        assignid(btndot,R.id.dot);
    }

    void assignid(MaterialButton btn,int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button= (MaterialButton) v;
        String buttonText = button.getText().toString();
        String datatocalculate=Solution.getText().toString();
        if(buttonText.equals("AC"))
        {
            Solution.setText("");
            Result.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            Solution.setText(Result.getText());
            return;
        }
        if(buttonText.equals("C"))
        {
            if(datatocalculate.length()==1)
            {
                return;
            }
            datatocalculate=datatocalculate.substring(0,datatocalculate.length()-1);
        }
        else {
            datatocalculate = datatocalculate + buttonText;
        }
        Solution.setText(datatocalculate);
        String final_result=getresult(datatocalculate);
        if(!final_result.equals("Err"))
        {
            Result.setText(final_result);
        }
    }
    String getresult(String data)
    {
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalresult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalresult.endsWith(".0"))
            {
                finalresult=finalresult.replace(".0","");
            }
            return finalresult;
        }catch(Exception e)
        {
            return "Err";
        }

    }
}