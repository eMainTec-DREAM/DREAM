package dream.mgr.usrcd.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.usrcd.dao.MaCdUsrDetailDAO;
import dream.mgr.usrcd.dto.MaCdUsrDetailDTO;

/**
 * 사용자코드관리
 * @author 
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maCdUsrDetailDAOTarget"
 * @spring.txbn id="maCdUsrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaCdUsrDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaCdUsrDetailDAO
{
    /**
     * 코드상세
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param cdUsrmId
     * @return
     */
    public MaCdUsrDetailDTO findDetail(User user, String cdUsrmId)
    {      
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();

        query.append("SELECT                                        ");
        query.append("       x.comp_no 	            compNo,         ");
        query.append("       x.cdusrm_id            cdUsrmId,       ");
        query.append("       x.dir_type             dirType,        ");
        query.append("       x.description,                         ");
        query.append("       x.remark,                              ");
        query.append("       x.is_use               isUse,          ");
        query.append("       SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isUseDesc ");
        query.append("FROM   TACDUSRM x                             ");
        query.append("WHERE  x.comp_no   = '"+compNo+"'             ");
        query.append("  AND  x.cdusrm_id = '"+cdUsrmId+"'           ");
        
        MaCdUsrDetailDTO maCdUsrDetailDTO = 
        		(MaCdUsrDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaCdUsrDetailDTO()));
        
        return maCdUsrDetailDTO;
    }
    
    /**
     * grid insert
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrDetailDTO
     * @return
     */
    public int insertDetail(MaCdUsrDetailDTO maCdUsrDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TACDUSRM (                                    ");
        query.append("    comp_no,      cdusrm_id,    dir_type,                 ");
        query.append("    description,  remark,       is_use                    ");
        query.append(")VALUES(                                                  ");
        query.append("    ?,            ?,            ?,                        ");
        query.append("    ?,            ?,            ?                         ");
        query.append(")                                                         ");
        
        Object[] objects = new Object[] {   
                maCdUsrDetailDTO.getCompNo(),
                maCdUsrDetailDTO.getCdUsrmId(),
                maCdUsrDetailDTO.getDirType(),
                maCdUsrDetailDTO.getDescription(),
                maCdUsrDetailDTO.getRemark(),
                maCdUsrDetailDTO.getIsUse()
                };
        
        int returnValue = this.getJdbcTemplate().update(query.toString(), objects);
        
        return returnValue;
    }
    
    /**
     * grid update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrDetailDTO
     * @return
     */
    public int updateDetail(MaCdUsrDetailDTO maCdUsrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TACDUSRM SET           ");
    	query.append("	     dir_type       = ?,	");
    	query.append("	     description	= ?,	");
    	query.append("	     remark         = ?,	");
    	query.append("	     is_use		    = ?     ");
    	query.append("WHERE  comp_no        = ?	    ");
    	query.append("  AND  cdusrm_id      = ?	    ");
    	
    	Object[] objects = new Object[] {   
    			maCdUsrDetailDTO.getDirType(),
    			maCdUsrDetailDTO.getDescription(),
    			maCdUsrDetailDTO.getRemark(),
    			maCdUsrDetailDTO.getIsUse(),
    			maCdUsrDetailDTO.getCompNo(),
    			maCdUsrDetailDTO.getCdUsrmId()
    	};
    	
    	int returnValue = this.getJdbcTemplate().update(query.toString(), objects);
    	
    	return returnValue;
    }
      
    /**
     * valid dirType
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maCdUsrDetailDTO
     * @return
     */
    public String validDirType(MaCdUsrDetailDTO maCdUsrDetailDTO)
    {
        
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                          ");
        query.append("FROM   TACDUSRM x                        ");
        query.append("WHERE  x.comp_no  = '" + maCdUsrDetailDTO.getCompNo() + "'  ");
        query.append("  AND  x.dir_type = '" + maCdUsrDetailDTO.getDirType() + "' ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
}