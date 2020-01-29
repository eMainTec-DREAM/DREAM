package dream.rcm.fmea.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.fmea.dao.RcmFmeaCrityDetailDAO;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityDetailDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;

/**
 * »ó¼¼ dao
 * @author  kim21017
 * @version $Id: RcmFmeaCrityDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFmeaCrityDetailDAOTarget"
 * @spring.txbn id="rcmFmeaCrityDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFmeaCrityDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmFmeaCrityDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFmeaCrityDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCrityListDTO
     * @param rcmFmeaCommonDTO
     * @return
     */
    public RcmFmeaCrityDetailDTO findDetail(RcmFmeaCrityListDTO rcmFmeaCrityListDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = rcmFmeaCommonDTO.getCompNo();
        
        query.append("SELECT 													");
        query.append("       x.col_name colName                            		");
        query.append("       ,x.row_name rowName                             	");
        query.append("       ,x.crityvalue                                		");
        query.append("       ,x.critycolor                                		");
        query.append("       ,x.is_critical isCritical                    		");
        query.append("       ,x.crity_lvl crityLvl                           	");
        query.append("       ,x.remark                                    		");
        query.append("       ,x.rcmcrity_id rcmcrityId                    		");
        query.append("       ,x.rcmfmea_id rcmfmeaId                        	");
        query.append("       ,x.rcmlist_id rcmlistId                         	");
        query.append("       ,x.comp_no compNo									");
        query.append("       ,x.crityvalue_id crityvalueId  					");
        query.append("       ,y.critylist_id critylistId                        ");
        query.append("FROM   TARCMCRITY   x  INNER JOIN TARCMLIST y ON x.rcmlist_id = y.rcmlist_id           	");
        query.append("WHERE  x.comp_no 		= ?									");
        query.append("  AND  x.rcmcrity_id 	= ?									");
        
        Object[] objects = new Object[] {
        	compNo,
        	rcmFmeaCrityListDTO.getRcmcrityId()
        };
    
        RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO1 = 
        		(RcmFmeaCrityDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new RcmFmeaCrityDetailDTO()));
        
        return rcmFmeaCrityDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFmeaCrityDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCrityDetailDTO
     * @param rcmFmeaCommonDTO
     * @return
     */
    public int updateDetail(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TARCMCRITY SET             ");
    	query.append("      col_name           	= ?		");
    	query.append("      ,row_name           = ?		");
    	query.append("      ,crityvalue         = ?		");
    	query.append("      ,critycolor         = ?		");
    	query.append("      ,is_critical        = ?		");
    	query.append("      ,remark          	= ?		");
    	query.append("      ,crity_lvl       	= ?    	");
    	query.append("      ,crityvalue_id     	= ?    	");
    	query.append("WHERE rcmcrity_id         = ?     ");
    	query.append("  AND comp_no             = ?     ");

    	
    	Object[] objects = new Object[] {
    			rcmFmeaCrityDetailDTO.getColName(),
    			rcmFmeaCrityDetailDTO.getRowName(),
    			rcmFmeaCrityDetailDTO.getCrityvalue(),
    			rcmFmeaCrityDetailDTO.getCritycolor(),
    			rcmFmeaCrityDetailDTO.getIsCritical(),
    			rcmFmeaCrityDetailDTO.getRemark(),
    			rcmFmeaCrityDetailDTO.getCrityLvl(),
    			rcmFmeaCrityDetailDTO.getCrityvalueId(),
    			rcmFmeaCrityDetailDTO.getRcmcrityId(),
    			rcmFmeaCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFmeaCrityDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCrityDetailDTO
     * @param rcmFmeaCommonDTO
     * @return
     */
    public int insertDetail(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMCRITY (                                		");
    	query.append("    comp_no,        rcmcrity_id,      rcmfmea_id,        		");
    	query.append("    rcmlist_id,     col_name,        	row_name,            	");
    	query.append("    crityvalue,     critycolor,      	is_critical,			");
    	query.append("    remark,         crity_lvl,                 				");
    	query.append("    crityvalue_id                        						");
    	query.append("    )    VALUES                (                            	");
    	query.append("    ?,              ?,                ?,                		");
    	query.append("    ?,              ?,                ?,     					");
    	query.append("    ?,              ?,                ?,            			");
    	query.append("    ?,              ?,                           				");
    	query.append("    ?                   										");
    	query.append("    )                                          				");

    	
    	Object[] objects = new Object[] {
    			rcmFmeaCommonDTO.getCompNo(),
    			rcmFmeaCrityDetailDTO.getRcmcrityId(),
    			rcmFmeaCommonDTO.getRcmfmeaId(),
    			rcmFmeaCommonDTO.getRcmlistId(),
    			rcmFmeaCrityDetailDTO.getColName(),
    			rcmFmeaCrityDetailDTO.getRowName(),
    			rcmFmeaCrityDetailDTO.getCrityvalue(),
    			rcmFmeaCrityDetailDTO.getCritycolor(),
    			rcmFmeaCrityDetailDTO.getIsCritical(),
    			rcmFmeaCrityDetailDTO.getRemark(),
    			rcmFmeaCrityDetailDTO.getCrityLvl(),
//    			rcmFmeaCrityDetailDTO.getRowOrdNo(),
//    			rcmFmeaCrityDetailDTO.getColOrdNo(),
    			rcmFmeaCrityDetailDTO.getCrityvalueId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }

	@Override
	public int findVal(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = rcmFmeaCommonDTO.getCompNo();
        
        query.append("SELECT count(1)									");
        query.append("FROM   TARCMCRITY x								");
        query.append("WHERE  x.crityvalue_id IN (						");
        query.append("                           SELECT crityvalue_id	");
        query.append(" 							 FROM  TACRITYVALUE		");
        query.append("							 WHERE critycol_id = ?)	");
        query.append("AND   x.rcmfmea_id = ?							");
        query.append("AND   x.rcmlist_id = ? 							");

        
        Object[] objects = new Object[] {
        		rcmFmeaCrityDetailDTO.getCritycolId()
        		,rcmFmeaCommonDTO.getRcmfmeaId()
    			,rcmFmeaCommonDTO.getRcmlistId()
        };
   
        return (int)getJdbcTemplate().queryForObject(query.toString(),objects, Integer.class);

	}

}