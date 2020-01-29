package dream.work.rpt.pmins.orgach.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.pmins.orgach.dao.WorkRptPminsOrgAchListDAO;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchCommonDTO;

/**
 * 예방점검 이행율(조직) 목록 dao
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptPminsOrgAchListDAOTarget"
 * @spring.txbn id="workRptPminsOrgAchListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPminsOrgAchListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptPminsOrgAchListDAO
{	
    /**
     * 예방점검 이행율(조직) grid find
     * @author  sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsOrgAchCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;						");
        
        query.append("SELECT 																");
        query.append("    ''                                           		AS	seqNo		");
        query.append("    ,x.dept_id                                        AS	deptId		");
        query.append("    ,x.description                                    AS	deptDesc	");
        // planed
        query.append("    ,(	SELECT                                                    	");
        query.append("				COUNT(1)        										");
        query.append("        	FROM TAPMINSLIST a			                                ");
        query.append("    		INNER JOIN TAPMLST b ON a.comp_no = b.comp_no AND a.pm_id = b.pm_id	");
        query.append("        	WHERE 1=1                   			                    ");
        query.append(this.getWhere(workRptPminsOrgAchCommonDTO,loginUser));
        query.append("		) 												AS	planed    	");
        // completed
        query.append("    ,(	SELECT														");
        query.append("          	ISNULL(SUM(CASE WHEN a.pmsched_status = 'C' THEN 1 ELSE 0 END), 0)	");
        query.append("			FROM TAPMINSLIST a        									");
        query.append("    		INNER JOIN TAPMLST b ON a.comp_no = b.comp_no AND a.pm_id = b.pm_id	");
        query.append("			WHERE 1=1                                                   ");
        query.append(this.getWhere(workRptPminsOrgAchCommonDTO,loginUser));
        query.append("		) 												AS	completed	");
        // incompleted
        query.append("    ,(	SELECT														");
        query.append("          	ISNULL(SUM(CASE WHEN a.pmsched_status != 'C' THEN 1 ELSE 0 END), 0)	");
        query.append("			FROM TAPMINSLIST a        									");
        query.append("    		INNER JOIN TAPMLST b ON a.comp_no = b.comp_no AND a.pm_id = b.pm_id	");
        query.append("			WHERE 1=1                                                   ");
        query.append(this.getWhere(workRptPminsOrgAchCommonDTO,loginUser));
        query.append("		) 												AS	incompleted	");
        // achievement
        query.append("    ,(	SELECT														");
        query.append("          	ISNULL(ROUND(SUM(CASE WHEN a.pmsched_status = 'C' THEN 1 ELSE 0 END)/CONVERT(FLOAT, CASE WHEN COUNT(1)=0 THEN NULL ELSE COUNT(1) END)*100,2),0)	");
        query.append("		  	FROM TAPMINSLIST a        									");
        query.append("    		INNER JOIN TAPMLST b ON a.comp_no = b.comp_no AND a.pm_id = b.pm_id	");
        query.append("			WHERE 1=1                                                   ");
        query.append(this.getWhere(workRptPminsOrgAchCommonDTO,loginUser));
        query.append("		) 												AS	achievement	");
        
        query.append("FROM TADEPT x															");
        query.append("WHERE 1=1                                                   			");
        query.append(this.getOuterWhere(workRptPminsOrgAchCommonDTO,loginUser));
        
        query.getOrderByQuery("x.dept_id", "x.dept_id", workRptPminsOrgAchCommonDTO.getOrderBy(), workRptPminsOrgAchCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPminsOrgAchCommonDTO.getIsLoadMaxCount(), workRptPminsOrgAchCommonDTO.getFirstRow()));
    }
    
    public String getOuterWhere(WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO,User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	query.getAndQuery("x.is_use", "Y");
    	query.getAndQuery("x.dept_categ", workRptPminsOrgAchCommonDTO.getFilterOrgId());
        query.getDeptLevelQuery("x.dept_id", workRptPminsOrgAchCommonDTO.getFilterDeptId(), workRptPminsOrgAchCommonDTO.getFilterDeptDesc(), loginUser.getCompNo());

        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.plant = x.plant )", 
        		workRptPminsOrgAchCommonDTO.getFilterPlantId(), workRptPminsOrgAchCommonDTO.getFilterPlantDesc());

    	return query.toString();
    }
    public String getWhere(WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        // 회사
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        // 일정삭제여부
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("b.is_deleted", "N");

        String fromDate = CommonUtil.dateToData(workRptPminsOrgAchCommonDTO.getFilterStartDate()+"-01");
        String toDate   = CommonUtil.dateToData(workRptPminsOrgAchCommonDTO.getFilterEndDate()+"-01");
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND a.wkor_date >=  '"+fromDate+"'     ");
        }        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND a.wkor_date <=  CONVERT(VARCHAR, DATEADD(DAY, -1, DATEADD(MONTH, 1, '"+toDate+"')), 112)        ");
        }
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no =a.comp_no AND plant = a.plant )", 
                workRptPminsOrgAchCommonDTO.getFilterPlantId(), workRptPminsOrgAchCommonDTO.getFilterPlantDesc());
        // 조직
        query.append(" AND a.dept_id IN (SELECT dept_id FROM SFADEPT_CALL('"+loginUser.getCompNo()+"',x.dept_id,'') )		");
        
        query.append("AND NOT EXISTS (SELECT x.equip_id							");
    	query.append("					 FROM  TAEQUIPMENT x            		");
    	query.append("					 WHERE 1=1                    			");
    	query.append("					 AND   x.equip_id = a.equip_id			");
    	query.getStringEqualQuery("x.comp_no", loginUser.getCompNo());
    	query.getAndQuery("x.is_last_version", "N");
        query.append("									)						");
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;        	");
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TADEPT x												");
        query.append("WHERE 1=1 												");
        query.append(this.getOuterWhere(workRptPminsOrgAchCommonDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
    
}