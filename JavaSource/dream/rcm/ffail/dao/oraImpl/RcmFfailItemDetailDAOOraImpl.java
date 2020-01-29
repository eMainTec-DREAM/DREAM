package dream.rcm.ffail.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.rcm.ffail.dao.RcmFfailItemDetailDAO;
import dream.rcm.ffail.dto.RcmFfailItemDetailDTO;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;

/**
 * 답변 상세 dao
 * @author  kim21017
 * @version $Id: RcmFfailItemDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFfailItemDetailDAOTarget"
 * @spring.txbn id="rcmFfailItemDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFfailItemDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmFfailItemDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFfailItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailItemListDTO
     * @param rcmFfailCommonDTO
     * @return
     */
    public RcmFfailItemDetailDTO findDetail(RcmFfailItemListDTO rcmFfailItemListDTO, RcmFfailCommonDTO rcmFfailCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = rcmFfailCommonDTO.getCompNo();
        
        query.append("SELECT 		");
        query.append("    x.rcmffail_no rcmFfailNo,		");
        query.append("    x.description description,		");
        query.append("    x.remark remark,		");
        query.append("    x.rcmffail_id rcmFfailId		");
        query.append("FROM TARCMFFAIL x		");
        query.append("WHERE x.comp_no = '"+compNo+"'						");
        query.getAndQuery("x.rcmffail_id",rcmFfailItemListDTO.getRcmFfailId());
    
        RcmFfailItemDetailDTO rcmFfailItemDetailDTO1 = 
        		(RcmFfailItemDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmFfailItemDetailDTO()));
        
        return rcmFfailItemDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFfailItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailItemDetailDTO
     * @param rcmFfailCommonDTO
     * @return
     */
    public int updateDetail(RcmFfailItemDetailDTO rcmFfailItemDetailDTO, RcmFfailCommonDTO rcmFfailCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TARCMFFAIL SET				");
    	query.append("	rcmffail_no				= ?	,	");
    	query.append("	description				= ?	,	");
    	query.append("	remark				    = ?		");
    	query.append("WHERE rcmffail_id 		= ?		");
    	query.append("  AND comp_no				= ? 	");
    	
    	Object[] objects = new Object[] {
    			rcmFfailItemDetailDTO.getRcmFfailNo(),
    			rcmFfailItemDetailDTO.getDescription(),
    			rcmFfailItemDetailDTO.getRemark(),
    			rcmFfailItemDetailDTO.getRcmFfailId(),
    			rcmFfailCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFfailItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailItemDetailDTO
     * @param rcmFfailCommonDTO
     * @return
     */
    public int insertDetail(RcmFfailItemDetailDTO rcmFfailItemDetailDTO, RcmFfailCommonDTO rcmFfailCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMFFAIL (								");
    	query.append("	comp_no,		rcmffail_id,		rcmfunc_id,		");
    	query.append("	rcmffail_no,	description,		remark,			");
    	query.append("	rcmlist_id     										");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	?,				?,					?,				");
    	query.append("	(SELECT rcmlist_id FROM TARCMFUNC WHERE rcmfunc_id=? AND COMP_NO=?  )");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			rcmFfailCommonDTO.getCompNo(),
    			rcmFfailItemDetailDTO.getRcmFfailId(),
    			rcmFfailCommonDTO.getRcmFuncId(),
    			rcmFfailItemDetailDTO.getRcmFfailNo(),
    			rcmFfailItemDetailDTO.getDescription(),
    			rcmFfailItemDetailDTO.getRemark(),
    			rcmFfailCommonDTO.getRcmFuncId(),
    			rcmFfailCommonDTO.getCompNo()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}