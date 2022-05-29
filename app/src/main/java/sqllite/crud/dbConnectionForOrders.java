package sqllite.crud;

// db connection for order crud operations

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbConnectionForOrders extends SQLiteOpenHelper {
    public dbConnectionForOrders(@Nullable Context context) {
        super(context, "FoodOrder.db", null ,1);
    }
    // table name is: OrderDetails
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table OrderDetails(name TEXT, cardNumber TEXT, cardExpiry TEXT, cardPin TEXT, totalPay TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists Orderdetails");
    }

    // Insertion Method
    public Boolean insertOrderData(String name, String cardNumber, String cardExpiry, String cardPin, String totalPay){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("cardNumber", cardNumber);
        contentValues.put("cardExpiry", cardExpiry);
        contentValues.put("cardPin", cardPin);
        contentValues.put("totalPay", totalPay);
        long result = db.insert("OrderDetails", null, contentValues);
        if (result == -1)
        {
            return false;
        }else{
            return true;
        }
    }

    // Update Method
    public Boolean updateOrderData(String name, String cardNumber, String cardExpiry, String cardPin, String totalPay){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cardNumber", cardNumber);
        contentValues.put("cardExpiry", cardExpiry);
        contentValues.put("cardPin", cardPin);
        contentValues.put("totalPay", totalPay);
        Cursor cursor = db.rawQuery("Select * from OrderDetails where name = ?", new String[]{name});
        if (cursor.getCount()>0){
            long result = db.update("OrderDetails", contentValues, "name=?", new String[]{name});
            if (result == -1)
            {
                return false;
            }else{
                return true;}
        }
        else{
            return false;
        }
    }

    // Delete Method
    public Boolean deleteOrderData(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from OrderDetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = db.delete("OrderDetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    // Retrieve Method
    public Cursor retrieveOrderData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from OrderDetails", null);
        return cursor;
    }


}