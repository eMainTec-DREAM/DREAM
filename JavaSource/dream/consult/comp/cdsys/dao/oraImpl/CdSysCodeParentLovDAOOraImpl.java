package dream.consult.comp.cdsys.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.cdsys.dao.CdSysCodeParentLovDAO;
import dream.consult.comp.cdsys.dto.CdSysCodeParentLovDTO;

/**
 * 시스템코드 부모 LOV - List DAO implements
 * @author kim21017
 * @version $Id: CdSysCodeParentLovDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="cdSysCodeParentLovDAOTarget"
 * @spring.txbn id="cdSysCodeParentLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CdSysCodeParentLovDAOOraImpl extends BaseJdbcDaoSupportOra implements CdSysCodeParentLovDAO
{
	public List findList(CdSysCodeParentLovDTO cdSysCodeParentLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT															");
        query.append("		x.cdsysm_id						AS cdsysm_id				");
        query.append("		,x.list_type					AS list_type				");
        query.append("		,(SELECT a.key_name											");
        query.append("			FROM TALANG a											");
        query.append("			WHERE 1=1												");
        query.getStringEqualQuery("a.lang", user.getLangId());
        query.append("			AND a.key_type	= x.key_type							");
        query.append("			AND a.key_no	= x.key_no	) AS description			");
        query.append("FROM TACDSYSM x													");
    	query.append("WHERE  1=1														");
    	query.append(this.getWhere(cdSysCodeParentLovDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.list_type", cdSysCodeParentLovDTO.getOrderBy(), cdSysCodeParentLovDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(cdSysCodeParentLovDTO.getIsLoadMaxCount(), cdSysCodeParentLovDTO.getFirstRow()));
    } 

	private String getWhere(CdSysCodeParentLovDTO cdSysCodeParentLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("is_use", conditionMap);
        //list type
        query.getLikeQuery("x.list_type", cdSysCodeParentLovDTO.getFilterListType());
        if(!"".equals(cdSysCodeParentLovDTO.getFilterCdSysMDesc())){
        	query.append("AND (SELECT key_name   						");
        	query.append("		FROM TALANG a							");
        	query.append("		WHERE 1=1 								");
        	query.getStringEqualQuery("a.lang", user.getLangId());
        	query.append("		AND a.key_type=x.key_type				");
        	query.append("		AND a.key_no = x.key_no ) LIKE '%"+cdSysCodeParentLovDTO.getFilterCdSysMDesc()+"%' ");
        }

    	return query.toString();
    }

    public String findTotalCount(CdSysCodeParentLovDTO cdSysCodeParentLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM TACDSYSM x				");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(cdSysCodeParentLovDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}