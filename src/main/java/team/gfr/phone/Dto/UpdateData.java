package team.gfr.phone.Dto;


//接受修改时，前端传过来的参数
public class UpdateData {
    private String field;

    private String value;

    private Integer id;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
