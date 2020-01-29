package dream.work.cal.pmyear.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.cal.pmyear.dao.MaWoYearSchedListDAO;
import dream.work.cal.pmyear.dto.MaWoYearSchedCommonDTO;

/**
 * 연간작업일정 - 목록 dao
 * @author  kim21017
 * @version $Id: MaWoYearSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoYearSchedListDAOTarget"
 * @spring.txbn id="maWoYearSchedListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoYearSchedListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoYearSchedListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoYearSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoYearSchedCommonDTO
     * @return List
     */
    public List findSchedList(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  				");
        query.append("SELECT                   															");
        query.append("       ''															SEQNO,			");
        query.append("		 '' 														ISDELCHECK,		");
        query.append("       x.pmsched_id 												PMSCHEDID,      ");
        query.append("       x.pmsched_id 												id,             ");
        query.getDate("x.plan_date", "PLANDATE");
        query.append(",																					");
        query.getDate("x.actual_date", "COMPLETEDATE");
        query.append(",																					");
        query.append("       x.sched_date 												SCHEDDATE,      ");
        query.append("       y.pm_no 													PMNO,        	");
        query.append("       dbo.SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','','"+user.getLangId()+"')		PMSCHEDSTATUS,											");
        query.append("       z.emp_id                                                                                       		empId,       									");
        query.append("       (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = y.comp_no AND a.emp_id = y.emp_id)                   planEmpDesc,                                    ");
        query.append("       (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = z.comp_no AND a.emp_id = z.emp_id)                   exeEmpDesc,                                     ");
        query.append("       y.description 												PMDESC,			");
        query.append("       (SELECT full_desc                                                          ");
        query.append("          FROM TAEQLOC                                                            ");
        query.append("          WHERE comp_no = zz.comp_no                                              ");
        query.append("           AND eqloc_id = zz.eqloc_id)                             AS eqLocDesc,  ");
        query.append("       (CASE zz.eqctg_type WHEN 'MD' THEN '('+zz.old_eq_no+')'+zz.item_no ELSE zz.item_no END)                AS EQUIPNO,     ");
        query.append("        (SELECT description FROM TADEPT WHERE comp_no = zz.comp_no AND dept_id = zz.usage_dept)               AS usageDeptDesc								");
        query.append("      , (SELECT item_no                                                      																				    ");
        query.append("                  FROM TAEQUIPMENT                                                                                                                            ");
        query.append("                  WHERE comp_no = zz.comp_no                                                                                                                  ");
        query.append("                   AND equip_id = zz.p_equip_id)		                                                                                    AS pequipNo			");
        query.append("      , (SELECT description                                                      																			    ");
        query.append("                  FROM TAEQUIPMENT                                                                                                                            ");
        query.append("                  WHERE comp_no = zz.comp_no                                                                                                                  ");
        query.append("                   AND equip_id = zz.p_equip_id)                                                                                          AS pequipDesc		");
        query.append("      , (SELECT (SELECT b.description FROM TADEPT b WHERE b.comp_no = a.comp_no AND b.dept_id = a.usage_dept)                                                 ");
        query.append("                  FROM TAEQUIPMENT a                                                                                                                          ");
        query.append("                  WHERE a.comp_no = zz.comp_no                                                                                                                ");
        query.append("                   AND a.equip_id = zz.p_equip_id)                                                                                AS pequipUsaDeptDesc		");
        query.append("      ,																																						");
        query.append("       (CASE zz.eqctg_type WHEN 'MD' THEN '('+zz.old_eq_no+')'+zz.description ELSE zz.description END)                            AS EQUIPDESC,               ");
        query.append("       (SELECT a.description														");
        query.append("          FROM TADEPT a															");
        query.append("         WHERE a.comp_no = y.comp_no												");
        query.append("           AND a.dept_id = y.dept_id) 							PLANDEPTDESC,	");
        query.append("       (SELECT a.description														");
        query.append("          FROM TADEPT a															");
        query.append("         WHERE a.comp_no = z.comp_no												");
        query.append("           AND a.dept_id = z.dept_id) 							EXEDEPTDESC,	");
        query.append("		 (SELECT a.description														");
        query.append("		  FROM TAWKCTR a															");
        query.append("		  WHERE a.comp_no = y.comp_no												");
        query.append("		  AND a.wkctr_id = y.wkctr_id) 								WKCTRDESC,		");
        query.append("       dbo.SFACODE_TO_DESC(y.pm_type,y.wo_type+'_TYPE','SYS','','"+user.getLangId()+"')			PMTYPEDESC,												");
        query.append("       y.pm_type 									 				pmType,			");
        query.append("       y.wo_type 									 															woType,											");
        query.append("       dbo.SFACODE_TO_DESC(y.schedule_type,'SCHEDULE_TYPE','SYS','','"+user.getLangId()+"')		SCHEDULETYPEDESC,										");
        query.append("       CONVERT(NVARCHAR, y.cycle)+y.period_type 					cycle,			");
        query.append("       y.USAGE 													USAGE,          ");
        query.append("       z.wo_status                     							woStatus,       ");
        query.append("        x.pmsched_status 											pmStatusCode,   ");
        query.append("        y.pm_id 													PMID,           ");
        query.append("       (SELECT a.param2 FROM TACDSYSD a WHERE a.cdsysd_no=y.pm_type AND a.list_type= y.wo_type+'_TYPE')	PARAM,  										");
        query.append("		(SELECT b.param1 FROM TACDSYSD b WHERE b.list_type=z.wo_type+'_TYPE' AND b.cdsysd_no= z.pm_type)	WOPARAM,		");
        query.append("       x.wkor_id 													wkorId          ");
        query.append("       ,z.vendor_id         										vendorId		");
        query.append("       ,dbo.SFAIDTODESC(z.vendor_id, '', 'VENDOR', z.comp_no)   	vendorDesc		");
        query.append("       ,z.tot_amt               									totAmt			");
        query.append("       ,dbo.SFACODE_TO_DESC(z.self_vendor_type, 'SELF_VENDOR_TYPE', 'SYS', '', '"+user.getLangId()+"')	selfVendorTypeDesc                            	");
        query.append("       ,x.remark                                                                                          remark                                          ");
        query.append("       , (SELECT a.full_desc FROM TAEQASMB a WHERE a.comp_no = x.comp_no AND a.eqasmb_id = z.eqasmb_id) 	eqasmbDesc                						");
        query.append("    FROM TAPMSCHED x INNER JOIN TAPMLST y											");
        query.append("    ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id								");
        query.append("    LEFT OUTER JOIN TAEQUIPMENT zz                                                ");
        query.append("    ON x.comp_no = zz.comp_no AND x.equip_id = zz.equip_id AND zz.is_deleted='N'  ");
        query.append("    LEFT OUTER JOIN taworkorder z													");
        query.append("    ON x.comp_no = z.comp_no AND x.wkor_id = z.wkor_id                            ");
        query.append("    WHERE 1=1                                    									");
        query.append(this.getWhere(maWoYearSchedCommonDTO, user));
        query.getOrderByQuery("x.pmsched_id", "x.sched_date, y.description", maWoYearSchedCommonDTO.getOrderBy(), maWoYearSchedCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoYearSchedCommonDTO.getIsLoadMaxCount(), maWoYearSchedCommonDTO.getFirstRow()));

    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaWoYearSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param yyyymm
     * @param status
     * @param compNo
     * @param deptId
     * @param deptDesc
     * @return
     * @throws Exception
     */
    private String getWhere(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO,User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String yyyymm      = maWoYearSchedCommonDTO.getYyyymm();
        String compNo      = user.getCompNo();
        String deptId      = maWoYearSchedCommonDTO.getDeptId();
        String deptDesc    = maWoYearSchedCommonDTO.getDeptDesc();
        String type        = maWoYearSchedCommonDTO.getSchedType();
        String eqLocId     = maWoYearSchedCommonDTO.getEqLocId();
        String eqLocDesc   = maWoYearSchedCommonDTO.getEqLocDesc();
        String eqCtgId     = maWoYearSchedCommonDTO.getEqCtgId();
        String eqCtgDesc   = maWoYearSchedCommonDTO.getEqCtgDesc();
        String eqCtgTypeId     = maWoYearSchedCommonDTO.getEqCtgTypeId();
        String eqCtgTypeDesc   = maWoYearSchedCommonDTO.getEqCtgTypeDesc();
        String mainMngId   = maWoYearSchedCommonDTO.getMainMngId();
        String mainMngName = maWoYearSchedCommonDTO.getMainMngName();
        String subMngId    = maWoYearSchedCommonDTO.getSubMngId();
        String subMngName  = maWoYearSchedCommonDTO.getSubMngName();
        String isLawEq     = maWoYearSchedCommonDTO.getIsLawEq();
        String plfTypeId   = maWoYearSchedCommonDTO.getPlfTypeId();
        String plfTypeDesc = maWoYearSchedCommonDTO.getPlfTypeDesc();
        String woTypeId    = maWoYearSchedCommonDTO.getWoTypeId();
        String woTypeDesc  = maWoYearSchedCommonDTO.getWoTypeDesc();
        String pmTypeId    = maWoYearSchedCommonDTO.getPmTypeId();
        String pmTypeDesc  = maWoYearSchedCommonDTO.getPmTypeDesc();
        String wkCtrId     = maWoYearSchedCommonDTO.getWkCtrId();
        String wkCtrDesc   = maWoYearSchedCommonDTO.getWkCtrDesc();
        String equipId     = maWoYearSchedCommonDTO.getEquipId();
        String equipDesc   = maWoYearSchedCommonDTO.getEquipDesc();
        String pmNo        = maWoYearSchedCommonDTO.getPmNo();
        String empId     = maWoYearSchedCommonDTO.getEmpId();
        String empDesc   = maWoYearSchedCommonDTO.getEmpDesc();
        
        String plantId     = maWoYearSchedCommonDTO.getPlantId();
        String plantDesc   = maWoYearSchedCommonDTO.getPlantDesc();
        String wkorId      = maWoYearSchedCommonDTO.getWkOrId();
        
        String equipNo          = maWoYearSchedCommonDTO.getEquipNo();
        String eqUsaDeptId      = maWoYearSchedCommonDTO.getEqUsaDeptId();
        String eqUsaDeptDesc    = maWoYearSchedCommonDTO.getEqUsaDeptDesc();
        String pequipId         = maWoYearSchedCommonDTO.getPequipId();
        String pequipDesc       = maWoYearSchedCommonDTO.getPequipDesc();
        String peqUsaDeptId     = maWoYearSchedCommonDTO.getPeqUsaDeptId();
        String peqUsaDeptDesc   = maWoYearSchedCommonDTO.getPeqUsaDeptDesc();
        
        if(!"".equals(wkorId)){
            query.getAndQuery("x.wkor_id", wkorId);
            return query.toString();
        }
        
        query.getAndNumKeyQuery("x.pmsched_id", maWoYearSchedCommonDTO.getPmSchedId());
        
        query.append("  AND x.sched_date like '"+yyyymm+"%'				");
        
        query.getSysCdQuery("x.pmsched_status", type, "", "PMSCHED_STATUS", compNo, user.getLangId());
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
        //법정설비여부
        query.getLikeQuery("zz.is_law_eq", isLawEq);
        
        //pmNo
        query.getAndQuery("y.pm_no", pmNo);
        //계획부서
        query.getDeptLevelQuery("y.dept_id", deptId, deptDesc, compNo);
        //실행부서
        query.getDeptLevelQuery("z.dept_id", maWoYearSchedCommonDTO.getExeDeptId(), maWoYearSchedCommonDTO.getExeDeptDesc(), compNo);
        //위치
        query.getEqLocLevelQuery("zz.eqloc_id", eqLocId, eqLocDesc, compNo);
        
        //종류
        query.getEqCtgLevelQuery("zz.eqctg_id", eqCtgId, eqCtgDesc, compNo);
        
        //담당자(정)
        query.getCodeLikeQuery("zz.main_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = zz.main_mng_id AND comp_no=zz.comp_no)", mainMngId, mainMngName);
        
        //담당자(부)
        query.getCodeLikeQuery("zz.sub_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = zz.sub_mng_id AND comp_no=zz.comp_no)", subMngId, subMngName);
        
        //설비
        query.getCodeLikeQuery("zz.equip_id", "zz.description+zz.item_no", equipId, equipDesc);
        
        //내/외자 구분
        query.getSysCdQuery("zz.plf_type", plfTypeId, plfTypeDesc, "PLF_TYPE", compNo,user.getLangId());
        
        //설비유형
        query.getSysCdQuery("zz.eqctg_type", eqCtgTypeId, eqCtgTypeDesc, "EQCTG_TYPE", compNo,user.getLangId());
        
    	query.getSysCdQuery("y.wo_type", woTypeId, woTypeDesc, "WO_TYPE", compNo,user.getLangId());
    	//작업형태
    	query.getSysCdQuery("y.pm_type", pmTypeId, pmTypeDesc, "y.wo_type+'_TYPE'", compNo,user.getLangId());
    	//작업그룹
    	query.getWkCtrLevelQuery("y.wkctr_id", wkCtrId, wkCtrDesc, compNo);
    	//계획담당자
        query.getCodeLikeQuery("y.emp_id", "(SELECT emp_name FROM TAEMP WHERE emp_id=y.emp_id AND comp_no=y.comp_no)", maWoYearSchedCommonDTO.getPlanEmpId(), maWoYearSchedCommonDTO.getPlanEmpDesc());
        //실행담당자
        query.getCodeLikeQuery("z.emp_id", "(SELECT emp_name FROM TAEMP WHERE emp_id=z.emp_id AND comp_no=z.comp_no)", empId, empDesc);
        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = y.plant )", 
        		plantId, plantDesc);
        
        //최신버전의 설비의 작업만 보여줌.
        query.getAndQuery("zz.is_last_version", "Y");
        
        //설비번호
        query.getLikeQuery("zz.item_no", equipNo);
        
        //설비사용부서
        query.getDeptLevelQuery("zz.usage_dept", eqUsaDeptId, eqUsaDeptDesc, compNo);
        
        //상위설비명
        if(!"".equals(pequipId) || !"".equals(pequipDesc))
        {
            query.append(" AND zz.p_equip_id IN                                 ");
            query.append("                  (SELECT a.equip_id                  ");
            query.append("                    FROM TAEQUIPMENT a                ");
            query.append("                    WHERE a.comp_no = x.comp_no       ");
            query.getCodeLikeQuery("a.equip_id", "a.description", pequipId, pequipDesc);
            query.append("                 )                                    ");
        }
        
        //상위설비사용부서
        if(!"".equals(peqUsaDeptId) || !"".equals(peqUsaDeptDesc))
        {
            query.append(" AND zz.p_equip_id IN (SELECT c.equip_id FROM TAEQUIPMENT c ");
            query.append("                          WHERE c.comp_no = a.comp_no         ");
            query.append("                            AND c.usage_dept IN ( SELECT d.dept_id FROM TADEPT d WHERE d.comp_no = c.comp_no          ");
            query.getDeptLevelQuery("d.dept_id", peqUsaDeptId, peqUsaDeptDesc, compNo);
            query.append("                                                 )            ");
            query.append("                        )                                     ");
        }
          
        return query.toString();
    }
    
    public String checkSched(String pmSchedId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT  PMSCHED_STATUS     ");
    	query.append("FROM TAPMSCHED             ");
        query.append("WHERE comp_no = ?          ");
        query.append("  AND PMSCHED_ID =  ?      ");
        Object[] objects = new Object[]{
        		user.getCompNo()
        		,pmSchedId
        };
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),getObject(objects)));
    }
    
    
    public int updateResultSchedDetail(String id, String scheDate, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int result = 0;
        
    	query.append("UPDATE TAWORKORDER  SET       ");
        query.append("         start_date = ?       ");
        query.append("        ,end_date   = ?       ");
        query.append("        ,wkor_date  = ?       ");
        query.append("        ,upd_date   = ?       ");
        query.append("WHERE COMP_NO = ?             ");
        query.append("  AND PMSCHED_ID    = ?       ");
        Object[] objects = new Object[]{
              scheDate
              ,scheDate
              ,scheDate
              ,DateUtil.getDate()
              ,user.getCompNo()
              ,id
        };
        result = this.getJdbcTemplate().update(query.toString(),objects);
        
        return result;
    }
    
    public int updateScheduleDate(String id, String scheDate, String remark, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int result = 0;
    	
    	query.append("UPDATE  TAWOPLAN SET                                 ");
  	    query.append("           WKOR_DATE     = ?                         ");
  	    query.append("          ,START_DATE    = ?                         ");
  	    query.append("          ,END_DATE      = ?                         ");
  	    query.append("WHERE COMP_NO = ?                                    ");
  	    query.append("    AND WKOR_ID = (                                  ");
  	    query.append("                         SELECT WKOR_ID              ");
  	    query.append("                         FROM TAPMSCHED              ");
  	    query.append("                         WHERE COMP_NO = ?           ");
  	    query.append("                              AND PMSCHED_ID = ?     ");
  	    query.append("                        )                            ");
  	    Object[] objects = new Object[]{
  			    scheDate
  			   ,scheDate
  			   ,scheDate
  			   ,user.getCompNo()
  			   ,user.getCompNo()
  			   ,id
  	    };
  		
  	    result =  this.getJdbcTemplate().update(query.toString(),getObject(objects));
        
        query.setClear();
        query.append("UPDATE TAPMSCHED  SET         ");
        query.append("     SCHED_DATE = ?           ");
        query.append("     ,REMARK    = ?           ");
        query.append("WHERE COMP_NO = ?             ");
        query.append("  AND PMSCHED_ID =  ?         ");
        
        objects = new Object[]{
        		scheDate
        		,remark
        		,user.getCompNo()
        		,id
        };
        
        return this.getJdbcTemplate().update(query.toString(),getObject(objects));
        
    }
    
    public String findTotalCount(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                                            ");
        query.append("       COUNT(1)                                                                   ");
        query.append("FROM TAPMSCHED x INNER JOIN TAPMLST y                                             ");
        query.append("ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id                                    ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT zz                                                    ");
        query.append("ON x.comp_no = zz.comp_no AND x.equip_id = zz.equip_id AND zz.is_deleted='N'      ");
        query.append("LEFT OUTER JOIN taworkorder z                                                     ");
        query.append("ON x.comp_no = z.comp_no AND x.wkor_id = z.wkor_id                                ");
        query.append("WHERE 1=1                                                                         ");
        query.append(this.getWhere(maWoYearSchedCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

	@Override
	public void SP_PM_MAKE_WO_BYONE(String compNo, String userNo, String pmschedId) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("EXEC dbo.SP_PM_MAKE_TO_WO '"+compNo+"',"+pmschedId+"; ");
        
        this.getJdbcTemplate().execute(query.toString());
	}
	@Override
    public int updatePmSchedStatus(AppReqDetailDTO appReqDetailDTO, User user, String pmSchedStatus) {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append(" UPDATE TAPMSCHED SET                         ");
        query.append("        pmsched_status    = ?                 ");
        query.append("      , wo_status         = ?                 ");
        query.append("  WHERE  comp_no          = ?                 ");
        query.append("    AND  wkor_id          = ?                 ");

        Object[] objects = new Object[] {
                 pmSchedStatus
               , appReqDetailDTO.getParentStatus()
               , user.getCompNo()
               , appReqDetailDTO.getObjectId()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
        
        return rtnValue;
    }
}