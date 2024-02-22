package org.vitaliistf.souvenirs.menu;

/**
 * Interface defining constants for menu options.
 */
public interface MenuOptions {
    int ADD_MANUFACTURER = 1;
    int UPDATE_MANUFACTURER = 2;
    int DELETE_MANUFACTURER = 3;
    int GET_ALL_MANUFACTURERS = 4;

    int ADD_SOUVENIR = 5;
    int UPDATE_SOUVENIR = 6;
    int DELETE_SOUVENIR = 7;
    int GET_ALL_SOUVENIRS = 8;

    int GET_SOUVENIRS_BY_MANUFACTURER = 9;
    int GET_SOUVENIRS_BY_COUNTRY = 10;
    int GET_MANUFACTURERS_BY_MAX_PRICE = 11;
    int GET_ALL_MANUFACTURERS_WITH_SOUVENIRS = 12;
    int GET_MANUFACTURERS_BY_SOUVENIR_AND_YEAR = 13;
    int GET_ALL_SOUVENIRS_BY_YEARS = 14;
}

