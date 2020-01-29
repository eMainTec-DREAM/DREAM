package dream.mgr.cdsys.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.cdsys.dao.MgrCdSysCodeListDAO;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * 시스템코드 detail-code 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrCdSysCodeListDAOTarget"
 * @spring.txbn id="mgrCdSysCodeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCdSysCodeListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrCdSysCodeListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCommonDTO
     * @param mgrCdSysCodeListDTO
     * @return List
     */
    public List findCodeList(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysCodeListDTO mgrCdSysCodeListDTO)
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
        query.append("         where  lang = '"+mgrCdSysCommonDTO.getUserLang()+"'         ");
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
        query.append(this.getWhere(mgrCdSysCommonDTO,mgrCdSysCodeListDTO));
        
        query.getOrderByQuery("x.cdsysd_id","x.ord_no", mgrCdSysCodeListDTO.getOrderBy(), mgrCdSysCodeListDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(mgrCdSysCodeListDTO.getIsLoadMaxCount(), mgrCdSysCodeListDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id: MgrCdSysCodeListDAO.java,v 1.0 20155/12/02 08:25:47 youngjoo38 Exp $
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
    
    private String getWhere(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysCodeListDTO mgrCdSysCodeListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndNumKeyQuery("x.cdsysm_id", mgrCdSysCommonDTO.getCdSysMId());
    	if (!"".equals(mgrCdSysCodeListDTO.getCdSysDId()))
        {
            query.getAndNumKeyQuery("x.cdsysd_id", mgrCdSysCodeListDTO.getCdSysDId());
            return query.toString();
        }
    	
    	query.getAndQuery("x.list_type", mgrCdSysCodeListDTO.getListType());
    	
    	query.getAndQuery("x.cdsysd_no", mgrCdSysCodeListDTO.getCdSysDNo());
    	
    	return query.toString();
    }

    @Override
    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO,
            MgrCdSysCodeListDTO mgrCdSysCodeListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("       count(1)                                   ");
        query.append("FROM   TACDSYSD x                                 ");
        query.append("WHERE  1 = 1                                      ");
        query.append(this.getWhere(mgrCdSysCommonDTO,mgrCdSysCodeListDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}