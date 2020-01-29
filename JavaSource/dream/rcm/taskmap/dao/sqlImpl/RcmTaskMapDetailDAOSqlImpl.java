package dream.rcm.taskmap.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.taskmap.dao.RcmTaskMapDetailDAO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.dto.RcmTaskMapDetailDTO;

/**
 * 질의 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: RcmTaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmTaskMapDetailDAOTarget"
 * @spring.txbn id="rcmTaskMapDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmTaskMapDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmTaskMapDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmTaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapCommonDTO
     * @return
     */
    public RcmTaskMapDetailDTO findDetail(RcmTaskMapCommonDTO rcmTaskMapCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT       		");
        query.append("    pmtaskmaplist_no mapNo		");
        query.append("    ,description mapName		");
        query.append("    ,is_use isUse		");
        query.append("    ,reg_date regDate		");
        query.append("    ,remark remark		");
        query.append("    ,pmtaskmaplist_id pmTaskMapListId		");
        query.append("FROM TAPMTASKMAPLIST		");
        query.append("WHERE  comp_no = '"+rcmTaskMapCommonDTO.getCompNo()+"' 	");
        query.append("  AND  pmtaskmaplist_id = "+rcmTaskMapCommonDTO.getPmTaskMapListId()+"");
    
        RcmTaskMapDetailDTO rcmTaskMapDetailDTO = 
        		(RcmTaskMapDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmTaskMapDetailDTO()));
        
        return rcmTaskMapDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmTaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * 
     * 
     * @param rcmTaskMapDetailDTO
     * @return
     */
    public int insertDetail(RcmTaskMapDetailDTO rcmTaskMapDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPMTASKMAPLIST							");
    	query.append("	(comp_no,		pmtaskmaplist_id,    pmtaskmaplist_no,	");
    	query.append("	 description,	is_use,	             reg_date,	");
    	query.append("	 remark      	");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?												");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			rcmTaskMapDetailDTO.getCompNo()
    			,rcmTaskMapDetailDTO.getPmTaskMapListId()
    			,rcmTaskMapDetailDTO.getMapNo()
    			,rcmTaskMapDetailDTO.getMapName()
    			,rcmTaskMapDetailDTO.getIsUse()
    			,rcmTaskMapDetailDTO.getRegDate()
    			,rcmTaskMapDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmTaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapDetailDTO
     * @return
     */
    public int updateDetail(RcmTaskMapDetailDTO rcmTaskMapDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPMTASKMAPLIST SET			");
    	query.append("	description			= ?	");
    	query.append("	,is_use			    = ?		");
    	query.append("	,reg_date			= ?		");
    	query.append("	,remark			    = ?		");
    	query.append("WHERE pmtaskmaplist_id		= ?		");
    	query.append("  AND comp_no 		= ?		");
    	
    	Object[] objects = new Object[] {
    			rcmTaskMapDetailDTO.getMapName()
    			,rcmTaskMapDetailDTO.getIsUse()
    			,rcmTaskMapDetailDTO.getRegDate()
    			,rcmTaskMapDetailDTO.getRemark()
    			,rcmTaskMapDetailDTO.getPmTaskMapListId()
    			,rcmTaskMapDetailDTO.getCompNo()
    			
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

}