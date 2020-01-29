package dream.pers.priv.db.set.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.pers.priv.db.set.dao.LovDbConAcListDAO;
import dream.pers.priv.db.set.dto.LovDbConAcListDTO;

/**
 * Contents Lov 
 * @author  nhkim8548
 * @version $Id: LovDbConAcListDAOSqlImpl.java,v 1.0 2018/08/06 09:36:40 nhkim8548 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovDbConAcListDAOTarget"
 * @spring.txbn id="lovDbConAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovDbConAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovDbConAcListDAO
{
    
    public List findList(LovDbConAcListDTO lovDbConAcListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                                                          ");
        query.append("         ''                                                                                                                  AS seqNo           ");
        query.append("       , x.dbcontents_id                                                                                                     AS usrDbCntId      ");
        query.append("       , x.dbcontents_id                                                                                                     AS usrDbCntNo      ");
        query.append("       , x.description                                                                                                       AS usrDbCntDesc    ");
        query.append("       , dbo.SFACODE_TO_DESC(x.dbcontents_type, 'DBCONTENTS_TYPE', 'SYS', '"+ user.getCompNo() +"', '"+ user.getLangId() +"')    AS usrDbCntType    ");
        query.append("       , (SELECT key_name                                                                                                                       ");
        query.append("            FROM TALANG                                                                                                                         ");
        query.append("           WHERE key_no = x.key_no                                                                                                              ");
        query.append("             AND key_type = x.key_type AND lang = '"+ user.getLangId()+ "')                                                  AS usrDbCntName    ");
        query.append("       , X.FILE_NAME                                                                                                         AS usrDbCntJspFile ");
        query.append("       , dbo.SFACODE_TO_DESC(x. dbcontents_width, 'DBCONTENTS_WIDTH', 'SYS', '"+ user.getCompNo() +"', '"+ user.getLangId() +"') AS usrDbCntWidth   ");
        query.append("       , x.image_file                                                                                                        AS usrDbCntImgFile ");
        query.append("       , X.IS_USE                                                                                                            AS usrDbCntIsUse   ");
        query.append("       , X.REMARK                                                                                                            AS usrDbCntRemark  ");
        query.append("  FROM TADBCONTENTS x                                                                                                                           ");
        query.append(" WHERE 1=1                                                                                                                                      ");
        query.append(this.getWhere(lovDbConAcListDTO, user));
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    private String getWhere(LovDbConAcListDTO lovDbConAcListDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        // contents 제목
        query.getLikeQuery("x.description", lovDbConAcListDTO.getUsrCntDesc());
        // contents 구분
        query.getSysCdQuery("x.dbcontents_type", lovDbConAcListDTO.getUsrCntTypeId(), lovDbConAcListDTO.getUsrCntTypeDesc(), "DBCONTENTS_TYPE", user.getCompNo(), user.getLangId());
        
        return query.toString();
    }
    
    
}