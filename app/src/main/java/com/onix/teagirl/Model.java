package com.onix.teagirl;

public class Model {
    public String mId, mName,mPos,mOrder, mDetails;
    public  String mTime;

    public Model() {

    }

    public Model(String mId, String mName, String mPos, String mOrder, String mDetails, String mSugar, String mTime) {
        this.mId = mId;
        this.mName = mName;
        this.mPos = mPos;
        this.mOrder = mOrder;
        this.mDetails = mDetails;
        this.mTime = mTime;
    }

    public String getmPos() {
        return mPos;
    }

    public void setmPos(String mPos) {
        this.mPos = mPos;
    }

    public String getmDetails() {
        return mDetails;
    }

    public void setmDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmOrder() {
        return mOrder;
    }

    public void setmOrder(String mOrder) {
        this.mOrder = mOrder;
    }
}
