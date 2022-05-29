package sqllite.crud;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class crudOrders extends AppCompatActivity {
    EditText name, cardNumber, cardPin, cardExpiry, total;
    Button  insert, update, delete , view;

    dbConnectionForOrders dbConnectionForOrders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crudorders);

        name = findViewById(R.id.name);
        cardNumber = findViewById(R.id.cardNumber);
        cardExpiry = findViewById(R.id.cardExpiry);
        cardPin = findViewById(R.id.cardPin);
        total = findViewById(R.id.total);

        insert = findViewById(R.id.insertData);
        update = findViewById(R.id.updateData);
        delete = findViewById(R.id.deleteData);
        view = findViewById(R.id.viewData);

        dbConnectionForOrders = new dbConnectionForOrders(this);
        // No need for insertion anyMore

        // on clickLister method for button(insert)
        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nameTXT = name.getText().toString();
                String cardNumberTXT = cardNumber.getText().toString();
                String cardExpiryTXT = cardExpiry.getText().toString();
                String cardPinTXT = cardPin.getText().toString();
                String totalTXT = total.getText().toString();
                Boolean checkInsertData = dbConnectionForOrders.insertOrderData(nameTXT, cardNumberTXT, cardExpiryTXT, cardPinTXT, totalTXT);
                if (checkInsertData)
                    Toast.makeText(crudOrders.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(crudOrders.this, "Entry Not Inserted", Toast.LENGTH_SHORT).show();

            }
        });

        // on clickLister method for button(update)
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nameTXT = name.getText().toString();
                String cardNumberTXT = cardNumber.getText().toString();
                String cardExpiryTXT = cardExpiry.getText().toString();
                String cardPinTXT = cardPin.getText().toString();
                String totalTXT = total.getText().toString();
                Boolean checkUpdateData = dbConnectionForOrders.updateOrderData(nameTXT, cardNumberTXT, cardExpiryTXT, cardPinTXT, totalTXT);
                if (checkUpdateData)
                    Toast.makeText(crudOrders.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(crudOrders.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();

            }
        });

        // on clickLister method for button(delete)
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nameTXT = name.getText().toString();
                Boolean checkDeleteData = dbConnectionForOrders.deleteOrderData(nameTXT);
                if (checkDeleteData)
                    Toast.makeText(crudOrders.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(crudOrders.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        // on clickLister method for button(view)
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = dbConnectionForOrders.retrieveOrderData();
                if(res.getCount()== 0){
                    Toast.makeText(crudOrders.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Prompt Orders Details using StringBuffer and AlertDialog
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name: " + res.getString(0 )+ "\n");
                    buffer.append("Card Number: " + res.getString(1 )+ "\n");
                    buffer.append("Card Expiry: " + res.getString(2 )+ "\n");
                    buffer.append("Card Pin: " + res.getString(3)+ "\n");
                    buffer.append("Total Pay: $" + res.getString(4 )+ "\n\n");
                }

                AlertDialog.Builder builder  = new AlertDialog.Builder(crudOrders.this);
                builder.setCancelable(true);
                builder.setTitle("Customer Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}