package dream.work.rpt.pmins.orgach.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class WorkRptPminsOrgAchDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptPminsOrgAchDetailDAO
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        																		");
        query.append("    x.month 															AS	month       ");
        query.append("    ,COUNT(x.pminslistId) 											AS	planed      ");
        query.append("    ,NVL(SUM(CASE WHEN x.pmschedStatus='C' THEN 1 ELSE 0 END),0)		AS	completed	");
        query.append("    ,NVL(ROUND(SUM(CASE WHEN x.pmschedStatus='C' THEN 1 ELSE 0 END)/COUNT(1)*100,2),0)	AS	achievement	");
        query.append("FROM (                																");
        query.append("    SELECT                															");
        query.append("        SUBSTR(a.wkor_date,1,4)||'-'||SUBSTR(a.wkor_date,5,2)			month			");
        query.append("        ,a.pminslist_id                                               pminslistId   	");
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
        QueryBuffer query = new QueryBuffer();
        // 회사
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        // 부서
        query.getDeptLevelQuery("a.dept_id", workRptPminsOrgAchDetailDTO.getDeptId(), "", loginUser.getCompNo());
        // 일정삭제여부
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("b.is_deleted", "N");

        String fromDate = workRptPminsOrgAchDetailDTO.getStartDate();
        String toDate   = workRptPminsOrgAchDetailDTO.getEndDate();        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND a.wkor_date >=  TO_CHAR(TO_DATE('"+fromDate+"','YYYY-MM'),'YYYYMMDD')     ");
        }        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND a.wkor_date <  TO_CHAR(ADD_MONTHS(TO_DATE('"+toDate+"','YYYY-MM'),1),'YYYYMMDD')        ");
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

	public String findTotalCount(WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO, User loginUser) 
	{
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                        ");
        query.append("       COUNT(1)                               ");
        query.append("	FROM (                						");
        query.append("			SELECT        						");
        query.append("  	 		x.comp_no						");
        query.append("			  FROM (        					");
        query.append("    		 	SELECT        					");
        query.append("        			a.comp_no					");
        query.append("        		  , SUBSTR(a.wkor_date,1,4)||'-'||SUBSTR(a.wkor_date,5,2)	month	");
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
        
		return QueryBuffer.listToString(resultList);
	}
    
}