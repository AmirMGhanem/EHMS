package Model;

public class Request {
    private String type;
    private String description;

    public Request() {
    }

    public Request(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String returnReq(){
        String[] strline = description.split(" ");
        int index = strline.length-1;

        return strline[index];
    }

    @Override
    public String toString() {
        return "Request{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
