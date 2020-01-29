package dream.rcm.funceq.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.rcm.funceq.dao.RcmFuncEqDetailDAO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqDetailDTO;

/**
 * 질의 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: RcmFuncEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmFuncEqDetailDAOTarget"
 * @spring.txbn id="rcmFuncEqDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFuncEqDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmFuncEqDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFuncEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public RcmFuncEqDetailDTO findDetail(RcmFuncEqCommonDTO rcmFuncEqCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                   								");
        query.append("    y.rcmlist_id rcmListId,		");
        query.append("    y.description rcmDesc,		");
        query.append("    (SELECT z.rcmfunc_no FROM TARCMFUNC z WHERE x.rcmfunc_id = z.rcmfunc_id AND x.comp_no=z.comp_no) funcNo,		");
        query.append("    (SELECT z.rcmfunc_id FROM TARCMFUNC z WHERE x.rcmfunc_id = z.rcmfunc_id AND x.comp_no=z.comp_no) funcId,		");
        query.append("    (SELECT z.description FROM TARCMFUNC z WHERE x.rcmfunc_id = z.rcmfunc_id AND x.comp_no=z.comp_no) funcName,		");
        query.append("    x.rcmffail_no rcmFfailNo,		");
        query.append("    x.description description,		");
        query.append("    x.remark remark		");
        query.append("FROM   TARCMFFAIL x, TARCMLIST y        							");
        query.append("WHERE  x.comp_no = '"+rcmFuncEqCommonDTO.getCompNo()+"' 	");
        query.append("  AND  x.rcmlist_id = y.rcmlist_id	");
        query.append("  AND  x.rcmffail_id = "+rcmFuncEqCommonDTO.getRcmFfailId()+"");
    
        RcmFuncEqDetailDTO rcmFuncEqDetailDTO = 
        		(RcmFuncEqDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmFuncEqDetailDTO()));
        
        return rcmFuncEqDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFuncEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * 
     * 
     * @param rcmFuncEqDetailDTO
     * @return
     */
    public int insertDetail(RcmFuncEqDetailDTO rcmFuncEqDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TARCMFFAIL							");
    	query.append("	(comp_no,		rcmffail_id,    rcmfunc_id,	    ");
    	query.append("	 rcmlist_id,	rcmffail_no,	description,	");
    	query.append("	 remark      	                                ");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?												");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			rcmFuncEqDetailDTO.getCompNo(),
    			rcmFuncEqDetailDTO.getRcmFfailId(),
    			rcmFuncEqDetailDTO.getFuncId(),
    			rcmFuncEqDetailDTO.getRcmListId(),
    			rcmFuncEqDetailDTO.getRcmFfailNo(),
    			rcmFuncEqDetailDTO.getDescription(),
    			rcmFuncEqDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFuncEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqDetailDTO
     * @return
     */
    public int updateDetail(RcmFuncEqDetailDTO rcmFuncEqDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TARCMFFAIL SET			");
    	query.append("	rcmfunc_id			= ?,	");
    	query.append("	rcmlist_id			= ?,	");
    	query.append("	rcmffail_no			= ?,	");
    	query.append("	description			= ?,	");
    	query.append("	remark			    = ? 	");
    	query.append("WHERE rcmffail_id		= ?		");
    	query.append("  AND comp_no 		= ?		");
    	
    	Object[] objects = new Object[] {
    			rcmFuncEqDetailDTO.getFuncId(),
    			rcmFuncEqDetailDTO.getRcmListId(),
    			rcmFuncEqDetailDTO.getRcmFfailNo(),
    			rcmFuncEqDetailDTO.getDescription(),
    			rcmFuncEqDetailDTO.getRemark(),
    			rcmFuncEqDetailDTO.getRcmFfailId(),
    			rcmFuncEqDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }


	@Override
	public int insertRcmFunc(RcmFuncEqDetailDTO rcmFuncEqDetailDTO) {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TARCMFUNC							");
    	query.append("	(comp_no,		rcmfunc_id,	    rcmlist_id,	");
    	query.append("	 rcmfunc_no,	description		");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?				");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			rcmFuncEqDetailDTO.getCompNo(),
    			rcmFuncEqDetailDTO.getFuncId(),
    			rcmFuncEqDetailDTO.getRcmListId(),
    			rcmFuncEqDetailDTO.getFuncNo(),
    			rcmFuncEqDetailDTO.getFuncName()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
		
	}

	@Override
	public int updateRcmFunc(RcmFuncEqDetailDTO rcmFuncEqDetailDTO) {
    	QueryBuffer query = new QueryBuffer();
        
    	query.append("UPDATE TARCMFUNC SET			");
    	query.append("	rcmfunc_no			= ?,		");
    	query.append("	description			= ? 		");
    	query.append("WHERE rcmfunc_id		= ?		");
    	query.append("  AND comp_no 		= ?		");
    	
    	Object[] objects = new Object[] {
    			rcmFuncEqDetailDTO.getFuncNo(),
    			rcmFuncEqDetailDTO.getFuncName(),
    			rcmFuncEqDetailDTO.getFuncId(),
    			rcmFuncEqDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
	}

}