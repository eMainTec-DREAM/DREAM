package dream.part.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.part.list.dao.LovPartListBinListDAO;
import dream.part.list.dto.LovPartListBinListDTO;

/**
 * 점검항목 팝업
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovPartListBinListDAOTarget"
 * @spring.txbn id="lovPartListBinListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPartListBinListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovPartListBinListDAO
{
    /**
     * 검색
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPartListBinListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgPartAcList(LovPartListBinListDTO lovPartListBinListDTO, User user, Map<String, String> conditionMap) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        																										");
        query.append("       ''                                                                                           	AS seqNo        ");
        query.append("       ,x.BIN_NO																						AS bin_no 		");
        query.append("       ,x.LOC																							AS loc 			");
        query.append("       ,x.COL																							AS col 			");
        query.append("       ,x.RO																							AS ro	 		");
        query.append("       ,x.REMARK                                                                                     	AS remark 		");
        query.append("       ,x.PTBINLIST_ID                                                                                AS ptBinListId	");
        query.append("       ,x.WCODE_ID                                                                                    AS wCodeId		");
        query.append("FROM TAPTBINLIST x        																							");
        query.append("WHERE 1=1																												");
        query.append(this.getWhere(lovPartListBinListDTO, user, conditionMap));
        
		return getJdbcTemplate().queryForList(query.toString());
    }
    
	private String getWhere(LovPartListBinListDTO lovPartListBinListDTO, User user, Map<String, String> conditionMap) throws Exception
	{
		QueryBuffer query = new QueryBuffer();
		
		query.getAndQuery("x.comp_no", conditionMap);
		query.getAndQuery("x.is_use", conditionMap);
		query.getAndQuery("x.wcode_id", conditionMap);
		
		return query.toString();
	}
	
	@Override
	public String findTotalCount(LovPartListBinListDTO lovPartListBinListDTO, User user,
			Map<String, String> conditionMap) throws Exception {

		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            ");
        query.append("		count(1)					");
    	query.append("FROM   TAPTBINLIST x           	");
        query.append("WHERE  1 = 1                      ");
    	query.append(this.getWhere(lovPartListBinListDTO, user, conditionMap));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}