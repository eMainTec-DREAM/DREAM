package dream.mgr.ptwh.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.ptwh.dao.MgrPtWhListDAO;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;

/**
 * 부품창고 - List DAO implements
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="mgrPtWhListDAOTarget"
 * @spring.txbn id="mgrPtWhListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrPtWhListDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrPtWhListDAO
{
	public List findPtWhList(MgrPtWhCommonDTO mgrPtWhCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT																									");
        query.append("         '' 																				AS seqNo		");
        query.append("        ,(SELECT description FROM TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant) 	As plantDesc	");
        query.append("        ,x.WCODE 																			As wcode		");
        query.append("        ,x.WNAME 																			As wname		");
        query.append("        ,SFACODE_TO_DESC(wh_type,'WH_TYPE','SYS','','"+user.getLangId()+"') 				AS whTypeDesc	");
        query.append("        ,x.IS_USE 																		As isUse		");
        query.append("        ,x.REMARK 																		As remark		");
        query.append("        ,x.WCODE_ID 																		As wcodeId		");
        query.append("FROM TAWAREHOUSE x																						");
        query.append("WHERE 1=1 																								");
        query.append(this.getWhere(mgrPtWhCommonDTO, user));
        query.getOrderByQuery("x.wcode_id", mgrPtWhCommonDTO.getOrderBy(), mgrPtWhCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrPtWhCommonDTO.getIsLoadMaxCount(), mgrPtWhCommonDTO.getFirstRow()));
    } 

	private String getWhere(MgrPtWhCommonDTO mgrPtWhCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();

        // 회사
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        // 창고 ID
        if(!"".equals(mgrPtWhCommonDTO.getWcodeId())){
            query.getAndQuery("x.wcode_id", mgrPtWhCommonDTO.getWcodeId());
            return query.toString();
        }
                
        // 창고명
        query.getCodeLikeQuery("x.wcode_id", "x.wname", mgrPtWhCommonDTO.getFilterWcodeId(), mgrPtWhCommonDTO.getFilterWName());        
        // 공장명
        query.getAndQuery("x.plant", mgrPtWhCommonDTO.getFilterPlantId());
        
        return query.toString();
    }

    public String findTotalCount(MgrPtWhCommonDTO mgrPtWhCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                 		");
        query.append("       COUNT(1)                              	");
        query.append("FROM TAWAREHOUSE x							");
        query.append("WHERE 1=1 									");
    	query.append(this.getWhere(mgrPtWhCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}