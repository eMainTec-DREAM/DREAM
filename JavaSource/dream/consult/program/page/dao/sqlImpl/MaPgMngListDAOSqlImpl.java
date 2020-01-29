package dream.consult.program.page.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.MaPgMngListDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면 - 목록 dao
 * @author  kim21017
 * @version $Id: MaPgMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPgMngListDAOTarget"
 * @spring.txbn id="maPgMngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPgMngListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPgMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPgMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @return List
     */
    public List findPgList(MaPgMngCommonDTO maPgMngCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                ");
        query.append("       '' seqNo,                                      ");
        query.append("       '' isDelCheck,                                 ");
        query.append("       x.page_id pageId,                              ");
        query.append("       x.file_name fileName,                          ");
        query.append("       x.description pageDesc,                        ");
        query.append("       x.remark remark,                               ");
        query.append("       x.is_use isUse,                                ");
        query.append("       x.page_categ pageCateg,                                ");
        query.append("       dbo.SFACODE_TO_DESC(x.page_type,'PAGE_TYPE','SYS','','"+user.getLangId()+"')           pageType,   ");
        query.append("       x.is_chkauth isChkauth                         ");
        query.append("FROM   TAPAGE x                                       ");
        query.append("WHERE  1=1                                            ");
        query.append(this.getWhere(maPgMngCommonDTO,user));
        //query.append("ORDER by file_name                                    ");
        query.getOrderByQuery("x.page_id","x.file_name", maPgMngCommonDTO.getOrderBy(), maPgMngCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPgMngCommonDTO.getIsLoadMaxCount(),maPgMngCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPgMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDTOList
     * @return
     */
    public int deletePage(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	String pageId=id;
    	
    	query.append("update TAEHMENU set 				                                                ");
    	query.append("        page_id = null, file_name = ''    	                                    ");
    	query.append("where page_id = "+pageId+" 	                                                 	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	
    	query = new QuerySqlBuffer();
    	query.append("update TAMENU set 				                                                ");
    	query.append("        page_id = null     		                                                ");
    	query.append("where page_id = "+pageId+"	                                                 	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
    	query.append("DELETE FROM TAPAGE				");
    	query.append("WHERE page_id = "+pageId+"		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
    	query.append("DELETE FROM TAPGBTN				");
    	query.append("WHERE page_id = "+pageId+"		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
    	query.append("DELETE FROM TAPGPAGE				");
    	query.append("WHERE page_id = "+pageId+"		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
    	query.append("DELETE FROM TAPGFIELD				");
    	query.append("WHERE page_id = "+pageId+"		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
    	query.append("DELETE FROM TAPGGRIDCOL				                                ");
    	query.append("WHERE  pggrid_id in (  				                                ");
    	query.append("                     select pggrid_id from TAPGGRID   				");
    	query.append("                     WHERE page_id = "+pageId+"		                ");
    	query.append("                    )	                                                ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
    	query.append("DELETE FROM TAPGGRID				");
    	query.append("WHERE page_id = "+pageId+"		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaPgMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPgMngCommonDTO maPgMngCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        String lang = user.getLangId();
        
        if (!"".equals(maPgMngCommonDTO.getPageId()))
        {
            query.getAndQuery("x.page_id", maPgMngCommonDTO.getPageId());
            return query.toString();
        }
        query.getLikeQuery("x.file_name", maPgMngCommonDTO.getFilterFileName());
        query.getLikeQuery("x.description", maPgMngCommonDTO.getFilterPageDesc());
        
        query.getSysCdQuery("x.page_type", maPgMngCommonDTO.getFilterPageTypeId(), maPgMngCommonDTO.getFilterPageType(), "PAGE_TYPE", compNo,lang);
        
        query.getLikeQuery("x.page_categ", maPgMngCommonDTO.getFilterPageCateg());
        //설정매뉴에서 파라미터를 던지면 그 값은 고정으로 셋팅되어야 함.
        query.getLikeQuery("x.page_categ", maPgMngCommonDTO.getParamPageType());
        
        if(!"".equals(maPgMngCommonDTO.getFilterMenuId())) {
            query.append("AND x.page_id IN (SELECT page_id FROM dbo.SFAPGPAGEINMENU_ALL("+maPgMngCommonDTO.getFilterMenuId()+"))");
        }
        
        return query.toString();
    }

	@Override
	public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, User user) throws Exception
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT              	");
        query.append("       COUNT(1)		");
        query.append("FROM   TAPAGE x      	");
        query.append("WHERE  1=1           	");
        query.append(this.getWhere(maPgMngCommonDTO,user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
}