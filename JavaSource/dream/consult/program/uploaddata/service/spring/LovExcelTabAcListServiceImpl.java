package dream.consult.program.uploaddata.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.program.uploaddata.dao.LovExcelTabAcListDAO;
import dream.consult.program.uploaddata.dto.LovExcelTabAcListDTO;
import dream.consult.program.uploaddata.form.LovExcelTabAcListForm;
import dream.consult.program.uploaddata.service.LovExcelTabAcListService;

/**
 * Excel Tab ÆË¾÷ ServiceImpl
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovExcelTabAcListServiceTarget"
 * @spring.txbn id="lovExcelTabAcListService"
 * @spring.property name="lovExcelTabAcListDAO" ref="lovExcelTabAcListDAO"
 */
public class LovExcelTabAcListServiceImpl implements LovExcelTabAcListService
{
    /** Excel Tab ÆË¾÷ DAO */
    private LovExcelTabAcListDAO lovExcelTabAcListDAO = null;
    public LovExcelTabAcListDAO getLovExcelTabAcListDAO() 
    {
		return lovExcelTabAcListDAO;
	}

	public void setLovExcelTabAcListDAO(LovExcelTabAcListDAO lovExcelTabAcListDAO) 
	{
		this.lovExcelTabAcListDAO = lovExcelTabAcListDAO;
	}

	public List findExcelTabList(LovExcelTabAcListDTO lovExcelTabAcListDTO, User loginUser)
    {
        return lovExcelTabAcListDAO.findExcelTabList(lovExcelTabAcListDTO, loginUser);
    }
	
    @Override
    public List findExcelTabAcList(LovExcelTabAcListDTO lovExcelTabAcListDTO, User user,
            LovExcelTabAcListForm lovExcelTabAcListForm)
    {
        String jsonParam    = lovExcelTabAcListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        return lovExcelTabAcListDAO.findExcelTabAcList(lovExcelTabAcListDTO, user,conditionMap);
    }
}