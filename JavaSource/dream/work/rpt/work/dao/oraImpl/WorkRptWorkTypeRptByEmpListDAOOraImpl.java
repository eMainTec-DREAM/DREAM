package dream.work.rpt.work.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.work.dao.WorkRptWorkTypeRptByEmpListDAO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpCommonDTO;

/**
 * 담당자별작업현황 DAO 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptWorkTypeRptByEmpListDAOTarget"
 * @spring.txbn id="workRptWorkTypeRptByEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptWorkTypeRptByEmpListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptWorkTypeRptByEmpListDAO
{
    @Override
    public List findList(WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO, User user)
    {
        String filterStartDate	= "";
        String filterEndDate   	= "";
        String filterPlant		= "";
        String filterPlantDesc  = "";
        String filterWoType    	= "";
        String filterWoTypeDesc	= "";
        if (!"".equals(workRptWorkTypeRptByEmpCommonDTO.getFilterFromYyyyMm()) && workRptWorkTypeRptByEmpCommonDTO.getFilterFromYyyyMm() != null) {
        	filterStartDate = workRptWorkTypeRptByEmpCommonDTO.getFilterFromYyyyMm().replace("-", "");
		}
        if (!"".equals(workRptWorkTypeRptByEmpCommonDTO.getFilterToYyyyMm()) && workRptWorkTypeRptByEmpCommonDTO.getFilterToYyyyMm() != null) {
        	filterEndDate = workRptWorkTypeRptByEmpCommonDTO.getFilterToYyyyMm().replace("-", "");
		}
        if (!"".equals(workRptWorkTypeRptByEmpCommonDTO.getFilterPlant()) && workRptWorkTypeRptByEmpCommonDTO.getFilterPlant() != null) {
        	filterPlant = workRptWorkTypeRptByEmpCommonDTO.getFilterPlant();
		}
        if (!"".equals(workRptWorkTypeRptByEmpCommonDTO.getFilterPlantDesc()) && workRptWorkTypeRptByEmpCommonDTO.getFilterPlantDesc() != null) {
        	filterPlantDesc = workRptWorkTypeRptByEmpCommonDTO.getFilterPlantDesc();
		}
        if (!"".equals(workRptWorkTypeRptByEmpCommonDTO.getFilterWoType()) && workRptWorkTypeRptByEmpCommonDTO.getFilterWoType() != null) {
        	filterWoType = workRptWorkTypeRptByEmpCommonDTO.getFilterWoType();
        }
        if (!"".equals(workRptWorkTypeRptByEmpCommonDTO.getFilterWoTypeDesc()) && workRptWorkTypeRptByEmpCommonDTO.getFilterWoTypeDesc() != null) {
        	filterWoTypeDesc = workRptWorkTypeRptByEmpCommonDTO.getFilterWoTypeDesc();
        }
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT    ''                                                            	seqNo				");
        query.append("        , x.emp_id                                                    	empId				");
        query.append("        ,	(SELECT emp_name																	");
        query.append("        	FROM TAEMP																			");
        query.append("        	WHERE comp_no = x.comp_no															");
        query.append("        	AND emp_id = x.emp_id)                                        	empDesc				");
        query.append("        , x.dept_id                                                    	deptId				");
        query.append("        , (SELECT description																	");
        query.append("        	FROM TADEPT																			");
        query.append("        	WHERE comp_no = x.comp_no															");
        query.append("        	AND dept_id = x.dept_id)                                    	deptDesc			");
        query.append("        , x.wkctr_id                                                		wkctrId				");
        query.append("        ,(SELECT description																	");
        query.append("        	FROM TAWKCTR																		");
        query.append("        	WHERE comp_no = x.comp_no															");
        query.append("        	AND WKCTR_ID = x.WKCTR_ID)                                    	wkctrDesc			");
        query.append("        , COUNT(x.wkor_id)                                            	cntAll				");
        query.append("        , COUNT(CASE WHEN x.wo_status != 'C' THEN x.wkor_id END)    		cntNCmpt			");
        query.append("        , COUNT(CASE WHEN x.wo_status = 'C' THEN x.wkor_id END)        	cntCmpt				");
        query.append("        , '"+filterStartDate+"'											filterStartDate		");
        query.append("        , '"+filterEndDate+"'												filterEndDate		");
        query.append("        , '"+filterPlant+"'												filterPlant			");
        query.append("        , '"+filterPlantDesc+"'											filterPlantDesc		");
        query.append("        , '"+filterWoType+"'												filterWoType		");
        query.append("        , '"+filterWoTypeDesc+"'											filterWoTypeDesc	");
        query.append("from TAWORKORDER x																			");
        query.append("WHERE 1=1             																		");
        query.append(this.getWhere(workRptWorkTypeRptByEmpCommonDTO,user));
        query.append("group by x.comp_no, x.emp_id, x.dept_id, x.wkctr_id											");
        query.getOrderByQuery("x.emp_id desc", workRptWorkTypeRptByEmpCommonDTO.getOrderBy(), workRptWorkTypeRptByEmpCommonDTO.getDirection());
        
        
        return getJdbcTemplate().queryForList(query.toString(workRptWorkTypeRptByEmpCommonDTO.getIsLoadMaxCount(), workRptWorkTypeRptByEmpCommonDTO.getFirstRow()));
    }
    
    public String getWhere(WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        query.append("AND x.emp_id is not null 	");
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.is_deleted", "N");
    	
        // 월
        String startDate = workRptWorkTypeRptByEmpCommonDTO.getFilterFromYyyyMm().replace("-", "")+"01";
        String endDate   = workRptWorkTypeRptByEmpCommonDTO.getFilterToYyyyMm().replace("-", "")+"31";
        query.getAndDateQuery("x.wkor_date", startDate, endDate);
        
        // 공장
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		workRptWorkTypeRptByEmpCommonDTO.getFilterPlant(), workRptWorkTypeRptByEmpCommonDTO.getFilterPlantDesc());
        // 작업부서
        query.getDeptLevelQuery("x.dept_id", workRptWorkTypeRptByEmpCommonDTO.getFilterWrkDeptId(), workRptWorkTypeRptByEmpCommonDTO.getFilterWrkDeptDesc(), user.getCompNo());
        // 작업그룹
        query.getWkCtrLevelQuery("x.wkctr_id", workRptWorkTypeRptByEmpCommonDTO.getFilterWkCtrId(), workRptWorkTypeRptByEmpCommonDTO.getFilterWkCtrDesc(), user.getCompNo());
         
        // 담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no='"+user.getCompNo()+"')", 
        		workRptWorkTypeRptByEmpCommonDTO.getFilterEmpId(), workRptWorkTypeRptByEmpCommonDTO.getFilterEmpDesc());
        
        // 작업종류
        query.getSysCdQuery("x.wo_type", workRptWorkTypeRptByEmpCommonDTO.getFilterWoType(), workRptWorkTypeRptByEmpCommonDTO.getFilterWoTypeDesc(), "WO_TYPE", user.getCompNo(),user.getLangId());
        
        
        return query.toString();
    }

	@Override
	public String findTotalCount(WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO, User user) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT    COUNT(1)                                      				");
        query.append("FROM  (   SELECT x.comp_no                                      		");
        query.append("			FROM TAWORKORDER x											");
        query.append("			WHERE 1=1             										");
        query.append(this.getWhere(workRptWorkTypeRptByEmpCommonDTO,user));
        query.append("			group by x.comp_no, x.emp_id, x.dept_id, x.wkctr_id	) aa	");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}

    
}