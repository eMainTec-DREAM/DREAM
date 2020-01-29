package mobile.dream.org.wrkgrp.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import mobile.dream.org.wrkgrp.dao.OrgWkCtrLovListDAO;
import mobile.dream.org.wrkgrp.dto.OrgWkCtrLovListDTO;


/**
 * 작업그룹 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="orgWkCtrLovListDAOTarget"
 * @spring.txbn id="orgWkCtrLovListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OrgWkCtrLovListDAOSqlImpl extends BaseJdbcDaoSupportSql implements OrgWkCtrLovListDAO
{
    /**
     * 작업그룹 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param orgWkCtrLovListDTO
     * @return
     */
    public List findWkCtrList(OrgWkCtrLovListDTO orgWkCtrLovListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("		x.wkctr_id		AS ID,							");
        query.append("		x.wkctr_id		AS WKCTRID,						");
        query.append("		x.p_wkctr_id		AS PAWKCTRID,				");
        query.append("		x.wkctr_no		AS WKCTRNO,						");
        query.append("      MIN(y.lvl) OVER( ORDER BY ISNULL(ord_no, '99999999')) AS MINLVL,	");
        query.append("      y.lvl 			AS LEVEL,						");
        query.append("		x.description		AS wkctrDesc				");
        query.append("FROM TAWKCTR x										");
        query.append("	  ,(SELECT * FROM dbo.SFAWKCTR_ALL('100','0')) y	");
        query.append("WHERE 1=1												");
        query.append("AND 	x.WKCTR_ID = y.WKCTR_ID							");
        query.getAndQuery("x.is_use", "Y");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.wkctr_no", orgWkCtrLovListDTO.getWkCtrNo());
        query.getLikeQuery("x.full_desc", orgWkCtrLovListDTO.getWkCtrDesc());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}