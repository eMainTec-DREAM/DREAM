package dream.mgr.usrrpt.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.usrrpt.dao.MaUserRptColDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptColDetailDTO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * »ó¼¼ dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maUserRptColDetailDAOTarget"
 * @spring.txbn id="maUserRptColDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptColDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaUserRptColDetailDAO
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
	public MaUserRptColDetailDTO findDetail(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT 											");
        query.append("       '' seqNo,									");
        query.append("       x.table_id tableId,						");
        query.append("       x.table_name tableName,					");
        query.append("       x.column_name columnName,					");
        query.append("       x.description colDesc,						");
        query.append("       ISNULL(col_alias, x.column_name) colAlias,				");
        query.append("       ISNULL(col_width, '150') colWidth,						");
        query.append("       ISNULL(col_size,'12') colSize,							");
        query.append("       ISNULL(col_align,'center') colAlign,						");
//        query.append("       SFACODE_TO_DESC(ISNULL(col_align,'center'),'ALIGN_TYPE','SYS','','"+loginUser.getLangId()+"') colAlignDesc,");
        query.append("		 dbo.SFACODE_TO_DESC(ISNULL(col_align,'center'),'ALIGN_TYPE','SYS','','"+loginUser.getLangId()+"')	colAlignDesc,");

        query.append("       ISNULL(use_yn,'N') useYn,						");
        query.append("       ISNULL(step_num, (SELECT ISNULL(MAX(a.step_num)+1,1) FROM TAUSRRPTCOL a WHERE a.usrrptlist_id = "+maUserRptCommonDTO.getUsrrptlistId()+")) stepNum,		");
        query.append("       usrrptcol_id usrrptcolId,					");
        query.append("       usrrpttab_id usrrpttabId,					");
        query.append("       usrrptlist_id usrrptlistId,				");
        query.append("       x.tabcol_id tabcolId   					");
        query.append("FROM   TATABCOL x LEFT OUTER JOIN TAUSRRPTCOL y ON x.tabcol_id = y.tabcol_id 	");
        query.getAndNumKeyQuery("y.usrrpttab_id", maUserRptCommonDTO.getUsrrpttabId());
        query.append("WHERE  x.table_id IN (SELECT a.table_id FROM TAUSRRPTTAB a WHERE a.usrrptlist_id = "+maUserRptCommonDTO.getUsrrptlistId()+") 	");
        query.getAndNumKeyQuery("x.tabcol_id", maUserRptCommonDTO.getTabcolId());
        
        MaUserRptColDetailDTO maUserRptColDetailDTO = 
        		(MaUserRptColDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaUserRptColDetailDTO()));
        
        return maUserRptColDetailDTO;
    }
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptColDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserRptColDetailDTO maUserRptColDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAUSRRPTCOL SET		");
    	query.append("	     step_num   	= ?, 	");
    	query.append("	     col_alias   	= ?, 	");
    	query.append("	     col_size   	= ?, 	");
    	query.append("	     col_width   	= ?, 	");
    	query.append("	     col_align   	= ?, 	");
    	query.append("	     use_yn   		= ? 	");
    	query.append("WHERE  comp_no    	= ?		");
    	query.append("  AND  usrrptcol_id  	= ?		");
    	
    	Object[] objects = new Object[] {
    			maUserRptColDetailDTO.getStepNum(),
    			maUserRptColDetailDTO.getColAlias(),
    			maUserRptColDetailDTO.getColSize(),
    			maUserRptColDetailDTO.getColWidth(),
    			maUserRptColDetailDTO.getColAlign(),
    			maUserRptColDetailDTO.getUseYn(),
    			maUserRptColDetailDTO.getCompNo(),
    			maUserRptColDetailDTO.getUsrrptcolId()

    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptColDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaUserRptColDetailDTO maUserRptColDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAUSRRPTCOL (				                    		");
    	query.append("	comp_no,   		usrrptlist_id,    	usrrpttab_id,   step_num, 	");
    	query.append("	usrrptcol_id,  	table_id,       	table_name,    	tabcol_id, 	");
    	query.append("	column_name,  	col_alias,       	col_size,    	col_width, 	");
    	query.append("	col_align,  	use_yn                                 			");
    	query.append(")	VALUES (                                                		");
    	query.append("  ?,	       		?,            		?,          	?,        	");
    	query.append("  ?,	       		?,            		?,          	?,         	");
    	query.append("  ?,	       		?,            		?,          	?,         	");
    	query.append("  ?,	       		?         										");
    	query.append(")									                        		");
    	
    	Object[] objects = new Object[] {
    			maUserRptColDetailDTO.getCompNo(),
    			maUserRptColDetailDTO.getUsrrptlistId(),
    			maUserRptColDetailDTO.getUsrrpttabId(),
    			maUserRptColDetailDTO.getStepNum(),
    			maUserRptColDetailDTO.getUsrrptcolId(),
    			maUserRptColDetailDTO.getTableId(),
    			maUserRptColDetailDTO.getTableName(),
    			maUserRptColDetailDTO.getTabcolId(),
    			maUserRptColDetailDTO.getColumnName(),
    			maUserRptColDetailDTO.getColAlias(),
    			maUserRptColDetailDTO.getColSize(),
    			maUserRptColDetailDTO.getColWidth(),
    			maUserRptColDetailDTO.getColAlign(),
    			maUserRptColDetailDTO.getUseYn()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }

    public int checkNextNum(String listId)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT                                                            	");
    	query.append("       ISNULL(MAX(step_num),0)            								");
    	query.append("FROM   TAUSRRPTCOL                                     				");
    	query.append("WHERE  1 = 1             												");
        query.append("  AND  usrrptlist_id = '"+listId+"' 	");
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
    }

}