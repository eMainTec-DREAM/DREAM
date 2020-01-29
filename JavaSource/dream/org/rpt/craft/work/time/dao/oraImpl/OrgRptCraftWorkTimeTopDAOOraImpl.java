package dream.org.rpt.craft.work.time.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.org.rpt.craft.work.time.dao.OrgRptCraftWorkTimeTopDAO;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeTopCommonDTO;

/**
 * 작업자 작업시간 Top
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="orgRptCraftWorkTimeTopDAOTarget"
 * @spring.txbn id="orgRptCraftWorkTimeTopDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OrgRptCraftWorkTimeTopDAOOraImpl extends BaseJdbcDaoSupportOra implements OrgRptCraftWorkTimeTopDAO
{

	@Override
    public List findList(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        	        										");
        query.append("            '' SEQNO        										");
        query.append("            ,x.EMPID        										");
        query.append("            ,x.EMPDESC        									");
        query.append("            ,x.PLANT        										");
        query.append("            ,x.PLANTDESC        									");
        query.append("            ,x.DEPTID        										");
        query.append("            ,x.DEPTDESC        									");
        query.append("            ,ROUND(x.WOWORKTIME/60,1)        WOWORKTIME			");
        query.append("            ,ROUND(x.PMWORKTIME/60,1)        PMWORKTIME			");
        query.append("            ,(ROUND(x.WOWORKTIME/60,1) + ROUND(x.PMWORKTIME/60,1))   TOTWORKTIME  ");
        query.append("FROM (        													");
        query.append("SELECT     ''                    				SEQNO        		");
        query.append("        ,a.EMP_ID            					EMPID        		");
        query.append("        ,a.emp_name            				EMPDESC        		");
        query.append("        , a.plant            					PLANT        		");
        query.append("        ,(SELECT description        								");
        query.append("        FROM TAPLANT        										");
        query.append("        WHERE comp_no = a.comp_no        							");
        query.append("        and plant = a.plant)    				PLANTDESC        	");
        query.append("        ,a.DEPT_ID                			DEPTID        		");
        query.append("        ,(SELECT description        								");
        query.append("        FROM TADEPT        										");
        query.append("        WHERE comp_no = a.comp_no        							");
        query.append("        and dept_id = a.dept_id)    			DEPTDESC        	");
        query.append("        ,NVL((SELECT SUM(aa.work_time)        					");
        query.append("        FROM TAWOCRAFT aa inner join TAWORKORDER bb				");
        query.append("        on aa.comp_no = bb.comp_no and aa.wkor_id = bb.wkor_id	");
        query.append("        WHERE aa.comp_no = a.comp_no        						");
        query.append(this.getDateWhere(orgRptCraftWorkTimeTopCommonDTO, user));
        query.append("        and bb.wo_status in ('C', 'PRC')       					");
        query.append("        and aa.emp_id = a.emp_id),0)        	WOWORKTIME        	");
        query.append("        ,NVL((SELECT SUM(aa.work_time)        					");
        query.append("        FROM TAPMINSLIST aa        								");
        query.append("        WHERE aa.comp_no = a.comp_no        						");
        query.append(this.getDateWhere(orgRptCraftWorkTimeTopCommonDTO, user));
        query.append("         and aa.PMSCHED_STATUS in ('C', 'PRC')					");
        query.append("        and aa.emp_id = a.emp_id),0)        	PMWORKTIME        	");
        query.append("FROM TAEMP a        												");
        query.append("WHERE 1=1        													");
        query.append(this.getWhere(orgRptCraftWorkTimeTopCommonDTO, user));
        query.append("						 ) x        								");
        query.append("WHERE 1=1        													");
        query.append("AND (x.woWorkTime + x.pmWorkTime) != 0   							");
        query.getOrderByQuery("TOTWORKTIME DESC", orgRptCraftWorkTimeTopCommonDTO.getOrderBy(), orgRptCraftWorkTimeTopCommonDTO.getDirection());
        

        return getJdbcTemplate().queryForList(query.toString(orgRptCraftWorkTimeTopCommonDTO.getIsLoadMaxCount(), orgRptCraftWorkTimeTopCommonDTO.getFirstRow()));
    }
    
    public String getWhere(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
            
        query.getAndQuery("a.comp_no", user.getCompNo());
        
        // 부서
        query.getDeptLevelQuery("a.DEPT_ID", orgRptCraftWorkTimeTopCommonDTO.getFilterDeptId(), orgRptCraftWorkTimeTopCommonDTO.getFilterDeptDesc(), user.getCompNo());
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT x.description FROM  TAPLANT x WHERE x.comp_no = '"+user.getCompNo()+"' AND x.plant = a.plant )", 
        		orgRptCraftWorkTimeTopCommonDTO.getFilterPlantId(), orgRptCraftWorkTimeTopCommonDTO.getFilterPlantDesc());
        
        //작업그룹
        query.getWkCtrLevelQuery("a.wkctr_id", orgRptCraftWorkTimeTopCommonDTO.getFilterWkCtrId(), orgRptCraftWorkTimeTopCommonDTO.getFilterWkCtrDesc(), user.getCompNo());
        
        return query.toString();
    }
    
    public String getDateWhere(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
            
        // 월
        query.getAndDateQuery("SUBSTR(aa.start_date,0,6)", orgRptCraftWorkTimeTopCommonDTO.getFilterStartDate(), orgRptCraftWorkTimeTopCommonDTO.getFilterEndDate());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    						");
        query.append("       COUNT(1)                           						");
        query.append("FROM (                                    						");
        query.append("SELECT      			       										");
        query.append("            '' SEQNO        										");
        query.append("            ,x.EMPID        										");
        query.append("            ,x.EMPDESC        									");
        query.append("            ,x.PLANT        										");
        query.append("            ,x.PLANTDESC        									");
        query.append("            ,x.DEPTID        										");
        query.append("            ,x.DEPTDESC        									");
        query.append("            ,x.WOWORKTIME        									");
        query.append("            ,x.PMWORKTIME        									");
        query.append("            ,(x.woWorkTime + x.pmWorkTime)   TOTWORKTIME        	");
        query.append("FROM (        													");
        query.append("SELECT     ''                    				SEQNO        		");
        query.append("        ,a.EMP_ID            					EMPID        		");
        query.append("        ,a.emp_name            				EMPDESC        		");
        query.append("        , a.plant            					PLANT        		");
        query.append("        ,(SELECT description        								");
        query.append("        FROM TAPLANT        										");
        query.append("        WHERE comp_no = a.comp_no        							");
        query.append("        and plant = a.plant)    				PLANTDESC        	");
        query.append("        ,a.DEPT_ID                			DEPTID        		");
        query.append("        ,(SELECT description        								");
        query.append("        FROM TADEPT        										");
        query.append("        WHERE comp_no = a.comp_no        							");
        query.append("        and dept_id = a.dept_id)    			DEPTDESC        	");
        query.append("        ,NVL((SELECT SUM(aa.work_time)        					");
        query.append("        FROM TAWOCRAFT aa inner join TAWORKORDER bb				");
        query.append("        on aa.comp_no = bb.comp_no and aa.wkor_id = bb.wkor_id	");
        query.append("        WHERE aa.comp_no = a.comp_no        						");
        query.append(this.getDateWhere(orgRptCraftWorkTimeTopCommonDTO, user));
        query.append("        and bb.wo_status in ('C', 'PRC')       					");
        query.append("        and aa.emp_id = a.emp_id),0)        	WOWORKTIME        	");
        query.append("        ,NVL((SELECT SUM(aa.work_time)        					");
        query.append("        FROM TAPMINSLIST aa        								");
        query.append("        WHERE aa.comp_no = a.comp_no        						");
        query.append(this.getDateWhere(orgRptCraftWorkTimeTopCommonDTO, user));
        query.append("         and aa.PMSCHED_STATUS in ('C', 'PRC')					");
        query.append("        and aa.emp_id = a.emp_id),0)        	PMWORKTIME        	");
        query.append("FROM TAEMP a        												");
        query.append("WHERE 1=1        													");
        query.append(this.getWhere(orgRptCraftWorkTimeTopCommonDTO, user));
        query.append("						 ) x        								");
        query.append("WHERE 1=1        													");
        query.append("AND (x.woWorkTime + x.pmWorkTime) != 0   							");
        query.append(") a                                       						");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

	@Override
	public List findChartList(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        	        										");
        query.append("            '' SEQNO        										");
        query.append("            ,x.EMPID        										");
        query.append("            ,x.EMPDESC        									");
        query.append("            ,x.PLANT        										");
        query.append("            ,x.PLANTDESC        									");
        query.append("            ,x.DEPTID        										");
        query.append("            ,x.DEPTDESC        									");
        query.append("            ,ROUND(x.WOWORKTIME/60,1)        WOWORKTIME			");
        query.append("            ,ROUND(x.PMWORKTIME/60,1)        PMWORKTIME			");
        query.append("            ,(ROUND(x.WOWORKTIME/60,1) + ROUND(x.PMWORKTIME/60,1))   TOTWORKTIME  ");
        query.append("FROM (        													");
        query.append("SELECT     ''                    				SEQNO        		");
        query.append("        ,a.EMP_ID            					EMPID        		");
        query.append("        ,a.emp_name            				EMPDESC        		");
        query.append("        , a.plant            					PLANT        		");
        query.append("        ,(SELECT description        								");
        query.append("        FROM TAPLANT        										");
        query.append("        WHERE comp_no = a.comp_no        							");
        query.append("        and plant = a.plant)    				PLANTDESC        	");
        query.append("        ,a.DEPT_ID                			DEPTID        		");
        query.append("        ,(SELECT description        								");
        query.append("        FROM TADEPT        										");
        query.append("        WHERE comp_no = a.comp_no        							");
        query.append("        and dept_id = a.dept_id)    			DEPTDESC        	");
        query.append("        ,NVL((SELECT SUM(aa.work_time)        					");
        query.append("        FROM TAWOCRAFT aa inner join TAWORKORDER bb				");
        query.append("        on aa.comp_no = bb.comp_no and aa.wkor_id = bb.wkor_id	");
        query.append("        WHERE aa.comp_no = a.comp_no        						");
        query.append(this.getDateWhere(orgRptCraftWorkTimeTopCommonDTO, user));
        query.append("        and bb.wo_status in ('C', 'PRC')       					");
        query.append("        and aa.emp_id = a.emp_id),0)        	WOWORKTIME        	");
        query.append("        ,NVL((SELECT SUM(aa.work_time)        					");
        query.append("        FROM TAPMINSLIST aa        								");
        query.append("        WHERE aa.comp_no = a.comp_no        						");
        query.append(this.getDateWhere(orgRptCraftWorkTimeTopCommonDTO, user));
        query.append("         and aa.PMSCHED_STATUS in ('C', 'PRC')					");
        query.append("        and aa.emp_id = a.emp_id),0)        	PMWORKTIME        	");
        query.append("FROM TAEMP a        												");
        query.append("WHERE 1=1        													");
        query.append(this.getWhere(orgRptCraftWorkTimeTopCommonDTO, user));
        query.append("						 ) x        								");
        query.append("WHERE 1=1        													");
        query.append("AND (x.woWorkTime + x.pmWorkTime) != 0   							");
        query.append("AND ROWNUM <=20        				  							");
        query.append("ORDER BY TOTWORKTIME DESC        									");

        return getJdbcTemplate().queryForList(query.toString());
    }
    

}