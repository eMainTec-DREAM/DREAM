package dream.mgr.usrrpt.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.usrrpt.dao.MaUserRptTableDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptTableDetailDTO;

/**
 * »ó¼¼ dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maUserRptTableDetailDAOTarget"
 * @spring.txbn id="maUserRptTableDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptTableDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUserRptTableDetailDAO
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
    public MaUserRptTableDetailDTO findDetail(MaUserRptCommonDTO maPtMstrCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT                                                        	");
    	query.append("       step_num                               stepNum,        	");
    	query.append("       y.table_name                           tableName,        	");
    	query.append("       y.description                          tableDesc,      	");
    	query.append("       join_type    							joinType,			");
    	query.append("       SFACODE_TO_DESC(join_type,'JOIN_TYPE','SYS','','"+loginUser.getLangId()+"') joinTypeDesc,	");
    	query.append("       x.table_id     						tableId,			");
    	query.append("       main_sub_type 							mainSubType,		");
    	query.append("       comp_no								compNo,				");
    	query.append("       usrrpttab_id                           usrrpttabId,    	");
    	query.append("       usrrptlist_id                          usrrptlistId     	");
    	query.append("FROM   TAUSRRPTTAB x, TATABLE y                                	");
    	query.append("WHERE  1 = 1                                                    	");
    	query.append("  AND  x.table_id = y.table_id                                	");
        query.append("  AND  x.comp_no   = '"+loginUser.getCompNo()+"'          		");
        query.append("  AND  x.usrrpttab_id = '"+maPtMstrCommonDTO.getUsrrpttabId()+"' 	");
    
        MaUserRptTableDetailDTO maUserRptTableDetailDTO = 
        		(MaUserRptTableDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaUserRptTableDetailDTO()));
        
        return maUserRptTableDetailDTO;
    }
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptTableDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserRptTableDetailDTO maUserRptTableDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSRRPTTAB SET		");
    	query.append("	     table_id   	= ?, 	");
    	query.append("	     table_name   	= ?, 	");
    	query.append("	     join_type   	= ? 	");
    	query.append("WHERE  comp_no    	= ?		");
    	query.append("  AND  usrrpttab_id  	= ?		");
    	
    	Object[] objects = new Object[] {
    			maUserRptTableDetailDTO.getTableId(),
    			maUserRptTableDetailDTO.getTableName(),
    			maUserRptTableDetailDTO.getJoinType(),
    			maUserRptTableDetailDTO.getCompNo(),
    			maUserRptTableDetailDTO.getUsrrpttabId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int checkNextNum(String listId)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                                                            	");
    	query.append("       NVL(MAX(step_num),0)            								");
    	query.append("FROM   TAUSRRPTTAB                                     				");
    	query.append("WHERE  1 = 1             												");
        query.append("  AND  usrrptlist_id = '"+listId+"' 	");
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptTableDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaUserRptTableDetailDTO maUserRptTableDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAUSRRPTTAB (				                    ");
    	query.append("	comp_no,   usrrptlist_id,    usrrpttab_id,    step_num, ");
    	query.append("	table_id,  table_name,       main_sub_type,   join_type ");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,	       ?,            	?,          	  ?,        ");
    	query.append("  ?,	       ?,            	?,          	  ?         ");
    	query.append(")									                        ");
    	
    	Object[] objects = new Object[] {
    			maUserRptTableDetailDTO.getCompNo(),
    			maUserRptTableDetailDTO.getUsrrptlistId(),
    			maUserRptTableDetailDTO.getUsrrpttabId(),
    			maUserRptTableDetailDTO.getStepNum(),
    			maUserRptTableDetailDTO.getTableId(),
    			maUserRptTableDetailDTO.getTableName(),
    			maUserRptTableDetailDTO.getMainSubType(),
    			maUserRptTableDetailDTO.getJoinType()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}