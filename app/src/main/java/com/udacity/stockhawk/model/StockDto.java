package com.udacity.stockhawk.model;

import android.os.Parcel;
import android.os.Parcelable;


public class StockDto implements Parcelable{

    private String symbol;
    private String price;
    private String absoluteChange;
    private String percentChange;
    private String history;

    public StockDto(String symbol, String price, String absoluteChange, String percentChange, String history) {
        this.symbol = symbol;
        this.price = price;
        this.absoluteChange = absoluteChange;
        this.percentChange = percentChange;
        this.history = history;
    }

    protected StockDto(Parcel in) {
        symbol = in.readString();
        price = in.readString();
        absoluteChange = in.readString();
        percentChange = in.readString();
        history = in.readString();
    }

    public static final Creator<StockDto> CREATOR = new Creator<StockDto>() {
        @Override
        public StockDto createFromParcel(Parcel in) {
            return new StockDto(in);
        }

        @Override
        public StockDto[] newArray(int size) {
            return new StockDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(symbol);
        parcel.writeString(price);
        parcel.writeString(absoluteChange);
        parcel.writeString(percentChange);
        parcel.writeString(history);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPrice() {
        return price;
    }

    public String getAbsoluteChange() {
        return absoluteChange;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public String getHistory() {
        return history;
    }
}
