package dream.pers.priv.db.set.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.pers.priv.db.set.dao.PersPrivDbSetContDetailDAO;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContDetailDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;

/**
 * 대시보드(Contents) - Detail DAO implements
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContDetailDAOOraImpl.java,v 1.0 2018/08/03 12:37:40 nhkim8548 Exp $
 * @since 1.0
 * 
 * @spring.bean id="persPrivDbSetContDetailDAOTarget"
 * @spring.txbn id="persPrivDbSetContDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersPrivDbSetContDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements PersPrivDbSetContDetailDAO
{
	
    public PersPrivDbSetContDetailDTO findDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append(" SELECT                                                        ");
    	query.append("          y.dbcontents_id                    AS usrDbCntId     ");
    	query.append("        , x.usrdbmenucnts_id                 AS usrDbMenuCntId ");
    	query.append("        , y.description                      AS usrDbCntDesc   ");
    	query.append("        , x.ord_no                           AS usrDbMenuOder  ");
    	query.append("   FROM   TAUSRDBMENUCNTS x                                    ");
    	query.append("  INNER JOIN TADBCONTENTS y                                    ");
    	query.append("     ON   x.dbcontents_id = y.dbcontents_id                    ");
    	query.append("  WHERE   x.comp_no = ?                                        ");
    	query.append("    AND   x.usrdbmenu_id = ?                                   ");
    	query.append("    AND   x.usrdbmenucnts_id = ?                               ");

    	Object[] objects = new Object[] {
                user.getCompNo()
              , persPrivDbSetCommonDTO.getUsrDbId()
              , persPrivDbSetContListDTO.getUsrDbMenuCntId()
        };
    
        PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO = 
        		(PersPrivDbSetContDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new PersPrivDbSetContDetailDTO()));
        
        return persPrivDbSetContDetailDTO;
    }

    public int insertDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO,PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAUSRDBMENUCNTS ( ");
    	query.append("            comp_no           ");
    	query.append("          , usrdbmenucnts_id  ");
    	query.append("          , usrdbmenu_id      ");
    	query.append("          , dbcontents_id     ");
    	query.append("          , ord_no            ");
    	query.append("   ) VALUES (                 ");
    	query.append("               ?              ");
    	query.append("             , ?              ");
    	query.append("             , ?              ");
    	query.append("             , ?              ");
    	query.append("             , ?              ");
    	query.append("            )                 ");

    	Object[] objects = new Object[] {
    			 user.getCompNo()
    		   , persPrivDbSetContDetailDTO.getUsrDbMenuCntId()
    		   , persPrivDbSetCommonDTO.getUsrDbId()
    		   , persPrivDbSetContDetailDTO.getUsrDbCntId()
    		   , persPrivDbSetContDetailDTO.getUsrDbMenuOder()
    	};
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAUSRDBMENUCNTS SET     ");
    	query.append("       usrdbmenu_id        = ? ");
    	query.append("     , dbcontents_id       = ? ");
    	query.append("     , ord_no              = ? ");
    	query.append(" WHERE comp_no             = ? ");
    	query.append("   AND usrdbmenucnts_id    = ? ");

    	Object[] objects = new Object[] {
    	         persPrivDbSetCommonDTO.getUsrDbId()
    	       , persPrivDbSetContDetailDTO.getUsrDbCntId()
    	       , persPrivDbSetContDetailDTO.getUsrDbMenuOder()
    	       , user.getCompNo()
    	       , persPrivDbSetContDetailDTO.getUsrDbMenuCntId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}