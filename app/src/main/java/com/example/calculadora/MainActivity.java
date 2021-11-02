package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //objetos que estão na tela, é necessario recuperar o ID
    private Button numeroZero, numeroUm, numeroDois, numeroTres, numeroQuatro, numeroCinco, numeroSeis, numeroSete, numeroOito,
    numeroNove, ponto, soma, subtracao, multiplicacao, divisao, igual, botaoLimpar, botaoDelete;

    private TextView txtExpressao, txtResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarComponentes();
        getSupportActionBar().hide(); //Esconde a barra de ações
        //Definir eventos de click usando this para recuperar o contexto atual

        numeroZero.setOnClickListener(this);
        numeroUm.setOnClickListener(this);
        numeroDois.setOnClickListener(this);
        numeroTres.setOnClickListener(this);
        numeroQuatro.setOnClickListener(this);
        numeroCinco.setOnClickListener(this);
        numeroSeis.setOnClickListener(this);
        numeroSete.setOnClickListener(this);
        numeroOito.setOnClickListener(this);
        numeroNove.setOnClickListener(this);
        ponto.setOnClickListener(this);
        soma.setOnClickListener(this);
        subtracao.setOnClickListener(this);
        divisao.setOnClickListener(this);
        multiplicacao.setOnClickListener(this);
        botaoDelete.setOnClickListener(this);

        //Limpar as texts
        botaoLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtExpressao.setText("");
                txtResultado.setText("");
            }
        });

        //Implementando o botão Delete - Exclusão numero a número
        botaoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recuperação do ID por outro modo
                TextView expressao = findViewById(R.id.textExpressao);
                String string = expressao.getText().toString();

                if(!string.isEmpty())
                {
                    byte var0=0;
                    int var1=string.length()-1;
                    String txtExpressao = string.substring(var0, var1);
                    expressao.setText(txtExpressao);
                }
                txtResultado.setText(""); //Precisa manter o resultado vazio

            }
        });
        try{
            igual.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Expression expressao = new ExpressionBuilder(txtExpressao.getText().toString()).build(); //Fará os calculos da aplicação
                    double resultado = expressao.evaluate();
                    long longResult = (long) resultado; //Casting resultado para long
                    if(resultado == (double)longResult){
                        txtResultado.setText((CharSequence) String.valueOf(longResult));
                    } else{
                        txtResultado.setText((CharSequence) String.valueOf(resultado));
                    }
                }
            });

        } catch (Exception mathException){
            Toast.makeText(this, "Erro inesperado, verifique a expressão", Toast.LENGTH_SHORT).show();
        }

    }
    private void iniciarComponentes()
    {
        //Vamos recuperar os IDs por findViewById
        numeroZero = findViewById(R.id.btn0);
        numeroUm = findViewById(R.id.btn1);
        numeroDois = findViewById(R.id.btn2);
        numeroTres = findViewById(R.id.btn3);
        numeroQuatro = findViewById(R.id.btn4);
        numeroCinco = findViewById(R.id.btn5);
        numeroSeis = findViewById(R.id.btn6);
        numeroSete = findViewById(R.id.btn7);
        numeroOito = findViewById(R.id.btn8);
        numeroNove = findViewById(R.id.btn9);
        soma = findViewById(R.id.btnSoma);
        subtracao = findViewById(R.id.btnSubtrai);
        multiplicacao = findViewById(R.id.btnMultiplica);
        divisao = findViewById(R.id.btnDivide);
        botaoLimpar = findViewById(R.id.btnC);
        txtExpressao = findViewById(R.id.textExpressao);
        txtResultado = findViewById(R.id.textResultado);
        igual = findViewById(R.id.btnIgual);
        ponto = findViewById(R.id.btnPonto);
        botaoDelete = findViewById(R.id.btnDelete);
    }

    public void adicionaExpressao (String string, boolean limpar_dados)
    {
    //Este metodo acrescenta a expressao na textView Expressao
        //Validações
        if(txtResultado.getText().equals(""))
        {
            txtExpressao.setText(" "); //Deixa a expressao vazia caso txtResultado esteja vazia
        }
        if(limpar_dados == true)
        {
            txtResultado.setText(" ");
            txtExpressao.append(string);//Ao limpar, acrescenta-se a string a expressao
        }
        else
            {
                txtExpressao.append(txtResultado.getText());
                txtExpressao.append(string); //Captura a string do clique do botão para aferir no visor
                txtResultado.setText(" ");//Limpa a caixa de resultados
            }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) //Ele vai captar a id do objeto selecionado, falta definir os cases
        {
            case R.id.btn0:
                adicionaExpressao("0",true);
                break;
            case R.id.btn1:
                adicionaExpressao("1",true);
                break;
            case R.id.btn2:
                adicionaExpressao("2",true);
                break;
            case R.id.btn3:
                adicionaExpressao("3",true);
                break;
            case R.id.btn4:
                adicionaExpressao("4",true);
                break;
            case R.id.btn5:
                adicionaExpressao("5",true);
                break;
            case R.id.btn6:
                adicionaExpressao("6",true);
                break;
            case R.id.btn7:
                adicionaExpressao("7",true);
                break;
            case R.id.btn8:
                adicionaExpressao("8",true);
                break;
            case R.id.btn9:
                adicionaExpressao("9",true);
                break;
            case R.id.btnPonto:
                adicionaExpressao(".",true);
                break;
            case R.id.btnSoma:
                adicionaExpressao("+",false);
                break;
            case R.id.btnSubtrai:
                adicionaExpressao("-",false);
                break;
            case R.id.btnMultiplica:
                adicionaExpressao("*",false);
                break;
            case R.id.btnDivide:
                adicionaExpressao("/",false);
                break;

        }
    }
}