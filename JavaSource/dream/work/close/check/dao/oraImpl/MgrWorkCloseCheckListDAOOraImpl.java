package dream.work.close.check.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.close.check.dao.MgrWorkCloseCheckListDAO;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;

/**
 * MgrWorkCloseCheck Page - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrWorkCloseCheckListDAOTarget"
 * @spring.txbn id="mgrWorkCloseCheckListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrWorkCloseCheckListDAOOraImpl  extends BaseJdbcDaoSupportOra implements MgrWorkCloseCheckListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckCommonDTO
     * @return List
     */
    public List findList(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 													");
        query.append("      '' 						        ISDELCHECK			");
        query.append("    , '' 						        SEQNO				");
		query.append("    , x.stwrk_id 				        stwrkId				");
		query.append("    , x.stwrk_no 				        stwrkNo				");
		query.append("    , x.description 			        stwrkDesc			");
//		query.append("    , (SELECT SFACODE_TO_DESC(x.stwrk_status, 'STWRK_STATUS', 'SYS', x.comp_no, '"+user.getLangId()+"') FROM DUAL ) stwrkStatusDesc		");
		query.append("    , (SELECT SFACODE_TO_DESC(y.wo_type , 'WO_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') FROM DUAL ) woTypeDesc 		");
		query.append("    , (SELECT SFACODE_TO_DESC(y.pm_type , y.wo_type||'_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') FROM DUAL ) pmTypeDesc 		");
		query.append("    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant) plantDesc		");
		query.append("    , (SELECT SFACODE_TO_DESC(x.is_active , 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"') FROM DUAL )  isActive		");
		query.append("    , x.remark 						remark				");
		query.append("FROM TASTWRKLST x INNER JOIN TASTWRKWORK y				");
		query.append("ON x.comp_no = y.Comp_no AND x.stwrk_id = y.stwrk_id		");
		query.append("WHERE 1=1													");
        query.append(this.getWhere(mgrWorkCloseCheckCommonDTO, user));
        query.getOrderByQuery("x.stwrk_no", mgrWorkCloseCheckCommonDTO.getOrderBy(), mgrWorkCloseCheckCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrWorkCloseCheckCommonDTO.getIsLoadMaxCount(), mgrWorkCloseCheckCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.is_deleted", "N");
        query.getAndQuery("x.stwrk_type", "W");

        if(!"".equals(mgrWorkCloseCheckCommonDTO.getStwrkId())){
            query.getAndQuery("x.stwrk_id", mgrWorkCloseCheckCommonDTO.getStwrkId());
            return query.toString();
        }
        
        //점검#
        query.getAndQuery("x.stwrk_no", mgrWorkCloseCheckCommonDTO.getFilterStwrkNo());
        
        //제목
        query.getLikeQuery("x.description", mgrWorkCloseCheckCommonDTO.getFilterStwrkDesc());
        
        //작업종류
    	query.getSysCdQuery("y.wo_type", mgrWorkCloseCheckCommonDTO.getFilterWoTypeId(), mgrWorkCloseCheckCommonDTO.getFilterWoTypeDesc(), "WO_TYPE", user.getCompNo(), user.getLangId());

    	//공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		mgrWorkCloseCheckCommonDTO.getFilterPlantId(), mgrWorkCloseCheckCommonDTO.getFilterPlantDesc());

        return query.toString();
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: mgrWorkCloseCheckListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckCommonDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

		query.append("UPDATE TASTWRKLST set  	");
		query.append("	  is_deleted = ? 		");
        query.append("WHERE stwrk_id = ?    	");
        query.append("  AND  comp_no = ?    	");
        
		
        Object[] objects = new Object[] {   
        		"Y"
                ,id
                ,user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public String findTotalCount(
            MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                   					");
        query.append("       COUNT(1)                          					");
		query.append("FROM TASTWRKLST x INNER JOIN TASTWRKWORK y				");
		query.append("ON x.comp_no = y.Comp_no AND x.stwrk_id = y.stwrk_id		");
		query.append("WHERE 1=1													");
        query.append(this.getWhere(mgrWorkCloseCheckCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}