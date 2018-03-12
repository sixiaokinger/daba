package com.longke.shot.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by longke on 2018/2/3.
 */

public class Info {


    /**
     * FormateStr : yyyy-MM-dd
     * ResultCode : 200
     * Success : true
     * Message :
     * Data : {"PadId":1,"RangeId":2,"RangeName":"自由射击靶场","TrainId":1,"TargetId":11,"TrainName":"测试训练","StudentId":1,"StudentName":"张三","GroupIndex":1,"StudentCode":"sz001","ShootModeName":"长枪射击","CurrScore":10,"IsLimitBullet":true,"RemainBullet":9,"TotalScore":90,"RemainTime":"00:30:00","Status":3,"ShootDetailList":[{"BulletIndex":1,"X":604,"Y":495,"Width":22,"Height":15,"Score":10},{"BulletIndex":2,"X":654,"Y":542,"Width":19,"Height":18,"Score":10},{"BulletIndex":3,"X":569,"Y":991,"Width":28,"Height":14,"Score":0},{"BulletIndex":4,"X":690,"Y":985,"Width":28,"Height":20,"Score":0},{"BulletIndex":5,"X":619,"Y":575,"Width":22,"Height":20,"Score":10},{"BulletIndex":6,"X":636,"Y":556,"Width":20,"Height":20,"Score":10},{"BulletIndex":7,"X":596,"Y":550,"Width":19,"Height":24,"Score":10},{"BulletIndex":8,"X":624,"Y":559,"Width":22,"Height":23,"Score":10},{"BulletIndex":9,"X":581,"Y":517,"Width":19,"Height":26,"Score":10},{"BulletIndex":10,"X":600,"Y":494,"Width":23,"Height":18,"Score":10},{"BulletIndex":11,"X":608,"Y":595,"Width":19,"Height":19,"Score":10}]}
     * TotalCount : 0
     * SessionId : x2p3w5r4ghdpbxpugwnjjx4x
     * ContentEncoding : null
     * ContentType : null
     * JsonRequestBehavior : 0
     * MaxJsonLength : null
     * RecursionLimit : null
     */

    private String FormateStr;
    private int ResultCode;
    private boolean Success;
    private String Message;
    private DataBean Data;
    private int TotalCount;
    private String SessionId;
    private Object ContentEncoding;
    private Object ContentType;
    private int JsonRequestBehavior;
    private Object MaxJsonLength;
    private Object RecursionLimit;

    public String getFormateStr() {
        return FormateStr;
    }

    public void setFormateStr(String FormateStr) {
        this.FormateStr = FormateStr;
    }

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int ResultCode) {
        this.ResultCode = ResultCode;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int TotalCount) {
        this.TotalCount = TotalCount;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String SessionId) {
        this.SessionId = SessionId;
    }

    public Object getContentEncoding() {
        return ContentEncoding;
    }

    public void setContentEncoding(Object ContentEncoding) {
        this.ContentEncoding = ContentEncoding;
    }

    public Object getContentType() {
        return ContentType;
    }

    public void setContentType(Object ContentType) {
        this.ContentType = ContentType;
    }

    public int getJsonRequestBehavior() {
        return JsonRequestBehavior;
    }

    public void setJsonRequestBehavior(int JsonRequestBehavior) {
        this.JsonRequestBehavior = JsonRequestBehavior;
    }

    public Object getMaxJsonLength() {
        return MaxJsonLength;
    }

    public void setMaxJsonLength(Object MaxJsonLength) {
        this.MaxJsonLength = MaxJsonLength;
    }

    public Object getRecursionLimit() {
        return RecursionLimit;
    }

    public void setRecursionLimit(Object RecursionLimit) {
        this.RecursionLimit = RecursionLimit;
    }

    public static class DataBean {
        /**
         * PadId : 1
         * RangeId : 2
         * RangeName : 自由射击靶场
         * TrainId : 1
         * TargetId : 11
         * TrainName : 测试训练
         * StudentId : 1
         * StudentName : 张三
         * GroupIndex : 1
         * StudentCode : sz001
         * ShootModeName : 长枪射击
         * CurrScore : 10
         * IsLimitBullet : true
         * RemainBullet : 9
         * TotalScore : 90
         * RemainTime : 00:30:00
         * Status : 3 0 表示还没开始 1表示为准备 2已准备 3 射击中 4表示结束
         * ShootDetailList : [{"BulletIndex":1,"X":604,"Y":495,"Width":22,"Height":15,"Score":10},{"BulletIndex":2,"X":654,"Y":542,"Width":19,"Height":18,"Score":10},{"BulletIndex":3,"X":569,"Y":991,"Width":28,"Height":14,"Score":0},{"BulletIndex":4,"X":690,"Y":985,"Width":28,"Height":20,"Score":0},{"BulletIndex":5,"X":619,"Y":575,"Width":22,"Height":20,"Score":10},{"BulletIndex":6,"X":636,"Y":556,"Width":20,"Height":20,"Score":10},{"BulletIndex":7,"X":596,"Y":550,"Width":19,"Height":24,"Score":10},{"BulletIndex":8,"X":624,"Y":559,"Width":22,"Height":23,"Score":10},{"BulletIndex":9,"X":581,"Y":517,"Width":19,"Height":26,"Score":10},{"BulletIndex":10,"X":600,"Y":494,"Width":23,"Height":18,"Score":10},{"BulletIndex":11,"X":608,"Y":595,"Width":19,"Height":19,"Score":10}]
         */

        private int PadId;
        private int RangeId;
        private String RangeName;
        private int TrainId;
        private int TargetId;
        private String TrainName;
        private int StudentId;
        private String StudentName;
        private int GroupIndex;
        private String StudentCode;
        private String BeginShootTime;
        private String TargetName;

        public String getTargetName() {
            return TargetName;
        }

        public void setTargetName(String targetName) {
            TargetName = targetName;
        }

        public String getBeginShootTime() {
            return BeginShootTime;
        }

        public void setBeginShootTime(String beginShootTime) {
            BeginShootTime = beginShootTime;
        }

        public String getEndShootTime() {
            return EndShootTime;
        }

        public void setEndShootTime(String endShootTime) {
            EndShootTime = endShootTime;
        }

        private String EndShootTime;

        private String ShootModeName;
        private int CurrScore;
        private boolean IsLimitBullet;
        private int RemainBullet;
        private int TotalScore;
        private String RemainTime;
        private String VideoStreamUrl;

        public String getVideoStreamUrl() {
            return VideoStreamUrl;
        }

        public void setVideoStreamUrl(String videoStreamUrl) {
            VideoStreamUrl = videoStreamUrl;
        }

        public List<ShootDetailListBean> getShootDetailList() {
            return ShootDetailList;
        }

        public void setShootDetailList(List<ShootDetailListBean> shootDetailList) {
            ShootDetailList = shootDetailList;
        }

        private int Status;
        private List<ShootDetailListBean> ShootDetailList;

        public int getPadId() {
            return PadId;
        }

        public void setPadId(int PadId) {
            this.PadId = PadId;
        }

        public int getRangeId() {
            return RangeId;
        }

        public void setRangeId(int RangeId) {
            this.RangeId = RangeId;
        }

        public String getRangeName() {
            return RangeName;
        }

        public void setRangeName(String RangeName) {
            this.RangeName = RangeName;
        }

        public int getTrainId() {
            return TrainId;
        }

        public void setTrainId(int TrainId) {
            this.TrainId = TrainId;
        }

        public int getTargetId() {
            return TargetId;
        }

        public void setTargetId(int TargetId) {
            this.TargetId = TargetId;
        }

        public String getTrainName() {
            return TrainName;
        }

        public void setTrainName(String TrainName) {
            this.TrainName = TrainName;
        }

        public int getStudentId() {
            return StudentId;
        }

        public void setStudentId(int StudentId) {
            this.StudentId = StudentId;
        }

        public String getStudentName() {
            return StudentName;
        }

        public void setStudentName(String StudentName) {
            this.StudentName = StudentName;
        }

        public int getGroupIndex() {
            return GroupIndex;
        }

        public void setGroupIndex(int GroupIndex) {
            this.GroupIndex = GroupIndex;
        }

        public String getStudentCode() {
            return StudentCode;
        }

        public void setStudentCode(String StudentCode) {
            this.StudentCode = StudentCode;
        }

        public String getShootModeName() {
            return ShootModeName;
        }

        public void setShootModeName(String ShootModeName) {
            this.ShootModeName = ShootModeName;
        }

        public int getCurrScore() {
            return CurrScore;
        }

        public void setCurrScore(int CurrScore) {
            this.CurrScore = CurrScore;
        }

        public boolean isIsLimitBullet() {
            return IsLimitBullet;
        }

        public void setIsLimitBullet(boolean IsLimitBullet) {
            this.IsLimitBullet = IsLimitBullet;
        }

        public int getRemainBullet() {
            return RemainBullet;
        }

        public void setRemainBullet(int RemainBullet) {
            this.RemainBullet = RemainBullet;
        }

        public int getTotalScore() {
            return TotalScore;
        }

        public void setTotalScore(int TotalScore) {
            this.TotalScore = TotalScore;
        }

        public String getRemainTime() {
            return RemainTime;
        }

        public void setRemainTime(String RemainTime) {
            this.RemainTime = RemainTime;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

       /* public List<ShootDetailListBean> getShootDetailList() {
            return ShootDetailList;
        }

        public void setShootDetailList(List<ShootDetailListBean> ShootDetailList) {
            this.ShootDetailList = ShootDetailList;
        }*/

        public static class ShootDetailListBean implements Serializable{
            /**
             * BulletIndex : 1
             * X : 604
             * Y : 495
             * Width : 22
             * Height : 15
             * Score : 10
             */

            private int BulletIndex;
            private int X;
            private int Y;
            private int Width;
            private int Height;
            private int Score;

            public int getBulletIndex() {
                return BulletIndex;
            }

            public void setBulletIndex(int BulletIndex) {
                this.BulletIndex = BulletIndex;
            }

            public int getX() {
                return X;
            }

            public void setX(int X) {
                this.X = X;
            }

            public int getY() {
                return Y;
            }

            public void setY(int Y) {
                this.Y = Y;
            }

            public int getWidth() {
                return Width;
            }

            public void setWidth(int Width) {
                this.Width = Width;
            }

            public int getHeight() {
                return Height;
            }

            public void setHeight(int Height) {
                this.Height = Height;
            }

            public int getScore() {
                return Score;
            }

            public void setScore(int Score) {
                this.Score = Score;
            }
        }
    }
}
