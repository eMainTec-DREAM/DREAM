package dream.work.pm.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmListEquipDetailDAO;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 사용자재 상세 dao
 * @author  kim21017
 * @version $Id: WorkPmListEquipDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workPmListEquipDetailDAOTarget"
 * @spring.txbn id="workPmListEquipDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmListEquipDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmListEquipDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public WorkPmListEquipDetailDTO findDetail(String pmId, String pmEquipId, User user)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("SELECT												");
        query.append("       x.pmequip_id 					pmEquipId		");
        query.append("       ,x.equip_id 					equipId		    ");
        query.append("       ,(SELECT item_no								");
        query.append("          FROM TAEQUIPMENT							");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND equip_id = x.equip_id) itemNo		    ");
        query.append("       ,x.eqasmb_id                   eqAsmbId        ");
        query.append("       ,(SELECT full_desc                             ");
        query.append("          FROM TAEQASMB                               ");
        query.append("         WHERE comp_no = x.comp_no                    ");
        query.append("           AND eqasmb_id = x.eqasmb_id) eqAsmbDesc    ");
        query.append("       ,(SELECT (SELECT full_desc 					");
        query.append("                 FROM TAEQLOC 						");
        query.append("                 WHERE eqloc_id = a.eqloc_id			");
        query.append("                   AND comp_no = a.comp_no)			");
        query.append("          FROM TAEQUIPMENT a							");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.equip_id = x.equip_id) eqLocDesc	    ");
        query.append("       ,(SELECT description							");
        query.append("          FROM TAEQUIPMENT							");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND equip_id = x.equip_id) equipDesc		");
        query.append("       ,x.description 					workPart	");
        query.append("       ,x.step_num                      stepNum       ");
        query.append("       ,x.is_active                       isActive    ");
        query.append("       ,x.remark                          remark      ");
        query.append("       ,x.init_wrk_date        as oldInitWrkDate      ");  // 사용자 등록일자(시작일)   -- 수정가능
        query.append("       ,x.init_wrk_date        as initWrkDate         ");  // 사용자 등록일자(시작일)   -- 수정가능
        query.append("       ,x.last_sch_date        as lastSchDate         ");   // 스케쥴 생성된 날짜(최종)  -- 수정불가능
        query.append("       ,x.next_sch_date        as nextSchDate         ");   // 스케쥴 보정된 날짜.          -- 수정불가능
        query.append("       ,x.last_cplt_date       as lastCpltDate        ");  // 최종 작업일자
        query.append("       ,x.next_plan_date       as nextPlanDate        ");  // 다음 예정일자
        query.append("		 ,(SELECT a.eqctg_Type                          ");
        query.append("         FROM TAEQUIPMENT a                           ");
        query.append("         WHERE a.comp_no = x.comp_no                  ");
        query.append("           AND a.equip_id = x.equip_id) as eqctgType  ");
        query.append("       , (SELECT b.description FROM TAEQUIPMENT b WHERE b.comp_no = x.comp_no AND b.equip_id = (SELECT a.p_equip_id FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id) ) pequipDesc		");
        query.append("       , (SELECT c.description FROM TADEPT c WHERE c.comp_no = x.comp_no AND c.dept_id = (SELECT b.usage_dept FROM TAEQUIPMENT b WHERE b.comp_no = x.comp_no AND b.equip_id = (SELECT a.p_equip_id FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id) ) ) pequipUsaDeptDesc		");
        query.append("FROM   TAPMEQUIP x	    							");
        query.append("WHERE	 x.pmequip_id 		= ?			                ");
        query.append("  AND  x.comp_no			= ?				            ");
        
        Object[] objects = new Object[]{
        		pmEquipId
        		,user.getCompNo()
        };
    
        WorkPmListEquipDetailDTO workPmListEquipDetailDTO = 
        		(WorkPmListEquipDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new WorkPmListEquipDetailDTO()));
        
        return workPmListEquipDetailDTO;
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListEquipDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPMEQUIP SET				");
    	query.append("     equip_id				= ?	    ");
    	query.append("    ,eqasmb_id            = ?     ");
    	query.append("    ,description			= ?	    ");
    	query.append("    ,step_num             = ?     ");
    	query.append("    ,is_active            = ?     ");
    	query.append("    ,remark               = ?     ");
    	query.append("    ,init_wrk_date        = ?     ");
    	query.append("WHERE pmequip_id		    = ?	    ");
    	query.append("  AND comp_no			    = ?		");
    	
    	Object[] objects = new Object[] {
    			workPmListEquipDetailDTO.getEquipId()
    			,workPmListEquipDetailDTO.getEqAsmbId()
    			,workPmListEquipDetailDTO.getWorkPart()
    			,workPmListEquipDetailDTO.getStepNum()
    			,workPmListEquipDetailDTO.getIsActive()
    			,workPmListEquipDetailDTO.getRemark()
    			,workPmListEquipDetailDTO.getInitWrkDate().replaceAll("-", "")
    			,workPmListEquipDetailDTO.getPmEquipId()
    			,user.getCompNo()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    public int deletePmSchedulePmEQuip(String compNo, String pmEquipId)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("begin                                                          ");
        query.append("SP_PM_MAKE_SCHEDPMEQUIP_DELETE(                                     ");
        query.append("                  '"+compNo+"'                                 ");
        query.append("                 ,'"+pmEquipId+"'                                   ");
        query.append("                 );                                            ");
        query.append("end;                                                           ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
    
    public int updateLastSchedDate(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPMEQUIP SET				");
    	query.append("	   last_sch_date		= ?	    ");
    	query.append("    ,next_sch_date        = ?     ");
    	query.append("WHERE pmequip_id		    = ?	    ");
    	query.append("  AND comp_no			    = ?		");
    	
    	Object[] objects = new Object[] {
    			workPmListEquipDetailDTO.getInitWrkDate().replaceAll("-", "")
    			,workPmListEquipDetailDTO.getInitWrkDate().replaceAll("-", "")
    			,workPmListEquipDetailDTO.getPmEquipId()
    			,user.getCompNo()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    
    
    public int updateSchedEquipDetail(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	
    	query.append(" update  TAPMINSLIST set                                    ");
    	query.append("     equip_id = ?                                           ");
    	query.append("  WHERE COMP_NO = ?                                         ");
    	query.append("       and pminssched_id in (select pminssched_id           ");
    	query.append("                            from TAPMINSSCHED               ");
    	query.append("                            where     comp_no = ?           ");
    	query.append("                                 and pmequip_id = ?         ");
    	query.append("                                 and pmsched_status != 'C'  ");
    	query.append("                                 AND plan_date >= TO_CHAR(SYSDATE,'YYYYMMDD')          ");
    	query.append("                             )                              ");
    	
    	Object[] objects = new Object[] {
    			workPmListEquipDetailDTO.getEquipId()
    			,user.getCompNo()
    			,user.getCompNo()
    			,workPmListEquipDetailDTO.getPmEquipId()
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	
    	query.setClear();
    	query.append("update TAPMINSSCHED set              ");
    	query.append("    equip_id = ?                     ");
    	query.append("where comp_no = ?                    ");
    	query.append("    and pmequip_id = ?               ");
    	query.append("    and pmsched_status != 'C'        ");
    	query.append("    AND plan_date >= TO_CHAR(SYSDATE,'YYYYMMDD')          ");
    	objects = new Object[] {
    			workPmListEquipDetailDTO.getEquipId()
    			,maPmMstrCommonDTO.getCompNo()
    			,workPmListEquipDetailDTO.getPmEquipId()
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	query.setClear();
    	query.append(" update  TAPMINSDLIST set                                    ");
    	query.append("     equip_id = ?                                           ");
    	query.append("  WHERE COMP_NO = ?                                         ");
    	query.append("       and pminsdsched_id in (select pminsdsched_id           ");
    	query.append("                            from TAPMINSDSCHED               ");
    	query.append("                            where     comp_no = ?           ");
    	query.append("                                 and pmequip_id = ?         ");
    	query.append("                                 and pmsched_status != 'C'  ");
    	query.append("                                 AND plan_date >= TO_CHAR(SYSDATE,'YYYYMMDD')          ");
    	query.append("                             )                              ");
    	
    	objects = new Object[] {
    			workPmListEquipDetailDTO.getEquipId()
    			,maPmMstrCommonDTO.getCompNo()
    			,maPmMstrCommonDTO.getCompNo()
    			,workPmListEquipDetailDTO.getPmEquipId()
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	
    	query.setClear();
    	query.append("update TAPMINSDSCHED set              ");
    	query.append("    equip_id = ?                     ");
    	query.append("where comp_no = ?                    ");
    	query.append("    and pmequip_id = ?               ");
    	query.append("    and pmsched_status != 'C'        ");
    	query.append("    AND plan_date >= TO_CHAR(SYSDATE,'YYYYMMDD')          ");
    	objects = new Object[] {
    			workPmListEquipDetailDTO.getEquipId()
    			,maPmMstrCommonDTO.getCompNo()
    			,workPmListEquipDetailDTO.getPmEquipId()
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	query.setClear();
    	query.append(" update  TAWOEQUIP set                                       ");
    	query.append("     equip_id = ?                                            ");
    	query.append("  WHERE COMP_NO = ?                                         ");
    	query.append("       AND WKOR_ID  IN (SELECT WKOR_ID                      ");
    	query.append("                        FROM TAWORKORDER                    ");
    	query.append("                        WHERE COMP_NO = ?                                  ");
    	query.append("                            and pmsched_id in (select pmsched_id            ");
    	query.append("                                               from TAPMSCHED                ");
    	query.append("                                               where     comp_no = ?            ");
    	query.append("                                                    and pmequip_id = ?          ");
    	query.append("                                                    and pmsched_status != 'C'   ");
    	query.append("                                                    AND plan_date >= TO_CHAR(SYSDATE,'YYYYMMDD')           ");
    	query.append("                                                )                                                           ");
    	query.append("                       )                     ");

    	
    	objects = new Object[] {
    			workPmListEquipDetailDTO.getEquipId()
    			,maPmMstrCommonDTO.getCompNo()
    			,maPmMstrCommonDTO.getCompNo()
    			,maPmMstrCommonDTO.getCompNo()
    			,workPmListEquipDetailDTO.getPmEquipId()
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	
    	
    	query.setClear();
    	query.append("update TAPMSCHED set              ");
    	query.append("    equip_id = ?                     ");
    	query.append("where comp_no = ?                    ");
    	query.append("    and pmequip_id = ?               ");
    	query.append("    and pmsched_status != 'C'        ");
    	query.append("    AND plan_date >= TO_CHAR(SYSDATE,'YYYYMMDD')          ");
    	objects = new Object[] {
    			workPmListEquipDetailDTO.getEquipId()
    			,maPmMstrCommonDTO.getCompNo()
    			,workPmListEquipDetailDTO.getPmEquipId()
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    	
    }
    
    /**
     * InitWrkDate update
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param initWrkDate
     * @param compNo
     * @param pmId
     * @return
     */
    public int updateInitWrkDate(String initWrkDate, String compNo, String pmId)
    {
    	QueryBuffer query = new QueryBuffer();

		query.append("UPDATE TAPMEQUIP SET 		");
		query.append("	init_wrk_date = ?		");
		query.append("	,last_sch_date = ?		");
		query.append("WHERE comp_no = ?			");
		query.append("  AND pm_id   = ?			");
		Object[] objects = new Object[] {
				initWrkDate
    	        ,initWrkDate
    	        ,compNo
    	        ,pmId
    	};
		
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListEquipDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPMEQUIP								");
    	query.append("	(comp_no,		pmequip_id,		pm_id,			");
    	query.append("	 equip_id,		eqasmb_id,      description,	");
    	query.append("	 init_wrk_date, last_sch_date,	step_num,		");
    	query.append("   is_active,     remark                          ");
    	query.append("	)VALUES											");
    	query.append("	(?,				?,			?,					");
    	query.append("	 ?,				?,			?,					");
    	query.append("   ?,            	?,          ?,              	");
    	query.append("	 ?,				?								");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    	        workPmListEquipDetailDTO.getPmEquipId(),
    	        maPmMstrCommonDTO.getPmId(),
    	        workPmListEquipDetailDTO.getEquipId(),
    	        workPmListEquipDetailDTO.getEqAsmbId(),
    	        workPmListEquipDetailDTO.getWorkPart(),
    	        workPmListEquipDetailDTO.getInitWrkDate().replaceAll("-", ""),
    	        workPmListEquipDetailDTO.getInitWrkDate().replaceAll("-", ""),
    	        workPmListEquipDetailDTO.getStepNum(),
    	        workPmListEquipDetailDTO.getIsActive(),
    	        workPmListEquipDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public int mergeEqPmCycle(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO,
			User user) {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT SQAEQPMCYCLE_ID.NEXTVAL	");
    	query.append("FROM DUAL 						");
    	
        String eqpmcycleId = QueryBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString()));
    	
    	query.setClear();
    	query.append("MERGE INTO TAEQPMCYCLE c          ");
    	query.append("USING (	SELECT	?	compNo		");
    	query.append("                , ?   pmId		");
    	query.append("                , ?   equipId     ");
    	query.append("          FROM dual         ) b   ");
    	query.append("ON (c.comp_no = b.compNo          ");
    	query.append("    AND c.pm_id = b.pmId )        ");
    	query.append("WHEN MATCHED THEN                 ");
    	query.append("  UPDATE SET                      ");
    	query.append("      c.equip_id = b.equipId      ");
    	query.append("WHEN NOT MATCHED THEN             ");
    	query.append("  INSERT (comp_no, eqpmcycle_id, equip_id, pm_id )    ");
    	query.append("  VALUES (b.compNo, ?, b.equipId, b.pmId )			");

    	Object[] objects = new Object[] {
    			  user.getCompNo()
    			, maPmMstrCommonDTO.getPmId()
    			, workPmListEquipDetailDTO.getEquipId()
    			, eqpmcycleId
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public String countPmEquip(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) {
		
    	QueryBuffer query = new QueryBuffer();
		
    	query.append(" SELECT                                                        ");
        query.append("        COUNT(1)                                               ");
		query.append("   FROM TAPMEQUIP x                                            ");
		query.append("  WHERE 1=1	                                				 ");
		query.append("  AND x.comp_no = ?                              				 ");
		query.append("  AND x.pm_id = ?                             				 ");
		query.append("  AND x.is_active = ?                            				 ");
		query.append("  AND x.is_deleted = ?                           				 ");
        
    	Object[] objects = new Object[] {
  			  user.getCompNo()
  			, maPmMstrCommonDTO.getPmId()
  			, "Y"
  			, "N"
    	};
        
        List resultList=  getJdbcTemplate().queryForList(query.toString(), objects);
        return QueryBuffer.listToString(resultList);
	}
}