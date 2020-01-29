package dream.consult.comp.warehouse.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.warehouse.dao.LovWhToolListDAO;
import dream.consult.comp.warehouse.dto.LovWhToolListDTO;

/**
 * 사용창고 팝업
 * @author  kim21017
 * @version $Id: LovWhToolListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovWhToolListDAOTarget"
 * @spring.txbn id="lovWhToolListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWhToolListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovWhToolListDAO
{
    /**
     * 사용창고 검색
     * @author  kim21017
     * @version $Id: LovWhToolListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovWhListDTO
     * @param loginUser
     * @return
     */
    public List findWhList(LovWhToolListDTO lovWhListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT															");
        query.append("       wcode_id AS wcodeId,										");
        query.append("       wcode AS wcode,											");
        query.append("       wname AS wname,											");
        query.append("       dbo.SFACODE_TO_DESC(wh_type,'WH_TYPE','SYS','','"+loginUser.getLangId()+"') AS whTypeDesc,	");
        query.append("       remark AS remark,											");
        query.append("       out_wcode outWcode,                                        ");
        query.append("       out_plant outPlant                                         ");
        query.append("FROM TAWAREHOUSE													");
        query.append("WHERE 1=1															");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("wh_categ", "TOOL");
        query.getLikeQuery("wcode", lovWhListDTO.getWcode());
        query.getLikeQuery("wname", lovWhListDTO.getWname());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}