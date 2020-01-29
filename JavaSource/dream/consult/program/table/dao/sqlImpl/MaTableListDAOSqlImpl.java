package dream.consult.program.table.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.table.dao.MaTableListDAO;
import dream.consult.program.table.dto.MaTableCommonDTO;

/**
 * ������ ���̺� - ��� dao
 * @author  kim21017
 * @version $Id: MaTableListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maTableListDAOTarget"
 * @spring.txbn id="maTableListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaTableListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaTableListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaTableListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableCommonDTO
     * @return List
     */
    public List findListTypeList(MaTableCommonDTO maTableCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
             
        query.append(" SELECT  														");
        query.append("       '' seqNo,                             					");
        query.append("       '' isDelCheck,                         				");
        query.append("       x.table_name tableName, 								");
        query.append("       x.description tableDesc, 								");
        query.append("       x.remark detailDesc, 									");
        query.append("       CONVERT(VARCHAR,CONVERT(DATE,x.credate),23) creDate,   ");
        //query.append("        (SELECT euser_name                                   	");
        //query.append("           FROM TAEHUSER                                      ");
        //query.append("            WHERE ehuser_id = x.creby)			 creBy, 	");
        query.append("       x.table_id tableId										");
        query.append(" FROM TATABLE x												");
        query.append(" WHERE 1=1													");
        query.append(this.getWhere(maTableCommonDTO));
        query.getOrderByQuery("x.table_id","x.table_name", maTableCommonDTO.getOrderBy(), maTableCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(maTableCommonDTO.getIsLoadMaxCount(), maTableCommonDTO.getFirstRow()));
    
    }
    
    /**
     * Filter ����
     * @author  kim21017
     * @version $Id: MaTableListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaTableCommonDTO maTableCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(maTableCommonDTO.getTableMId()))
        {
            query.getAndQuery("x.table_id", maTableCommonDTO.getTableMId());
            return query.toString();
        }
        
        query.getLikeQuery("x.table_name", maTableCommonDTO.getFilterTable());
        query.getLikeQuery("x.description", maTableCommonDTO.getFilterTableDesc());
        query.getLikeQuery("x.remark", maTableCommonDTO.getFilterDescription());
      //��������
        query.getAndDateQuery("x.credate", maTableCommonDTO.getCreSdate(), maTableCommonDTO.getCreEdate());
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaTableListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteListType(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	String cdsysMId = id;
    	
    	query.append("DELETE FROM TATABLE				");
    	query.append("WHERE table_id = '"+cdsysMId+"'	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TATABCOL				");
    	query.append("WHERE table_id = '"+cdsysMId+"'	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }

	@Override
	public String findTotalCount(MaTableCommonDTO maTableCommonDTO, User user) throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append(" SELECT  			");
		query.append(" COUNT(*)  		");
        query.append(" FROM TATABLE x	");
        query.append(" WHERE 1=1		");
        query.append(this.getWhere(maTableCommonDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}