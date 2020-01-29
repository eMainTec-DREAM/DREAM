package dream.rcm.system.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.system.dao.RcmSysFDefDetailDAO;
import dream.rcm.system.dto.RcmSysFDefDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 기능정의 상세 dao
 * @author  kim21017
 * @version $Id: RcmSysFDefDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmSysFDefDetailDAOTarget"
 * @spring.txbn id="rcmSysFDefDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysFDefDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmSysFDefDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysFDefDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public RcmSysFDefDetailDTO findDetail(String rcmListId, String rcmFuncId, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT												");
        query.append("       x.rcmfunc_no					rcmFuncNo,		");
        query.append("       x.description 					description,	");
        query.append("       x.remark 						remark,			");
        query.append("       x.rcmfunc_id 					rcmFuncId		");
        query.append("FROM   TARCMFUNC x	    							");
        query.append("WHERE	 x.rcmfunc_id 		= '"+rcmFuncId+"'			");
        query.append("  AND  x.rcmlist_id		= '"+rcmListId+"'			");
        query.append("  AND  x.comp_no			= '"+compNo+"'				");
    
        RcmSysFDefDetailDTO rcmSysFDefDetailDTO = 
        		(RcmSysFDefDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmSysFDefDetailDTO()));
        
        return rcmSysFDefDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysFDefDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFDefDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(RcmSysFDefDetailDTO rcmSysFDefDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TARCMFUNC SET				");
    	query.append("	rcmfunc_no				= ?,	");
    	query.append("	description				= ?,	");
    	query.append("	remark					= ?		");
    	query.append("WHERE rcmfunc_id		    = ?	    ");
    	query.append("  AND comp_no			    = ?		");
    	
    	Object[] objects = new Object[] {
    			rcmSysFDefDetailDTO.getRcmFuncNo(),
    			rcmSysFDefDetailDTO.getDescription(),
    			rcmSysFDefDetailDTO.getRemark(),
    			rcmSysFDefDetailDTO.getRcmFuncId(),
    			maPmMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysFDefDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFDefDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(RcmSysFDefDetailDTO rcmSysFDefDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TARCMFUNC							");
    	query.append("	(comp_no,		rcmlist_id,		rcmfunc_id,	");
    	query.append("	 rcmfunc_no,	description,	remark		");
    	query.append("	)VALUES										");
    	query.append("	(?,				?,			?,				");
    	query.append("	 ?,				?,			?				");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			maPmMstrCommonDTO.getCompNo(),
    			maPmMstrCommonDTO.getRcmListId(),
    			rcmSysFDefDetailDTO.getRcmFuncId(),
    			rcmSysFDefDetailDTO.getRcmFuncNo(),
    			rcmSysFDefDetailDTO.getDescription(),
    			rcmSysFDefDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}