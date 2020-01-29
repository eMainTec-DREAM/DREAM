package dream.pers.priv.db.set.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.pers.priv.db.set.dao.PersPrivDbSetListDAO;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;

/**
 * Guide Page - List DAO implements
 * @author nhkim8548
 * @version $Id: PersPrivDbSetListDAOSqlImpl.java,v 1.0 2018/07/31 07:43:40 nhkim8548 Exp $
 * @since 1.0
 * 
 * @spring.bean id="persPrivDbSetListDAOTarget"
 * @spring.txbn id="persPrivDbSetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersPrivDbSetListDAOSqlImpl extends BaseJdbcDaoSupportSql implements PersPrivDbSetListDAO
{
    public List findList(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("  SELECT                                                                        ");
        query.append("          ''                                            AS isDelCheck           ");
        query.append("        , ''                                            AS seqNo                ");
        query.append("        , x.usrdbmenu_id                                AS usrDbId              ");
        query.append("        , x.description                                 AS usrDbDesc            ");
        query.append("        , ( SELECT a.key_name                                                   ");
        query.append("              FROM TALANG a                                                     ");
        query.append("             WHERE a.key_type = x.key_type                                      ");
        query.append("               AND a.key_no   = x.key_no                                        ");
        query.append("               AND a.lang = '"+ user.getLangId() +"')   AS usrDbMenuNameDesc    ");
        query.append("        , x.is_use                                      AS isUseDesc            ");
        query.append("        , x.remark                                      AS usrDbMenuRemark      ");
        query.append("    FROM TAUSRDBMENU x                                                          ");
        query.append("   WHERE 1=1                                                                    ");
        query.append(this.getWhere(persPrivDbSetCommonDTO, user));
        query.getOrderByQuery("x.usrdbmenu_id","x.usrdbmenu_id", persPrivDbSetCommonDTO.getOrderBy(), persPrivDbSetCommonDTO.getDirection());        

        return getJdbcTemplate().queryForList(query.toString(persPrivDbSetCommonDTO.getIsLoadMaxCount(), persPrivDbSetCommonDTO.getFirstRow()));
    } 

    private String getWhere(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if(!"".equals(persPrivDbSetCommonDTO.getUsrDbId())){
            query.getAndQuery("x.usrdbmenu_id", persPrivDbSetCommonDTO.getUsrDbId());
            return query.toString();
        }

        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getLikeQuery("x.description", persPrivDbSetCommonDTO.getUsrDbDesc());
        query.getStringEqualQuery("x.user_id", user.getUserId());
        
        if(!"".equals(persPrivDbSetCommonDTO.getUsrCntTypeId()) || !"".equals(persPrivDbSetCommonDTO.getUsrCntTypeDesc())) {
            query.append("     AND x.usrdbmenu_id IN ( SELECT a.usrdbmenu_id                              ");
            query.append("                               FROM TAUSRDBMENUCNTS a INNER JOIN TADBCONTENTS b ");
            query.append("                                 ON a.dbcontents_id = b.dbcontents_id           ");
            query.append("                              WHERE 1=1                                         ");
            query.append("                                AND a.comp_no = x.comp_no                       ");
            query.getSysCdQuery("b.dbcontents_type", persPrivDbSetCommonDTO.getUsrCntTypeId(), persPrivDbSetCommonDTO.getUsrCntTypeDesc(), "DBCONTENTS_TYPE", user.getCompNo(), user.getLangId());
            query.append("                                                            )                   ");
        }
        
        return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE                     ");
        query.append("  FROM TAUSRDBMENU         ");
        query.append(" WHERE comp_no      = ?   ");
        query.append("   AND usrdbmenu_id = ?   ");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public String findTotalCount(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                           ");
        query.append("       COUNT(1)                                                  ");
        query.append("  FROM TAUSRDBMENU x                                             ");
        query.append(" WHERE 1=1                                                       ");                                      
        query.append(this.getWhere(persPrivDbSetCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}