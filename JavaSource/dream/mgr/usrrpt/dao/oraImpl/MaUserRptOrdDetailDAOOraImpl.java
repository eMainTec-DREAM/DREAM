package dream.mgr.usrrpt.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.usrrpt.dao.MaUserRptOrdDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptOrdDetailDTO;

/**
 * »ó¼¼ dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maUserRptOrdDetailDAOTarget"
 * @spring.txbn id="maUserRptOrdDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptOrdDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUserRptOrdDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return
     */
    public MaUserRptOrdDetailDTO findDetail(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT 											");
        query.append("       '' seqNo,									");
        query.append("       x.table_id tableId,						");
        query.append("       x.table_name tableName,					");
        query.append("       x.column_name columnName,					");
        query.append("       x.description colDesc,						");
        query.append("       sort_type sortType, 						");
        query.append("       SFACODE_TO_DESC(sort_type,'SORT_TYPE','SYS','','"+loginUser.getLangId()+"') sortTypeDesc,");
        query.append("       NVL(use_yn,'N') useYn,						");
        query.append("       NVL(step_num, (SELECT NVL(MAX(a.step_num)+1,1) FROM TAUSRRPTORD a WHERE a.usrrptlist_id = "+maUserRptCommonDTO.getUsrrptlistId()+")) stepNum,		");
        query.append("       usrrptord_id usrrptordId,					");
        query.append("       usrrpttab_id usrrpttabId,					");
        query.append("       usrrptlist_id usrrptlistId,				");
        query.append("       x.tabcol_id tabcolId   					");
        query.append("FROM   TATABCOL x LEFT OUTER JOIN TAUSRRPTORD y ON x.tabcol_id = y.tabcol_id 	");
        query.getAndNumKeyQuery("y.usrrptord_id", maUserRptCommonDTO.getUsrrptordId());
        query.append("WHERE  x.table_id IN (SELECT a.table_id FROM TAUSRRPTTAB a WHERE a.usrrptlist_id = "+maUserRptCommonDTO.getUsrrptlistId()+") 	");
        query.getAndNumKeyQuery("x.tabcol_id", maUserRptCommonDTO.getTabcolId());
        
        MaUserRptOrdDetailDTO maUserRptOrdDetailDTO = 
        		(MaUserRptOrdDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaUserRptOrdDetailDTO()));
        
        return maUserRptOrdDetailDTO;
    }
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptOrdDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserRptOrdDetailDTO maUserRptOrdDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSRRPTORD SET		");
    	query.append("	     step_num   	= ?, 	");
    	query.append("	     sort_type   	= ?, 	");
    	query.append("	     use_yn   		= ? 	");
    	query.append("WHERE  comp_no    	= ?		");
    	query.append("  AND  usrrptord_id  	= ?		");
    	
    	Object[] objects = new Object[] {
    			maUserRptOrdDetailDTO.getStepNum(),
    			maUserRptOrdDetailDTO.getSortType(),
    			maUserRptOrdDetailDTO.getUseYn(),
    			maUserRptOrdDetailDTO.getCompNo(),
    			maUserRptOrdDetailDTO.getUsrrptordId()

    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptOrdDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaUserRptOrdDetailDTO maUserRptOrdDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAUSRRPTORD (				                    		");
    	query.append("	comp_no,   		usrrptlist_id,    	usrrpttab_id,   step_num, 	");
    	query.append("	usrrptord_id,  	table_id,       	table_name,    	tabcol_id, 	");
    	query.append("	column_name,  	sort_type,       	use_yn    	             	");
    	query.append(")	VALUES (                                                		");
    	query.append("  ?,	       		?,            		?,          	?,        	");
    	query.append("  ?,	       		?,            		?,          	?,         	");
    	query.append("  ?,	       		?,            		?                        	");
    	query.append(")									                        		");
    	
    	Object[] objects = new Object[] {
    			maUserRptOrdDetailDTO.getCompNo(),
    			maUserRptOrdDetailDTO.getUsrrptlistId(),
    			maUserRptOrdDetailDTO.getUsrrpttabId(),
    			maUserRptOrdDetailDTO.getStepNum(),
    			maUserRptOrdDetailDTO.getUsrrptordId(),
    			maUserRptOrdDetailDTO.getTableId(),
    			maUserRptOrdDetailDTO.getTableName(),
    			maUserRptOrdDetailDTO.getTabcolId(),
    			maUserRptOrdDetailDTO.getColumnName(),
    			maUserRptOrdDetailDTO.getSortType(),
    			maUserRptOrdDetailDTO.getUseYn()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }

    public int checkNextNum(String listId)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                                                            	");
    	query.append("       NVL(MAX(step_num),0)            								");
    	query.append("FROM   TAUSRRPTORD                                     				");
    	query.append("WHERE  1 = 1             												");
        query.append("  AND  usrrptlist_id = '"+listId+"' 	");
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
    }

}