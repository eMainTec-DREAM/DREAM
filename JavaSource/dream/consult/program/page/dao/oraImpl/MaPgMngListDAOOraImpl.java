package dream.consult.program.page.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class MaPgMngListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPgMngListDAO
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
        QueryBuffer query = new QueryBuffer();

        if (!"".equals(maPgMngCommonDTO.getFilterMenuId()) || !"".equals(maPgMngCommonDTO.getFilterMenuDesc()))
        {
            String menuId = maPgMngCommonDTO.getFilterMenuId();
            String menuDesc = maPgMngCommonDTO.getFilterMenuDesc();
            
            query.setClear();
            query.append("SELECT                                                    ");
            query.append("       ROWNUM ID                                          ");
            query.append("       ,'' seqNo                                              ");
            query.append("       ,'' isDelCheck                                                                     ");
            query.append("       ,pageId                                            ");
            query.append("       ,pPageId                                           ");
            query.append("       ,pageDesc                                      ");
            query.append("       ,fileName                              ");
            query.append("       ,REMARK        ");
            query.append("       ,isUse     ");
            query.append("       ,pageType      ");
            query.append("       ,pageCateg     ");
            query.append("       ,isChkauth     ");
            query.append("       ,LEVEL lvl                                                                         ");
            query.append("       ,SUBSTR(SYS_CONNECT_BY_PATH(pageid, ','), 2) curpath               ");
            query.append("       , CASE  SUBSTR(SYS_CONNECT_BY_PATH(pageid, ','), 2)                ");
            query.append("         WHEN  REPLACE(SUBSTR(SYS_CONNECT_BY_PATH(pageid, ','), 2), ','||pageid)   THEN ''            ");
            query.append("         ELSE  REPLACE(SUBSTR(SYS_CONNECT_BY_PATH(pageid, ','), 2), ','||pageid)          ");
            query.append("         END ppath            ");
            query.append("FROM (                                                    ");
            query.append("        SELECT                                                ");
            query.append("            y.page_id  pPageId                            ");
            query.append("            ,NVL(y.c_page_id, x.page_id) pageId           ");
            query.append("            ,x.description           pageDesc                     ");
            query.append("            ,x.file_name         fileName                     ");
            query.append("            ,x.REMARK   REMARK        ");
            query.append("            ,x.page_categ   pageCateg     ");
            query.append("            ,SFACODE_TO_DESC(x.page_type,'PAGE_TYPE','SYS','','"+user.getLangId()+"')            pageType       ");
            query.append("            , x.is_use isUse      ");
            query.append("            ,x.is_chkauth isChkauth       ");
            query.append("            ,y.ord_no ordNo                               ");
            query.append("            ,pgpage_id                                    ");
            query.append("      FROM TAPAGE x,  TAPGPAGE y                          ");
            query.append("      WHERE x.page_id = y.c_page_id(+)                    ");
            query.append("        AND x.is_chkauth = 'Y'                            ");
            query.append("        AND x.is_use ='Y'                                 ");
            query.append(this.getWhere(maPgMngCommonDTO,user));
            query.append("     )x                                                   ");
            query.append("START WITH pageId IN (SELECT a.page_id FROM TAMENU a WHERE a.menu_id IN    ");

            if("".equals(menuId))
            {
                query.append("    (                                 ");
                query.append("SELECT c.menu_id      ");
                query.append("FROM TAMENU c         ");
                query.append("WHERE c.key_no  IN (SELECT d.key_no       ");
                query.append("                    FROM TALANG d      ");
                query.append("                    WHERE d.lang = '"+user.getLangId()+"'        ");
                query.getLikeQuery("d.key_name", menuDesc);
                query.append("                               ))      ");

            }
            else if(!"".equals(menuId))
            {
                query.append("  '"+menuId+"'  ");
            }
            
            query.append("                               )      ");
            query.append("CONNECT BY PRIOR pageId = pPageId                         ");
            //query.append("ORDER SIBLINGS BY ordNo                                   ");
            query.getOrderByQuery("ordNo", maPgMngCommonDTO.getOrderBy(), maPgMngCommonDTO.getDirection());
        }

        else if("".equals(maPgMngCommonDTO.getFilterMenuId()))
        {
            query.append("SELECT                                                ");
            query.append("       '' seqNo,                                      ");
            query.append("       '' isDelCheck,                                 ");
            query.append("       x.page_id pageId,                              ");
            query.append("       x.file_name fileName,                          ");
            query.append("       x.description pageDesc,                        ");
            query.append("       x.remark remark,                               ");
            query.append("       x.is_use isUse,                                ");
            query.append("       x.page_categ pageCateg,                                ");
            query.append("       SFACODE_TO_DESC(x.page_type,'PAGE_TYPE','SYS','','"+user.getLangId()+"')           pageType,   ");
            query.append("       x.is_chkauth isChkauth                         ");
            query.append("FROM   TAPAGE x                                       ");
            query.append("WHERE  1=1                                            ");
            query.append(this.getWhere(maPgMngCommonDTO,user));
            //query.append("ORDER by file_name                                    ");
            query.getOrderByQuery("file_name", maPgMngCommonDTO.getOrderBy(), maPgMngCommonDTO.getDirection());
        }
        
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
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	String pageId=id;
    	
    	query.append("update TAEHMENU set 				                                                ");
    	query.append("        page_id = null, file_name = ''    	                                    ");
    	query.append("where page_id = '"+pageId+"'	                                                 	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	query.append("update TAMENU set 				                                                ");
    	query.append("        page_id = null     		                                                ");
    	query.append("where page_id = '"+pageId+"'	                                                 	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	query.append("DELETE FROM TAPAGE				");
    	query.append("WHERE page_id = '"+pageId+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	query.append("DELETE FROM TAPGBTN				");
    	query.append("WHERE page_id = '"+pageId+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	query.append("DELETE FROM TAPGPAGE				");
    	query.append("WHERE page_id = '"+pageId+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	query.append("DELETE FROM TAPGFIELD				");
    	query.append("WHERE page_id = '"+pageId+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	query.append("DELETE FROM TAPGGRIDCOL				                                ");
    	query.append("WHERE  pggrid_id in (  				                                ");
    	query.append("                     select pggrid_id from TAPGGRID   				");
    	query.append("                     WHERE page_id = '"+pageId+"'		                ");
    	query.append("                    )	                                                ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	query.append("DELETE FROM TAPGGRID				");
    	query.append("WHERE page_id = '"+pageId+"'		");
    	
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
        QueryBuffer query = new QueryBuffer();
        
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
        return query.toString();
    }

	@Override
	public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, User user) throws Exception
	{
        QueryBuffer query = new QueryBuffer();

        if (!"".equals(maPgMngCommonDTO.getFilterMenuId()) || !"".equals(maPgMngCommonDTO.getFilterMenuDesc()))
        {
            String menuId = maPgMngCommonDTO.getFilterMenuId();
            String menuDesc = maPgMngCommonDTO.getFilterMenuDesc();
            
            query.setClear();
            query.append("SELECT                                    ");
            query.append("       COUNT(1)							");
            query.append("FROM (                                    ");
            query.append("        SELECT                            ");
            query.append("            y.page_id  pPageId            ");
            query.append("            ,NVL(y.c_page_id, x.page_id) pageId           ");
            query.append("      FROM TAPAGE x,  TAPGPAGE y          ");
            query.append("      WHERE x.page_id = y.c_page_id(+)    ");
            query.append("        AND x.is_chkauth = 'Y'            ");
            query.append("        AND x.is_use ='Y'                 ");
            query.append(this.getWhere(maPgMngCommonDTO,user));
            query.append("     )x                                   ");
            query.append("START WITH pageId IN (SELECT a.page_id FROM TAMENU a WHERE a.menu_id IN    ");

            if("".equals(menuId))
            {
                query.append("    (                                 ");
                query.append("SELECT c.menu_id      				");
                query.append("FROM TAMENU c         				");
                query.append("WHERE c.key_no  IN (SELECT d.key_no   ");
                query.append("                    FROM TALANG d     ");
                query.append("                    WHERE d.lang = '"+user.getLangId()+"'        ");
                query.getLikeQuery("d.key_name", menuDesc);
                query.append("                               ))     ");

            }
            else if(!"".equals(menuId))
            {
                query.append("  '"+menuId+"'  ");
            }
            
            query.append("                               )      ");
            query.append("CONNECT BY PRIOR pageId = pPageId                         ");
        }

        else if("".equals(maPgMngCommonDTO.getFilterMenuId()))
        {
            query.append("SELECT                ");
            query.append("       COUNT(1)		");
            query.append("FROM   TAPAGE x       ");
            query.append("WHERE  1=1            ");
            query.append(this.getWhere(maPgMngCommonDTO,user));
        }
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}