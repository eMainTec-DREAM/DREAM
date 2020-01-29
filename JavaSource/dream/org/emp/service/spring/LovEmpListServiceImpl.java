package dream.org.emp.service.spring;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.org.emp.dao.LovEmpListDAO;
import dream.org.emp.dto.LovEmpListDTO;
import dream.org.emp.form.LovEmpListForm;
import dream.org.emp.service.LovEmpListService;

/**
 * »ç¿øÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovEmpListServiceTarget"
 * @spring.txbn id="lovEmpListService"
 * @spring.property name="lovEmpListDAO" ref="lovEmpListDAO"
 */
public class LovEmpListServiceImpl implements LovEmpListService
{
    /** »ç¿øÆË¾÷ DAO */
    private LovEmpListDAO lovEmpListDAO = null;

    public LovEmpListDAO getLovEmpListDAO() 
    {
		return lovEmpListDAO;
	}

	public void setLovEmpListDAO(LovEmpListDAO lovEmpListDAO) 
	{
		this.lovEmpListDAO = lovEmpListDAO;
	}

	public List findEmpList(LovEmpListDTO lovEmpListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovEmpListDAO.findEmpList(lovEmpListDTO,loginUser);
        
        return resultList;
    }

	@Override
	public List findEmpAcList(LovEmpListDTO lovEmpListDTO, User user, LovEmpListForm lovEmpListForm) {
		String jsonParam    = lovEmpListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovEmpListDAO.findEmpAcList(lovEmpListDTO, user ,conditionMap);
	}
	public String findTotalCount(LovEmpListForm lovEmpListForm, User user)
    {
		LovEmpListDTO lovEmpListDTO = lovEmpListForm.getLovEmpListDTO();
		String code         = lovEmpListForm.getCode();    //Search Value
        String keyCode      = lovEmpListForm.getKeyCode();  //Search Column
        String codeType     = lovEmpListForm.getCodeType(); //Table
        String jsonParam    = lovEmpListForm.getParam();  //Condition
        String jaonCol      = lovEmpListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
		
        return lovEmpListDAO.findTotalCount(lovEmpListDTO, user, columnMap ,conditionMap);
    }
	public LovEmpListDTO setJsonParm(LovEmpListDTO lovEmpListDTO) throws ClassNotFoundException, ParseException {
		
		String extCode = lovEmpListDTO.getExtCode1();
    	
    	Class<?> clss = LovEmpListDTO.class;
    	
        Class<?> cls = Class.forName(clss.getName());
        //Object obj = cls.newInstance();
        
    	if(!"".equals(extCode))
    	{
    		JSONParser jp = new JSONParser();
	    	JSONObject jsonObj = (JSONObject)jp.parse(extCode);
	    	Iterator<?> keys = jsonObj.keySet().iterator();
	    	Method configMethod = null;
	    	
	    	while( keys.hasNext() ) {
	    	    String key = (String)keys.next();
	    	    
	    	    String[] keyArray = new String[2];
	    	    if(key.indexOf(".") > 0) keyArray = key.split("\\.");
	    	    else keyArray[1] = key;
	    	    
	    	    keyArray[1] = "set"+keyArray[1].substring(0, 1).toUpperCase() + keyArray[1].substring(1);

	            try
	            {
	                configMethod = cls.getMethod(keyArray[1], new Class[]{java.lang.String.class});
	                configMethod.invoke(lovEmpListDTO, new Object[]{new String((String)jsonObj.get(key))});
	            }
	            catch (Exception ex)
	            {
	                ex.printStackTrace();
	            }
	            
	    	}
    	}
		return lovEmpListDTO;
	}
}