package team.gfr.phone.result;

public class Response {
    //new Response(ExceptionMsg,data)
    private String code="0";

    private String msg="操作成功";
    //当没有返回数据的时候，用于自定义状态码和消息

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    //传一个枚举类型进来
    public Response(ExceptionMsg exceptionMsg) {
        this.code = exceptionMsg.getCode();
        this.msg = exceptionMsg.getMsg();
    }
    //只需要状态码，不需要msg
    public Response(String code) {
        this.code = code;
        this.msg = "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
