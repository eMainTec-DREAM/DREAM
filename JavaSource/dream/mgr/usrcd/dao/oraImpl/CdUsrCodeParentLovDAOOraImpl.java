package dream.mgr.usrcd.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrcd.dao.CdUsrCodeParentLovDAO;
import dream.mgr.usrcd.dto.CdUsrCodeParentLovDTO;

/**
 * 사용자코드 부모 LOV - List DAO implements
 * @author kim21017
 * @version $Id: CdUsrCodeParentLovDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="cdUsrCodeParentLovDAOTarget"
 * @spring.txbn id="cdUsrCodeParentLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CdUsrCodeParentLovDAOOraImpl extends BaseJdbcDaoSupportOra implements CdUsrCodeParentLovDAO
{
	public List findList(CdUsrCodeParentLovDTO cdUsrCodeParentLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT															");
        query.append("		MAX(x.cdusrm_id)					AS cdusrm_id			");
        query.append("		,x.dir_type							AS dir_type				");
        query.append("		,MAX(x.description)					AS description			");
        query.append("FROM TACDUSRM x													");
    	query.append("WHERE  1=1														");
    	query.append(this.getWhere(cdUsrCodeParentLovDTO, user, columnMap, conditionMap));
    	query.append("GROUP BY x.dir_type												");
        query.getOrderByQuery("x.dir_type", cdUsrCodeParentLovDTO.getOrderBy(), cdUsrCodeParentLovDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(cdUsrCodeParentLovDTO.getIsLoadMaxCount(), cdUsrCodeParentLovDTO.getFirstRow()));
    } 

	private String getWhere(CdUsrCodeParentLovDTO cdUsrCodeParentLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("is_use", conditionMap);
        //list type
        query.getLikeQuery("x.dir_type", cdUsrCodeParentLovDTO.getDirType());
        query.getLikeQuery("x.description", cdUsrCodeParentLovDTO.getFilterCdUsrMDesc());

    	return query.toString();
    }

    public String findTotalCount(CdUsrCodeParentLovDTO cdUsrCodeParentLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM TACDUSRM x				");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(cdUsrCodeParentLovDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}