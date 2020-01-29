package dream.part.rec.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.rec.dao.MaPtRecSerialListDAO;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;

/**
 * 구매입고 item 목록 dao
 * @author  kim21017
 * @version $Id: MaPtRecSerialListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPtRecSerialListDAOTarget"
 * @spring.txbn id="maPtRecSerialListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtRecSerialListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtRecSerialListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPtRecSerialListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecCommonDTO
     * @param maPtRecSerialListDTO
     * @return List
     */
    public List findItemList(MaPtRecCommonDTO maPtRecCommonDTO, MaPtRecSerialListDTO maPtRecSerialListDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPtRecCommonDTO.getCompNo();
        query.append(" SELECT 		");
        query.append("       '' seqNo                                        		");
        query.append("       ,'' isDelCheck		");
        query.append("       ,serial_no serialNo		");
        query.append("       ,remark remark		");
        query.append("       ,prreclist_serial_id prreclistserialId                                		");
        query.append("       ,prreclist_id prreclistId                                		");
        query.append(" FROM TAPTPRRECLIST_SERIAL x		");
        query.append("WHERE x.comp_no = '"+compNo+"'						");
        query.append(this.getWhere(maPtRecCommonDTO,maPtRecSerialListDTO));
        query.append("ORDER BY x.serial_no								");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPtRecSerialListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow, String deleteRowsExt)
    {
    	QueryBuffer query = new QueryBuffer();

    	String prreclistSerialId=deleteRow;
    	String prreclistId=  deleteRowsExt; 
    	query.append("DELETE FROM TAPTPRRECLIST_SERIAL						");
    	query.append("WHERE prreclist_serial_id 	= '"+prreclistSerialId+"'		");
    	query.append("AND prreclist_id 	= '"+prreclistId+"'		");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPtRecCommonDTO maPtRecCommonDTO, MaPtRecSerialListDTO maPtRecSerialListDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.prreclist_id", maPtRecCommonDTO.getPrRecListId());
   	if (!"".equals(maPtRecSerialListDTO.getPrreclistSerialId()))
        {
            query.getAndQuery("x.prreclist_serial_id", maPtRecSerialListDTO.getPrreclistSerialId());
            return query.toString();
        }
    	
    	return query.toString();
    }
}