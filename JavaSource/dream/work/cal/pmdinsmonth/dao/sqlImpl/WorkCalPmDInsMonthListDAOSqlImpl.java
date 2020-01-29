package dream.work.cal.pmdinsmonth.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.cal.pmdinsmonth.dao.WorkCalPmDInsMonthListDAO;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthCommonDTO;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthDetailDTO;

/**
 * 월간예방일정 - 목록 dao
 * @author  kim21017
 * @version $Id: WorkCalPmDInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workCalPmDInsMonthListDAOTarget"
 * @spring.txbn id="workCalPmDInsMonthListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmDInsMonthListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkCalPmDInsMonthListDAO
{
	/**
     * grid find
     * @author  kim21017
     * @version $Id: WorkCalPmDInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmDInsMonthCommonDTO
     * @return List
     */
    public List findSchedList(WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                                                    ");
        query.append("        ''                                                                                                        as SEQNO                ");
        query.append("       ,''                                                                                                        as ISDELCHECK           ");
        query.append("       ,x.pminsdsched_id                                                                                          as PMINSDSCHEDID        ");
        query.append("       ,x.plan_date                                                                                               as PLANDATE             ");
        query.append("       ,x.sched_date                                                                                              as SCHEDDATE            ");
        query.append("       ,x.actual_date                                                                                             as COMPLETEDATE         ");
        query.append("       ,y.pm_no                                                                                                   as PMNO                 ");
        query.append("       ,y.description                                                                                             as PMDESC               ");
        query.append("       ,x.pmsched_status                                                                                          as PMSCHEDSTATUS        ");
        query.append("       ,dbo.SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','','"+user.getLangId()+"')                    as PMSCHEDSTATUSDESC    ");
        query.append("       ,(SELECT full_desc                                                                                                                 ");
        query.append("          FROM TAEQLOC                                                                                                                    ");
        query.append("         WHERE comp_no = z.comp_no                                                                                                        ");
        query.append("           AND eqloc_id = z.eqloc_id)                                                                             as EQLOCDESC            ");
        query.append("       ,z.item_no                                                                                                 as EQUIPNO              ");
        query.append("       ,z.description                                                                                             as EQUIPDESC            ");
        query.append("       ,x.work_number                                                                                             as WORKNUMBER           ");
        query.append("       ,(SELECT aa.description FROM TADEPT aa                                                                                             ");
        query.append("         WHERE y.comp_no = aa.comp_no                                                                                                     ");
        query.append("           AND y.dept_id = aa.dept_id)                                                                            as DEPTDESC             ");
        query.append("       ,(SELECT aa.description FROM TAWKCTR aa                                                                                            ");
        query.append("         WHERE y.comp_no = aa.comp_no                                                                                                     ");
        query.append("              AND y.wkctr_id = aa.wkctr_id)                                                                       as WKCTRDESC            ");
        query.append("       ,y.pm_id                                                                                                   as PMID                 ");
        query.append("       ,x.PMINSDLIST_ID                                                                                           as PMINSDLISTID         ");
        query.append("       ,(SELECT param2 FROM TACDSYSD WHERE cdsysd_no=y.pm_type AND list_type= y.wo_type+'_TYPE')                  as PARAM                ");
        query.append("       ,y.pm_type                                                                                                 as PMTYPE               ");
        query.append("       ,dbo.SFACODE_TO_DESC(y.pm_type,y.wo_type+'_TYPE','SYS','','"+user.getLangId()+"')                          as PMTYPEDESC           ");
        query.append("       ,y.schedule_type                                                                                           as SCHEDULETYPE         ");
        query.append("       ,dbo.SFACODE_TO_DESC(y.schedule_type,'SCHEDULE_TYPE','SYS','','"+user.getLangId()+"')                      as SCHEDULETYPEDESC     ");
        query.append("       ,(SELECT emp_id FROM TAPMINSDLIST WHERE pm_id = x.pm_id AND pminsdlist_id = x.pminsdlist_id)               as EMPID                ");
        query.append("       ,(SELECT emp_name FROM TAEMP WHERE emp_id = (SELECT emp_id FROM TAPMINSdLIST                                                       ");
        query.append("                                                    WHERE pm_id = x.pm_id AND pminsdlist_id = x.pminsdlist_id))   as EMPDESC              ");
        query.append("       ,y.cycle                                                                                                   as CYCLE                ");
        query.append("       ,STR(y.cycle)+y.period_type                                                                                as CYCLEDESC            ");
        query.append("       ,y.period_type                                                                                             as PERIODTYPE           ");
        query.append("       ,dbo.SFACODE_TO_DESC(y.period_type,'PERIOD_TYPE','SYS','','"+user.getLangId()+"')                          as PERIODTYPEDESC       ");
        query.append("       ,y.remark                                                                                                  as REMARK               ");
        query.append("       ,y.usage                                                                                                   as USAGE                ");
        query.append("FROM TAPMINSDSCHED x inner join TAPMLST y on x.comp_no = y.comp_no and x.pm_id = y.pm_id                                                  ");
        query.append("                     LEFT OUTER JOIN TAEQUIPMENT z on x.comp_no = z.comp_no and x.equip_id = z.equip_id AND z.is_deleted='N'              ");
        query.append("WHERE 1=1                                                                                                                                 ");
        query.append(this.getWhere(workCalPmDInsMonthCommonDTO,user));
        query.getOrderByQuery("x.pminsdsched_id", "x.sched_date, y.description", workCalPmDInsMonthCommonDTO.getOrderBy(), workCalPmDInsMonthCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(workCalPmDInsMonthCommonDTO.getIsLoadMaxCount(), workCalPmDInsMonthCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: WorkCalPmDInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
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
    private String getWhere(WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO ,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getStringEqualQuery("x.IS_DELETED", "N");
        query.getStringEqualQuery("y.wo_type", "PMI");
        query.getStringEqualQuery("y.pm_type", "DINS");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        if(!"".equals(workCalPmDInsMonthCommonDTO.getPmInsDListId()) || !"".equals(workCalPmDInsMonthCommonDTO.getPmInsDSchedId())){
            query.getAndNumKeyQuery("x.pminsdlist_id", workCalPmDInsMonthCommonDTO.getPmInsDListId());
            query.getAndNumKeyQuery("x.pminsdsched_id", workCalPmDInsMonthCommonDTO.getPmInsDSchedId());
            return query.toString();
        }
        
        query.getAndNumKeyQuery("x.pmequip_id", workCalPmDInsMonthCommonDTO.getPmEquipId());
        
        query.getSysCdQuery("x.pmsched_status", workCalPmDInsMonthCommonDTO.getPmSchedStatus(), workCalPmDInsMonthCommonDTO.getPmSchedStatusDesc(), "PMSCHED_STATUS", user.getCompNo(), user.getLangId());
        
        if(!"".equals(workCalPmDInsMonthCommonDTO.getYyyymmdd())){
            query.append("  AND x.sched_date  like        '"+CommonUtil.getRowDateToNum(workCalPmDInsMonthCommonDTO.getYyyymmdd())+"%'				");
        }
    	query.getAndQuery("y.pm_no", workCalPmDInsMonthCommonDTO.getPmNo());
    	query.getDeptLevelQuery("y.dept_id", workCalPmDInsMonthCommonDTO.getDeptId(), workCalPmDInsMonthCommonDTO.getDeptDesc(), user.getCompNo());
        query.getWkCtrLevelQuery("y.wkctr_id", workCalPmDInsMonthCommonDTO.getWkCtrId(), workCalPmDInsMonthCommonDTO.getWkCtrDesc(), user.getCompNo());
        
        //법정설비여부
        query.getLikeQuery("z.is_law_eq", workCalPmDInsMonthCommonDTO.getIsLawEq());
        
        //설비
        query.getCodeLikeQuery("z.equip_id", "z.description+z.item_no",workCalPmDInsMonthCommonDTO.getEquipId(), workCalPmDInsMonthCommonDTO.getEquipDesc());
        
        //위치
        query.getEqLocLevelQuery("z.eqloc_id", workCalPmDInsMonthCommonDTO.getEqLocId(), workCalPmDInsMonthCommonDTO.getEqLocDesc(), user.getCompNo());
        
        //종류
        query.getEqCtgLevelQuery("z.eqctg_id", workCalPmDInsMonthCommonDTO.getEqCtgId(), workCalPmDInsMonthCommonDTO.getEqCtgDesc(), user.getCompNo());
        
        //담당자(정)
        query.getCodeLikeQuery("z.main_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = z.main_mng_id AND comp_no=z.comp_no)", workCalPmDInsMonthCommonDTO.getMainMngId(), workCalPmDInsMonthCommonDTO.getMainMngName());
        
        //담당자(부)
        query.getCodeLikeQuery("z.sub_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = z.sub_mng_id AND comp_no=z.comp_no)", workCalPmDInsMonthCommonDTO.getSubMngId(), workCalPmDInsMonthCommonDTO.getSubMngName());
        
        //내/외자 구분
        query.getSysCdQuery("z.plf_type", workCalPmDInsMonthCommonDTO.getPlfTypeId(), workCalPmDInsMonthCommonDTO.getPlfTypeDesc(), "PLF_TYPE", user.getCompNo(),user.getLangId());
        
        //설비유형
        query.getSysCdQuery("z.eqctg_type", workCalPmDInsMonthCommonDTO.getEqCtgTypeId(), workCalPmDInsMonthCommonDTO.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
        
    	//담당자
        query.getCodeLikeQuery("(SELECT emp_id FROM TAPMINSDLIST "
                              + "WHERE pm_id = x.pm_id AND pminsdlist_id = x.pminsdlist_id)"
                , "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = (SELECT emp_id FROM TAPMINSDLIST WHERE pm_id = x.pm_id AND pminsdlist_id = x.pminsdlist_id)"
                + "  AND a.comp_no='"+user.getCompNo()+"')", workCalPmDInsMonthCommonDTO.getEmpId(), workCalPmDInsMonthCommonDTO.getEmpDesc());

        //최신설비
        query.getAndQuery("z.is_last_version", "Y");
        
        return query.toString();
    }
    
	@Override
	public void SP_PM_MAKE_TO_PMI(String pmInsdSchedId, User user) throws Exception
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("EXEC                                                           ");
        query.append(" dbo.SP_PM_MAKE_TO_PMIDAY '"+user.getCompNo()+"',"+pmInsdSchedId+"; ");

        this.getJdbcTemplate().execute(query.toString());
		
	}

    @Override
    public int[] updateDeleteTag(final List<WorkCalPmDInsMonthDetailDTO> list, final User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        final String deleteTime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");
        
        query.append("UPDATE TAPMINSDSCHED  SET     ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?     ");
        query.append("WHERE COMP_NO = ?              ");
        query.append("  AND PMINSDSCHED_ID =  ?      ");
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
                WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO = list.get(i);

                Object[] objects = getObject(new Object[] {
                        user.getEmpId(),
                        deleteTime,
                        user.getCompNo(),
                        workCalPmDInsMonthDetailDTO.getPmInsDSchedId()
                });
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
    
    @Override
    public String findTotalCount(WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO ,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            		");
        query.append("       COUNT(1)                                   		");
        query.append("	FROM TAPMINSDSCHED x INNER JOIN TAPMLST y				");
        query.append("							ON x.comp_no = y.comp_no		");
        query.append("						   AND x.pm_id 	 = y.pm_id        	");
        query.append("                     	  LEFT OUTER JOIN TAEQUIPMENT z		");
        query.append("							ON x.comp_no    = z.comp_no		");
        query.append("						   AND x.equip_id   = z.equip_id	");
        query.append("						   AND z.is_deleted = 'N'         	");
        query.append(" WHERE 1 = 1                                          	");
        query.append(this.getWhere(workCalPmDInsMonthCommonDTO,user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
}