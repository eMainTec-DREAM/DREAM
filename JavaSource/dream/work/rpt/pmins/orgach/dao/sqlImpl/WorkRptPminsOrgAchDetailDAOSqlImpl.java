package dream.work.rpt.pmins.orgach.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.pmins.orgach.dao.WorkRptPminsOrgAchDetailDAO;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchDetailDTO;

/**
 * 예방점검 이행율(조직) 상세 목록 dao
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptPminsOrgAchDetailDAOTarget"
 * @spring.txbn id="workRptPminsOrgAchDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPminsOrgAchDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptPminsOrgAchDetailDAO
{
    /**
     * 월별 예방점검 이행율(조직) grid find
     * @author  sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsOrgAchDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT        																		");
        query.append("    x.month															AS	month		");
        query.append("    ,COUNT(x.pminslistId) 											AS	planed		");
        query.append("    ,ISNULL(SUM(CASE WHEN x.pmschedStatus='C' THEN 1 ELSE 0 END),0)	AS	completed	");
        query.append("    ,ISNULL(ROUND(SUM(CASE WHEN x.pmschedStatus='C' THEN 1 ELSE 0 END)/CONVERT(FLOAT,CASE WHEN COUNT(1)=0 THEN NULL ELSE COUNT(1) END)*100,2),0)	AS	achievement	");
        query.append("FROM (        																		");
        query.append("    SELECT        																	");
        query.append("        SUBSTRING(a.wkor_date,1,4)+'-'+SUBSTRING(a.wkor_date,5,2)		month			");
        query.append("        ,a.pminslist_id                                              	pminslistId		");
        query.append("        ,a.pmsched_status                                            	pmschedStatus 	");
        query.append("        ,a.comp_no                                            		compNo 			");
        query.append("    FROM TAPMINSLIST a        														");
        query.append("    INNER JOIN TAPMLST b ON a.comp_no = b.comp_no AND a.pm_id = b.pm_id				");
        query.append("    WHERE 1=1     																	");
        query.append(this.getWhere(workRptPminsOrgAchDetailDTO,loginUser));
        query.append(") x       																			");
        query.append("GROUP BY x.month, x.compNo															");
        query.getOrderByQuery("x.compNo", "x.month", workRptPminsOrgAchDetailDTO.getOrderBy(), workRptPminsOrgAchDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPminsOrgAchDetailDTO.getIsLoadMaxCount(), workRptPminsOrgAchDetailDTO.getFirstRow()));
    }
    
    public String getWhere(WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        // 회사
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        // 부서
        query.append(" AND a.dept_id IN (SELECT c.dept_id FROM SFADEPT_CALL('"+loginUser.getCompNo()+"','"+workRptPminsOrgAchDetailDTO.getDeptId()+"','') c)		");
        // 일정삭제여부
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("b.is_deleted", "N");

        String fromDate = CommonUtil.dateToData(workRptPminsOrgAchDetailDTO.getStartDate()+"-01");
        String toDate   = CommonUtil.dateToData(workRptPminsOrgAchDetailDTO.getEndDate()+"-01");        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND a.wkor_date >=  CONVERT(VARCHAR,'"+fromDate+"')     ");
        }        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND a.wkor_date <=  CONVERT(VARCHAR, DATEADD(DAY, -1, DATEADD(MONTH, 1, '"+toDate+"')), 112)        ");
        }
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no =a.comp_no AND plant = a.plant )", 
        		workRptPminsOrgAchDetailDTO.getPlantId(), workRptPminsOrgAchDetailDTO.getPlantDesc());
        
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
	public String findTotalCount(WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO, User loginUser) 
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                        ");
        query.append("       COUNT(1)                               ");
        query.append("	FROM (                						");
        query.append("			SELECT        						");
        query.append("  	 		x.comp_no						");
        query.append("			  FROM (        					");
        query.append("    		 	SELECT        					");
        query.append("        			a.comp_no					");
        query.append("        		  , SUBSTRING(a.wkor_date,1,4)+'-'+SUBSTRING(a.wkor_date,5,2)	month	");
        query.append("    			  FROM TAPMINSLIST a        	");
        query.append("    			 INNER JOIN TAPMLST b			");
        query.append("    			 	ON a.comp_no = b.comp_no	");
        query.append("    			   AND a.pm_id = b.pm_id		");
        query.append("   			  WHERE 1=1     				");
        query.append(this.getWhere(workRptPminsOrgAchDetailDTO,loginUser));
        query.append("			) x       							");
        query.append("GROUP BY x.month, x.comp_no      				");
        query.append("		) a				       					");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
    
}