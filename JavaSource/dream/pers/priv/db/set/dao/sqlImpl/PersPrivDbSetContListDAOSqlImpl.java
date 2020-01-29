package dream.pers.priv.db.set.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.pers.priv.db.set.dao.PersPrivDbSetContListDAO;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;

/**
 * PersPrivDbSetCont Page - List DAO implements
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContListDAOSqlImpl.java,v 1.0 2017/08/22 15:19:40 nhkim8548 Exp $
 * @since 1.0
 * 
 * @spring.bean id="persPrivDbSetContListDAOTarget"
 * @spring.txbn id="persPrivDbSetContListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersPrivDbSetContListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements PersPrivDbSetContListDAO
{
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id: persPrivDbSetContListDAO.java,v 1.0 2018/08/05 17:17:12 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param persPrivDbSetContListDTO
     * @return List
     */
    public List findList(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append(" SELECT                                                                                                                                             ");
        query.append("         ''                                                                                                                      AS ISDELCHECK      ");
        query.append("        ,''                                                                                                                      AS SEQNO           ");
        query.append("        , x.usrdbmenucnts_id                                                                                                     AS usrDbMenuCntId  ");
        query.append("        , y.dbcontents_id                                                                                                        AS usrDbCntNo      ");
        query.append("        , y.description                                                                                                          AS usrDbCntDesc    ");
        query.append("        , dbo.SFACODE_TO_DESC(y.dbcontents_type, 'DBCONTENTS_TYPE', 'SYS', x.comp_no, '"+ user.getLangId() +"')                  AS usrDbCntType    "); 
        query.append("        , (SELECT a.key_name                                                                                                                        ");
        query.append("             FROM TALANG a                                                                                                                          ");
        query.append("            WHERE a.key_type = y.key_type                                                                                                           ");
        query.append("              AND a.key_no   = y.key_no AND a.lang = '"+ user.getLangId() +"')                                                   AS usrDbCntName    ");
        query.append("        , y.file_name                                                                                                            AS usrDbCntJspFile ");
        query.append("        , dbo.SFACODE_TO_DESC(y.dbcontents_width, 'DBCONTENTS_WIDTH', 'SYS', '"+ user.getCompNo() +"', '"+ user.getLangId() +"') AS usrDbCntWidth   ");
        query.append("        , y.image_file                                                                                                           AS usrDbCntImgFile ");
        query.append("        , x.ord_no                                                                                                               AS usrDbCntOrder   ");
        query.append("   FROM TAUSRDBMENUCNTS x                                                                                                                           ");
        query.append("  INNER JOIN TADBCONTENTS y                                                                                                                         ");
        query.append("     ON x.DBCONTENTS_ID = y.DBCONTENTS_ID                                                                                                           ");
        query.append("  WHERE 1=1                                                                                                                                         ");
        query.append(this.getWhere(persPrivDbSetCommonDTO, persPrivDbSetContListDTO, user));
        query.getOrderByQuery("x.usrdbmenucnts_id","x.usrdbmenucnts_id", persPrivDbSetContListDTO.getOrderBy(), persPrivDbSetContListDTO.getDirection());                
        
        return getJdbcTemplate().queryForList(query.toString(persPrivDbSetContListDTO.getIsLoadMaxCount(), persPrivDbSetContListDTO.getFirstRow()));
    }
    
    private String getWhere(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndNumKeyQuery("x.usrdbmenu_id", persPrivDbSetCommonDTO.getUsrDbId());
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        if(!"".equals(persPrivDbSetContListDTO.getUsrDbMenuCntId())){
            query.getAndQuery("x.usrdbmenucnts_id", persPrivDbSetContListDTO.getUsrDbMenuCntId());
            return query.toString();
        }
        
        return query.toString();
    }
    public int deleteList(String id, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAUSRDBMENUCNTS ");
        query.append(" WHERE usrdbmenucnts_id = ? ");
        query.append("   AND comp_no = ?          ");
        
        Object[] objects = new Object[] {   
                 id
                ,user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    public String findTotalCount(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO,
            PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user) throws Exception 
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT               ");
        query.append("       COUNT(1)      ");
        query.append("  FROM TAUSRDBMENUCNTS x ");
        query.append("  INNER JOIN TADBCONTENTS y                                                                                                                     ");
        query.append("     ON x.DBCONTENTS_ID = y.DBCONTENTS_ID                                                                                                       ");
        query.append("  WHERE 1=1                                                                                                                                     ");
        query.append(this.getWhere(persPrivDbSetCommonDTO, persPrivDbSetContListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
