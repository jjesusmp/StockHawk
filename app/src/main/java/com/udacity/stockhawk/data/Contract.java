package com.udacity.stockhawk.data;


import android.net.Uri;
import android.provider.BaseColumns;

import com.google.common.collect.ImmutableList;

public final class Contract {

    static final String AUTHORITY = "com.udacity.stockhawk";
    static final String PATH_QUOTE = "quote";
    static final String PATH_QUOTE_WITH_SYMBOL = "quote/*";
    private static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

    private Contract() {
    }

    @SuppressWarnings("unused")
    public static final class Quote implements BaseColumns {

        public static final Uri URI = BASE_URI.buildUpon().appendPath(PATH_QUOTE).build();
        public static final String COLUMN_SYMBOL = "symbol";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_ABSOLUTE_CHANGE = "absolute_change";
        public static final String COLUMN_PERCENTAGE_CHANGE = "percentage_change";
        public static final String COLUMN_HISTORY = "history";
        public static final int POSITION_ID = 0;
        public static final int POSITION_SYMBOL = 1;
        public static final int POSITION_PRICE = 2;
        public static final int POSITION_ABSOLUTE_CHANGE = 3;
        public static final int POSITION_PERCENTAGE_CHANGE = 4;
        public static final int POSITION_HISTORY = 5;
        public static final ImmutableList<String> QUOTE_COLUMNS = ImmutableList.of(
                _ID,
                COLUMN_SYMBOL,
                COLUMN_PRICE,
                COLUMN_ABSOLUTE_CHANGE,
                COLUMN_PERCENTAGE_CHANGE,
                COLUMN_HISTORY
        );
        public static final String TABLE_NAME = "quotes";

        public static Uri makeUriForStock(String symbol) {
            return URI.buildUpon().appendPath(symbol).build();
        }

        static String getStockFromUri(Uri queryUri) {
            return queryUri.getLastPathSegment();
        }

        public static Uri getURI() {
            return URI;
        }

        public static String getId() {
            return _ID;
        }

        public static String getColumnSymbol() {
            return COLUMN_SYMBOL;
        }

        public static String getColumnPrice() {
            return COLUMN_PRICE;
        }

        public static String getColumnAbsoluteChange() {
            return COLUMN_ABSOLUTE_CHANGE;
        }

        public static String getColumnPercentageChange() {
            return COLUMN_PERCENTAGE_CHANGE;
        }

        public static String getColumnHistory() {
            return COLUMN_HISTORY;
        }

        public static int getPositionId() {
            return POSITION_ID;
        }

        public static int getPositionSymbol() {
            return POSITION_SYMBOL;
        }

        public static int getPositionPrice() {
            return POSITION_PRICE;
        }

        public static int getPositionAbsoluteChange() {
            return POSITION_ABSOLUTE_CHANGE;
        }

        public static int getPositionPercentageChange() {
            return POSITION_PERCENTAGE_CHANGE;
        }

        public static int getPositionHistory() {
            return POSITION_HISTORY;
        }

        public static ImmutableList<String> getQuoteColumns() {
            return QUOTE_COLUMNS;
        }

        public static String getTableName() {
            return TABLE_NAME;
        }
    }

}
