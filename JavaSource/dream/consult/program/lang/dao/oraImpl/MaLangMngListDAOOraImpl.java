package dream.consult.program.lang.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.lang.dao.MaLangMngListDAO;
import dream.consult.program.lang.dto.MaLangMngCommonDTO;

/**
 * 다국어 - 목록 dao
 * @author  kim21017
 * @version $Id: MaLangMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maLangMngListDAOTarget"
 * @spring.txbn id="maLangMngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaLangMngListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaLangMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaLangMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngCommonDTO
     * @return List
     */
    public List findKeyList(MaLangMngCommonDTO maLangMngCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select                                                                                      ");
        query.append("     '' seqNo										                                      ");
        query.append("    ,'' isDelCheck          									                              ");
        query.append("    ,x.lang_id                                                              as langId       ");
        query.append("    ,x.lang                                                                 as lang         ");
        query.append("    ,SFACODE_TO_DESC(x.lang,'LANG','SYS','','"+user.getLangId()+"')         as langDesc     ");
        query.append("    ,x.key_type                                                             as keyType      ");
        query.append("    ,SFACODE_TO_DESC(x.key_type,'KEY_TYPE','SYS','','"+user.getLangId()+"') as keyTypeDesc  ");
        query.append("    ,x.key_no                                                               as keyNo        ");
        query.append("    ,x.key_name                                                             as keyName      ");
        query.append("    ,x.is_comm_js_use                                                       as isCommJsUse  ");
        query.append("    ,x.remark                                                               as remark       ");
        query.append("    ,x.use_web                                                              as useWeb       ");
        query.append("    ,x.use_android                                                          as useAndroid   ");
        query.append("from TALANG x                                                                               ");
        query.append("where 1=1                                                                                   ");
        //query.append("    and rownum < 100                                                                        ");
        query.append(this.getWhere(maLangMngCommonDTO, user));
        query.getOrderByQuery("x.lang, x.key_type, x.key_no", maLangMngCommonDTO.getOrderBy(), maLangMngCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maLangMngCommonDTO.getIsLoadMaxCount(), maLangMngCommonDTO.getFirstRow()));
    }         

    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaLangMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaLangMngCommonDTO maLangMngCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        // CommonDTO의 Key ID가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maLangMngCommonDTO.getLangId()) )
        {
            query.append(" AND x.lang_id	= '"+maLangMngCommonDTO.getLangId()+"'				");
            return query.toString();
        }
        
      //키타입
        query.getSysCdQuery("x.key_type", maLangMngCommonDTO.getFilterKeyType(), maLangMngCommonDTO.getFilterKeyTypeDesc(), "KEY_TYPE", maLangMngCommonDTO.getCompNo(),user.getLangId());
        query.getLikeQuery("x.key_name", maLangMngCommonDTO.getFilterKeyName());
        query.getSysCdQuery("x.lang", maLangMngCommonDTO.getFilterLang(), maLangMngCommonDTO.getFilterLangDesc(), "LANG", maLangMngCommonDTO.getCompNo(), user.getLangId());
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaLangMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteKey(String id)
    {
    	QueryBuffer query = new QueryBuffer();

    	String langId  =id;
    	
    	query.append("delete from talang					                                                ");
    	query.append("where lang != (select lang  FROM TALANG WHERE lang_id = "+langId+")					");
    	query.append("      and key_type =   (select key_type  FROM TALANG WHERE lang_id = "+langId+")		");
    	query.append("      and key_no =   (select key_no  FROM TALANG WHERE lang_id = "+langId+")			");
    	this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	query.append("DELETE FROM TALANG					");
    	query.append("WHERE lang_id = '"+langId+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }

	@Override
	public String findTotalCount(MaLangMngCommonDTO maLangMngCommonDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                   ");
        query.append("       COUNT(1)                          ");
        query.append("from TALANG x                                                                               ");
        query.append("where 1=1                                                                                   ");
        query.append(this.getWhere(maLangMngCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    
	}
}