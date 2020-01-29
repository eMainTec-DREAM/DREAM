package common.util;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Call Method in Class for Java Script.
 * @author  javaworker
 * @version $Id: CallUtilServlet.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
 * @since   1.0
 */
public class CallUtilServlet extends HttpServlet 
{
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String className = request.getParameter("className");
        String methodName = request.getParameter("methodName");
        
        String [] values = request.getParameterValues("value");
        String [] types = request.getParameterValues("type");
        
        Object result = "";
        
        try
        {
            Class[] paramTypes = null;
            Object[] paramVlaues = null;
            if (types != null && values != null)
            {
                paramTypes = new Class[types.length];
                paramVlaues = new Object[types.length];
                for (int i=0; i<types.length; i++)
                {
                    paramTypes[i] = getTypeClass(types[i]);
                    paramVlaues[i] = getParamValue(values[i], types[i]);
                }
            }
            
            Class callClass = Class.forName("common.util."+className);
            Method callMethod = callClass.getMethod(methodName, paramTypes);
            result = callMethod.invoke(callClass.getInterfaces(), paramVlaues);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        request.setAttribute("AJAX_DESC", new String[]{result.toString()});
        
        RequestDispatcher rqd = getServletContext().getRequestDispatcher("/common/ajax/ajaxXmlVal.jsp");
        rqd.forward(request, response);
    }

    /**
     * return class
     * @author  javaworker
     * @version $Id: CallUtilServlet.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param string
     * @return
     */
    private Class getTypeClass(String className) throws ClassNotFoundException
    {
        Class typeClass = null;
        
        if ("int".equals(className)) typeClass = int.class;
        else if ("String".equals(className)) typeClass = String.class;
        else if ("float".equals(className)) typeClass = float.class;
        else if ("double".equals(className)) typeClass = double.class;
        
        return typeClass;
    }
    
    /**
     * return paramter
     * @author  javaworker
     * @version $Id: CallUtilServlet.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param string
     * @return
     */
    private Object getParamValue(String value, String className)
    {
        Object valueObject = null;
        
        if ("int".equals(className)) valueObject = Integer.parseInt(value);
        else if ("String".equals(className)) valueObject = value;
        else if ("float".equals(className)) valueObject = Float.parseFloat(value);
        else if ("double".equals(className)) valueObject = Double.parseDouble(value);
        
        return valueObject;
    }
}