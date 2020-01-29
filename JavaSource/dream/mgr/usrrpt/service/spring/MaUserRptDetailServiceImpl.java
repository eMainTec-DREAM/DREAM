package dream.mgr.usrrpt.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.QueryBuffer;
import dream.mgr.usrrpt.dao.MaUserRptDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptDetailDTO;
import dream.mgr.usrrpt.service.MaUserRptDetailService;

/**
 * 메뉴 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaUserRptDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maUserRptDetailServiceTarget"
 * @spring.txbn id="maUserRptDetailService"
 * @spring.property name="maUserRptDetailDAO" ref="maUserRptDetailDAO"
 */
public class MaUserRptDetailServiceImpl implements MaUserRptDetailService
{
    private MaUserRptDetailDAO maUserRptDetailDAO = null;
    
    public MaUserRptDetailDAO getMaUserRptDetailDAO() {
		return maUserRptDetailDAO;
	}

	public void setMaUserRptDetailDAO(MaUserRptDetailDAO maUserRptDetailDAO) {
		this.maUserRptDetailDAO = maUserRptDetailDAO;
	}

	public MaUserRptDetailDTO findDetail(String menuId, String lang)throws Exception
    {
        return maUserRptDetailDAO.findDetail(menuId, lang);
    }
    
	public int insertDetail(MaUserRptDetailDTO maUserRptDetailDTO,User loginUser) throws Exception
    {        
        return maUserRptDetailDAO.insertDetail(maUserRptDetailDTO, loginUser);
    }
	
	public int updateDetail(MaUserRptDetailDTO maUserRptDetailDTO,User loginUser) throws Exception
    {        
        return maUserRptDetailDAO.updateDetail(maUserRptDetailDTO, loginUser);
    }

	public Map<String, List<Map>> makeReport(MaUserRptCommonDTO maUserRptCommonDTO, User user) {
		
		MaUserRptDetailDTO maUserRptDetailDTO = maUserRptDetailDAO.findDetail(maUserRptCommonDTO.getUsrrptlistId(), user.getLangId());
		
		List<Map> tableList = maUserRptDetailDAO.findTableList(maUserRptCommonDTO, user);
		
		List<Map> colList = maUserRptDetailDAO.findColList(maUserRptCommonDTO, user);
		
		List<Map> paramList = maUserRptDetailDAO.findParamList(maUserRptCommonDTO, user);
		
		List<Map> ordList = maUserRptDetailDAO.findOrdList(maUserRptCommonDTO, user);
		
		List<Map> joinList = maUserRptDetailDAO.findJoinList(maUserRptCommonDTO, user);
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT ");
		
		int colSize = colList.size();
		int colCnt= 0;
		for( Map colMap : colList )
		{ 
			query.append(colMap.get("key")+"."+colMap.get("columnName")+" "+colMap.get("colAlias"));
			colCnt++;
			
			if(colCnt != colSize)query.append(",");
		} 
		
		query.append("FROM ");
		int tabSize = tableList.size();
		int tabCnt= 0;
		String joinType = "";
		for( Map tabMap : tableList )
		{ 		
			if("INNER".equals(tabMap.get("joinType"))) joinType = " INNER JOIN ";
			else if("LEFTOUTER".equals(tabMap.get("joinType"))) joinType = " LEFT OUTER JOIN ";
			else if("RIGHTOUTER".equals(tabMap.get("joinType"))) joinType = " RIGHT OUTER JOIN ";
			else if("CROSS".equals(tabMap.get("joinType"))) joinType = " CROSS JOIN ";

			if(tabCnt != 0 && joinType == "")query.append(",");

			query.append(joinType);
			query.append(tabMap.get("tableName")+" "+tabMap.get("key"));
			tabCnt++;

			int joinSize = joinList.size();
			if(joinType != "" && joinType != " CROSS JOIN ")
			{
				query.append("ON ");
				int jnCnt = 0;
				for(Map joinMap : joinList)
				{
					if(!joinMap.get("usrrpttabId").equals(tabMap.get("usrrpttabId"))) continue;
					
					if(jnCnt != 0) query.append("AND ");
						
					if("VALUE".equals(joinMap.get("colValueType")))
						query.append(joinMap.get("lkey")+"."+joinMap.get("ltabcolName")+joinMap.get("tabConOperatorDesc")+" '"+joinMap.get("conValue")+"'");
					else
						query.append(joinMap.get("lkey")+"."+joinMap.get("ltabcolName")+joinMap.get("tabConOperatorDesc")+joinMap.get("rkey")+"."+joinMap.get("rtabcolName"));
					
					jnCnt++;
				}
			}

		} 
		
		query.append("WHERE 1 = 1");
		for( Map paramMap : paramList )
		{ 
			query.append("AND " + paramMap.get("key")+"."+paramMap.get("columnName")+" "+paramMap.get("whrConOperatorDesc") +" '" +paramMap.get("conValue")+"'");
		} 
		
		int ordSize = ordList.size();
		int ordCnt= 0;
		if(ordSize > 0) query.append("ORDER BY ");
		for( Map ordMap : ordList )
		{ 
			query.append(ordMap.get("key")+"."+ordMap.get("columnName")+" "+ordMap.get("sortType"));
			ordCnt++;
			
			if(ordCnt != ordSize)query.append(",");
		} 
		
		List resultList = maUserRptDetailDAO.findReportData(query.toString());
		
		Map<String, List<Map>> resultMap = new HashMap();
		resultMap.put("resultList", resultList);
		resultMap.put("colList",colList);
		
		return resultMap;

	}
}
