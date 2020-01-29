package dream.conn.rpt.month.log.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.conn.rpt.month.log.dao.ConnRptMonthLogListDAO;
import dream.conn.rpt.month.log.dto.ConnRptMonthLogListDTO;
import dream.conn.rpt.month.log.service.ConnRptMonthLogListService;

/**
 * 월별접속현황 serviceimpl
 * @author sy.yang
 * @version $Id: ConnRptMonthLogListServiceImpl.java,v 1.0 2015/12/02 09:12:51 sy.yang Exp $
 * @since 1.0
 * 
 * @spring.bean id="connRptMonthLogListServiceTarget"
 * @spring.txbn id="connRptMonthLogListService"
 * @spring.property name="connRptMonthLogListDAO" ref="connRptMonthLogListDAO"
 */
public class ConnRptMonthLogListServiceImpl implements ConnRptMonthLogListService
{
    private ConnRptMonthLogListDAO connRptMonthLogListDAO = null;

    public ConnRptMonthLogListDAO getConnRptMonthLogListDAO() {
		return connRptMonthLogListDAO;
	}
	public void setConnRptMonthLogListDAO(ConnRptMonthLogListDAO connRptMonthLogListDAO) {
		this.connRptMonthLogListDAO = connRptMonthLogListDAO;
	}
	
	public List findConnChart(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user)
    {      
        return connRptMonthLogListDAO.findConnChart(connRptMonthLogListDTO, user);
    }

	public List findConnList(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user)
    {      
		List list = connRptMonthLogListDAO.findConnList(connRptMonthLogListDTO, user);
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
	
	public List findUsrList(ConnRptMonthLogListDTO connRptMonthLogListDTO,User user)
    {
        return connRptMonthLogListDAO.findUsrList(connRptMonthLogListDTO, user);
    }
}