package dream.consult.program.table.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.program.table.dao.MaTableDetailDAO;
import dream.consult.program.table.dto.MaTableDetailDTO;

/**
 * 데이터 테이블 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaTableDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maTableDetailDAOTarget"
 * @spring.txbn id="maTableDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaTableDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaTableDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaTableDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param tableMId
     * @return
     */
    public MaTableDetailDTO findDetail(String tableMId, String lang)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT   ");
        query.append("    x.table_name tableNo, ");
        query.append("    x.description tableDesc, ");
        query.append("    x.creby creBy, ");
        //query.append("        (SELECT euser_name                                                        ");
        //query.append("           FROM TAEHUSER                                                          ");
        //query.append("            WHERE ehuser_id = x.creby)  creByDesc,     ");
        query.append("    x.credate creDate,   ");
        query.append("    x.remark detailDesc, ");
        query.append("    x.table_id tableMId  ");
        query.append("FROM TATABLE x");
        query.append("WHERE  x.table_id = '"+tableMId+"'		");
    
        MaTableDetailDTO maTableDetailDTO = 
        		(MaTableDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaTableDetailDTO()));
        
        return maTableDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaTableDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableDetailDTO
     * @return
     */
    public int insertDetail(MaTableDetailDTO maTableDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TATABLE								");
    	query.append("	(table_id,		table_name,		description,	");
    	query.append("	 remark,		creby,			credate		    ");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,				?				");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			maTableDetailDTO.getTableMId(),
    			maTableDetailDTO.getTableNo(),
    			maTableDetailDTO.getTableDesc(),
    			maTableDetailDTO.getDetailDesc(),
    			maTableDetailDTO.getCreBy(),
    			maTableDetailDTO.getCreDate()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaTableDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableDetailDTO
     * @return
     */
    public int updateDetail(MaTableDetailDTO maTableDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TATABLE SET		");
    	query.append("	table_name		= ?,	");
    	query.append("	description		= ?,	");
    	query.append("	remark			= ?  	");
    	query.append("WHERE table_id 	= ?		");
    	
    	Object[] objects = new Object[] {
    			maTableDetailDTO.getTableNo(),
    			maTableDetailDTO.getTableDesc(),
    			maTableDetailDTO.getDetailDesc(),
    			maTableDetailDTO.getTableMId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}