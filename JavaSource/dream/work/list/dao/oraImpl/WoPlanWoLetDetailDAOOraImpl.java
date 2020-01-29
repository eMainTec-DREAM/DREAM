package dream.work.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.list.dao.WoPlanWoLetDetailDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;

/**
 * 작업계획목록 - 안전작업 상세 dao
 * @author  syyang
 * @version $Id$
 * @since   1.0
 * @spring.bean id="woPlanWoLetDetailDAOTarget"
 * @spring.txbn id="woPlanWoLetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WoPlanWoLetDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WoPlanWoLetDetailDAO
{
    /**
     * detail find
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     */
    public WoPlanWoLetDetailDTO findDetail(String wkOrId, String woWoLetListId, User user)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("SELECT															");
        query.append("		x.wowoletlist_id 						AS woWoLetListId	");
        query.append("  	,x.wkor_id 								AS wkorId			");
        query.append("		,x.woletctg_id 							AS woLetCtgId		");
        query.append("		,(SELECT aa.woletctg_no										");
        query.append("        FROM TAWOLETCTG aa										");
        query.append("        WHERE aa.comp_no = x.comp_no								");
        query.append("         AND aa.woletctg_id = x.woletctg_id						");
        query.append("       ) 										AS woLetCtgNo		");
        query.append("		,(SELECT aa.description										");
        query.append("        FROM TAWOLETCTG aa										");
        query.append("        WHERE aa.comp_no = x.comp_no								");
        query.append("         AND aa.woletctg_id = x.woletctg_id						");
        query.append("       ) 										AS woLetCtgDesc		");
        query.append("		,x.woletctg_type 						AS woLetCtgType		");
        query.append("		,SFACODE_TO_DESC(x.woletctg_type,'WOLETCTG_TYPE','SYS','','ko')	AS woLetCtgTypeDesc	");
        query.append("		,x.remark								AS remark			");
        query.append("FROM  TAWOWOLETLIST x												");
        query.append("WHERE 1=1 														");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.woWoLetList_id", woWoLetListId);
    
        WoPlanWoLetDetailDTO woPlanWoLetDetailDTO = 
        		(WoPlanWoLetDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new WoPlanWoLetDetailDTO()));
        
        return woPlanWoLetDetailDTO;
    }
    
    /**
     * detail update
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @param woPlanWoLetDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAWOWOLETLIST SET			");
    	query.append("		woletctg_id			= ?		");
    	query.append("		,woletctg_type		= ?		");
    	query.append("		,remark				= ?		");
    	query.append("WHERE comp_no				= ?		");
    	query.append("  AND wowoletlist_id		= ?		");
    	query.append("  AND wkor_id				= ?		");
    	
    	Object[] objects = new Object[] {
    			woPlanWoLetDetailDTO.getWoLetCtgId()
    			,woPlanWoLetDetailDTO.getWoLetCtgType()
    			,woPlanWoLetDetailDTO.getRemark()
    			, user.getCompNo()
    			,woPlanWoLetDetailDTO.getWoWoLetListId()
    			,woPlanCommonDTO.getWkOrId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @param woPlanWoLetDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAWOWOLETLIST				");
    	query.append("	( comp_no		,wowoletlist_id		");
    	query.append("	 ,wkor_id		,woletctg_id		");
    	query.append("	 ,woletctg_type	,remark				");
    	query.append("	)	VALUES							");
    	query.append("	( ?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	 ,(SELECT aa.woletctg_type 			");
    	query.append("	   FROM TAWOLETCTG aa  				");
    	query.append("	   where aa.comp_no = ? 			");
    	query.append("	    AND aa.woletctg_id = ? 			");
    	query.append("	  )				,?					");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,woPlanWoLetDetailDTO.getWoWoLetListId()
    			,woPlanCommonDTO.getWkOrId()
    			,woPlanWoLetDetailDTO.getWoLetCtgId()
    			,user.getCompNo()
    			,woPlanWoLetDetailDTO.getWoLetCtgId()
    			,woPlanWoLetDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public String validWoLet(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user)
	{
		QueryBuffer query = new QueryBuffer();
		
    	query.append("SELECT count(*) 			");
    	query.append("FROM TAWOWOLETLIST x		");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.wkor_id", woPlanCommonDTO.getWkOrId());
    	query.getAndQuery("x.woletctg_id", woPlanWoLetDetailDTO.getWoLetCtgId());
    	
    	if(!"".equals(woPlanWoLetDetailDTO.getWoWoLetListId()))
    	{
    		query.append("AND x.wowoletlist_id != " + woPlanWoLetDetailDTO.getWoWoLetListId());
    	}
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
	}
    
}