package dream.mgr.usrrpt.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.usrrpt.dao.MaUserRptJoinDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptJoinDetailDTO;

/**
 * »ó¼¼ dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maUserRptJoinDetailDAOTarget"
 * @spring.txbn id="maUserRptJoinDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptJoinDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaUserRptJoinDetailDAO
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
	public MaUserRptJoinDetailDTO findDetail(MaUserRptCommonDTO maPtMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
    	  query.append("SELECT                                                        																");
          query.append("       step_num  stepNum,        																							");
          query.append("       (SELECT y.table_name FROM TATABLE y WHERE x.ltable_id = y.table_id) ltableName,     									");
          query.append("       (SELECT a.usrrpttab_id FROM TAUSRRPTTAB a WHERE a.usrrpttab_id = x.lusrrpttab_id) lusrrpttabId,						");
          query.append("       (SELECT a.usrrpttab_id FROM TAUSRRPTTAB a WHERE a.usrrpttab_id = x.rusrrpttab_id) rusrrpttabId,           			");
          query.append("       x.ltable_id                               ltableId,        															");
          query.append("       (SELECT z.column_name FROM TATABCOL z WHERE z.table_id = x.ltable_id AND z.tabcol_id = x.ltabcol_id) lcolumnName,	");
          query.append("       x.ltabcol_id                               ltabcolId,        														");
//          query.append("       SFACODE_TO_DESC(x.tab_con_operator,'TAB_CON_OPERATOR','SYS','','"+user.getLangId()+"') tabConOperatorDesc,			");
          query.append("	   dbo.SFACODE_TO_DESC(x.tab_con_operator,'TAB_CON_OPERATOR','SYS','','"+user.getLangId()+"')	tabConOperatorDesc,			");
          
          query.append("       x.tab_con_operator                               tabConOperator,        												");
//          query.append("       SFACODE_TO_DESC(x.col_value_type,'COL_VALUE_TYPE','SYS','','"+user.getLangId()+"') colValueTypeDesc,					");
          query.append("	   dbo.SFACODE_TO_DESC(x.col_value_type,'COL_VALUE_TYPE','SYS','','"+user.getLangId()+"')	colValueTypeDesc,				");			
           
          query.append("       x.col_value_type                               colValueType,        													");
          query.append("       (SELECT y.table_name FROM TATABLE y WHERE x.rtable_id = y.table_id) rtableName,     									");
          query.append("       x.rtable_id                               rtableId,        															");
          query.append("       (SELECT z.column_name FROM TATABCOL z WHERE z.table_id = x.rtable_id AND z.tabcol_id = x.rtabcol_id) rcolumnName,	");
          query.append("       x.rtabcol_id                               rtabcolId,        														");
          query.append("       con_value conValue, 																									");
          query.append("       usrrpttab_id                           usrrpttabId,    																");
          query.append("       usrrptlist_id                          usrrptlistId,																	");
          query.append("       usrrptjoin_id usrrptjoinId     								");
          query.append("FROM   TAUSRRPTJOIN x           									");
          query.append("WHERE  1 = 1                            							");
          query.append("  AND  x.comp_no   = '"+user.getCompNo()+"'          				");
          query.append("  AND  x.usrrptjoin_id = '"+maPtMstrCommonDTO.getUsrrptjoinId()+"' 	");
    
        MaUserRptJoinDetailDTO maUserRptJoinDetailDTO = 
        		(MaUserRptJoinDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaUserRptJoinDetailDTO()));
        
        return maUserRptJoinDetailDTO;
    }
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptJoinDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserRptJoinDetailDTO maUserRptJoinDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSRRPTJOIN SET			");
    	query.append("	     ltable_id   		= ?, 	");
    	query.append("	     ltabcol_id   		= ?, 	");
    	query.append("	     lusrrpttab_id   	= ?, 	");
    	query.append("	     rusrrpttab_id   	= ?, 	");
    	query.append("	     tab_con_operator   = ?, 	");
    	query.append("	     col_value_type   	= ?, 	");
    	query.append("	     rtable_id   		= ?, 	");
    	query.append("	     rtabcol_id   		= ?, 	");
    	query.append("	     con_value   		= ? 	");
    	query.append("WHERE  comp_no    		= ?		");
    	query.append("  AND  usrrptjoin_id  	= ?		");
    	
    	Object[] objects = new Object[] {
    			maUserRptJoinDetailDTO.getLtableId(),
    			maUserRptJoinDetailDTO.getLtabcolId(),
    			maUserRptJoinDetailDTO.getLusrrpttabId(),
    			maUserRptJoinDetailDTO.getRusrrpttabId(),
    			maUserRptJoinDetailDTO.getTabConOperator(),
    			maUserRptJoinDetailDTO.getColValueType(),
    			maUserRptJoinDetailDTO.getRtableId(),
    			maUserRptJoinDetailDTO.getRtabcolId(),
    			maUserRptJoinDetailDTO.getConValue(),
    			maUserRptJoinDetailDTO.getCompNo(),
    			maUserRptJoinDetailDTO.getUsrrptjoinId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int checkNextNum(MaUserRptJoinDetailDTO maUserRptJoinDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                                                            	");
    	query.append("       ISNULL(MAX(step_num),0)            								");
    	query.append("FROM   TAUSRRPTJOIN                                     				");
    	query.append("WHERE  1 = 1             												");
        query.append("  AND  usrrpttab_id = '"+maUserRptJoinDetailDTO.getUsrrpttabId()+"'   ");
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptJoinDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaUserRptJoinDetailDTO maUserRptJoinDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAUSRRPTJOIN (				                    	");
    	query.append("	comp_no,   		usrrptlist_id,    usrrpttab_id,    	 step_num, 	");
    	query.append("	usrrptjoin_id,  tab_con_operator, col_value_type,    ltable_id, ");
    	query.append("	ltabcol_id,  	rtable_id,        rtabcol_id,   	 con_value,	");
    	query.append("	lusrrpttab_id,  rusrrpttab_id 									");
    	query.append(")	VALUES (                                                		");
    	query.append("  ?,	       		?,            	?,          	  	?,        	");
    	query.append("  ?,	       		?,            	?,          	  	?,        	");
    	query.append("  ?,	       		?,            	?,          	  	?,         	");
    	query.append("  ?,	       		?         										");
    	query.append(")									                        		");
    	
    	Object[] objects = new Object[] {
    			maUserRptJoinDetailDTO.getCompNo(),
    			maUserRptJoinDetailDTO.getUsrrptlistId(),
    			maUserRptJoinDetailDTO.getUsrrpttabId(),
    			maUserRptJoinDetailDTO.getStepNum(),
    			maUserRptJoinDetailDTO.getUsrrptjoinId(),
    			maUserRptJoinDetailDTO.getTabConOperator(),
    			maUserRptJoinDetailDTO.getColValueType(),
    			maUserRptJoinDetailDTO.getLtableId(),
    			maUserRptJoinDetailDTO.getLtabcolId(),
    			maUserRptJoinDetailDTO.getRtableId(),
    			maUserRptJoinDetailDTO.getRtabcolId(),
    			maUserRptJoinDetailDTO.getConValue(),
    			maUserRptJoinDetailDTO.getLusrrpttabId(),
    			maUserRptJoinDetailDTO.getRusrrpttabId()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}