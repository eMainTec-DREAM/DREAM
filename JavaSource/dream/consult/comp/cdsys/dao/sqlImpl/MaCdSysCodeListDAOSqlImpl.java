package dream.consult.comp.cdsys.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.cdsys.dao.MaCdSysCodeListDAO;
import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 detail-code 목록 dao
 * @author  kim21017
 * @version $Id: MaCdSysCodeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maCdSysCodeListDAOTarget"
 * @spring.txbn id="maCdSysCodeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaCdSysCodeListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaCdSysCodeListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaCdSysCodeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCommonDTO
     * @param maCdSysCodeListDTO
     * @return List
     */
    public List findCodeList(MaCdSysCommonDTO maCdSysCommonDTO, MaCdSysCodeListDTO maCdSysCodeListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("       '' seqNo,									");
        query.append("       '' isDelCheck,								");
        query.append("       x.cdsysm_id			cdSysMId,			");
        query.append("       x.cdsysd_id			cdSysDId,			");
        query.append("       x.CDSYSD_NO			code,				");
        //query.append("       description			codeDesc,			");
        query.append("       (select aa.key_name                ");
        query.append("         from talang aa                   ");
        query.append("         where  lang = '"+maCdSysCommonDTO.getUserLang()+"'         ");
        query.append("             and x.key_type = aa.key_type ");
        query.append("             and x.key_no = aa.key_no) as codeDesc,   ");
        query.append("       x.remark				remark,				");
        query.append("       x.ord_no				ordNo,				");
        query.append("       x.is_use 				isUse				");
        query.append("       ,x.param1 				param1				");
        query.append("       ,x.param2 				param2				");
        query.append("       ,x.param3 				param3				");
        query.append("FROM   TACDSYSD x									");
        query.append("WHERE  1 = 1                                      ");
        query.append(this.getWhere(maCdSysCommonDTO,maCdSysCodeListDTO));
        query.append("ORDER BY x.ord_no									");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaCdSysCodeListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteCodeList(String deleteRow, String deleteRowsExt)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String cdSysMId=deleteRow;
    	String cdSysDId=deleteRowsExt;
    	
    	query.append("DELETE FROM TACDSYSD					");
    	query.append("WHERE cdsysm_id 	= '"+cdSysMId+"'	");
    	query.append("  AND cdsysd_id 	= '"+cdSysDId+"'	");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaCdSysCommonDTO maCdSysCommonDTO, MaCdSysCodeListDTO maCdSysCodeListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndNumKeyQuery("x.cdsysm_id", maCdSysCommonDTO.getCdSysMId());
    	if (!"".equals(maCdSysCodeListDTO.getCdSysDId()))
        {
            query.getAndNumKeyQuery("x.cdsysd_id", maCdSysCodeListDTO.getCdSysDId());
            return query.toString();
        }                                                                                                                     
    	
    	return query.toString();
    }
}