package dream.asset.rpt.monthnew.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.rpt.monthnew.dao.AssetRptMonthNewListDAO;
import dream.asset.rpt.monthnew.dto.AssetRptMonthNewListDTO;
import dream.asset.rpt.monthnew.service.AssetRptMonthNewListService;

/**
 * 신규설비등록현황 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="assetRptMonthNewListServiceTarget"
 * @spring.txbn id="assetRptMonthNewListService"
 * @spring.property name="assetRptMonthNewListDAO" ref="assetRptMonthNewListDAO"
 */
public class AssetRptMonthNewListServiceImpl implements AssetRptMonthNewListService
{
    private AssetRptMonthNewListDAO assetRptMonthNewListDAO = null;

    public AssetRptMonthNewListDAO getAssetRptMonthNewListDAO() {
		return assetRptMonthNewListDAO;
	}

	public void setAssetRptMonthNewListDAO(AssetRptMonthNewListDAO assetRptMonthNewListDAO) {
		this.assetRptMonthNewListDAO = assetRptMonthNewListDAO;
	}
	public List findConnList(AssetRptMonthNewListDTO assetRptMonthNewListDTO, User loginUser)
    {      
		List list = assetRptMonthNewListDAO.findConnList(assetRptMonthNewListDTO, loginUser);
		List resultList = new ArrayList();
		
		if(list != null)
	        for(Object resultMap : list)
	        {
	            Map resultM = (Map)resultMap;
	            
	            String deptId  = String.valueOf(resultM.get("DEPTID"));
	            String pDeptId = String.valueOf(resultM.get("PDEPTID"));
	            String minLvl  = String.valueOf(resultM.get("MINLVL"));
	            String curLvl  = String.valueOf(resultM.get("LVL"));
	            
	            if(curLvl.equals(minLvl))
	            {
	                List subList = getSubList(deptId, list, "PDEPTID", "DEPTID");
	                if(subList.size() > 0) resultM.put("rows", subList);

	                resultList.add(resultM);
	            }
	            
	        }
		
        return resultList;
    }
	
	public List getSubList(String pCode, List resultList, String pCodeCol, String codeCol)
	{
	    List subList = new ArrayList();
	    Map rMap = null;
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
                String deptId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(deptId, resultList, pCodeCol, codeCol);
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
	}
}