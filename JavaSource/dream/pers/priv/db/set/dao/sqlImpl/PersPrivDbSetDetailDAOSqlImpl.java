package dream.pers.priv.db.set.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.pers.priv.db.set.dao.PersPrivDbSetDetailDAO;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetDetailDTO;

/**
 * Guide Page - Detail DAO implements
 * @author nhkim8548
 * @version $Id: PersPrivDbSetDetailDAOSqlImpl.java,v 1.0 2018/07/31 13:33:40 nhkim8548 Exp $
 * @since 1.0
 * 
 * @spring.bean id="persPrivDbSetDetailDAOTarget"
 * @spring.txbn id="persPrivDbSetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersPrivDbSetDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements PersPrivDbSetDetailDAO
{
	
    public PersPrivDbSetDetailDTO findDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user) 
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        query.append("  SELECT                                                                                                             ");
        query.append("           x.usrdbmenu_id                                                                      AS usrDbId            ");
        query.append("         , x.description                                                                       AS usrDbDesc          ");
        query.append("         , ( SELECT a.key_name                                                                                       ");
        query.append("               FROM TALANG a                                                                                         ");
        query.append("              WHERE a.key_type = x.key_type                                                                          ");
        query.append("                AND a.key_no   = x.key_no   )                                                  AS usrDbMenuDesc      ");
        query.append("         , x.is_use                                                                            AS usrDbMenuIsUseId   ");
        query.append("         , dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no, '"+ user.getLangId() +"') AS usrDbMenuIsUseDesc ");
        query.append("         , x.remark                                                                            AS usrDbMenuRemark    ");
        query.append("    FROM TAUSRDBMENU x                                                                                               ");
        query.append("   WHERE 1=1                                                                                                         ");
        query.append("     AND x.comp_no         = ?                                                                                       ");
        query.append("     AND x.usrdbmenu_id    = ?                                                                                       ");
        query.append("     AND x.user_id         = ?                                                                                       ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
        		,persPrivDbSetCommonDTO.getUsrDbId()
        		,user.getUserId()
    	};
    
        PersPrivDbSetDetailDTO persPrivDbSetDetailDTO = 
        		(PersPrivDbSetDetailDTO)getJdbcTemplate().query(query.toString(), getObject(objects), new MwareExtractor(new PersPrivDbSetDetailDTO()));
        
        return persPrivDbSetDetailDTO;
        
    }

    @Override
    public int insertDetail(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;

        query.append(" INSERT INTO TAUSRDBMENU    ");
        query.append("            (               ");
        query.append("               comp_no       ");
        query.append("             , usrdbmenu_id  ");
        query.append("             , user_id       ");
        query.append("             , description   ");
        query.append("             , key_type      ");
        query.append("             , key_no        ");
        query.append("             , is_use        ");
        query.append("             , remark        ");
        query.append("             , is_public_use ");
        query.append("   ) VALUES (                ");
        query.append("               ?             ");
        query.append("             , ?             ");
        query.append("             , ?             ");
        query.append("             , ?             ");
        query.append("             , ?             ");
        query.append("             , ?             ");
        query.append("             , ?             ");
        query.append("             , ?             ");
        query.append("             , ?             ");
        query.append("            )                ");

        Object[] objects = new Object[] {
                user.getCompNo()
               ,persPrivDbSetDetailDTO.getUsrDbId()
               ,user.getUserId()
               ,persPrivDbSetDetailDTO.getUsrDbDesc()
               ,persPrivDbSetDetailDTO.getUsrDbMenuKeyType()
               ,persPrivDbSetDetailDTO.getUsrDbMenuKeyNo()
               ,persPrivDbSetDetailDTO.getUsrDbMenuIsUseId()
               ,persPrivDbSetDetailDTO.getUsrDbMenuRemark()
               ,"Y"
       };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
        
        return rtnValue;
    }

    @Override
    public int updateDetail(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAUSRDBMENU         ");
        query.append("   SET                     ");
        query.append("         description   = ? ");
        query.append("       , key_type      = ? ");
        query.append("       , key_no        = ? ");
        query.append("       , is_use        = ? ");
        query.append("       , remark        = ? ");
        query.append("       , is_public_use = ? ");
        query.append(" WHERE   comp_no       = ? ");
        query.append("   AND   usrdbmenu_id  = ? ");
        query.append("   AND   user_id       = ? ");
        
        Object[] objects = new Object[] {
                persPrivDbSetDetailDTO.getUsrDbDesc()
               ,persPrivDbSetDetailDTO.getUsrDbMenuKeyType()
               ,persPrivDbSetDetailDTO.getUsrDbMenuKeyNo()
               ,persPrivDbSetDetailDTO.getUsrDbMenuIsUseId()
               ,persPrivDbSetDetailDTO.getUsrDbMenuRemark()
               ,"Y"
               ,user.getCompNo()
               ,persPrivDbSetDetailDTO.getUsrDbId()
               ,user.getUserId()
       };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public String validDbMenu(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT   key_no            ");
        query.append("  FROM   TALANG            ");
        query.append(" WHERE   1=1               ");
        query.getAndQuery("key_name", persPrivDbSetDetailDTO.getUsrDbMenuDesc());
        query.getAndQuery("key_type", "MENU");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }

    @Override
    public int insertDbMenu(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append(" INSERT INTO TALANG                              ");
        query.append("                    (                            ");
        query.append("                       lang_id                   ");
        query.append("                     , lang                      ");
        query.append("                     , key_type                  ");
        query.append("                     , key_no                    ");
        query.append("                     , key_name                  ");
        query.append("                     , is_comm_js_use            ");
        query.append("                     , use_web                   ");
        query.append("           ) VALUES (                            ");
        query.append("                       NEXT VALUE FOR sqalang_id ");
        query.append("                     , 'ko'                      ");
        query.append("                     , ?                         ");
        query.append("                     , ?                         ");
        query.append("                     , ?                         ");
        query.append("                     , 'N'                       ");
        query.append("                     , 'Y'                       ");
        query.append("                    )                            ");

        Object[] objects = new Object[] {
                persPrivDbSetDetailDTO.getUsrDbMenuKeyType()
               ,persPrivDbSetDetailDTO.getUsrDbMenuKeyNo()
               ,persPrivDbSetDetailDTO.getUsrDbMenuDesc()
       };
       
       rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
       
       return rtnValue;
    }
}