package dream.work.rpt.org.bd.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.org.bd.dao.WorkRptOrgBdListDAO;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;

/**
 * 조직별 고장분석 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptOrgBdListDAOTarget"
 * @spring.txbn id="workRptOrgBdListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptOrgBdListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptOrgBdListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgBdCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 							");
        query.append("    x.dept_id 	AS DEPTID		");
        query.append("  , x.description AS ORGNAME		");
        query.append("  , (SELECT   					");
        query.append("     		COUNT(a.item_no)	    ");
        query.append("     FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b		");
        query.append("     ON a.item_no=b.item_no       ");
        query.append("     AND a.comp_no=b.comp_no      ");
        query.append("     WHERE 1=1     				");
        query.append(this.getDeptWhere(workRptOrgBdCommonDTO,loginUser));
        query.append("               )  AS BDCNT		");
        query.append("  , (SELECT   					");
        query.append("     		ROUND(NVL(SUM(a.work_time),0)/60,1) ");
//        query.append("	  	 	FLOOR(NVL(SUM(a.work_time),0)/60) || '시간 ' || MOD(NVL(SUM(a.work_time),0),60)||'분' AS WOTIMEHOUR		");
        query.append("     FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b		");
        query.append("     ON a.item_no=b.item_no       ");
        query.append("     AND a.comp_no=b.comp_no      ");
        query.append("     WHERE 1=1     				");
        query.append(this.getDeptWhere(workRptOrgBdCommonDTO,loginUser));
        query.append("               )  AS WOTIMEHOUR	");
        query.append("  , (SELECT   					");
        query.append("     		NVL(SUM(a.tot_amt),0)   ");
        query.append("     FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b      ");
        query.append("     ON a.item_no=b.item_no       ");
        query.append("     AND a.comp_no=b.comp_no      ");
        query.append("     WHERE 1=1     				");
        query.append(this.getDeptWhere(workRptOrgBdCommonDTO,loginUser));
        query.append("               )  AS MAINTAMT		");
        query.append("FROM TADEPT x						");
        query.append("WHERE 1=1     					");
        query.append(this.getWhere(workRptOrgBdCommonDTO,loginUser));
        query.getOrderByQuery("x.description", workRptOrgBdCommonDTO.getOrderBy(), workRptOrgBdCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptOrgBdCommonDTO.getIsLoadMaxCount(), workRptOrgBdCommonDTO.getFirstRow()));
    }
    
    public String getWhere(WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO,User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	// 회사코드
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        
        // 사용여부
        query.getAndQuery("x.is_use", "Y");

        // 조직구분
        query.getCodeLikeQuery("x.dept_categ", "SFACODE_TO_DESC(x.dept_categ,'DEPT_CATEG','SYS','','"+loginUser.getLangId()+"')", workRptOrgBdCommonDTO.getFilterDeptCategId(), workRptOrgBdCommonDTO.getFilterDeptCategDesc());
        
    	// 부서
    	query.getDeptLevelQuery("x.dept_id", workRptOrgBdCommonDTO.getFilterDeptId(), workRptOrgBdCommonDTO.getFilterDeptDesc(), loginUser.getCompNo());

    	//공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
                workRptOrgBdCommonDTO.getFilterPlantId(), workRptOrgBdCommonDTO.getFilterPlantDesc());

    	return query.toString();
    }    	
    public String getDeptWhere(WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.append("AND a.wo_type='BM'        								");    
        query.append("AND a.wkor_date IS NOT NULL      							");
        query.append("AND b.dept_id IN (SELECT dept_id                         	");
        query.append("                  FROM TADEPT                             ");
        query.append("                  WHERE comp_no = x.comp_no               ");
        query.append("                  START WITH dept_id = x.dept_id          ");
        query.append("                  CONNECT BY PRIOR dept_id = p_dept_id)	");
    	        
        // 월
        String fromDate = workRptOrgBdCommonDTO.getFilterStartDate()+"01";
        String toDate   = workRptOrgBdCommonDTO.getFilterEndDate()+"31";
        query.getAndDateQuery("a.wkor_date", fromDate, toDate);
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )", 
                workRptOrgBdCommonDTO.getFilterPlantId(), workRptOrgBdCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT            ");
        query.append("       COUNT(1)   ");
        query.append("FROM TADEPT x		");
        query.append("WHERE 1=1 		");
        query.append(this.getWhere(workRptOrgBdCommonDTO,loginUser));
                
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}