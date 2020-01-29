package dream.work.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.MaWoResultPointDetailDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointDetailDTO;

/**
 * 작업결과-검사항목 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultPointDetailDAOTarget"
 * @spring.txbn id="maWoResultPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultPointDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoResultPointDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPointId
     * @param compNo
     * @return
     */
    public MaWoResultPointDetailDTO findDetail(String wkOrId, String woPointId, String pmPointId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String compNo = user.getCompNo();
    	String searchId = (woPointId=="")?pmPointId:woPointId;

        query.append("SELECT																									");
        query.append("		y.pm_point_id     					pmPointId,														");
        query.append("		x.wopoint_id     					woPointId,														");
        query.append("		y.step_num     						stepNum,														");
        query.append("		(SELECT ISNULL(a.full_desc, a.description)															");
        query.append("		   FROM TAEQASMB a																					");
        query.append("		  WHERE a.comp_no = y.comp_no																		");
        query.append("			AND a.eqasmb_id = y.eqasmb_id) 	eqAsmbDesc,														");
        query.append("		y.eqasmb_id		    				eqAsmbId,														");
        query.append("		y.pm_id			    				pmId,															");
        query.append("		y.check_point 						'CHECKPOINT',													");
        query.append("		y.check_method 						checkMethod,													");
        query.append("		y.fit_basis 						fitBasis,														");
        query.append("		dbo.SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS','','"+user.getLangId()+"')	checkTypeDesc,		");
        query.append("		convert(nvarchar,y.check_min)+' / '+convert(nvarchar,y.check_basis_val)+' / '+convert(nvarchar,y.check_max)+	 ");
        query.append("			CASE WHEN y.uom IS NULL THEN '' ELSE ' . ('+y.uom+')' END					checkValUom,		");
        query.append("		y.remark 							remark,															");
        query.append("		x.pm_point_rslt_status 				pmPointRsltStatus,												");
        query.append("		dbo.SFACODE_TO_DESC(LTRIM(RTRIM(x.pm_point_rslt_status)),'PM_POINT_RSLT_STATUS','SYS','','"+user.getLangId()+"') pmPointRsltStatusDesc,	");
        query.append("		x.result_value 						resultValue,													");
        query.append("		x.remark 							ResultRemark													");
        query.append("		,y.check_type						checkTypeId														");
        query.append("FROM  TAWOPOINT x RIGHT OUTER JOIN TAPMPOINT y															");
        query.append("	ON x.comp_no = y.comp_no																				");
        query.append(" AND x.pm_point_id = y.pm_point_id																		");
        query.append(" AND x.wkor_id = ?																						");
        query.append("WHERE 1=1																									");
        query.append("  AND y.comp_no = ?																						");
        query.append("	AND y.pm_id = (SELECT pm_id 																			");
		query.append("				     FROM TAWORKORDER 																		");
		query.append("				    WHERE comp_no 	= ?																		");
		query.append("				      AND wkor_id 	= ?																		");
		query.append("				  )																							");
		if(!"".equals(woPointId))
        {
        	query.append("  AND x.wopoint_id = ?																					");
        }
        else
        {
        	query.append("  AND y.pm_point_id = ?																					");	
        }
        
        Object[] objects = new Object[] {
        		wkOrId
    			,compNo
    			,compNo
    			,wkOrId
    			,searchId
    	};
        MaWoResultPointDetailDTO maWoResultPointDetailDTO = 
        		(MaWoResultPointDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaWoResultPointDetailDTO()));
        
        return maWoResultPointDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPointDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(MaWoResultPointDetailDTO maWoResultPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, boolean inputFlag, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	Object[] objects;
    	
    	if(inputFlag){

        	query.append("INSERT INTO TAWOPOINT									");
        	query.append("	(comp_no,					wopoint_id,				");
        	query.append("	 wkor_id,					pm_point_id,			");
        	query.append("	 pm_point_rslt_status,		result_value,			");
        	query.append("	 remark,					pm_point_rep_status		");
        	query.append("	)	VALUES											");
        	query.append("	(?,							?,						");
        	query.append("	 ?,							?,						");
        	query.append("	 ?,							?,						");
        	query.append("	 ?,							?						");
        	query.append("	)													");
        	
        	objects = new Object[] {
        			user.getCompNo(),
        			maWoResultMstrCommonDTO.getWkOrId(),
        			maWoResultPointDetailDTO.getWoPointId(),
        			maWoResultPointDetailDTO.getPmPointId(),
        			maWoResultPointDetailDTO.getPmPointRsltStatus(),
        			maWoResultPointDetailDTO.getResultValue(),
        			maWoResultPointDetailDTO.getResultRemark(),
        			maWoResultPointDetailDTO.getPmPointRsltStatus()
        	};
    	}else{
    		query.append("UPDATE TAWOPOINT SET				");
        	query.append("	pm_point_rslt_status	= ?,	");
        	query.append("	pm_point_rep_status		= ?,	");
        	query.append("	result_value			= ?,	");
        	query.append("	remark					= ?		");
        	query.append("WHERE wopoint_id		= ?			");
        	query.append("  AND comp_no			= ?			");
        	
        	objects = new Object[] {
        			maWoResultPointDetailDTO.getPmPointRsltStatus(),
        			maWoResultPointDetailDTO.getPmPointRsltStatus(),
        			maWoResultPointDetailDTO.getResultValue(),
        			maWoResultPointDetailDTO.getResultRemark(),
        			maWoResultPointDetailDTO.getWoPointId(),
        			user.getCompNo()
        	};
    	}
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPointDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(MaWoResultPointDetailDTO maWoResultPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	Object[] objects = new Object[] {
    	};
    	return 0;
    }
}