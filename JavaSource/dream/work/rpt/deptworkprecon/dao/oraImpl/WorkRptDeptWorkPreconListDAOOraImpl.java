package dream.work.rpt.deptworkprecon.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.deptworkprecon.dao.WorkRptDeptWorkPreconListDAO;
import dream.work.rpt.deptworkprecon.dto.WorkRptDeptWorkPreconListDTO;

/**
 * 부서별 작업진행현황 DAO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workRptDeptWorkPreconListDAOTarget"
 * @spring.txbn id="workRptDeptWorkPreconListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptDeptWorkPreconListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptDeptWorkPreconListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param workRptDeptWorkPreconListDTO
     * @return List
     */
    public List findList(WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        List woTypeList = workRptDeptWorkPreconListDTO.getWoTypeList();
        
        query.append("SELECT                                                ");
        query.append("    ''        				AS    seqno        		");
        query.append("    ,x.dept_id                AS    deptId        	");
        query.append("    ,x.description            AS    deptDesc    		");
        query.append("	  ,x.p_dept_id        		AS 	  pdeptId			");
        query.append("    ,x.dept_id                AS 	  ID       			");
        query.append("   , LEVEL          			AS	  LVL				");
        query.append("   , MIN(LEVEL) OVER() 		AS	  MINLVL			");
        
        // TACDSYSD 에 IS_USE가 Y인 WO_TYPE 갯수만큼
        for(int i=0; i<woTypeList.size(); i++) {
            Map woTypeMap = (Map) woTypeList.get(i);
            
            query.append("    , (SELECT COUNT(1) 								");
            query.append("       FROM TAWORKORDER a 							");
            query.append("       WHERE 1=1										");
            query.append(this.getWorkWhere(workRptDeptWorkPreconListDTO, loginUser, woTypeMap.get("WOTYPE").toString()));
            query.append("                         )    AS    "+woTypeMap.get("WOTYPE").toString()+"CNT       		");
        }
        
        // 합계, 완료, 미완료 고정
        query.append("    , (SELECT COUNT(1) 								");
        query.append("       FROM TAWORKORDER a 							");
        query.append("       WHERE 1=1										");
        query.append(this.getWorkWhere(workRptDeptWorkPreconListDTO, loginUser, "TOT"));
        query.append("                         )    AS    totCnt       		");
        query.append("    , (SELECT COUNT(1) 								");
        query.append("       FROM TAWORKORDER a 							");
        query.append("       WHERE 1=1										");
        query.append(this.getWorkWhere(workRptDeptWorkPreconListDTO, loginUser, "COM"));
        query.append("                         )    AS    comCnt       		");
        query.append("    , (SELECT COUNT(1) 								");
        query.append("       FROM TAWORKORDER a 							");
        query.append("       WHERE 1=1										");
        query.append(this.getWorkWhere(workRptDeptWorkPreconListDTO, loginUser, "INCOM"));
        query.append("                         )    AS    incomCnt       	");
        query.append("FROM TADEPT x                                         ");
        query.append("WHERE 1=1                                             ");
        query.append(this.getWhere(workRptDeptWorkPreconListDTO, loginUser));
        query.append("START WITH p_dept_id = 0								");
        query.append("CONNECT BY PRIOR x.dept_id = x.p_dept_id      		");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param workRptDeptWorkPreconListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        query.getAndQuery("x.is_monitoring", "Y");

        //공장
    	query.getCodeLikeQuery("x.plant", "(SELECT b.description FROM  TAPLANT b WHERE b.comp_no = '"+loginUser.getCompNo()+"' AND b.plant = x.plant )"
    			, workRptDeptWorkPreconListDTO.getFilterPlantId(), workRptDeptWorkPreconListDTO.getFilterPlantDesc());
    	
        return query.toString();
    }        

    private String getWorkWhere(WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO, User loginUser, String type)
    {        
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getAndQuery("a.comp_no", loginUser.getCompNo());
    	query.getAndQuery("a.is_deleted", "N");
    	query.append("AND a.wo_type IN (SELECT c.cdsysd_no FROM TACDSYSD c WHERE c.list_type = 'WO_TYPE' AND c.is_use = 'Y') ");
    	query.append(" AND a.dept_id IN (SELECT dept_id                             				");
    	query.append("                                    FROM TADEPT d                             ");
    	query.append("                                    WHERE d.comp_no = x.comp_no               ");
    	query.getAndQuery("d.is_use", "Y");
        query.getAndQuery("d.is_monitoring", "Y");
    	query.append("                                    START WITH d.dept_id = x.dept_id          ");
    	query.append("                                    CONNECT BY PRIOR d.dept_id = d.p_dept_id 	");
    	query.append("					)															");
    	query.getAndDateQuery("a.wkor_date", workRptDeptWorkPreconListDTO.getFilterStartDate() , workRptDeptWorkPreconListDTO.getFilterEndDate());
    	
    	if(!type.equals("TOT")&&!type.equals("COM")&&!type.equals("INCOM"))
    		query.getAndQuery("a.wo_type", type);
    	
    	switch (type)
    	{
    		case "COM" :
    			query.getAndQuery("a.wo_status", "C");
    			break;
    		case "INCOM" :
    			query.append("AND a.wo_status != 'C' ");
    			break;
    		default:
    			break;
    	}
    	
    	return query.toString();
    }        
    
    public List findWoTypes(User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT 					");
    	query.append("    cdsysd_no		woType 	");
    	query.append("FROM TACDSYSD x			");
    	query.append("WHERE x.list_Type = ?		");
    	query.append("AND x.is_use 		= ?		");
    	query.append("ORDER BY x.ord_no			");
    	
    	Object[] objects = new Object[] {   
    			"WO_TYPE"
                , "Y"
        };

        return getJdbcTemplate().queryForList(query.toString(),objects);
    }
    
}