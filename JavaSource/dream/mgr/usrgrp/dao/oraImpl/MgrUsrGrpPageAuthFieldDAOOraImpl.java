package dream.mgr.usrgrp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrgrp.dao.MgrUsrGrpPageAuthFieldDAO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthFieldDTO;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrUsrGrpPageAuthFieldDAOTarget"
 * @spring.txbn id="mgrUsrGrpPageAuthFieldDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrUsrGrpPageAuthFieldDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrUsrGrpPageAuthFieldDAO
{
	
    public String getColums(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT																								");
        query.append("  '' 																				ISDELCHECK			");
        query.append("  ,'' 																			SEQNO				");
        query.append("  ,x.field_id 																	FIELDID				");
        query.append("  ,(SELECT a.key_name FROM TALANG a 																	");
        query.append("  	WHERE a.key_no = x.key_no 																		");
        query.append("  	AND a.key_type = x.key_type 																	");
        query.append("  	AND a.lang = '"+user.getLangId()+"') 										LABELDESC			");
        query.append("  ,x.ord_no 																		ORDNO				");
        query.append("  ,NVL(z.auth_limit_type, 'NL')													AUTHLIMITTYPEID		");
        query.append("  ,SFACODE_TO_DESC(NVL(z.auth_limit_type,'NL'),'AUTH_LIMIT_TYPE','SYS','','"+user.getLangId()+"') 	AUTHLIMITTYPEDESC	");
        query.append("  ,y.usrgrp_id																	USRGRPID			");
        query.append("  ,z.ugpgfieldau_id 																UGPGFIELDAUID		");
        query.append("  ,x.pgfield_id 																	PGFIELDID			");
        query.append("  ,x.page_id 																		PAGEID				");
	    query.append("  ,x.check_yn 																	CHECKYN				");
        
        return query.toString();
    }
    
    public String getTables(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" FROM TAPGFIELD x																					");
        query.append("INNER JOIN TAUSRGRP y																				");
        query.append("ON 1=1																							");
        query.append("LEFT OUTER JOIN TAUGPGFIELDAU z 																	");
        query.append("ON x.field_id = z.field_id																		");
        query.append("AND x.page_id = z.page_id																			");
        query.append("AND y.comp_no = z.comp_no																			");
        query.append("AND y.usrgrp_id = z.usrgrp_id																		");
        
        return query.toString();
    }
    
    public String getOrderBy(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getOrderByQuery("x.ord_no", mgrUsrGrpPageAuthFieldDTO.getOrderBy(), mgrUsrGrpPageAuthFieldDTO.getDirection());
        
        return query.toString();
    }
    
    @Override
    public List findList(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(getColums(mgrUsrGrpPageAuthFieldDTO,user));
        query.append(getTables(mgrUsrGrpPageAuthFieldDTO,user));
        query.append(this.getWhere(mgrUsrGrpPageAuthFieldDTO, user));
        query.append(getOrderBy(mgrUsrGrpPageAuthFieldDTO,user));
        
        return getJdbcTemplate().queryForList(query.toString(mgrUsrGrpPageAuthFieldDTO.getIsLoadMaxCount(), mgrUsrGrpPageAuthFieldDTO.getFirstRow()));
    }

    @Override
    public String findTotalCount(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                        ");
        query.append("    COUNT(1)                                                                                                  ");
        query.append(getTables(mgrUsrGrpPageAuthFieldDTO,user));
        query.append(this.getWhere(mgrUsrGrpPageAuthFieldDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
     }
    
    public String getWhere(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.append("WHERE 1=1");
        query.getStringEqualQuery("x.hidden_yn", "N");
        query.getStringEqualQuery("NVL(x.system_yn,'N')", "N");
        query.getStringEqualQuery("y.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.page_id", mgrUsrGrpPageAuthFieldDTO.getPageId());
        query.getStringEqualQuery("y.usrgrp_id", mgrUsrGrpPageAuthFieldDTO.getUsrgrpId());
        query.getStringEqualQuery("x.pgfield_id", mgrUsrGrpPageAuthFieldDTO.getPgfieldId());

        if(!"".equals(mgrUsrGrpPageAuthFieldDTO.getUgpgfieldauId())){
            query.getAndQuery("z.ugpgfieldau_id", mgrUsrGrpPageAuthFieldDTO.getUgpgfieldauId());
            return query.toString();
        }
        
        return query.toString();
    }
    
	@Override
	public int updateDetail(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) {

		QueryBuffer query = new QueryBuffer();
		
        query.append("UPDATE TAUGPGFIELDAU  	     ");
        query.append("SET auth_limit_type 	= ?    	 ");
        query.append("WHERE 1=1				    	 ");
        query.append("AND comp_no      		= ?      ");
        query.append("AND field_id    		= ?      ");
        query.append("AND page_id      		= ?      ");
        query.append("AND usrgrp_id    		= ?      ");
        
        Object[] objects = new Object[] {
       		mgrUsrGrpPageAuthFieldDTO.getAuthLimitTypeId()
       		,user.getCompNo()
       		,mgrUsrGrpPageAuthFieldDTO.getFieldId()
       		,mgrUsrGrpPageAuthFieldDTO.getPageId()
       		,mgrUsrGrpPageAuthFieldDTO.getUsrgrpId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    	
	}

	@Override
	public int insertAuStatus(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) {
	
		QueryBuffer query = new QueryBuffer();
		
		query.append("INSERT INTO TAUGPGFIELDAU (               ");
    	query.append("   comp_no      	,ugpgfieldau_id			");
    	query.append("   ,usrgrp_id		,page_id				");
    	query.append("   ,field_id    	,auth_limit_type		");
    	query.append("   ,file_name								");
    	query.append(")VALUES(							        ");
    	query.append("	 ?			   ,?		   				");
    	query.append("	 ,?			   ,?		   				");
    	query.append("   ,?            ,?						");
    	query.append("   ,(SELECT file_name FROM TAPAGE WHERE page_id = ?)");
    	query.append(")											");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,mgrUsrGrpPageAuthFieldDTO.getUgpgfieldauId()
    			,mgrUsrGrpPageAuthFieldDTO.getUsrgrpId()
    			,mgrUsrGrpPageAuthFieldDTO.getPageId()
    			,mgrUsrGrpPageAuthFieldDTO.getFieldId()
    			,mgrUsrGrpPageAuthFieldDTO.getAuthLimitTypeId()
    			,mgrUsrGrpPageAuthFieldDTO.getPageId()
    			
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public int updateAuStatus(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
		
        query.append("UPDATE TAUGPGFIELDAU  	     ");
        query.append("SET auth_limit_type 	= ?    	 ");
        query.append("WHERE 1=1				    	 ");
        query.append("AND comp_no      		= ?      ");
        query.append("AND field_id    		= ?      ");
        query.append("AND page_id      		= ?      ");
        query.append("AND usrgrp_id    		= ?      ");
        
        Object[] objects = new Object[] {
       		mgrUsrGrpPageAuthFieldDTO.getAuthLimitTypeId()
       		,user.getCompNo()
       		,mgrUsrGrpPageAuthFieldDTO.getFieldId()
       		,mgrUsrGrpPageAuthFieldDTO.getPageId()
       		,mgrUsrGrpPageAuthFieldDTO.getUsrgrpId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
	}

}