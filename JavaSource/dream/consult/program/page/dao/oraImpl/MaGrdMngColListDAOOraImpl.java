package dream.consult.program.page.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.consult.program.page.dao.MaGrdMngColListDAO;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;

/**
 * Ä®·³ ¸ñ·Ï dao
 * @author  jung7126
 * @version $Id: MaGrdMngColListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maGrdMngColListDAOTarget"
 * @spring.txbn id="maGrdMngColListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaGrdMngColListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaGrdMngColListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaGrdMngColListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngCommonDTO
     * @param user 
     * @return List
     */
    public List findColList(MaGrdMngCommonDTO maGrdMngCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                        ");
        query.append("       ''                                                                                                  seqNo,        ");
        query.append("       ''                                                                                                  isDelCheck,  ");
        query.append("       z.file_name                                                                                    fileName,     ");
        query.append("       z.description                                                                                  pageDesc,   ");
        query.append("       z.is_use                                                                                          pageIsUse,  ");
        query.append("       y.grid_obj_id                                                                                  gridObjId,    ");
        query.append("       y.description                                                                                  gridDesc,    ");
        query.append("       x.column_id                                                                                    columnId,   ");
        query.append("       (SELECT a.key_name                                                     ");
        query.append("        FROM   TALANG a                                                       ");
        query.append("        WHERE  a.key_type = 'LABEL'                                           ");
        query.append("          AND  a.lang = '"+user.getLangId()+"'                                ");
        query.append("          AND  a.key_no = x.key_no)                                                            columnDesc,");
        query.append("       SFACODE_TO_DESC(x.type,'COLUMN_TYPE','SYS','','"+user.getLangId()+"') type,        ");
        query.append("       x.width                                                                                          width,       ");
        query.append("       x.ord_no                                                                                        ordNo,       ");
        query.append("       SFACODE_TO_DESC(x.align,'ALIGN_TYPE','SYS','','"+user.getLangId()+"')    alignDesc,  ");
        query.append("       DECODE(UPPER(x.hidden),'TRUE','Y','N')                                                as hidden,      ");
        query.append("       UPPER(x.display_yn)                                                 as displayYn,      ");
        query.append("       x.system_col                                                                                  systemCol,  ");
        query.append("       x.pggridcol_id                                                                                 pggridcolId  ");
        query.append("FROM   TAPGGRIDCOL x INNER JOIN TAPGGRID y                     ");
        query.append("ON x.pggrid_id = y.pggrid_id                                                  ");
        query.append("INNER JOIN TAPAGE z                                                       ");
        query.append("ON y.page_id=z.page_id                                                    ");
        query.append("WHERE  1=1													                ");
        query.append(this.getWhere(maGrdMngCommonDTO, user));
        query.getOrderByQuery("z.file_name, y.grid_obj_id, x.ord_no", maGrdMngCommonDTO.getOrderBy(), maGrdMngCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maGrdMngCommonDTO.getIsLoadMaxCount(), maGrdMngCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaGrdMngColListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteColList(String id)
    {
    	QueryBuffer query = new QueryBuffer();

    	String grdColId=id;
    	
    	query.append("DELETE FROM TAPGGRIDCOL					    ");
    	query.append("WHERE  pggridcol_id 	= '"+grdColId+"'		");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaGrdMngCommonDTO maGrdMngCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.pggrid_id", maGrdMngCommonDTO.getPgGridId());
    	if (!"".equals(maGrdMngCommonDTO.getPgGridColId()))
        {
            query.getAndQuery("x.pggridcol_id", maGrdMngCommonDTO.getPgGridColId());
            return query.toString();
        }
    	
    	if(!"".equals(maGrdMngCommonDTO.getFilterPageId())) {
    	    query.getAndQuery("z.page_id", maGrdMngCommonDTO.getFilterPageId());
    	}
    	else {
    	    query.getLikeQuery("z.file_name", maGrdMngCommonDTO.getFilterFileName());
    	    query.getLikeQuery("z.description", maGrdMngCommonDTO.getFilterPageDesc());
    	}
    	
    	query.getLikeQuery("y.grid_obj_id", maGrdMngCommonDTO.getFilterGridObjId());
    	query.getLikeQuery("y.description", maGrdMngCommonDTO.getFilterGridDesc());
    	query.getLikeQuery("x.column_id", maGrdMngCommonDTO.getFilterColumnId());
    	
    	if(!"".equals(maGrdMngCommonDTO.getFilterColumnDesc())) {
    	    query.append("AND x.key_no IN (SELECT key_no FROM TALANG     ");
    	    query.append("                        WHERE 1=1     ");
    	    query.getLikeQuery("key_name", maGrdMngCommonDTO.getFilterColumnDesc());
    	    query.append("                        AND key_type = 'LABEL'        ");
    	    query.append("                        AND lang = '"+user.getLangId()+"'       ");
    	    query.append("                        )                                                 ");
    	}
    	
    	query.getSysCdQuery("x.type", maGrdMngCommonDTO.getFilterType(), maGrdMngCommonDTO.getFilterTypeDesc(), "COLUMN_TYPE", "", user.getLangId());
    	query.getSysCdQuery("x.align", maGrdMngCommonDTO.getFilterAlign(), maGrdMngCommonDTO.getFilterAlignDesc(), "ALIGN_TYPE", "", user.getLangId());
    	query.getLikeQuery("DECODE(UPPER(x.hidden),'TRUE','Y','N')", maGrdMngCommonDTO.getFilterHidden());
    	query.getLikeQuery("x.system_col", maGrdMngCommonDTO.getFilterSystemCol());
    	
    	return query.toString();
    }
    
    public int sysYColList(String id)
    {
        QueryBuffer query = new QueryBuffer();

        String grdColId=id;
        
        query.append("UPDATE TAPGGRIDCOL SET system_col = 'Y', hidden = 'true'       ");
        query.append("WHERE  pggridcol_id   = '"+grdColId+"'        ");
        
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    public int sysNColList(String id)
    {
        QueryBuffer query = new QueryBuffer();

        String grdColId=id;
        
        query.append("UPDATE TAPGGRIDCOL SET system_col = 'N', hidden = 'false'       ");
        query.append("WHERE  pggridcol_id   = '"+grdColId+"'        ");
        
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    public String findTotalCount(MaGrdMngCommonDTO maGrdMngCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                        ");
        query.append("      COUNT(1)                ");
        query.append("FROM   TAPGGRIDCOL x INNER JOIN TAPGGRID y                     ");
        query.append("ON x.pggrid_id = y.pggrid_id                                                  ");
        query.append("INNER JOIN TAPAGE z                                                       ");
        query.append("ON y.page_id=z.page_id                                                    ");
        query.append("WHERE  1=1                    ");
        query.append(this.getWhere(maGrdMngCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}