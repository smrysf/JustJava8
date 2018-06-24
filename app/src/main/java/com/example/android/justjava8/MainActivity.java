package com.example.android.justjava8;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffee =1;
    int price =5;
    String name, priceMessage;
    boolean hasWhippedCream, hasChocolate;
    TextView quantityTextView, orderSummaryTextView;
    CheckBox whippedCreamCheckBox, chocolateCheckBox;
    EditText nameEditText;
    int whippedCreamPrice =1;
    int chocolatePrice =2;
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitOrder(View view){
        nameEditText = (EditText)findViewById(R.id.editText);
        name= nameEditText.getText().toString();

        whippedCreamCheckBox= (CheckBox)findViewById(R.id.wippedCreamChB);
        hasWhippedCream= whippedCreamCheckBox.isChecked();

        chocolateCheckBox= (CheckBox)findViewById(R.id.chocolateChB);
        hasChocolate= chocolateCheckBox.isChecked();

       displayPrice(numberOfCoffee);

      priceMessage  = "Total: $"+ calculatePrice(numberOfCoffee);
     priceMessage = createOrderSummary(name, numberOfCoffee, hasWhippedCream, hasChocolate)+priceMessage + "\n"+getString(R.string.Thanks);
     displayMessage(priceMessage);
    }
    private int calculatePrice(int numberOfCoffee){
        if(hasWhippedCream&& hasChocolate)
           total = (numberOfCoffee*whippedCreamPrice)+(numberOfCoffee*chocolatePrice);
        else if(hasChocolate)
            total = (numberOfCoffee*chocolatePrice);
        else if(hasWhippedCream)
            total = (numberOfCoffee*whippedCreamPrice);
        else
            total =0;
            return (numberOfCoffee*price)+total;
        }
    private int display(int number){
         quantityTextView = (TextView)findViewById(R.id.quantity__text_view);
        quantityTextView.setText(""+ number);
        return numberOfCoffee;
    }

    public void displayPrice(int number){
        orderSummaryTextView = (TextView)findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number*5)); }

    public void incrementCoffee(View view){
        if(numberOfCoffee<100)
        numberOfCoffee++;
        else
            Toast.makeText(this, "You can't have moe than 100",Toast.LENGTH_LONG).show();

        display(numberOfCoffee); }

    public void decrementCoffee(View view){
        if(numberOfCoffee>1)
            numberOfCoffee--;
        else
            Toast.makeText(this, "You can't have less than 1", Toast.LENGTH_LONG).show();

        display(numberOfCoffee);
    }
    public void displayMessage(String message){
        orderSummaryTextView.setText(message); }

    private String createOrderSummary
            (String name, int numberOfCoffee, boolean hasWhippedCream, boolean hasChocolate) {

        return "Name: "+name +  "\nAdd Whipped Cream? "+ hasWhippedCream+
                "\nAdd Chocolate? "+ hasChocolate+
                "\nQuantity: " +numberOfCoffee+ "\n"; }

                public void sendEmail(View view) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto","smr.ysf@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Coffe Price");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));

                }

}
