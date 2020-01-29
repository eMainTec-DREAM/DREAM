package mobile.dream.org.wrkgrp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class OrgWkCtrLovListDAOOraImpl extends BaseJdbcDaoSupportOra implements OrgWkCtrLovListDAO
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("		wkctr_id		AS id,							");
        query.append("		wkctr_id		AS wkctrId,						");
        query.append("		p_wkctr_id		AS paWkCtrId,					");
        query.append("		wkctr_no		AS wkctrNo,						");
//        query.append("      MIN(LEVEL) OVER( ORDER BY ord_no) AS minLvl,	");
        query.append("      LEVEL,											");
        query.append("		description		AS wkctrDesc					");
        query.append("FROM TAWKCTR											");
        query.append("WHERE 1=1												");
        query.getAndQuery("is_use", "Y");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("wkctr_no", orgWkCtrLovListDTO.getWkCtrNo());
        query.getLikeQuery("full_desc", orgWkCtrLovListDTO.getWkCtrDesc());
        String[] cols = {"description","wkctr_no"};
        query.getLikeQuery(cols, orgWkCtrLovListDTO.getSearchText());
        
        query.append(" START WITH p_wkctr_id = 0							");
        query.append(" CONNECT BY PRIOR wkctr_id = p_wkctr_id				");
      query.append(" ORDER SIBLINGS by wkctr_no 		");
      
        return getJdbcTemplate().queryForList(query.toString());
    }
}