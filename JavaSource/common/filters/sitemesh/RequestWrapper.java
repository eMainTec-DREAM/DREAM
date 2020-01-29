package common.filters.sitemesh;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public final class RequestWrapper extends HttpServletRequestWrapper {

//    Map auditMap;
//    int size;
    
    public RequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
//        auditMap = new HashMap();
//        size = 0;
//        System.out.println("래퍼 이닛!!!!!");
    }

    public String[] getParameterValues(String parameter) {
        
      String[] values = super.getParameterValues(parameter);
      
      if (values==null)  {
                  return null;
          }
      //그리드 xml은 XSS 적용하지 않는다.
      if(parameter.equals("grid_xml"))
      {
    	  return values;
      }
      
      int count = values.length;

      String[] encodedValues = new String[count];
//      StringBuffer encValues = new StringBuffer();
   
      for (int i = 0; i < count; i++) {
                 encodedValues[i] = cleanXSS(values[i]);
//                 encValues.append(i==0?encodedValues[i]:"^|^"+encodedValues[i]);
       }
      
      return encodedValues;
    }

    public String getParameter(String parameter) {

          String value = super.getParameter(parameter);
          if (value == null) {
                 return null;
                  }
          
          //그리드 xml은 XSS 적용하지 않는다.
          if(parameter.equals("grid_xml"))
          {
        	  return value;
          }

          return cleanXSS(value);
    }

    public String getHeader(String name) {

        String value = super.getHeader(name);

        if (value == null)

            return null;
        return cleanXSS(value);
    }

    private String cleanXSS(String value) {

                //You'll need to remove the spaces from the html entities below

        //value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        //value = value==""?null:value;
//        value = value.replaceAll("'", "& #39;");

        //value = value.replaceAll("eval\\((.*)\\)", "");

        //value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

        //value = value.replaceAll("script", "");

        return value;
    }

}
