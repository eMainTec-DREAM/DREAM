package dream.work.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.list.dao.WorkListCavalDetailDAO;
import dream.work.list.dto.WorkListCavalDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업상세  - 검교정 - 측정값 상세 dao
 * @author  kim21017
 * @version $Id: WorkListCavalDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workListCavalDetailDAOTarget"
 * @spring.txbn id="workListCavalDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListCavalDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListCavalDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkListCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCalibValueId
     * @param compNo
     * @return
     */
    public WorkListCavalDetailDTO findDetail(String wkOrId, String woCalibValueId, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("SELECT											");
        query.append("       x.wkor_id 				wkOrId,				");
        query.append("       x.wocalibetcvalue_id	woCalibValueId,		");
        query.append("       x.set_value 			setVal,				");
        query.append("		 x.basis_value			basisVal,			");
        query.append("		 x.before_value			befVal,				");
        query.append("		 x.before_diff_value	befDiffVal,			");
        query.append("		 x.after_value			aftVal,				");
        query.append("       x.after_diff_value 	aftDiffVal,			");
        query.append("       x.remark 				remark				");
        query.append("FROM   TAWOCALIBETCVALUE x							");
        query.append("WHERE	 x.wocalibetcvalue_id 	= '"+woCalibValueId+"'	");
        query.append("  AND  x.comp_no			= '"+compNo+"'			");
    
        WorkListCavalDetailDTO workListCavalDetailDTO = 
        		(WorkListCavalDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new WorkListCavalDetailDTO()));
        
        return workListCavalDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkListCavalDetailDTO workListCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAWOCALIBETCVALUE SET			");
    	query.append("	set_value				= ?,	");
    	query.append("	basis_value				= ?,	");
    	query.append("	before_value			= ?,	");
    	query.append("	before_diff_value		= ?,	");
    	query.append("	after_value				= ?,	");
    	query.append("	after_diff_value		= ?,	");
    	query.append("	remark					= ?		");
    	query.append("WHERE wocalibetcvalue_id		= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
    			CommonUtil.getRowMoneyToNum(workListCavalDetailDTO.getSetVal()),
    			CommonUtil.getRowMoneyToNum(workListCavalDetailDTO.getBasisVal()),
    			CommonUtil.getRowMoneyToNum(workListCavalDetailDTO.getBefVal()),
    			workListCavalDetailDTO.getBefDiffVal(),
    			CommonUtil.getRowMoneyToNum(workListCavalDetailDTO.getAftVal()),
    			workListCavalDetailDTO.getAftDiffVal(),
    			workListCavalDetailDTO.getRemark(),
    			workListCavalDetailDTO.getWoCalibValueId(),
    			maWoResultMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListCavalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkListCavalDetailDTO workListCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAWOCALIBETCVALUE					");
    	query.append("	(comp_no,				wocalibetcvalue_id,	");
    	query.append("	 wkor_id,				set_value,			");
    	query.append("	 basis_value,			before_value,		");
    	query.append("	 before_diff_value,		after_value,		");
    	query.append("	 after_diff_value,		remark				");
    	query.append("	)	VALUES									");
    	query.append("	(?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?					");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrCommonDTO.getCompNo(),
    			workListCavalDetailDTO.getWoCalibValueId(),
    			maWoResultMstrCommonDTO.getWkOrId(),
    			CommonUtil.getRowMoneyToNum(workListCavalDetailDTO.getSetVal()),
    			CommonUtil.getRowMoneyToNum(workListCavalDetailDTO.getBasisVal()),
    			CommonUtil.getRowMoneyToNum(workListCavalDetailDTO.getBefVal()),
    			workListCavalDetailDTO.getBefDiffVal(),
    			CommonUtil.getRowMoneyToNum(workListCavalDetailDTO.getAftVal()),
    			workListCavalDetailDTO.getAftDiffVal(),
    			workListCavalDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}