package common.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class ResponseDTO
{
    private int status;
    private String message;
    private List data;
    private Map ext;
    
    public static class Builder {
        private int status = HttpServletResponse.SC_OK;
        private String defaultMessage;
        private StringBuffer messageBuffer = new StringBuffer();
        private List data = new ArrayList();
        private Map ext = new HashMap();
        
        public Builder(String defaultMessage){
            this.defaultMessage = defaultMessage;
        }
        
        public Builder status(int status){
            this.status = status;
            return this;
        }
        public Builder message(String message){
            this.messageBuffer.setLength(0);
            this.messageBuffer.append(message);
            return this;
        }
        public Builder addMessage(String message){
            this.messageBuffer.append(message);
            return this;
        }
        public Builder clearMessage(){
            this.messageBuffer.setLength(0);
            return this;
        }
        public Builder data(List data){
            this.data = data;
            return this;
        }
        public Builder addData(Object value){
            this.data.add(value);
            return this;
        }
        public Builder clearData(){
            this.data.clear();
            return this;
        }
        public Builder ext(Map ext){
            this.ext = ext;
            return this;
        }
        public Builder addExt(String key, Object value){
            this.ext.put(key, value);
            return this;
        }
        
        public ResponseDTO build(){
            return new ResponseDTO(this);
        }
    }
    
    private ResponseDTO(Builder builder) {
        setStatus(builder.status);
        if(builder.messageBuffer.length() > 0) {
            setMessage(builder.messageBuffer.toString());
        }
        else {
            setMessage(builder.defaultMessage);
        }
        setData(builder.data);
        setExt(builder.ext);
    }
    
    public int getStatus()
    {
        return status;
    }
    public void setStatus(int status)
    {
        this.status = status;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public List getData()
    {
        return data;
    }
    public void setData(List data)
    {
        this.data = data;
    }
    public Map getExt()
    {
        return ext;
    }
    public void setExt(Map ext)
    {
        this.ext = ext;
    }
}
