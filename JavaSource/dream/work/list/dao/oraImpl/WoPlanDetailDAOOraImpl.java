package dream.work.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.list.dao.WoPlanDetailDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;

/**
 * 작업계획목록 - 상세 dao
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="woPlanDetailDAOTarget"
 * @spring.txbn id="woPlanDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WoPlanDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WoPlanDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @return
     */
    public WoPlanDetailDTO findDetail(WoPlanCommonDTO woPlanCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                            												");
        query.append("		 x.wkor_id                                                											AS WKORID           	");
        query.append("     , x.wo_no                                                    										AS WONO            		");
        query.append("     , x.woplan_status                                                									AS WOPLANSTATUSID       ");
        query.append("     , SFACODE_TO_DESC(x.woplan_status, 'WOPLAN_STATUS', 'SYS', '', '"+ user.getLangId() +"')				AS WOPLANSTATUSDESC		");
        query.append("     , x.wo_type                                                											AS WOTYPEID        		");
        query.append("     , SFACODE_TO_DESC(x.wo_type, 'WO_TYPE', 'SYS', '', '"+ user.getLangId() +"')   						AS WOTYPEDESC      		");
        query.append("     , x.pm_type                                                											AS PMTYPEID        		");
        query.append("     , SFACODE_TO_DESC(x.pm_type, x.wo_type||'_TYPE', 'SYS', '', '"+ user.getLangId() +"')				AS PMTYPEDESC			");
        query.append("     , x.eqloc_id                                               											AS EQLOCID            	");
        query.append("     , CASE WHEN (SELECT count(*)                                                     											");
        query.append("                    FROM TAWOEQUIP a                                                												");
        query.append("                   WHERE a.comp_no = x.comp_no                                        											");
        query.append("                     AND a.wkor_id = x.wkor_id                                        											");
        query.append("                 ) > 0                                                             												");
        query.append("        	  THEN (SELECT c.full_desc                                                               	     		    			");
        query.append("         			  FROM TAEQLOC c                                                                								");
        query.append("         			 WHERE x.comp_no  = c.comp_no                                                									");
        query.append("            		   AND b.eqloc_id = c.eqloc_id)                                             									");
        query.append("        	  ELSE (SELECT a.full_desc                                                    											");
        query.append("              	  FROM TAEQLOC a                                                        										");
        query.append("              	 WHERE a.comp_no  = x.comp_no                                            										");
        query.append("                	   AND a.eqloc_id = x.eqloc_id)                                        											");
        query.append("        	   END                                                   										AS EQLOCDESC        	");
        query.append("     , x.description                                            											AS WKORDESC        		");
        query.append("     , b.equip_id                                                 										AS EQUIPID          	");
        query.append("     , b.description                                             											AS EQUIPDESC        	");
        query.append("     , a.eqasmb_id                                             											AS EQASMBID        		");
        query.append("     , (SELECT aa.full_desc                                             								       						");
        query.append("     		FROM TAEQASMB aa                                             									      					");
        query.append("     	   WHERE aa.comp_no   = x.comp_no                                             						       					");
        query.append("     		 AND aa.eqasmb_id = a.eqasmb_id)                                             					AS EQASMBDESC        	");
        query.append("     , x.wkor_date                                               											AS WKORDATE        		");
        query.append("     , x.plant    																						AS PLANT				");
        query.append("     , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant) 			AS PLANTDESC			");
        query.append("     , x.dept_id                                                											AS DEPTID      			");
        query.append("     , (SELECT description                                                            											");
        query.append("          FROM TADEPT                                                                												");
        query.append("         WHERE comp_no = x.comp_no                                                    											");
        query.append("           AND dept_id = x.dept_id)                            											AS DEPTDESC        		");
        query.append("     , x.wkctr_id                                                											AS WKCTRID          	");
        query.append("     , (SELECT description                                                            											");
        query.append("     	    FROM TAWKCTR                                                                											");
        query.append("         WHERE comp_no  = x.comp_no                                                    											");
        query.append("           AND wkctr_id = x.wkctr_id)                              										AS WKCTRDESC        	");
        query.append("     , x.emp_id                                                											AS EMPID            	");
        query.append("     , (SELECT emp_name                                                            												");
        query.append("          FROM TAEMP                                                                    											");
        query.append("         WHERE comp_no = x.comp_no                                                    											");
        query.append("           AND emp_id  = x.emp_id)                                										AS EMPDESC          	");
        query.append("     , x.shift_type                                                										AS SHIFTTYPEID    		");
        query.append("     , SFACODE_TO_DESC(x.shift_type, 'SHIFT_TYPE', 'SYS', '', '"+ user.getLangId() +"')     		 		AS SHIFTTYPEDESC 		");
        query.append("     , x.sub_emp_id                                            											AS SUBEMPID     		");
        query.append("     , (SELECT emp_name                                                            												");
        query.append("          FROM TAEMP                                                                    											");
        query.append("         WHERE comp_no = x.comp_no                                                    											");
        query.append("           AND emp_id  = x.sub_emp_id)                            										AS SUBEMPDESC  			");
        query.append("     , x.self_vendor_type                                        											AS SELFVENDORTYPE   	");
        query.append("     , SFACODE_TO_DESC(x.self_vendor_type, 'SELF_VENDOR_TYPE', 'SYS', '', '"+ user.getLangId() +"')		AS SELFVENDORTYPEDESC	");
        query.append("     , x.start_date                                            											AS STARTDATE        	");
        query.append("     , x.start_time                                            											AS STARTTIME       		");
        query.append("     , x.end_date                                                											AS ENDDATE          	");
        query.append("     , x.end_time                                                											AS ENDTIME          	");
        query.append("     , x.work_time                                               											AS WORKTIME        		");
        query.append("     , x.vendor_id                                               											AS VENDORID        		");
        query.append("     , SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)   												AS VENDORDESC  			");
        query.append("     , x.tot_amt                                                											AS TOTAMT           	");
        query.append("     , (SELECT user_name                                                             												");
        query.append("     		FROM TAUSER                                                                												");
        query.append("         WHERE comp_no = x.comp_no                                                   												");
        query.append("     	 	 AND user_id = x.complete_id)                          											AS CLOSEBY          	");
        query.append("     , x.perform                                                											AS PERFORM           	");
        query.append("     , x.wo_gen_type                                            											AS WOGENTYPE        	");
        query.append("     , x.p_wkor_id                                              											AS PARENTWOID        	");
        query.append("     , x.complete_date                                            										AS CLOSEDATE        	");
        query.append("     , (SELECT wo_no FROM TAWOPLAN aa WHERE aa.comp_no = x.comp_no AND aa.wkor_id = x.p_wkor_id)    		AS PARENTWONO    		");
        query.append("     , (SELECT c.pmcalibstdtp_id                                                    												");
        query.append("     		FROM TAEQTOOL c                                                            												");
        query.append("         WHERE b.comp_no  = c.comp_no                                                												");
        query.append("           AND b.equip_id = c.equip_id)                            										AS PMCALIBSTDTPID  		");
        query.append("     , x.courselist_id                                          											AS COURSELISTID    		");
        query.append("     , (SELECT description FROM TACOURSELIST WHERE courselist_id = x.courselist_id)    					AS TRAINDESC    		");
        query.append("	FROM TAWOPLAN x INNER JOIN TAWOEQUIP a                																			");
        query.append("					   ON x.wkor_id = a.wkor_id      																				");
        query.append("					  AND x.comp_no = a.comp_no     																				");
        query.append("					INNER JOIN TAEQUIPMENT b        																				");
        query.append("					   ON a.comp_no  = b.comp_no      																				");
        query.append("					  AND a.equip_id = b.equip_id           																		");
        query.getStringEqualQuery("b.IS_DELETED", "N");
        query.append(" WHERE 1 = 1																														");
        query.append("   AND x.wkor_id = ?																												");
        query.append("   AND x.comp_no = ?																												");
    
        Object[] objects = new Object[] {
        		 woPlanCommonDTO.getWkOrId()
        	   , woPlanCommonDTO.getCompNo()
    	};
        
        WoPlanDetailDTO woPlanDetailDTO = (WoPlanDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WoPlanDetailDTO()));
        
        return woPlanDetailDTO;
    }
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanDetailDTO
     * @return
     */
    public int insertDetail(WoPlanDetailDTO woPlanDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAWOPLAN (							    			");
    	query.append("		comp_no				, wkor_id				, woplan_status	");
    	query.append("	  , description			, wo_type				, dept_id		");
    	query.append("	  ,	pm_type				, emp_id				, start_date	");
    	query.append("	  , start_time			, end_date				, end_time		");
    	query.append("	  , work_time			, perform				, wo_no			");
    	query.append("	  , vendor_id			, self_vendor_type		, wo_gen_type	");
    	query.append("    , p_wkor_id			, shift_type			, wkor_date		");
    	query.append("    , tot_amt				, eqloc_id				, wkctr_id		");
    	query.append("    ,	courselist_id		, vendor_desc	        , sub_emp_id    ");
    	query.append("    , plant           											");
    	query.append("	)	VALUES	(													");
    	query.append("	    ?					, ?						, ?				");
    	query.append("	  , ?					, ?						, ?				");
    	query.append("	  , ?					, ?						, ?				");
    	query.append("	  , ?					, ?						, ?				");
    	query.append("	  , ?					, ?						, ?				");
    	query.append("	  , ?					, ?						, ?				");
    	query.append("    , ?             		, ?						, ?				");
    	query.append("    , ?					, ?						, ?				");
    	query.append("    , ?					, ?    					, ? 		    ");
    	query.append("    , ?             		                            			");
    	query.append("	)																");
    	
    	Object[] objects = new Object[] {
    			 woPlanDetailDTO.getCompNo()
	   		   , woPlanDetailDTO.getWkOrId()
	   		   , woPlanDetailDTO.getWoPlanStatusId()
	   		   , woPlanDetailDTO.getWkOrDesc()
	   		   , woPlanDetailDTO.getWoTypeId()
	   		   , woPlanDetailDTO.getDeptId()
	   		   , woPlanDetailDTO.getPmTypeId()
	   		   , woPlanDetailDTO.getEmpId()
	   		   , woPlanDetailDTO.getStartDate()
	   		   , woPlanDetailDTO.getStartTime()
	   		   , woPlanDetailDTO.getEndDate()
	   		   , woPlanDetailDTO.getEndTime()
	   		   , woPlanDetailDTO.getWorkTime()
	   		   , woPlanDetailDTO.getPerform()
	   		   , woPlanDetailDTO.getWoNo()
	   		   , woPlanDetailDTO.getVendorId()
	   		   , woPlanDetailDTO.getSelfVendorType()
	   		   , "WORSLT"
	   		   , woPlanDetailDTO.getParentWoId()
	   		   , woPlanDetailDTO.getShiftTypeId()
	   		   , woPlanDetailDTO.getWkorDate()
	   		   , woPlanDetailDTO.getTotAmt()
	   		   , woPlanDetailDTO.getEqLocId()
	   		   , woPlanDetailDTO.getWkCtrId()
	   		   , woPlanDetailDTO.getCourseListId()
	   		   , woPlanDetailDTO.getVendorDesc()
	   		   , woPlanDetailDTO.getSubEmpId()
	   		   , woPlanDetailDTO.getPlant()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public String selectWoequipCnt(WoPlanDetailDTO woPlanDetailDTO, User user){
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT 												");
        query.append("		 COUNT(*)                    					");
        query.append("	FROM TAWOEQUIP                    					");
        query.append(" WHERE 1=1                                         	");
        query.append("	 AND comp_no = '"+ woPlanDetailDTO.getCompNo() +"'  ");
        query.append(" 	 AND wkor_id = "+ woPlanDetailDTO.getWkOrId() +"	");
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    	
    }
    
    public int insertWoequip(WoPlanDetailDTO woPlanDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
        
        if(!"".equals(woPlanDetailDTO.getEquipId())&&woPlanDetailDTO.getEquipId()!=null){
    		query.append("INSERT INTO TAWOEQUIP(																			");
        	query.append("	comp_no				, woequip_id					, wkor_id				, equip_id			");
        	query.append(", eqctg_id      		, description       			, work_time   			, eqasmb_id			");
        	query.append("	)  VALUES	(																					");
        	query.append("	?					, SQAWOEQUIP_ID.NEXTVAL			,?						,? 					");
        	query.append("	,(SELECT eqctg_id FROM TAEQUIPMENT WHERE comp_no = ? and equip_id = ?)		, ?		,?		,?	");
        	query.append("	)																								");
        	
    		Object[] objects = new Object[] {
    				 woPlanDetailDTO.getCompNo()
    			   , woPlanDetailDTO.getWkOrId()
    			   , woPlanDetailDTO.getEquipId()
    			   , woPlanDetailDTO.getCompNo()
    			   , woPlanDetailDTO.getEquipId()
    			   , woPlanDetailDTO.getEquipDesc()
    			   , woPlanDetailDTO.getWorkTime()
    			   , woPlanDetailDTO.getEqAsmbId()
        	};
    		
            rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
        }
    	return rtnValue;
    }
    
    public int updateWoequip(WoPlanDetailDTO woPlanDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        if(!"".equals(woPlanDetailDTO.getEquipId())&&woPlanDetailDTO.getEquipId()!=null){
            query.append("UPDATE TAWOEQUIP          																	");
            query.append("	 SET equip_id 		= ?         															");
            query.append("	   , description	= ?         															");
            query.append("	   , eqasmb_id 		= ?         															");
            query.append("	   , eqctg_id 		= (SELECT eqctg_id FROM TAEQUIPMENT WHERE comp_no = ? and equip_id = ?)	");
            query.append("	   , work_time 		= ?        																");
            query.append("WHERE comp_no			= ?                       												");
            query.append("  AND wkor_id 		= ?                       												");
            
            Object[] objects = new Object[] {
                     woPlanDetailDTO.getEquipId()
                   , woPlanDetailDTO.getEquipDesc()
                   , woPlanDetailDTO.getEqAsmbId()
                   , woPlanDetailDTO.getCompNo()
                   , woPlanDetailDTO.getEquipId()
                   , woPlanDetailDTO.getWorkTime()
                   , woPlanDetailDTO.getCompNo()
                   , woPlanDetailDTO.getWkOrId()
            };
            
            rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
        }
        return rtnValue;
    }
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanDetailDTO
     * @return
     */
    public int updateDetail(WoPlanDetailDTO woPlanDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWOPLAN SET		    	");
    	query.append("		description			= ?		");	
    	query.append("	  , wo_type				= ?		");
    	query.append("	  , woplan_status		= ?		");
    	query.append("	  , dept_id				= ?		");
    	query.append("	  , pm_type				= ?		");
    	query.append("	  , emp_id				= ?		");
    	query.append("	  , start_date			= ?		");
    	query.append("	  , start_time			= ?		");
    	query.append("	  , end_date			= ?		");
    	query.append("	  , end_time			= ?		");
    	query.append("	  , work_time			= ?		");
    	query.append("	  , self_vendor_type	= ?		");
    	query.append("	  , vendor_id			= ?		");
    	query.append("	  , vendor_desc			= ?		");
    	query.append("	  , perform				= ?		");
    	query.append("	  , wkor_date			= ?		");
    	query.append("	  , tot_amt				= ?		");
    	query.append("	  , shift_type			= ?		");
    	query.append("	  , p_wkor_id			= ?		");
    	query.append("	  , eqloc_id			= ?		");
    	query.append("	  , wkctr_id			= ?		");
    	query.append("    , courselist_id       = ?    	");
        query.append("    , sub_emp_id          = ?     ");
        query.append("    , plant          		= ?     ");
    	query.append("WHERE wkor_id	 			= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
	   			 woPlanDetailDTO.getWkOrDesc()
	   		   , woPlanDetailDTO.getWoTypeId()
	   		   , woPlanDetailDTO.getWoPlanStatusId()
	   		   , woPlanDetailDTO.getDeptId()
	   		   , woPlanDetailDTO.getPmTypeId()
	   		   , woPlanDetailDTO.getEmpId()
	   		   , woPlanDetailDTO.getStartDate()
	   		   , woPlanDetailDTO.getStartTime()
	   		   , woPlanDetailDTO.getEndDate()
	   		   , woPlanDetailDTO.getEndTime()
	   		   , woPlanDetailDTO.getWorkTime()
	   		   , woPlanDetailDTO.getSelfVendorType()
	   		   , woPlanDetailDTO.getVendorId()
	   		   , woPlanDetailDTO.getVendorDesc()
	   		   , woPlanDetailDTO.getPerform()
	   		   , woPlanDetailDTO.getWkorDate()
	   		   , woPlanDetailDTO.getTotAmt()
	   		   , woPlanDetailDTO.getShiftTypeId()
	   		   , woPlanDetailDTO.getParentWoId()
	   		   , woPlanDetailDTO.getEqLocId()
	   		   , woPlanDetailDTO.getWkCtrId()
	   		   , woPlanDetailDTO.getCourseListId()
	   		   , woPlanDetailDTO.getSubEmpId()
	   		   , woPlanDetailDTO.getPlant()
	   		   , woPlanDetailDTO.getWkOrId()
	   		   , woPlanDetailDTO.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanDetailDTO
     * @return
     */
    public int completeDetail(WoPlanDetailDTO woPlanDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWOPLAN SET           								");
        query.append("		woplan_status       = 'PPC'  						");
        query.append("    , complete_id         = ?	    							");
        query.append("    , complete_date       = TO_CHAR(SYSDATE,'yyyymmdd')		");
        query.append(" WHERE wkor_id         	= ?     							");
        query.append("   AND comp_no         	= ?     							");
    	
        Object[] objects = new Object[] {
	             user.getUserId()
	           , woPlanDetailDTO.getWkOrId()
	           , user.getCompNo()
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }

    public String checkPoint(WoPlanDetailDTO woPlanDetailDTO,User user) {

		QueryBuffer query = new QueryBuffer();
		query.append("SELECT NVL(val,0) FROM (select to_char(sum(decode(decode(y.check_type,'SEN',x.pm_point_rslt_status,'VAL',x.result_value),null,1,0))) val");
		query.append("from tawopoint x, tapmpoint y					");
		query.append("where x.comp_no = y.comp_no					");
		query.append("and x.pm_point_id = y.pm_point_id				");
		query.getStringEqualQuery("x.comp_no", user.getCompNo());
		query.getStringEqualQuery("x.wkor_id", woPlanDetailDTO.getWkOrId());
		query.append(")												");
		
		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    // TAWOPLAN에서 TAWORKORDER로 데이터 INSERT
    public int insertWoPlan(WoPlanDetailDTO woPlanDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("INSERT INTO TAWORKORDER (                               								");
        query.append("		  comp_no			, wkor_id		, wo_no             						");
        query.append("    	, eqloc_id			, p_wkor_id		, description       						");
        query.append("    	, wo_status			, wo_type		, pm_type           						");
        query.append("    	, wo_gen_type		, wkor_date		, shift_type        						");
        query.append("    	, tot_amt			, start_date	, start_time        						");
        query.append("    	, end_date			, end_time		, work_time									");
        query.append("    	, self_vendor_type	, vendor_id		, vendor_desc     							");
        query.append("    	, courselist_id 	, dept_id		, emp_id            						");
        query.append("    	, sub_emp_id		, wkctr_id		, perform           						");
        query.append("    	, pm_id				, pmsched_id	, wongpoint_id								");
        query.append("    	, upd_date			, upd_by		, plant 		    						");
        query.append("    	, eqasmb_id 		    														");
        query.append(")SELECT comp_no			, wkor_id		, wo_no             						");
        query.append("      , eqloc_id			, p_wkor_id		, description       						");
        query.append("      , 'P'				, wo_type		, pm_type           						");
        query.append("      , wo_gen_type		, wkor_date		, shift_type        						");
        query.append("      , tot_amt			, start_date	, start_time        						");
        query.append("      , end_date			, end_time		, work_time     							");
        query.append("      , self_vendor_type	, vendor_id		, vendor_desc     							");
        query.append("      , courselist_id		, dept_id		, emp_id            						");
        query.append("      , sub_emp_id		, wkctr_id		, perform           						");
        query.append("      , pm_id				, pmsched_id	, wongpoint_id      						");
        query.append("      , upd_date			, upd_by		, plant             						");
        query.append("    	, (SELECT x.eqasmb_id FROM TAWOEQUIP x WHERE x.comp_no = ? AND x.wkor_id = ?)	");
        query.append("	 FROM TAWOPLAN x                                       								");
        query.append("  WHERE x.comp_no = ?                                   								");
        query.append("    AND x.wkor_id = ?                                   								");

        Object[] objects = new Object[] {
        		 user.getCompNo()
               , woPlanDetailDTO.getWkOrId()
          	   , user.getCompNo()
               , woPlanDetailDTO.getWkOrId()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    // TAWOPLANCRAFT에서 TAWOCRAFT로 데이터 INSERT (갯수만큼)
    public int insertWoPlanCraft(WoPlanDetailDTO woPlanDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        int craftCnt = 0;
        String woCraftId = "";
        
        // 갯수 알아오기
        query.append("SELECT COUNT(1)       ");
        query.append("FROM TAWOPLANCRAFT x  ");
        query.append("WHERE x.wkor_id = ?   ");

        Object[] cntObjects = new Object[] {
                woPlanDetailDTO.getWkOrId()
        };
        
        craftCnt = Integer.parseInt(QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),cntObjects)));

        // 등록된 작업자가 0명이 아니면
        if(craftCnt>=1)
        {
            // 등록된 작업자명수만큼 INSERT한다.
            for(int i=1; i<=craftCnt; i++)
            {
                // 시퀀스 가져오기
                query.setClear();
                query.append("SELECT SQAWOCRAFT_ID.NEXTVAL  ");
                query.append("FROM DUAL                     ");
                
                woCraftId = QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
                
                // TAWOPLANCRAFT에서 TAWOCRAFT로 데이터 INSERT (갯수만큼)
                query.setClear();
                query.append("INSERT INTO TAWOCRAFT (comp_no, wocraft_id, wkor_id, emp_id, start_date, start_time, end_date, end_time, work_time, REMARK)                ");
                query.append("SELECT comp_no, ?, wkor_id, emp_id, start_date, start_time, end_date, end_time, work_time, REMARK        ");
                query.append("FROM (                                ");
                query.append("    SELECT ROWNUM AS rno, comp_no, wkor_id, emp_id, start_date, start_time, end_date, end_time, work_time, REMARK        ");
                query.append("    FROM TAWOPLANCRAFT x              ");
                query.append("    WHERE x.wkor_id = ?               ");
                query.append("    ORDER BY 1                        ");
                query.append("     )                                ");
                query.append("WHERE rno = '"+i+"'                   ");
                
                Object[] objects = new Object[] {
                        woCraftId
                      , woPlanDetailDTO.getWkOrId()
                };
                
                rtnValue += getJdbcTemplate().update(query.toString(), objects);
            }
        }
        
        return rtnValue;
    }
    // TAWOPLANPARTS에서 TAWOPARTS로 데이터 INSERT (갯수만큼)
    public int insertWoPlanParts(WoPlanDetailDTO woPlanDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        int partCnt = 0;
        String woPartId = "";
        
        // 갯수 알아오기
        query.append("SELECT COUNT(1)      ");
        query.append("FROM TAWOPLANPARTS x ");
        query.append("WHERE x.wkor_id = ?  ");

        Object[] cntObjects = new Object[] {
                woPlanDetailDTO.getWkOrId()
        };
        
        partCnt = Integer.parseInt(QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),cntObjects)));
        // 등록된 부품이 0개가 아니면
        if(partCnt>=1)
        {
            // 등록된 부품갯수만큼 INSERT한다.
            for(int i=1; i<=partCnt; i++)
            {
                // 시퀀스 가져오기
                query.setClear();
                query.append("SELECT SQAWOPART_ID.NEXTVAL   ");
                query.append("FROM DUAL                     ");
                
                woPartId = QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
                
                // TAWOPLANPARTS에서 TAWOPARTS로 데이터 INSERT (갯수만큼)
                query.setClear();
                query.append("INSERT INTO TAWOPARTS (comp_no, wopart_id, wkor_id, wcode_id, part_id, part_grade, use_qty, REMARK)       ");
                query.append("SELECT comp_no, ?, wkor_id, wcode_id, part_id, part_grade, use_qty, REMARK                ");
                query.append("FROM (                                        ");
                query.append("    SELECT ROWNUM AS rno, comp_no, wkor_id, wcode_id, part_id, part_grade, use_qty, REMARK       ");
                query.append("    FROM TAWOPLANPARTS x                      ");
                query.append("    WHERE x.wkor_id = ?                       ");
                query.append("    ORDER BY 1                                ");
                query.append("     )                                        ");
                query.append("WHERE rno = '"+i+"'                           ");
                
                Object[] objects = new Object[] {
                        woPartId
                      , woPlanDetailDTO.getWkOrId()
                };
                
                rtnValue += getJdbcTemplate().update(query.toString(), objects);
            }
        }
        
        return rtnValue;
    }
    @Override
    public int updateReqStatus(WoPlanDetailDTO woPlanDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("UPDATE TAWOREQ SET           								");
        query.append("  woreq_status    = 'WRK'    								");
        query.append("WHERE woreq_id In (SELECT woreq_id 						");
        query.append("                     FROM TAWOREQRES WHERE wkor_id = ?	");
        query.getAndQuery("woreqres_id", woPlanDetailDTO.getWoReqResId());
        query.append("                      AND comp_no = ?)					");
        query.append("  AND comp_no     = ?     								");
        
        Object[] objects = new Object[] {
                woPlanDetailDTO.getWkOrId(),
                user.getCompNo(),
                user.getCompNo()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    @Override
    public int updateResStatus(WoPlanDetailDTO woPlanDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("UPDATE TAWOREQRES SET         ");
        query.append("  wores_status        = 'WRK' ");
        query.append("WHERE wkor_id         = ?     ");
        query.getAndQuery("woreqres_id", woPlanDetailDTO.getWoReqResId());
        query.append("  AND comp_no         = ?     ");
        
        Object[] objects = new Object[] {
                woPlanDetailDTO.getWkOrId(),
                user.getCompNo()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    @Override
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAWOPLAN SET                    ");
        query.append("       woplan_status   = CASE WHEN woplan_status='C' THEN 'C' ELSE ? END             ");
        query.append("WHERE  wkor_id       = ?             ");
        query.append("AND  comp_no       = ?             ");
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    @Override
    public String woPlanCheck(WoPlanCommonDTO woPlanCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(1)       ");
        query.append("FROM TAWOPLAN x       ");
        query.append("WHERE 1=1     ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x. wkor_id", woPlanCommonDTO.getWkOrId());
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}