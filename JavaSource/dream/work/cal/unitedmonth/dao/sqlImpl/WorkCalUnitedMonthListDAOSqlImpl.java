package dream.work.cal.unitedmonth.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.cal.unitedmonth.dao.WorkCalUnitedMonthListDAO;
import dream.work.cal.unitedmonth.dto.WorkCalUnitedMonthCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 월간작업일정 - 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workCalUnitedMonthListDAOTarget"
 * @spring.txbn id="workCalUnitedMonthListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalUnitedMonthListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkCalUnitedMonthListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param workCalUnitedMonthCommonDTO
     * @return List
     */
    public List findWorkSchedList(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              					");
        query.append("SELECT																			");
        query.append("		''															seqNo			");
        query.append("		,''															isDelCheck		");
        query.append("		,x.wkor_id													wkOrId			");
        query.append("		,x.wo_no 													woNo			");
        query.append("		,x.description 												wkOrDesc		");
        query.append("      ,x.wkor_date                                                wkOrDate  		");
        query.append("      ,x.wkor_date                                                startDate  		");
        query.append("      ,x.start_date+x.start_time                                 startDatetime	");
        query.append("      ,x.end_date+x.end_time                                     endDatetime		");
        query.append("      ,(SELECT full_desc                                						    ");
        query.append("        FROM TAEQLOC                                                              ");
        query.append("        WHERE comp_no = b.comp_no                                         	    ");
        query.append("          AND eqloc_id = b.eqloc_id )                    	 AS	eqLocDesc           ");
        query.append("	   ,(CASE b.eqctg_type WHEN 'MD' THEN '('+b.old_eq_no+')'+b.description ELSE b.description END) AS equipDesc		");
        query.append("	   ,b.item_no												AS equipNo			");
        query.append("		,(SELECT description														");
        query.append("		   FROM TADEPT																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND dept_id = x.dept_id) 								deptDesc		");
        query.append("		 ,(SELECT description														");
        query.append("		  FROM TAWKCTR																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		  AND wkctr_id = x.wkctr_id) 								wkCtrDesc		");
        query.append("		,dbo.SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+loginUser.getLangId()+"')	shiftDesc	");
        query.append("		,dbo.SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+loginUser.getLangId()+"') 			woTypeDesc	");
        query.append("		,dbo.SFACODE_TO_DESC(x.pm_type,x.wo_type+'_TYPE','SYS','','"+loginUser.getLangId()+"') pmTypeDesc	");
        query.append("		,x.work_time 												workTime		");
        query.append("		,SUBSTRING(x.start_time,1,2)+':'+SUBSTRING(x.start_time,3,4) startTime		");
        query.append("		,SUBSTRING(x.end_time,1,2)+':'+SUBSTRING(x.end_time,3,4) 	endTime			");
        query.append("		,(SELECT a.lndn_start_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no		");
        query.append("			AND a.wkor_id = x.wkor_id) 								prodStartTime	");
        query.append("		,(SELECT a.lndn_end_time FROM TAWOFAIL a WHERE a.comp_no = x.comp_no		");
        query.append("			AND a.wkor_id = x.wkor_id) 								prodEndTime		");
        query.append("		,dbo.SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+loginUser.getLangId()+"')		woStatusDesc	");
        query.append("		,x.wo_status												woStatus		");
        query.append("		,(SELECT emp_name															");
        query.append("		   FROM TAEMP																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND emp_id = x.emp_id) 									empDesc			");
        query.append("	    ,(SELECT emp_name															");
        query.append("		  FROM TAEMP 										                        ");
        query.append("		  WHERE comp_no = b.comp_no                                       			");
        query.append("			AND emp_id = b.sub_mng_id) 							AS subMng			");
        query.append("      ,(SELECT full_desc                             							    ");
        query.append("        FROM TAEQCTG                                                              ");
        query.append("        WHERE comp_no = b.comp_no                                      	 		");
        query.append("        	AND eqctg_id = b.eqctg_id)             			 	AS eqCtgDesc		");
        query.append("		,x.perform 													perform			");
        query.append("      ,x.wo_type													woType			"); 
        query.append("      ,x.pm_type                                                  pmType			"); 
        query.append("      ,x.wo_gen_type												woGenType		"); 
        query.append("      ,(select param1 FROM tacdsysd where list_Type= x.wo_type+'_TYPE' AND cdsysd_no=x.pm_type)	param ");
        query.append("      ,(SELECT b.pm_no                                                        	");
        query.append("       FROM TAPMSCHED a INNER JOIN TAPMLST b                                  	");
        query.append("       ON a.comp_no=b.comp_no AND a.pm_id=b.pm_id                             	");
        query.append("       WHERE a.comp_no=x.comp_no AND a.wkor_id=x.wkor_id) 		pmNo       		");
        query.append("      , x.self_vendor_type                                        selfVendorType	");
        query.append("      , dbo.SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+loginUser.getLangId()+"')	selfVendorTypeDesc	");
        query.append("      ,( SELECT full_desc     													");
        query.append("         FROM TAEQASMB     														");
        query.append("         WHERE comp_no = x.comp_no    											");
        query.append("      	AND eqasmb_id=( SELECT z1.eqasmb_id     								");
        query.append("      					FROM TAWOFAIL z1     									");
        query.append("      					WHERE x.wkor_id=z1.wkor_id	)    						");
        query.append("        ) 														eqAsmbDesc  	");
        query.append("FROM TAWORKORDER x LEFT OUTER JOIN TAWOEQUIP a                                    ");
        query.append("ON x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id                                ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT b                                                     ");
        query.append("ON a.comp_no = b.comp_no AND a.equip_id = b.equip_id                              ");
        query.append("WHERE 1=1																			");
        
        query.append(this.getWhere(workCalUnitedMonthCommonDTO, maWoResultMstrCommonDTO, loginUser));

        query.getOrderByQuery("x.wkor_id", "x.wkor_date, x.description", workCalUnitedMonthCommonDTO.getOrderBy(), workCalUnitedMonthCommonDTO.getDirection());
                
        return getJdbcTemplate().queryForList(query.toString(workCalUnitedMonthCommonDTO.getIsLoadMaxCount(), workCalUnitedMonthCommonDTO.getFirstRow()));

    }
    
	@Override
	public List findPmiSchedList(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              					");
        query.append("SELECT																		    ");
        query.append("		''															AS SEQNO		");
        query.append("		,''															AS ISDELCHECK	");
        query.append("      ,x.description                                              AS DESCRIPTION  ");
        query.append("		,x.wkor_date												AS WKORDATE	    ");
        query.append("      ,(select key_name															");
        query.append("       from talang																");
        query.append("       where key_type = 'CODESET' and lang = '"+user.getLangId()+"'				");
        query.append("         and key_no = 'MEASURE_TIME.'+ x.measure_time)  			AS MEASURETIME	");
        query.append("		,y.item_no													AS EQUIPNO		");
        query.append("		,y.description												AS EQUIPDESC	");
        query.append("        ,(SELECT full_desc                                                        ");
        query.append("          FROM TAEQLOC b                                                          ");
        query.append("         WHERE comp_no = y.comp_no                                                ");
        query.append("           AND eqloc_id = y.eqloc_id)                             AS EQLOCDESC    ");
        query.append("		,dbo.SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+user.getLangId()+"')			AS SHIFTTYPE		");
        query.append("		,(SELECT description													                                ");
        query.append("		    FROM TADEPT															                                ");
        query.append("		   WHERE comp_no = x.comp_no											                                ");
        query.append("			 AND dept_id = x.dept_id) 								AS DEPTDESC	                                ");
        query.append("		,dbo.SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+user.getLangId()+"') 				AS PMTYPE			");
        query.append("		,x.pmsched_status											AS PMSCHEDSTATUS	                            ");
        query.append("		,dbo.SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','','"+user.getLangId()+"')	AS PMISCHEDSTATUS	");
        query.append("		,x.work_time												AS WORKTIME	                                ");
        query.append("		,(SELECT emp_name														                                ");
        query.append("		   FROM TAEMP															                                ");
        query.append("		  WHERE comp_no = x.comp_no												                                ");
        query.append("			AND emp_id = x.emp_id) 									AS EMPDESC		                            ");
        query.append("		,x.remark													AS REMARK		                            ");
        query.append("		,x.pminslist_id												AS PMINSLISTID	                            "); 
        query.append("      ,'workPmiDetail'											AS param 									");
        query.append("FROM  TAPMINSLIST x LEFT OUTER JOIN TAEQUIPMENT y                                                             ");
        query.append("ON x.comp_no = y.comp_no AND x.equip_id = y.equip_id                                                          ");
        query.append("WHERE 1=1																		                                ");
    	query.append(this.getInsWhere(workCalUnitedMonthCommonDTO, user));
    	
    	query.getOrderByQuery("x.pminslist_id", "x.wkor_date, x.description", workCalUnitedMonthCommonDTO.getOrderBy(), workCalUnitedMonthCommonDTO.getDirection());
    	
    	return getJdbcTemplate().queryForList(query.toString(workCalUnitedMonthCommonDTO.getIsLoadMaxCount(), workCalUnitedMonthCommonDTO.getFirstRow()));
    	
	}
    
    private String getWhere(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO , MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {        
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getStringEqualQuery("x.IS_DELETED", "N");
    	
    	if (!"".equals(workCalUnitedMonthCommonDTO.getCreatedWkorId()))
    	{
    		query.getAndQuery("x.wkor_id", workCalUnitedMonthCommonDTO.getCreatedWkorId());
    		return query.toString();
    	}
    	
    	//날짜 전체 검색(리스트조회 사용)
    	String day = CommonUtil.getRowDateToNum(workCalUnitedMonthCommonDTO.getYyyymmdd());
		query.append("  AND x.wkor_date LIKE '"+day+"%'						");
		if (!"".equals(maWoResultMstrCommonDTO.getHiddenWoType())) query.append("AND x.wo_type <> 'PMC'");
    	
    	//법정설비여부
		query.getLikeQuery("b.is_law_eq", workCalUnitedMonthCommonDTO.getIsLawEq());
    	
    	//상태
    	query.getSysCdQuery("x.wo_status",workCalUnitedMonthCommonDTO.getSchedType(), "", "WO_STATUS", user.getCompNo(), user.getLangId());
    	
    	//부서
    	query.getDeptLevelQuery("x.dept_id", workCalUnitedMonthCommonDTO.getDeptId(), workCalUnitedMonthCommonDTO.getDeptDesc(), user.getCompNo());
    	
    	//위치
    	query.getEqLocLevelQuery("b.eqloc_id", workCalUnitedMonthCommonDTO.getEqLocId(), workCalUnitedMonthCommonDTO.getEqLocDesc(), user.getCompNo());
    	
    	//담당자
    	query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
    			workCalUnitedMonthCommonDTO.getEmpId(), workCalUnitedMonthCommonDTO.getEmpDesc());
    	
    	//종류
    	query.getEqCtgLevelQuery("b.eqctg_id", workCalUnitedMonthCommonDTO.getEqCtgId(), workCalUnitedMonthCommonDTO.getEqCtgDesc(), user.getCompNo()); 
    	
    	//담당자(정)
    	query.getCodeLikeQuery("b.main_mng_id", 
    	        "(SELECT emp_name FROM TAEMP WHERE emp_id = b.main_mng_id AND comp_no = b.comp_no)"
    	        , workCalUnitedMonthCommonDTO.getMainMngId(), workCalUnitedMonthCommonDTO.getMainMngName());
    	
    	//담당자(부)
    	query.getCodeLikeQuery("b.sub_mng_id", 
    	        "(SELECT emp_name FROM TAEMP WHERE emp_id = b.sub_mng_id AND comp_no = b.comp_no)"
    	        , workCalUnitedMonthCommonDTO.getSubMngId(), workCalUnitedMonthCommonDTO.getSubMngName());
    	
    	//설비
    	query.getCodeLikeQuery("b.equip_id", 
    	        "b.description+b.item_no", workCalUnitedMonthCommonDTO.getEquipId(), workCalUnitedMonthCommonDTO.getEquipDesc());
    	
    	//내/외자 구분
    	query.getSysCdQuery("b.plf_type", workCalUnitedMonthCommonDTO.getPlfTypeId(), workCalUnitedMonthCommonDTO.getPlfTypeDesc(), "PLF_TYPE", user.getCompNo(),user.getLangId());
    	
    	//작업형태
    	query.getSysCdQuery("x.pm_type", workCalUnitedMonthCommonDTO.getPmTypeId(), workCalUnitedMonthCommonDTO.getPmTypeDesc(), "x.wo_type+'_TYPE'", user.getCompNo(), user.getLangId());
    	
    	//작업종류
    	query.getSysCdQuery("x.wo_type", workCalUnitedMonthCommonDTO.getWoTypeId(), workCalUnitedMonthCommonDTO.getWoTypeDesc(), "WO_TYPE", user.getCompNo(), user.getLangId());
    	
    	//작업종류
    	query.getSysCdQuery("x.shift_type", workCalUnitedMonthCommonDTO.getShiftId(), workCalUnitedMonthCommonDTO.getShiftDesc(), "SHIFT_TYPE", user.getCompNo(), user.getLangId());
    	
    	//작업그룹 
    	query.getWkCtrLevelQuery("x.wkctr_id", workCalUnitedMonthCommonDTO.getWkCtrId(), workCalUnitedMonthCommonDTO.getWkCtrDesc(), user.getCompNo());
    	
    	//설비유형
    	query.getSysCdQuery("b.eqctg_type", workCalUnitedMonthCommonDTO.getEqCtgTypeId(), workCalUnitedMonthCommonDTO.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(), user.getLangId());
    	
    	//공장코드
    	query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", 
    			workCalUnitedMonthCommonDTO.getPlantId(), workCalUnitedMonthCommonDTO.getPlantDesc());

        //최신버전의 설비의 작업만 보여줌.
    	query.getAndQuery("b.is_last_version", "Y");
        
    	return query.toString();
    }
    
    private String getInsWhere(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO , User user)
    {        
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getStringEqualQuery("x.IS_DELETED", "N");
    	
    	if (!"".equals(workCalUnitedMonthCommonDTO.getCreatedPmInsId()))
    	{
    		query.getAndQuery("x.pminslist_id", workCalUnitedMonthCommonDTO.getCreatedPmInsId());
    		return query.toString();
    	}
    	
    	//날짜 전체 검색(리스트조회 사용)
    	String day = CommonUtil.getRowDateToNum(workCalUnitedMonthCommonDTO.getYyyymmdd());
    	query.append("  AND x.wkor_date LIKE '"+day+"%'						");
    	
    	
    	//법정설비여부
    	query.getLikeQuery("y.is_law_eq", workCalUnitedMonthCommonDTO.getIsLawEq());
    	
    	//상태
    	query.getSysCdQuery("x.pmsched_status",workCalUnitedMonthCommonDTO.getSchedType(), "", "PMSCHED_STATUS", user.getCompNo(), user.getLangId());
    	
    	//부서
    	query.getDeptLevelQuery("x.dept_id", workCalUnitedMonthCommonDTO.getDeptId(), workCalUnitedMonthCommonDTO.getDeptDesc(), user.getCompNo());
    	
    	//위치
    	query.getEqLocLevelQuery("y.eqloc_id", workCalUnitedMonthCommonDTO.getEqLocId(), workCalUnitedMonthCommonDTO.getEqLocDesc(), user.getCompNo());
    	
    	//담당자
    	query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
    			workCalUnitedMonthCommonDTO.getEmpId(), workCalUnitedMonthCommonDTO.getEmpDesc());
    	
    	//종류
    	query.getEqCtgLevelQuery("y.eqctg_id", workCalUnitedMonthCommonDTO.getEqCtgId(), workCalUnitedMonthCommonDTO.getEqCtgDesc(), user.getCompNo()); 
    	
    	//담당자(정)
    	query.getCodeLikeQuery("y.main_mng_id", 
    	        "(SELECT emp_name FROM TAEMP WHERE emp_id = y.main_mng_id AND comp_no = y.comp_no)"
    	        , workCalUnitedMonthCommonDTO.getMainMngId(), workCalUnitedMonthCommonDTO.getMainMngName());
    	
    	//담당자(부)
    	query.getCodeLikeQuery("y.sub_mng_id", 
    	        "(SELECT emp_name FROM TAEMP WHERE emp_id = y.sub_mng_id AND comp_no = y.comp_no)"
    	        , workCalUnitedMonthCommonDTO.getSubMngId(), workCalUnitedMonthCommonDTO.getSubMngName());
    	
    	//설비
    	query.getCodeLikeQuery("y.equip_id", 
    	        "y.description+y.item_no", workCalUnitedMonthCommonDTO.getEquipId(), workCalUnitedMonthCommonDTO.getEquipDesc());
    	
    	//내/외자 구분
    	query.getSysCdQuery("y.plf_type", workCalUnitedMonthCommonDTO.getPlfTypeId(), workCalUnitedMonthCommonDTO.getPlfTypeDesc(), "PLF_TYPE", user.getCompNo(),user.getLangId());
    	
    	//작업형태
    	query.getSysCdQuery("x.pm_type", workCalUnitedMonthCommonDTO.getPmTypeId(), workCalUnitedMonthCommonDTO.getPmTypeDesc(), "x.wo_type+'_TYPE'", user.getCompNo(), user.getLangId());
    	
    	//작업종류
    	query.getSysCdQuery("x.wo_type", workCalUnitedMonthCommonDTO.getWoTypeId(), workCalUnitedMonthCommonDTO.getWoTypeDesc(), "WO_TYPE", user.getCompNo(), user.getLangId());
    	
    	//작업종류
    	query.getSysCdQuery("x.shift_type", workCalUnitedMonthCommonDTO.getShiftId(), workCalUnitedMonthCommonDTO.getShiftDesc(), "SHIFT_TYPE", user.getCompNo(), user.getLangId());
    	
    	//작업그룹 
    	query.getWkCtrLevelQuery("x.wkctr_id", workCalUnitedMonthCommonDTO.getWkCtrId(), workCalUnitedMonthCommonDTO.getWkCtrDesc(), user.getCompNo());
    	
    	//설비유형
    	query.getSysCdQuery("y.eqctg_type", workCalUnitedMonthCommonDTO.getEqCtgTypeId(), workCalUnitedMonthCommonDTO.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(), user.getLangId());
    	
    	//공장코드
    	query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", 
    			workCalUnitedMonthCommonDTO.getPlantId(), workCalUnitedMonthCommonDTO.getPlantDesc());
    	
    	//최신버전의 설비의 작업만 보여줌.
    	query.getAndQuery("y.is_last_version", "Y");
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
		
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              	");
        query.append("SELECT															");
        query.append("       COUNT(1)                                           		");
        query.append("FROM TAWORKORDER x LEFT OUTER JOIN TAWOEQUIP a                                    ");
        query.append("ON x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id                                ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT b                                                     ");
        query.append("ON a.comp_no = b.comp_no AND a.equip_id = b.equip_id                              ");
        query.append("WHERE 1=1															");
        query.append(this.getWhere(workCalUnitedMonthCommonDTO, maWoResultMstrCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}

	@Override
	public String findPmiTotalCount(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO,MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              					");
		query.append("SELECT					");
		query.append("       COUNT(1)           ");
		query.append("FROM  TAPMINSLIST x LEFT OUTER JOIN TAEQUIPMENT y                                                             ");
        query.append("ON x.comp_no = y.comp_no AND x.equip_id = y.equip_id                                                          ");
        query.append("WHERE 1=1					");
    	query.append(this.getInsWhere(workCalUnitedMonthCommonDTO, user));
    	
		List resultList=  getJdbcTemplate().queryForList(query.toString());
		return QuerySqlBuffer.listToString(resultList);
	}
}