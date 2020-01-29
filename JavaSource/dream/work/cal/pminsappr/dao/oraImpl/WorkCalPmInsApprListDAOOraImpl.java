package dream.work.cal.pminsappr.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.cal.pminsappr.dao.WorkCalPmInsApprListDAO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;

/**
 * 예방점검계획승인 - 목록 dao
 * @author  kim21017
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workCalPmInsApprListDAOTarget"
 * @spring.txbn id="workCalPmInsApprListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmInsApprListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkCalPmInsApprListDAO
{
    public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT															        ");
        query.append("		''										AS seqNo			        ");
        query.append("		,''										AS isDelCheck		        ");
        query.append("		,x.pminsschedappr_id					AS pmInsSchedApprId	        ");
        query.append("		,x.pminsschedappr_no					AS pmInsSchedApprNo	        ");
        query.append("		,x.description							AS description		        ");
        query.append("		,x.start_date							AS startDate		        ");
        query.append("		,x.end_date								AS endDate			        ");
        query.append("		,x.wo_dept								AS deptId			        ");
        query.append("		,( SELECT a.dept_no 										        ");
        query.append("		   FROM TADEPT a											        ");
        query.append("		   WHERE 1=1												        ");
        query.append("		    AND a.comp_no = x.comp_no								        ");
        query.append("		    AND a.dept_id = x.wo_dept)			AS deptNo			        ");
        query.append("		,(SELECT a.description 										        ");
        query.append("			FROM TADEPT a											        ");
        query.append("			WHERE 1=1												        ");
        query.append("			AND a.comp_no = x.comp_no								        ");
        query.append("			AND a.dept_id = x.wo_dept)			AS deptDesc			        ");
        query.append("		,x.upd_date								AS updDate			        ");
        query.append("		,x.upd_by								AS updBy			        ");
        query.append("		,( SELECT a.emp_no											        ");
        query.append("		   FROM TAEMP a											        ");
        query.append("		   WHERE 1=1												        ");
        query.append("		    AND a.comp_no = x.comp_no								        ");
        query.append("		    AND a.emp_id = x.upd_by)			AS updEmpNo			        ");
        query.append("		,(SELECT a.emp_name											        ");
        query.append("			FROM TAEMP a											        ");
        query.append("			WHERE 1=1												        ");
        query.append("			AND a.comp_no = x.comp_no								        ");
        query.append("			AND a.emp_id = x.upd_by)			AS updDesc			        ");
        query.append("		,x.pminsschedapp_status					AS pmInsSchedAppStatusId	");
        query.append("		,SFACODE_TO_DESC(x.pminsschedapp_status,'PMINSSCHEDAPP_STATUS','SYS','','"+user.getLangId()+"') AS pmInsSchedAppStatusDesc	");
        query.append("		,x.remark								AS remark			        ");
        query.append("      ,x.yyyy                                 AS yyyy 	                ");
        query.append("      ,( CASE WHEN x.yyyymm IS NOT NULL                                   ");
        query.append("              THEN SUBSTR(x.yyyymm, 0, 4)||'-'||SUBSTR(x.yyyymm, 5, 2)    ");
        query.append("              ELSE ''                                                     ");
        query.append("         END )                          		AS yyyymm                   ");
        query.append("		,x.plant								AS plant	        		");
        query.append("      ,(SELECT description                                         		");
        query.append("        FROM TAPLANT                                             			");
        query.append("        WHERE comp_no = x.comp_no                                 		");
        query.append("         AND plant = x.plant)                 AS plantDesc	        	");
        query.append("		,x.pminsschedappr_type					AS pminsschedapprType		");
        query.append("		,SFACODE_TO_DESC(x.pminsschedappr_type,'PMINSSCHEDAPPR_TYPE','SYS','','"+user.getLangId()+"') AS pminsschedapprTypeDesc	");
        query.append("FROM TAPMINSSCHEDAPPR	x											        ");
        query.append("WHERE 1=1															        ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.append(this.getWhere(workCalPmInsApprCommonDTO,user));
        query.getOrderByQuery("x.upd_date DESC", workCalPmInsApprCommonDTO.getOrderBy(), workCalPmInsApprCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workCalPmInsApprCommonDTO.getIsLoadMaxCount(), workCalPmInsApprCommonDTO.getFirstRow()));
    }
    
    public int deleteList(String id, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
		
    	query.append("DELETE FROM TAPMINSSCHEDAPPR							");
    	query.append("WHERE 1=1												");
    	query.getAndNumKeyQuery("pminsschedappr_id", id);
    	query.getStringEqualQuery("comp_no", user.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    private String getWhere(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user)
    {        
    	String lang = user.getLangId();
    	
        QueryBuffer query = new QueryBuffer();
        String startDate = workCalPmInsApprCommonDTO.getFilterStartDate();
        String endDate = workCalPmInsApprCommonDTO.getFilterEndDate();
        
        if (!"".equals(workCalPmInsApprCommonDTO.getPmInsSchedApprId()))
        {
            query.getAndQuery("x.pminsschedappr_id", workCalPmInsApprCommonDTO.getPmInsSchedApprId());
            return query.toString();
        }
        
        query.getAndQuery("x.pminsschedappr_type", workCalPmInsApprCommonDTO.getPminsschedapprType());
        
        //작성일자
        query.getAndDateQuery("x.upd_date", startDate, endDate);
        //부서
        query.getDeptLevelQuery("x.wo_dept", workCalPmInsApprCommonDTO.getFilterDeptId(), workCalPmInsApprCommonDTO.getFilterDeptDesc(), user.getCompNo());
        //제목
        query.getLikeQuery("x.description", workCalPmInsApprCommonDTO.getFilterDesc());
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		workCalPmInsApprCommonDTO.getFilterPlantId(), workCalPmInsApprCommonDTO.getFilterPlantDesc());
        //년
        query.getLikeQuery("x.yyyy", workCalPmInsApprCommonDTO.getFilterYyyy());
        //년월
        query.getLikeQuery("x.YYYYMM", workCalPmInsApprCommonDTO.getFilterYyyymm());
        //예방점검승인 계획/완료
        query.getSysCdQuery("x.pminsschedappr_type", workCalPmInsApprCommonDTO.getFilterPminsschedapprType(), workCalPmInsApprCommonDTO.getFilterPminsschedapprTypeDesc(), "PMINSSCHEDAPPR_TYPE", user.getCompNo(), lang);
        //승인번호
        query.getLikeQuery("x.pminsschedappr_no", workCalPmInsApprCommonDTO.getFilterPmInsSchedApprNo());
        
        return query.toString();
    }

    public String findTotalCount(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT								");
        query.append("      COUNT(1)						");
        query.append("FROM TAPMINSSCHEDAPPR	x				");
        query.append("WHERE 1=1								");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.append(this.getWhere(workCalPmInsApprCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}