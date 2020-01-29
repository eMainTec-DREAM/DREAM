package dream.work.pmi.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.pmi.list.dao.WorkPmiPointDetailDAO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointDetailDTO;

/**
 * 점검작업 점검 상세 dao
 * @author  kim21017
 * @version $Id: WorkPmiPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workPmiPointDetailDAOTarget"
 * @spring.txbn id="workPmiPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmiPointDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmiPointDetailDAO
{
	 /**
     * detail find
     * @author kim21017
     * @version $Id: WorkPmiPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPointId
     * @param compNo
     * @return
     */
    public WorkPmiPointDetailDTO findDetail(String pminslistId, String pmInsPointId, String pmPointId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String compNo = user.getCompNo();
    	String searchId = (pmInsPointId=="")?pmPointId:pmInsPointId;

        query.append("SELECT																									");
        query.append("		y.pm_point_id	 																pmPointId			");
        
        query.append("      ,x.equip_id                                                                     equipId             ");
        query.append("      ,x.pmequip_id                                                                   pmEquipId           ");
        
        query.append("		,x.pminspoint_id 																pmInsPointId		");
        query.append("		,y.step_num 																	stepNum				");
        query.append("		,(SELECT ISNULL(a.full_desc, a.description)															");
        query.append("		    FROM TAEQASMB a																					");
        query.append("		   WHERE a.comp_no = y.comp_no																		");
        query.append("			 AND a.eqasmb_id = y.eqasmb_id) 											eqAsmbDesc			");
        query.append("		,y.check_point 																	'CHECKPOINT'		");
        query.append("		,y.check_method 																checkMethod			");
        query.append("		,y.fit_basis 																	fitBasis			");
        query.append("		,dbo.SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS','','"+user.getLangId()+"') checkTypeDesc		");
        query.append("		,ISNULL(CONVERT(nvarchar,y.check_min),'')+' / '+ISNULL(CONVERT(nvarchar,y.check_basis_val),'')+' / '+ISNULL(CONVERT(nvarchar,y.check_max),'')+	");
        query.append("			CASE WHEN y.uom IS NULL THEN '' ELSE ' . ('+y.uom+')' END					checkValUom			");
        query.append("		,y.remark 																		remark				");
        query.append("		,x.pm_point_rslt_status 														pmPointRsltStatus	");
        query.append("		,dbo.SFACODE_TO_DESC(ltrim(rtrim(x.pm_point_rslt_status)),'PM_POINT_RSLT_STATUS','SYS','','"+user.getLangId()+"') 	pmPointRsltStatusDesc		");
        query.append("		,x.result_value 																resultValue			");
        query.append("		,x.remark 																		ResultRemark		");
        query.append("  , y.stwrk_point_id          														stwrkPointId    	");
        query.append("  , (SELECT a.check_point                     															");
        query.append("     FROM TASTDCHECKPOINT a                   															");
        query.append("     WHERE a.std_check_point_id = y.stwrk_point_id)        							stwrkPointDesc		");
        query.append("		,y.check_type																	checkTypeId			");
        query.append(" FROM TAPMINSPOINT x RIGHT OUTER JOIN TAPMPOINT y															");
        query.append("	 ON x.comp_no = y.comp_no																				");
        query.append("  AND x.pm_point_id = y.pm_point_id																		");
        query.append("  AND x.pminslist_id = ?																					");
        query.append("WHERE 1=1																									");
        query.append("  AND y.comp_no = ?																						");
        query.append("	AND y.pm_id = (SELECT pm_id 																			");
		query.append("				     FROM TAPMINSLIST 																		");
		query.append("				    WHERE comp_no 	 = ?																	");
		query.append("				      AND pminslist_id = ?																	");
		query.append("				  )																							");
        
        if(!"".equals(pmInsPointId))
        {
        	query.append("  AND x.pminspoint_id = ?																				");
        }
        else
        {
        	query.append("  AND y.pm_point_id = ?																				");	
        }
        
        Object[] objects = new Object[] {
        		pminslistId
    			,compNo
    			,compNo
    			,pminslistId
    			,searchId
    	};
    
        WorkPmiPointDetailDTO workPmiPointDetailDTO = (WorkPmiPointDetailDTO)getJdbcTemplate().query(query.toString(), getObject(objects), new MwareExtractor(new WorkPmiPointDetailDTO()));
        
        return workPmiPointDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkPmiPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiPointDetailDTO
     * @param workPmiCommonDTO
     * @return
     */
    public int updateDetail(WorkPmiPointDetailDTO workPmiPointDetailDTO, WorkPmiCommonDTO workPmiCommonDTO, boolean inputFlag, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	Object[] objects;
    	
    	if(inputFlag){

        	query.append("INSERT INTO TAPMINSPOINT									");
        	query.append("	(comp_no,					pminslist_id,				");
        	query.append("	 pminspoint_id,				pm_point_id,				");
        	query.append("	 pm_point_rslt_status,		result_value,				");
        	query.append("	 remark,					pm_point_rep_status			");
        	query.append("	)	VALUES												");
        	query.append("	(?,							?,							");
        	query.append("	 ?,							?,							");
        	query.append("	 ?,							?,							");
        	query.append("	 ?,							?							");
        	query.append("	)														");
        	
        	objects = new Object[] {
        			 user.getCompNo()
        			,workPmiCommonDTO.getPminslistId()
        			,workPmiPointDetailDTO.getPmInsPointId()
        			,workPmiPointDetailDTO.getPmPointId()
        			,workPmiPointDetailDTO.getPmPointRsltStatus()
        			,workPmiPointDetailDTO.getResultValue()
        			,workPmiPointDetailDTO.getResultRemark()
        			,workPmiPointDetailDTO.getPmPointRsltStatus()
        	};
    	}else{
    		query.append("UPDATE TAPMINSPOINT SET			");
        	query.append("	pm_point_rslt_status	= ?,	");
        	query.append("	pm_point_rep_status		= ?,	");
        	query.append("	result_value			= dbo.IS_EMPTY(?),	");
        	query.append("	remark					= ?,	");
        	query.append("	is_saved				= ?,	");
        	query.append("	actual_date				= ?		");
        	query.append("WHERE pminspoint_id		= ?		");
        	query.append("  AND comp_no				= ?		");
        	
        	objects = new Object[] {
        			workPmiPointDetailDTO.getPmPointRsltStatus(),
        			workPmiPointDetailDTO.getPmPointRsltStatus(),
        			workPmiPointDetailDTO.getResultValue(),
        			workPmiPointDetailDTO.getResultRemark(),
        			"Y",
        			DateUtil.getDate(),
        			workPmiPointDetailDTO.getPmInsPointId(),
        			user.getCompNo()
        	};
    	}
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}