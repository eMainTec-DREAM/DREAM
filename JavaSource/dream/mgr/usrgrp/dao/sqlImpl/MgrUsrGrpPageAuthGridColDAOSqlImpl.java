package dream.mgr.usrgrp.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.usrgrp.dao.MgrUsrGrpPageAuthGridColDAO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthGridColDTO;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrUsrGrpPageAuthGridColDAOTarget"
 * @spring.txbn id="mgrUsrGrpPageAuthGridColDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrUsrGrpPageAuthGridColDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrUsrGrpPageAuthGridColDAO
{

	@Override
	public String getColums(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) {
        
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT 																							");
        query.append("  '' 																			ISDELCHECK			");
        query.append("  ,'' 																		SEQNO				");
        query.append("  ,xx.grid_obj_id																GRIDID				");
        query.append("  ,x.column_id																COLUMNID			");
        query.append("  ,(SELECT a.key_name FROM TALANG a 																");
        query.append("  	WHERE a.key_no = x.key_no 																	");
        query.append("  	AND a.key_type = x.key_type 																");
        query.append("  	AND a.lang = '"+user.getLangId()+"') 									LABELDESC			");
        query.append("  ,x.ord_no 																	ORDNO				");
        query.append("  ,ISNULL(z.auth_limit_type, 'NL')											AUTHLIMITTYPEID		");
        query.append("  ,dbo.SFACODE_TO_DESC(ISNULL(z.auth_limit_type,'NL'),'AUTH_LIMIT_TYPE','SYS','','"+user.getLangId()+"') 	AUTHLIMITTYPEDESC	");
        query.append("  ,x.pggridcol_id 															PGGRIDCOLID			");
        query.append("  ,y.usrgrp_id 																USRGRPID			");
        query.append("  ,xx.page_id 																PAGEID				");
        query.append("  ,z.ugpgridcolau_id															UGPGRIDCOLAUID		");
        
        return query.toString();
	}

	@Override
	public String getTables(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) {
        
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("FROM TAPGGRIDCOL x					");
        query.append("INNER JOIN TAPGGRID xx				");
        query.append("ON x.pggrid_id = xx.pggrid_id			");
        query.append("INNER JOIN TAUSRGRP y 				");
        query.append("ON 1=1								");
        query.append("LEFT OUTER JOIN TAUGPGRIDCOLAU z		");
        query.append("ON 1=1								");
        query.append("AND xx.page_id 		= z.page_id		");
        query.append("AND xx.grid_obj_id 	= z.grid_obj_id	");
        query.append("AND x.column_id 	= z.column_id		");
        query.append("AND y.usrgrp_id 	= z.usrgrp_id		");
        query.append("AND y.comp_no 	= z.comp_no			");
        
        return query.toString();
	}

	@Override
	public String getOrderBy(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) {
        
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.getOrderByQuery("x.pggridcol_id", "x.ord_no", mgrUsrGrpPageAuthGridColDTO.getOrderBy(), mgrUsrGrpPageAuthGridColDTO.getDirection());
        
        return query.toString();
	}

	@Override
	public List findList(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception {
        
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append(getColums(mgrUsrGrpPageAuthGridColDTO,user));
        query.append(getTables(mgrUsrGrpPageAuthGridColDTO,user));
        query.append(this.getWhere(mgrUsrGrpPageAuthGridColDTO, user));
        query.append(getOrderBy(mgrUsrGrpPageAuthGridColDTO,user));
        
        return getJdbcTemplate().queryForList(query.toString(mgrUsrGrpPageAuthGridColDTO.getIsLoadMaxCount(), mgrUsrGrpPageAuthGridColDTO.getFirstRow()));
    
	}

	@Override
	public String findTotalCount(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception {
        
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                                                                        ");
        query.append("    COUNT(1)                                                                                                  ");
        query.append(getTables(mgrUsrGrpPageAuthGridColDTO,user));
        query.append(this.getWhere(mgrUsrGrpPageAuthGridColDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
   
	}

	@Override
	public String getWhere(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) {
        
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("WHERE 1=1			");
        query.getStringEqualQuery("x.hidden", "false");
        query.getStringEqualQuery("x.system_col", "N");
        query.getStringEqualQuery("y.comp_no", user.getCompNo());
        query.getStringEqualQuery("y.usrgrp_id", mgrUsrGrpPageAuthGridColDTO.getUsrgrpId());
        query.getStringEqualQuery("xx.page_id", mgrUsrGrpPageAuthGridColDTO.getPageId());
        
        if(!"".equals(mgrUsrGrpPageAuthGridColDTO.getPggridcolId())){
            query.getAndQuery("x.pggridcol_id", mgrUsrGrpPageAuthGridColDTO.getPggridcolId());
            return query.toString();
        }
        
        return query.toString();
	}

	@Override
	public int updateDetail(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) {
        
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAUGPGRIDCOLAU  		     ");
        query.append("SET auth_limit_type 		= ?    	 ");
        query.append("WHERE 1=1							 ");
        query.append("AND comp_no      			= ?      ");
        query.append("AND usrgrp_id				= ?		 ");
        query.append("AND grid_obj_id			= ?		 ");
        query.append("AND column_id				= ?		 ");
        query.append("AND page_id				= ?		 ");
        
        Object[] objects = new Object[] {
        		mgrUsrGrpPageAuthGridColDTO.getAuthLimitTypeId()
        		,user.getCompNo()
        		,mgrUsrGrpPageAuthGridColDTO.getUsrgrpId()
        		,mgrUsrGrpPageAuthGridColDTO.getGridId()
        		,mgrUsrGrpPageAuthGridColDTO.getColumnId()
        		,mgrUsrGrpPageAuthGridColDTO.getPageId()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    	
	}

	@Override
	public int insertAuStatus(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) {
        
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAUGPGRIDCOLAU (              ");
    	query.append("   comp_no      	,ugpgridcolau_id		");
    	query.append("   ,usrgrp_id		,auth_limit_type		");
    	query.append("   ,column_id    	,page_id				");
    	query.append("   ,grid_obj_id   ,file_name				");
    	query.append(")VALUES(							        ");
    	query.append("	 ?			   ,?		   				");
    	query.append("	 ,?			   ,?		   				");
    	query.append("   ,?            ,?						");
    	query.append("   ,?            ,(SELECT file_name FROM TAPAGE WHERE page_id = ?)");
    	query.append(")											");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,mgrUsrGrpPageAuthGridColDTO.getUgpgridcolauId()
    			,mgrUsrGrpPageAuthGridColDTO.getUsrgrpId()
    			,mgrUsrGrpPageAuthGridColDTO.getAuthLimitTypeId()
    			,mgrUsrGrpPageAuthGridColDTO.getColumnId()
    			,mgrUsrGrpPageAuthGridColDTO.getPageId()
    			,mgrUsrGrpPageAuthGridColDTO.getGridId()
    			,mgrUsrGrpPageAuthGridColDTO.getPageId()
    		
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public int updateAuStatus(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAUGPGRIDCOLAU  	     ");
        query.append("SET auth_limit_type 	= ?    	 ");
        query.append("WHERE 1=1				    	 ");
        query.append("AND comp_no      		= ?      ");
        query.append("AND grid_obj_id  		= ?      ");
        query.append("AND column_id    		= ?      ");
        query.append("AND page_id      		= ?      ");
        query.append("AND usrgrp_id    		= ?      ");
        
        Object[] objects = new Object[] {
        	mgrUsrGrpPageAuthGridColDTO.getAuthLimitTypeId()
       		,user.getCompNo()
       		,mgrUsrGrpPageAuthGridColDTO.getGridId()
       		,mgrUsrGrpPageAuthGridColDTO.getColumnId()
       		,mgrUsrGrpPageAuthGridColDTO.getPageId()
       		,mgrUsrGrpPageAuthGridColDTO.getUsrgrpId()
    	};
        
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
	}
}