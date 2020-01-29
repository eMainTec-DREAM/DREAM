package dream.work.rpt.mawodaily.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dream.work.rpt.mawodaily.dao.MaWoDailyChartDAO;
import dream.work.rpt.mawodaily.dto.MaWoDailyChartDTO;
import dream.work.rpt.mawodaily.service.MaWoDailyChartService;

/**
 * 일별작업실행율 serviceimpl
 * @author kim21017
 * @version $Id: MaWoDailyChartServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoDailyChartServiceTarget"
 * @spring.txbn id="maWoDailyChartService"
 * @spring.property name="maWoDailyChartDAO" ref="maWoDailyChartDAO"
 */
public class MaWoDailyChartServiceImpl implements MaWoDailyChartService
{
    private MaWoDailyChartDAO maWoDailyChartDAO = null;

    public MaWoDailyChartDAO getMaWoDailyChartDAO() {
		return maWoDailyChartDAO;
	}

	public void setMaWoDailyChartDAO(MaWoDailyChartDAO maWoDailyChartDAO) {
		this.maWoDailyChartDAO = maWoDailyChartDAO;
	}

	public List findWoList(MaWoDailyChartDTO maWoDailyChartDTO)
    {      
		List list = maWoDailyChartDAO.findWoList(maWoDailyChartDTO);
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