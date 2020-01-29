package dream.work.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.list.dao.WorkListGnlCavalDetailDAO;
import dream.work.list.dto.WorkListGnlCavalDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업상세  - 검교정 - 측정값 상세 dao
 * @author  kim21017
 * @version $Id: WorkListGnlCavalDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workListGnlCavalDetailDAOTarget"
 * @spring.txbn id="workListGnlCavalDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListGnlCavalDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListGnlCavalDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkListGnlCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCalibValueId
     * @param compNo
     * @return
     */
    public WorkListGnlCavalDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                                        	");
    	query.append("       SFACODE_TO_DESC(calib_point_type,'CALIB_POINT_TYPE','SYS','',? ) calibPointTypeDesc        	");
    	query.append("       ,calib_point_type calibPointType        	");
    	query.append("       ,calib_point calibPoint                	");
    	query.append("       ,allow_value allowValue                	");
    	query.append("       ,asf_std_value asfStdValue                	");
    	query.append("       ,asf_cal_value asfCalValue                	");
    	query.append("       ,asf_diff_value asfDiffValue            	");
    	query.append("       ,asl_std_value aslStdValue                	");
    	query.append("       ,asl_cal_value aslCalValue                	");
    	query.append("       ,asl_diff_value aslDiffValue            	");
    	query.append("       ,ord_no ordNo                            	");
    	query.append("       ,remark                                	");
    	query.append("       ,wkor_id wkorId                            ");
    	query.append("       ,wocalibgnlvalue_id wocalibgnlvalueId    	");
    	query.append("FROM   TAWOCALIBGNLVALUE       					");
    	query.append("WHERE	 wocalibgnlvalue_id 	= ?					");
    	query.append("  AND	 comp_no 				= ?					");

    	Object[] objects = new Object[] {
    			user.getLangId(),
    			maWoResultMstrCommonDTO.getWocalibgnlvalueId(),
    			user.getCompNo()
    	};
    	
        WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO = 
        		(WorkListGnlCavalDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkListGnlCavalDetailDTO()));
        
        return workListGnlCavalDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListGnlCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAWOCALIBGNLVALUE SET	     ");
    	query.append("	calib_point_type	    = ?,	 ");
    	query.append("	calib_point			    = ?,	 ");
    	query.append("	allow_value			    = ?,	 ");
    	query.append("	asf_std_value		    = ?,	 ");
    	query.append("	asf_cal_value		    = ?,	 ");
    	query.append("	asf_diff_value		    = ?,	 ");
    	query.append("	asl_std_value		    = ?,	 ");
    	query.append("	asl_cal_value		    = ?,	 ");
    	query.append("	asl_diff_value		    = ?,	 ");
    	query.append("	ord_no				    = ?,	 ");
    	query.append("	remark				    = ?		 ");
    	query.append("WHERE wocalibgnlvalue_id	= ?		 ");
    	query.append("  AND comp_no				= ?		 ");
    	
    	Object[] objects = new Object[] {
    			workListGnlCavalDetailDTO.getCalibPointType(),
    			workListGnlCavalDetailDTO.getCalibPoint(),
    			workListGnlCavalDetailDTO.getAllowValue(),
    			workListGnlCavalDetailDTO.getAsfStdValue(),
    			workListGnlCavalDetailDTO.getAsfCalValue(),
    			workListGnlCavalDetailDTO.getAsfDiffValue(),
    			workListGnlCavalDetailDTO.getAslStdValue(),
    			workListGnlCavalDetailDTO.getAslCalValue(),
    			workListGnlCavalDetailDTO.getAslDiffValue(),
    			workListGnlCavalDetailDTO.getOrdNo(),
    			workListGnlCavalDetailDTO.getRemark(),
    			workListGnlCavalDetailDTO.getWocalibgnlvalueId(),
    			maWoResultMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListGnlCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAWOCALIBGNLVALUE					");
    	query.append("	(comp_no,				calib_point_type,	");
    	query.append("	 wkor_id,				calib_point,		");
    	query.append("	 allow_value,			asf_std_value,		");
    	query.append("	 asf_diff_value,		asl_std_value,		");
    	query.append("	 asl_cal_value,			asl_diff_value,		");
    	query.append("	 ord_no,				remark,				");
    	query.append("	 wocalibgnlvalue_id,    asf_cal_value		");
    	query.append("	)	VALUES									");
    	query.append("	(?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?					");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrCommonDTO.getCompNo(),
    			workListGnlCavalDetailDTO.getCalibPointType(),
    			maWoResultMstrCommonDTO.getWkOrId(),
    			workListGnlCavalDetailDTO.getCalibPoint(),
    			workListGnlCavalDetailDTO.getAllowValue(),
    			workListGnlCavalDetailDTO.getAsfStdValue(),
    			workListGnlCavalDetailDTO.getAsfDiffValue(),
    			workListGnlCavalDetailDTO.getAslStdValue(),
    			workListGnlCavalDetailDTO.getAslCalValue(),
    			workListGnlCavalDetailDTO.getAslDiffValue(),
    			workListGnlCavalDetailDTO.getOrdNo(),
    			workListGnlCavalDetailDTO.getRemark(),
    			workListGnlCavalDetailDTO.getWocalibgnlvalueId(),
    			workListGnlCavalDetailDTO.getAsfCalValue()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}