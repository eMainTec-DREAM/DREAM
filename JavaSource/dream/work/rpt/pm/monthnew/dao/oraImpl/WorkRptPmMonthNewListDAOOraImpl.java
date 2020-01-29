package dream.work.rpt.pm.monthnew.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.pm.monthnew.dao.WorkRptPmMonthNewListDAO;
import dream.work.rpt.pm.monthnew.dto.WorkRptPmMonthNewListDTO;

/**
 * 신규점검등록현황 DAO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workRptPmMonthNewListDAOTarget"
 * @spring.txbn id="workRptPmMonthNewListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmMonthNewListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptPmMonthNewListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param workRptPmMonthNewListDTO
     * @return List
     */
    public List findConnList(WorkRptPmMonthNewListDTO workRptPmMonthNewListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        int month = Integer.parseInt(workRptPmMonthNewListDTO.getMonths());
        
        query.append("SELECT                                                ");
        query.append("    ''        				AS    seqno        		");
        query.append("    ,x.dept_id                AS    deptId        	");
        query.append("    ,x.description            AS    deptDesc    		");
        query.append("	  ,x.p_dept_id        		AS 	  pdeptId			");
        query.append("    ,x.dept_id                AS 	  ID       			");
        query.append("   , LEVEL          			AS	  LVL				");
        query.append("   , MIN(LEVEL) OVER() 		AS	  MINLVL			");
        
        for(int a=0; a<=month; a++) {
        	
        	String fromYear = workRptPmMonthNewListDTO.getFilterStartDate().replaceAll("-", "").substring(0, 4); // 년
        	String fromMonth = workRptPmMonthNewListDTO.getFilterStartDate().replaceAll("-", "").substring(4);
        	String fromDate = fromYear+fromMonth;
        	
            query.append("    ,( SELECT                                         ");
            query.append("           COUNT(1)                                   ");
            query.append("         FROM TAPMLST a                           	");
            query.append("         WHERE 1=1                                    ");
            query.append("  AND  a.comp_no = x.comp_no							");
            query.append("  AND  a.is_deleted = 'N'								");
            query.append("  AND a.wo_type = 'PMI'								");
            query.getAndQuery("a.is_last_version", "Y");
            query.append("  AND a.cre_date BETWEEN '"+fromDate+"01000000' AND '"+fromDate+"31235959' ");
            query.append("AND a.dept_id IN ( SELECT dept_id                     ");
            query.append("                     FROM TADEPT                      ");
            query.append("                     WHERE comp_no = x.comp_no        ");
            query.getAndQuery("is_use", "Y");
            query.getAndQuery("is_monitoring", "Y");
            query.append("                     START WITH dept_id = x.dept_id   ");
            query.append("                     CONNECT BY PRIOR dept_id = p_dept_id    ");
            query.append("                  )                                   ");
            query.append("     )      newPMICnt"+fromDate+"    				");
            
            if(Integer.parseInt(fromMonth)+1==13)
            {
            	fromYear = Integer.toString(Integer.parseInt(fromYear)+1); // 1년 증가
        		fromMonth = "01"; // 1월로 월 초기화
            }
        	else
        	{
        		fromMonth = Integer.parseInt(fromMonth)+1<10?"0"+Integer.toString(Integer.parseInt(fromMonth)+1):Integer.toString(Integer.parseInt(fromMonth)+1);
        	}
	            workRptPmMonthNewListDTO.setFilterStartDate(fromYear+fromMonth);
	            
            }
        query.append("FROM TADEPT x                                         ");
        query.append("WHERE 1=1                                             ");
        query.append(this.getWhere(workRptPmMonthNewListDTO, loginUser));
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
     * @param workRptPmMonthNewListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptPmMonthNewListDTO workRptPmMonthNewListDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        query.getAndQuery("x.is_monitoring", "Y");

        //부서
        query.getDeptLevelQuery("x.dept_id", workRptPmMonthNewListDTO.getFilterDeptId(), workRptPmMonthNewListDTO.getFilterDeptDesc(), loginUser.getCompNo());

        return query.toString();
    }        

}