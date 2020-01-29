package dream.work.cal.pminsappr.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.cal.pminsappr.dao.WorkCalPmInsApprWorkListDAO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;

/**
 * 예방점검계획승인-점검작업 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workCalPmInsApprWorkListDAOTarget"
 * @spring.txbn id="workCalPmInsApprWorkListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmInsApprWorkListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkCalPmInsApprWorkListDAO
{
    public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;          ");
        query.append("SELECT                                                                                        ");
        query.append("         ''                                                   	AS seqNo            ");
        query.append("       , ''                                                   	AS isDelCheck       ");
        query.append("       , y.pminssched_id                                      	AS pmInsSchedId     ");
        query.append("       , y.plan_date                                          	AS planDate         ");
        query.append("       , y.sched_date                                         	AS schedDate        ");
        query.append("       , y.actual_date                                        	AS completeDate     ");
        query.append("       , z.pm_no                                              	AS pmNo             ");
        query.append("       , z.description                                        	AS pmDesc           ");
        query.append("       , y.pmsched_status                                     	AS pmStatusCode     ");
        query.append("       , dbo.SFACODE_TO_DESC(y.pmsched_status,'PMSCHED_STATUS','SYS','','"+user.getLangId()+"') AS pmSchedStatus         ");
        query.append("       ,(SELECT b.full_desc                                                           ");
        query.append("         FROM TAEQUIPMENT a, TAEQLOC b                                                ");
        query.append("         WHERE a.comp_no = b.comp_no                                                  ");
        query.append("          AND a.eqloc_id = b.eqloc_id                                                	");
        query.append("           AND a.comp_no = y.comp_no                                                  ");
        query.append("            AND a.equip_id = y.equip_id)                         	AS eqLocDesc        ");
        query.append("       ,(SELECT aa.item_no                                                   			");
        query.append("         FROM TAEQUIPMENT aa                                     						");
        query.append("         WHERE y.comp_no = aa.comp_no                                               	");
        query.append("          AND y.equip_id = aa.equip_id)							AS equipNo     		");
        query.append("       ,(SELECT aa.description                                                    	");
        query.append("         FROM TAEQUIPMENT aa                                           				");
        query.append("         WHERE y.comp_no = aa.comp_no                                                 ");
        query.append("          AND y.equip_id = aa.equip_id)                       	AS equipDesc        ");
        query.append("       ,(SELECT aa.dept_no                                                   			");
        query.append("         FROM TADEPT aa                                          						");
        query.append("         WHERE z.comp_no = aa.comp_no                                             	");
        query.append("          AND z.dept_id = aa.dept_id)                            	AS deptNo	      	");
        query.append("       ,(SELECT aa.description                                                     	");
        query.append("         FROM TADEPT aa                                                				");
        query.append("         WHERE z.comp_no = aa.comp_no                                                 ");
        query.append("          AND z.dept_id = aa.dept_id)                          	AS deptDesc         ");
        query.append("       ,(SELECT aa.wkctr_no                                                   		");
        query.append("         FROM TAWKCTR aa                                         						");
        query.append("         WHERE z.comp_no = aa.comp_no                                             	");
        query.append("          AND z.wkctr_id = aa.wkctr_id)                          	AS wkctrNo     		");
        query.append("       ,(SELECT aa.description                                                    	");
        query.append("         FROM TAWKCTR aa                                               				");
        query.append("         WHERE z.comp_no = aa.comp_no                                                 ");
        query.append("          AND z.wkctr_id = aa.wkctr_id)                       	AS wkCtrDesc        ");
        query.append("       ,dbo.SFACODE_TO_DESC(z.schedule_type,'SCHEDULE_TYPE','SYS','','"+user.getLangId()+"')	AS scheduleTypeDesc	");
        query.append("       ,convert(nvarchar,z.cycle)+z.period_type                  	AS cycle            ");
        query.append("       ,z.USAGE                                                  	AS USAGE            ");
        query.append("       ,z.pm_id                                                  	AS pmId             ");
        query.append("       ,y.PMINSLIST_ID                                           	AS pmInsListId      ");
        query.append("       ,(SELECT param2 FROM TACDSYSD WHERE cdsysd_no=z.pm_type AND list_type= z.wo_type+'_TYPE')	AS param	");
        query.append("       ,z.pm_type                                               	AS pmType           ");
        query.append("       ,(SELECT emp_id FROM TAPMINSLIST WHERE pm_id = y.pm_id AND pminslist_id = y.pminslist_id)	AS empId	");
        query.append("       ,(SELECT emp_no                                                     			");
        query.append("         FROM TAEMP                                                     				");
        query.append("         WHERE emp_id=(SELECT emp_id FROM TAPMINSLIST WHERE pm_id=y.pm_id AND pminslist_id=y.pminslist_id)) 	AS empNo    ");
        query.append("       ,(SELECT emp_name                                                     			");
        query.append("         FROM TAEMP                                                     				");
        query.append("         WHERE emp_id=(SELECT emp_id FROM TAPMINSLIST WHERE pm_id=y.pm_id AND pminslist_id=y.pminslist_id))	AS empDesc	");
        query.append("FROM TAPMINSSCHEDAPPRLIST x INNER JOIN TAPMINSSCHED y ON x.comp_no = y.comp_no AND x.pminssched_id = y.pminssched_id ");
        query.append("   INNER JOIN TAPMLST z ON y.comp_no = z.comp_no AND y.pm_id = z.pm_id                                               ");
        query.append("WHERE 1=1                                                                             ");
        query.append(this.getWhere(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user));
        query.getOrderByQuery("y.pminssched_id", "y.sched_date", workCalPmInsApprCommonDTO.getOrderBy(), workCalPmInsApprCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(workCalPmInsApprCommonDTO.getIsLoadMaxCount(), workCalPmInsApprCommonDTO.getFirstRow()));
    }
    
    private String getWhere(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.pminsschedappr_id", workCalPmInsApprCommonDTO.getPmInsSchedApprId());
    	query.getAndQuery("y.is_deleted", "N");

    	if(workCalPmInsApprDetailDTO.getPminsschedapprType().equals("ACT")){
    		query.append(" AND y.pmsched_status IN ('C')				");    		
    	}else if(workCalPmInsApprDetailDTO.getPminsschedapprType().equals("NOT")){
    		query.append(" AND y.pmsched_status NOT IN ('C')			");    		
    	} 
    	
        return query.toString();
    }
 
    public String findTotalCount(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("FROM TAPMINSSCHEDAPPRLIST x INNER JOIN TAPMINSSCHED y ON x.comp_no = y.comp_no AND x.pminssched_id = y.pminssched_id ");
        query.append("   INNER JOIN TAPMLST z ON y.comp_no = z.comp_no AND y.pm_id = z.pm_id                                               ");
        query.append("WHERE 1=1                                                                                         ");
        query.append(this.getWhere(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}