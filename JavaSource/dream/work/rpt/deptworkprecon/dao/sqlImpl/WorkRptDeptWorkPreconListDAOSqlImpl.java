package dream.work.rpt.deptworkprecon.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class WorkRptDeptWorkPreconListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptDeptWorkPreconListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        List woTypeList = workRptDeptWorkPreconListDTO.getWoTypeList();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     ");
        query.append("SELECT                                                ");
        query.append("    ''        				AS    SEQNO        		");
        query.append("    , x.dept_id               AS    DEPTID        	");
        query.append("    , x.description           AS    DEPTDESC    		");
        query.append("	  , x.p_dept_id        		AS 	  PDEPTID			");
        query.append("    , x.dept_id               AS 	  ID       			");
        query.append("    , MIN(y.lvl) OVER()    	AS 	  MINLVL    		");
        query.append("    , y.lvl 					AS	  LVL             	");
        
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
        query.append("FROM   TADEPT x ,(SELECT * FROM dbo.SFADEPT_ALL('"+loginUser.getCompNo()+"','0')) y	");
        query.append("WHERE x.dept_id = y.dept_id                           ");
        query.append(this.getWhere(workRptDeptWorkPreconListDTO, loginUser));

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        query.getAndQuery("x.is_monitoring", "Y");

        //공장
    	query.getCodeLikeQuery("x.plant", "(SELECT b.description FROM  TAPLANT b WHERE b.comp_no = '"+loginUser.getCompNo()+"' AND b.plant = x.plant )"
    			, workRptDeptWorkPreconListDTO.getFilterPlantId(), workRptDeptWorkPreconListDTO.getFilterPlantDesc());
    	
    	return query.toString();
    }
    
    public String getWorkWhere(WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO, User loginUser, String type)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getAndQuery("a.comp_no", loginUser.getCompNo());
    	query.getAndQuery("a.is_deleted", "N");
    	query.append("AND a.wo_type IN (SELECT c.cdsysd_no FROM TACDSYSD c WHERE c.list_type = 'WO_TYPE' AND c.is_use = 'Y') ");
    	query.append("AND a.dept_id IN ( SELECT b.dept_id                 			");
    	query.append("                  FROM dbo.SFADEPT_ALL('"+loginUser.getCompNo()+"',x.dept_id ) b INNER JOIN TADEPT c       ");
        query.append("					     ON b.comp_no = c.comp_no        		");
    	query.append("					    AND b.dept_id = c.dept_id        		");
    	query.getAndQuery("c.is_use", "Y");
        query.getAndQuery("c.is_monitoring", "Y");
    	query.append("					)											");
    	
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
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