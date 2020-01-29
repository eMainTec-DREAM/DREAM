package dream.consult.program.table.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.program.table.dao.MaTableColDetailDAO;
import dream.consult.program.table.dto.MaTableColDetailDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;

/**
 * 데이터 테이블-분류 dao
 * @author  kim21017
 * @version $Id: MaTableColDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maTableColDetailDAOTarget"
 * @spring.txbn id="maTableColDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaTableColDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaTableColDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaTableColDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param tableMId
     * @param tabColId
     * @return
     */
    public MaTableColDetailDTO findDetail(String tableMId, String tabColId, String lang)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT ");
        query.append("       x.tabcol_id tabColId, 		");
        query.append("       x.column_name columnName,  ");
        query.append("       x.description description, ");
        query.append("       x.data_type dataTypeId, ");
        query.append("       ( select                                             ");
        query.append("        nvl((select bb.key_name                             ");
        query.append("             from talang bb                                 ");
        query.append("             where  bb.lang = '"+lang+"'                    ");
        query.append("             and aa.key_type = bb.key_type                  ");
        query.append("             and aa.key_no = bb.key_no), aa.description)    ");
        query.append("        description                                         ");
        query.append("       FROM TACDSYSD aa                               ");
        query.append("         where aa.list_type = 'DATA_TYPE'        		");
        query.append("                   and aa.cdsysd_no = x.data_type     ");
        query.append("       ) as dataTypeDesc, ");
        query.append("       data_length dataLength,    ");
        query.append("         (SELECT euser_name                                                         ");
        query.append("            FROM TAEHUSER                                                           ");
        query.append("             WHERE ehuser_id = x.creby)                                     creByDesc,       ");
        query.append("                x.credate creDate,    ");
        query.append("               x.remark remark,       ");
        query.append("               x.ref_table_name refTable,       ");
        query.append("               x.ref_column_name refColumn,      ");
        query.append("               x.ord_no seqNo         ");
        query.append("FROM TATABCOL x ");
        query.append("WHERE  x.tabcol_id = '"+tabColId+"'	");
        query.append("AND  x.table_id = '"+tableMId+"'		");
        
        MaTableColDetailDTO maTableColDetailDTO = 
        		(MaTableColDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaTableColDetailDTO()));
        
        return maTableColDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaTableColDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableColDetailDTO
     * @param maTableCommonDTO
     * @return
     */
    public int updateDetail(MaTableColDetailDTO maTableColDetailDTO, MaTableCommonDTO maTableCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TATABCOL SET	");
    	query.append("	column_name		= ?,");
    	query.append("	description			= ?,");
    	query.append("	data_type			= ?,");
    	query.append("	data_length			= ?,");
    	query.append("	ref_tabcol_id			= ?,");
    	query.append("	ref_column_name			= ?,");
    	query.append("	ref_table_name			= ?,");
    	query.append("	ord_no			= ?,");
    	query.append("	remark			= ?");
    	query.append("WHERE table_id 	= ?	");
    	query.append("  AND tabcol_id	= ? ");
    	
    	if(maTableColDetailDTO.getRefColumn().equals(""))
    	{
    		maTableColDetailDTO.setRefTabColId("");
    		maTableColDetailDTO.setRefTable("");
    	}
    	Object[] objects = new Object[] {
    			maTableColDetailDTO.getColumnName(),
    			maTableColDetailDTO.getDescription(),
    			maTableColDetailDTO.getDataTypeId(),
    			maTableColDetailDTO.getDataLength(),
    			maTableColDetailDTO.getRefTabColId(),
    			maTableColDetailDTO.getRefColumn(),
    			maTableColDetailDTO.getRefTable(),
    			maTableColDetailDTO.getSeqNo(),
    			maTableColDetailDTO.getRemark(),
    			maTableCommonDTO.getTableMId(),
    			maTableColDetailDTO.getTabColId()
    			
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaTableColDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableColDetailDTO
     * @param maTableCommonDTO
     * @return
     */
    public int insertDetail(MaTableColDetailDTO maTableColDetailDTO, MaTableCommonDTO maTableCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TATABCOL x(					");
    	query.append("	x.tabcol_id,		x.table_id,		    x.table_name,	");
    	query.append("	x.column_name,	    x.data_type,		x.data_length,	");
    	query.append("	x.ref_table_name,	x.ref_column_name,  x.credate,	");
    	query.append("	x.creby,			x.remark,           x.description,            	");
    	query.append("	x.ord_no          	");
    	query.append("	)	VALUES				(				");
    	query.append("	?,				?,				(SELECT table_name FROM TATABLE WHERE table_id=? ),		");
    	query.append("	?,				?,				?,		");
    	query.append("	?,				?,				?,		");
    	query.append("	?,				?,		        ?,      ");
    	query.append("	?      									");
    	query.append("	)										");
    		
    	Object[] objects = new Object[] {
    			maTableColDetailDTO.getTabColId(),
    			maTableCommonDTO.getTableMId(),
    			maTableCommonDTO.getTableMId(),
    			maTableColDetailDTO.getColumnName(),
    			maTableColDetailDTO.getDataTypeId(),
    			maTableColDetailDTO.getDataLength(),
    			"",
    			"",
    			maTableColDetailDTO.getCreDate(),
    			maTableColDetailDTO.getCreBy(),
    			maTableColDetailDTO.getRemark(),
    			maTableColDetailDTO.getDescription(),
    			maTableColDetailDTO.getSeqNo()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);    	
    	
    	return rtnValue;
    }
}