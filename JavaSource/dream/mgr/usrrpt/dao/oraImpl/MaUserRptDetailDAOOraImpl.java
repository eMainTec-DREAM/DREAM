package dream.mgr.usrrpt.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.usrrpt.dao.MaUserRptDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptDetailDTO;


/**
 * »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: MaUserRptDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maUserRptDetailDAOTarget"
 * @spring.txbn id="maUserRptDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUserRptDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaUserRptDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public MaUserRptDetailDTO findDetail(String usrrpt_id, String lang)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    	");
        query.append("       SFACODE_TO_DESC(x.usrdata_type,'USRDATA_TYPE','SYS','','"+lang+"')    usrdataTypeDesc,    	");
        query.append("       usrdata_type usrdataType,                            	");
        query.append("       title,                                                	");
        query.append("       description,                                        	");
        query.append("       cre_date creDate,                                    	");
        query.append("       dept_id creDept,                                    	");
        query.append("       (SELECT description                                	");
        query.append("        FROM   TADEPT a                                    	");
        query.append("        WHERE  a.dept_id = x.dept_id) creDeptDesc,        	");
        query.append("       cre_id creBy,                                        	");
        query.append("       (SELECT user_name                                    	");
        query.append("        FROM   TAUSER                                        	");
        query.append("        WHERE  user_id = x.cre_id) creByDesc,               	");
        query.append("       comp_no compNo,                                      	");
        query.append("       usrrptlist_id usrrptlistId                             ");
        query.append("FROM   TAUSRRPTLIST x     									");
        query.append("WHERE  x.usrrptlist_id = '"+usrrpt_id+"'                 		");

    
        MaUserRptDetailDTO DetailDTO = 
        		(MaUserRptDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaUserRptDetailDTO()));
        
        return DetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaUserRptDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int insertDetail(MaUserRptDetailDTO DetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAUSRRPTLIST                   	");
    	query.append("(		usrrptlist_id,  usrdata_type,           ");
    	query.append("     	comp_no,        dept_id,                ");
    	query.append("     	cre_id,        	cre_date,            	");
    	query.append("     	title,        	description)    	    ");
    	query.append("     VALUES        							");
    	query.append("(		?,             	?,                    	");
    	query.append("     	?,              ?,                      ");
    	query.append("     	?,             	?,                  	");
    	query.append("     	?,             	?  						");
    	query.append(")                                  			");

    	
    	Object[] objects = new Object[] {
    			DetailDTO.getUsrrptlistId(),
    			DetailDTO.getUsrdataType(),
    			loginUser.getCompNo(),
    			DetailDTO.getCreDept(),
    			DetailDTO.getCreBy(),
    			DetailDTO.getCreDate(),
    			DetailDTO.getTitle(),
    			DetailDTO.getDescription()

    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaUserRptDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int updateDetail(MaUserRptDetailDTO DetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSRRPTLIST SET   ");
    	query.append("	usrdata_type	= ?,	");
    	query.append("	dept_id		= ?,		");
    	query.append("	cre_id		= ?,		");
    	query.append("	cre_date	= ?,		");
    	query.append("	title		= ?,		");
    	query.append("	description	= ?			");
    	query.append("WHERE usrrptlist_id = ?	");
    	
    	Object[] objects = new Object[] {
    			DetailDTO.getUsrdataType(),
    			DetailDTO.getCreDept(),
    			DetailDTO.getCreBy(),
    			DetailDTO.getCreDate(),
    			DetailDTO.getTitle(),
    			DetailDTO.getDescription(),
    			DetailDTO.getUsrrptlistId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
	@Override
	public List<Map> findTableList(MaUserRptCommonDTO maUserRptCommonDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    	");
        query.append("       step_num                               stepNum,		");
        query.append("       table_name      						tableName,		");
        query.append("       main_sub_type                          mainSubType,	");
        query.append("       table_id                               tableId,		");
        query.append("       join_type                              joinType,		");
        query.append("       table_name||usrrpttab_id               key,			"); //TAPAGE2
        query.append("       usrrpttab_id                           usrrpttabId,	");
        query.append("       usrrptlist_id                          usrrptlistId 	");
        query.append("FROM   TAUSRRPTTAB x	    									");
        query.append("WHERE  usrrptlist_id  = '"+maUserRptCommonDTO.getUsrrptlistId()+"'");       
        query.append("ORDER BY step_num	                                    		");
        
        return getJdbcTemplate().queryForList(query.toString(), Map.class);
	}
	@Override
	public List<Map> findColList(MaUserRptCommonDTO maUserRptCommonDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 									");
        query.append("       table_name tableName,				");
        query.append("       column_name columnName,			");
        query.append("       step_num stepNum,					");
        query.append("       col_alias colAlias,				");
        query.append("       col_width colWidth,				");
        query.append("       col_size colSize,					");
        query.append("       col_align colAlign,				");
        query.append("       table_id tableId,					");
        query.append("       table_name||usrrpttab_id key,	 	");
        query.append("       usrrptcol_id usrrptcolId,			");
        query.append("       usrrpttab_id usrrpttabId,          ");
        query.append("       usrrptlist_id usrrptlistId,      	");
        query.append("       tabcol_id tabcolId      			");
        query.append("FROM   TAUSRRPTCOL  						");
        query.append("WHERE  usrrptlist_id  = '"+maUserRptCommonDTO.getUsrrptlistId()+"'");   
        query.append("  AND  use_yn = 'Y' 						");
        query.append("ORDER BY step_num	");

        return getJdbcTemplate().queryForList(query.toString(), Map.class);
	}
	@Override
	public List<Map> findParamList(MaUserRptCommonDTO maUserRptCommonDTO, User user) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    ");
        query.append("       table_name tableName,            	");
        query.append("       column_name columnName,            ");
        query.append("       step_num stepNum,                  ");
        query.append("       whr_con_operator whrConOperator,   ");
        query.append("       SFACODE_TO_DESC(whr_con_operator,'TAB_CON_OPERATOR','SYS','','"+user.getLangId()+"') whrConOperatorDesc,          	");
        query.append("       con_value conValue,                ");
        query.append("       table_name||usrrpttab_id key,		");
        query.append("       usrrptwhr_id usrrptwhrId,          ");
        query.append("       usrrpttab_id usrrpttabId,          ");
        query.append("       usrrptlist_id usrrptlistId,        ");
        query.append("       tabcol_id tabcolId               	");
        query.append("FROM   TAUSRRPTWHR   						");
        query.append("WHERE  usrrptlist_id  = '"+maUserRptCommonDTO.getUsrrptlistId()+"'"); 
        query.append("  AND  use_yn = 'Y' 						");
        query.append("ORDER BY step_num    	");

        return getJdbcTemplate().queryForList(query.toString(), Map.class);
	}
	@Override
	public List<Map> findOrdList(MaUserRptCommonDTO maUserRptCommonDTO, User user) {
		 QueryBuffer query = new QueryBuffer();
	        
	        query.append("SELECT 									");
	        query.append("       table_name tableName,				");
	        query.append("       column_name columnName,			");
	        query.append("       sort_type sortType,				");
	        query.append("       step_num stepNum,	    			");
	        query.append("       table_name||usrrpttab_id key,	    ");
	        query.append("       usrrptord_id usrrptordId,			");
	        query.append("       usrrpttab_id usrrpttabId,          ");
	        query.append("       usrrptlist_id usrrptlistId,      	");
	        query.append("       tabcol_id tabcolId   				");
	        query.append("FROM   TAUSRRPTORD 						");
	        query.append("WHERE  usrrptlist_id  = '"+maUserRptCommonDTO.getUsrrptlistId()+"'");
	        query.append("  AND  use_yn = 'Y' 						");
	        query.append("ORDER BY step_num							");

	        return getJdbcTemplate().queryForList(query.toString(), Map.class);
	}
	@Override
	public List<Map> findJoinList(MaUserRptCommonDTO maUserRptCommonDTO, User user) {

		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT               									");
        query.append("       step_num  stepNum,        		    			");
        query.append("       ltable_id ltableId,     						");
        query.append("       ltabcol_id ltabcolId,							");
        query.append("       tab_con_operator tabConOperator,				");
        query.append("       col_value_type colValueType,					");
        query.append("       rtable_id  rtableId,     						");
        query.append("       rtabcol_id rtabcolId,							");
        query.append("       con_value conValue, 							");
        query.append("       (SELECT a.column_name FROM TATABCOL a WHERE a.tabcol_id = ltabcol_id) ltabcolName,		");
        query.append("       (SELECT a.column_name FROM TATABCOL a WHERE a.tabcol_id = rtabcol_id) rtabcolName,	");
        query.append("       SFACODE_TO_DESC(tab_con_operator,'TAB_CON_OPERATOR','SYS','','"+user.getLangId()+"') tabConOperatorDesc,          	");
        query.append("       (SELECT a.table_name FROM tausrrpttab a WHERE a.usrrpttab_id = x.lusrrpttab_id)||lusrrpttab_id lkey,		");
        query.append("       (SELECT a.table_name FROM tausrrpttab a WHERE a.usrrpttab_id = x.rusrrpttab_id)||rusrrpttab_id rkey,   	");
        query.append("       usrrpttab_id  usrrpttabId,    					");
        query.append("       usrrptlist_id usrrptlistId,					");
        query.append("       usrrptjoin_id usrrptjoinId     				");
        query.append("FROM   TAUSRRPTJOIN x          						");
        query.append("WHERE  usrrptlist_id  = '"+maUserRptCommonDTO.getUsrrptlistId()+"'");
        query.append("ORDER BY step_num	                          			");
        
        return getJdbcTemplate().queryForList(query.toString(), Map.class);
	}
	
    public List findReportData(String script)
    {
        QueryBuffer query = new QueryBuffer();

        query.append(script);
        
        return getJdbcTemplate().queryForList(query.toString(), Map.class);
    }
}