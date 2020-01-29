package dream.consult.program.table.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.table.dao.MaTableColListDAO;
import dream.consult.program.table.dto.MaTableColListDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;

/**
 * ������ ���̺� detail-code ��� dao
 * @author  kim21017
 * @version $Id: MaTableColListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maTableColListDAOTarget"
 * @spring.txbn id="maTableColListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaTableColListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaTableColListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaTableColListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableCommonDTO
     * @param maTableColListDTO
     * @return List
     */
    public List findCodeList(MaTableCommonDTO maTableCommonDTO, MaTableColListDTO maTableColListDTO, String lang)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT														");
        query.append("       '' seqNo,												");
        query.append("       '' isDelCheck,											");
        query.append("       x.table_id			tableMId,							");
        query.append("       x.tabcol_id			tabColId,						");
        query.append("       x.table_name			tableName,						");
        query.append("       x.column_name			columnName,						");
        query.append("       x.ref_table_name refTable,         					");
        query.append("       x.ref_column_name refColumn,       					");
        query.append("       ( select                                             	");
        query.append("        ISNULL((select bb.key_name                            ");
        query.append("             from talang bb                                 	");
        query.append("             where  bb.lang = '"+lang+"'                    	");
        query.append("             and aa.key_type = bb.key_type                  	");
        query.append("             and aa.key_no = bb.key_no), aa.description)    	");
        query.append("        description                                         	");
        query.append("       FROM TACDSYSD aa                            			");
        query.append("         where aa.list_type = 'DATA_TYPE'          			");
        query.append("                   and aa.cdsysd_no = x.data_type  			");
        query.append("       ) as type, 											");
        query.append("       x.data_length			length,							");
        query.append("       x.description			remark,							");
        query.append("       x.ref_table_name			refTableName,				");
        query.append("       x.ref_column_name			refColumnName,				");
        query.append("       CONVERT(VARCHAR,CONVERT(DATE,x.credate),23) 	creDate,");
        query.append("        (SELECT euser_name                                    ");
        query.append("           FROM TAEHUSER                                      ");
        query.append("            WHERE ehuser_id = x.creby)				creBy   ");
        query.append("FROM   TATABCOL x												");
        query.append("WHERE  1 = 1                                      			");
        query.append(this.getWhere(maTableCommonDTO,maTableColListDTO));
        query.getOrderByQuery("x.table_id","CONVERT(INT, x.ord_no)", maTableCommonDTO.getOrderBy(), maTableCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maTableCommonDTO.getIsLoadMaxCount(), maTableCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaTableColListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteCodeList(String deleteRow, String deleteRowsExt)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String tableMId=deleteRow;
    	String tabColId=deleteRowsExt;
    	
    	query.append("DELETE FROM TATABCOL					");
    	query.append("WHERE table_id 	= '"+tableMId+"'	");
    	query.append("  AND tabcol_id 	= '"+tabColId+"'	");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaTableCommonDTO maTableCommonDTO, MaTableColListDTO maTableColListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndNumKeyQuery("x.table_id", maTableCommonDTO.getTableMId());
    	if (!"".equals(maTableColListDTO.getTableDId()))
        {
            query.getAndNumKeyQuery("x.tabcol_id", maTableColListDTO.getTableDId());
            return query.toString();
        }                                                                                                                     
    	
    	return query.toString();
    }

	public String findTotalCount(MaTableCommonDTO maTableCommonDTO, MaTableColListDTO maTableColListDTO, User user) {
    	QuerySqlBuffer query = new QuerySqlBuffer();

		query.append(" SELECT  			");
		query.append(" COUNT(*)  		");
        query.append(" FROM TATABCOL x	");
        query.append(" WHERE 1=1		");
        query.append(this.getWhere(maTableCommonDTO, maTableColListDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}