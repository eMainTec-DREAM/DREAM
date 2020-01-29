package dream.rcm.fmea.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.fmea.dao.RcmFmeaCrityListDAO;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;

/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: RcmFmeaCrityListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFmeaCrityListDAOTarget"
 * @spring.txbn id="rcmFmeaCrityListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFmeaCrityListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmFmeaCrityListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFmeaCrityListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCommonDTO
     * @param rcmFmeaCrityListDTO
     * @return List
     */
    public List findList(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = rcmFmeaCommonDTO.getCompNo();
        
        query.append("SELECT 											");
        query.append("       '' seqNo									");
        query.append("       ,'' isDelCheck								");
        query.append("       ,x.col_name colName            			");
        query.append("       ,x.row_name rowName 						");
        query.append("       ,x.crityvalue                            	");
        query.append("       ,x.critycolor								");
        query.append("       ,x.is_critical isCritical    				");
        query.append("       ,x.crity_lvl crityLvl                      ");
        query.append("       ,x.remark                                	");
        query.append("       ,x.rcmcrity_id rcmcrityId                	");
        query.append("       ,x.rcmfmea_id rcmfmeaId                    ");
        query.append("       ,x.rcmlist_id rcmlistId 					");
        query.append("       ,x.comp_no compNo           				");
        query.append("       ,'' colorDisplay            				");
        query.append("       ,y.critylist_id critylistId                ");
        query.append("       ,x.crityvalue_id crityvalueId              ");
        query.append("FROM   TARCMCRITY x  INNER JOIN TARCMLIST y ON x.rcmlist_id = y.rcmlist_id           	");
        query.append("       INNER JOIN TACRITYVALUE a ON a.crityvalue_id = x.crityvalue_id       		");
        query.append("WHERE  x.comp_no = '"+compNo+"'						");
        query.append(this.getWhere(rcmFmeaCommonDTO,rcmFmeaCrityListDTO));
        query.getOrderByQuery("x.rcmcrity_id","x.rcmcrity_id", rcmFmeaCommonDTO.getOrderBy(), rcmFmeaCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(rcmFmeaCommonDTO.getIsLoadMaxCount(), rcmFmeaCommonDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFmeaCrityListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteList(String deleteRow)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String question_id=deleteRow;
    	
    	query.append("DELETE FROM TARCMCRITY					");
    	query.append("WHERE rcmcrity_id 	= '"+question_id+"'	");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	if (!"".equals(rcmFmeaCrityListDTO.getRcmcrityId()))
        {
            query.getAndQuery("rcmcrity_id", rcmFmeaCrityListDTO.getRcmcrityId());
            return query.toString();
        }
    	query.getAndQuery("rcmfmea_id", rcmFmeaCommonDTO.getRcmfmeaId());
    	
    	return query.toString();
    }

	@Override
	public void insertCrity(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMCRITY (                                      ");
    	query.append("    comp_no,        rcmcrity_id,      rcmfmea_id,             ");
    	query.append("    rcmlist_id,     col_name,         row_name,               ");
    	query.append("    crityvalue,     critycolor,       is_critical,            ");
    	query.append("    remark,         crity_lvl,                    	 		");
    	query.append("    crityvalue_id                             				");
    	query.append("    )															");
    	query.append(" SELECT ?,  			sqarcmcrity_id.nextval, ?,				");
    	query.append("        ?,  			a.col_name, 			a.row_name,		");
    	query.append("        a.crityvalue, a.critycolor, 			a.is_critical,	");
    	query.append("        '',  			a.crity_lvl, 			 				");
    	query.append("        a.crityvalue_id										");
    	query.append(" FROM TACRITYVALUE a											");
    	query.append(" WHERE a.crityvalue_id = ?									");


    	
    	Object[] objects = new Object[] {
    			rcmFmeaCommonDTO.getCompNo(),
    			rcmFmeaCommonDTO.getRcmfmeaId(),
    			rcmFmeaCommonDTO.getRcmlistId(),
    			rcmFmeaCrityListDTO.getCrityvalueId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	
	public void deleteCrity(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

    	
    	query.append("DELETE FROM TARCMCRITY			");
    	query.append("WHERE rcmfmea_id 		= ?			");
    	query.append("  AND rcmlist_id 		= ?			");
    	query.append("  AND crityvalue_id 	= ?			");
    	query.append("  AND comp_no 		= ?			");
    	
    	Object[] objects = new Object[] {
    			rcmFmeaCommonDTO.getRcmfmeaId(),
    			rcmFmeaCommonDTO.getRcmlistId(),
    			rcmFmeaCrityListDTO.getCrityvalueId(),
    			rcmFmeaCommonDTO.getCompNo()
    	};
    	
    	this.getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public String findTotalCount(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO,
			User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		String compNo = rcmFmeaCommonDTO.getCompNo();
        
		query.append("SELECT 											");
        query.append("  COUNT(1)										");
        query.append("FROM   TARCMCRITY x  INNER JOIN TARCMLIST y ON x.rcmlist_id = y.rcmlist_id        ");
        query.append("       INNER JOIN TACRITYVALUE a ON a.crityvalue_id = x.crityvalue_id       		");
        query.append("WHERE  x.comp_no = '"+compNo+"'						");
        query.append(this.getWhere(rcmFmeaCommonDTO,rcmFmeaCrityListDTO));

	    List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    
	}

	
}