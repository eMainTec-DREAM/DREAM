package dream.work.rpt.work.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.work.dao.WorkRptWorkTypeRptByEmpMonthDAO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpMonthDTO;

/**
 * 담당자별작업현황 - 담당자 월별작업현황 탭 목록 dao 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptWorkTypeRptByEmpMonthDAOTarget"
 * @spring.txbn id="workRptWorkTypeRptByEmpMonthDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptWorkTypeRptByEmpMonthDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptWorkTypeRptByEmpMonthDAO
{

	@Override
	public List findMonthList(WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT    ''                                                            	seqNo		");
        query.append("        , SUBSTR(x.wkor_date,0,6)                                        	yyyyMM		");
        query.append("        , x.emp_id                                                    	empId		");
        query.append("        , (SELECT emp_name															");
        query.append("        	FROM TAEMP																	");
        query.append("        	WHERE comp_no = x.comp_no													");
        query.append("        	AND emp_id = x.emp_id)                                        	empDesc		");
        query.append("        , x.dept_id                                                    	deptId		");
        query.append("        , (SELECT description															");
        query.append("        	FROM TADEPT																	");
        query.append("        	WHERE comp_no = x.comp_no													");
        query.append("        	AND dept_id = x.dept_id)                                    	deptDesc	");
        query.append("        , COUNT(x.wkor_id)                                            	cntAll		");
        query.append("        , COUNT(case when x.wo_status != 'C' then x.wkor_id end)    		cntNCmpt	");
        query.append("        , COUNT(case when x.wo_status = 'C' then x.wkor_id end)        	cntCmpt		");
        query.append("FROM TAWORKORDER x																	");
        query.append("WHERE 1=1                                                                 			");
        query.append(this.getWhere(workRptWorkTypeRptByEmpMonthDTO,user));
        query.append("group by x.comp_no, x.emp_id, x.dept_id, SUBSTR(x.wkor_date,0,6)						");
        query.getOrderByQuery("x.emp_id", "SUBSTR(x.wkor_date,0,6)", workRptWorkTypeRptByEmpMonthDTO.getOrderBy(), workRptWorkTypeRptByEmpMonthDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptWorkTypeRptByEmpMonthDTO.getIsLoadMaxCount(), workRptWorkTypeRptByEmpMonthDTO.getFirstRow()));
	}
	
	public String getWhere(WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO, User user)
    {
		QueryBuffer query = new QueryBuffer();
        
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.is_deleted", "N");
    	
        // 월
        String startDate = workRptWorkTypeRptByEmpMonthDTO.getFilterStartDate().replace("-", "")+"01";
        String endDate   = workRptWorkTypeRptByEmpMonthDTO.getFilterEndDate().replace("-", "")+"31";
        query.getAndDateQuery("x.wkor_date", startDate, endDate);
        
        // 공장
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		workRptWorkTypeRptByEmpMonthDTO.getFilterPlant(), workRptWorkTypeRptByEmpMonthDTO.getFilterPlantDesc());
        // 작업부서
        query.getAndQuery("x.dept_id", workRptWorkTypeRptByEmpMonthDTO.getDeptId());
        
        // 작업그룹
        query.getAndQuery("x.wkctr_id", workRptWorkTypeRptByEmpMonthDTO.getWkCtrId());
         
        // 담당자
        query.getAndQuery("x.emp_id", workRptWorkTypeRptByEmpMonthDTO.getEmpId());
        
        // 작업종류
        query.getSysCdQuery("x.wo_type", workRptWorkTypeRptByEmpMonthDTO.getFilterWoType(), workRptWorkTypeRptByEmpMonthDTO.getFilterWoTypeDesc(), "WO_TYPE", user.getCompNo(),user.getLangId());
        
        return query.toString();
    }
	

	@Override
	public String findTotalCount(WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO, User user) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT count(*)                               	");
        query.append("FROM (			                            	");
        query.append("		SELECT                                      ");
        query.append("    		''							seqNo       ");
        query.append("		FROM TAWORKORDER x							");
        query.append("		WHERE 1=1                                 	");
        query.append(this.getWhere(workRptWorkTypeRptByEmpMonthDTO,user));
        query.append("		GROUP BY x.comp_no, x.emp_id, x.dept_id, SUBSTR(x.wkor_date,0,6)	");
        query.append(") aa					                            ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }


    
}