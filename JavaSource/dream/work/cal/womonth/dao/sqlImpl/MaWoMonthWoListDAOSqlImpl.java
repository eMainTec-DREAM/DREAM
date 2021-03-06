package dream.work.cal.womonth.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.cal.womonth.dao.MaWoMonthWoListDAO;
import dream.work.cal.womonth.dto.MaWoMonthWoCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 월간작업일정 - 목록 dao
 * @author  kim21017
 * @version $Id: MaWoMonthWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoMonthWoListDAOTarget"
 * @spring.txbn id="maWoMonthWoListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoMonthWoListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoMonthWoListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthWoCommonDTO
     * @return List
     */
    public List findSchedList(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                     	             		");
        query.append("SELECT                                                            	                		");
        query.append("        ''                                                        AS seqNo            		");
        query.append("        ,''                                                       AS isDelCheck        		");
        query.append("        ,x.wkor_id                                                AS wkOrId            		");
        query.append("        ,x.wo_no                                                 	AS woNo                		");
        query.append("        ,x.description                                            AS wkOrDesc            		");
        query.append("        ,x.wkor_date                                              AS wkOrDate            		");
        query.append("		, 																			");
        query.getDate("x.wkor_date", "startDate");
        query.append("        ,CASE WHEN x.start_date = '' THEN '' ELSE SUBSTRING(x.start_date, 1, 4)+'-'+SUBSTRING(x.start_date, 5, 2)+'-'+SUBSTRING(x.start_date, 7, 2) END        		");
        query.append("       +' '+                                                                             		");
        query.append("       CASE ISNULL(LEN(x.start_time),0) WHEN 0 THEN ''                                		");
        query.append("         WHEN 4 THEN SUBSTRING(x.start_time, 1, 2)+':'+SUBSTRING(x.start_time, 3, 2)    		");
        query.append("         WHEN 3 THEN SUBSTRING(x.start_time, 1, 1)+':'+SUBSTRING(x.start_time, 3, 2)    		");
        query.append("         WHEN 2 THEN x.start_time                                                        		");
        query.append("         WHEN 1 THEN x.start_time                                                        		");
        query.append("         ELSE SUBSTRING(x.start_time, 1, LEN(x.start_time)-4)+':'+SUBSTRING(x.start_time, LEN(x.start_time)-3, 2)+':'+SUBSTRING(x.start_time, LEN(x.start_time)-1, 2) 		");
        query.append("                                                             END     startDatetime         	");
        query.append("        ,CASE WHEN x.end_date = '' THEN '' ELSE SUBSTRING(x.end_date, 1, 4)+'-'+SUBSTRING(x.end_date, 5, 2)+'-'+SUBSTRING(x.end_date, 7, 2) END        		");
        query.append("       +' '+                                                                             		");
        query.append("       CASE ISNULL(LEN(x.end_time),0) WHEN 0 THEN ''                                    		");
        query.append("         WHEN 4 THEN SUBSTRING(x.end_time, 1, 2)+':'+SUBSTRING(x.end_time, 3, 2)        		");
        query.append("         WHEN 3 THEN SUBSTRING(x.end_time, 1, 1)+':'+SUBSTRING(x.end_time, 3, 2)        		");
        query.append("         WHEN 2 THEN x.end_time                                                        		");
        query.append("         WHEN 1 THEN x.end_time                                                        		");
        query.append("         ELSE SUBSTRING(x.end_time, 1, LEN(x.end_time)-4)+':'+SUBSTRING(x.end_time, LEN(x.end_time)-3, 2)+':'+SUBSTRING(x.end_time, LEN(x.end_time)-1, 2) 		");
        query.append("                                                             END     endDatetime         		");
        query.append("        ,                                                                                 	");
        query.append("      (SELECT c.full_desc                                                                		");
        query.append("          FROM TAEQLOC c                                                              		");
        query.append("          WHERE b.comp_no = c.comp_no                                               			");
        query.append("              AND b.eqloc_id = c.eqloc_id)                   AS eqLocDesc,             		");
        query.append("        CASE b.eqctg_type WHEN 'MD' THEN '('+b.old_eq_no+')'+ b.description ELSE b.description END               AS equipDesc,            		");
        query.append("        b.item_no                                      AS equipNo,                				");
        query.append("        (SELECT description                                                                		");
        query.append("           FROM TADEPT                                                                    		");
        query.append("          WHERE comp_no = x.comp_no                                                        		");
        query.append("            AND dept_id = x.dept_id)                                         deptDesc,        	");
        query.append("         (SELECT description                                                                		");
        query.append("          FROM TAWKCTR                                                                    		");
        query.append("          WHERE comp_no = x.comp_no                                                        		");
        query.append("          AND wkctr_id = x.wkctr_id)                                         wkCtrDesc,        	");
        query.append("        dbo.SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+loginUser.getLangId()+"')      shiftDesc,        		");
        query.append("        dbo.SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+loginUser.getLangId()+"')            woTypeDesc,        		");
        query.append("        dbo.SFACODE_TO_DESC(x.pm_type,x.wo_type+'_TYPE','SYS','','"+loginUser.getLangId()+"')    pmTypeDesc,        		");
        query.append("        x.work_time                                                         workTime,        		");
        query.append("        SUBSTRING(x.start_time,1,2)+':'+SUBSTRING(x.start_time,3,4)         startTime,         	");
        query.append("        SUBSTRING(x.end_time,1,2)+':'+SUBSTRING(x.end_time,3,4)             endTime,           	");
        query.append("        (SELECT a.lndn_start_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no                		");
        query.append("            AND a.wkor_id = x.wkor_id) prodStartTime,                                        		");
        query.append("        (SELECT a.lndn_end_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no                		");
        query.append("            AND a.wkor_id = x.wkor_id) prodEndTime,                                        		");
        query.append("        dbo.SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+loginUser.getLangId()+"')       woStatusDesc,     		");
        query.append("        x.wo_status                                                      woStatus,        		");
        query.append("        (SELECT emp_name                                                                   		");
        query.append("           FROM TAEMP                                                                        		");
        query.append("          WHERE comp_no = x.comp_no                                                        		");
        query.append("            AND emp_id = x.emp_id)                                         empDesc,        		");
        query.append("        (SELECT ',' + c.emp_name                                                            		");
        query.append("            FROM TAEMP c                                                                    		");
        query.append("            WHERE b.comp_no = c.comp_no                                                   		");
        query.append("                AND b.sub_mng_id = c.emp_id)                             AS subMng,         		");
        query.append("           (SELECT ',' + c.full_desc                                                            	");
        query.append("            FROM TAEQCTG c                                                                		");
        query.append("            WHERE b.comp_no = c.comp_no                                                   		");
        query.append("                AND b.eqctg_id = c.eqctg_id)                          AS eqCtgDesc,          		");
        query.append("        x.perform                                                     perform,            		");
        query.append("      x.wo_type                                                    	woType,                		");
        query.append("      x.pm_type                                                   	pmType,                   	");
        query.append("      x.wo_gen_type                                                	woGenType,              	");
        query.append("      (select param1 FROM tacdsysd where list_Type= x.wo_type+'_TYPE' AND cdsysd_no=x.pm_type) param,        		");
        query.append("      (SELECT b.pm_no                                                                 		");
        query.append("       FROM TAPMSCHED a INNER JOIN TAPMLST b                                          		");
        query.append("       ON a.comp_no=b.comp_no AND a.pm_id=b.pm_id                                     		");
        query.append("       WHERE a.comp_no=x.comp_no AND a.wkor_id=x.wkor_id) pmNo,                       		");
        query.append("      x.self_vendor_type                                          selfVendorType,                                                		");
        query.append("      dbo.SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+loginUser.getLangId()+"')    selfVendorTypeDesc            		");
        query.append("     , (SELECT aa.full_desc																	");
        query.append("     		FROM TAEQASMB aa																	");
        query.append("     	   WHERE aa.comp_no = x.comp_no															");
        query.append("     		 AND aa.eqasmb_id = a.eqasmb_id)				 			AS eqAsmbDesc      		");
        query.append("FROM TAWORKORDER x                                                                     		");
        query.append("INNER JOIN TAWOEQUIP a                                                                 		");
        query.append("ON x.comp_no = a.comp_no                                                                 		");
        query.append("AND x.wkor_id = a.wkor_id                                                              		");
        query.append("INNER JOIN TAEQUIPMENT b                            		");
        query.append("ON a.comp_no = b.comp_no                          		");
        query.append("AND a.equip_id = b.equip_id                                                                  		");
        query.append("WHERE 1=1                                                                                                 		");
        query.append(this.getWhere(maWoMonthWoCommonDTO, maWoResultMstrCommonDTO, loginUser));

        query.getOrderByQuery("x.wkor_id", "x.wkor_date, x.description", maWoMonthWoCommonDTO.getOrderBy(), maWoMonthWoCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoMonthWoCommonDTO.getIsLoadMaxCount(), maWoMonthWoCommonDTO.getFirstRow()));

    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
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
    private String getWhere(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String yyyymmdd    = CommonUtil.getRowDateToNum(maWoMonthWoCommonDTO.getYyyymmdd());
        String compNo      = user.getCompNo();
        String deptId      = maWoMonthWoCommonDTO.getDeptId();
        String deptDesc    = maWoMonthWoCommonDTO.getDeptDesc();
        String type        = maWoMonthWoCommonDTO.getSchedType();
        String eqLocId     = maWoMonthWoCommonDTO.getEqLocId();
        String eqLocDesc   = maWoMonthWoCommonDTO.getEqLocDesc();
        String eqCtgId     = maWoMonthWoCommonDTO.getEqCtgId();
        String eqCtgDesc   = maWoMonthWoCommonDTO.getEqCtgDesc();
        String mainMngId   = maWoMonthWoCommonDTO.getMainMngId();
        String mainMngName = maWoMonthWoCommonDTO.getMainMngName();
        String subMngId    = maWoMonthWoCommonDTO.getSubMngId();
        String subMngName  = maWoMonthWoCommonDTO.getSubMngName();
        String isLawEq     = maWoMonthWoCommonDTO.getIsLawEq();
        String plfTypeId   = maWoMonthWoCommonDTO.getPlfTypeId();
        String plfTypeDesc = maWoMonthWoCommonDTO.getPlfTypeDesc();
        String woTypeId    = maWoMonthWoCommonDTO.getWoTypeId();
        String woTypeDesc  = maWoMonthWoCommonDTO.getWoTypeDesc();
        String pmTypeId    = maWoMonthWoCommonDTO.getPmTypeId();
        String pmTypeDesc  = maWoMonthWoCommonDTO.getPmTypeDesc();
        String wkCtrId     = maWoMonthWoCommonDTO.getWkCtrId();
        String wkCtrDesc   = maWoMonthWoCommonDTO.getWkCtrDesc();
        String eqCtgTypeId = maWoMonthWoCommonDTO.getEqCtgTypeId();
        String eqCtgTypeDesc= maWoMonthWoCommonDTO.getEqCtgTypeDesc();
        String shiftId = maWoMonthWoCommonDTO.getShiftId();
        String shiftDesc= maWoMonthWoCommonDTO.getShiftDesc();
        String equipId     = maWoMonthWoCommonDTO.getEquipId();
        String equipDesc   = maWoMonthWoCommonDTO.getEquipDesc();
        String empId     = maWoMonthWoCommonDTO.getEmpId();
        String empDesc   = maWoMonthWoCommonDTO.getEmpDesc();
        String plantId     = maWoMonthWoCommonDTO.getPlantId();
        String plantDesc   = maWoMonthWoCommonDTO.getPlantDesc();
        
        if (!"".equals(maWoMonthWoCommonDTO.getCreatedWkorId()))
        {
            query.getAndQuery("x.wkor_id", maWoMonthWoCommonDTO.getCreatedWkorId());
            return query.toString();
        }
        
        //교정작업은 조회되면 안됨
        query.append("AND wo_type != 'PMC'       ");
        
        query.append("  AND x.wkor_date LIKE '"+yyyymmdd+"%'                        ");
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        if (!"".equals(maWoResultMstrCommonDTO.getHiddenWoType())) query.append("AND x.wo_type <> 'PMC'");
    	
        //법정설비여부
        query.getLikeQuery("b.is_law_eq", isLawEq);
    	
    	//상태
    	query.getSysCdQuery("x.wo_status", type, "", "WO_STATUS", compNo, user.getLangId());
    	
    	//부서
    	query.getDeptLevelQuery("x.dept_id", deptId, deptDesc, compNo);
    	
    	//위치
    	query.getEqLocLevelQuery("b.eqloc_id", eqLocId, eqLocDesc, compNo);
    	
    	//담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", empId, empDesc);
        
    	//종류
        query.getEqCtgLevelQuery("b.eqctg_id", eqCtgId, eqCtgDesc, compNo); 
    	
    	//담당자(정)
        query.getCodeLikeQuery("b.main_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = b.main_mng_id AND comp_no = b.comp_no)", mainMngId, mainMngName);
    	
    	//담당자(부)
        query.getCodeLikeQuery("b.sub_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = b.sub_mng_id AND comp_no = b.comp_no)", subMngId, subMngName);
    	
    	//설비
        query.getCodeLikeQuery("b.equip_id", "b.description+b.item_no", equipId, equipDesc);
    	
    	//내/외자 구분
        query.getSysCdQuery("b.plf_type", plfTypeId, plfTypeDesc, "PLF_TYPE", compNo,user.getLangId());
    	
    	//작업형태
    	query.getSysCdQuery("x.pm_type", pmTypeId, pmTypeDesc, "x.wo_type+'_TYPE'", compNo,user.getLangId());
    	
        //작업종류
      	query.getSysCdQuery("x.wo_type", woTypeId, woTypeDesc, "WO_TYPE", compNo,user.getLangId());
      	
      	//작업종류
      	query.getSysCdQuery("x.shift_type", shiftId, shiftDesc, "SHIFT_TYPE", compNo,user.getLangId());
      	
      	//작업그룹 
      	query.getWkCtrLevelQuery("x.wkctr_id", wkCtrId, wkCtrDesc, compNo);
        
        //설비유형
      	query.getSysCdQuery("b.eqctg_type", eqCtgTypeId, eqCtgTypeDesc, "EQCTG_TYPE", compNo,user.getLangId());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", plantId, plantDesc);
        
        //최신버전의 설비의 작업만 보여줌.
        query.getAndQuery("b.is_last_version", "Y");
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int updateDeleteTagSched(String id, User user)
    {
    	int rtnValue  = 0;
    	
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.append("UPDATE TAWORKORDER  SET       ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?      ");
        query.append("WHERE COMP_NO = ?             ");
    	query.append("  AND WKOR_ID =      ?       ");
    	
    	Object[] objects = new Object[]{
    			 user.getEmpId()
    			,DateUtil.getDateTime()
        		,user.getCompNo()
        		,id
        };
    	rtnValue = this.getJdbcTemplate().update(query.toString(),objects);
    	
    	return rtnValue;
    }
    
    public int create4wp(String wkorId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("EXEC dbo.SP_WOMAKE_4WP_BYONE         ");
    	query.append("                  '"+user.getCompNo()+"'       ");
    	query.append("                 ,"+wkorId+"         ");
    	query.append("                 ;                   ");
    	
    	this.getJdbcTemplate().execute(query.toString());
    	
    	return 0;
    }
    
    /**
     * 리포트 바디
     * @author kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoMonthWoCommonDTO
     * @return
     */
    public List findReportWoList(String id, MaWoMonthWoCommonDTO maWoMonthWoCommonDTO ) {

		QuerySqlBuffer query = new QuerySqlBuffer();
		String lang = maWoMonthWoCommonDTO.getUserLang();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  				");
        query.append("SELECT                                                                    		");
        query.append("        x.wo_no woNo                                                        		");
        query.append("        ,(SELECT description                                                		");
        query.append("            FROM TADEPT                                                        	");
        query.append("            WHERE comp_no = x.comp_no                                        		");
        query.append("              AND dept_id = x.dept_id) deptDesc                                	");
        query.append("        ,(SELECT emp_name                                                    		");
        query.append("            FROM TAEMP                                                        	");
        query.append("            WHERE comp_no = x.comp_no                                        		");
        query.append("              AND emp_id = x.emp_id) empdesc                                		");
        query.append("        ,CONVERT(VARCHAR, CONVERT(DATE,x.start_date), 23)+' '+                	");
        query.append("        SUBSTRING(x.start_time,1,2)+':'+SUBSTRING(x.start_time,3,4)+' ~ '+    	");
        query.append("        CONVERT(VARCHAR, CONVERT(DATE,x.end_date), 23)+' '+                    	");
        query.append("        SUBSTRING(x.end_time,1,2)+':'+SUBSTRING(x.end_time,3,4) woDate        	");
        query.append("        ,x.work_time workTime                                                		");
        query.append("        ,x.description woDesc                                                		");
        query.append("        ,b.description                                         AS eqDesc        	");
        query.append("        ,(SELECT full_desc                                                    	");
        query.append("            FROM TAEQLOC                                                    		");
        query.append("            WHERE comp_no = x.comp_no                                        		");
        query.append("                AND eqloc_id = x.eqloc_id    )                 AS eqLocDesc    	");
        query.append("        ,dbo.SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+lang+"') woTypeDesc	");
        query.append("        ,dbo.SFACODE_TO_DESC(x.pm_type,x.wo_type+'_TYPE','SYS','','"+lang+"') pmTypeDesc    		");
        query.append("        ,(SELECT (SELECT (SELECT b.key_name                                    	");
        query.append("                        FROM TALANG b                                        		");
        query.append("                        WHERE  b.lang = '"+lang+"'                            	");
        query.append("                        AND aa.key_type = b.key_type                        		");
        query.append("                        AND aa.key_no = b.key_no)    description                	");
        query.append("                    FROM TAFAILURE aa                                        		");
        query.append("                    WHERE aa.comp_no = x.comp_no                            		");
        query.append("                      AND aa.failure_id = ca_cd                             		");
        query.append("                      AND aa.fail_type='CA')                                		");
        query.append("            FROM TAWOFAIL                                                    		");
        query.append("            WHERE comp_no = x.comp_no                                        		");
        query.append("              AND wkor_id = x.wkor_id) caCdDesc                                	");
        query.append("        ,(SELECT (SELECT (SELECT b.key_name                                    	");
        query.append("                        FROM TALANG b                                        		");
        query.append("                        WHERE  b.lang = '"+lang+"'                            	");
        query.append("                        AND aa.key_type = b.key_type                        		");
        query.append("                        AND aa.key_no = b.key_no)    description                	");
        query.append("                    FROM TAFAILURE aa                                        		");
        query.append("                    WHERE aa.comp_no = x.comp_no                            		");
        query.append("                      AND aa.failure_id = re_cd                                	");
        query.append("                      AND aa.fail_type='RE')                                		");
        query.append("            FROM TAWOFAIL                                                    		");
        query.append("            WHERE comp_no = x.comp_no                                        		");
        query.append("              AND wkor_id = x.wkor_id) reCdDesc                                		");
        query.append("        ,x.perform perform,                                                                    		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woResultReport' AND key_type='LABEL') woResultReport,		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='number' AND key_type='LABEL') num,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woDate' AND key_type='LABEL') workDate,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woEquip' AND key_type='LABEL') woEquip,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipNo' AND key_type='LABEL') equipNo,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='costTime' AND key_type='LABEL') costTime,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkPoint' AND key_type='LABEL') 'checkPoint',				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woName' AND key_type='LABEL') woName,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipName' AND key_type='LABEL') equipName,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqLocName' AND key_type='LABEL') equipLoc,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woType' AND key_type='LABEL') woType,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmType' AND key_type='LABEL') pmType,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='action' AND key_type='LABEL') action,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woRemark' AND key_type='LABEL') woRemark,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woCraft' AND key_type='LABEL') workCraft,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='empNumber' AND key_type='LABEL') empNumber,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='name' AND key_type='LABEL') empName,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='costTime' AND key_type='LABEL') costTime2,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark' AND key_type='LABEL') remark,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='insertPart' AND key_type='LABEL') insertPart,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNo' AND key_type='LABEL') partNumber,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNameSize' AND key_type='LABEL') partNameSize,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='useQty' AND key_type='LABEL') useQty,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='seqNo' AND key_type='LABEL') seqNo,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='asmbInspect' AND key_type='LABEL') asmbInspect,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkMethod' AND key_type='LABEL') checkMethod,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='fitBasis' AND key_type='LABEL') fitBasis,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkType' AND key_type='LABEL') checkType,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmPointRsltStatus' AND key_type='LABEL') pmPointRsltStatus,	");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='dept' AND key_type='LABEL') dept,							");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='manager' AND key_type='LABEL') manager,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='reason' AND key_type='LABEL') reason,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='startDate' AND key_type='LABEL') startDate,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='endDate' AND key_type='LABEL') endDate,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='endDate' AND key_type='LABEL') endDate,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkValUom' AND key_type='LABEL') checkValUom,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmPoint' AND key_type='LABEL') pmPoint,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='resultVal' AND key_type='LABEL') resultVal					");
        query.append("FROM TAWORKORDER x                                                                     		");
        query.append("INNER JOIN TAWOEQUIP a                                                                 		");
        query.append("ON x.comp_no = a.comp_no                                                                 		");
        query.append("AND x.wkor_id = a.wkor_id                                                              		");
        query.append("INNER JOIN TAEQUIPMENT b                            		");
        query.append("ON a.comp_no = b.comp_no                          		");
        query.append("AND a.equip_id = b.equip_id                        		");
        query.append("WHERE 1=1                                                                                                		");
        query.append(" AND x.comp_no = '"+maWoMonthWoCommonDTO.getCompNo()+"'					");
        query.append(" AND x.wkor_id = "+id+"													");

		return getJdbcTemplate().queryForList(query.toString());
	}

    /**
     * 리포트 작업자
     * @author kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoMonthWoCommonDTO
     * @return
     */
    public List findReportWoCraftList(String id, MaWoMonthWoCommonDTO maWoMonthWoCommonDTO ) {

		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              	");
		query.append("SELECT																		");
        query.append("		(SELECT emp_no															");
        query.append("			FROM TAEMP															");
        query.append("			WHERE comp_no = x.comp_no											");
        query.append("			  AND emp_id = x.emp_id) craftEmpNo									");
        query.append("		,(SELECT emp_name														");
        query.append("			FROM TAEMP															");
        query.append("			WHERE comp_no = x.comp_no											");
        query.append("			  AND emp_id = x.emp_id) craftEmpDesc								");
        query.append("		,CONVERT(VARCHAR, CONVERT(DATE,x.start_date), 23)+' '+					");
        query.append("		SUBSTRING(x.start_time,1,2)+':'+SUBSTRING(x.start_time,3,4) craftStartDate	");
        query.append("		,CONVERT(VARCHAR, CONVERT(DATE,x.end_date), 23)+' '+					");
        query.append("		SUBSTRING(x.end_time,1,2)+':'+SUBSTRING(x.end_time,3,4) craftEndDate	");
        query.append("		,CONVERT(VARCHAR, x.work_time) craftWorkTime							");
        query.append("		,x.remark craftRemark													");
        query.append("FROM TAWOCRAFT x																");
        query.append("WHERE x.comp_no = '"+maWoMonthWoCommonDTO.getCompNo()+"'						");
        query.append("  AND x.wkor_id = "+id+"														");
        query.append("UNION ALL																		");
        query.append("SELECT '','','','','',''														");

		return getJdbcTemplate().queryForList(query.toString());
	}

    /**
     * 리포트 투입부품
     * @author kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoMonthWoCommonDTO
     * @return
     */
    public List findReportWoPartList(String id, MaWoMonthWoCommonDTO maWoMonthWoCommonDTO ) {

		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              	");
		query.append("SELECT														");
        query.append("		y.part_no partNo										");
        query.append("		,y.description+', '+ISNULL(y.pt_size,'') partDesc		");
        query.append("		,CONVERT(VARCHAR, x.use_qty) partUseQty					");
        query.append("FROM TAWOPARTS x, TAPARTS y									");
        query.append("WHERE x.comp_no = y.comp_no									");
        query.append("  AND x.part_id = y.part_id									");
        query.append("  AND x.comp_no = '"+maWoMonthWoCommonDTO.getCompNo()+"'		");
        query.append("  AND x.wkor_id = "+id+"										");
        query.append("UNION ALL														");
        query.append("SELECT '','',''												");

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    /**
     * 리포트 검사항목
     * @author kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoMonthWoCommonDTO
     * @return
     */
    public List findReportWoPointList(String id, MaWoMonthWoCommonDTO maWoMonthWoCommonDTO ) {

		QuerySqlBuffer query = new QuerySqlBuffer();
		String lang = maWoMonthWoCommonDTO.getUserLang();

		query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              	");
		query.append("SELECT CONVERT(VARCHAR, y.step_num) AS POSEQNO,									");
        query.append("		(SELECT description															");
        query.append("		  FROM TAEQASMB																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		  AND eqasmb_id = y.eqasmb_id)+'/'+											");
        query.append("		y.check_point AS POCHECKPOINT,												");
        query.append("		y.check_method+'/'+y.fit_basis AS POFITBASIS,								");
        query.append("		dbo.SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS',x.comp_no,'"+lang+"') AS POCHECKTYPE,		");
        query.append("		CONVERT(VARCHAR, y.check_min)+'/'+CONVERT(VARCHAR, y.check_basis_val)+'/'+CONVERT(VARCHAR, y.check_max)+'('+y.uom+')' AS POUOM,");
        query.append("		CASE z.wo_status WHEN 'C' THEN												");
        query.append("		dbo.SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+lang+"')	");
        query.append("		ELSE '' END +'' AS POSTATUS,												");
        query.append("		CASE z.wo_status WHEN 'C' THEN CONVERT(VARCHAR, x.result_value) ELSE '' END +'' AS POVALUE		");
        query.append("FROM TAWOPOINT x, TAPMPOINT y, TAWORKORDER z										");
        query.append("WHERE x.comp_no = y.comp_no														");
        query.append("AND x.comp_no = z.comp_no															");
        query.append("AND x.wkor_id = z.wkor_id															");
        query.append("	AND x.pm_point_id = y.pm_point_id												");
        query.getAndQuery("x.wkor_id", id);
        query.getAndQuery("x.comp_no", maWoMonthWoCommonDTO.getCompNo());
        query.append("UNION ALL																			");
        query.append("SELECT '','','','','','',''														");

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    /**
     * 리포트 작업설비항목
     * @author kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoMonthWoCommonDTO
     * @return
     */
    public List findReportWoEqList(String id, MaWoMonthWoCommonDTO maWoMonthWoCommonDTO ) {

		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              	");
		query.append("SELECT (SELECT item_no 								");
        query.append("			FROM TAEQUIPMENT 							");
        query.append("			WHERE comp_no = x.comp_no					");
        query.append("			AND   equip_id = x.equip_id )  itemNumber	");
        query.append("		,(SELECT description 							");
        query.append("			FROM TAEQUIPMENT 							");
        query.append("			WHERE comp_no = x.comp_no					");
        query.append("			AND   equip_id = x.equip_id )  itemDesc		");
        query.append("FROM TAWOEQUIP x										");
        query.append("WHERE 1=1												");
        query.getAndQuery("x.wkor_id", id);
        query.getAndQuery("x.comp_no", maWoMonthWoCommonDTO.getCompNo());

		return getJdbcTemplate().queryForList(query.toString());
	}

	@Override
	public String findTotalCount(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              	");
        query.append("SELECT															");
        query.append("       COUNT(1)                                           		");
        query.append("FROM TAWORKORDER x                                                ");
        query.append("INNER JOIN TAWOEQUIP a                                            ");
        query.append("ON x.comp_no = a.comp_no                                          ");
        query.append("AND x.wkor_id = a.wkor_id                                         ");
        query.append("INNER JOIN TAEQUIPMENT b                            				");
        query.append("ON a.comp_no = b.comp_no                          				");
        query.append("AND a.equip_id = b.equip_id                                                                  		");
        query.append("WHERE 1=1                                                                                                 		");
        query.append(this.getWhere(maWoMonthWoCommonDTO, maWoResultMstrCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}