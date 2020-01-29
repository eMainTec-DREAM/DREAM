package dream.mgr.usrgrp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrgrp.dao.LovUsrGrpListDAO;
import dream.mgr.usrgrp.dto.LovUsrGrpListDTO;

/**
 * ±ÇÇÑ ÆË¾÷
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovUsrGrpListDAOTarget"
 * @spring.txbn id="lovUsrGrpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovUsrGrpListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovUsrGrpListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovUsrGrpListDTO
     * @param loginUser
     * @return
     */
    public List findUsrGrpList(LovUsrGrpListDTO lovUsrGrpListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       x.usrgrp_id usrGrpId,						");
        query.append("       x.group_name groupName						");
        query.append("FROM   TAUSRGRP x									");
        query.append("WHERE 1=1											");
        query.getLikeQuery("x.groupName", lovUsrGrpListDTO.getUsrGrpDesc());
        query.append("ORDER BY x.usrgrp_id								");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}