package dream.consult.comp.user.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.user.dao.LovUsrGrpAcListDAO;
import dream.consult.comp.user.dto.LovUsrGrpAcListDTO;

/**
 * LOV - List DAO implements
 * @author youngjoo38
 * @version $Id: LovUsrGrpAcListDAOSqlImpl.java,v 1.0 2017/09/12 16:15:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="lovUsrGrpAcListDAOTarget"
 * @spring.txbn id="lovUsrGrpAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovUsrGrpAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovUsrGrpAcListDAO
{
	public List findList(LovUsrGrpAcListDTO lovUsrGrpAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                            ");
        query.append("        x.usrgrp_id               ");
        query.append("      , x.usrgrp_no               ");
        query.append("      , x.group_name              ");
        query.append("FROM TAUSRGRP x                   ");
        query.append("WHERE  1 = 1                      ");
        
        query.append(this.getWhere(lovUsrGrpAcListDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.usrgrp_id","x.group_name", lovUsrGrpAcListDTO.getOrderBy(), lovUsrGrpAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovUsrGrpAcListDTO.getIsLoadMaxCount(), lovUsrGrpAcListDTO.getFirstRow()));
    } 

	private String getWhere(LovUsrGrpAcListDTO lovUsrGrpAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("is_admin_group", conditionMap);
        // ±×·ì¸í
        query.getLikeQuery("x.group_name", lovUsrGrpAcListDTO.getUsrGrpDesc());

    	return query.toString();
    }

    public String findTotalCount(LovUsrGrpAcListDTO lovUsrGrpAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("      COUNT(1)                ");
        query.append("FROM TAUSRGRP x               ");
        query.append("WHERE  1=1                    ");
        query.append(this.getWhere(lovUsrGrpAcListDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}