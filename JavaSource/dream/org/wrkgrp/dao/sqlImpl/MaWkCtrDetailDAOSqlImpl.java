package dream.org.wrkgrp.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.org.wrkgrp.dao.MaWkCtrDetailDAO;
import dream.org.wrkgrp.dto.MaWkCtrDetailDTO;

/**
 * 작업그룹 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maWkCtrDetailDAOTarget"
 * @spring.txbn id="maWkCtrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWkCtrDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWkCtrDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param wkCtrId
     * @return
     */
    public MaWkCtrDetailDTO findDetail(String compNo, String wkCtrId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT								                    ");
        query.append("       x.wkctr_id                             wkCtrId,    ");
        query.append("       x.wkctr_no                             wkCtrNo,    ");
        query.append("       x.description	                        wkCtrDesc,  ");
        query.append("       x.p_wkctr_id                           paWkCtrId,  ");
        query.append("       (SELECT description								");
        query.append("          FROM TAWKCTR									");
        query.append("			WHERE comp_no = x.comp_no						");
        query.append("			AND wkctr_id = x.p_wkctr_id) 		paWkCtrDesc,");
        query.append("       x.ord_no                               ordNo,      ");
        query.append("       x.is_use			                    isUse,      ");
        query.append("       x.remark			                    remark      ");
        query.append("FROM   TAWKCTR x						                    ");
        query.append("WHERE  x.comp_no  = '"+compNo+"'							");
        query.append("  AND  x.wkctr_id = '"+wkCtrId+"'							");
    
        MaWkCtrDetailDTO maWkCtrDetailDTO = 
        		(MaWkCtrDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWkCtrDetailDTO()));

        
        return maWkCtrDetailDTO;
    }
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWkCtrDetailDTO
     * @return
     */
    public int insertDetail(MaWkCtrDetailDTO maWkCtrDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAWKCTR(                                      ");
    	query.append("   comp_no,    wkctr_id,    wkctr_no, description,		");
    	query.append("	 full_desc,  p_wkctr_id,  is_use,   ord_no,     remark  ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,          ?,	           ?,       ?,                  ");
    	query.append("	 ?,          ISNULL(?, '0'),  ?,       ?,		?	        ");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			maWkCtrDetailDTO.getCompNo(),
    			maWkCtrDetailDTO.getWkCtrId(),
    			maWkCtrDetailDTO.getWkCtrNo(),
    			maWkCtrDetailDTO.getWkCtrDesc(),
    			maWkCtrDetailDTO.getWkCtrNo()+", "+maWkCtrDetailDTO.getWkCtrDesc(),
    			maWkCtrDetailDTO.getPaWkCtrId(),
    			maWkCtrDetailDTO.getIsUse(),
    			maWkCtrDetailDTO.getOrdNo(),
    			maWkCtrDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWkCtrDetailDTO
     * @return
     */
    public int updateDetail(MaWkCtrDetailDTO maWkCtrDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAWKCTR SET	                ");
    	query.append("	     wkctr_no       = ?,	        ");
    	query.append("	     description    = ?,	        ");
    	query.append("	     p_wkctr_id     = ISNULL(?, 0),	");
    	query.append("	     is_use	        = ?,	        ");
    	query.append("	     full_desc	    = ?,	        ");
    	query.append("	     remark	        = ?,	        ");
    	query.append("	     ord_no         = ?		        ");
    	query.append("WHERE  comp_no        = ?	            ");
    	query.append("  AND  wkctr_id       = ?	            ");
    	
    	Object[] objects = new Object[] {
    			maWkCtrDetailDTO.getWkCtrNo(),
    			maWkCtrDetailDTO.getWkCtrDesc(),
    			maWkCtrDetailDTO.getPaWkCtrId(),
    			maWkCtrDetailDTO.getIsUse(),
    			maWkCtrDetailDTO.getWkCtrNo()+", "+maWkCtrDetailDTO.getWkCtrDesc(),
    			maWkCtrDetailDTO.getRemark(),
    			maWkCtrDetailDTO.getOrdNo(),
    			maWkCtrDetailDTO.getCompNo(),
    			maWkCtrDetailDTO.getWkCtrId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * valid wkCtrNo
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maWkCtrDetailDTO
     * @return
     */
    public String validWkCtrNo(MaWkCtrDetailDTO maWkCtrDetailDTO)
    {
        
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                        ");
        query.append("FROM   TAWKCTR x                        ");
        query.append("WHERE  x.comp_no  = '" + maWkCtrDetailDTO.getCompNo() + "' ");
        query.append("  AND  x.wkctr_no = '" + maWkCtrDetailDTO.getWkCtrNo() + "'");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
}