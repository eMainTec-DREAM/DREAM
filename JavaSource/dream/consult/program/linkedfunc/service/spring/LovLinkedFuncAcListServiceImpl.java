package dream.consult.program.linkedfunc.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.program.linkedfunc.dao.LovLinkedFuncAcListDAO;
import dream.consult.program.linkedfunc.dto.LovLinkedFuncAcListDTO;
import dream.consult.program.linkedfunc.form.LovLinkedFuncAcListForm;
import dream.consult.program.linkedfunc.service.LovLinkedFuncAcListService;

/**
 * Linked Function ServiceImpl
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovLinkedFuncAcListServiceTarget"
 * @spring.txbn id="lovLinkedFuncAcListService"
 * @spring.property name="lovLinkedFuncAcListDAO" ref="lovLinkedFuncAcListDAO"
 */
public class LovLinkedFuncAcListServiceImpl implements LovLinkedFuncAcListService
{
    /** Linked Function ÆË¾÷ DAO */
    private LovLinkedFuncAcListDAO lovLinkedFuncAcListDAO = null;
    
    public LovLinkedFuncAcListDAO getLovLinkedFuncAcListDAO() 
    {
		return lovLinkedFuncAcListDAO;
	}

	public void setLovLinkedFuncAcListDAO(LovLinkedFuncAcListDAO lovLinkedFuncAcListDAO) 
	{
		this.lovLinkedFuncAcListDAO = lovLinkedFuncAcListDAO;
	}

	public List findLinkedFuncAcList(LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovLinkedFuncAcListDAO.findLinkedFuncAcList(lovLinkedFuncAcListDTO,loginUser);
        
        return resultList;
    }

    @Override
    public List findAcList(LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO, User user,
            LovLinkedFuncAcListForm lovLinkedFuncAcListForm)
    {
        String code         = lovLinkedFuncAcListForm.getCode();    //Search Value
        String keyCode      = lovLinkedFuncAcListForm.getKeyCode();  //Search Column
        String codeType     = lovLinkedFuncAcListForm.getCodeType(); //Table
        String jsonParam    = lovLinkedFuncAcListForm.getParam();  //Condition
        String jaonCol      = lovLinkedFuncAcListForm.getResultCol();  //Result Column


        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type); // <maPtMstrList.partNo:part_no>

        List resultList = null;
        resultList = lovLinkedFuncAcListDAO.findAcList(lovLinkedFuncAcListDTO, user, columnMap ,conditionMap);
        
        return resultList;
    }
}