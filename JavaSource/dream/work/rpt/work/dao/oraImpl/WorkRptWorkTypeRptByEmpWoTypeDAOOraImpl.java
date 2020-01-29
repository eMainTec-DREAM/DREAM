package dream.work.rpt.work.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.work.dao.WorkRptWorkTypeRptByEmpWoTypeDAO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpWoTypeDTO;

/**
 * 담당자별작업현황 탭 목록 dao 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptWorkTypeRptByEmpWoTypeDAOTarget"
 * @spring.txbn id="workRptWorkTypeRptByEmpWoTypeDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptWorkTypeRptByEmpWoTypeDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptWorkTypeRptByEmpWoTypeDAO
{

	@Override
	public List findWoTypeList(WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO, User user) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT    ''                                                          	seqNo		");
        query.append("        , SUBSTR(x.wkor_date,0,6)											yyyyMM		");
        query.append("        , x.wo_type														woType		");
        query.append("        , (SELECT key_name															");
        query.append("        	FROM TALANG																	");
        query.append("        	WHERE key_no = 'WO_TYPE.'||x.wo_type										");
        query.append("        	and lang = '"+user.getLangId()+"'											");
        query.append("        	and key_type = 'CODESET')										woTypeDesc	");
        query.append("        , x.emp_id                                                    	empId		");
        query.append("        , (SELECT emp_name															");
        query.append("        	FROM TAEMP																	");
        query.append("        	WHERE comp_no = x.comp_no													");
        query.append("        	AND emp_id = x.emp_id)                                      	empDesc		");
        query.append("        , x.dept_id                                                   	deptId		");
        query.append("        ,	(SELECT description															");
        query.append("        	FROM TADEPT																	");
        query.append("        	WHERE comp_no = x.comp_no													");
        query.append("        	AND dept_id = x.dept_id)                                    	deptDesc	");
        query.append("        , COUNT(x.wkor_id)                                            	cntAll		");
        query.append("        , COUNT(CASE when x.wo_status != 'C' then x.wkor_id end)    		cntNCmpt	");
        query.append("        , COUNT(CASE when x.wo_status = 'C' then x.wkor_id end)       	cntCmpt		");
        query.append("from TAWORKORDER x																	");
        query.append("WHERE 1=1                                                            	     			");
        query.append(this.getWhere(workRptWorkTypeRptByEmpWoTypeDTO, user));
        query.append("GROUP BY x.comp_no, x.emp_id, x.dept_id, SUBSTR(x.wkor_date,0,6), x.wo_type			");
        query.getOrderByQuery("x.emp_id", "left(x.wkor_date,6), x.wo_type", workRptWorkTypeRptByEmpWoTypeDTO.getOrderBy(), workRptWorkTypeRptByEmpWoTypeDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptWorkTypeRptByEmpWoTypeDTO.getIsLoadMaxCount(), workRptWorkTypeRptByEmpWoTypeDTO.getFirstRow()));
    }
	
	public String getWhere(WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO, User user)
    {
		QueryBuffer query = new QueryBuffer();
        
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.is_deleted", "N");
    	
        // 월
        String startDate = workRptWorkTypeRptByEmpWoTypeDTO.getFilterStartDate().replace("-", "")+"01";
        String endDate   = workRptWorkTypeRptByEmpWoTypeDTO.getFilterEndDate().replace("-", "")+"31";
        query.getAndDateQuery("x.wkor_date", startDate, endDate);
        
        // 공장
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		workRptWorkTypeRptByEmpWoTypeDTO.getFilterPlant(), workRptWorkTypeRptByEmpWoTypeDTO.getFilterPlantDesc());
        // 작업부서
        query.getAndQuery("x.dept_id", workRptWorkTypeRptByEmpWoTypeDTO.getDeptId());
        
        // 작업그룹
        query.getAndQuery("x.wkctr_id", workRptWorkTypeRptByEmpWoTypeDTO.getWkCtrId());
         
        // 담당자
        query.getAndQuery("x.emp_id", workRptWorkTypeRptByEmpWoTypeDTO.getEmpId());
        
        // 작업종류
        query.getSysCdQuery("x.wo_type", workRptWorkTypeRptByEmpWoTypeDTO.getFilterWoType(), workRptWorkTypeRptByEmpWoTypeDTO.getFilterWoTypeDesc(), "WO_TYPE", user.getCompNo(),user.getLangId());
        
        return query.toString();
    }

	@Override
	public String findTotalCount(WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO, User user) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT count(*)                               	");
        query.append("FROM (			                            	");
        query.append("		SELECT                                      ");
        query.append("    		''							seqNo       ");
        query.append("		FROM TAWORKORDER x							");
        query.append("		WHERE 1=1                                   ");
        query.append(this.getWhere(workRptWorkTypeRptByEmpWoTypeDTO, user));
        query.append("		GROUP BY x.comp_no, x.emp_id, x.dept_id, SUBSTR(x.wkor_date,0,6), x.wo_type	");
        query.append(") aa					                            ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }


    
}