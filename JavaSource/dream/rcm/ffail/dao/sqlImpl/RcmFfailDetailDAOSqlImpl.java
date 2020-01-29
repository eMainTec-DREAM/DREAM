package dream.rcm.ffail.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.ffail.dao.RcmFfailDetailDAO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.dto.RcmFfailDetailDTO;

/**
 * 질의 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: RcmFfailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmFfailDetailDAOTarget"
 * @spring.txbn id="rcmFfailDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFfailDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmFfailDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFfailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailCommonDTO
     * @return
     */
    public RcmFfailDetailDTO findDetail(RcmFfailCommonDTO rcmFfailCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                   			                        ");
        query.append("    y.rcmlist_id rcmListId,		                        ");
        query.append("    y.description rcmDesc,		                        ");
        query.append("    x.rcmfunc_no funcNo,		                            ");
        query.append("    x.description funcName,		                        ");
        query.append("    x.remark remark		                                ");
        query.append("FROM   TARCMFUNC x, TARCMLIST y                           ");
        query.append("WHERE  x.comp_no = '"+rcmFfailCommonDTO.getCompNo()+"' 	");
        query.append("  AND  x.rcmlist_id = y.rcmlist_id	                    ");
        query.append("  AND  x.rcmfunc_id = "+rcmFfailCommonDTO.getRcmFuncId()+"");
    
        RcmFfailDetailDTO rcmFfailDetailDTO = 
        		(RcmFfailDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmFfailDetailDTO()));
        
        return rcmFfailDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFfailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * 
     * 
     * @param rcmFfailDetailDTO
     * @return
     */
    public int insertDetail(RcmFfailDetailDTO rcmFfailDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TARCMFUNC							    ");
    	query.append("	(comp_no,		rcmfunc_id,	    rcmlist_id,	    ");
    	query.append("	 rcmfunc_no,	description,	remark		    ");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,				?				");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			rcmFfailDetailDTO.getCompNo(),
    			rcmFfailDetailDTO.getRcmFuncId(),
    			rcmFfailDetailDTO.getRcmListId(),
    			rcmFfailDetailDTO.getFuncNo(),
    			rcmFfailDetailDTO.getFuncName(),
    			rcmFfailDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFfailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDetailDTO
     * @return
     */
    public int updateDetail(RcmFfailDetailDTO rcmFfailDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TARCMFUNC SET			");
    	query.append("	rcmlist_id			= ?,	");
    	query.append("	rcmfunc_no			= ?,	");
    	query.append("	description			= ?,	");
    	query.append("	remark			    = ?		");
    	query.append("WHERE rcmfunc_id		= ?		");
    	query.append("  AND comp_no 		= ?		");
    	
    	Object[] objects = new Object[] {
    			rcmFfailDetailDTO.getRcmListId(),
    			rcmFfailDetailDTO.getFuncNo(),
    			rcmFfailDetailDTO.getFuncName(),
    			rcmFfailDetailDTO.getRemark(),
    			rcmFfailDetailDTO.getRcmFuncId(),
    			rcmFfailDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: RcmFfailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDetailDTO
     * @return
     */
    public int confirmDetail(RcmFfailDetailDTO rcmFfailDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("");
    	
    	Object[] objects = new Object[] {
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}