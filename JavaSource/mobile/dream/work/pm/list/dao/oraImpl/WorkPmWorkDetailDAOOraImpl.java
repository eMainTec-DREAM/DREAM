package mobile.dream.work.pm.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import mobile.dream.work.pm.list.dao.WorkPmWorkDetailDAO;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.dto.WorkPmWorkDetailDTO;

/**
 * 상세 dao
 * 
 * @author jung7126
 * @version $Id: WorkPmWorkDetailDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 * @spring.bean id="workPmWorkDetailDAOTarget"
 * @spring.txbn id="workPmWorkDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmWorkDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmWorkDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: WorkPmWorkDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     */
    public WorkPmWorkDetailDTO findDetail(WorkPmWorkCommonDTO dto, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        String lang = user.getLangId();
        
        query.append("SELECT																			");
        query.append("		x.wkor_id													wkOrId,			");
        query.append("		x.wo_no														woNo,			");
        query.append("		x.wo_status													woStatusId,		");
        query.append("		SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+lang+"')				woStatusDesc,	");
        query.append("		z.equip_id 													AS equipId,		");
        query.append("		z.description						 						AS equipDesc,	");
        query.append("		(SELECT c.full_desc															");
        query.append("		 FROM TAEQLOC c																");
        query.append("		 WHERE c.comp_no = x.comp_no												");
        query.append("			AND c.eqloc_id = z.eqloc_id	) 							AS eqLocDesc,	");
        query.append("		x.description												wkOrDesc,		");
        query.append("		x.wo_type													woTypeId,		");
        query.append("		SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+lang+"')					woTypeDesc,		");
        query.append("		x.pm_type													pmTypeId,		");
        query.append("		SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+lang+"')		pmTypeDesc,		");
        query.append("		x.shift_type												shiftTypeId,	");
        query.append("		SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+lang+"')			shiftTypeDesc,	");
        query.append("		x.dept_id													deptId,			");
        query.append("		(SELECT description															");
        query.append("		   FROM TADEPT																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND dept_id = x.dept_id)								deptDesc,		");
        query.append("       x.wkctr_id	                             					wkCtrId,		");
        query.append("		 (SELECT description														");
        query.append("		  FROM TAWKCTR																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		  AND wkctr_id = x.wkctr_id) 			 					wkCtrDesc,		");
        query.append("		x.emp_id													empId,			");
        query.append("		(SELECT emp_name															");
        query.append("		   FROM TAEMP																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND emp_id = x.emp_id)									empDesc,		");
        query.append("		x.start_date												startDate,		");
        query.append("		x.start_time												startTime,		");
        query.append("		x.end_date													endDate,		");
        query.append("		x.end_time													endTime,		");
        query.append("		x.work_time													workTime,		");
        query.append("		x.wkor_date													wkorDate,		");
        query.append("		x.vendor_id													vendorId,		");
        query.append("		x.tot_amt													totAmt,			");
        query.append("      SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)           vendorDesc,  	"); 
        query.append("		x.self_vendor_type											selfVendorType,	");
        query.append("		SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+lang+"') selfVendorTypeDesc,");
        query.append("		x.perform													perform,		");
        query.append("		x.pmaction													pmAction,		");
        query.append("		x.wo_gen_type												woGenType,		");
        query.append("		x.eqloc_id													eqLocId,		");
        query.append("		x.p_wkor_id													parentWoId,		");
        query.append("        (SELECT user_name                                                         ");
        query.append("           FROM TAUSER                                                            ");
        query.append("          WHERE comp_no = x.comp_no                                               ");
        query.append("            AND user_id = x.close_id)                               closeBy,   	");
        query.append("		x.close_date												closeDate,		");
        query.append("		(SELECT wo_no FROM TAWORKORDER aa WHERE aa.comp_no = x.comp_no AND aa.wkor_id = x.p_wkor_id)	parentWoNo");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y                                         ");
        query.append("ON x.comp_no = y.comp_no AND x.wkor_id = y.wkor_id								");
        query.append("		INNER JOIN TAEQUIPMENT z        											");
        query.append("      ON y.comp_no = z.comp_no AND y.equip_id = z.equip_id						");
        query.append("WHERE 1=1																			");
        query.append("  AND x.wkor_id = ?																");
        query.append("  AND x.comp_no = ?																");
    
        Object[] objects = new Object[] {
        		dto.getWkorId(),
        		user.getCompNo()
    	};
  
        WorkPmWorkDetailDTO workPmWorkDetailDTO = 
        		(WorkPmWorkDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkPmWorkDetailDTO()));
        
        return workPmWorkDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmWorkDetailDTO
     * @return
     */
    public int insertDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAWORKORDER							");
    	query.append("	(comp_no,		wkor_id,		wo_status,		");
    	query.append("					description,	wo_type,		");
    	query.append("	 dept_id,		pm_type,		emp_id,			");
    	query.append("	 start_date,	start_time,		end_date,		");
    	query.append("	 end_time,		work_time,		perform,		");
    	query.append("	 wo_no,         vendor_id,      self_vendor_type,");
    	query.append("   wo_gen_type,   p_wkor_id,		shift_type,		");
    	query.append("   wkor_date,		tot_amt,		eqloc_id,		");
    	query.append("   pmaction,		wkctr_id     					");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("   ?,             ?,				?,				");
    	query.append("   ?,				?,				?,				");
    	query.append("   ?,				?    						    ");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			workPmWorkDetailDTO.getCompNo(),
    			workPmWorkDetailDTO.getWkOrId(),
    			workPmWorkDetailDTO.getWoStatusId(),
    			workPmWorkDetailDTO.getWkOrDesc(),
    			workPmWorkDetailDTO.getWoTypeId(),
    			workPmWorkDetailDTO.getDeptId(),
    			workPmWorkDetailDTO.getPmTypeId(),
    			workPmWorkDetailDTO.getEmpId(),
    			workPmWorkDetailDTO.getStartDate(),
    			workPmWorkDetailDTO.getStartTime(),
    			workPmWorkDetailDTO.getEndDate(),
    			workPmWorkDetailDTO.getEndTime(),
    			workPmWorkDetailDTO.getWorkTime(),
    			workPmWorkDetailDTO.getPerform(),
    			workPmWorkDetailDTO.getWoNo(),
    			workPmWorkDetailDTO.getVendorId(),
    			workPmWorkDetailDTO.getSelfVendorType(),
    			"WORSLT",
    			workPmWorkDetailDTO.getParentWoId(),
    			workPmWorkDetailDTO.getShiftTypeId(),
    			workPmWorkDetailDTO.getWkorDate(),
    			workPmWorkDetailDTO.getTotAmt(),
    			workPmWorkDetailDTO.getEqLocId(),
    			workPmWorkDetailDTO.getPmAction(),
    			workPmWorkDetailDTO.getWkCtrId()
    	};
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	if(!"".equals(workPmWorkDetailDTO.getEquipId())&&workPmWorkDetailDTO.getEquipId()!=null){
    		query = new QueryBuffer();
    		
    		query.append("INSERT INTO TAWOEQUIP(comp_no, woequip_id,wkor_id,equip_id)	");
    		query.append("VALUES(?,SQAWOEQUIP_ID.NEXTVAL,?,?)							");
    		Object[] objects1 = new Object[] {
    				workPmWorkDetailDTO.getCompNo(),
    				workPmWorkDetailDTO.getWkOrId(),
    				workPmWorkDetailDTO.getEquipId()
        	};
        	rtnValue = this.getJdbcTemplate().update(query.toString(), objects1);
    	}
    	
    	return rtnValue;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmWorkDetailDTO
     * @return
     */
    public int updateDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	
    	query.append("UPDATE TAWORKORDER SET		");
    	query.append("	description			= ?,	");
    	query.append("	wo_type				= ?,	");
//    	query.append("	dept_id				= ?,	");
    	query.append("	pm_type				= ?,	");
//    	query.append("	emp_id				= ?,	");
    	query.append("	start_date			= ?,	");
    	query.append("	start_time			= ?,	");
    	query.append("	end_date			= ?,	");
    	query.append("	end_time			= ?,	");
    	query.append("	work_time			= ?,	");
//    	query.append("	self_vendor_type	= ?,	");
//    	query.append("	vendor_id			= ?,	");
    	query.append("	perform				= ?,	");
//    	query.append("	pmaction			= ?,	");
    	query.append("	wkor_date			= ?,	");
//    	query.append("	tot_amt				= ?,	");
//    	query.append("	shift_type			= ?,	");
//    	query.append("	p_wkor_id			= ?,	");
//    	query.append("	eqloc_id			= ?,	");
//    	query.append("	wkctr_id			= ?		");
    	query.append("WHERE wkor_id	 		= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			workPmWorkDetailDTO.getWkOrDesc(),
    			workPmWorkDetailDTO.getWoTypeId(),
//    			workPmWorkDetailDTO.getDeptId(),
    			workPmWorkDetailDTO.getPmTypeId(),
//    			workPmWorkDetailDTO.getEmpId(),
    			workPmWorkDetailDTO.getStartDate(),
    			workPmWorkDetailDTO.getStartTime(),
    			workPmWorkDetailDTO.getEndDate(),
    			workPmWorkDetailDTO.getEndTime(),
    			workPmWorkDetailDTO.getWorkTime(),
//    			workPmWorkDetailDTO.getSelfVendorType(),
//    			workPmWorkDetailDTO.getVendorId(),
    			workPmWorkDetailDTO.getPerform(),
//    			workPmWorkDetailDTO.getPmAction(),
    			workPmWorkDetailDTO.getStartDate(),   //작업일자 항목이 없어서 StartDate로 변경
//    			workPmWorkDetailDTO.getTotAmt(),
//    			workPmWorkDetailDTO.getShiftTypeId(),
//    			workPmWorkDetailDTO.getParentWoId(),
//    			workPmWorkDetailDTO.getEqLocId(),
//    			workPmWorkDetailDTO.getWkCtrId(),
    			workPmWorkDetailDTO.getWkOrId(),
    			workPmWorkDetailDTO.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	if(!"".equals(workPmWorkDetailDTO.getEquipId())&&workPmWorkDetailDTO.getEquipId()!=null){
    		query = new QueryBuffer();
    		
    		query.append("UPDATE TAWOEQUIP SET equip_id = ?			");
    		query.append("WHERE comp_no   = ?						");
    		query.append("  AND wkor_id   = ?						");
    		Object[] objects1 = new Object[] {
    				workPmWorkDetailDTO.getEquipId(),
    				workPmWorkDetailDTO.getCompNo(),
    				workPmWorkDetailDTO.getWkOrId()
        	};
        	rtnValue = this.getJdbcTemplate().update(query.toString(), objects1);
    	}
    	
    	return rtnValue;
    }
}