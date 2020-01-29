package dream.work.close.check.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class MgrWorkCloseCheckListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements MgrWorkCloseCheckListDAO
{
	public List findList(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 													");
        query.append("      '' 						        ISDELCHECK			");
        query.append("    , '' 						        SEQNO				");
		query.append("    , x.stwrk_id 				        STWRKID				");
		query.append("    , x.stwrk_no 				        STWRKNO				");
		query.append("    , x.description 			        STWRKDESC			");
//		query.append("    , (SELECT dbo.SFACODE_TO_DESC(x.stwrk_status, 'STWRK_STATUS', 'SYS', x.comp_no, '"+user.getLangId()+"') ) STWRKSTATUSDESC		");
		query.append("    , (SELECT dbo.SFACODE_TO_DESC(y.wo_type , 'WO_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') ) WOTYPEDESC 		");
		query.append("    , (SELECT dbo.SFACODE_TO_DESC(y.pm_type , y.wo_type+'_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') ) PMTYPEDESC 		");
		query.append("    , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant) PLANTDESC		");
		query.append("    , (SELECT dbo.SFACODE_TO_DESC(x.is_active , 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"') )  ISACTIVE		");
		query.append("    , x.remark 						REMARK				");
		query.append("FROM TASTWRKLST x INNER JOIN TASTWRKWORK y				");
		query.append("ON x.comp_no = y.Comp_no AND x.stwrk_id = y.stwrk_id		");
		query.append("WHERE 1=1													");
		query.append(this.getWhere(mgrWorkCloseCheckCommonDTO, user));
		query.getOrderByQuery("x.stwrk_id", "x.stwrk_no", mgrWorkCloseCheckCommonDTO.getOrderBy(), mgrWorkCloseCheckCommonDTO.getDirection());
		
        return getJdbcTemplate().queryForList(query.toString(mgrWorkCloseCheckCommonDTO.getIsLoadMaxCount(), mgrWorkCloseCheckCommonDTO.getFirstRow()));
	}
	
	private String getWhere(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
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
	
	public int deleteList(String id, User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
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

	public String findTotalCount(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT                                   					");
        query.append("       COUNT(1)                          					");
		query.append("FROM TASTWRKLST x INNER JOIN TASTWRKWORK y				");
		query.append("ON x.comp_no = y.Comp_no AND x.stwrk_id = y.stwrk_id		");
		query.append("WHERE 1=1													");
        query.append(this.getWhere(mgrWorkCloseCheckCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}