 package dream.consult.rpt.maconn.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.rpt.maconn.dao.MaConnChartDAO;
import dream.consult.rpt.maconn.dto.MaConnChartDTO;
import dream.consult.rpt.maconn.service.MaConnChartService;

/**
 * 접속현황 serviceimpl
 * @author kim21017
 * @version $Id: MaConnChartServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maConnChartServiceTarget"
 * @spring.txbn id="maConnChartService"
 * @spring.property name="maConnChartDAO" ref="maConnChartDAO"
 */
public class MaConnChartServiceImpl implements MaConnChartService
{
    private MaConnChartDAO maConnChartDAO = null;

    public MaConnChartDAO getMaConnChartDAO() {
		return maConnChartDAO;
	}

	public void setMaConnChartDAO(MaConnChartDAO maConnChartDAO) {
		this.maConnChartDAO = maConnChartDAO;
	}
	public List findConnChart(MaConnChartDTO maConnChartDTO, User user)
    {      
        return maConnChartDAO.findConnChart(maConnChartDTO, user);
    }
	public List findUsrList(MaConnChartDTO maConnChartDTO, User user)
    {
        return maConnChartDAO.findUsrList(maConnChartDTO, user);
    }
	public List findConnList(MaConnChartDTO maConnChartDTO, User user)
    {      
		List list = maConnChartDAO.findConnList(maConnChartDTO, user);
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