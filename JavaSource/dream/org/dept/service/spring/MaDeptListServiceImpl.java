package dream.org.dept.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.org.dept.dao.MaDeptListDAO;
import dream.org.dept.dto.MaDeptCommonDTO;
import dream.org.dept.service.MaDeptListService;

/**
 * 보전부서 - 목록 serviceimpl
 * @author ssong
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maDeptListServiceTarget"
 * @spring.txbn id="maDeptListService"
 * @spring.property name="maDeptListDAO" ref="maDeptListDAO"
 */
public class MaDeptListServiceImpl implements MaDeptListService
{
    private MaDeptListDAO maDeptListDAO = null;

    public MaDeptListDAO getMaDeptListDAO() 
    {
		return maDeptListDAO;
	}

	public void setMaDeptListDAO(MaDeptListDAO maDeptListDAO) 
	{
		this.maDeptListDAO = maDeptListDAO;
	}

	public List findDeptList(MaDeptCommonDTO maDeptCommonDTO,User user, boolean excelExport)
    {      
        List list = null;
        list = maDeptListDAO.findDeptList(maDeptCommonDTO,user);
        
        //List resultList = new ArrayList();

        /*if(list != null) 
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String deptId = String.valueOf(resultM.get("DEPTID"));
            String pDeptId = String.valueOf(resultM.get("PDEPTID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(deptId, list, "PDEPTID", "DEPTID");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }
            
        }*/
        /*return resultList;*/
        return CommonUtil.makeTreeList(list, "PDEPTID", "DEPTID", excelExport);
    }
	
	public int deleteList(String compNo, String[] deleteRows) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                maDeptListDAO.mergeDept(compNo, id);
                result = result + maDeptListDAO.deleteDept(compNo, id);
            }
        
        return result;
    }
	
	   
    public List getSubList(String pCode, List resultList, String pCodeCol, String codeCol)
    {
        List subList = new ArrayList();
        Map rMap = null;
        //Parent Dept Id 가 0인 Equipment를 찾아주세요.
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
                String eqDeptId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(eqDeptId, resultList, pCodeCol, codeCol);
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
    }

	@Override
	public String getData(User user, MaDeptCommonDTO maDeptCommonDTO) {
		
		String type = "DEPT_DETAIL";
				
		maDeptCommonDTO.setExceltabNo(type);
		
		return maDeptListDAO.getData(user, maDeptCommonDTO);
	}
}

