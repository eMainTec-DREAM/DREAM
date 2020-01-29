package dream.work.let.categ.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.let.categ.dao.LovWorkLetCategListDAO;
import dream.work.let.categ.dto.LovWorkLetCategListDTO;

/**
 * 안전작업유형 팝업
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovWorkLetCategListDAOTarget"
 * @spring.txbn id="lovWorkLetCategListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWorkLetCategListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovWorkLetCategListDAO
{

	@Override
	public List findWorkLetCategAcList(LovWorkLetCategListDTO lovWorkLetCategListDTO, User user, Map<String, String> conditionMap)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SELECT 																									");
        query.append(" 		''               										AS seqNo			");
        query.append(" 		,comp_no    											AS compNo		  	");
        query.append(" 		,a.woletctg_type										AS woLetCtgType		");
        query.append(" 		,dbo.SFACODE_TO_DESC(a.woletctg_type,'WOLETCTG_TYPE','SYS','','ko')		AS woLetCtgTypeDesc	");
        query.append(" 		,woletctg_no    										AS woletctgNo  		");
        query.append(" 		,description  											AS description		");
        query.append(" 		,dbo.SFACODE_TO_DESC(a.is_use, 'IS_USE', 'SYS', '', 'ko')	AS isUse		");
        query.append(" 		,remark                                                 AS remark			");
        query.append(" 		,woletctg_id                                            AS woLetCtgId 		");
        query.append("FROM TAWOLETCTG a																	");
        query.append("WHERE  1=1																		");
        query.getAndQuery("a.comp_no", conditionMap);
    	query.append(this.getWhere(lovWorkLetCategListDTO, user));	
        query.getOrderByQuery("a.woletctg_id", "a.woletctg_id", lovWorkLetCategListDTO.getOrderBy(), lovWorkLetCategListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(lovWorkLetCategListDTO.getIsLoadMaxCount(), lovWorkLetCategListDTO.getFirstRow()));
	}
	
	private String getWhere(LovWorkLetCategListDTO lovWorkLetCategListDTO, User user)
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		//안전작업유형
		query.getSysCdQuery("a.woletctg_type", lovWorkLetCategListDTO.getWoLetCtgType(), lovWorkLetCategListDTO.getWoLetCtgTypeDesc(), "WOLETCTG_TYPE", user.getCompNo(), user.getLangId());
		//안전작업 No
		query.getLikeQuery("a.woletctg_no", lovWorkLetCategListDTO.getWoLetCtgNo());
        //제목
        query.getLikeQuery("a.description", lovWorkLetCategListDTO.getDescription());
        //사용여부
        query.getSysCdQuery("a.is_use", lovWorkLetCategListDTO.getIsUse(), lovWorkLetCategListDTO.getIsUse(), "IS_USE", user.getCompNo(),user.getLangId());
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(LovWorkLetCategListDTO lovWorkLetCategListDTO, User user, Map<String, String> conditionMap) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                   ");
        query.append("       COUNT(1)                          ");
        query.append("FROM TAWOLETCTG a						   ");
        query.append("WHERE  1=1						  	   ");
        query.getAndQuery("a.comp_no", conditionMap);
    	query.append(this.getWhere(lovWorkLetCategListDTO, user));	
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
}