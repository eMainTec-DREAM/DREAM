package dream.work.pmi.list.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.pmi.list.dao.WorkPmiListDAO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiDetailDTO;

/**
 * 점검작업 - 목록 dao
 * @author  kim21017
 * @version $Id: WorkPmiListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workPmiListDAOTarget"
 * @spring.txbn id="workPmiListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmiListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmiListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkPmiListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiCommonDTO
     * @return List
     */
    public List findList(WorkPmiCommonDTO workPmiCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                            ");
        query.append("      ''                                                                                  AS SEQNO                ");
        query.append("      ,''                                                                                 AS ISDELCHECK           ");
        query.append("      ,x.description                                                                      AS PMIDESC              ");
        query.append("      ,x.wkor_date                                                                        AS WKORDATE             ");
        query.append("      ,x.measure_time                                                                     AS MEASURETIME          ");
        query.append("      ,x.equip_id                                                                         AS EQUIPID              ");
        query.append("      ,y.item_no                                                                          AS ITEMNO               ");
        query.append("      ,y.description                                                                      AS EQUIPDESC            ");
        query.append("      ,y.eqloc_id                                                                         AS EQLOCID              ");
        query.append("        ,(SELECT full_desc                                                                                        ");
        query.append("          FROM TAEQLOC                                                                                            ");
        query.append("         WHERE comp_no = y.comp_no                                                                                ");
        query.append("           AND eqloc_id = y.eqloc_id)                                                     AS EQLOCDESC            ");
        query.append("      ,x.shift_type                                                                       AS SHIFTTYPEID          ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+user.getLangId()+"')     AS SHIFTTYPEDESC        ");
        query.append("      ,x.dept_id                                                                          AS DEPTID               ");
        query.append("      ,(SELECT description                                                                                        ");
        query.append("          FROM TADEPT                                                                                             ");
        query.append("         WHERE comp_no = x.comp_no                                                                                ");
        query.append("           AND dept_id = x.dept_id)                                                       AS DEPTDESC             ");
        query.append("       ,x.wkctr_id                                                                        AS WKCTRID              ");
        query.append("       ,(SELECT description                                                                                       ");
        query.append("           FROM TAWKCTR                                                                                           ");
        query.append("          WHERE comp_no = x.comp_no                                                                               ");
        query.append("            AND wkctr_id = x.wkctr_id)                                                    AS WKCTRDESC            ");
        query.append("      ,x.wo_type                                                                          AS WOTYPEID             ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+user.getLangId()+"')           AS WOTYPEDESC           ");
        query.append("      ,x.pm_type                                                                          AS PMTYPEID             ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.pm_type,x.wo_type+'_TYPE','SYS','','"+user.getLangId()+"')   AS PMTYPEDESC           ");
        query.append("      ,x.pmsched_status                                                                   AS PMSCHEDSTATUSID      ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','','"+user.getLangId()+"') AS PMSCHEDSTATUSDESC    ");
        query.append("      ,x.start_date                                                                       AS STARTDATE            ");
        query.append("      ,x.start_time                                                                       AS STARTTIME            ");
        query.append("      ,x.end_date                                                                         AS ENDDATE              ");
        query.append("      ,x.end_time                                                                         AS ENDTIME              ");
        query.append("      ,x.work_time                                                                        AS WORKTIME             ");
        query.append("      ,x.emp_id                                                                           AS EMPID                ");
        query.append("      ,(SELECT emp_name                                                                                           ");
        query.append("         FROM TAEMP                                                                                               ");
        query.append("        WHERE comp_no = x.comp_no                                                                                 ");
        query.append("          AND emp_id = x.emp_id)                                                          AS EMPDESC              ");
        query.append("      ,x.remark                                                                           AS REMARK               ");
        query.append("      ,x.pminslist_id                                                                     AS PMINSLISTID          ");
        query.append("      ,x.pminssched_id                                                                    AS PMINSSCHEDID         ");
        query.append("      ,x.pm_id                                                                            AS PMID                 ");
        query.append("      ,( SELECT param2                                                                                            ");
        query.append("         FROM TACDSYSD                                                                                            ");
        query.append("         WHERE cdsysd_no=x.pm_type                                                                                ");
        query.append("          AND list_type= x.wo_type+'_TYPE' )                                              AS PARAM                ");
        query.append("      , x.upd_date 							                                            AS UPDDATE              ");
        query.append("      , x.upd_by 							                                         	   	AS UPDBYID              ");
        query.append("      , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.upd_by) 	AS UPDBY            ");
        query.append("      , x.cre_date 							                                            AS CREDATE              ");
        query.append("      , x.cre_by 							                                        	    AS CREBYID              ");
        query.append("      , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.cre_by) AS CREBY             ");
        query.append("FROM  TAPMINSLIST x LEFT OUTER JOIN TAEQUIPMENT y                                                                 ");
        query.append("ON x.comp_no = y.comp_no AND x.equip_id = y.equip_id                                                              ");
        query.append("WHERE 1=1                                                                                                         ");
        query.append(this.getWhere(workPmiCommonDTO,user));
        query.getOrderByQuery("x.pminslist_id", "4 DESC", workPmiCommonDTO.getOrderBy(), workPmiCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPmiCommonDTO.getIsLoadMaxCount(), workPmiCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: WorkPmiListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkPmiCommonDTO workPmiCommonDTO, User user)
    {        
    	String lang = user.getLangId();
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        String startDate = workPmiCommonDTO.getFilterStartDate();
        String endDate = workPmiCommonDTO.getFilterEndDate();
        String compNo  =workPmiCommonDTO.getCompNo();
        String usageDept   = workPmiCommonDTO.getFilterUsageDeptId();
        String usageDeptDesc   = workPmiCommonDTO.getFilterUsageDeptDesc();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        // 삭제 된 점검은 보이지 않아야함.
        query.getAndQuery("x.is_deleted", "N");

        if (!"".equals(workPmiCommonDTO.getPminslistId()))
        {
            query.getAndNumKeyQuery("x.pminslist_id", workPmiCommonDTO.getPminslistId());
            return query.toString();
        }
        
        query.getAndNumKeyQuery("x.pm_id", workPmiCommonDTO.getPmId());
        
        query.getAndNumKeyQuery("x.pminssched_id", workPmiCommonDTO.getPminsschedId());
        
        //점검일자
        query.getAndDateQuery("x.wkor_date", startDate, endDate);
        //법정설비
        query.getAndQuery("y.is_law_eq", workPmiCommonDTO.getFilterIsLawEq());
    	//작업종류
    	query.getSysCdQuery("x.wo_type", workPmiCommonDTO.getFilterWoTypeId(), workPmiCommonDTO.getFilterWoTypeDesc(), "WO_TYPE", compNo,lang);
    	//작업형태
    	query.getSysCdQuery("x.pm_type", workPmiCommonDTO.getFilterPmTypeId(), workPmiCommonDTO.getFilterPmTypeDesc(), "x.wo_type+'_TYPE'", compNo,lang);
        //예방작업번호
    	if(!"".equals(workPmiCommonDTO.getFilterPmNo())){
    		query.append("AND EXISTS (SELECT 1  			");
        	query.append("				FROM TAPMLST		");
        	query.append("			   WHERE 1=1			");
        	query.append("			   	 AND pm_id=x.pm_id	");
        	query.getStringEqualQuery("comp_no", compNo);
        	query.getAndQuery("pm_no", workPmiCommonDTO.getFilterPmNo());
        	query.append("			 )						");
    	}
        //부서
        query.getDeptLevelQuery("x.dept_id", workPmiCommonDTO.getFilterDeptId(), workPmiCommonDTO.getFilterDeptDesc(), compNo);
        //설비
        query.getCodeLikeQuery("y.equip_id", "y.description+y.item_no", workPmiCommonDTO.getFilterEquipId(), workPmiCommonDTO.getFilterEquipDesc());
        //관리자(정)
        query.getCodeLikeQuery("y.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.main_mng_id AND a.comp_no=y.comp_no)", 
                workPmiCommonDTO.getFilterMainMngId(), workPmiCommonDTO.getFilterMainMngName());
        //관리자(부)
        query.getCodeLikeQuery("y.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.sub_mng_id AND a.comp_no=y.comp_no)", 
                workPmiCommonDTO.getFilterSubMngId(), workPmiCommonDTO.getFilterSubMngName());
        
        //설비유형
        query.getSysCdQuery("y.eqctg_type", workPmiCommonDTO.getFilterEqCtgTypeId(), workPmiCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", compNo,lang);
        //위치
        query.getEqLocLevelQuery("y.eqloc_id", workPmiCommonDTO.getFilterEqLocId(), workPmiCommonDTO.getFilterEqLocDesc(), compNo);
        //종류
        query.getEqCtgLevelQuery("y.eqctg_id", workPmiCommonDTO.getFilterEqCtgId(), workPmiCommonDTO.getFilterEqCtgDesc(), compNo);
        
        query.getWkCtrLevelQuery("x.wkctr_id", workPmiCommonDTO.getFilterWkCtrId(), workPmiCommonDTO.getFilterWkCtrDesc(), compNo);
        
        //담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                workPmiCommonDTO.getFilterManagerId(), workPmiCommonDTO.getFilterManagerDesc());
        
        //제외하고....
        if(!"".equals(workPmiCommonDTO.getNotPmschedStatusId())){
            query.append("AND x.pmsched_status  NOT IN ('"+workPmiCommonDTO.getNotPmschedStatusId()+"') ");
        }
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		workPmiCommonDTO.getFilterPlantId(), workPmiCommonDTO.getFilterPlantDesc());
        
        //점검작업상태
        query.getSysCdQuery("x.pmsched_status", workPmiCommonDTO.getFilterPmSchedStatusId(), workPmiCommonDTO.getFilterPmSchedStatusDesc(), "PMSCHED_STATUS", "x.comp_no", user.getLangId());

        //최신버전의 설비의 작업만 보여줌.
        query.getAndQuery("y.is_last_version", "Y");

        //사용부서
        query.getDeptLevelQuery("y.usage_dept", usageDept, usageDeptDesc, user.getCompNo());
        
        return query.toString();
    }

    public String findTotalCount(WorkPmiCommonDTO workPmiCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
                
        query.append(" SELECT                                               ");
        query.append("        COUNT(1)                                      ");
        query.append("FROM  TAPMINSLIST x LEFT OUTER JOIN TAEQUIPMENT y     ");
        query.append("ON x.comp_no = y.comp_no AND x.equip_id = y.equip_id  ");
        query.append("  WHERE 1=1                                           ");
        query.append(this.getWhere(workPmiCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

    @Override
    public int[] updateDeleteTag(final List<WorkPmiDetailDTO> list, final User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        final String deleteTime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");
        
        query.append("UPDATE TAPMINSLIST  SET     ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?     ");
        query.append("WHERE COMP_NO = ?              ");
        query.append("  AND PMINSLIST_ID =  ?      ");
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
                WorkPmiDetailDTO workPmiDetailDTO = list.get(i);

                Object[] objects = getObject(new Object[] {
                        user.getEmpId(),
                        deleteTime,
                        user.getCompNo(),
                        workPmiDetailDTO.getPminslistId()
                });
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
}