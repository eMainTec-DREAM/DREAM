package dream.mgr.usrrpt.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.usrrpt.dao.MaUserRptParamDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptParamDetailDTO;

/**
 * »ó¼¼ dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maUserRptParamDetailDAOTarget"
 * @spring.txbn id="maUserRptParamDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptParamDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUserRptParamDetailDAO
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
    public MaUserRptParamDetailDTO findDetail(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT                                             	");
    	query.append("       '' seqNo,                                    	");
    	query.append("       x.table_id tableId,                        	");
    	query.append("       x.table_name tableName,                    	");
    	query.append("       x.column_name columnName,                    	");
    	query.append("       x.description colDesc,                         ");
    	query.append("       NVL(use_yn,'N') useYn,                        	");
    	query.append("       NVL(step_num, (SELECT NVL(MAX(a.step_num)+1,1) FROM TAUSRRPTWHR a WHERE a.usrrptlist_id = "+maUserRptCommonDTO.getUsrrptlistId()+")) stepNum,       	");
    	query.append("       SFACODE_TO_DESC(whr_con_operator,'WHR_CON_OPERATOR','SYS','','ko') whrConOperatorDesc,	");
    	query.append("       whr_con_operator whrConOperator,	");
    	query.append("       con_value conValue,	");
    	query.append("       usrrptwhr_id usrrptwhrId,                    	");
    	query.append("       usrrpttab_id usrrpttabId,                    	");
    	query.append("       usrrptlist_id usrrptlistId,                	");
    	query.append("       x.tabcol_id tabcolId                       	");
    	query.append("FROM   TATABCOL x LEFT OUTER JOIN TAUSRRPTWHR y ON x.tabcol_id = y.tabcol_id     	");
        query.getAndNumKeyQuery("y.usrrptwhr_id", maUserRptCommonDTO.getUsrrptwhrId());
        query.append("WHERE  x.table_id IN (SELECT a.table_id FROM TAUSRRPTTAB a WHERE a.usrrptlist_id = "+maUserRptCommonDTO.getUsrrptlistId()+") 	");
        query.getAndNumKeyQuery("x.tabcol_id", maUserRptCommonDTO.getTabcolId());
        
        MaUserRptParamDetailDTO maUserRptParamDetailDTO = 
        		(MaUserRptParamDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaUserRptParamDetailDTO()));
        
        return maUserRptParamDetailDTO;
    }
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptParamDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserRptParamDetailDTO maUserRptParamDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSRRPTWHR SET		");
    	query.append("	     step_num   		= ?, 	");
    	query.append("	     whr_con_operator   = ?, 	");
    	query.append("	     con_value   		= ?, 	");
    	query.append("	     use_yn   			= ? 	");
    	query.append("WHERE  comp_no    		= ?		");
    	query.append("  AND  usrrptwhr_id  		= ?		");
    	
    	Object[] objects = new Object[] {
    			maUserRptParamDetailDTO.getStepNum(),
    			maUserRptParamDetailDTO.getWhrConOperator(),
    			maUserRptParamDetailDTO.getConValue(),
    			maUserRptParamDetailDTO.getUseYn(),
    			maUserRptParamDetailDTO.getCompNo(),
    			maUserRptParamDetailDTO.getUsrrptwhrId()

    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptParamDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaUserRptParamDetailDTO maUserRptParamDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAUSRRPTWHR (				                    		");
    	query.append("	comp_no,   		usrrptlist_id,    	usrrpttab_id,   step_num, 	");
    	query.append("	usrrptwhr_id,  	table_id,       	table_name,    	tabcol_id, 	");
    	query.append("	column_name,  	whr_con_operator,  	con_value,    	use_yn 	    ");
    	query.append(")	VALUES (                                                		");
    	query.append("  ?,	       		?,            		?,          	?,        	");
    	query.append("  ?,	       		?,            		?,          	?,         	");
    	query.append("  ?,	       		?,            		?,          	?          	");
    	query.append(")									                        		");
    	
    	Object[] objects = new Object[] {
    			maUserRptParamDetailDTO.getCompNo(),
    			maUserRptParamDetailDTO.getUsrrptlistId(),
    			maUserRptParamDetailDTO.getUsrrpttabId(),
    			maUserRptParamDetailDTO.getStepNum(),
    			maUserRptParamDetailDTO.getUsrrptwhrId(),
    			maUserRptParamDetailDTO.getTableId(),
    			maUserRptParamDetailDTO.getTableName(),
    			maUserRptParamDetailDTO.getTabcolId(),
    			maUserRptParamDetailDTO.getColumnName(),
    			maUserRptParamDetailDTO.getWhrConOperator(),
    			maUserRptParamDetailDTO.getConValue(),
    			maUserRptParamDetailDTO.getUseYn()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }

    public int checkNextNum(String listId)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                                                            	");
    	query.append("       NVL(MAX(step_num),0)            								");
    	query.append("FROM   TAUSRRPTWHR                                     				");
    	query.append("WHERE  1 = 1             												");
        query.append("  AND  usrrptlist_id = '"+listId+"' 	");
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
    }

}