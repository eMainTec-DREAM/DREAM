package dream.work.planappr.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.planappr.dao.WorkPlanApprListDAO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;

/**
 * 작업계획승인 - 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workPlanApprListDAOTarget"
 * @spring.txbn id="workPlanApprListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPlanApprListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPlanApprListDAO
{
    public List findList(WorkPlanApprCommonDTO workPlanApprCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															");
        query.append("		''										AS seqNo			");
        query.append("		,''										AS isDelCheck		");
        query.append("		,x.woplanappr_id						AS woPlanApprId		");
        query.append("		,x.description							AS description		");
        query.append("		,x.start_date							AS startDate		");
        query.append("		,x.end_date								AS endDate			");
        query.append("		,x.dept_id								AS deptId			");
        query.append("		,(SELECT a.description 										");
        query.append("			FROM TADEPT a											");
        query.append("			WHERE 1=1												");
        query.append("			AND a.comp_no = x.comp_no								");
        query.append("			AND a.dept_id = x.dept_id)			AS deptDesc			");
        query.append("		,x.upd_date								AS updDate			");
        query.append("		,x.upd_by								AS updBy			");
        query.append("		,(SELECT a.emp_name											");
        query.append("			FROM TAEMP a											");
        query.append("			WHERE 1=1												");
        query.append("			AND a.comp_no = x.comp_no								");
        query.append("			AND a.emp_id = x.upd_by)			AS updDesc			");
        query.append("		,x.woplanappr_status					AS woPlanApprStatusId			");
        query.append("		,SFACODE_TO_DESC(x.woplanappr_status,'WOPLANAPPR_STATUS','SYS','','"+user.getLangId()+"') AS woPlanApprStatusDesc	");
        query.append("		,x.remark								AS remark			");
        query.append("      ,x.yyyy                                                     ");
        query.append("      ,x.yyyymm                                                   ");
        query.append("FROM TAWOPLANAPPR	x												");
        query.append("WHERE 1=1															");
        query.append(this.getWhere(workPlanApprCommonDTO,user));
        query.getOrderByQuery("x.upd_date DESC", workPlanApprCommonDTO.getOrderBy(), workPlanApprCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPlanApprCommonDTO.getIsLoadMaxCount(), workPlanApprCommonDTO.getFirstRow()));
    }
    
    public int deleteList(String id, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
		
    	query.append("DELETE FROM TAWOPLANAPPR	  ");
    	query.append("WHERE 1=1					  ");
    	query.getAndNumKeyQuery("woplanappr_id", id);
    	query.getStringEqualQuery("comp_no", user.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    private String getWhere(WorkPlanApprCommonDTO workPlanApprCommonDTO, User user)
    {        
    	String lang = user.getLangId();
    	
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());

        String startDate = workPlanApprCommonDTO.getFilterStartDate();
        String endDate = workPlanApprCommonDTO.getFilterEndDate();
        
        if (!"".equals(workPlanApprCommonDTO.getWoPlanApprId()))
        {
            query.getAndQuery("x.woplanappr_id", workPlanApprCommonDTO.getWoPlanApprId());
            return query.toString();
        }
        
        query.getAndQuery("x.woplanappr_type", workPlanApprCommonDTO.getWoplanapprType());
        //작성일자
        query.getAndDateQuery("x.upd_date", startDate, endDate);
        //부서
        query.getDeptLevelQuery("x.dept_id", workPlanApprCommonDTO.getFilterDeptId(), workPlanApprCommonDTO.getFilterDeptDesc(), user.getCompNo());
        //제목
        query.getLikeQuery("x.description", workPlanApprCommonDTO.getFilterDesc());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		workPlanApprCommonDTO.getFilterPlantId(), workPlanApprCommonDTO.getFilterPlantDesc());

        return query.toString();
    }

    public String findTotalCount(WorkPlanApprCommonDTO workPlanApprCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = workPlanApprCommonDTO.getCompNo();
        query.append("SELECT							");
        query.append("      COUNT(1)					");
        query.append("FROM TAWOPLANAPPR	x				");
        query.append("WHERE 1=1							");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.append(this.getWhere(workPlanApprCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}