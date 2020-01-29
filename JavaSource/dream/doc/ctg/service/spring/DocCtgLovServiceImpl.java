package dream.doc.ctg.service.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.doc.ctg.dao.DocCtgLovDAO;
import dream.doc.ctg.dto.DocCtgLovDTO;
import dream.doc.ctg.form.DocCtgLovForm;
import dream.doc.ctg.service.DocCtgLovService;


/**
 * 문서분류체계 LOV - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="docCtgLovServiceTarget"
 * @spring.txbn id="docCtgLovService"
 * @spring.property name="docCtgLovDAO" ref="docCtgLovDAO"
 */
public class DocCtgLovServiceImpl implements DocCtgLovService
{
    private DocCtgLovDAO docCtgLovDAO = null;

	public DocCtgLovDAO getDocCtgLovDAO() 
    {
		return docCtgLovDAO;
	}

	public void setDocCtgLovDAO(DocCtgLovDAO docCtgLovDAO) 
	{
		this.docCtgLovDAO = docCtgLovDAO;
	}

    public List findList(DocCtgLovDTO docCtgLovDTO, DocCtgLovForm docCtgLovForm, User user)
    {      
    	String code         = docCtgLovForm.getCode();    //Search Value
        String keyCode      = docCtgLovForm.getKeyCode();  //Search Column
        String codeType     = docCtgLovForm.getCodeType(); //Table
        String jsonParam    = docCtgLovForm.getParam();  //Condition
        String jaonCol      = docCtgLovForm.getResultCol();  //Result Column


        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type); // <maPtMstrList.partNo:part_no>

        List list = docCtgLovDAO.findList(docCtgLovDTO, columnMap ,conditionMap, user);
        
        List resultList = new ArrayList();
        List clonedList = new ArrayList();
        
        String locId;
        String pLocId;
        String minLvl;
        String curLvl;
        Map resultM = null;
        List subList = null;
        
        clonedList.addAll(list);
        
        if(list != null)
        for(Object resultMap : list)
        {
            resultM = (Map)resultMap;
            
            locId = String.valueOf(resultM.get("DOCCTG_ID"));
            pLocId = String.valueOf(resultM.get("P_DOCCTG_ID"));
            minLvl = String.valueOf(resultM.get("MINLVL"));
            curLvl = String.valueOf(resultM.get("LVL"));
            
            if(curLvl.equals(minLvl))
            {
            	clonedList.remove(resultM);
                subList = getSubList(locId, clonedList, "P_DOCCTG_ID", "DOCCTG_ID");
                if(subList.size() > 0)
                {
                    resultM.put("rows", subList);
                }

                resultList.add(resultM);
            }
            else clonedList.remove(resultM);
            
        }
        return resultList;
        
    }
    
    public List getSubList(String pCode, List resultList, String pCodeCol, String codeCol)
	{
	    List subList = new ArrayList();
	    List parentList = new ArrayList();
	    Map rMap = null;
	    List list = null;
	    String eqLocId;
	    
	    parentList.addAll(resultList);
	    
	    //Parent Equip Location Code 가 0인 Equipment를 찾아주세요.
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
                eqLocId = String.valueOf(rMap.get(codeCol));
                parentList.remove(rMap);
                
                list = getSubList(eqLocId, parentList, pCodeCol, codeCol);

                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
	}
}

