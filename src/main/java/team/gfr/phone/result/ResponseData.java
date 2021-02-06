package team.gfr.phone.result;

public class ResponseData extends Response{
    private Object data;
    //layui框架分页参数--当前数据总量
    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    //自定义消息和状态码
    public ResponseData(String code, String msg) {
        super(code, msg);
    }
    //通过枚举实现消息设置
    public ResponseData(ExceptionMsg exceptionMsg) {
        super(exceptionMsg);
    }
    //只设置状态码    不设置msg和data
    public ResponseData(String code) {
        super(code);
    }
    //自定义消息和数据
    public ResponseData(String code,Object data) {
        super(code);
        this.data=data;
    }

    //自定义消息和数据
    public ResponseData(String code,String msg,Object data) {
        super(code,msg);
        this.data=data;
    }

    //Layui需要的自定义消息和数据和格式
    public ResponseData(String code,String msg,Object data,Long count) {
        super(code,msg);
        this.data=data;
        this.count=count;
    }

    //通过枚举实现数据返回
    public ResponseData(ExceptionMsg exceptionMsg,Object data) {
        super(exceptionMsg);
        this.data=data;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}