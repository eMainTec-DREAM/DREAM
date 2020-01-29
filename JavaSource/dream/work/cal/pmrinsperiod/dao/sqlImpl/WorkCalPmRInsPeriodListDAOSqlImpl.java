package dream.work.cal.pmrinsperiod.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.cal.pmrinsperiod.dao.WorkCalPmRInsPeriodListDAO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodDetailDTO;

/**
 * 예방작업일정(기간) - 목록 dao
 * @author  kim21017
 * @version $Id: WorkCalPmRInsPeriodListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workCalPmRInsPeriodListDAOTarget"
 * @spring.txbn id="workCalPmRInsPeriodListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmRInsPeriodListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkCalPmRInsPeriodListDAO
{
	/**
     * grid find
     * @author  kim21017
     * @version $Id: WorkCalPmRInsPeriodListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsPeriodCommonDTO
     * @return List
     */
    public List findSchedList(WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                                                                                                ");
        query.append("        ''                                                                                                    AS seqNo                ");
        query.append("       ,''                                                                                                    AS isDelCheck           ");
        query.append("       ,x.pminssched_id                                                                                       AS pmInsSchedId         ");
        query.append("       ,x.plan_date                                                                                           AS planDate             ");
        query.append("       ,x.sched_date                                                                                          AS schedDate            ");
        query.append("       ,x.actual_date                                                                                         AS completeDate         ");
        query.append("       ,y.pm_no                                                                                               AS pmNo                 ");
        query.append("       ,y.description                                                                                         AS pmDesc               ");
        query.append("       ,x.pmsched_status                                                                                      AS pmSchedStatus        ");
        query.append("       ,dbo.SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','','"+user.getLangId()+"')                AS pmSchedStatusDesc    ");
        query.append("       ,x.measure_time                                                                                        AS measureTime          ");
        query.append("       ,(SELECT b.full_desc                                                                                                           ");
        query.append("          FROM TAEQUIPMENT a, TAEQLOC b                                                                                               ");
        query.append("         WHERE a.comp_no = b.comp_no                                                                                                  ");
        query.append("           AND a.eqloc_id = b.eqloc_id                                                                                                ");
        query.append("           AND a.comp_no = x.comp_no                                                                                                  ");
        query.append("           AND a.equip_id = x.equip_id)                                                                       AS eqLocDesc            ");
        query.append("       ,(SELECT aa.item_no from TAEQUIPMENT aa                                                                                        ");
        query.append("          WHERE x.comp_no = aa.comp_no                                                                                                ");
        query.append("                AND x.equip_id = aa.equip_id)                                                                 AS equipNo              ");
        query.append("       ,(SELECT aa.description from TAEQUIPMENT aa                                                                                    ");
        query.append("          WHERE x.comp_no = aa.comp_no                                                                                                ");
        query.append("                AND x.equip_id = aa.equip_id)                                                                 AS equipDesc            ");
        query.append("       ,(SELECT aa.description FROM TADEPT aa                                                                                         ");
        query.append("         WHERE y.comp_no = aa.comp_no                                                                                                 ");
        query.append("           AND y.dept_id = aa.dept_id)                                                                        AS deptDesc             ");
        query.append("       ,(SELECT aa.description FROM TAWKCTR aa                                                                                        ");
        query.append("         WHERE y.comp_no = aa.comp_no                                                                                                 ");
        query.append("              AND y.wkctr_id = aa.wkctr_id)                                                                   AS wkCtrDesc            ");
        query.append("       ,y.schedule_type                                                                                       AS scheduleType         ");
        query.append("       ,dbo.SFACODE_TO_DESC(y.schedule_type,'SCHEDULE_TYPE','SYS','','"+user.getLangId()+"')                  AS scheduleTypeDesc     ");
        query.append("       ,y.period_type                                                                                         AS periodType           ");
        query.append("       ,dbo.SFACODE_TO_DESC(y.period_type,'PERIOD_TYPE','SYS','','"+user.getLangId()+"')                      AS periodTypeDesc       ");
        query.append("       ,y.cycle                                                                                               AS cycle                ");
        query.append("       ,convert(char,y.cycle)+y.period_type                                                                   AS cycleDesc            ");
        query.append("       ,y.USAGE                                                                                               AS USAGE                ");
        query.append("       ,y.pm_id                                                                                               AS pmId                 ");
        query.append("       ,x.PMINSLIST_ID                                                                                        AS pmInsListId          ");
        query.append("       ,y.pm_type                                                                                             AS pmType               ");
        query.append("       ,dbo.SFACODE_TO_DESC(y.pm_type,y.wo_type+'_TYPE','SYS','','"+user.getLangId()+"')                      AS pmTypeDesc           ");
        query.append("       ,(SELECT param2 FROM TACDSYSD WHERE cdsysd_no=y.pm_type AND list_type= y.wo_type+'_TYPE')              AS param                ");
        query.append("       ,(SELECT emp_id FROM TAPMINSLIST WHERE pm_id = x.pm_id AND pminslist_id = x.pminslist_id)              AS empId                ");
        query.append("       ,(SELECT emp_name FROM TAEMP WHERE emp_id = (SELECT emp_id FROM TAPMINSLIST                                                    ");
        query.append("                                                    WHERE pm_id = x.pm_id AND pminslist_id = x.pminslist_id)) AS empDesc              ");
        query.append("       ,x.remark                                                                                              AS remark               ");
        query.append("FROM TAPMINSSCHED x inner join TAPMLST y on x.comp_no = y.comp_no and x.pm_id = y.pm_id                                               ");
        query.append("INNER JOIN TAPMEQUIP z                                                                                                                ");
        query.append("ON z.pmequip_id = x.pmequip_id AND z.comp_no = x.comp_no                                                                              ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                                                              ");
        query.append("ON z.equip_id = c.equip_id AND z.comp_no = c.comp_no                                                                                  ");
        query.append("WHERE 1=1                                                                                                                             ");
        query.append(this.getWhere(workCalPmRInsPeriodCommonDTO, user));
        query.getOrderByQuery("x.pminssched_id", "x.plan_date, y.description", workCalPmRInsPeriodCommonDTO.getOrderBy(), workCalPmRInsPeriodCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workCalPmRInsPeriodCommonDTO.getIsLoadMaxCount(), workCalPmRInsPeriodCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: WorkCalPmRInsPeriodListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param day
     * @param status
     * @param compNo
     * @param deptId
     * @param deptDesc
     * @return
     * @throws Exception
     */
    private String getWhere(WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String usageDept       = workCalPmRInsPeriodCommonDTO.getFilterUsageDeptId();
        String usageDeptDesc   = workCalPmRInsPeriodCommonDTO.getFilterUsageDeptDesc();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        if(!"".equals(workCalPmRInsPeriodCommonDTO.getPmInsSchedId()) ||!"".equals(workCalPmRInsPeriodCommonDTO.getPmInsListId())){
            query.getAndNumKeyQuery("x.pminssched_id", workCalPmRInsPeriodCommonDTO.getPmInsSchedId());
            query.getAndNumKeyQuery("x.pminslist_id", workCalPmRInsPeriodCommonDTO.getPmInsListId());
            return query.toString();
        }
        
        query.getAndNumKeyQuery("x.pmequip_id", workCalPmRInsPeriodCommonDTO.getPmEquipId());
        
        query.getSysCdQuery("x.pmsched_status", workCalPmRInsPeriodCommonDTO.getPmSchedStatus(), workCalPmRInsPeriodCommonDTO.getPmSchedStatusDesc(), "PMSCHED_STATUS", user.getCompNo(), user.getLangId());
        
        //사용부서
        query.getDeptLevelQuery("c.usage_dept", usageDept, usageDeptDesc, user.getCompNo());
        
        //날짜 전체 검색(리스트조회 사용)
        query.getAndDateQuery("x.sched_date", workCalPmRInsPeriodCommonDTO.getFilterStartDate(), workCalPmRInsPeriodCommonDTO.getFilterEndDate());
    	query.getAndQuery("y.pm_no", workCalPmRInsPeriodCommonDTO.getFilterPmNo());
    	query.getDeptLevelQuery("y.dept_id", workCalPmRInsPeriodCommonDTO.getFilterDeptId(), workCalPmRInsPeriodCommonDTO.getFilterDeptDesc(), user.getCompNo());
        query.getWkCtrLevelQuery("y.wkctr_id", workCalPmRInsPeriodCommonDTO.getFilterWkCtrId(), workCalPmRInsPeriodCommonDTO.getFilterWkCtrDesc(), user.getCompNo());
        
    	
    	//법정설비여부
        if(!"".equals(workCalPmRInsPeriodCommonDTO.getFilterIsLawEq())){
        	query.append("AND x.equip_id IN (SELECT a.equip_id						");
        	query.append("					 FROM  TAEQUIPMENT a            		");
        	query.append("					 WHERE 1=1                    			");
        	query.getStringEqualQuery("a.is_deleted", "N");
        	query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getLikeQuery("a.is_law_eq", workCalPmRInsPeriodCommonDTO.getFilterIsLawEq());
            query.append("									)					");
        }
        //설비
    	if(!"".equals(workCalPmRInsPeriodCommonDTO.getFilterEquipId())||!"".equals(workCalPmRInsPeriodCommonDTO.getFilterEquipDesc())){
    		query.append("AND x.equip_id IN (SELECT a.equip_id						");
        	query.append("					 FROM  TAEQUIPMENT a            		");
        	query.append("					 WHERE 1=1                    			");
        	query.getStringEqualQuery("a.is_deleted", "N");
        	query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getCodeLikeQuery("a.equip_id", "a.description+a.item_no",workCalPmRInsPeriodCommonDTO.getFilterEquipId(), workCalPmRInsPeriodCommonDTO.getFilterEquipDesc());
            query.append("									)					");
        }
    	//위치
    	if(!"".equals(workCalPmRInsPeriodCommonDTO.getFilterEqLocId())||!"".equals(workCalPmRInsPeriodCommonDTO.getFilterEqLocDesc())){
    		query.append("AND x.equip_id IN (SELECT a.equip_id						");
        	query.append("					 FROM  TAEQUIPMENT a            		");
        	query.append("					 WHERE 1=1                    			");
        	query.getStringEqualQuery("a.is_deleted", "N");
        	query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getEqLocLevelQuery("a.eqloc_id", workCalPmRInsPeriodCommonDTO.getFilterEqLocId(), workCalPmRInsPeriodCommonDTO.getFilterEqLocDesc(), user.getCompNo());
            query.append("									)					");
        }
    	//종류
    	if(!"".equals(workCalPmRInsPeriodCommonDTO.getFilterEqCtgId())||!"".equals(workCalPmRInsPeriodCommonDTO.getFilterEqCtgDesc())){
    		query.append("AND x.equip_id IN (SELECT a.equip_id						");
        	query.append("					 FROM  TAEQUIPMENT a            		");
        	query.append("					 WHERE 1=1                    			");
        	query.getStringEqualQuery("a.is_deleted", "N");
        	query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getEqCtgLevelQuery("a.eqctg_id", workCalPmRInsPeriodCommonDTO.getFilterEqCtgId(), workCalPmRInsPeriodCommonDTO.getFilterEqCtgDesc(), user.getCompNo());
            query.append("									)					");
        }
    	//담당자(정)
    	if(!"".equals(workCalPmRInsPeriodCommonDTO.getFilterMainMngId())||!"".equals(workCalPmRInsPeriodCommonDTO.getFilterMainMngName())){
    		query.append("AND x.equip_id IN (SELECT a.equip_id						");
        	query.append("					 FROM  TAEQUIPMENT a            		");
        	query.append("					 WHERE 1=1                    			");
        	query.getStringEqualQuery("a.is_deleted", "N");
        	query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getCodeLikeQuery("a.main_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = a.main_mng_id AND aa.comp_no='"+user.getCompNo()+"')",
        			workCalPmRInsPeriodCommonDTO.getFilterMainMngId(), workCalPmRInsPeriodCommonDTO.getFilterMainMngName());
            query.append("									)					");
        }
    	//담당자(부)
    	if(!"".equals(workCalPmRInsPeriodCommonDTO.getFilterSubMngId())||!"".equals(workCalPmRInsPeriodCommonDTO.getFilterSubMngName())){
    		query.append("AND x.equip_id IN (SELECT a.equip_id						");
        	query.append("					 FROM  TAEQUIPMENT a            		");
        	query.append("					 WHERE 1=1                    			");
        	query.getStringEqualQuery("a.is_deleted", "N");
        	query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getCodeLikeQuery("a.sub_mng_id", "(SELECT aa.emp_name FROM TAEMP aa WHERE aa.emp_id = a.sub_mng_id AND aa.comp_no='"+user.getCompNo()+"')",
        			workCalPmRInsPeriodCommonDTO.getFilterSubMngId(), workCalPmRInsPeriodCommonDTO.getFilterSubMngName());
            query.append("									)					");
        }
    	//설비유형
    	if(!"".equals(workCalPmRInsPeriodCommonDTO.getFilterEqCtgTypeId())||!"".equals(workCalPmRInsPeriodCommonDTO.getFilterEqCtgTypeDesc())){
    		query.append("AND x.equip_id IN (SELECT a.equip_id						");
        	query.append("					 FROM  TAEQUIPMENT a            		");
        	query.append("					 WHERE 1=1                    			");
        	query.getStringEqualQuery("a.is_deleted", "N");
        	query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getSysCdQuery("a.eqctg_type", workCalPmRInsPeriodCommonDTO.getFilterEqCtgTypeId(), workCalPmRInsPeriodCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
            query.append("									)					");
        }
        
        //담당자
        query.getCodeLikeQuery("(SELECT emp_id FROM TAPMINSLIST "
                              + "WHERE pm_id = x.pm_id AND pminslist_id = x.pminslist_id)"
                , "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = (SELECT emp_id FROM TAPMINSLIST WHERE pm_id = x.pm_id AND pminslist_id = x.pminslist_id)"
                + "  AND a.comp_no='"+user.getCompNo()+"')", workCalPmRInsPeriodCommonDTO.getFilterEmpId(), workCalPmRInsPeriodCommonDTO.getFilterEmpDesc());

        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = y.plant )", 
        		workCalPmRInsPeriodCommonDTO.getFilterPlantId(), workCalPmRInsPeriodCommonDTO.getFilterPlantDesc());
        
        //최신설비
        query.append("AND NOT EXISTS (SELECT a.equip_id							");
    	query.append("					 FROM  TAEQUIPMENT a            		");
    	query.append("					 WHERE 1=1                    			");
    	query.append("					 AND   a.equip_id = x.equip_id			");
    	query.getStringEqualQuery("a.comp_no", user.getCompNo());
    	query.getAndQuery("a.is_last_version", "N");
        query.append("									)						");
        
        return query.toString();
    }



    /**
     * delete
     * @author kim21017
     * @version $Id: WorkCalPmRInsPeriodListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public boolean canDeleteWorkOrder(String id, String compNo)
    {

    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String pmSchedId = id;

    	query.append("select 1                                                ");
    	query.append("from taworkorder                                        ");
    	query.append("where comp_no = ?                                       ");
    	query.append("      and wo_status = 'P'                               ");
    	query.append("      and wkor_id = (select wkor_id                     ");
    	query.append("                     from TAPMSCHED                     ");
    	query.append("                     where comp_no = ?                  ");
    	query.append("                          and pmsched_id = ?            ");
    	query.append("                     )                                  ");


        Object[] objects = new Object[] {
        		compNo
        		,compNo
        		,pmSchedId
        };

        List resultList = this.getJdbcTemplate().queryForList(query.toString(), objects);
        return QuerySqlBuffer.haveData(resultList);

    }

	@Override
	public String findTotalCount(WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO, User user)
			throws Exception {

        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append(" SELECT                                                            ");
        query.append("        COUNT(1)                                                   ");
        query.append("   FROM TAPMINSSCHED x                                             ");
        query.append("  INNER JOIN TAPMLST y                                             ");
        query.append("          ON x.comp_no = y.comp_no and x.pm_id = y.pm_id           ");
        query.append("  INNER JOIN TAPMEQUIP z                                           ");
        query.append("          ON z.pmequip_id = x.pmequip_id AND z.comp_no = x.comp_no ");
        query.append("  INNER JOIN TAEQUIPMENT c                                         ");
        query.append("          ON z.equip_id = c.equip_id AND z.comp_no = c.comp_no     ");
        query.append("  WHERE 1=1                                                        ");
        query.append(this.getWhere(workCalPmRInsPeriodCommonDTO, user));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}

    @Override
    public int[] updateDeleteTag(final List<WorkCalPmRInsPeriodDetailDTO> list, final User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        final String deleteTime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");
        
        query.append("UPDATE TAPMINSSCHED  SET     ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?     ");
        query.append("WHERE COMP_NO = ?              ");
        query.append("  AND PMINSSCHED_ID =  ?      ");
        query.append("  AND PMSCHED_STATUS != 'C'      ");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {

            @Override
            public int getBatchSize()
            {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO = list.get(i);

                Object[] objects = getObject(new Object[] {
                        user.getEmpId(),
                        deleteTime,
                        user.getCompNo(),
                        workCalPmRInsPeriodDetailDTO.getPmInsSchedId()
                });
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
}