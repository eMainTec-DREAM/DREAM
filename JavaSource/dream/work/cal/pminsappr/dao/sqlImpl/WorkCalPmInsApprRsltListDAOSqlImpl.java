package dream.work.cal.pminsappr.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.cal.pminsappr.dao.WorkCalPmInsApprRsltListDAO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;

/**
 * 예방점검계획승인-점검결과 - 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workCalPmInsApprRsltListDAOTarget"
 * @spring.txbn id="workCalPmInsApprRsltListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmInsApprRsltListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkCalPmInsApprRsltListDAO
{
    public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 											                                                          				                                                                    ");
        query.append("     '' 									 		                                                          AS seqNo		                                                                    ");
        query.append("   , (SELECT description  						                                                          				                                                                    ");
        query.append("   	FROM TAEQUIPMENT   							                                                          				                                                                    ");
        query.append("   	WHERE comp_no = c.comp_no 					                                                          				                                                                    ");
        query.append("   	 AND equip_id = c.equip_id)  				                                                          AS equipDesc	                                                                    ");
        query.append("   , (SELECT item_no 								                                                          				                                                                    ");
        query.append("   	FROM TAEQUIPMENT   							                                                          				                                                                    ");
        query.append("   	WHERE comp_no = c.comp_no 					                                                          				                                                                    ");
        query.append("   	 AND equip_id = c.equip_id)  				                                                          AS equipNo	                                                                    ");
        query.append("   , (SELECT full_desc  							                                                          				                                                                    ");
        query.append("   	FROM TAEQLOC   								                                                          				                                                                    ");
        query.append("   	WHERE comp_no = c.comp_no 					                                                          				                                                                    ");
        query.append("   	 AND eqloc_id = (SELECT eqloc_id  			                                                          				                                                                    ");
        query.append("   					   FROM TAEQUIPMENT    		                                                          				                                                                    ");
        query.append("   					   WHERE comp_no = c.comp_no  			                                           	                                                                                    ");
        query.append("   					    AND equip_id = c.equip_id)	  		                                           	                                                                                    ");
        query.append("      )											                                                          AS eqLocDesc		                                                                ");
        query.append("   , dbo.SFACODE_TO_DESC(b.pmsched_status  , 'PMSCHED_STATUS', 'SYS', b.comp_no, '"+user.getLangId()+"')	  AS pmSchedStatus	                                                                ");
        query.append("   , CASE WHEN e.start_time IS NOT NULL 								                                                                                                                        ");
        query.append("          THEN SUBSTRING(e.start_date,1,4) + '-' + SUBSTRING(e.start_date,5,2) + '-' + SUBSTRING(e.start_date,7,2) +' '+ SUBSTRING(e.start_time,1,2) +':' + SUBSTRING(e.start_time,3,2)		");
        query.append("          ELSE (CASE WHEN e.start_date IS NOT NULL THEN SUBSTRING(e.start_date,1,4) + '-' + SUBSTRING(e.start_date,5,2) + '-' + SUBSTRING(e.start_date,7,2) ELSE '' END) 		                ");
        query.append("          END                    					                                                          AS inspectFromDate                                                                ");
        query.append("   , CASE WHEN e.end_time IS NOT NULL 			                                                            					                                                            ");
        query.append("          THEN SUBSTRING(e.end_date,1,4) + '-' + SUBSTRING(e.end_date,5,2) + '-' + SUBSTRING(e.end_date,7,2) +' '+ SUBSTRING(e.end_time,1,2) +':' + SUBSTRING(e.end_time,3,2)		            ");
        query.append("          ELSE (CASE WHEN e.end_date IS NOT NULL THEN SUBSTRING(e.end_date,1,4) + '-' + SUBSTRING(e.end_date,5,2) + '-' + SUBSTRING(e.end_date,7,2) ELSE '' END) 		                        ");
        query.append("          END                    					                                                          AS inspectToDate                                                                	");
        query.append("   , e.work_time       				  			                                                          AS workTime  		                                                                ");
        query.append("   , d.step_num                                                                           	              AS stepNum		                                                              	");
        query.append("   , CASE WHEN d.eqasmb_id IS NULL 	                                                                                                                                                        ");
        query.append("              THEN d.eqasmb_desc                                                                      		                                                                                ");
        query.append("              ELSE (SELECT full_desc FROM TAEQASMB WHERE comp_no = d.comp_no AND eqasmb_id = d.eqasmb_id)	                                                                                    ");
        query.append("              END       				  			                                                          AS eqAsmbDesc		                                                                ");
        query.append("   , d.check_point 	                                                                                      AS 'CHECKPOINT'  	                                                                ");
        query.append("   , d.fit_basis                                                                                     	      AS fitBasis		                                                                ");
        query.append("   , dbo.SFACODE_TO_DESC(d.check_type,'CHECK_TYPE','SYS','','"+user.getLangId()+"')	                      AS checkTypeDesc	                                                                ");
        query.append("   , dbo.SFACODE_TO_DESC(f.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS','','"+user.getLangId()+"')     AS pmPointRsltStatusDesc	                                                        ");
        query.append("   , f.result_value            					                                                          AS resultValue		                                                            ");
        query.append("   , f.REMARK          							                                                          AS remark			                                                                ");
        query.append("FROM TAPMINSSCHEDAPPRLIST a INNER JOIN TAPMINSSCHED b                                                                                                                                         ");
        query.append("ON a.comp_no = b.comp_no AND a.pminssched_id = b.pminssched_id                                                                                                                                ");
        query.append("INNER JOIN TAPMEQUIP c                                                                                                                                                                        ");
        query.append("ON b.comp_no = c.comp_no AND b.pmequip_id = c.pmequip_id                                                                                                                                      ");
        query.append("INNER JOIN TAPMPOINT d                                                                                                                                                                        ");
        query.append("ON c.comp_no = d.comp_no AND c.pm_id = d.pm_id                                                                                                                                                ");
        query.append("LEFT OUTER JOIN TAPMINSLIST e                                                                                                                                                                 ");
        query.append("ON b.comp_no = e.comp_no AND b.pminssched_id = e.pminssched_id                                                                                                                                ");
        query.append("LEFT OUTER JOIN TAPMINSPOINT f                                                                                                                                                                ");
        query.append("ON d.comp_no = f.comp_no AND d.pm_point_id = f.pm_point_id AND e.pminslist_id = f.pminslist_id AND b.pminssched_id = f.pminssched_id AND c.pmequip_id = f.pmequip_id AND c.pm_id = f.pm_id    ");
        query.append("WHERE 1=1                                                                                                                                                                                     ");
        query.append(this.getWhere(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user));
        query.getOrderByQuery("a.pminsschedapprlist_id", "f.actual_date , (SELECT full_desc FROM TAEQLOC WHERE comp_no = c.comp_no AND eqloc_id = (SELECT eqloc_id FROM TAEQUIPMENT WHERE comp_no = c.comp_no AND equip_id = c.equip_id)), c.equip_id,d.step_num"
        		, workCalPmInsApprCommonDTO.getOrderBy(), workCalPmInsApprCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workCalPmInsApprCommonDTO.getIsLoadMaxCount(), workCalPmInsApprCommonDTO.getFirstRow()));
    }

    private String getWhere(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getStringEqualQuery("a.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("a.pminsschedappr_id", workCalPmInsApprCommonDTO.getPmInsSchedApprId());
    	query.getAndQuery("b.is_deleted", "N");

    	if(workCalPmInsApprDetailDTO.getPminsschedapprType().equals("ACT")){
    		query.append(" AND b.pmsched_status IN ('C')				");    		
    	}else if(workCalPmInsApprDetailDTO.getPminsschedapprType().equals("NOT")){
    		query.append(" AND b.pmsched_status NOT IN ('C')			");    		
    	} 
    	
        return query.toString();
    }

    public String findTotalCount(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT                							");
        query.append("       COUNT(1)       							");
        query.append("FROM TAPMINSSCHEDAPPRLIST a INNER JOIN TAPMINSSCHED b                                                                                                                                         ");
        query.append("ON a.comp_no = b.comp_no AND a.pminssched_id = b.pminssched_id                                                                                                                                ");
        query.append("INNER JOIN TAPMEQUIP c                                                                                                                                                                        ");
        query.append("ON b.comp_no = c.comp_no AND b.pmequip_id = c.pmequip_id                                                                                                                                      ");
        query.append("INNER JOIN TAPMPOINT d                                                                                                                                                                        ");
        query.append("ON c.comp_no = d.comp_no AND c.pm_id = d.pm_id                                                                                                                                                ");
        query.append("LEFT OUTER JOIN TAPMINSLIST e                                                                                                                                                                 ");
        query.append("ON b.comp_no = e.comp_no AND b.pminssched_id = e.pminssched_id                                                                                                                                ");
        query.append("LEFT OUTER JOIN TAPMINSPOINT f                                                                                                                                                                ");
        query.append("ON d.comp_no = f.comp_no AND d.pm_point_id = f.pm_point_id AND e.pminslist_id = f.pminslist_id AND b.pminssched_id = f.pminssched_id AND c.pmequip_id = f.pmequip_id AND c.pm_id = f.pm_id    ");
        query.append("WHERE 1=1                                                                                                                                                                                     ");
        query.append(this.getWhere(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}